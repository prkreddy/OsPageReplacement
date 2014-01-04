package org.iiitb.mt2013.os.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.iiitb.mt2013.os.bean.Frame;
import org.iiitb.mt2013.os.conts.Constants;

public class MemoryGraphicView extends JPanel
{

	private Map<String, List<MemoryPrint>> algoMememoryPrints;

	private Map<String, Long> noOfFramesMap;

	public Map<String, Long> getNoOfFramesMap( )
	{
		return noOfFramesMap;
	}

	public void setNoOfFramesMap(Map<String, Long> noOfFramesMap)
	{
		this.noOfFramesMap = noOfFramesMap;
	}

	private List<String> algoNames;

	public List<String> getAlgoNames( )
	{
		return algoNames;
	}

	public void setAlgoNames(List<String> algoNames)
	{
		this.algoNames = algoNames;
	}

	private Map<String, Long> hitCount;

	public Map<String, Long> getHitCount( )
	{
		return hitCount;
	}

	public void setHitCount(Map<String, Long> hitCount)
	{
		this.hitCount = hitCount;
	}

	public List<MemoryPrint> getMemoryPrints( )
	{
		return memoryPrints;
	}

	public void setMemoryPrints(List<MemoryPrint> memoryPrints)
	{
		this.memoryPrints = memoryPrints;
	}

	private long noOfFrames;

	private long noOfPageRefereces;

	public long getNoOfPageRefereces( )
	{
		return noOfPageRefereces;
	}

	public void setNoOfPageRefereces(long noOfPageRefereces)
	{
		this.noOfPageRefereces = noOfPageRefereces;
	}

	List<MemoryPrint> memoryPrints = null;

	public long getNoOfFrames( )
	{
		return noOfFrames;
	}

	public void setNoOfFrames(long noOfFrames)
	{
		this.noOfFrames = noOfFrames;
	}

	public MemoryGraphicView()
	{
		algoMememoryPrints = new HashMap<String, List<MemoryPrint>>();
		hitCount = new HashMap<String, Long>();
		algoNames = new ArrayList<String>();
		noOfFramesMap=new HashMap<String, Long>();
	}

	public Map<String, List<MemoryPrint>> getAlgoMememoryPrints( )
	{
		return algoMememoryPrints;
	}

