package com.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExponentialSearchTest {

    @Test
    public void testExponentialSearch() {
        ExponentialSearch expSearch = new ExponentialSearch();

        Integer[] arr = {11, 14, 23, 29, 36, 40, 42, 52};
        int x = 36;
        int index = expSearch.findIndex(arr, x);
        assertEquals(4, index);

        Integer[] arrTwo = {-210, -190, -180, -160, -130, -120, -100};
        x = -120;
        index = expSearch.findIndex(arrTwo, x);
        assertEquals(5, index);

        String[] arrString = {"101", "122", "136", "165", "225", "251", "291"};
        String stringX = "122";
        index = expSearch.findIndex(arrString, stringX);
        assertEquals(1, index);

        String[] arrThree = {};
        assertEquals(-1, expSearch.findIndex(arrThree, ""));
    }

}
