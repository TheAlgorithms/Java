package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    void testCompareTo() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(5, -1);
        Point p3 = new Point(3, 9);
        Point p4 = new Point(3, 9);
        assertEquals(1, p1.compareTo(p2));
        assertEquals(-1, p2.compareTo(p3));
        assertEquals(0, p3.compareTo(p4));
    }

    @Test
    void testToString() {
        Point p = new Point(-3, 5);
        assertEquals("(-3, 5)", p.toString());
    }

    @Test
    void testPolarOrder() {
        Point p = new Point(0, 0);
        assertNotNull(p.polarOrder());
    }

    @Test
    void testOrientation() {
        // setup points
        Point pA = new Point(0, 0);
        Point pB = new Point(1, 0);
        Point pC = new Point(1, 1);

        // test for left curve
        assertEquals(1, Point.orientation(pA, pB, pC));

        // test for right curve
        pB = new Point(0, 1);
        assertEquals(-1, Point.orientation(pA, pB, pC));

        // test for left curve
        pC = new Point(-1, 1);
        assertEquals(1, Point.orientation(pA, pB, pC));

        // test for right curve
        pB = new Point(1, 0);
        pC = new Point(1, -1);
        assertEquals(-1, Point.orientation(pA, pB, pC));

        // test for collinearity
        pB = new Point(1, 1);
        pC = new Point(2, 2);
        assertEquals(0, Point.orientation(pA, pB, pC));
    }

    @Test
    void testPolarOrderCompare() {
        Point ref = new Point(0, 0);

        Point pA = new Point(1, 1);
        Point pB = new Point(1, -1);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);

        pA = new Point(3, 0);
        pB = new Point(2, 0);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);

        pA = new Point(0, 1);
        pB = new Point(-1, 1);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);

        pA = new Point(1, 1);
        pB = new Point(2, 2);
        assertEquals(0, ref.polarOrder().compare(pA, pB));

        pA = new Point(1, 2);
        pB = new Point(2, 1);
        assertTrue(ref.polarOrder().compare(pA, pB) > 0);

        pA = new Point(2, 1);
        pB = new Point(1, 2);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);

        pA = new Point(-1, 0);
        pB = new Point(-2, 0);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);

        pA = new Point(2, 3);
        pB = new Point(2, 3);
        assertEquals(0, ref.polarOrder().compare(pA, pB));

        pA = new Point(0, 1);
        pB = new Point(0, -1);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);

        ref = new Point(1, 1);

        pA = new Point(1, 2);
        pB = new Point(2, 2);
        assertTrue(ref.polarOrder().compare(pA, pB) > 0);

        pA = new Point(2, 1);
        pB = new Point(2, 0);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);

        pA = new Point(0, 1);
        pB = new Point(1, 0);
        assertTrue(ref.polarOrder().compare(pA, pB) < 0);
    }
}
