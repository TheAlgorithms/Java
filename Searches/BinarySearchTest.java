package Searches;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest {

    //Tìm kiếm không phù hợp
    @Test
    public void test1() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] arr = new Integer[]{1, 5, 10, 25, 56};
        assertEquals(1, binarySearch.find(arr, 5));
    }

    //Tìm kiếm với dãy số
    @Test
    public void test2() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] arr = new Integer[]{1, 5, 10, 25, 56};
        assertEquals(-1, binarySearch.find(arr, 1000));
    }

    //Tìm kiếm với dãy kí tự
    @Test
    public void test3() {
        BinarySearch binarySearch = new BinarySearch();
        Character[] arr = new Character[]{'a', 'b', 'c', 'k', 'y'};
        assertEquals(1, binarySearch.find(arr, 'b'));
    }

    //Tìm kiếm với dãy số có âm
    @Test
    public void test4() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] arr = new Integer[]{-3, -7, 0, 5, 10, 25, 56};
        assertEquals(1, binarySearch.find(arr, -7));
    }

    //Tìm kiếm với dãy chuỗi
    @Test
    public void test5() {
        BinarySearch binarySearch = new BinarySearch();
        String[] name = new String[]{"cuong", "huy", "hoang"};
        assertEquals(0, binarySearch.find(name, "cuong"));
    }
}