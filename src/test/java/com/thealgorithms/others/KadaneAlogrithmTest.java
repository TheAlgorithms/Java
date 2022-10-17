package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.KadaneAlgorithm;
import org.junit.jupiter.api.Test;

public class KadaneAlogrithmTest {

    @Test
    void testForOneElement() {
        int a[] = { -1 };
        assertTrue(KadaneAlgorithm.max_Sum(a, -1));
    }

    @Test
    void testForTwoElements() {
        int a[] = { -2, 1 };
        assertTrue(KadaneAlgorithm.max_Sum(a, 1));
    }

    @Test
    void testForThreeElements() {
        int a[] = { 5, 3, 12 };
        assertTrue(KadaneAlgorithm.max_Sum(a, 20));
    }

    @Test
    void testForFourElements() {
        int a[] = { -1, -3, -7, -4 };
        assertTrue(KadaneAlgorithm.max_Sum(a, -1));
    }

    @Test
    void testForFiveElements() {
        int a[] = { 4, 5, 3, 0, 2 };
        assertTrue(KadaneAlgorithm.max_Sum(a, 14));
    }

    @Test
    void testForSixElements() {
        int a[] = { -43, -45, 47, 12, 87, -13 };
        assertTrue(KadaneAlgorithm.max_Sum(a, 146));
    }

    @Test
    void testForSevenElements() {
        int a[] = { 9, 8, 2, 23, 13, 6, 7 };
        assertTrue(KadaneAlgorithm.max_Sum(a, 68));
    }

    @Test
    void testForEightElements() {
        int a[] = { 9, -5, -5, -2, 4, 5, 0, 1 };
        assertTrue(KadaneAlgorithm.max_Sum(a, 10));
    }
}
