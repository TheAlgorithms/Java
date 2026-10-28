package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DampedOscillator}.
 *
 * <p>Tests focus on:
 * <ul>
 *   <li>Constructor validation</li>
 *   <li>Analytical displacement for underdamped and overdamped parameterizations</li>
 *   <li>Basic numeric integration sanity using explicit Euler for small step sizes</li>
 *   <li>Method argument validation (null/invalid inputs)</li>
 * </ul>
 */
@DisplayName("DampedOscillator — unit tests")
public class DampedOscillatorTest {

    private static final double TOLERANCE = 1e-3;

    @Test
    @DisplayName("Constructor rejects invalid parameters")
    void constructorValidation() {
        assertAll("invalid-constructor-params",
            ()
                -> assertThrows(IllegalArgumentException.class, () -> new DampedOscillator(0.0, 0.1), "omega0 == 0 should throw"),
            () -> assertThrows(IllegalArgumentException.class, () -> new DampedOscillator(-1.0, 0.1), "negative omega0 should throw"), () -> assertThrows(IllegalArgumentException.class, () -> new DampedOscillator(1.0, -0.1), "negative gamma should throw"));
    }

    @Test
    @DisplayName("Analytical displacement matches expected formula for underdamped case")
    void analyticalUnderdamped() {
        double omega0 = 10.0;
        double gamma = 0.5;
        DampedOscillator d = new DampedOscillator(omega0, gamma);

        double a = 1.0;
        double phi = 0.2;
        double t = 0.123;

        // expected: a * exp(-gamma * t) * cos(omega_d * t + phi)
        double omegaD = Math.sqrt(Math.max(0.0, omega0 * omega0 - gamma * gamma));
        double expected = a * Math.exp(-gamma * t) * Math.cos(omegaD * t + phi);

        double actual = d.displacementAnalytical(a, phi, t);
        assertEquals(expected, actual, 1e-12, "Analytical underdamped displacement should match closed-form value");
    }

    @Test
    @DisplayName("Analytical displacement gracefully handles overdamped parameters (omegaD -> 0)")
    void analyticalOverdamped() {
        double omega0 = 1.0;
        double gamma = 2.0; // gamma > omega0 => omega_d = 0 in our implementation (Math.max)
        DampedOscillator d = new DampedOscillator(omega0, gamma);

        double a = 2.0;
        double phi = Math.PI / 4.0;
        double t = 0.5;

        // With omegaD forced to 0 by implementation, expected simplifies to:
        double expected = a * Math.exp(-gamma * t) * Math.cos(phi);
        double actual = d.displacementAnalytical(a, phi, t);

        assertEquals(expected, actual, 1e-12, "Overdamped handling should reduce to exponential * cos(phase)");
    }

    @Test
    @DisplayName("Explicit Euler step approximates analytical solution for small dt over short time")
    void eulerApproximatesAnalyticalSmallDt() {
        double omega0 = 10.0;
        double gamma = 0.5;
        DampedOscillator d = new DampedOscillator(omega0, gamma);

        double a = 1.0;
        double phi = 0.0;

        // initial conditions consistent with amplitude a and zero phase:
        // x(0) = a, v(0) = -a * gamma * cos(phi) + a * omegaD * sin(phi)
        double omegaD = Math.sqrt(Math.max(0.0, omega0 * omega0 - gamma * gamma));
        double x0 = a * Math.cos(phi);
        double v0 = -a * gamma * Math.cos(phi) - a * omegaD * Math.sin(phi); // small general form

        double dt = 1e-4;
        int steps = 1000; // simulate to t = 0.1s
        double tFinal = steps * dt;

        double[] state = new double[] {x0, v0};
        for (int i = 0; i < steps; i++) {
            state = d.stepEuler(state, dt);
        }

        double analyticAtT = d.displacementAnalytical(a, phi, tFinal);
        double numericAtT = state[0];

        // Euler is low-order — allow a small tolerance but assert it remains close for small dt + short time.
        assertEquals(analyticAtT, numericAtT, TOLERANCE, String.format("Numeric Euler should approximate analytical solution at t=%.6f (tolerance=%g)", tFinal, TOLERANCE));
    }

    @Test
    @DisplayName("stepEuler validates inputs and throws on null/invalid dt/state")
    void eulerInputValidation() {
        DampedOscillator d = new DampedOscillator(5.0, 0.1);

        assertAll("invalid-stepEuler-args",
            ()
                -> assertThrows(IllegalArgumentException.class, () -> d.stepEuler(null, 0.01), "null state should throw"),
            ()
                -> assertThrows(IllegalArgumentException.class, () -> d.stepEuler(new double[] {1.0}, 0.01), "state array with invalid length should throw"),
            () -> assertThrows(IllegalArgumentException.class, () -> d.stepEuler(new double[] {1.0, 0.0}, 0.0), "non-positive dt should throw"), () -> assertThrows(IllegalArgumentException.class, () -> d.stepEuler(new double[] {1.0, 0.0}, -1e-3), "negative dt should throw"));
    }

    @Test
    @DisplayName("Getter methods return configured parameters")
    void gettersReturnConfiguration() {
        double omega0 = Math.PI;
        double gamma = 0.01;
        DampedOscillator d = new DampedOscillator(omega0, gamma);

        assertAll("getters", () -> assertEquals(omega0, d.getOmega0(), 0.0, "getOmega0 should return configured omega0"), () -> assertEquals(gamma, d.getGamma(), 0.0, "getGamma should return configured gamma"));
    }

    @Test
    @DisplayName("Analytical displacement at t=0 returns initial amplitude * cos(phase)")
    void analyticalAtZeroTime() {
        double omega0 = 5.0;
        double gamma = 0.2;
        DampedOscillator d = new DampedOscillator(omega0, gamma);

        double a = 2.0;
        double phi = Math.PI / 3.0;
        double t = 0.0;

        double expected = a * Math.cos(phi);
        double actual = d.displacementAnalytical(a, phi, t);

        assertEquals(expected, actual, 1e-12, "Displacement at t=0 should be a * cos(phase)");
    }
}
