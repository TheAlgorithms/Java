package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class VolumeTest {

    @Test
    public void volume() {
        /* test cube */
        assertEquals(343.0, Volume.volumeCube(7));

        /* test cuboid */
        assertEquals(70.0, Volume.volumeCuboid(2, 5, 7));

        /* test sphere */
        assertEquals(1436.7550402417319, Volume.volumeSphere(7));

        /* test cylinder */
        assertEquals(197.92033717615698, Volume.volumeCylinder(3, 7));

        /* test hemisphere */
        assertEquals(718.3775201208659, Volume.volumeHemisphere(7));

        /* test cone */
        assertEquals(65.97344572538566, Volume.volumeCone(3, 7));

        /* test prism */
        assertEquals(20.0, Volume.volumePrism(10, 2));

        /* test pyramid */
        assertEquals(10.0, Volume.volumePyramid(10, 3));

        /* test frustum */
        assertEquals(359.188760060433, Volume.volumeFrustumOfCone(3, 5, 7));

        /* test pyramid frustum */
        assertEquals(140.0, Volume.volumeFrustumOfPyramid(6, 24, 10));

        /* test torus */
        assertEquals(39.47841760435743, Volume.volumeTorus(2, 1));

        /* test ellipsoid */
        assertEquals(25.1327412287183459, Volume.volumeEllipsoid(3, 2, 1));
    }

    @Test
    public void testIllegalInputs() {
        assertAll(()
                      -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCube(0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCube(-1)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCuboid(0, 1, 1)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCuboid(1, 0, 1)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCuboid(1, 1, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeSphere(0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeSphere(-5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCylinder(0, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCylinder(5, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeHemisphere(0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCone(0, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeCone(5, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumePrism(0, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumePrism(5, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumePyramid(0, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumePyramid(5, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeFrustumOfCone(0, 5, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeFrustumOfCone(5, 0, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeFrustumOfCone(5, 5, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeFrustumOfPyramid(0, 5, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeFrustumOfPyramid(5, 0, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeFrustumOfPyramid(5, 5, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeTorus(0, 5)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeTorus(5, 0)),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeTorus(2, 5)),
            () -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeEllipsoid(0, 1, 2)), () -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeEllipsoid(1, 0, 2)), () -> assertThrows(IllegalArgumentException.class, () -> Volume.volumeEllipsoid(1, 2, 0)));
    }
}
