package Searches;

import org.junit.Test;

import static org.junit.Assert.*;

public class InterpolationSearchDataFlowTest {

    @Test
    public void findTestPath1() {
        int array[] = {1, 4, 52, 54};
        int key = 100;
        int actual = new InterpolationSearch().find(array, key);
        int expected = -1;

        assertEquals(expected, actual );
    }

    @Test
    public void findTestPath2() {
        int array[] = {1, 4, 52, 54};
        int key = 1;
        int actual = new InterpolationSearch().find(array, key);
        int expected = 0;

        assertEquals(expected, actual );
    }

    @Test
    public void findTestPath3() {
        int array[] = {1, 4, 52, 54};
        int key = 3;
        int actual = new InterpolationSearch().find(array, key);
        int expected = -1;

        assertEquals(expected, actual );
    }

    @Test
    public void findTestPath4() {
        int array[] = {1, 4, 52, 54};
        int key = 50;
        int actual = new InterpolationSearch().find(array, key);
        int expected = -1;

        assertEquals(expected, actual );
    }
}