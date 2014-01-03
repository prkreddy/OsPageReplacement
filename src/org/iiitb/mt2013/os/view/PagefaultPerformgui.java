package org.iiitb.mt2013.os.view;

import java.awt.Color;
import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import org.iiitb.mt2013.os.conts.Constants;

public class PagefaultPerformgui extends JFrame
{

	private static final long serialVersionUID = 1L;

	public PagefaultPerformgui(List<String> s, List<Double> pfault)
	{

		double min = Constants.MAX_VALUE, max = -1;
		int min_pointer = -1, max_pointer = -1;
		int min_2_pointer = -1;
		for (int i = 0; i < pfault.size(); i++)
		{
			if (min > pfault.get(i))
			{
				min = pfault.get(i);
				min_pointer = i;

			}
			if (max < pfault.get(i))
			{
				max = pfault.get(i);
				max_pointer = i;
			}

		}

		min = Constants.MAX_VALUE;
		for (int i = 0; i < pfault.size(); i++)
		{
			if (i == min_pointer)
				continue;
			if (min > pfault.get(i))
			{
				min = pfault.get(i);
				min_2_pointer = i;

			}

		}

		setVisible(true);
		setSize(1000, 600);
		Container panel = getContentPane();

		JButton jbutton;
		panel.setLayout(null);

		jbutton = new JButton();
		jbutton.setLocation(50, 50);
		jbutton.setSize(500, 30);
		jbutton.setText("------------------------------- PageFault Rates----------------------------------->");
		jbutton.setBorderPainted(false);
		jbutton.setEnabled(false);
		panel.add(jbutton);
		for (int i = 1; i <= s.size(); i++)
		{
			jbutton = new JButton();
			jbutton.setLocation(50, 50 + i * 40);
			jbutton.setSize((int) (10 * pfault.get(i - 1)), 30);
			jbutton.setText(s.get(i - 1));
			jbutton.setHorizontalTextPosition(SwingConstants.LEFT);
			jbutton.setToolTipText("PageFaultRate = "
					+ pfault.get(i - 1).toString().substring(0, pfault.get(i - 1).toString().indexOf(".")) + "%");

			if (min_2_pointer == i - 1)
			{
				jbutton.setBackground(Color.green);
			} else if (max_pointer == i - 1)
			{
				jbutton.setBackground(Color.RED);
			} else if (min_pointer == i - 1)
			{
				jbutton.setBackground(Color.ORANGE);
			}
			else{
				jbutton.setBackground(Color.DARK_GRAY);
			}

			jbutton.setEnabled(false);
			panel.add(jbutton);
		}

		setTitle("PageFault Rate performance of different algos Gui Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

}
