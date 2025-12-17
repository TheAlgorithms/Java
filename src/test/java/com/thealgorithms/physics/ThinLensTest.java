package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ThinLensTest {

    @Test
    void testConvexLensRealImage() {
        double v = ThinLens.imageDistance(10, 20);
        assertEquals(20, v, 1e-6);
    }

    @Test
    void testMagnification() {
        assertEquals(2.0, ThinLens.magnification(20, 10), 1e-6);
    }
}
