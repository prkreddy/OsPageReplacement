package org.iiitb.mt2013.os;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.iiitb.mt2013.os.conts.Constants;

public class HomeFramePanel extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	JTextField noOFFrameField, noOFFrameField1, windowSizeField;
	JTextArea pageReferencesField;
	JButton button, reset, help;

	Container cp;

	public HomeFramePanel()
	{
		mainText = new JLabel("Page Replacement Policies");
		policesActionLister = new PoliciesSelectionActionListener(this);

		pageRefLabel = new JLabel("Page References");
		noOfFrameLabel = new JLabel("No of Frames1");
		noOfFrameLabel1 = new JLabel("No of Frames2");
		windowSizelabel = new JLabel("Window size");

		noOFFrameField = new JTextField();
		noOFFrameField.setSelectedTextColor(Color.cyan);
		noOFFrameField.setSelectionColor(Color.RED);

		noOFFrameField1 = new JTextField();
		noOFFrameField1.setSelectedTextColor(Color.cyan);
		noOFFrameField1.setSelectionColor(Color.RED);

		windowSizeField = new JTextField();
		windowSizeField.setSelectedTextColor(Color.cyan);
		windowSizeField.setSelectionColor(Color.RED);

		btnGroup = new ButtonGroup();
		btnGroup1 = new ButtonGroup();
		comparePolicies = new JRadioButton("Test PageReplacement Policies");
		compareBeladys = new JRadioButton("Test Belady's Anomaly on Policies");

		allPolicies = new JRadioButton("Apply above testing on all Page Rep. polices");
		specificPolicies = new JRadioButton("Apply above testing on Page Rep. polices individually");

		pageReferencesField = new JTextArea();
		button = new JButton("Submit");
		reset = new JButton("Reset");
		help = new JButton("Help");
		cp = getContentPane();
		cp.setLayout(null);

		fifo = getCheckBox(Constants.FIFO_REPLACEMENT_ALGO);
		lru = getCheckBox(Constants.LRU_REPLACEMENT_ALGO);
		optimal = getCheckBox(Constants.OPTIMUM_REPLACE_ALGO);
		count = getCheckBox(Constants.COUNT_REPLACEMENT_ALGO_NAME);
		workSetModel = getCheckBox(Constants.WORKINGSETMODEL);
		clock = getCheckBox(Constants.CLOCK_REPLACEMENT_ALGO_NAME);

		btnGroup.add(comparePolicies);
		btnGroup.add(compareBeladys);
		btnGroup1.add(allPolicies);
		btnGroup1.add(specificPolicies);

		comparePolicies.addActionListener(this);
		compareBeladys.addActionListener(this);
		allPolicies.addActionListener(policesActionLister);
		specificPolicies.addActionListener(policesActionLister);
		workSetModel.addActionListener(new WorkSetActionListener(this));
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

		mainText.setBounds(100, 0, 500, 50);
		mainText.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, 30));
		mainText.setForeground(Color.GRAY);

		comparePolicies.setBounds(20, 60, 250, 50);
		compareBeladys.setBounds(300, 60, 300, 50);
		noOfFrameLabel.setBounds(30, 100, 150, 50);
		noOFFrameField.setBounds(140, 115, 100, 20);

		noOfFrameLabel1.setBounds(300, 100, 150, 50);
		noOFFrameField1.setBounds(410, 115, 100, 20);

		pageRefLabel.setBounds(30, 140, 150, 50);
		pageReferencesField.setBounds(160, 160, 500, 60);
		pageReferencesField.setBorder(BorderFactory.createLineBorder(Color.black));

		allPolicies.setBounds(20, 240, 500, 40);
		specificPolicies.setBounds(20, 270, 500, 40);

		fifo.setBounds(30, 310, 180, 30);
		lru.setBounds(210, 310, 180, 30);
		optimal.setBounds(390, 310, 180, 30);

		clock.setBounds(30, 340, 180, 30);
		count.setBounds(210, 340, 180, 30);
		workSetModel.setBounds(390, 340, 180, 30);

		windowSizelabel.setBounds(150, 380, 100, 20);
		windowSizeField.setBounds(260, 380, 100, 20);

		button.setBounds(280, 420, 100, 30);
		reset.setBounds(380, 420, 100, 30);
		help.setBounds(600, 10, 100, 20);

		cp.add(mainText);
		cp.add(comparePolicies);
		cp.add(compareBeladys);
		cp.add(noOfFrameLabel);
		cp.add(noOFFrameField);
		cp.add(noOfFrameLabel1);
		cp.add(noOFFrameField1);
		cp.add(pageRefLabel);
		cp.add(pageReferencesField);
		cp.add(allPolicies);
		cp.add(specificPolicies);

		cp.add(fifo);
		cp.add(lru);
		cp.add(optimal);
		cp.add(clock);
		cp.add(count);
		cp.add(workSetModel);

		cp.add(windowSizelabel);
		cp.add(windowSizeField);
		cp.add(button);

		cp.add(reset);
		cp.add(help);
		button.addActionListener(new SubmitActionListener(this));
		reset.addActionListener(new ResetActionLister(this));
		// cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		help.addActionListener(new HelpActionListener(this));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		setTitle("Page Replacement Policies");
		this.setLocation(200, 150);
		setResizable(true);
		this.setVisible(true);

		/*
		 * setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setSize(600,
		 * 500);// Window size
		 * setTitle(PropertiesUtil.getUICompName("HOMETITLE"));// Title for the
		 * // Frame
		 * 
		 * setLocation(200, 150);// Set the Position on the Screen
		 * setResizable(true);// Resize of window is disabled.
		 * setVisible(true);// window enabled.
		 */
	}

	public PoliciesSelectionActionListener getPolicesActionLister( )
	{
		return policesActionLister;
	}

	public void setPolicesActionLister(PoliciesSelectionActionListener policesActionLister)
	{
		this.policesActionLister = policesActionLister;
	}

	public JTextArea getPageReferencesField( )
	{
		return pageReferencesField;
	}

	public void setPageReferencesField(JTextArea pageReferencesField)
	{
		this.pageReferencesField = pageReferencesField;
	}

	public Container getCp( )
	{
		return cp;
	}

	public void setCp(Container cp)
	{
		this.cp = cp;
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

	public void setPageRefToolTipText( )
	{
		this.pageReferencesField.setToolTipText("Entry Format: 1,2,1,2,3,4,5,6,7");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (this.getComparePolicies().isSelected())
		{

			this.getPageReferencesField().setEditable(true);
			this.getNoOFFrameField1().setText("");
			this.getNoOFFrameField1().setEditable(false);
			this.getNoOFFrameField().setEditable(true);
			this.getButton().setEnabled(true);
			this.getAllPolicies().setEnabled(true);
			this.getSpecificPolicies().setEnabled(true);
			this.setPageRefToolTipText();

			reset.setEnabled(true);

		} else if (this.getCompareBeladys().isSelected())
		{
			this.getNoOFFrameField1().setEditable(true);
			this.getNoOFFrameField().setEditable(true);
			this.getButton().setEnabled(true);
			this.getPageReferencesField().setEditable(true);
			this.getAllPolicies().setEnabled(true);
			this.getSpecificPolicies().setEnabled(true);

			this.setPageRefToolTipText();
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
