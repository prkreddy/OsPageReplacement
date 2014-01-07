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
		StringBuilder stringBuilder = new StringBuilder();
		int count=0;
		try
		{

			if (panel.getComparePolicies().isSelected())
			{

				if (panel.getNoOFFrameField() == null || "".equals(panel.getNoOFFrameField().getText()))
				{
					stringBuilder.append(++count+": \"No OF Frames1\" Field is blank \n");
				} else
				{

					try
					{
						int temp = Integer.parseInt(panel.getNoOFFrameField().getText());
					} catch (NumberFormatException ex)
					{
						stringBuilder.append(++count+": please enter numberic value in \"No OF Frames1\" Field\n");
					}
				}
				if (panel.getPageReferencesField() == null || "".equals(panel.getPageReferencesField().getText()))
				{
					stringBuilder.append(++count+": \"Page References\" Field is blank \n");
				}
			} else if (panel.getCompareBeladys().isSelected())
			{
				if (panel.getNoOFFrameField() == null || "".equals(panel.getNoOFFrameField().getText()))
				{
					stringBuilder.append(++count+": \"No OF Frames1\" Field is blank \n");
				} else
				{

					try
					{
						int temp = Integer.parseInt(panel.getNoOFFrameField().getText());
					} catch (NumberFormatException ex)
					{
						stringBuilder.append(++count+": please enter numberic value in \"No OF Frames1\" Field\n");
					}
				}
				if (panel.getPageReferencesField() == null || "".equals(panel.getPageReferencesField().getText()))
				{
					stringBuilder.append(++count+": \"Page References\" Field is blank \n");
				}
				if (panel.getNoOFFrameField1() == null || "".equals(panel.getNoOFFrameField1().getText()))
				{
					stringBuilder.append(++count+": \"No OF Frames2\" Field is blank \n");
				} else
				{

					try
					{
						int temp = Integer.parseInt(panel.getNoOFFrameField1().getText());
					} catch (NumberFormatException ex)
					{
						stringBuilder.append(++count+": please enter numberic value in \"No OF Frames2\" Field\n");
					}
				}
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
					stringBuilder.append(++count+": please select atleast one policy checkbox\n");

				}

			} else
			{
				stringBuilder.append(++count+": please select polices related data\n");

			}

			if (panel.getWorkSetModel().isSelected())
			{
				if (panel.getWindowSizeField() == null || "".equals(panel.getWindowSizeField().getText()))
				{
					stringBuilder.append(++count+": \nWindow size\" field is blank\n");

				} else
				{

					try
					{
						int temp = Integer.parseInt(panel.getWindowSizeField().getText());
					} catch (NumberFormatException ex)
					{
						stringBuilder.append(++count+": please enter numberic value in \nWindow size\" Field");
					}
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

				if (panel.getWorkSetModel().isSelected())
				{
					pgsim.setWindowSize(Long.parseLong(panel.getWindowSizeField().getText()));
				}
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
				if (panel.getWorkSetModel().isSelected())
				{
					pgsim.setWindowSize(Long.parseLong(panel.getWindowSizeField().getText()));
				}
				pgsim.startSimulator(newAlgoName, Long.parseLong(panel.getNoOFFrameField1().getText()));
			}

		} catch (Exception ex)
		{
			System.out.println(stringBuilder.toString());
		}
	}
}
