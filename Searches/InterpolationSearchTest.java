package Searches;

import static org.junit.jupiter.api.Assertions.*;
import static sun.java2d.cmm.ColorTransform.In;

class InterpolationSearchTest {

    @org.junit.jupiter.api.Test
    void find1() {
        assertEquals(-1, Searches.InterpolationSearch.find(new int[]{}, 3));
    }

    @org.junit.jupiter.api.Test
    void find2() {
        assertEquals(1, Searches.InterpolationSearch.find(new int[]{1, 2}, 2));
    }

    @org.junit.jupiter.api.Test
    void find3() {
        assertEquals(0, Searches.InterpolationSearch.find(new int[]{1}, 1)); //this test case is false
    }

    @org.junit.jupiter.api.Test
    void find4() {
        assertEquals(-1, InterpolationSearch.find(new int[]{1, 3, 9}, 11));
    }

    @org.junit.jupiter.api.Test
    void find5() {
        assertEquals(-1, Searches.InterpolationSearch.find(new int[]{-7, 5}, 3));
    }
}