package test.DataUtilities;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.ProcessHandle.Info;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class GetCumulativePercentagesTest {

    // Static resources that can be initialised once for the entire class
    public static DefaultKeyedValues data1;
    public static DefaultKeyedValues data2;
    public static DefaultKeyedValues data3;
    public static Comparable<String> key0;
    public static Comparable<String> key1;
    public static Comparable<String> key2;
    
    private static double DELTA = 0.001;

    @BeforeClass
    public static void setup() {
    
    	key0 = "0";
    	key1 = "1";
    	key2 = "2";
    	
    	data1 = new DefaultKeyedValues();
    	data1.addValue(key0, 6.0);
    	data1.addValue(key1, 10.0);
    	data1.addValue(key2, 4.0);
    	

        // Dataset 2
        data2 = new DefaultKeyedValues();
        data2.addValue(key0, 6.0);   
        data2.addValue(key1, -10.0); 
        data2.addValue(key2, 4.0);   

        // Dataset 3
        data3 = new DefaultKeyedValues();
        data3.addValue(key0, 6.0);        
        data3.addValue(key1, Double.NaN); 
        data3.addValue(key2, 4.0);        

    }
    
    @AfterClass
    public static void teardown() {
    	data1 = null;
    	data2 = null;
    	data3 = null;
    	
    }

    // Test Case 1: Only Positive Values
    @Test
    public void testOnlyPositiveValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(data1);

        assertEquals(0.3125, (double)result.getValue(key0), DELTA); // Compare using the Integer keys (0, 1, 2)
        assertEquals(0.875, (double)result.getValue(key1), DELTA); 
        assertEquals(1.0, (double)result.getValue(key2), DELTA);
        
    }

    // Test Case 2: Includes Negative Values
    @Test
    public void testIncludesNegativeValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(data2);
        
        // Assuming negative values are ignored or handled as expected
        assertEquals(0.3125,(double)result.getValue(key0), DELTA);
        assertEquals(0.875,(double)result.getValue(key1), DELTA);
        assertEquals(1.0, (double)result.getValue(key2), DELTA);
        
    }

    // Test Case 3: Includes Constant Values (NaN)
    @Test
    public void testIncludesConstantValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(data3);
        assertTrue(Double.isNaN((Double) result.getValue(key0)));
        assertTrue(Double.isNaN((Double) result.getValue(key1)));
        assertTrue(Double.isNaN((Double) result.getValue(key2)));
       
    }

}
