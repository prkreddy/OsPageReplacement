package org.iiitb.mt2013.os.bean;

import java.util.Comparator;

import org.iiitb.model.bean.MemoryUnit;

public class Frame extends MemoryUnit implements Comparable<Frame>
{

	public Frame(long address, long size)
	{
		super(address, size);
		this.setPageReferenceBit(1);
	}

	public Frame(Frame frame)
	{
		super(frame.getAddress(), frame.getSize());
		this.setPageReferenceBit(frame.getPageReferenceBit());
	}

	private int pageReferenceBit; /* reference bit */

	public int getPageReferenceBit( )
	{
		return pageReferenceBit;
	}

	public void setPageReferenceBit(int pageReferenceBit)
	{
		this.pageReferenceBit = pageReferenceBit;
	}

	@Override
	public int compareTo(Frame o)
	{
		// TODO Auto-generated method stub
		return this.pageReferenceBit - o.pageReferenceBit;
	}

}
