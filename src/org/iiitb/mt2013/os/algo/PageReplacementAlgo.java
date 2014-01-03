package org.iiitb.mt2013.os.algo;

import java.util.List;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.mt2013.os.bean.MemoryTemp;
import org.iiitb.mt2013.os.bean.Page;
import org.iiitb.mt2013.os.view.MemoryPrint;

public abstract class PageReplacementAlgo
{

	public String algoName;

	protected List<Page> pages;

	protected long noOfFrames, freeFrames;

	protected MemoryTemp memory;

	public long hitCount;

	public long getHitCount( )
	{
		long count = 0;
		if (hitCount == 0)
		{

			for (Page page : pages)
			{
				if (!page.isPageFaultExists())
					count++;
			}
			hitCount = count;

		} else
			count = hitCount;
		return count;
	}

	public abstract List<MemoryPrint> executeAlgo( ) throws InvalidMemoryUnitException;

	public Double getPageFaultRate( )
	{

		int faultCount = pages.size() - Long.valueOf(getHitCount()).intValue();
		return ((double) faultCount) / pages.size();
	}

	public String getAlgoName( )
	{
		return algoName;
	}

	public void setAlgoName(String algoName)
	{
		this.algoName = algoName;
	}

	public void printPageFaultRate( )
	{
		int leng = 0;
		leng = getAlgoName().length() + "PageFaultRate: ".length() + getPageFaultRate().toString().length();
		for (int i = 0; i < leng; i++)
		{
			System.out.print("*");
		}

		System.out.println("\n" + getAlgoName() + " PageFaultRate: " + getPageFaultRate());
		for (int i = 0; i < leng; i++)
		{
			System.out.print("*");
		}
	}
}
