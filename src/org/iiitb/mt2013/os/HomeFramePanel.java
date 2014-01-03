package org.iiitb.mt2013.os;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.iiitb.mt2013.os.conts.Constants;

public class HomeFramePanel extends JFrame implements ActionListener
{

	JLabel mainText;
	JLabel pageRefLabel;
	JLabel noOfFrameLabel, noOfFrameLabel1, windowSizelabel;
	JRadioButton comparePolicies, compareBeladys, allPolicies, specificPolicies;

	public ButtonGroup getBtnGroup1( )
	{
		return btnGroup1;
	}

	public void setBtnGroup1(ButtonGroup btnGroup1)
	{
		this.btnGroup1 = btnGroup1;
	}

	PoliciesSelectionActionListener policesActionLister;

	public JRadioButton getCompareBeladys( )
	{
		return compareBeladys;
	}

	public void setCompareBeladys(JRadioButton compareBeladys)
	{
		this.compareBeladys = compareBeladys;
	}

	public JRadioButton getAllPolicies( )
	{
		return allPolicies;
	}

	public void setAllPolicies(JRadioButton allPolicies)
	{
		this.allPolicies = allPolicies;
	}

	public JRadioButton getSpecificPolicies( )
	{
		return specificPolicies;
	}

	public void setSpecificPolicies(JRadioButton specificPolicies)
	{
		this.specificPolicies = specificPolicies;
	}

	JCheckBox fifo, lru, optimal, clock, count, workSetModel;

	ButtonGroup btnGroup, btnGroup1;

	JTextField pageReferencesField, noOFFrameField, noOFFrameField1, windowSizeField;
	JButton button, reset;
	JPanel panel;

	public HomeFramePanel()
	{
		mainText = new JLabel("Page Replacement Policies");
		policesActionLister = new PoliciesSelectionActionListener(this);
		pageRefLabel = new JLabel("pageReference");
		noOfFrameLabel = new JLabel("noOfFrames1");
		noOfFrameLabel1 = new JLabel("noOfFrames2");
		windowSizelabel = new JLabel("window size");
		noOFFrameField = new JTextField();
		noOFFrameField1 = new JTextField();
		windowSizeField = new JTextField();
		btnGroup = new ButtonGroup();
		btnGroup1 = new ButtonGroup();
		comparePolicies = new JRadioButton("Test PageReplacement Policies");
		compareBeladys = new JRadioButton("Test Belady's Anomaly on Policies");
		allPolicies = new JRadioButton("do want to apply above testing on all polices");
		specificPolicies = new JRadioButton(
				"do want to apply above testing only on specific polices, pls select them individually");
		pageReferencesField = new JTextField();
		button = new JButton("submit");
		reset = new JButton("reset");
		panel = new JPanel();

		fifo = getCheckBox(Constants.FIFO_REPLACEMENT_ALGO);
		lru = getCheckBox(Constants.LRU_REPLACEMENT_ALGO);
		optimal = getCheckBox(Constants.OPTIMUM_REPLACE_ALGO);
		count = getCheckBox(Constants.COUNT_REPLACEMENT_ALGO_NAME);
		workSetModel = getCheckBox(Constants.WORKINGSETMODEL);
		clock = getCheckBox(Constants.CLOCK_REPLACEMENT_ALGO_NAME);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);

		panel.add(mainText);
		btnGroup.add(comparePolicies);
		btnGroup.add(compareBeladys);
		btnGroup1.add(allPolicies);
		btnGroup1.add(specificPolicies);
		panel.add(comparePolicies);
		panel.add(compareBeladys);

		comparePolicies.addActionListener(this);
		compareBeladys.addActionListener(this);
		allPolicies.addActionListener(policesActionLister);
		specificPolicies.addActionListener(policesActionLister);
		this.getNoOFFrameField().setEditable(false);
		this.getPageReferencesField().setEditable(false);
		this.getNoOFFrameField1().setEditable(false);
		this.getWindowSizeField().setEditable(false);
		this.getButton().setEnabled(false);
		reset.setEnabled(false);
		this.getAllPolicies().setEnabled(false);
		this.getSpecificPolicies().setEnabled(false);
		for (String name : Constants.ALGORITHMS_NAMES)
		{
			this.getCheckBox(name).setEnabled(false);
		}
	
		panel.add(noOfFrameLabel);
		panel.add(noOFFrameField);
		panel.add(noOfFrameLabel1);
		panel.add(noOFFrameField1);
		panel.add(pageRefLabel);
		panel.add(pageReferencesField);
		panel.add(allPolicies);
		panel.add(specificPolicies);

		panel.add(fifo);
		panel.add(lru);
		panel.add(optimal);
		panel.add(clock);
		panel.add(count);
		panel.add(workSetModel);
		workSetModel.addActionListener(new WorkSetActionListener(this));
		panel.add(windowSizelabel);
		panel.add(windowSizeField);
		panel.add(button);

		panel.add(reset);

		button.addActionListener(new SubmitActionListener(this));
		reset.addActionListener(new ResetActionLister(this));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.getContentPane().add(panel);

