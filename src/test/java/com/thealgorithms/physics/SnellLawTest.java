package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SnellLawTest {
//tests for example environmet
    @Test
    public void testCalculateRefractionAngle() {
        double n1 = 1.0;  // air
        double n2 = 1.5;  // glass
        double theta1 = Math.toRadians(30); // 30 grados
        
        double theta2 = SnellsLaw.calculateRefractionAngle(n1, n2, theta1);
        
        // expected value using: sin(theta2) = n1/n2 * sin(theta1)
        double expected = Math.asin((n1 / n2) * Math.sin(theta1));

        assertEquals(expected, theta2, 1e-9);
    }
//tests for total refraction
    @Test
    public void testInvalidRefractiveIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            SnellsLaw.calculateRefractionAngle(-1, 1.5, 0.5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            SnellsLaw.calculateRefractionAngle(1, 0, 0.5);
        });
    }
}
