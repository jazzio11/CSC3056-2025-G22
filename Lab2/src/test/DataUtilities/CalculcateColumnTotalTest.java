package test.DataUtilities;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.junit.*;

public class CalculcateColumnTotalTest {

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
    
    
    //ValidData test cases
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateColumnTotalValidDataColumnNegative() {
        // Test Case #1
        double result = DataUtilities.calculateColumnTotal(validData, -40);
    }

    @Test
    public void testCalculateColumnTotalValidDataColumn1() {
        // Test Case #2
        double result = DataUtilities.calculateColumnTotal(validData, 1);
        assertEquals("Expected -1.0 for column 1, where values sum to 5 + (-6) = -1.0",-1.0, result, 0.0); 
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateColumnTotal_ValidData_ColumnOutBoundPositive() {
        // Test Case #3
        double result = DataUtilities.calculateColumnTotal(validData, 1);
    }
    
    

    @Test
    public void testCalculateColumnTotalNullDataColumn0() {
    	// Test Case #4
    	assertThrows(IllegalArgumentException.class, () -> {
    		DataUtilities.calculateColumnTotal(nullData, 0);
        });
    }

    @Test
    public void testCalculateColumnTotalNullDataColumn1() {
    	// Test Case #5
    	try {
    		assertThrows(IllegalArgumentException.class, () -> {
        		DataUtilities.calculateColumnTotal(nullData, 0);
            });
    	} catch (Exception e) {
    		fail(); 
    	}
    }

    @Test
    public void testCalculateColumnTotalNullDataColumn3() {
        // Test Case #6
    	assertThrows(IllegalArgumentException.class, () -> {
    		DataUtilities.calculateColumnTotal(nullData, 3);
        });
    }

    @Test
    public void testCalculateColumnTotalValidData2Column0() {
        // Test Case #7
        double result = DataUtilities.calculateColumnTotal(validData2, 0);
        assertEquals("Expected 44.0 for column 0 in validData2, where values sum to 50 + (-6) = 44.0",44.0, result, 0.0);
    }

    @Test
    public void testCalculateColumnTotalValidData3Column2() {
        // Test Case #8
        double result = DataUtilities.calculateColumnTotal(validData3, 2);
        assertEquals("Expected 9.0 for column 2 in validData3, with a missing value skipped",9.0, result, 0.0); 
    }

    @Test
    public void testCalculateColumnTotalValidData3Column3() {
        // Test Case #9
    	try {
        double result = DataUtilities.calculateColumnTotal(validData3, 3);
        System.out.print(result);
        assertEquals("Expected 0.0 for invalid column index 3 in validData3",0.0, result, 0.0); 
        
    	} catch (Exception e) {
    		fail(e.getClass().getSimpleName());
    	}
    }

    @Test
    public void testCalculateColumnTotalValidData4Column1() {
        // Test Case #10
        Double result = DataUtilities.calculateColumnTotal(validData4, 1);
        assertTrue("Expected NaN for column 1 in validData4 due to Double.NaN value",result.isNaN()); 
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

