package com.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciSearchTest {

    @Test
    public void testFibonacciSearch() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();

        Integer[] arr = {11, 14, 23, 32, 36, 40, 54, 69};
        int x = 54;
        int index = fibonacciSearch.findIndex(arr, x);
        assertEquals(6, index);

        Integer[] arrTwo = {-400, -283, -180, -160, -129, -120, -30};
        x = -120;
        index = fibonacciSearch.findIndex(arrTwo, x);
        assertEquals(5, index);

        String[] arrString = {"101", "122", "136", "165", "225", "351", "458"};
        String stringX = "136";
        index = fibonacciSearch.findIndex(arrString, stringX);
        assertEquals(2, index);

        String[] arrThree = {};
        assertEquals(-1, fibonacciSearch.findIndex(arrThree, ""));
    }
}
