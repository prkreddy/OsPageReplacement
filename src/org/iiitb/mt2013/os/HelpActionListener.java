package org.iiitb.mt2013.os;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HelpActionListener implements ActionListener
{

	public HelpActionListener(HomeFramePanel panel)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		try
		{
			Desktop.getDesktop().open(new File(System.getProperty("user.dir")+"/src/resources/welcome"));
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
