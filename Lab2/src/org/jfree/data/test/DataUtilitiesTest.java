package org.jfree.data.test;

import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest {
	
	private Values2D values2D;


	@Before
	public void setUp() {
		
			DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
			values2D = testValues;
			testValues.addValue(1,  0, 0);
			testValues.addValue(4,  1, 0);
	}

	@After
	public void tearDown() {
		
		values2D = null;
	}
//calculateColumnTotal
	@Test
	
	public void testValidDataAndColumnTotal() {
		
			assertEquals("Wrong sum returned.It should be 5.0", 
					5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	public void testNullDataColumnTotal() {
		
			try
			{
				DataUtilities.calculateColumnTotal(null, 0);
				fail("No exceptions thrown. The expected outcome was: "
						+ "a thrown exception of type: IllegalArgumentException");
		
			}
			catch (Exception e)
			{
				
					assertTrue("Incorrect exception type thrown", 
							e.getClass().equals(IllegalArgumentException.class));
			}
		
		
	}
	
	@Test(expected = NullPointerException.class)
    public void testCalculateColumnTotal_NullData() {
        DataUtilities.calculateColumnTotal(null, 0);
    }

    @Test
    public void testCalculateColumnTotal_ValidColumnIndex() {
        double expectedTotal = 5.0;
        assertEquals("Wrong sum returned. It should be 5.0",
                expectedTotal, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateColumnTotal_InvalidColumnIndex() {
        DataUtilities.calculateColumnTotal(values2D, values2D.getColumnCount());
    }

    @Test
    public void testCalculateColumnTotal_NoColumnsOrAllNulls() {
        Values2D emptyValues2D = new DefaultKeyedValues2D();
        assertEquals(0.0, DataUtilities.calculateColumnTotal(emptyValues2D, 0), 0.0000001d);
    }
    
//calculateRowTotal
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateRowTotal_InvalidRowIndex() {
        DataUtilities.calculateRowTotal(values2D, values2D.getRowCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotal_NullData() {
        DataUtilities.calculateRowTotal(null, 0);
    }

    @Test
    public void testCalculateRowTotal_NoRowsOrAllNulls() {
        Values2D emptyValues2D = new DefaultKeyedValues2D();
        assertEquals(0.0, DataUtilities.calculateRowTotal(emptyValues2D, 0), 0.0000001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotal_NegativeRowIndex() { 
        DataUtilities.calculateRowTotal(values2D, -1);
    }
    
    //createNumberArray
    
    @Test
    public void testCreateNumberArray_WithValidArray() {
        double[] data = {1.5, 2.5, 3.5};
        Number[] expected = {1.5, 2.5, 3.5};
        Number[] actual = DataUtilities.createNumberArray(data);
        assertArrayEquals("The arrays do not match", expected, actual);
    }

    @Test
    public void testCreateNumberArray_WithEmptyArray() {
        double[] data = {};
        Number[] expected = {};
        Number[] actual = DataUtilities.createNumberArray(data);
        assertArrayEquals("The arrays do not match", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray_WithNullArray() {
        DataUtilities.createNumberArray(null);
    }
    
    //createNumberArray2D
    
    @Test
    public void testCreateNumberArray2D_WithNonEmptyArray() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] actual = DataUtilities.createNumberArray2D(data);
        assertArrayEquals("The arrays do not match", expected, actual);
    }

    @Test
    public void testCreateNumberArray2D_WithEmptyInnerArray() {
        double[][] data = {{}, {1.0, 2.0}};
        Number[][] expected = {{}, {1.0, 2.0}};
        Number[][] actual = DataUtilities.createNumberArray2D(data);
        assertArrayEquals("The arrays do not match", expected, actual);
    }

    @Test
    public void testCreateNumberArray2D_WithEmptyOuterArray() {
        double[][] data = {};
        Number[][] expected = {};
        Number[][] actual = DataUtilities.createNumberArray2D(data);
        assertArrayEquals("The arrays do not match", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2D_WithNullArray() {
        DataUtilities.createNumberArray2D(null);
    }
    
    @Test
    public void testGetCumulativePercentages_WithPositiveValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("Key1", 5);
        data.addValue("Key2", 9);
        data.addValue("Key3", 2);
        
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        
        assertEquals(0.3125, result.getValue("Key1").doubleValue(), 0.0001d);
        assertEquals(0.875, result.getValue("Key2").doubleValue(), 0.0001d);
        assertEquals(1.0, result.getValue("Key3").doubleValue(), 0.0001d);
    }

    @Test
    public void testGetCumulativePercentages_WithMixedValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("Key1", -5);
        data.addValue("Key2", 10);
        data.addValue("Key3", 0);
        
    }

    @Test
    public void testGetCumulativePercentages_ValuesSumToZero() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("Key1", -5);
        data.addValue("Key2", 5);
        
        try {
            DataUtilities.getCumulativePercentages(data);
            fail("Expected an IllegalArgumentException or special handling for division by zero.");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentages_WithNullData() {
        DataUtilities.getCumulativePercentages(null);
    }

}


