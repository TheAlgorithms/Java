package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for ShortestCoprimeSegment algorithm
 *
 * @author DomTr (<a href="https://github.com/DomTr">...</a>)
 */
public class ShortestCoprimeSegmentTest {
    @Test
    public void testShortestCoprimeSegment() {
        assertArrayEquals(new long[] {4, 6, 9}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {4, 6, 9, 3, 6}));
        assertArrayEquals(new long[] {4, 5}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {4, 5, 9, 3, 6}));
        assertArrayEquals(new long[] {3, 2}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {3, 2}));
        assertArrayEquals(new long[] {9, 10}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {3, 9, 9, 9, 10}));

        long[] test5 = new long[] {3 * 11, 11 * 7, 11 * 7 * 3, 11 * 7 * 3 * 5, 11 * 7 * 3 * 5 * 13, 7 * 13, 11 * 7 * 3 * 5 * 13};
        long[] answer5 = Arrays.copyOfRange(test5, 0, test5.length - 1);
        assertArrayEquals(answer5, ShortestCoprimeSegment.shortestCoprimeSegment(test5));

        // Test suite, when the entire array needs to be taken
        long[] test6 = new long[] {3 * 7, 7 * 5, 5 * 7 * 3, 3 * 5};
        assertArrayEquals(test6, ShortestCoprimeSegment.shortestCoprimeSegment(test6));

        long[] test7 = new long[] {3 * 11, 11 * 7, 11 * 7 * 3, 3 * 7};
        assertArrayEquals(test7, ShortestCoprimeSegment.shortestCoprimeSegment(test7));

        long[] test8 = new long[] {3 * 11, 11 * 7, 11 * 7 * 3, 11 * 7 * 3 * 5, 5 * 7};
        assertArrayEquals(test8, ShortestCoprimeSegment.shortestCoprimeSegment(test8));

        long[] test9 = new long[] {3 * 11, 11 * 7, 11 * 7 * 3, 11 * 7 * 3 * 5, 11 * 7 * 3 * 5 * 13, 7 * 13};
        assertArrayEquals(test9, ShortestCoprimeSegment.shortestCoprimeSegment(test9));

        long[] test10 = new long[] {3 * 11, 7 * 11, 3 * 7 * 11, 3 * 5 * 7 * 11, 3 * 5 * 7 * 11 * 13, 2 * 3 * 5 * 7 * 11 * 13, 2 * 3 * 5 * 7 * 11 * 13 * 17, 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19, 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23, 7 * 13};
        assertArrayEquals(test10, ShortestCoprimeSegment.shortestCoprimeSegment(test10));

        // Segment can consist of one element
        long[] test11 = new long[] {1};
        assertArrayEquals(test11, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {4, 6, 1, 3, 6}));
        long[] test12 = new long[] {1};
        assertArrayEquals(test12, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {1}));
    }
    @Test
    public void testShortestCoprimeSegment2() {
        assertArrayEquals(new long[] {2 * 3, 2 * 3 * 5, 2 * 3 * 5 * 7, 5 * 7}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {2 * 3, 2 * 3 * 5, 2 * 3 * 5 * 7, 5 * 7, 2 * 3 * 5 * 7}));
        assertArrayEquals(new long[] {5 * 7, 2}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {2 * 3, 2 * 3 * 5, 2 * 3 * 5 * 7, 5 * 7, 2}));
        assertArrayEquals(new long[] {5 * 7, 2 * 5 * 7, 2 * 11}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {2 * 3, 2 * 3 * 5, 2 * 3 * 5 * 7, 5 * 7, 2 * 5 * 7, 2 * 11}));
        assertArrayEquals(new long[] {3 * 5 * 7, 2 * 3, 2}, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {2, 2 * 3, 2 * 3 * 5, 3 * 5 * 7, 2 * 3, 2}));
    }
    @Test
    public void testNoCoprimeSegment() {
        // There may not be a coprime segment
        long[] empty = new long[] {};
        assertArrayEquals(empty, ShortestCoprimeSegment.shortestCoprimeSegment(null));
        assertArrayEquals(empty, ShortestCoprimeSegment.shortestCoprimeSegment(empty));
        assertArrayEquals(empty, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {4, 6, 8, 12, 8}));
        assertArrayEquals(empty, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {4, 4, 4, 4, 10, 4, 6, 8, 12, 8}));
        assertArrayEquals(empty, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {100}));
        assertArrayEquals(empty, ShortestCoprimeSegment.shortestCoprimeSegment(new long[] {2, 2, 2}));
    }
}
