package test.DataUtilities;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class createNumberArrayTest {

    private double[] input;
    private Number[] expected;

    // Constructor to inject test data
    public createNumberArrayTest(double[] input, Number[] expected) {
        this.input = input;
        this.expected = expected;
    }

    // Parameterized data: input array and expected result
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            // Test Case #1: Array with positive values
            { new double[] {10.0, 3.0, 11.0, 2.0}, new Number[] {10.0, 3.0, 11.0, 2.0} },

            // Test Case #2: Null array (expect exception)
            { null, null },

            // Test Case #3: Array with constant values like NaN, infinity, etc.
            { new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY},
              new Number[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY} },

            // Test Case #4: Array with negative values
            { new double[] {-10.0, -3.0, -11.0, -2.0}, new Number[] {-10.0, -3.0, -11.0, -2.0} },

            // Test Case #5: Array with mixed positive and negative values
            { new double[] {-10.0, -3.0, 11.0, 2.0, 0.0}, new Number[] {-10.0, -3.0, 11.0, 2.0, 0.0} },

            // Test Case #6: Empty array
            { new double[] {}, new Number[] {} },

            // Test Case #7: Empty array with declared size (default value is 0.0)
            { new double[3], new Number[] {0.0, 0.0, 0.0} },

            // Test Case #8: Array with duplicate values
            { new double[] {1.0, 1.0, 1.0}, new Number[] {1.0, 1.0, 1.0} }
        });
    }

    // Actual test method using the parameterized data
    @Test
    public void testCreateNumberArray() {
        if (input == null) {
            try {
                DataUtilities.createNumberArray(input);
                fail("Expected IllegalArgumentException for null input");
            } catch (IllegalArgumentException e) {
                // Expected exception, test passes
            }
        } else {
            Number[] result = DataUtilities.createNumberArray(input);
            assertArrayEquals("Array not correctly populated with the expected values", expected, result);
        }
    }
}
