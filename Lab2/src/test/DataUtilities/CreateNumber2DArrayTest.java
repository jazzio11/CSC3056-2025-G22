package test.DataUtilities;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CreateNumber2DArrayTest {

    private double[][] input;
    private Number[][] expected;
    private String message;  // Custom message for each test case

    // Constructor to inject test data and message
    public CreateNumber2DArrayTest(double[][] input, Number[][] expected, String message) {
        this.input = input;
        this.expected = expected;
        this.message = message;
    }

    // Parameterised data: input array, expected result, and custom message
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            // Test Case #1: Valid 2D array with evenly populated data
            { new double[][] { {10.0, 3.0, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} },
              new Number[][] { {10.0, 3.0, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} },
              "Valid 2D array with evenly populated data" },

            // Test Case #2: Null 2D array (expect exception)
            { null, null, "Null 2D array should throw IllegalArgumentException" },

            // Test Case #3: 2D array with constant values like NaN
            { new double[][] { {10.0, Double.NaN, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} },
              new Number[][] { {10.0, Double.NaN, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} },
              "2D array with constant values like NaN" },

            // Test Case #4: Empty 2D array
            { new double[0][0], new Number[0][0], "Empty 2D array" },

            // Test Case #5: Empty 2D array of set size
            { new double[2][0], new Number[][] { {0.0, 0.0}, {} }, "2D array with empty rows of set size" },

            // Test Case #6: 2D array with duplicate values
            { new double[][] { {1.0, 1.0}, {1.0, 1.0} },
              new Number[][] { {1.0, 1.0}, {1.0, 1.0} },
              "2D array with duplicate values" },

            // Test Case #7: 2D array with inconsistent row lengths
            { new double[][] { {1.0, 1.0}, {1.0} },
              new Number[][] { {1.0, 1.0}, {1.0} },
              "2D array with inconsistent row length should still be handled" }
        });
    }

    // Test method using parameterised data and custom message
    @Test
    public void testCreateNumberArray2D() {
        if (input == null) {
            try {
                DataUtilities.createNumberArray2D(input);
                fail("Expected IllegalArgumentException for null input - " + message);
            } catch (IllegalArgumentException e) {
                // Expected exception, test passes
            }
        } else {
            Number[][] result = DataUtilities.createNumberArray2D(input);
            assertArrayEquals(message, expected, result);
        }
    }
}
