package org.iiitb.mt2013.os.view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MemoryView extends JPanel {

	String name;

	MemoryView(String color) {
		this.name = color;
	}

	public void paint(Graphics g) {
		
		FontMetrics fm=g.getFontMetrics();
		int stringw=fm.stringWidth("hello");
		
		super.paint(g);
		this.setBackground(Color.WHITE);
//		if (this.name.equals("BLUE")) {
//			g.setColor(Color.BLUE);
//			g.fillRect(25, 75, 100, 30);
//		} else {
			g.setColor(Color.CYAN);
			g.fillRect(25, 25, 100, 30);
//		}
		
		
		g.setColor(Color.BLACK);
		g.drawString("hello", 25+(100-stringw)/2, 25+(30+fm.getHeight())/2);
	}
}
