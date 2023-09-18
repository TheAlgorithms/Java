package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalculateMaxOfMinTest {

    @Test
    void testForOneElement() {
        int[] a = {10, 20, 30, 50, 10, 70, 30};
        int k = CalculateMaxOfMin.calculateMaxOfMin(a);
        assertEquals(70, k);
    }

    @Test
    void testForTwoElements() {
        int[] a = {5, 3, 2, 6, 3, 2, 6};
        int k = CalculateMaxOfMin.calculateMaxOfMin(a);
        assertEquals(6, k);
    }

    @Test
    void testForThreeElements() {
        int[] a = {10, 10, 10, 10, 10, 10, 10};
        int k = CalculateMaxOfMin.calculateMaxOfMin(a);
        assertEquals(10, k);
    }

    @Test
    void testForFourElements() {
        int[] a = {70, 60, 50, 40, 30, 20};
        int k = CalculateMaxOfMin.calculateMaxOfMin(a);
        assertEquals(70, k);
    }

    @Test
    void testForFiveElements() {
        int[] a = {50};
        int k = CalculateMaxOfMin.calculateMaxOfMin(a);
        assertEquals(50, k);
    }

    @Test
    void testForSixElements() {
        int[] a = {1, 4, 7, 9, 2, 4, 6};
        int k = CalculateMaxOfMin.calculateMaxOfMin(a);
        assertEquals(9, k);
    }

    @Test
    void testForSevenElements() {
        int[] a = {-1, -5, -7, -9, -12, -14};
        int k = CalculateMaxOfMin.calculateMaxOfMin(a);
        assertEquals(-1, k);
    }
}
