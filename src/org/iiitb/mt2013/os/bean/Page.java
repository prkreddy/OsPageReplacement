package org.iiitb.mt2013.os.bean;

import org.iiitb.model.bean.MemoryUnit;

public class Page extends MemoryUnit {

	boolean pageFaultExists;

	public Page(long address, long size) {
		super(address, size);
	}

	public boolean isPageFaultExists() {
		return pageFaultExists;
	}

	public void setPageFaultExists(boolean pageFaultExists) {
		this.pageFaultExists = pageFaultExists;
	}

}