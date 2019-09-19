/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorts;

import static Sorts.QuickSort.partition;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ad
 */
public class QuickSortTest {
    
    public QuickSortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sort method, of class QuickSort.
     */
    @Test
    public void testpartitionBVAtc1() {
        Integer[] arrayTest = {3,7,5,8,1,445,7,1,3};
        int expResult = 2;
        int result = partition(arrayTest, 0, 8);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc3() {
        Integer[] arrayTest = {1,2,3,4,5,6,7,8};
        int expResult = 4;
        int result = partition(arrayTest, 0, 7);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc4() {
        Integer[] arrayTest = {8,7,6,5,4,3,2,1};
        int expResult = 4;
        int result = partition(arrayTest, 0, 7);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc5() {
        Integer[] arrayTest = {0};
        int expResult = 1;
        int result = partition(arrayTest, 0, 0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc6() {
        Integer[] arrayTest = {-1};
        int expResult = 1;
        int result = partition(arrayTest, 0, 0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc7() {
        Integer[] arrayTest = {1,1,1,1,1,1,1,1,1};
        int expResult = 5;
        int result = partition(arrayTest, 0, 8);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc2() {
        Integer[] arrayTest = {Integer.MAX_VALUE,3,7,5,8,1,445,7,1,3};
        int expResult = 7;
        int result = partition(arrayTest, 0, 9);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc8() {
        Integer[] arrayTest = {Integer.MIN_VALUE,3,7,5,8,1,445,7,1,3};
        int expResult = 8;
        int result = partition(arrayTest, 0, 9);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc9() {
        Integer[] arrayTest = {Integer.MAX_VALUE+1,3,7,5,8,1,445,7,1,3};
        int expResult = 8;
        int result = partition(arrayTest, 0, 9);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc10() {
        Integer[] arrayTest = {Integer.MIN_VALUE-1,3,7,5,8,1,445,7,1,3};
        int expResult = 7;
        int result = partition(arrayTest, 0, 9);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc11() {
        Integer[] arrayTest = {Integer.MAX_VALUE+2,3,7,5,8,1,445,7,1,3};
        int expResult = 8;
        int result = partition(arrayTest, 0, 9);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testpartitionBVAtc12() {
        Integer[] arrayTest = {Integer.MIN_VALUE-2,3,7,5,8,1,445,7,1,3};
        int expResult = 7;
        int result = partition(arrayTest, 0, 9);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
}
