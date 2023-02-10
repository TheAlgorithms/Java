package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RabinKarpAlgorithmTest {

    RabinKarpAlgorithm RKA = new RabinKarpAlgorithm();
    int q = 101;

    @Test
    // valid test case
    public void RabinKarpAlgorithmTestExample() {
        String txt = "This is an example for rabin karp algorithmn";
        String pat = "algorithmn";
        int value = RKA.search(pat, txt, q);
        assertEquals(value, 34);
    }

    @Test
    // valid test case
    public void RabinKarpAlgorithmTestFront() {
        String txt = "AAABBDDG";
        String pat = "AAA";
        int value = RKA.search(pat, txt, q);
        assertEquals(value, 0);
    }

    @Test
    // valid test case
    public void RabinKarpAlgorithmTestMiddle() {
        String txt = "AAABBCCBB";
        String pat = "BBCC";
        int value = RKA.search(pat, txt, q);
        assertEquals(value, 3);
    }

    @Test
    // valid test case
    public void RabinKarpAlgorithmTestLast() {
        String txt = "AAAABBBBCCC";
        String pat = "CCC";
        int value = RKA.search(pat, txt, q);
        assertEquals(value, 8);
    }

    @Test
    // valid test case
    public void RabinKarpAlgorithmTestNotFound() {
        String txt = "ABCBCBCAAB";
        String pat = "AADB";
        int value = RKA.search(pat, txt, q);
        assertEquals(value, -1);
    }
}
