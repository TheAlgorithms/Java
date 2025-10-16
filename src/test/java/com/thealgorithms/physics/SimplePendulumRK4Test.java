package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for SimplePendulumRK4.
 * Tests numerical accuracy, physical correctness, and edge cases.
 */
class SimplePendulumRK4Test {

    private static final double EPSILON = 1e-6;
    private static final double ENERGY_DRIFT_TOLERANCE = 1e-3;
    @Test
    @DisplayName("Test constructor creates valid pendulum")
    void testConstructor() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.5, 9.81);
        assertNotNull(pendulum);
        assertEquals(1.5, pendulum.getLength(), EPSILON);
        assertEquals(9.81, pendulum.getGravity(), EPSILON);
    }

    @Test
    @DisplayName("Test constructor rejects negative length")
    void testConstructorNegativeLength() {
        assertThrows(IllegalArgumentException.class, () -> { new SimplePendulumRK4(-1.0, 9.81); });
    }

    @Test
    @DisplayName("Test constructor rejects negative gravity")
    void testConstructorNegativeGravity() {
        assertThrows(IllegalArgumentException.class, () -> { new SimplePendulumRK4(1.0, -9.81); });
    }

    @Test
    @DisplayName("Test constructor rejects zero length")
    void testConstructorZeroLength() {
        assertThrows(IllegalArgumentException.class, () -> { new SimplePendulumRK4(0.0, 9.81); });
    }

    @Test
    @DisplayName("Test getters return correct values")
    void testGetters() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(2.5, 10.0);
        assertEquals(2.5, pendulum.getLength(), EPSILON);
        assertEquals(10.0, pendulum.getGravity(), EPSILON);
    }

    @Test
    @DisplayName("Test single RK4 step returns valid state")
    void testSingleStep() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {0.1, 0.0};
        double[] newState = pendulum.stepRK4(state, 0.01);

        assertNotNull(newState);
        assertEquals(2, newState.length);
    }

    @Test
    @DisplayName("Test equilibrium stability (pendulum at rest stays at rest)")
    void testEquilibrium() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {0.0, 0.0};

        for (int i = 0; i < 100; i++) {
            state = pendulum.stepRK4(state, 0.01);
        }

        assertEquals(0.0, state[0], EPSILON, "Theta should remain at equilibrium");
        assertEquals(0.0, state[1], EPSILON, "Omega should remain zero");
    }

    @Test
    @DisplayName("Test small angle oscillation returns to initial position")
    void testSmallAngleOscillation() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double initialAngle = Math.toRadians(5.0);
        double[] state = {initialAngle, 0.0};
        double dt = 0.01;

        // Theoretical period for small angles
        double expectedPeriod = 2 * Math.PI * Math.sqrt(1.0 / 9.81);
        int stepsPerPeriod = (int) (expectedPeriod / dt);

        double[][] trajectory = pendulum.simulate(state, dt, stepsPerPeriod);
        double finalTheta = trajectory[stepsPerPeriod][0];

        // After one period, should return close to initial position
        double error = Math.abs(finalTheta - initialAngle) / Math.abs(initialAngle);
        assertTrue(error < 0.05, "Small angle approximation error should be < 5%");
    }

    @Test
    @DisplayName("Test large angle oscillation is symmetric")
    void testLargeAngleOscillation() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(120.0), 0.0};

        double[][] trajectory = pendulum.simulate(state, 0.01, 500);

        double maxTheta = Double.NEGATIVE_INFINITY;
        double minTheta = Double.POSITIVE_INFINITY;

        for (double[] s : trajectory) {
            maxTheta = Math.max(maxTheta, s[0]);
            minTheta = Math.min(minTheta, s[0]);
        }

        assertTrue(maxTheta > 0, "Should have positive excursions");
        assertTrue(minTheta < 0, "Should have negative excursions");

        // Check symmetry
        double asymmetry = Math.abs((maxTheta + minTheta) / maxTheta);
        assertTrue(asymmetry < 0.1, "Oscillation should be symmetric");
    }

    @Test
    @DisplayName("Test energy conservation for small angle")
    void testEnergyConservationSmallAngle() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(15.0), 0.0};

        double initialEnergy = pendulum.calculateEnergy(state);

        for (int i = 0; i < 1000; i++) {
            state = pendulum.stepRK4(state, 0.01);
        }

        double finalEnergy = pendulum.calculateEnergy(state);
        double drift = Math.abs(finalEnergy - initialEnergy) / initialEnergy;

        assertTrue(drift < ENERGY_DRIFT_TOLERANCE, "Energy drift should be < 0.1%, got: " + (drift * 100) + "%");
    }

    @Test
    @DisplayName("Test energy conservation for large angle")
    void testEnergyConservationLargeAngle() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(90.0), 0.0};

        double initialEnergy = pendulum.calculateEnergy(state);

        for (int i = 0; i < 1000; i++) {
            state = pendulum.stepRK4(state, 0.01);
        }

        double finalEnergy = pendulum.calculateEnergy(state);
        double drift = Math.abs(finalEnergy - initialEnergy) / initialEnergy;

        assertTrue(drift < ENERGY_DRIFT_TOLERANCE, "Energy drift should be < 0.1%, got: " + (drift * 100) + "%");
    }

    @Test
    @DisplayName("Test simulate method returns correct trajectory")
    void testSimulate() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] initialState = {Math.toRadians(20.0), 0.0};
        int steps = 100;

        double[][] trajectory = pendulum.simulate(initialState, 0.01, steps);

        assertEquals(steps + 1, trajectory.length, "Trajectory should have steps + 1 entries");
        assertArrayEquals(initialState, trajectory[0], EPSILON, "First entry should match initial state");

        // Verify state changes over time
        boolean changed = false;
        for (int i = 1; i <= steps; i++) {
            if (Math.abs(trajectory[i][0] - initialState[0]) > EPSILON) {
                changed = true;
                break;
            }
        }
        assertTrue(changed, "Simulation should progress from initial state");
    }

    @Test
    @DisplayName("Test energy calculation at equilibrium")
    void testEnergyAtEquilibrium() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {0.0, 0.0};
        double energy = pendulum.calculateEnergy(state);
        assertEquals(0.0, energy, EPSILON, "Energy at equilibrium should be zero");
    }

    @Test
    @DisplayName("Test energy calculation at maximum angle")
    void testEnergyAtMaxAngle() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.PI / 2, 0.0};
        double energy = pendulum.calculateEnergy(state);
        assertTrue(energy > 0, "Energy should be positive at max angle");
    }

    @Test
    @DisplayName("Test energy calculation with angular velocity")
    void testEnergyWithVelocity() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {0.0, 1.0};
        double energy = pendulum.calculateEnergy(state);
        assertTrue(energy > 0, "Energy should be positive with velocity");
    }

    @Test
    @DisplayName("Test stepRK4 rejects null state")
    void testStepRejectsNullState() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        assertThrows(IllegalArgumentException.class, () -> { pendulum.stepRK4(null, 0.01); });
    }

    @Test
    @DisplayName("Test stepRK4 rejects invalid state length")
    void testStepRejectsInvalidStateLength() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        assertThrows(IllegalArgumentException.class, () -> { pendulum.stepRK4(new double[] {0.1}, 0.01); });
    }

    @Test
    @DisplayName("Test stepRK4 rejects negative time step")
    void testStepRejectsNegativeTimeStep() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        assertThrows(IllegalArgumentException.class, () -> { pendulum.stepRK4(new double[] {0.1, 0.2}, -0.01); });
    }

    @Test
    @DisplayName("Test extreme condition: very large angle")
    void testExtremeLargeAngle() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(179.0), 0.0};
        double[] result = pendulum.stepRK4(state, 0.01);

        assertNotNull(result);
        assertTrue(Double.isFinite(result[0]), "Should handle large angles without NaN");
        assertTrue(Double.isFinite(result[1]), "Should handle large angles without NaN");
    }

    @Test
    @DisplayName("Test extreme condition: high angular velocity")
    void testExtremeHighVelocity() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {0.0, 10.0};
        double[] result = pendulum.stepRK4(state, 0.01);

        assertNotNull(result);
        assertTrue(Double.isFinite(result[0]), "Should handle high velocity without NaN");
        assertTrue(Double.isFinite(result[1]), "Should handle high velocity without NaN");
    }

    @Test
    @DisplayName("Test extreme condition: very small time step")
    void testExtremeSmallTimeStep() {
        SimplePendulumRK4 pendulum = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(10.0), 0.0};
        double[] result = pendulum.stepRK4(state, 1e-6);

        assertNotNull(result);
        assertTrue(Double.isFinite(result[0]), "Should handle small time steps without NaN");
        assertTrue(Double.isFinite(result[1]), "Should handle small time steps without NaN");
    }
}
