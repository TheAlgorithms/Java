package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ElasticCollision2DTest {

    @Test
    void testEqualMassHeadOnCollision() {
        ElasticCollision2D.Body a = new ElasticCollision2D.Body(0, 0, 1, 0, 1.0, 0.5);
        ElasticCollision2D.Body b = new ElasticCollision2D.Body(1, 0, -1, 0, 1.0, 0.5);

        ElasticCollision2D.resolveCollision(a, b);

        assertEquals(-1.0, a.vx, 1e-6);
        assertEquals(0.0, a.vy, 1e-6);
        assertEquals(1.0, b.vx, 1e-6);
        assertEquals(0.0, b.vy, 1e-6);
    }

    @Test
    void testUnequalMassHeadOnCollision() {
        ElasticCollision2D.Body a = new ElasticCollision2D.Body(0, 0, 2, 0, 2.0, 0.5);
        ElasticCollision2D.Body b = new ElasticCollision2D.Body(1, 0, -1, 0, 1.0, 0.5);

        ElasticCollision2D.resolveCollision(a, b);

        // 1D head-on collision results
        assertEquals(0.0, a.vx, 1e-6);
        assertEquals(0.0, a.vy, 1e-6);
        assertEquals(3.0, b.vx, 1e-6);
        assertEquals(0.0, b.vy, 1e-6);
    }

    @Test
    void testMovingApartNoCollision() {
        ElasticCollision2D.Body a = new ElasticCollision2D.Body(0, 0, -1, 0, 1.0, 0.5);
        ElasticCollision2D.Body b = new ElasticCollision2D.Body(1, 0, 1, 0, 1.0, 0.5);

        ElasticCollision2D.resolveCollision(a, b);

        assertEquals(-1.0, a.vx, 1e-6);
        assertEquals(0.0, a.vy, 1e-6);
        assertEquals(1.0, b.vx, 1e-6);
        assertEquals(0.0, b.vy, 1e-6);
    }

    @Test
    void testGlancingCollision() {
        ElasticCollision2D.Body a = new ElasticCollision2D.Body(0, 0, 1, 1, 1.0, 0.5);
        ElasticCollision2D.Body b = new ElasticCollision2D.Body(1, 1, -1, -0.5, 1.0, 0.5);

        ElasticCollision2D.resolveCollision(a, b);

        // Ensure relative velocity along normal is reversed
        double nx = (b.x - a.x) / Math.hypot(b.x - a.x, b.y - a.y);
        double ny = (b.y - a.y) / Math.hypot(b.x - a.x, b.y - a.y);
        double relVelAfter = (b.vx - a.vx) * nx + (b.vy - a.vy) * ny;

        assertTrue(relVelAfter > 0);
    }

    @Test
    void testOverlappingBodies() {
        ElasticCollision2D.Body a = new ElasticCollision2D.Body(0, 0, 1, 0, 1.0, 0.5);
        ElasticCollision2D.Body b = new ElasticCollision2D.Body(0, 0, -1, 0, 1.0, 0.5);

        ElasticCollision2D.resolveCollision(a, b);

        // Should not crash, velocities may remain unchanged
        assertEquals(1.0, a.vx, 1e-6);
        assertEquals(0.0, a.vy, 1e-6);
        assertEquals(-1.0, b.vx, 1e-6);
        assertEquals(0.0, b.vy, 1e-6);
    }

    @Test
    void testStationaryBodyHit() {
        ElasticCollision2D.Body a = new ElasticCollision2D.Body(0, 0, 2, 0, 1.0, 0.5);
        ElasticCollision2D.Body b = new ElasticCollision2D.Body(1, 0, 0, 0, 1.0, 0.5);

        ElasticCollision2D.resolveCollision(a, b);

        assertEquals(0.0, a.vx, 1e-6);
        assertEquals(0.0, a.vy, 1e-6);
        assertEquals(2.0, b.vx, 1e-6);
        assertEquals(0.0, b.vy, 1e-6);
    }
}
