package org.iiitb.mt2013.os;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.iiitb.mt2013.os.conts.Constants;

public class PoliciesSelectionActionListener implements ActionListener
{

	HomeFramePanel panel;

	public PoliciesSelectionActionListener(HomeFramePanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (panel.getAllPolicies().isSelected())
		{
			System.out.println("all polices selected");
			for (String checkBox : Constants.ALGORITHMS_NAMES)
			{
				panel.getCheckBox(checkBox).setEnabled(true);
				panel.getCheckBox(checkBox).setSelected(true);
			}
			panel.getWindowSizeField().setEditable(true);

		} else if (panel.getSpecificPolicies().isSelected())
		{
			System.out.println("specific selected");
			for (String checkBox : Constants.ALGORITHMS_NAMES)
			{
				panel.getCheckBox(checkBox).setEnabled(true);
				panel.getCheckBox(checkBox).setSelected(false);
			}
			panel.getWindowSizeField().setEditable(false);
		}

	}

}
