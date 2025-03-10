package test;

import org.jfree.data.Values2D;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

import org.jfree.data.*;


public class AllTests {

	private Values2D values2D;

	@Before
	public void setUp()
	{
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}
	
	@Test
	public void testValidDataAndColumnColumnTotal()
	{
		// arrange - ensure all arguments are prepared
		// act - 
		// assert
		assertEquals("Wrong sum returned. It should be 5.0", 
				5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	@After
	public void tearDown()
	{
		values2D = null;
	}

}
