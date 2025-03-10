package test.DataUtilities;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class calculateRowTotalTest {
	
    private static DefaultKeyedValues2D validData;
    private static DefaultKeyedValues2D validData2;
    private static DefaultKeyedValues2D validData3;
    private static DefaultKeyedValues2D validData4;
    private static DefaultKeyedValues2D nullData;

    @BeforeClass
    public static void setUp() {
        // Initialise validData
        validData = new DefaultKeyedValues2D();
        validData.addValue(1.0, 0, 0);
        validData.addValue(5.0, 0, 1);
        validData.addValue(9.0, 0, 2);
        validData.addValue(2.0, 1, 0);
        validData.addValue(-6.0, 1, 1);
        validData.addValue(10.0, 1, 2);

        // Initialise validData2
        validData2 = new DefaultKeyedValues2D();
        validData2.addValue((byte)50, 0, 0);
        validData2.addValue(3.14, 0, 1);
        validData2.addValue(1000L, 0, 2);
        validData2.addValue(-6, 1, 0);
        validData2.addValue(2.718f, 1, 1);
        validData2.addValue((short)10, 1, 2);

        // Initialise validData3
        validData3 = new DefaultKeyedValues2D();
        validData3.addValue(1.0, 0, 0);
        validData3.addValue(5.0, 0, 1);
        validData3.addValue(9.0, 0, 2);
        validData3.addValue(2.0, 1, 0);
        validData3.addValue(-6.0, 1, 1);

        // Initialise validData4
        validData4 = new DefaultKeyedValues2D();
        validData4.addValue(1.0, 0, 0);
        validData4.addValue(5.0, 0, 1);
        validData4.addValue(9.0, 0, 2);
        validData4.addValue(2.0, 1, 0);
        validData4.addValue(Double.NaN, 1, 1);
        validData4.addValue(10.0, 1, 2);
        
        
        nullData = null;
    }

    @Test
    public void testCalculateRowTotal_ValidData_RowNegative() {
        // Test Case #1: Negative row index should throw an IndexOutOfBoundsException
    	assertThrows(IndexOutOfBoundsException.class, () -> {
    		DataUtilities.calculateRowTotal(validData, -40);
        });
    }

    @Test
    public void testCalculateRowTotal_ValidData_Row1() {
        // Test Case #2: Row 1 should return the sum of values, '5' and '-6'
        double result = DataUtilities.calculateRowTotal(validData, 1);
        assertEquals("Expected -1.0 for row index 1", 8.0, result, 0.0);
    }

    @Test
    public void testCalculateRowTotal_ValidData_RowOutOfBounds() {
        // Test Case #3
        assertThrows(IndexOutOfBoundsException.class, () -> {
    		DataUtilities.calculateRowTotal(validData, 3);
        });
    }

    @Test
    public void testCalculateRowTotal_NullData_RowNegative() {
        // Test Case #4: 
    	try {
    		assertThrows(IllegalArgumentException.class, () -> {
        		DataUtilities.calculateRowTotal(nullData, -40);
            });
    	} catch (Exception e) {
    		fail("Incorrect exception thrown "); 
    	}
    }

    @Test
    public void testCalculateRowTotal_NullData_Row1() {
        // Test Case #5: 
    	try {
    		assertThrows(IllegalArgumentException.class, () -> {
        		DataUtilities.calculateRowTotal(nullData, 1);
            });
    	} catch (Exception e) {
    		fail("Incorrect exception thrown "); 
    	}
    }

    @Test
    public void testCalculateRowTotal_NullData_Row3() {
        // Test Case #6:
    	try {
    		assertThrows(IllegalArgumentException.class, () -> {
        		DataUtilities.calculateRowTotal(nullData, 3);
            });
    	} catch (Exception e) {
    		fail("Incorrect exception thrown "); 
    	}
    }

    @Test
    public void testCalculateRowTotal_ValidData2_Row0() {
        // Test Case #11: Row 0 of validData2 should sum to 1053.14
        double result = DataUtilities.calculateRowTotal(validData2, 0);
        assertEquals("Expected 1053.14 for row index 0 in validData2", 1053.14, result, 0.0);
    }

    @Test
    public void testCalculateRowTotal_ValidData3_Row1() {
        // Test Case #14: Row 1 of validData3 should sum to -4.0 (2.0 - 6.0)
        double result = DataUtilities.calculateRowTotal(validData3, 1);
        assertEquals("Expected -4.0 for row index 1 in validData3", -4.0, result, 0.0);
    }

    @Test
    public void testCalculateRowTotal_ValidData4_Row1() {
        // Test Case #17: Row 1 of validData4 should return NaN due to NaN value in the row
        double result = DataUtilities.calculateRowTotal(validData4, 1);
        assertTrue("Expected NaN for row index 1 in validData4", Double.isNaN(result));
    }
    
    @Test
    public void testCalculateRowTotal_ValidData4_RowOutOfBounds() {
        // Test Case #17: Row 1 of validData4 should return NaN due to NaN value in the row
        assertThrows(IndexOutOfBoundsException.class, () -> {
    		DataUtilities.calculateRowTotal(validData4, 3);
        });
    }
    
    @AfterClass
    public static void tearDown() {
        validData = null;
        validData2 = null;
        validData3 = null;
        validData4 = null;
        System.out.println("Test case completed, cleanup performed.");
    }
}