		this.setVisible(true);
	}

	public JLabel getWindowSizelabel( )
	{
		return windowSizelabel;
	}

	public void setWindowSizelabel(JLabel windowSizelabel)
	{
		this.windowSizelabel = windowSizelabel;
	}

	public JCheckBox getWorkSetModel( )
	{
		return workSetModel;
	}

	public void setWorkSetModel(JCheckBox workSetModel)
	{
		this.workSetModel = workSetModel;
	}

	public JTextField getWindowSizeField( )
	{
		return windowSizeField;
	}

	public void setWindowSizeField(JTextField windowSizeField)
	{
		this.windowSizeField = windowSizeField;
	}

	public JLabel getNoOfFrameLabel1( )
	{
		return noOfFrameLabel1;
	}

	public void setNoOfFrameLabel1(JLabel noOfFrameLabel1)
	{
		this.noOfFrameLabel1 = noOfFrameLabel1;
	}

	public JTextField getNoOFFrameField1( )
	{
		return noOFFrameField1;
	}

	public void setNoOFFrameField1(JTextField noOFFrameField1)
	{
		this.noOFFrameField1 = noOFFrameField1;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("action performed");
		if (this.getComparePolicies().isSelected())
		{

			this.getPageReferencesField().setEditable(true);
			this.getNoOFFrameField1().setText("");
			this.getNoOFFrameField1().setEditable(false);
			this.getNoOFFrameField().setEditable(true);
			this.getButton().setEnabled(true);
			this.getAllPolicies().setEnabled(true);
			this.getSpecificPolicies().setEnabled(true);
			reset.setEnabled(true);

		} else if (this.getCompareBeladys().isSelected())
		{
			this.getNoOFFrameField1().setEditable(true);
			this.getNoOFFrameField().setEditable(true);
			this.getButton().setEnabled(true);
			this.getPageReferencesField().setEditable(true);
			this.getAllPolicies().setEnabled(true);
			this.getSpecificPolicies().setEnabled(true);
			reset.setEnabled(true);
		}
	}

	public JCheckBox getFifo( )
	{
		return fifo;
	}

	public void setFifo(JCheckBox fifo)
	{
		this.fifo = fifo;
	}

	public JCheckBox getLru( )
	{
		return lru;
	}

	public void setLru(JCheckBox lru)
	{
		this.lru = lru;
	}

	public JCheckBox getOptimal( )
	{
		return optimal;
	}

	public void setOptimal(JCheckBox optimal)
	{
		this.optimal = optimal;
	}

	public JCheckBox getClock( )
	{
		return clock;
	}

	public void setClock(JCheckBox clock)
	{
		this.clock = clock;
	}

	public JLabel getNoOfFrameLabel( )
	{
		return noOfFrameLabel;
	}

	public void setNoOfFrameLabel(JLabel noOfFrameLabel)
	{
		this.noOfFrameLabel = noOfFrameLabel;
	}

	public JRadioButton getComparePolicies( )
	{
		return comparePolicies;
	}

	public void setComparePolicies(JRadioButton comparePolicies)
	{
		this.comparePolicies = comparePolicies;
	}

	public ButtonGroup getBtnGroup( )
	{
		return btnGroup;
	}

	public void setBtnGroup(ButtonGroup btnGroup)
	{
		this.btnGroup = btnGroup;
	}

	public JTextField getNoOFFrameField( )
	{
		return noOFFrameField;
	}

	public void setNoOFFrameField(JTextField noOFFrameField)
	{
		this.noOFFrameField = noOFFrameField;
	}

	public JLabel getMainText( )
	{
		return mainText;
	}

	public void setMainText(JLabel mainText)
	{
		this.mainText = mainText;
	}

	public JLabel getPageRefLabel( )
	{
		return pageRefLabel;
	}

	public void setPageRefLabel(JLabel pageRefLabel)
	{
		this.pageRefLabel = pageRefLabel;
	}

	public JTextField getPageReferencesField( )
	{
		return pageReferencesField;
	}

	public void setPageReferencesField(JTextField pageReferencesField)
	{
		this.pageReferencesField = pageReferencesField;
	}

	public JButton getButton( )
	{
		return button;
	}

	public void setButton(JButton button)
	{
		this.button = button;
	}

	public JCheckBox getCheckBox(String checkBoxName)
	{
		JCheckBox temp = null;
		switch (checkBoxName)
		{
		case Constants.FIFO_REPLACEMENT_ALGO:
			if (fifo == null)
				temp = new JCheckBox(Constants.FIFO_REPLACEMENT_ALGO);
			else
				temp = fifo;
			break;
		case Constants.LRU_REPLACEMENT_ALGO:
			if (lru == null)
				temp = new JCheckBox(Constants.LRU_REPLACEMENT_ALGO);
			else
				temp = lru;
			break;
		case Constants.CLOCK_REPLACEMENT_ALGO_NAME:
			if (clock == null)
				temp = new JCheckBox(Constants.CLOCK_REPLACEMENT_ALGO_NAME);
			else
				temp = clock;
			break;
		case Constants.OPTIMUM_REPLACE_ALGO:
			if (optimal == null)
				temp = new JCheckBox(Constants.OPTIMUM_REPLACE_ALGO);
			else
				temp = optimal;
			break;

		case Constants.COUNT_REPLACEMENT_ALGO_NAME:
			if (count == null)
				temp = new JCheckBox(Constants.COUNT_REPLACEMENT_ALGO_NAME);
			else
				temp = count;
			break;
		case Constants.WORKINGSETMODEL:
			if (workSetModel == null)
				temp = new JCheckBox(Constants.WORKINGSETMODEL);
			else
				temp = workSetModel;
		}
		return temp;

	}

	public JCheckBox getCount( )
	{
		return count;
	}

	public void setCount(JCheckBox count)
	{
		this.count = count;
	}

	public JButton getReset( )
	{
		return reset;
	}

	public void setReset(JButton reset)
	{
		this.reset = reset;
	}
}
