package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TournamentSortTest {

    @Test
    public void testBasic() {
        TournamentSort sorter = new TournamentSort();
        Integer[] arr = {5, 3, 8, 4, 1, 2};
        Integer[] expected = {1, 2, 3, 4, 5, 8};
        assertArrayEquals(expected, sorter.sort(arr));
    }

    @Test
    public void testAlreadySorted() {
        TournamentSort sorter = new TournamentSort();
        Integer[] arr = {1, 2, 3, 4};
        assertArrayEquals(arr, sorter.sort(arr));
    }

    @Test
    public void testReverseOrder() {
        TournamentSort sorter = new TournamentSort();
        Integer[] arr = {9, 7, 5, 3, 1};
        Integer[] expected = {1, 3, 5, 7, 9};
        assertArrayEquals(expected, sorter.sort(arr));
    }

    @Test
    public void testSingleElement() {
        TournamentSort sorter = new TournamentSort();
        Integer[] arr = {42};
        assertArrayEquals(arr, sorter.sort(arr));
    }

    @Test
    public void testEmptyArray() {
        TournamentSort sorter = new TournamentSort();
        Integer[] arr = {};
        assertArrayEquals(arr, sorter.sort(arr));
    }
}
