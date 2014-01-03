package org.iiitb.mt2013.os;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.iiitb.mt2013.os.conts.Constants;

public class ResetActionLister implements ActionListener
{
	HomeFramePanel panel;

	public ResetActionLister(HomeFramePanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		panel.getNoOFFrameField().setText("");
		panel.getPageReferencesField().setText("");
		panel.getNoOFFrameField1().setText("");
		panel.getBtnGroup().clearSelection();
		// panel.getCompareBeladys().setSelected(false);
		// panel.getAllPolicies().setSelected(false);
		// panel.getSpecificPolicies().setSelected(false);
		panel.getBtnGroup1().clearSelection();

		for (String name : Constants.ALGORITHMS_NAMES)
		{
			panel.getCheckBox(name).setSelected(false);
			panel.getCheckBox(name).setEnabled(false);
		}

		panel.getNoOFFrameField().setEditable(false);
		panel.getPageReferencesField().setEditable(false);
		panel.getNoOFFrameField1().setEditable(false);
		panel.getButton().setEnabled(false);
		panel.getAllPolicies().setEnabled(false);
		panel.getSpecificPolicies().setEnabled(false);
		panel.getReset().setEnabled(false);
		panel.getWindowSizeField().setEditable(false);
//		panel.dispose();
//		panel = new HomeFramePanel();
	}

}
