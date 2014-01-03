package org.iiitb.mt2013.os;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkSetActionListener implements ActionListener
{

	HomeFramePanel panel;

	public WorkSetActionListener(HomeFramePanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		if(panel.getWorkSetModel().isSelected()){
			panel.getWindowSizeField().setEditable(true);
		}else{
			panel.getWindowSizeField().setEditable(false);
		}

	}

}