	public void setAlgoMememoryPrints(Map<String, List<MemoryPrint>> algoMememoryPrints)
	{
		this.algoMememoryPrints = algoMememoryPrints;
	}

	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);

		this.setBackground(Color.WHITE);

		draw(g);
	}

	public void draw(Graphics g)
	{

		int xcord = 0, ycord = 200, initialxcord = 200, initialYcord = 50, maxYcord = 200, elementWidth = 0, maxElementWidth = 0;
		Font font = g.getFont();
		FontMetrics fm = g.getFontMetrics();
		// Iterator<String> iterator = algoMememoryPrints.keySet().iterator();
		List<Frame> frames = null;
		Long address = 0l, pointer = 0l;
		boolean pageFault = false;
		int pageReferenced = -1;
		int i = 0, j = 0;

		String pageRef, memoryElement = "";
		boolean printPageRef = false;

		drawHeaderLines(g, fm, font);
		long noOFframes = 0;
		for (String algoName : algoNames)
		{
			noOFframes = this.getNoOfFramesMap().get(algoName);
			// algoName = iterator.next();
			g.setColor(Color.BLACK);
			memoryPrints = algoMememoryPrints.get(algoName);

			// to add gap between algorithms view's
			maxYcord = maxYcord + 40;

			if (!printPageRef)
			{
				g.setFont(font);
				// finding max width of rectangle to be drawn
				for (i = 0; i < memoryPrints.size(); i++)
				{
					pageRef = Long.valueOf(memoryPrints.get(i).getPageReference()).toString();
					elementWidth = fm.stringWidth(pageRef + "*>");
					if (maxElementWidth < elementWidth)
					{
						maxElementWidth = elementWidth;
					}
				}
				setGraphicsFont(g, Color.BLUE, new Font("TimesRoman", Font.BOLD, 16));
				g.drawString("PageReferences", 0,
						ycord + (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm.getHeight()) / 2);
				g.setFont(font);
				// printing the page references in the first line
				for (i = 0; i < memoryPrints.size(); i++)
				{
					pageRef = Long.valueOf(memoryPrints.get(i).getPageReference()).toString();
					xcord = initialxcord
							+ i
							* (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
					g.setColor(Color.DARK_GRAY);
					g.drawString(pageRef, xcord + Constants.RECT_WITHOUT_ELEMENT_WIDTH / 2, ycord
							+ (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm.getHeight()) / 2);
				}

				printPageRef = true;
			}
			g.setFont(new Font("TimesRoman", Font.BOLD, 16));
			g.drawString(algoName, 0, maxYcord + maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH
					+ Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
			if (memoryPrints != null)
			{
				initialYcord = maxYcord + Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS;
				g.setFont(font);
				for (i = 0; i < memoryPrints.size(); i++)
				{
					frames = memoryPrints.get(i).getMemoryFrames();
					pointer = memoryPrints.get(i).getPointer();
					pageFault = memoryPrints.get(i).isPageFault();
					pageReferenced = memoryPrints.get(i).getPageReplacedPointer();

					for (j = -1; j < noOFframes; j++)
					{

						// memory prints view varies with maxElementWidth+
						// Constants.RECT_WITHOUT_ELEMENT_WIDTH +
						// Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS
						xcord = initialxcord
								+ i
								* (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
						ycord = initialYcord
								+ (j + 1)
								* (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
						if (maxYcord < ycord)
						{
							maxYcord = ycord;
						}

						if (j > -1)
						{
							if (pageReferenced != j)
								g.setColor(Color.CYAN);
							else
								g.setColor(Color.LIGHT_GRAY);
						} else
						{
							if (pageFault)
								g.setColor(Color.RED);
							else
								g.setColor(Color.green);
						}
						g.fillRect(xcord, ycord, maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH,
								maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH);

						// end of filling the rectangle

						g.setColor(Color.BLACK);

						if (j < frames.size() && j > -1)
						{
							address = frames.get(j).getAddress();
							if (Constants.CLOCK_REPLACEMENT_ALGO_NAME.equals(algoName.split("_")[0]))
							{

								if (frames.get(j).getPageReferenceBit() == 1)
									if (pointer == j)
									{

										memoryElement = ">" + address + "*";
									} else
									{
										memoryElement = address + "*";
									}

								else
								{
									if (pointer == j)
									{
										memoryElement = ">" + address + "";
									} else
										memoryElement = address + "";
								}
							} else
							{
								memoryElement = address + "";
							}
						} else if (j > -1)
						{
							if (Constants.CLOCK_REPLACEMENT_ALGO_NAME.equals(algoName.split("_")[0]) && pointer == j)

								memoryElement = ">";
							else
								memoryElement = "";
						} else
						{
							if (pageFault)
								memoryElement = "F";
							else
								memoryElement = "H";
						}

						g.drawString(memoryElement, xcord + Constants.RECT_WITHOUT_ELEMENT_WIDTH / 2, ycord
								+ (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm.getHeight()) / 2);

					}

				}

				xcord = initialxcord
						+ i
						* (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
				ycord = initialYcord + (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm.getHeight()) / 2;

				setGraphicsFont(g, Color.MAGENTA, new Font("TimesRoman", Font.BOLD, 16));

				g.drawString("HitCount: " + this.getHitCount().get(algoName), xcord, ycord);

				g.drawString(
						"PageFaultRate: " + (double) (this.getNoOfPageRefereces() - this.getHitCount().get(algoName))
								/ this.getNoOfPageRefereces(), xcord, ycord
								+ (maxElementWidth + Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm.getHeight()) / 2);

			}

		}
	}

	private void setGraphicsFont(Graphics g, Color c, Font f)
	{
		g.setFont(f);
		g.setColor(c);

	}

	private void drawHeaderLines(Graphics g, FontMetrics fm, Font font)
	{
		int height = 0;
		setGraphicsFont(g, Color.BLUE, new Font("TimesRoman", Font.BOLD, 16));
		g.drawString("PAGE REPLACEMENT ALGORITHMS", 10, 20);
		height = fm.getHeight();

		setGraphicsFont(g, Color.black, font);
		g.drawString(" In (ClockPolicy) '*' indicates usebit=1 in a frame otherwise usebit=0", 0, 30 + height);
		g.drawString(
				" In (ClockPolicy) '>' indicates next frame to be considered for page replacement if its usebit=0", 0,
				30 + height + fm.getHeight());
	}

}
