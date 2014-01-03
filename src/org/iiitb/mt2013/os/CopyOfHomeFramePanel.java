package org.iiitb.mt2013.os;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.iiitb.mt2013.os.conts.Constants;

public class CopyOfHomeFramePanel extends JFrame implements ActionListener
{

	JLabel mainText;
	JLabel memorySizeLabel, pageSizeLabel, pageRefLabel;
	JLabel noOfFrameLabel, noOfFrameLabel1;
	JRadioButton comparePolicies;
	JRadioButton compareBelaydious;
	ButtonGroup btnGroup;

	public JLabel getMainText( )
	{
		return mainText;
	}

	public void setMainText(JLabel mainText)
	{
		this.mainText = mainText;
	}

	public JLabel getMemorySizeLabel( )
	{
		return memorySizeLabel;
	}

	public void setMemorySizeLabel(JLabel memorySizeLabel)
	{
		this.memorySizeLabel = memorySizeLabel;
	}

	public JLabel getPageSizeLabel( )
	{
		return pageSizeLabel;
	}

	public void setPageSizeLabel(JLabel pageSizeLabel)
	{
		this.pageSizeLabel = pageSizeLabel;
	}

	public JLabel getPageRefLabel( )
	{
		return pageRefLabel;
	}

	public void setPageRefLabel(JLabel pageRefLabel)
	{
		this.pageRefLabel = pageRefLabel;
	}

	public JTextField getMemorySizeField( )
	{
		return memorySizeField;
	}

	public void setMemorySizeField(JTextField memorySizeField)
	{
		this.memorySizeField = memorySizeField;
	}

	public JTextField getPageSizeField( )
	{
		return pageSizeField;
	}

	public void setPageSizeField(JTextField pageSizeField)
	{
		this.pageSizeField = pageSizeField;
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

	JTextField memorySizeField, pageSizeField, pageReferencesField, noOFFrameField, noOFFrameField1;
	JButton button;
	JPanel panel;

	public CopyOfHomeFramePanel()
	{
		mainText = new JLabel("Page Replacement Policies");
		pageSizeLabel = new JLabel("PageSize");
		memorySizeLabel = new JLabel("MemorySize");
		pageRefLabel = new JLabel("pageReference");
		noOfFrameLabel = new JLabel("noOfFrames1");
		noOfFrameLabel1 = new JLabel("noOfFrames2");
		noOFFrameField = new JTextField();
		noOFFrameField1 = new JTextField();
		btnGroup = new ButtonGroup();
		comparePolicies = new JRadioButton("compare PageReplacePolicies");
		compareBelaydious = new JRadioButton("compare Beladious");
		memorySizeField = new JTextField();
		pageSizeField = new JTextField();
		pageReferencesField = new JTextField();
		button = new JButton("submit");
		panel = new JPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 350);
		//
		// mainText.setLocation(400, 20);
		// pageSizeLabel.setLocation(20, 50);
		// memorySizeLabel.setLocation(20, 100);
		// pageRefLabel.setLocation(20, 150);
		// pageSizeField.setLocation(100, 50);
		// memorySizeField.setLocation(100, 100);
		// pageReferencesField.setLocation(100, 150);
		//
		// button.setLocation(400, 150);
		panel.add(mainText);
		btnGroup.add(comparePolicies);
		btnGroup.add(compareBelaydious);

		panel.add(comparePolicies);
		panel.add(compareBelaydious);

		comparePolicies.addActionListener(this);
		compareBelaydious.addActionListener(this);
		panel.add(pageSizeLabel);
		panel.add(pageSizeField);
		panel.add(memorySizeLabel);
		panel.add(memorySizeField);
		this.getPageSizeField().setEditable(false);
		this.getMemorySizeField().setEditable(false);
		this.getNoOFFrameField().setEditable(false);
		this.getPageReferencesField().setEditable(false);
		this.getNoOFFrameField1().setEditable(false);
		panel.add(noOfFrameLabel);
		panel.add(noOFFrameField);
		panel.add(noOfFrameLabel1);
		panel.add(noOFFrameField1);
		panel.add(pageRefLabel);
		panel.add(pageReferencesField);

		panel.add(button);
		button.addActionListener(this);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.getContentPane().add(panel);

		this.setVisible(true);
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

			this.getPageSizeField().setEditable(true);
			this.getMemorySizeField().setEditable(true);
			this.getNoOFFrameField().setEditable(false);
			this.getPageReferencesField().setEditable(true);
			this.getNoOFFrameField1().setEditable(false);

			PgReplaceSimulator pgsim = new PgReplaceSimulator();

		} else if (this.getCompareBelaydious().isSelected())
		{
			this.getPageSizeField().setEditable(false);
			this.getMemorySizeField().setEditable(false);
			this.getNoOFFrameField().setEditable(true);
			this.getNoOFFrameField1().setEditable(true);

			this.getPageReferencesField().setEditable(true);

		} else
		{
			String inputsequences = "";
			PgReplaceSimulator pgsim = new PgReplaceSimulator();

			pgsim.setMemorySize(Long.parseLong(this.getMemorySizeField().getText()));
			pgsim.setPageSize(Long.parseLong(this.getPageSizeField().getText()));
			inputsequences = this.getPageReferencesField().getText();
			List<Integer> values = new ArrayList<Integer>();
			for (int i = 0; i < inputsequences.split(",").length; i++)
			{
				values.add(Integer.parseInt(inputsequences.split(",")[i]));

			}
			pgsim.setPageReferences(values);
			pgsim.startSimulator(Constants.ALGORITHMS_NAMES);
		}
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

	public JRadioButton getCompareBelaydious( )
	{
		return compareBelaydious;
	}

	public void setCompareBelaydious(JRadioButton compareBelaydious)
	{
		this.compareBelaydious = compareBelaydious;
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
}
