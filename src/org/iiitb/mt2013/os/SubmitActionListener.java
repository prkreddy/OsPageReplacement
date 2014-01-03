package org.iiitb.mt2013.os;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.iiitb.mt2013.os.conts.Constants;

public class SubmitActionListener implements ActionListener
{
	HomeFramePanel panel;
	List<String> algoNames;

	public SubmitActionListener(HomeFramePanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			StringBuilder stringBuilder = new StringBuilder();
			if (panel.getComparePolicies().isSelected())
			{

				if (panel.getNoOFFrameField() == null || "".equals(panel.getNoOFFrameField().getText()))
				{
					stringBuilder.append("noOfFrames1 Field is blank \n");
				}
				if (panel.getPageReferencesField() == null || "".equals(panel.getPageReferencesField().getText()))
				{
					stringBuilder.append("pageReferenceField Field is blank \n");
				}
			} else if (panel.getCompareBeladys().isSelected())
			{
				if (panel.getNoOFFrameField() == null || "".equals(panel.getNoOFFrameField().getText()))
				{
					stringBuilder.append("noOfFrames1 Field is blank \n");
				}
				if (panel.getPageReferencesField() == null || "".equals(panel.getPageReferencesField().getText()))
				{
					stringBuilder.append("pageReferenceField Field is blank \n");
				}
				if (panel.getNoOFFrameField1() == null || "".equals(panel.getNoOFFrameField1().getText()))
				{
					stringBuilder.append("noOfFrames2 Field is blank \n");
				}
			} else
			{
				stringBuilder.append("please fill required data to continue");

			}

			if (panel.getAllPolicies().isSelected())
			{
				algoNames = Constants.ALGORITHMS_NAMES;

			} else if (panel.getSpecificPolicies().isSelected())
			{
				algoNames = new ArrayList<String>();
				boolean checkBoxSelected = false;
				for (String checkbox : Constants.ALGORITHMS_NAMES)
				{
					if (panel.getCheckBox(checkbox).isSelected())
					{
						checkBoxSelected = true;
						algoNames.add(panel.getCheckBox(checkbox).getText());
					}
				}
				if (!checkBoxSelected)
				{
					stringBuilder.append("please select atleast one policy on selecting specific policy checkbox");

				}

			} else
			{
				stringBuilder.append("please select polices related data");

			}

			if (panel.getWorkSetModel().isSelected())
			{
				if (panel.getWindowSizeField() == null || "".equals(panel.getWindowSizeField().getText()))
				{
					stringBuilder.append("windowSize field is blank");

				}
			}

			if (stringBuilder.toString().length() != 0)
			{
				JOptionPane.showMessageDialog(panel, stringBuilder.toString());
				throw new Exception();

			} else
			{
				panel.dispose();
			}

			String inputsequences = "";
			PgReplaceSimulator pgsim = new PgReplaceSimulator();
			pgsim.setMemorySize(Long.parseLong(panel.getNoOFFrameField().getText()));
			pgsim.setPageSize(Long.valueOf(1));
			inputsequences = panel.getPageReferencesField().getText();
			List<Integer> values = new ArrayList<Integer>();
			for (int i = 0; i < inputsequences.split(",").length; i++)
			{
				values.add(Integer.parseInt(inputsequences.split(",")[i]));

			}
			pgsim.setPageReferences(values);
			if (panel.getComparePolicies().isSelected())
			{
				pgsim.setWindowSize(Long.parseLong(panel.getWindowSizeField().getText()));
				pgsim.startSimulator(algoNames);
			} else
			{
				List<String> newAlgoName = new ArrayList<String>();
				for (String algoName : algoNames)
				{
					newAlgoName.add(algoName);
					newAlgoName.add(algoName);
					if (algoName.equals(Constants.WORKINGSETMODEL))
					{
						pgsim.setWindowSize(Long.parseLong(panel.getWindowSizeField().getText()));

					}
				}
				pgsim.startSimulator(newAlgoName, Long.parseLong(panel.getNoOFFrameField1().getText()));
			}

		} catch (Exception ex)
		{

		}
	}
}
