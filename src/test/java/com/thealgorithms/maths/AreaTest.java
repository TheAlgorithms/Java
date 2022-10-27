package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Amarildo Aliaj
 */
class AreaTest {

    @Test
    void testSurfaceAreaCube() {
        assertEquals(6.0, Area.surfaceAreaCube(1));
    }

    @Test
    void testSurfaceAreaSphere() {
        assertEquals(12.566370614359172, Area.surfaceAreaSphere(1));
    }

    @Test
    void testSurfaceAreaRectangle() {
        assertEquals(200.0, Area.surfaceAreaRectangle(10, 20));
    }

    @Test
    void testSurfaceAreaCylinder() {
        assertEquals(18.84955592153876, Area.surfaceAreaCylinder(1, 2));
    }

    @Test
    void testSurfaceAreaSquare() {
        assertEquals(100.0, Area.surfaceAreaSquare(10));
    }

    @Test
    void testSurfaceAreaTriangleRectangle() {
        assertEquals(50.0, Area.surfaceAreaTriangleRectangle(10, 10));
    }

    @Test
    void testSurfaceAreaParallelogram() {
        assertEquals(200.0, Area.surfaceAreaParallelogram(10, 20));
    }

    @Test
    void testSurfaceAreaTrapezium() {
        assertEquals(450.0, Area.surfaceAreaTrapezium(10, 20, 30));
    }

    @Test
    void testSurfaceAreaCircle() {
        assertEquals(1256.6370614359173, Area.surfaceAreaCircle(20));
    }

    @Test
    void surfaceAreaHemisphere() {
        assertEquals(235.61944901923448, Area.surfaceAreaHemisphere(5));
    }

    @Test
    void surfaceAreaCone() {
        assertEquals(301.59289474462014, Area.surfaceAreaCone(6, 8));
    }

    @Test
    void testAllIllegalInput() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCube(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSphere(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaRectangle(0, 10)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaRectangle(10, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCylinder(0, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCylinder(1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSquare(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTriangleRectangle(0, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTriangleRectangle(1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaParallelogram(0, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaParallelogram(1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(0, 1, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(1, 0, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(1, 1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCircle(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaHemisphere(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCone(1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCone(0, 1))
        );
    }
}