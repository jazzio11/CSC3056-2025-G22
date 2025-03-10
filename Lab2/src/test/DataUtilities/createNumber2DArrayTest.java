package test.DataUtilities;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class createNumber2DArrayTest {

    @Test
    public void testCreateNumberArray2D_ValidEvenlyPopulated() {
        // Test Case #1: Valid 2D array with evenly populated data
        double[][] input = { {10.0, 3.0, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} };
        Number[][] expected = { {10.0, 3.0, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} };
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertArrayEquals("2D array not correctly populated with valid values", expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2D_Null2DArray() {
        // Test Case #2: Null 2D array should throw IllegalArgumentException
        DataUtilities.createNumberArray2D(null);
    }

    @Test
    public void testCreateNumberArray2D_ConstantValues() {
        // Test Case #3: 2D array with constant values like NaN
        double[][] input = { {10.0, Double.NaN, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} };
        Number[][] expected = { {10.0, Double.NaN, 11.0, 2.0}, {1.0, 6.0, 4.0, 7.0} };
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertEquals("2D array not correctly populated with constant values", expected[0][1], result[0][1]);
    }

    @Test
    public void testCreateNumberArray2D_EmptyArray() {
        // Test Case #4: Empty 2D array
        double[][] input = new double[0][0];
        Number[][] expected = new Number[0][0];
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertArrayEquals("2D array should be empty", expected, result);
    }

    @Test
    public void testCreateNumberArray2D_EmptyArrayOfSetSize() {
        // Test Case #5: Empty array of set size
        double[][] input = new double[2][0];  // Array with 2 rows but 0 columns in each
        Number[][] expected = { {0.0, 0.0}, {} };
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertArrayEquals("2D array should have empty rows with 0.0 values", expected, result);
    }

    @Test
    public void testCreateNumberArray2D_DuplicateValues() {
        // Test Case #6: 2D array with duplicate values
        double[][] input = { {1.0, 1.0}, {1.0, 1.0} };
        Number[][] expected = { {1.0, 1.0}, {1.0, 1.0} };
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertArrayEquals("2D array not correctly populated with duplicate values", expected, result);
    }

    @Test
    public void testCreateNumberArray2D_InconsistentRowLength() {
        // Test Case #7: 2D array with inconsistent row lengths
        double[][] input = { {1.0, 1.0}, {1.0} };
        Number[][] expected = { {1.0, 1.0}, {1.0} };
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertArrayEquals("2D array with inconsistent row length should still be handled", expected, result);
    }
}

