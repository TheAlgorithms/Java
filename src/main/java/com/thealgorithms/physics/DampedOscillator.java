package com.thealgorithms.physics;

/**
 * Models a damped harmonic oscillator, capturing the behavior of a mass-spring-damper system.
 *
 * <p>The system is defined by the second-order differential equation:
 *     x'' + 2 * gamma * x' + omega₀² * x = 0
 * where:
 * <ul>
 *   <li><b>omega₀</b> is the natural (undamped) angular frequency in radians per second.</li>
 *   <li><b>gamma</b> is the damping coefficient in inverse seconds.</li>
 * </ul>
 *
 * <p>This implementation provides:
 * <ul>
 *   <li>An analytical solution for the underdamped case (γ < ω₀).</li>
 *   <li>A numerical integrator based on the explicit Euler method for simulation purposes.</li>
 * </ul>
 *
 * <p><strong>Usage Example:</strong>
 * <pre>{@code
 * DampedOscillator oscillator = new DampedOscillator(10.0, 0.5);
 * double displacement = oscillator.displacementAnalytical(1.0, 0.0, 0.1);
 * double[] nextState = oscillator.stepEuler(new double[]{1.0, 0.0}, 0.001);
 * }</pre>
 *
 * @author [Yash Rajput](https://github.com/the-yash-rajput)
 */
public final class DampedOscillator {

    /** Natural (undamped) angular frequency (rad/s). */
    private final double omega0;

    /** Damping coefficient (s⁻¹). */
    private final double gamma;

    private DampedOscillator() {
        throw new AssertionError("No instances.");
    }

    /**
     * Constructs a damped oscillator model.
     *
     * @param omega0 the natural frequency (rad/s), must be positive
     * @param gamma  the damping coefficient (s⁻¹), must be non-negative
     * @throws IllegalArgumentException if parameters are invalid
     */
    public DampedOscillator(double omega0, double gamma) {
        if (omega0 <= 0) {
            throw new IllegalArgumentException("Natural frequency must be positive.");
        }
        if (gamma < 0) {
            throw new IllegalArgumentException("Damping coefficient must be non-negative.");
        }
        this.omega0 = omega0;
        this.gamma = gamma;
    }

    /**
     * Computes the analytical displacement of an underdamped oscillator.
     * Formula: x(t) = A * exp(-γt) * cos(ω_d t + φ)
     *
     * @param amplitude the initial amplitude A
     * @param phase     the initial phase φ (radians)
     * @param time      the time t (seconds)
     * @return the displacement x(t)
     */
    public double displacementAnalytical(double amplitude, double phase, double time) {
        double omegaD = Math.sqrt(Math.max(0.0, omega0 * omega0 - gamma * gamma));
        return amplitude * Math.exp(-gamma * time) * Math.cos(omegaD * time + phase);
    }

    /**
     * Performs a single integration step using the explicit Euler method.
     * State vector format: [x, v], where v = dx/dt.
     *
     * @param state the current state [x, v]
     * @param dt    the time step (seconds)
     * @return the next state [x_next, v_next]
     * @throws IllegalArgumentException if the state array is invalid or dt is non-positive
     */
    public double[] stepEuler(double[] state, double dt) {
        if (state == null || state.length != 2) {
            throw new IllegalArgumentException("State must be a non-null array of length 2.");
        }
        if (dt <= 0) {
            throw new IllegalArgumentException("Time step must be positive.");
        }

        double x = state[0];
        double v = state[1];
        double acceleration = -2.0 * gamma * v - omega0 * omega0 * x;

        double xNext = x + dt * v;
        double vNext = v + dt * acceleration;

        return new double[] {xNext, vNext};
    }

    /** @return the natural (undamped) angular frequency (rad/s). */
    public double getOmega0() {
        return omega0;
    }

    /** @return the damping coefficient (s⁻¹). */
    public double getGamma() {
        return gamma;
    }
}
