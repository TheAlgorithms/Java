package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FibonacciSearchTest {
    @Test
    void testFibonacciSearch() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();

        Integer[] arr = {11, 14, 23, 32, 36, 40, 54, 69};
        int x = 54;
        int index = fibonacciSearch.findIndex(arr, x);
        Assertions.assertEquals(6, index, "Incorrect index");

        Integer[] arrTwo = {-400, -283, -180, -160, -129, -120, -30};
        x = -120;
        index = fibonacciSearch.findIndex(arrTwo, x);
        Assertions.assertEquals(5, index, "Incorrect index");

        String[] arrString = {"101", "122", "136", "165", "225", "351", "458"};
        String stringX = "136";
        index = fibonacciSearch.findIndex(arrString, stringX);
        Assertions.assertEquals(2, index, "Incorrect index");

        String[] arrThree = {};
        Assertions.assertEquals(-1, fibonacciSearch.findIndex(arrThree, ""), "Incorrect index");
    }
}
