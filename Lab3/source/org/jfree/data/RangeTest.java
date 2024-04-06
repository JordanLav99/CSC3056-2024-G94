package org.jfree.data;

import static org.junit.Assert.*;

import junit.framework.TestCase;
import org.jfree.data.Range;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.*;

public class RangeTest {
	
 private Range rangeObjectUnderTest;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
		@Test
	    public void testGetCentralValue_PositiveBounds() {
	        Range range = new Range(10, 20);
	        assertEquals("The central value of a positive range is incorrect", 15, range.getCentralValue(), 0.01);
	    }
		
		@Test(expected = IllegalArgumentException.class)
		public void testRangeConstructorWithLowerGreaterThanUpper() {
		    new Range(5.0, 4.0); 
		}
		
		@Test
		public void testIntersects_NonIntersectingRangeAfter() {
		    Range existingRange = new Range(10, 20);
		    assertFalse("A range that starts after the existing range and ends before it starts should not intersect",
		            existingRange.intersects(21, 15));
		}


		@Test
		public void testCombine_WithFirstRangeNull() {
		    Range range2 = new Range(1.0, 2.0);
		    assertNull("Combining a null range with a non-null range should return null",
		            Range.combine(null, range2));
		}

		@Test
		public void testCombine_WithSecondRangeNull() {
		    Range range1 = new Range(1.0, 2.0);
		    assertNotNull("Combining a non-null range with a null range should return the non-null range",
		            Range.combine(range1, null));
		    assertEquals("Combining a non-null range with a null range should return the non-null range", 
		            range1, Range.combine(range1, null));
		}


		@Test
		public void testCombine_WithNonNullRanges() {
		    Range range1 = new Range(1.0, 3.0);
		    Range range2 = new Range(2.0, 4.0);
		    Range combined = Range.combine(range1, range2);
		    assertEquals("Combined range's lower bound is incorrect", 1.0, combined.getLowerBound(), 0.0000001);
		    assertEquals("Combined range's upper bound is incorrect", 4.0, combined.getUpperBound(), 0.0000001);
		}

		@Test
		public void testCombine_WithBothRangesNull() {
		    assertNull("Combining two null ranges should return null", Range.combine(null, null));
		}



	
	    @Test
	    public void testGetCentralValue_NegativeBounds() {
	        Range range = new Range(-20, -10);
	        assertEquals("The central value of a negative range is incorrect", -15, range.getCentralValue(), 0.01);
	    }
	
	    @Test
	    public void testGetCentralValue_SpanningZero() {
	        Range range = new Range(-10, 10);
	        assertEquals("The central value of a range spanning zero is incorrect", 0, range.getCentralValue(), 0.01);
	    }
	
	    @Test
	    public void testGetCentralValue_ZeroWidth() {
	        Range range = new Range(5, 5);
	        assertEquals("The central value of a zero-width range is incorrect", 5, range.getCentralValue(), 0.01);
	    }

	    
	    @Test
	    public void testConstrain_WithValueWithinRange() {
	        double value = 0; // Value within the test range of -1 to 1
	        assertEquals("Value within range should remain unchanged", value, rangeObjectUnderTest.constrain(value), 0.0);
	    }

	    @Test
	    public void testConstrain_WithValueOnLowerBound() {
	        double value = -1; // Lower boundary value of the range
	        assertEquals("Value on lower boundary should remain unchanged", value, rangeObjectUnderTest.constrain(value), 0.0);
	    }

	    @Test
	    public void testConstrain_WithValueOnUpperBound() {
	        double value = 1; // Upper boundary value of the range
	        assertEquals("Value on upper boundary should remain unchanged", value, rangeObjectUnderTest.constrain(value), 0.0);
	    }

	    @Test
	    public void testConstrain_WithValueBelowRange() {
	        double valueBelowRange = -2; 
	        assertEquals("Value below range should return lower bound", -1, rangeObjectUnderTest.constrain(valueBelowRange), 0.0);
	    }

	    @Test
	    public void testConstrain_WithValueAboveRange() {
	        double valueAboveRange = 2; 
	        assertEquals("Value above range should return upper bound", 1, rangeObjectUnderTest.constrain(valueAboveRange), 0.0);
	    }
	    
