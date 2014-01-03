package org.iiitb.mt2013.os.pageAlgo.impl;

import java.util.List;

import org.iiitb.mt2013.os.algo.PageReplacementAlgo;
import org.iiitb.mt2013.os.conts.Constants;

public class ReplacementImplUtil
{
	static PageReplacementAlgo algo = null;

	public static PageReplacementAlgo getReplacementAlgo(List<Integer> pageNos, long memorySize, long pageSize,
			String algoName, long windowSize)
	{
		switch (algoName)
		{
		case Constants.CLOCK_REPLACEMENT_ALGO_NAME:
			algo = new ClockReplacmentImpl(pageNos, memorySize, pageSize, algoName);
			break;
		case Constants.COUNT_REPLACEMENT_ALGO_NAME:
			algo = new CountReplacmentImpl(pageNos, memorySize, pageSize, algoName);
			break;
		case Constants.FIFO_REPLACEMENT_ALGO:
			algo = new FIFOImpl(pageNos, memorySize, pageSize, algoName);
			break;
		case Constants.LRU_REPLACEMENT_ALGO:
			algo = new LRUImpl(pageNos, memorySize, pageSize, algoName);
			break;
		case Constants.OPTIMUM_REPLACE_ALGO:
			algo = new OptimumReplacmentImpl(pageNos, memorySize, pageSize, algoName);
			break;
		case Constants.WORKINGSETMODEL:
			algo = new WorkingSetModelImpl(pageNos, memorySize, pageSize, algoName, windowSize);
		}
		return algo;

	}
}
