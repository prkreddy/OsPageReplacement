package org.iiitb.mt2013.os.pageAlgo.impl;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.mt2013.os.algo.PageReplacementAlgo;
import org.iiitb.mt2013.os.bean.Frame;
import org.iiitb.mt2013.os.bean.MemoryTemp;
import org.iiitb.mt2013.os.bean.Page;
import org.iiitb.mt2013.os.view.MemoryPrint;

public class CountReplacmentImpl extends PageReplacementAlgo {

	List<Frame> pageRef = new ArrayList<Frame>();
	List<Integer> pageStack = new ArrayList<Integer>();

	Long pointer = Long.valueOf(0);
	MemoryPrint memoryPrint;
	int pageReference = -1;

	public CountReplacmentImpl(List<Integer> pageNos, long memorySize,
			long pageSize, String algoName) {

		this.algoName = algoName;
		if (pageNos.size() > 0) {
			this.pages = new ArrayList<Page>();
		}

		for (Integer pageNo : pageNos) {
			this.pages.add(new Page(pageNo, pageSize));
		}
		this.memory = new MemoryTemp(memorySize);
		if (this.pages.size() > 0) {
			this.noOfFrames = memorySize / pageSize;
			this.freeFrames = this.noOfFrames;
		} else {

			// pf no pages requested
		}
	}

	@Override
	public List<MemoryPrint> executeAlgo() throws InvalidMemoryUnitException {

		Frame frame;

		List<MemoryPrint> pgRefMemPrints = new ArrayList<MemoryPrint>();

		for (Page page : this.pages) {
			frame = this.memory.get(page.getAddress());
			/*
			 * checking whether coming page is already exists in Frames if
			 * exists setting the reference bit to
			 */
			if (frame != null) {

				int i;
				i = frame.getPageReferenceBit();
				frame.setPageReferenceBit(++i);
				page.setPageFaultExists(false);
				pageReference = pageRef.indexOf(frame);
				pgRefMemPrints.add(printFramesInMemory(page));
				
				continue;
			}
			/*
			 * when the Queue Of frames is not full , assigning the page to a
			 * frame by setting the reference bit to
			 */
			if (this.freeFrames > 0) {
				frame = new Frame(page.getAddress(), page.getSize());
				pageRef.add(frame);
				pageReference = pageRef.indexOf(frame);

				pointer = (pointer + 1) % this.noOfFrames;
				--this.freeFrames;
				page.setPageFaultExists(true);
				this.memory.add(frame);

			} else {

				int max = 0;
				int indx = 0;
				int ref = 0;

				while (indx < this.noOfFrames) {

					if (pageRef.get(indx).getPageReferenceBit() > max) {

						max = pageRef.get(indx).getPageReferenceBit();
						ref = indx;

					}
					indx++;
				}

				/*
				 * if all the pages have the same count then follow the old page
				 * to be retrieved
				 */

				page.setPageFaultExists(true);

				this.memory.remove(pageRef.get(ref).getAddress());
				frame = new Frame(page.getAddress(), page.getSize());
				frame.setPageReferenceBit(1);
				pageRef.set(ref, frame);
				this.memory.add(frame);
				pageReference = pageRef.indexOf(frame);

				pointer = (pointer + 1) % this.noOfFrames;
			}
			pgRefMemPrints.add(printFramesInMemory(page));

		}
		printPageFaultRate();
		return pgRefMemPrints;

	}

	public MemoryPrint printFramesInMemory(Page page) {
		memoryPrint = new MemoryPrint();
		memoryPrint.setPageReference(page.getAddress());
		System.out.println("PageReferece: " + page.getAddress());
		List<Frame> values = new ArrayList<Frame>();

		for (int i = 0; i < pageRef.size(); i++) {
			values.add(new Frame(pageRef.get(i)));
			System.out.println("Frame : " + pageRef.get(i).getAddress()
					+ " :  preferenceBit: "
					+ pageRef.get(i).getPageReferenceBit());
		}
		memoryPrint.setMemoryFrames(values);
		memoryPrint.setPointer(pointer);
		memoryPrint.setPageReplacedPointer(pageReference);
		memoryPrint.setPageFault(page.isPageFaultExists());
		System.out.println("Pointer :" + pointer);
		System.out.println("\n\n");

		return memoryPrint;
	}
}