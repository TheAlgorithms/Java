package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KMPSearchTest {

    @Test
    // valid test case
    public void kmpSearchTestLast() {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.kmpSearch(pat, txt);
        System.out.println(value);
        assertEquals(10, value);
    }

    @Test
    // valid test case
    public void kmpSearchTestFront() {
        String txt = "AAAAABAAABA";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.kmpSearch(pat, txt);
        System.out.println(value);
        assertEquals(0, value);
    }

    @Test
    // valid test case
    public void kmpSearchTestMiddle() {
        String txt = "AAACAAAAAC";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.kmpSearch(pat, txt);
        System.out.println(value);
        assertEquals(4, value);
    }

    @Test
    // valid test case
    public void kmpSearchTestNotFound() {
        String txt = "AAABAAAA";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.kmpSearch(pat, txt);
        System.out.println(value);
        assertEquals(4, value);
    }

    @Test
    // not valid test case
    public void kmpSearchTest4() {
        String txt = "AABAAA";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.kmpSearch(pat, txt);
        System.out.println(value);
        assertEquals(-1, value);
    }
}
