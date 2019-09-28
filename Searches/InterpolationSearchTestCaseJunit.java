package Searches;

import org.junit.Test;

import static org.junit.Assert.*;

public class InterpolationSearchTest {

    @Test
    public void testSearchEmptyArray() {
        int array[] = {};
        int key = 5;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void testSearchNullArray() {
        int array[] = null;
        int key = 5;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void testSearchNonSortedArray() {
        int array[] = {74, 46, 28, 70, 9};
        int key = 28;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void testSearchNotInArray() {
        int array[] = {1, 4, 9, 54, 85};
        int key = 10;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void testSearchKeyLocateFirstInArray() {
        int array[] = {14, 80, 91, 94, 95};
        int key = 14;

        assertEquals(0, new InterpolationSearch().find(array, key));
    }

    @Test
    public void testSearchKeyLocateLastInArray() {
        int array[] = {10, 15, 45, 51, 55};
        int key = 55;

        assertEquals(4, new InterpolationSearch().find(array, key));
    }

    @Test
    public void testSearchKeyInArray() {
        int array[] = {35, 73, 75, 77, 93};
        int key = 73;

        assertEquals(1, new InterpolationSearch().find(array, key));
    }
} 