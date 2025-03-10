package test.DataUtilities;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CreateNumberArrayTest {

    private double[] input;
    private Number[] expected;
    private String message;  // For custom messages

    // Constructor to inject test data and message
    public CreateNumberArrayTest(double[] input, Number[] expected, String message) {
        this.input = input;
        this.expected = expected;
        this.message = message;
    }

    // Parameterised data: input array, expected result, and custom message
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            // Test Case #0: Array with positive values
            { new double[] {10.0, 3.0, 11.0, 2.0}, new Number[] {10.0, 3.0, 11.0, 2.0}, "Array with positive values" },

            // Test Case #1: Null array (expect exception)
            { null, null, "Null array should throw IllegalArgumentException" },

            // Test Case #2: Array with constant values like NaN, infinity, etc.
            { new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY},
              new Number[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}, "Array with constant values (NaN, Infinity)" },

            // Test Case #3: Array with negative values
            { new double[] {-10.0, -3.0, -11.0, -2.0}, new Number[] {-10.0, -3.0, -11.0, -2.0}, "Array with negative values" },

            // Test Case #4: Array with mixed positive and negative values
            { new double[] {-10.0, -3.0, 11.0, 2.0, 0.0}, new Number[] {-10.0, -3.0, 11.0, 2.0, 0.0}, "Array with mixed positive and negative values" },

            // Test Case #5: Empty array
            { new double[] {}, new Number[] {}, "Empty array" },

            // Test Case #6: Empty array with declared size (default value is 0.0)
            { new double[3], new Number[] {0.0, 0.0, 0.0}, "Empty array with declared size (default values 0.0)" },

            // Test Case #7: Array with duplicate values
            { new double[] {1.0, 1.0, 1.0}, new Number[] {1.0, 1.0, 1.0}, "Array with duplicate values" }
        });
    }

    // Actual test method using the parameterised data and message
    @Test
    public void testCreateNumberArray() {
        if (input != null) {
        	Number[] result = DataUtilities.createNumberArray(input);
            assertArrayEquals(message, expected, result);
        } else {
            try {
                DataUtilities.createNumberArray(input);
                fail("Expected IllegalArgumentException for null input - " + message);
            } catch (IllegalArgumentException e) {
                // Expected exception, test passes
            }
        }
    }
}
