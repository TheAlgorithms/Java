package Searches;

import org.junit.Test;

import static org.junit.Assert.*;

public class IterativeBinarySearchTest {

    //Tìm kiếm không phù hợp
    @Test
    public void test1() {
        IterativeBinarySearch iterativeBinarySearch = new IterativeBinarySearch();
        Integer[] arr = new Integer[]{1, 5, 10, 25, 56};
        assertEquals(1, iterativeBinarySearch.find(arr, 5));
    }

    //Tìm kiếm với dãy số
    @Test
    public void test2() {
        IterativeBinarySearch iterativeBinarySearch = new IterativeBinarySearch();
        Integer[] arr = new Integer[]{1, 5, 10, 25, 56};
        assertEquals(-1, iterativeBinarySearch.find(arr, 1000));
    }

    //Tìm kiếm với dãy kí tự
    @Test
    public void test3() {
        IterativeBinarySearch iterativeBinarySearch = new IterativeBinarySearch();
        Character[] arr = new Character[]{'a', 'b', 'c', 'k', 'y'};
        assertEquals(1, iterativeBinarySearch.find(arr, 'b'));
    }

    //Tìm kiếm với dãy số có âm
    @Test
    public void test4() {
        IterativeBinarySearch iterativeBinarySearch = new IterativeBinarySearch();
        Integer[] arr = new Integer[]{-3, -7, 0, 5, 10, 25, 56};
        assertEquals(1, iterativeBinarySearch.find(arr, -7));
    }

    //Tìm kiếm với dãy chuỗi
    @Test
    public void test5() {
        IterativeBinarySearch iterativeBinarySearch = new IterativeBinarySearch();
        String[] name = new String[]{"cuong", "huy", "hoang"};
        assertEquals(0, iterativeBinarySearch.find(name, "cuong"));
    }
}