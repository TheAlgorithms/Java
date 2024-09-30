package com.thealgorithms.maths;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class VolumeTest {

    @Test
    public void volume(){

//        test cube
        assertTrue(Volume.volumeCube(7) == 343.0);
        assertTrue(Volume.volumeCuboid(2, 5, 7) == 70.0);
        assertTrue(Volume.volumeSphere(7) == 1436.7550402417319);
        assertTrue(Volume.volumeCylinder(3, 7) == 197.92033717615698);
        assertTrue(Volume.volumeHemisphere(7) == 718.3775201208659);
        assertTrue(Volume.volumeCone(3, 7) == 65.97344572538566);
        assertTrue(Volume.volumePrism(10, 2) == 20.0);
        assertTrue(Volume.volumePyramid(10, 3) == 10.0);
        assertTrue(Volume.volumeFrustumOfCone(3, 5, 7) == 359.188760060433);



    }

}
