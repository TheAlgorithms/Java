package com.thealgorithms.geometry;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class RotatingCalipersTest {

    private static final double EPS = 1e-9;

    @Test
    void testSquare() {
        List<RotatingCalipers.Point> square = Arrays.asList(
                new RotatingCalipers.Point(0, 0),
                new RotatingCalipers.Point(0, 1),
                new RotatingCalipers.Point(1, 1),
                new RotatingCalipers.Point(1, 0)
        );

        RotatingCalipers.PointPair pair = RotatingCalipers.diameter(square);
        assertEquals(Math.sqrt(2.0), pair.distance(), EPS);

        double w = RotatingCalipers.width(square);
        assertEquals(1.0, w, EPS);

        RotatingCalipers.Rectangle r = RotatingCalipers.minAreaBoundingRectangle(square);
        assertNotNull(r);
        assertEquals(1.0, r.width * r.height, 1e-6); // area approx 1
    }

    @Test
    void testDegenerate() {
        List<RotatingCalipers.Point> empty = Arrays.asList();
        assertNull(RotatingCalipers.minAreaBoundingRectangle(empty));
        assertEquals(0.0, RotatingCalipers.width(empty), EPS);

        List<RotatingCalipers.Point> single = Arrays.asList(new RotatingCalipers.Point(1, 2));
        RotatingCalipers.PointPair p = RotatingCalipers.diameter(single);
        assertNotNull(p);
        assertEquals(0.0, p.distance(), EPS);
    }
}
