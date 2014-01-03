package org.iiitb.mt2013.os;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.mt2013.os.conts.Constants;

/**
 * 
 * {@code} this will call all other programs same input will be provided for the
 * every program
 * 
 * @version v6.0 18/10/2013
 * 
 * @author Venkat Reddy Thangella
 * 
 */
public class MainProgram
{

	public static void main(String[] args) throws NumberFormatException, IOException
	{

		String inputsequences = "";
		PgReplaceSimulator pgsim = new PgReplaceSimulator();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" Please enter the main memory size:");

		pgsim.setMemorySize(Integer.parseInt(br.readLine()));
		System.out.println(" Please enter the page size:");
		pgsim.setPageSize(Integer.parseInt(br.readLine()));
		System.out.println(" Please enter the pageReference addresses delimetered by comma(,) ");
		inputsequences = br.readLine();
		List<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < inputsequences.split(",").length; i++)
		{
			values.add(Integer.parseInt(inputsequences.split(",")[i]));

		}
		pgsim.setPageReferences(values);
		pgsim.startSimulator(Constants.ALGORITHMS_NAMES);

	}
}
