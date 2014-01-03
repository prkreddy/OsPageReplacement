package org.iiitb.mt2013.os.view;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.mt2013.os.bean.Frame;

public class MemoryPrint {

	private long pageReference;
	
	private boolean pageFault;

	private List<Frame> memoryFrames = new ArrayList<Frame>();

	private long pointer;
	
	private int pageReplacedPointer;

	public int getPageReplacedPointer() {
		return pageReplacedPointer;
	}

	public void setPageReplacedPointer(int pageReplacedPointer) {
		this.pageReplacedPointer = pageReplacedPointer;
	}

	public long getPageReference() {
		return pageReference;
	}

	public void setPageReference(long pageReference) {
		this.pageReference = pageReference;
	}

	public List<Frame> getMemoryFrames() {
		return memoryFrames;
	}

	public void setMemoryFrames(List<Frame> memoryFrames) {
		this.memoryFrames = memoryFrames;
	}

	public long getPointer() {
		return pointer;
	}

	public void setPointer(long pointer) {
		this.pointer = pointer;
	}

	public boolean isPageFault() {
		return pageFault;
	}

	public void setPageFault(boolean pageFault) {
		this.pageFault = pageFault;
	}

}
