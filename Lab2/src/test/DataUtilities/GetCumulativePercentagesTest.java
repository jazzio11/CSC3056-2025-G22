
import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.*;

public class GetCumulativePercentagesTest {

    // Static resources that can be initialised once for the entire class
    private static DefaultKeyedValues data1, data2, data3, data4;

    // BeforeClass: Setup that runs before all tests
    @BeforeClass
    public static void setupClass() {
        // Initialise data using DefaultKeyedValues for 4 different detests

        data1 = new DefaultKeyedValues();
        data1.insertValue(0, (Comparable) 0, 6.0);   // Using insertValue
        data1.insertValue(1, (Comparable) 1, 10.0);  // Using insertValue
        data1.insertValue(2, (Comparable) 2, 4.0);   // Using insertValue

        // Dataset 2: Includes negative values
        data2 = new DefaultKeyedValues();
        data2.insertValue(0, (Comparable) 0, 6.0);   // Using insertValue
        data2.insertValue(1, (Comparable) 1, -10.0); // Using insertValue
        data2.insertValue(2, (Comparable) 2, 4.0);   // Using insertValue

        // Dataset 3: Includes constant values (NaN)
        data3 = new DefaultKeyedValues();
        data3.insertValue(0, (Comparable) 0, 6.0);        // Using insertValue
        data3.insertValue(1, (Comparable) 1, Double.NaN); // Using insertValue (NaN)
        data3.insertValue(2, (Comparable) 2, 4.0);        // Using insertValue

        // Dataset 4: Contains no values (empty dataset)
        data4 = new DefaultKeyedValues();
    }

    // AfterClass: Cleanup that runs after all tests
    @AfterClass
    public static void tearDownClass() {
        // Clean up static resources or perform finalisation here
        data1 = null;
        data2 = null;
        data3 = null;
        data4 = null;
    }

    // Test Case 1: Only Positive Values
    @Test
    public void testOnlyPositiveValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(data1);
        
        assertEquals(data1, result);
    }

    // Test Case 2: Includes Negative Values
    @Test
    public void testIncludesNegativeValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(data2);
        
        // Assuming negative values are ignored or handled as expected
        assertEquals(data2, result);
    }

    // Test Case 3: Includes Constant Values (NaN)
    @Test
    public void testIncludesConstantValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(data3);
        assertEquals(data3, result);
    }

    // Test Case 4: Contains No Values
    @Test
    public void testContainsNoValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(data4);
        assertEquals(data4, result);
    }

}
