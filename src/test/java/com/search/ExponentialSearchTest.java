package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExponentialSearchTest {
    @Test
    void testExponentialSearch() {
        ExponentialSearch expSearch = new ExponentialSearch();

        Integer[] arr = {11, 14, 23, 29, 36, 40, 42, 52};
        int x = 36;
        int index = expSearch.findIndex(arr, x);
        Assertions.assertEquals(4, index, "Incorrect index");

        Integer[] arrTwo = {-210, -190, -180, -160, -130, -120, -100};
        x = -120;
        index = expSearch.findIndex(arrTwo, x);
        Assertions.assertEquals(5, index, "Incorrect index");

        String[] arrString = {"101", "122", "136", "165", "225", "251", "291"};
        String stringX = "122";
        index = expSearch.findIndex(arrString, stringX);
        Assertions.assertEquals(1, index, "Incorrect index");

        String[] arrThree = {};
        Assertions.assertEquals(-1, expSearch.findIndex(arrThree, ""), "Incorrect index");
    }

}
