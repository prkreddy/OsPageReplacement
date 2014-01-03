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
import org.iiitb.mt2013.os.view.MemoryPrint;

public class OptimumReplacmentImpl extends PageReplacementAlgo {

	List<Frame> circularQueue = new ArrayList<Frame>();

	Map<String, List<Integer>> pageReferencesMap;

	Long pointer = Long.valueOf(0);
	MemoryPrint memoryPrint;
	int pageReferenced = -1;

	public OptimumReplacmentImpl(List<Integer> pageNos, long memorySize,
			long pageSize, String algoName) {
		int count = 0;
		pageReferencesMap = new HashMap<String, List<Integer>>();
		this.algoName = algoName;
		if (pageNos.size() > 0) {
			this.pages = new ArrayList<Page>();
		}
		List<Integer> tempList;
		for (Integer pageNo : pageNos) {
			this.pages.add(new Page(pageNo, pageSize));
			if (pageReferencesMap.get(pageNo + "") == null) {
				tempList = new ArrayList<Integer>();
				tempList.add(count);
				pageReferencesMap.put(pageNo + "", tempList);
			} else {
				pageReferencesMap.get(pageNo + "").add(count);
			}
			count++;
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
		int count = 0;
		for (Page page : this.pages) {
			frame = this.memory.get(page.getAddress());
			int culprit = -1, max = -1;
			/*
			 * checking whether coming page is already exists in Frames if
			 * exists setting the reference bit to
			 */
			if (frame != null) {
				frame.setPageReferenceBit(1);
				page.setPageFaultExists(false);
				pageReferenced = circularQueue.indexOf(frame);
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
				pageReferenced = circularQueue.indexOf(frame);
				// pointer = (pointer + 1) % this.noOfFrames;
				--this.freeFrames;
				page.setPageFaultExists(true);
				this.memory.add(frame);
			} else {

				for (int i = 0; i < circularQueue.size(); i++) {

					boolean found = false;

					for (Integer temp : pageReferencesMap.get(circularQueue
							.get(i).getAddress() + "")) {

						if (temp < count) {
							continue;
						} else {
							if (max < temp) {
								max = temp;
								culprit = i;
							}
							found = true;
							break;
						}

					}

					if (!found) {
						culprit = i;
						break;
					}

				}
				page.setPageFaultExists(true);
				pageReferenced = culprit;
				frame = new Frame(page.getAddress(), page.getSize());
				this.memory.remove(circularQueue.get(culprit).getAddress());

				circularQueue.set(culprit, frame);
				this.memory.add(frame);
				// pointer = (pointer + 1) % this.noOfFrames;
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
		memoryPrint.setPointer(pointer);
		memoryPrint.setPageReplacedPointer(pageReferenced);
		memoryPrint.setPageFault(page.isPageFaultExists());
		System.out.println("Pointer :" + pointer);
		System.out.println("\n\n");

		return memoryPrint;
	}
}
