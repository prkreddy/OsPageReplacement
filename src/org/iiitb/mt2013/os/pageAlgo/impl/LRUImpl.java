package org.iiitb.mt2013.os.pageAlgo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.mt2013.os.algo.PageReplacementAlgo;
import org.iiitb.mt2013.os.bean.Frame;
import org.iiitb.mt2013.os.bean.MemoryTemp;
import org.iiitb.mt2013.os.bean.Page;
import org.iiitb.mt2013.os.conts.Constants;
import org.iiitb.mt2013.os.view.MemoryPrint;

public class LRUImpl extends PageReplacementAlgo {

	List<Frame> circularQueue = new ArrayList<Frame>();

	Long pointer = Long.valueOf(0);
	MemoryPrint memoryPrint;
	int pageReference = -1;

	public LRUImpl(List<Integer> pageNos, long memorySize, long pageSize,
			String algoName) {
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

		int count = 0, minValue = Constants.MAX_VALUE;

		List<MemoryPrint> pgRefMemPrints = new ArrayList<MemoryPrint>();
		for (Page page : this.pages) {
			frame = this.memory.get(page.getAddress());
			/*
			 * checking whether coming page is already exists in Frames if
			 * exists setting the reference bit to
			 */
			if (frame != null) {
				frame.setPageReferenceBit(count);
				page.setPageFaultExists(false);
				pageReference = circularQueue.indexOf(frame);
				pgRefMemPrints.add(printFramesInMemory(page));
				++count;
				
				continue;
			}
			/*
			 * when the Queue Of frames is not full , assigning the page to a
			 * frame by setting the refernce bit to
			 */
			if (this.freeFrames > 0) {
				frame = new Frame(page.getAddress(), page.getSize());
				circularQueue.add(frame);
				frame.setPageReferenceBit(count);
				// pointer = (pointer + 1) % this.noOfFrames;
				pageReference = circularQueue.indexOf(frame);
				--this.freeFrames;
				page.setPageFaultExists(true);
				this.memory.add(frame);

			} else {

				// while (circularQueue.get(pointer.intValue())
				// .getPageReferenceBit() != 0) {
				// circularQueue.get(pointer.intValue())
				// .setPageReferenceBit(0);
				// pointer = (pointer + 1) % this.noOfFrames;
				// }
				minValue = Constants.MAX_VALUE;

				for (Frame tempFrame : circularQueue) {
					if (minValue > tempFrame.getPageReferenceBit()) {
						pointer = Long
								.valueOf(circularQueue.indexOf(tempFrame));
						minValue = tempFrame.getPageReferenceBit();
					}

				}

				page.setPageFaultExists(true);
				frame = new Frame(page.getAddress(), page.getSize());
				frame.setPageReferenceBit(count);
				this.memory.remove(circularQueue.get(pointer.intValue())
						.getAddress());
				circularQueue.set(pointer.intValue(), frame);
				this.memory.add(frame);
				pageReference = circularQueue.indexOf(frame);
				pointer = (pointer + 1) % this.noOfFrames;
			}
			pgRefMemPrints.add(printFramesInMemory(page));
			++count;
		}
		printPageFaultRate();
		return pgRefMemPrints;

	}

	public MemoryPrint printFramesInMemory(Page page) {
		memoryPrint = new MemoryPrint();
		memoryPrint.setPageReference(page.getAddress());
		System.out.println("PageReferece: " + page.getAddress());
		List<Frame> values = new ArrayList<Frame>();

		for (int i = 0; i < circularQueue.size(); i++) {
			values.add(new Frame(circularQueue.get(i)));
			System.out.println("Frame : " + circularQueue.get(i).getAddress()
					+ " :  preferenceBit: "
					+ circularQueue.get(i).getPageReferenceBit());
		}
		memoryPrint.setMemoryFrames(values);
		// memoryPrint.setPointer(pointer);
		memoryPrint.setPageReplacedPointer(pageReference);
		memoryPrint.setPageFault(page.isPageFaultExists());
		System.out.println("Pointer :" + pointer);
		System.out.println("\n\n");

		return memoryPrint;
	}
}
