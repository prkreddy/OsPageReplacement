package org.iiitb.mt2013.os;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.mt2013.os.algo.PageReplacementAlgo;
import org.iiitb.mt2013.os.conts.Constants;
import org.iiitb.mt2013.os.pageAlgo.impl.ReplacementImplUtil;
import org.iiitb.mt2013.os.view.MemoryGraphicView;
import org.iiitb.mt2013.os.view.MemoryPrint;
import org.iiitb.mt2013.os.view.PagefaultPerformgui;

public class PgReplaceSimulator implements ActionListener
{

	private List<Integer> pageReferences;

	private long memorySize;

	private long pageSize;

	private long windowSize;

	public long getWindowSize( )
	{
		return windowSize;
	}

	public void setWindowSize(long windowSize)
	{
		this.windowSize = windowSize;
	}

	public List<Integer> getPageReferences( )
	{
		return pageReferences;
	}

	public void setPageReferences(List<Integer> pageReferences)
	{
		this.pageReferences = pageReferences;
	}

	PageReplacementAlgo pageReplacementAlgo;

	public long getMemorySize( )
	{
		return memorySize;
	}

	public void setMemorySize(long memorySize)
	{
		this.memorySize = memorySize;
	}

	public long getPageSize( )
	{
		return pageSize;
	}

	public void setPageSize(long pageSize)
	{
		this.pageSize = pageSize;
	}

	public PageReplacementAlgo getPageReplacementAlgo( )
	{
		return pageReplacementAlgo;
	}

	public void setPageReplacementAlgo(PageReplacementAlgo pageReplacementAlgo)
	{
		this.pageReplacementAlgo = pageReplacementAlgo;
	}

	List<String> algoname = new ArrayList<String>();
	List<Double> pagefault = new ArrayList<Double>();

	public void startSimulator(List<String> algorithmNames)
	{

		List<MemoryPrint> memoryPrints;
		MemoryGraphicView memoryGraphicView = new MemoryGraphicView();

		try
		{

			for (String algoName : algorithmNames)
			{
				pageReplacementAlgo = ReplacementImplUtil.getReplacementAlgo(pageReferences, memorySize, pageSize,
						algoName, windowSize);

				memoryPrints = pageReplacementAlgo.executeAlgo();

				updateMemoryGraphicView(algoName, memoryPrints, memoryGraphicView, memorySize/pageSize);
				pagefault.add(pageReplacementAlgo.getPageFaultRate() * 100);
				algoname.add(algoName);
			}

		} catch (InvalidMemoryUnitException e)
		{
			e.printStackTrace();
		}

		buildFrame(memoryGraphicView);
	}

	public void startSimulator(List<String> algorithmNames, Long noOfFrame2)
	{

		List<MemoryPrint> memoryPrints;
		MemoryGraphicView memoryGraphicView = new MemoryGraphicView();

		try
		{
			int i = 0;
			for (String algoName : algorithmNames)
			{
				if (i % 2 == 0)
				{
					pageReplacementAlgo = ReplacementImplUtil.getReplacementAlgo(pageReferences, memorySize, 1,
							algoName, windowSize);
					memoryPrints = pageReplacementAlgo.executeAlgo();

					updateMemoryGraphicView(algoName + "_" + memorySize, memoryPrints, memoryGraphicView,memorySize);
					pagefault.add(pageReplacementAlgo.getPageFaultRate() * 100);
					algoname.add(algoName + "_" + memorySize);
				} else
				{
					pageReplacementAlgo = ReplacementImplUtil.getReplacementAlgo(pageReferences, noOfFrame2, 1,
							algoName, windowSize);
					memoryPrints = pageReplacementAlgo.executeAlgo();

					updateMemoryGraphicView(algoName + "_" + noOfFrame2, memoryPrints, memoryGraphicView,noOfFrame2);
					pagefault.add(pageReplacementAlgo.getPageFaultRate() * 100);
					algoname.add(algoName + "_" + noOfFrame2);
				}

				++i;
			}

		} catch (InvalidMemoryUnitException e)
		{
			e.printStackTrace();
		}

		buildFrame(memoryGraphicView);
	}

	void updateMemoryGraphicView(String algoName, List<MemoryPrint> memoryPrints, MemoryGraphicView memoryGraphicView, long noOfFrames)
	{

		memoryGraphicView.getAlgoMememoryPrints().put(algoName, memoryPrints);
		
		memoryGraphicView.getNoOfFramesMap().put(algoName, noOfFrames);

		memoryGraphicView.getAlgoNames().add(algoName);

		memoryGraphicView.getHitCount().put(algoName, pageReplacementAlgo.getHitCount());
	}

	void buildFrame(MemoryGraphicView memoryGraphicView)
	{

		memoryGraphicView.setNoOfFrames(memorySize / pageSize);
		memoryGraphicView.setNoOfPageRefereces(pageReferences.size());

		memoryGraphicView.setPreferredSize(new Dimension(200 + pageReferences.size() * 50, 5000));

		JFrame frame = new JFrame();

		JButton button = new JButton("PageFault Statistics");
		button.setLocation(100, 400);

		button.setSize(200, 25);

		memoryGraphicView.add(button);
		button.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane(memoryGraphicView);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 700);

		frame.getContentPane().add(scrollPane);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{

		new PagefaultPerformgui(algoname, pagefault);
	}

	// 2,3,2,1,5,2,4,5,3,2
}
