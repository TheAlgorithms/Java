package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VolumeTest {

    @Test
    public void volume() {

        /* test cube */
        assertEquals(Volume.volumeCube(7), 343.0);

        /* test cuboid */
        assertEquals(Volume.volumeCuboid(2, 5, 7), 70.0);

        /* test sphere */
        assertEquals(Volume.volumeSphere(7), 1436.7550402417319);

        /* test cylinder */
        assertEquals(Volume.volumeCylinder(3, 7), 197.92033717615698);

        /* test hemisphere */
        assertEquals(Volume.volumeHemisphere(7), 718.3775201208659);

        /* test cone */
        assertEquals(Volume.volumeCone(3, 7), 65.97344572538566);

        /* test prism */
        assertEquals(Volume.volumePrism(10, 2), 20.0);

        /* test pyramid */
        assertEquals(Volume.volumePyramid(10, 3), 10.0);

        /* test frustum */
        assertEquals(Volume.volumeFrustumOfCone(3, 5, 7), 359.188760060433);
    }
}
