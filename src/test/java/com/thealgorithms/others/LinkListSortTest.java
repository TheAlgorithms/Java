package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.sorts.LinkListSort;
import org.junit.jupiter.api.Test;

public class LinkListSortTest {

    @Test
    void testForOneElement() {
        int a[] = { 56 };
        assertTrue(LinkListSort.isSorted(a, 2));
    }

    @Test
    void testForTwoElements() {
        int a[] = { 6, 4 };
        assertTrue(LinkListSort.isSorted(a, 1));
    }

    @Test
    void testForThreeElements() {
        int a[] = { 875, 253, 12 };
        assertTrue(LinkListSort.isSorted(a, 3));
    }

    @Test
    void testForFourElements() {
        int a[] = { 86, 32, 87, 13 };
        assertTrue(LinkListSort.isSorted(a, 1));
    }

    @Test
    void testForFiveElements() {
        int a[] = { 6, 5, 3, 0, 9 };
        assertTrue(LinkListSort.isSorted(a, 1));
    }

    @Test
    void testForSixElements() {
        int a[] = { 9, 65, 432, 32, 47, 327 };
        assertTrue(LinkListSort.isSorted(a, 3));
    }

    @Test
    void testForSevenElements() {
        int a[] = { 6, 4, 2, 1, 3, 6, 7 };
        assertTrue(LinkListSort.isSorted(a, 1));
    }

    @Test
    void testForEightElements() {
        int a[] = { 123, 234, 145, 764, 322, 367, 768, 34 };
        assertTrue(LinkListSort.isSorted(a, 2));
    }
}
