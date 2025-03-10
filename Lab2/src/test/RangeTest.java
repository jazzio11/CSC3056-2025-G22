package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCombineNullInputs() {
		
		Range result = Range.combine(null, null);

		assertEquals(null, result);
	}
	
	@Test
	public void testCombineOneNullOneValid() {
		Range result = Range.combine(null, new Range(1,10));
		Range expected = new Range(1,10);
		assertEquals(expected, result);
	}
	
	@Test
	public void testCombineOneValidOneNull() {
		Range result = Range.combine(new Range(1,10), null);
		Range expected = new Range(1,10);
		assertEquals(expected, result);		
	}
	
	@Test
	public void testCombineTwoValidInputs() {
		try {
			Range range1 = new Range(1,10);
			Range range2 = new Range(11,20);
			Range result = Range.combine(range1, range2);
			assertEquals(new Range(1,20), result);
		}catch(IllegalArgumentException ex) {
			fail("Failure when attempting to combine two ranges");
		}
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCombineOneValidOneInvalid() {
		Range.combine(new Range(1,10), new Range(10,1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCombineOneInvalidOneValid() {		
		Range.combine(new Range(10,1), new Range(1,10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCombineTwoInvalidInputs() {
		Range.combine(new Range(10,1), new Range(10,1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCombineOneNullOneInvalid() {		
		Range.combine(null, new Range(10,1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCombineOneInvalidOneNull() {		
		Range.combine(new Range(10,1), null);
	}
	
	@Test
	public void testCombineNegativePositiveRanges() {
		Range range1 = new Range(-5,5);
		Range range2 = new Range(5,10);
		Range expected = new Range(-5,10);
		Range result = Range.combine(range2, range1);
		assertEquals("Error combining positive and negative ranges", expected, result);
	}
	
	@Test
	public void testConstrainLeftOfRange() {
		Range range = new Range(5,10);
		double result = range.constrain(1);
		assertEquals("Error when constraining value 1 to the range (5,10)", 5, result, 0);
	}
	
	@Test
	public void testConstrainRightOfRange() {
		Range range = new Range(5,10);
		double result = range.constrain(11);
		assertEquals("Error when constraining value 11 to the range (5,10)", 10, result, 0);
	}
	
	@Test
	public void testConstrainWithinRange() {
		Range range = new Range(5,10);
		double result = range.constrain(6);
		assertEquals("Error when constraining value 6 to the range (5,10)", 6, result, 0);
	}
	
	@Test
	public void testConstrainLowerBoundary() {
		Range range = new Range(5,10);
		double result = range.constrain(5);
		assertEquals("Error when constraining value 5 to the range (5,10)", 5, result, 0);
	}
	
	@Test
	public void testConstrainUpperBoundary() {
		Range range = new Range(5,10);
		double result = range.constrain(10);
		assertEquals("Error when constraining value 10 to the range (5,10)", 10, result, 0);
	}
	
	@Test
	public void testConstrainNegativeValue() {
		Range range = new Range(5,10);
		double result = range.constrain(-5);
		assertEquals("Error when constraining value -5 to the range (5,10)", 5, result, 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testConstrainNullRangeNegativeValue() {
		Range range = null;
		double result = range.constrain(-5);
	}
	
	@Test(expected=NullPointerException.class)
	public void testConstrainNullRangePositiveValue() {
		Range range = null;
		double result = range.constrain(5);
	}
	
	@Test
	public void testEqualsValidRangeNullObj() {
		Range range1 = new Range(1,5);		
		Boolean result = range1.equals(null);
		assertEquals("Error when checking null obj equals range", false, result);
	}
	
	@Test
	public void testEqualsValidRangeStringObj() {
		Range range1 = new Range(1,5);		
		Boolean result = range1.equals("String");
		assertEquals("Error when checking null obj equals range", false, result);
	}
	
	@Test
	public void testEqualsSameInstance() {
		Range range1 = new Range(1,5);		
		Boolean result = range1.equals(range1);
		assertEquals("Error when checking same range instance is equal", true, result);
	}
	
	@Test
	public void testEqualsIdenticalInstance() {
		Range range1 = new Range(1,5);	
		Range range2 = new Range(1,5);
		Boolean result = range1.equals(range2);
		assertEquals("Error when checking identical ranges are equal", true, result);
	}
	
	@Test
	public void testEqualsPartialMatch() {
		Range range1 = new Range(1,5);	
		Range range2 = new Range(3,5);
		Boolean result = range1.equals(range2);
		assertEquals("Error when checking partial match ranges are equal", false, result);
	}
	
	@Test(expected=NullPointerException.class)
	public void testEqualsNullRanges() {
		Range range1 = null;	
		Range range2 = null;
		Boolean result = range1.equals(range2);
	}
	
	@Test(expected=NullPointerException.class)
	public void testEqualsNullRangeStringObj() {
		Range range1 = null;	
		Boolean result = range1.equals("String");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEqualsInvalidRangeNullObj() {
		Range range1 = new Range(10,5);	
		Boolean result = range1.equals(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEqualsInvalidRangeValidRangeObj() {
		Range range1 = new Range(10,5);	
		Range range2 = new Range(5,10);
		Boolean result = range1.equals(range2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEqualsInvalidRangeStringObj() {
		Range range1 = new Range(10,5);	
		Boolean result = range1.equals("String");
	}
	
	@Test
	public void testIntersectsLeftRange() {
		Range range = new Range(5,10);
		boolean result = range.intersects(1, 1);
		assertEquals("Error when checking intersection left of range", false, result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntersectsLargerReverseRange() {
		Range range = new Range(5,10);
		boolean result = range.intersects(11, 1);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntersectsSmallerReverseRange() {
		Range range = new Range(5,10);
		boolean result = range.intersects(6, 1);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntersectsLowerBoundaryReverseRange() {
		Range range = new Range(5,10);
		boolean result = range.intersects(5, 1);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntersectsUpperBoundaryReverseRange() {
		Range range = new Range(5,10);
		boolean result = range.intersects(10, 1);		
	}
	
	@Test
	public void testIntersectsNegativeRange() {
		Range range = new Range(5,10);
		boolean result = range.intersects(-10, 6);
		assertEquals("Error when checking negative intersection", true, result);
	}
	
	@Test(expected=NullPointerException.class)
	public void testIntersectsNullRange() {
		Range range = null;
		boolean result = range.intersects(1, 1);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntersectsInvalidRange() {
		Range range = new Range(10,5);
		boolean result = range.intersects(1, 1);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntersectsInvalidRanges() {
		Range range = new Range(10,5);
		boolean result = range.intersects(5, 1);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntersectsInvalidRangeNegativeRange() {
		Range range = new Range(10,5);
		boolean result = range.intersects(-5, -1);		
	}
	
	@Test
	public void testShiftValidRangeZeroDelta() {
		Range range = new Range(1,5);
		Range result = Range.shift(range, 0);
		
		assertEquals("Error when shifting range by 0", range, result);
	}
	
	@Test
	public void testShiftValidRangePositiveDelta() {
		Range range = new Range(1,5);
		Range expected = new Range(6,10);
		Range result = Range.shift(range, 5);
		
		assertEquals("Error when shifting range by 5", expected, result);
	}
	
	@Test
	public void testShiftValidRangeNegativeDelta() {
		Range range = new Range(1,5);
		Range expected = new Range(-4,0);
		Range result = Range.shift(range, -5);
		
		assertEquals("Error when shifting range by -5", expected, result);
	}
	
	@Test(expected=NullPointerException.class)
	public void testShiftNullRangeZeroDelta() {
		Range range = null;
		Range result = Range.shift(range, 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testShiftNullRangePositiveDelta() {
		Range range = null;		
		Range result = Range.shift(range, 5);
	}
	
	@Test(expected=NullPointerException.class)
	public void testShiftNullRangeNegativeDelta() {
		Range range = null;		
		Range result = Range.shift(range, -5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShiftInvalidRangeZeroDelta() {
		Range range = new Range(5,1);
		Range result = Range.shift(range, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShiftInvalidRangePositiveDelta() {
		Range range = new Range(5,1);		
		Range result = Range.shift(range, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShiftInvalidRangeNegativeDelta() {
		Range range = new Range(5,1);		
		Range result = Range.shift(range, -5);
	}
	
	
}
