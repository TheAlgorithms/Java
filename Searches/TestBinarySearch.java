package Searches;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestBinarySearch {

    @Test
    public void test0() {
        TestBinarySearch Test0 = new TestBinarySearch();
        Integer[] arr = new Integer[]{1, 3, 7, 9, 18};
        assertEquals(1, Test0.find(arr, 7));
    }

    @Test
    public void test1() {
        TestBinarySearch Test1 = new TestBinarySearch();
        Integer[] arr = new Integer[]{1, 2, 8, 9, 18};
        assertEquals(-1, Test1.find(arr, 99));
    }

    @Test
    public void test2() {
        TestBinarySearch Test2 = new TestBinarySearch();
        String[] name = new String[]{"Nam", "Minh", "Vuong"};
        assertEquals(0, Test2.find(name, "Vuong"));
    }

    @Test
    public void test3() {
        TestBinarySearch Test3 = new TestBinarySearch();
        Integer[] arr = new Integer[]{-1, -5, 8, 9, 0, 15};
        assertEquals(1, Test3.find(arr, -5));
    }

    @Test
    public void test2() {
        TestBinarySearch Test4 = new TestBinarySearch();
        Character[] arr = new Character[]{'a', 'b', 'c', 'd', 'e', 'f'};
        assertEquals(1, Test4.find(arr, 'c'));
    }
    
} 
