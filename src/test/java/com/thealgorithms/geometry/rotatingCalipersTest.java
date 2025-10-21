package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class RotatingCalipersTest {
    @Test
    void testDiameterSquare() {
        var square = Arrays.asList(
            new Point(0, 0), new Point(0, 1),
            new Point(1, 1), new Point(1, 0)
        );
        double diameter = RotatingCalipers.findDiameter(square);
        assertEquals(Math.sqrt(2), diameter, 1e-6);
    }
}
