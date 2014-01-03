package org.iiitb.mt2013.os.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.iiitb.mt2013.os.conts.Constants;

public class MemoryGraphicViewBakup extends JPanel {

	private Map<String, List<MemoryPrint>> algoMememoryPrints;

	private Map<String, Double> pageFaultRate;

	private long noOfFrames;

	List<MemoryPrint> memoryPrints = null;

	public long getNoOfFrames() {
		return noOfFrames;
	}

	public void setNoOfFrames(long noOfFrames) {
		this.noOfFrames = noOfFrames;
	}

	public MemoryGraphicViewBakup() {
		algoMememoryPrints = new HashMap<String, List<MemoryPrint>>();
		pageFaultRate = new HashMap<String, Double>();
	}

	public Map<String, List<MemoryPrint>> getAlgoMememoryPrints() {
		return algoMememoryPrints;
	}

	public void setAlgoMememoryPrints(
			Map<String, List<MemoryPrint>> algoMememoryPrints) {
		this.algoMememoryPrints = algoMememoryPrints;
	}

	public Map<String, Double> getPageFaultRate() {
		return pageFaultRate;
	}

	public void setPageFaultRate(Map<String, Double> pageFaultRate) {
		this.pageFaultRate = pageFaultRate;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		this.setBackground(Color.WHITE);

		draw(g);
	}

	public void draw(Graphics g) {

		int xcord = 0, ycord = 50, initialxcord = 200, initialYcord = 50, maxYcord = 50, elementWidth = 0, maxElementWidth = 0;
		Font font = g.getFont();
		FontMetrics fm = g.getFontMetrics();
		Iterator<String> iterator = algoMememoryPrints.keySet().iterator();

		String algoName, pageRef, memoryElement = "";
		boolean printPageRef = false;

		drawHeaderLines(g, fm, font);

		while (iterator.hasNext()) {
			algoName = iterator.next();
			g.setColor(Color.BLACK);
			memoryPrints = algoMememoryPrints.get(algoName);

			// to add gap between algorithms view's
			maxYcord = maxYcord + 40;

			if (!printPageRef) {
				g.setFont(font);
				// finding max width of rectangle to be drawn
				for (int i = 0; i < memoryPrints.size(); i++) {
					pageRef = Long.valueOf(
							memoryPrints.get(i).getPageReference()).toString();
					elementWidth = fm.stringWidth(pageRef + "*>");
					if (maxElementWidth < elementWidth) {
						maxElementWidth = elementWidth;
					}
				}
				setGraphicsFont(g, Color.BLUE, new Font("TimesRoman",
						Font.BOLD, 16));
				g.drawString(
						"PageReferences",
						0,
						ycord
								+ (maxElementWidth
										+ Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm
											.getHeight()) / 2);
				g.setFont(font);
				// printing the page references in the first line
				for (int i = 0; i < memoryPrints.size(); i++) {
					pageRef = Long.valueOf(
							memoryPrints.get(i).getPageReference()).toString();
					xcord = initialxcord
							+ i
							* (maxElementWidth
									+ Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
					g.setColor(Color.DARK_GRAY);
					g.drawString(
							pageRef,
							xcord + Constants.RECT_WITHOUT_ELEMENT_WIDTH / 2,
							ycord
									+ (maxElementWidth
											+ Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm
												.getHeight()) / 2);
				}

				printPageRef = true;
			}
			g.setFont(new Font("TimesRoman", Font.BOLD, 16));
			g.drawString(algoName, 0, maxYcord + maxElementWidth
					+ Constants.RECT_WITHOUT_ELEMENT_WIDTH
					+ Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
			if (memoryPrints != null) {
				initialYcord = maxYcord + Constants.RECT_WITHOUT_ELEMENT_WIDTH
						+ Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS;
				g.setFont(font);
				for (int i = 0; i < memoryPrints.size(); i++) {

					for (int j = 0; j < noOfFrames; j++) {

						// memory prints view varies with maxElementWidth+
						// Constants.RECT_WITHOUT_ELEMENT_WIDTH +
						// Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS
						xcord = initialxcord
								+ i
								* (maxElementWidth
										+ Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
						ycord = initialYcord
								+ j
								* (maxElementWidth
										+ Constants.RECT_WITHOUT_ELEMENT_WIDTH + Constants.GAP_BETWEEN_TWO_MEMORY_PRNTS);
						if (maxYcord < ycord) {
							maxYcord = ycord;
						}

						g.setColor(Color.CYAN);
						g.fillRect(xcord, ycord, maxElementWidth
								+ Constants.RECT_WITHOUT_ELEMENT_WIDTH,
								maxElementWidth
										+ Constants.RECT_WITHOUT_ELEMENT_WIDTH);
						g.setColor(Color.BLACK);

						if (j < memoryPrints.get(i).getMemoryFrames().size()) {
							if (algoName
									.equals(Constants.CLOCK_REPLACEMENT_ALGO_NAME)) {

								if (memoryPrints.get(i).getMemoryFrames()
										.get(j).getPageReferenceBit() == 1)
									if (memoryPrints.get(i).getPointer() == j) {
										memoryElement = ">"
												+ memoryPrints.get(i)
														.getMemoryFrames()
														.get(j).getAddress()
												+ "*";
									} else {
										memoryElement = memoryPrints.get(i)
												.getMemoryFrames().get(j)
												.getAddress()
												+ "*";
									}

								else {
									if (memoryPrints.get(i).getPointer() == j) {
										memoryElement = ">"
												+ memoryPrints.get(i)
														.getMemoryFrames()
														.get(j).getAddress()
												+ "";
									} else
										memoryElement = memoryPrints.get(i)
												.getMemoryFrames().get(j)
												.getAddress()
												+ "";
								}
							} else {
								memoryElement = memoryPrints.get(i)
										.getMemoryFrames().get(j).getAddress()
										+ "";
							}
						} else {
							if (algoName
									.equals(Constants.CLOCK_REPLACEMENT_ALGO_NAME)
									&& memoryPrints.get(i).getPointer() == j)

								memoryElement = ">";
							else
								memoryElement = "";
						}
						g.drawString(
								memoryElement,
								xcord + Constants.RECT_WITHOUT_ELEMENT_WIDTH
										/ 2,
								ycord
										+ (maxElementWidth
												+ Constants.RECT_WITHOUT_ELEMENT_WIDTH + fm
													.getHeight()) / 2);

					}

				}
			}

		}
	}

	private void setGraphicsFont(Graphics g, Color c, Font f) {
		g.setFont(f);
		g.setColor(c);

	}

	private void drawHeaderLines(Graphics g, FontMetrics fm, Font font) {
		int height = 0;
		setGraphicsFont(g, Color.BLUE, new Font("TimesRoman", Font.BOLD, 16));
		g.drawString(
				"PAGE REPLACEMENT ALGORITHMS MEMORY ALLOCATION WITH PAGE REFERENCES ",
				10, 20);
		height = fm.getHeight();

		setGraphicsFont(g, Color.black, font);
		g.drawString(
				" In (ClockPolicy) '*' indicates usebit=1 in a frame otherwise usebit=0",
				0, 20 + height);
		g.drawString(
				" In (ClockPolicy) '>' indicates next frame to be considered for page replacement if its usebit=0",
				0, 20 + height + fm.getHeight());
	}
}
