package com.thealgorithms.slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for ShortestCoprimeSegment algorithm
 *
 * @author DomTr (https://github.com/DomTr)
 */
public class ShortestCoprimeSegmentTest {
    @Test
    public void testShortestCoprimeSegment() {
        assertEquals(3, ShortestCoprimeSegment.shortestCoprimeSegment(5, new long[]{4, 6, 9, 3, 6}));
        assertEquals(2, ShortestCoprimeSegment.shortestCoprimeSegment(5, new long[]{4, 5, 9, 3, 6}));
        assertEquals(2, ShortestCoprimeSegment.shortestCoprimeSegment(2, new long[]{3, 2}));
        assertEquals(2, ShortestCoprimeSegment.shortestCoprimeSegment(5, new long[]{3, 9, 9, 9, 10}));
        assertEquals(4, ShortestCoprimeSegment.shortestCoprimeSegment(4, new long[]{3 * 7, 7 * 5, 5 * 7 * 3, 3 * 5}));
        assertEquals(4, ShortestCoprimeSegment.shortestCoprimeSegment(4, new long[]{3 * 11, 11 * 7, 11 * 7 * 3, 3 * 7}));
        assertEquals(5, ShortestCoprimeSegment.shortestCoprimeSegment(5, new long[]{3 * 11, 11 * 7, 11 * 7 * 3, 11 * 7 * 3 * 5, 5 * 7}));
        assertEquals(6, ShortestCoprimeSegment.shortestCoprimeSegment(6, new long[]{3 * 11, 11 * 7, 11 * 7 * 3, 11 * 7 * 3 * 5, 11 * 7 * 3 * 5 * 13, 7 * 13}));
        assertEquals(6, ShortestCoprimeSegment.shortestCoprimeSegment(7, new long[]{3 * 11, 11 * 7, 11 * 7 * 3, 11 * 7 * 3 * 5, 11 * 7 * 3 * 5 * 13, 7 * 13, 11 * 7 * 3 * 5 * 13}));
        assertEquals(10, ShortestCoprimeSegment.shortestCoprimeSegment(10, new long[]{3 * 11, 7 * 11, 3 * 7 * 11, 3 * 5 * 7 * 11, 3 * 5 * 7 * 11 * 13, 2 * 3 * 5 * 7 * 11 * 13, 2 * 3 * 5 * 7 * 11 * 13 * 17, 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19, 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23, 7 * 13}));
        // Segment can consist of one element
        assertEquals(1, ShortestCoprimeSegment.shortestCoprimeSegment(5, new long[]{4, 6, 1, 3, 6}));
        assertEquals(1, ShortestCoprimeSegment.shortestCoprimeSegment(1, new long[]{1}));
    }

    @Test
    public void testNoCoprimeSegment() {
        // There may not be a coprime segment
        assertEquals(-1, ShortestCoprimeSegment.shortestCoprimeSegment(5, new long[]{4, 6, 8, 12, 8}));
        assertEquals(-1, ShortestCoprimeSegment.shortestCoprimeSegment(10, new long[]{4, 4, 4, 4, 10, 4, 6, 8, 12, 8}));
        assertEquals(-1, ShortestCoprimeSegment.shortestCoprimeSegment(1, new long[]{100}));
        assertEquals(-1, ShortestCoprimeSegment.shortestCoprimeSegment(3, new long[]{2, 2, 2}));

    }
}
