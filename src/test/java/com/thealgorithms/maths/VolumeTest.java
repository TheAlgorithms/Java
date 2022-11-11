package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VolumeTest {

    @Test
    public void volume() {

        /* test cube */
        assertTrue(Volume.volumeCube(7) == 343.0);

        /* test cuboid */
        assertTrue(Volume.volumeCuboid(2, 5, 7) == 70.0);

        /* test sphere */
        assertTrue(Volume.volumeSphere(7) == 1436.7550402417319);

        /* test cylinder */
        assertTrue(Volume.volumeCylinder(3, 7) == 197.92033717615698);

        /* test hemisphere */
        assertTrue(Volume.volumeHemisphere(7) == 718.3775201208659);

        /* test cone */
        assertTrue(Volume.volumeCone(3, 7) == 65.97344572538566);

        /* test prism */
        assertTrue(Volume.volumePrism(10, 2) == 20.0);

        /* test pyramid */
        assertTrue(Volume.volumePyramid(10, 3) == 10.0);
    }
}
