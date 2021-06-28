package Searches;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest{
   
    @Test
    public void test1(){
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = new int[]{1, 15, 8, 95, 158};
        assertEquals(1, binarySearch.find(arr, 5));
    }
    
    @Test
    public void test2(){
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = new int[]{1, 15, 8, 95, 158};
        assertEquals(-1, binarySearch.find(arr, 1995));
    }

    @Test
    public void test3(){
        BinarySearch binarySearch = new BinarySearch();
        char[] arr = new char[]{'a', 'b', 'c', 'd', 'e'};
        assertEquals(2, binarySearch.find(arr, 'c'));
    }

    @Test
    public void test4(){
        BinarySearch binarySearch = new BinarySearch();
        string[] arr = new string[]{"abc", "def", "jqka"};
        assertEquals("jqka", binarySearch.find(arr, 2));
    }
    
    @Test
    public void test5(){
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = new int[]{-1, -8, 0, 1, 2, 8, 100};
        assertEquals(-8, binarySearch.find(arr, 1));
    }   
} 
