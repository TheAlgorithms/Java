package Searches;

import org.junit.Test;

import static org.junit.Assert.*;

public class InterpolationSearchTest {

    @Test
    public void findInEmptyArray() {
        int array[] = {};
        int key = 5;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void findInNullArray() {
        int array[] = null;
        int key = 5;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void findInNonSortedArray() {
        int array[] = {4, 1, 7, 54};
        int key = 1;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void findKeyIsntInArray() {
        int array[] = {1, 4, 7, 54};
        int key = 3;

        assertEquals(-1, new InterpolationSearch().find(array, key));
    }

    @Test
    public void findKeyLocatedAtFirst() {
        int array[] = {1, 4, 7, 54};
        int key = 1;

        assertEquals(0, new InterpolationSearch().find(array, key));
    }

    @Test
    public void findKeyLocatedAtLast() {
        int array[] = {1, 4, 7, 54};
        int key = 54;

        assertEquals(3, new InterpolationSearch().find(array, key));
    }

    @Test
    public void findKeyInArray() {
        int array[] = {1, 4, 7, 54};
        int key = 7;

        assertEquals(2, new InterpolationSearch().find(array, key));
    }
}