	    @Test
	    public void testExpand_WithPositiveMargins() {
	        Range originalRange = new Range(10, 20);
	        double lowerMargin = 0.1; // 10% of the range length
	        double upperMargin = 0.2; // 20% of the range length
	        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
	        assertEquals("Expanded range's lower bound is incorrect", 9.0, expandedRange.getLowerBound(), 0.001);
	        assertEquals("Expanded range's upper bound is incorrect", 22.0, expandedRange.getUpperBound(), 0.001);
	    }

	    @Test
	    public void testExpand_WithZeroMargins() {
	        Range originalRange = new Range(10, 20);
	        Range expandedRange = Range.expand(originalRange, 0, 0);
	        assertEquals("Range should remain unchanged with zero margins", originalRange, expandedRange);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testExpand_WithNullRange() {
	        Range.expand(null, 0.1, 0.1);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testExpand_WithNegativeMargins() {
	        Range originalRange = new Range(10, 20);
	        Range.expand(originalRange, -0.1, -0.1);
	    }

	    @Test
	    public void testExpand_WithVeryHighMargins() {
	        Range originalRange = new Range(10, 20);
	        double lowerMargin = 5; // 500% of the range length
	        double upperMargin = 5; // 500% of the range length
	        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
	        assertNotNull("Expanded range should not be null even with very high margins", expandedRange);
	        // Asserting that the range is expanded significantly might depend on the behavior of Range.expand with high margins.
	    }

	    @Test
	    public void testIntersects_InsideInside() {
	        assertTrue("The test range within the existing range should intersect", rangeObjectUnderTest.intersects(-0.5, 0.5));
	    }

	    @Test
	    public void testIntersects_BelowInside() {
	        assertTrue("The test range partially overlapping the lower side of the existing range should intersect", rangeObjectUnderTest.intersects(-2, 0));
	    }

	    @Test
	    public void testIntersects_InsideAbove() {
	        assertTrue("The test range partially overlapping the upper side of the existing range should intersect", rangeObjectUnderTest.intersects(0, 2));
	    }

	    @Test
	    public void testIntersects_BelowAbove() {
	        assertTrue("The test range completely enveloping the existing range should intersect", rangeObjectUnderTest.intersects(-2, 2));
	    }

	    @Test
	    public void testIntersects_BelowBelow() {
	        assertFalse("The test range entirely lower than the existing range with no overlap should not intersect", rangeObjectUnderTest.intersects(-3, -2));
	    }

	    @Test
	    public void testIntersects_AboveAbove() {
	        assertFalse("The test range entirely higher than the existing range with no overlap should not intersect", rangeObjectUnderTest.intersects(2, 3));
	    }
	    
	    
	    @Test
	    public void testEquals_WithNonRangeObject() {
	        Object obj = new Object();
	        Range range = new Range(1.0, 2.0);
	        assertFalse("Equals should return false for a non-Range object", range.equals(obj));
	    }

	    // Test the equals method with a Range object that has a different lower bound
	    @Test
	    public void testEquals_WithDifferentLowerBound() {
	        Range range1 = new Range(1.0, 2.0);
	        Range range2 = new Range(1.1, 2.0);
	        assertFalse("Equals should return false for different lower bounds", range1.equals(range2));
	    }

	    // Test the equals method with a Range object that has a different upper bound
	    @Test
	    public void testEquals_WithDifferentUpperBound() {
	        Range range1 = new Range(1.0, 2.0);
	        Range range2 = new Range(1.0, 2.1);
	        assertFalse("Equals should return false for different upper bounds", range1.equals(range2));
	    }

	    // Test the equals method with a Range object that is equal
	    @Test
	    public void testEquals_WithIdenticalRange() {
	        Range range1 = new Range(1.0, 2.0);
	        Range range2 = new Range(1.0, 2.0);
	        assertTrue("Equals should return true for identical Ranges", range1.equals(range2));
	    }

	    // Test the equals method with null
	    @Test
	    public void testEquals_WithNull() {
	        Range range = new Range(1.0, 2.0);
	        assertFalse("Equals should return false when comparing to null", range.equals(null));
	    }
	
	    
	    @Test
	    public void testShift_PositiveValue_NoCrossing() {
	        Range base = new Range(2.0, 4.0);
	        double delta = 1.0;
	        Range result = Range.shift(base, delta, false);
	        assertEquals("Shifting a positive range without crossing zero should give correct result",
	                new Range(2.0 + delta, 4.0 + delta), result);
	    }

	    @Test
	    public void testShift_PositiveValue_Crossing() {
	        Range base = new Range(0.1, 0.2);
	        double delta = -0.15;
	        Range result = Range.shift(base, delta, false);
	        assertEquals("Shifting a positive range to cross zero should clamp to zero at the lower end",
	                new Range(0.0, 0.2 + delta), result);
	    }

	    @Test
	    public void testShift_NegativeValue_NoCrossing() {
	        Range base = new Range(-4.0, -2.0);
	        double delta = -1.0;
	        Range result = Range.shift(base, delta, false);
	        assertEquals("Shifting a negative range without crossing zero should give correct result",
	                new Range(-4.0 + delta, -2.0 + delta), result);
	    }

	    @Test
	    public void testShift_NegativeValue_Crossing() {
	        Range base = new Range(-0.2, -0.1);
	        double delta = 0.15;
	        Range result = Range.shift(base, delta, false);
	        assertEquals("Shifting a negative range to cross zero should clamp to zero at the upper end",
	                new Range(-0.2 + delta, 0.0), result);
	    }

	    @Test
	    public void testShift_ZeroValue_PositiveDelta() {
	        Range base = new Range(0.0, 0.0);
	        double delta = 1.0;
	        Range result = Range.shift(base, delta, false);
	        assertEquals("Shifting a zero range by a positive delta should give correct result",
	                new Range(0.0, delta), result);
	    }

	    @Test
	    public void testShift_ZeroValue_NegativeDelta() {
	        Range base = new Range(0.0, 0.0);
	        double delta = -1.0;
	        Range result = Range.shift(base, delta, false);
	        assertEquals("Shifting a zero range by a negative delta should give correct result",
	                new Range(delta, 0.0), result);
	    }
	
	    
	    
	    
	    @Test
	    public void testExpandToInclude_WithNullRange() {
	        double value = 5.0;
	        Range result = Range.expandToInclude(null, value);
	        assertNotNull("The result should not be null", result);
	        assertEquals("The lower bound of the range should be the given value", value, result.getLowerBound(), 0.0000001);
	        assertEquals("The upper bound of the range should be the given value", value, result.getUpperBound(), 0.0000001);
	    }

	    @Test
	    public void testExpandToInclude_WithLesserValue() {
	        Range range = new Range(10.0, 20.0);
	        double value = 5.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("The lower bound of the range should be the given value", value, result.getLowerBound(), 0.0000001);
	        assertEquals("The upper bound should remain unchanged", 20.0, result.getUpperBound(), 0.0000001);
	    }

	    @Test
	    public void testExpandToInclude_WithGreaterValue() {
	        Range range = new Range(10.0, 20.0);
	        double value = 25.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("The lower bound should remain unchanged", 10.0, result.getLowerBound(), 0.0000001);
	        assertEquals("The upper bound of the range should be the given value", value, result.getUpperBound(), 0.0000001);
	    }

	    @Test
	    public void testExpandToInclude_WithValueInRange() {
	        Range range = new Range(10.0, 20.0);
	        double value = 15.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("The range should remain unchanged when value is within the range", range, result);
	    }

	    @Test
	    public void testExpandToInclude_WithValueAsLowerBound() {
	        Range range = new Range(10.0, 20.0);
	        double value = 10.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("The range should remain unchanged when value is the lower bound", range, result);
	    }

	    @Test
	    public void testExpandToInclude_WithValueAsUpperBound() {
	        Range range = new Range(10.0, 20.0);
	        double value = 20.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("The range should remain unchanged when value is the upper bound", range, result);
	    }
	
	    
	    
	    @Test
	    public void testShift_WithPositiveDelta() {
	        Range base = new Range(1, 5);
	        double delta = 3;
	        Range expected = new Range(4, 8);
	        Range actual = Range.shift(base, delta);
	        assertEquals("Range should be shifted right by delta", expected, actual);
	    }

	    @Test
	    public void testShift_WithZeroDelta() {
	        Range base = new Range(1, 5);
	        double delta = 0;
	        Range expected = new Range(1, 5); // Expect the original range
	        Range actual = Range.shift(base, delta);
	        assertEquals("Range with zero delta should remain unchanged", expected, actual);
	    }

	    @Test
	    public void testShift_WithNegativeDelta() {
	        Range base = new Range(2, 6);
	        double delta = -1;
	        Range expected = new Range(1, 5); // Shifted left by 1
	        Range actual = Range.shift(base, delta);
	        assertEquals("Range should be shifted left by delta", expected, actual);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testShift_WithNullBase() {
	        Range.shift(null, 1); 
	    }
}
