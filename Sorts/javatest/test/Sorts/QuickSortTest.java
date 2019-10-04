/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorts;

/**
 *
 * @author BOT
 */
public class QuickSortTest {
    
    public QuickSortTest() {
    }
    
 
    public static void setUpClass() {
    }
    
 
    public static void tearDownClass() {
    }
    

    public void setUp() {
    }
    
    public void tearDown() {
    }

    /**
     * Test of sort method, of class QuickSort.
     */
    
    public void testSort1() {
        System.out.println("sort");
        Comparable[] array = {3,76,3,45,5};
        QuickSort instance = new QuickSort();
        Comparable[] expResult = {3,3,5,45,76};
        Comparable[] result = instance.sort(array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public void testSort2() {
        System.out.println("sort");
        Comparable[] array = {76,3,45,5};
        QuickSort instance = new QuickSort();
        Comparable[] expResult = {3,3,5,45,76};
        Comparable[] result = instance.sort(array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  public void testSort3() {
        System.out.println("sort");
        Comparable[] array = {76,90,45,5};
        QuickSort instance = new QuickSort();
        Comparable[] expResult = {3,3,5,45,76};
        Comparable[] result = instance.sort(array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private void assertArrayEquals(Comparable[] expResult, Comparable[] result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fail(String the_test_case_is_a_prototype) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
