package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KMPSearchTest {

    @Test
    // valid test case
    public void KMPSearchTestLast() {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.KMPSearch(pat, txt);
        System.out.println(value);
        assertEquals(value, 10);
    }

    @Test
    // valid test case
    public void KMPSearchTestFront() {
        String txt = "AAAAABAAABA";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.KMPSearch(pat, txt);
        System.out.println(value);
        assertEquals(value, 0);
    }

    @Test
    // valid test case
    public void KMPSearchTestMiddle() {
        String txt = "AAACAAAAAC";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.KMPSearch(pat, txt);
        System.out.println(value);
        assertEquals(value, 4);
    }

    @Test
    // valid test case
    public void KMPSearchTestNotFound() {
        String txt = "AAABAAAA";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.KMPSearch(pat, txt);
        System.out.println(value);
        assertEquals(value, 4);
    }

    @Test
    // not valid test case
    public void KMPSearchTest4() {
        String txt = "AABAAA";
        String pat = "AAAA";
        KMPSearch kmpSearch = new KMPSearch();
        int value = kmpSearch.KMPSearch(pat, txt);
        System.out.println(value);
        assertEquals(value, -1);
    }
}
