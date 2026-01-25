package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }
}
