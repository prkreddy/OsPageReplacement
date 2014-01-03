package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.mt2013.os.PgReplaceSimulator;
import org.iiitb.mt2013.os.algo.PageReplacementAlgo;
import org.iiitb.mt2013.os.bean.Frame;
import org.iiitb.mt2013.os.conts.Constants;
import org.iiitb.mt2013.os.pageAlgo.impl.ClockReplacmentImpl;
import org.iiitb.mt2013.os.pageAlgo.impl.CountReplacmentImpl;
import org.iiitb.mt2013.os.pageAlgo.impl.FIFOImpl;
import org.iiitb.mt2013.os.pageAlgo.impl.LRUImpl;
import org.iiitb.mt2013.os.pageAlgo.impl.OptimumReplacmentImpl;
import org.iiitb.mt2013.os.view.MemoryPrint;
import org.junit.BeforeClass;
import org.junit.Test;

public class PageReplcAlgoTests
{
	static PgReplaceSimulator pgsim = new PgReplaceSimulator();

	PageReplacementAlgo pageReplacementAlgo;
	static Properties properties = new Properties();
	Map<String, List<String>> outputs = new HashMap<String, List<String>>();
	List<MemoryPrint> memoryPrints;
	List<Frame> frames = null;

	@BeforeClass
	public static void beforeClass( ) throws FileNotFoundException, IOException
	{

		properties.load(new InputStreamReader(new FileInputStream(System.getProperty("user.dir")
				+ "/src/resources/imp.properties")));

		String inputsequences = "";

		pgsim.setMemorySize(Integer.parseInt(properties.getProperty("memorySize")));
		pgsim.setPageSize(Integer.parseInt(properties.getProperty("pageSize")));
		inputsequences = properties.getProperty("pageReferences");
		List<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < inputsequences.split(",").length; i++)
		{
			values.add(Integer.parseInt(inputsequences.split(",")[i]));

		}
		pgsim.setPageReferences(values);

	}

	@Test
	public void clockReplacAlgoTest( ) throws InvalidMemoryUnitException
	{
		pageReplacementAlgo = new ClockReplacmentImpl(pgsim.getPageReferences(), pgsim.getMemorySize(),
				pgsim.getPageSize(), Constants.CLOCK_REPLACEMENT_ALGO_NAME);

		memoryPrints = pageReplacementAlgo.executeAlgo();

		checkResults("CLOCK_");
	}

	@Test
	public void optimumReplacAlgoTest( ) throws InvalidMemoryUnitException
	{
		pageReplacementAlgo = new OptimumReplacmentImpl(pgsim.getPageReferences(), pgsim.getMemorySize(),
				pgsim.getPageSize(), Constants.OPTIMUM_REPLACE_ALGO);

		memoryPrints = pageReplacementAlgo.executeAlgo();

		checkResults("OPTIMUM_");

	}

	@Test
	public void fifoReplacAlgoTest( ) throws InvalidMemoryUnitException
	{
		pageReplacementAlgo = new FIFOImpl(pgsim.getPageReferences(), pgsim.getMemorySize(), pgsim.getPageSize(),
				Constants.FIFO_REPLACEMENT_ALGO);

		memoryPrints = pageReplacementAlgo.executeAlgo();

		checkResults("FIFO_");

	}

	@Test
	public void lruReplacAlgoTest( ) throws InvalidMemoryUnitException
	{
		pageReplacementAlgo = new LRUImpl(pgsim.getPageReferences(), pgsim.getMemorySize(), pgsim.getPageSize(),
				Constants.LRU_REPLACEMENT_ALGO);

		memoryPrints = pageReplacementAlgo.executeAlgo();

		checkResults("LRU_");

	}

	@Test
	public void countReplacAlgoTest( ) throws InvalidMemoryUnitException
	{
		pageReplacementAlgo = new CountReplacmentImpl(pgsim.getPageReferences(), pgsim.getMemorySize(),
				pgsim.getPageSize(), Constants.COUNT_REPLACEMENT_ALGO_NAME);

		memoryPrints = pageReplacementAlgo.executeAlgo();

		checkResults("COUNT_");

	}

	public void checkResults(String algo)
	{
		String[] expectedData;

		int countReferences = pgsim.getPageReferences().size();

		for (int i = 1; i <= countReferences; i++)
		{
			frames = memoryPrints.get(i - 1).getMemoryFrames();
			expectedData = properties.getProperty(algo + i).split(",");

			for (int j = 0; j < expectedData.length; j++)
			{
				Assert.assertEquals(expectedData[j], frames.get(j).getAddress() + "");
			}

		}
	}
}
