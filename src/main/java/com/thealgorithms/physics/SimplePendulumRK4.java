package com.thealgorithms.physics;

/**
 * Simulates a simple pendulum using the Runge-Kutta 4th order method.
 * The pendulum is modeled with the nonlinear differential equation.
 *
 * @author [Yash Rajput](https://github.com/the-yash-rajput)
 */
public final class SimplePendulumRK4 {

    private SimplePendulumRK4() {
        throw new AssertionError("No instances.");
    }

    private final double length; // meters
    private final double g; // acceleration due to gravity (m/s^2)

    /**
     * Constructs a simple pendulum simulator.
     *
     * @param length the length of the pendulum in meters
     * @param g the acceleration due to gravity in m/s^2
     */
    public SimplePendulumRK4(double length, double g) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        if (g <= 0) {
            throw new IllegalArgumentException("Gravity must be positive");
        }
        this.length = length;
        this.g = g;
    }

    /**
     * Computes the derivatives of the state vector.
     * State: [theta, omega] where theta is angle and omega is angular velocity.
     *
     * @param state the current state [theta, omega]
     * @return the derivatives [dtheta/dt, domega/dt]
     */
    private double[] derivatives(double[] state) {
        double theta = state[0];
        double omega = state[1];
        double dtheta = omega;
        double domega = -(g / length) * Math.sin(theta);
        return new double[] {dtheta, domega};
    }

    /**
     * Performs one time step using the RK4 method.
     *
     * @param state the current state [theta, omega]
     * @param dt the time step size
     * @return the new state after time dt
     */
    public double[] stepRK4(double[] state, double dt) {
        if (state == null || state.length != 2) {
            throw new IllegalArgumentException("State must be array of length 2");
        }
        if (dt <= 0) {
            throw new IllegalArgumentException("Time step must be positive");
        }

        double[] k1 = derivatives(state);
        double[] s2 = new double[] {state[0] + 0.5 * dt * k1[0], state[1] + 0.5 * dt * k1[1]};

        double[] k2 = derivatives(s2);
        double[] s3 = new double[] {state[0] + 0.5 * dt * k2[0], state[1] + 0.5 * dt * k2[1]};

        double[] k3 = derivatives(s3);
        double[] s4 = new double[] {state[0] + dt * k3[0], state[1] + dt * k3[1]};

        double[] k4 = derivatives(s4);

        double thetaNext = state[0] + dt / 6.0 * (k1[0] + 2 * k2[0] + 2 * k3[0] + k4[0]);
        double omegaNext = state[1] + dt / 6.0 * (k1[1] + 2 * k2[1] + 2 * k3[1] + k4[1]);

        return new double[] {thetaNext, omegaNext};
    }

    /**
     * Simulates the pendulum for a given duration.
     *
     * @param initialState the initial state [theta, omega]
     * @param dt the time step size
     * @param steps the number of steps to simulate
     * @return array of states at each step
     */
    public double[][] simulate(double[] initialState, double dt, int steps) {
        double[][] trajectory = new double[steps + 1][2];
        trajectory[0] = initialState.clone();

        double[] currentState = initialState.clone();
        for (int i = 1; i <= steps; i++) {
            currentState = stepRK4(currentState, dt);
            trajectory[i] = currentState.clone();
        }

        return trajectory;
    }

    /**
     * Calculates the total energy of the pendulum.
     * E = (1/2) * m * L^2 * omega^2 + m * g * L * (1 - cos(theta))
     * We use m = 1 for simplicity.
     *
     * @param state the current state [theta, omega]
     * @return the total energy
     */
    public double calculateEnergy(double[] state) {
        double theta = state[0];
        double omega = state[1];
        double kineticEnergy = 0.5 * length * length * omega * omega;
        double potentialEnergy = g * length * (1 - Math.cos(theta));
        return kineticEnergy + potentialEnergy;
    }

    public double getLength() {
        return length;
    }

    public double getGravity() {
        return g;
    }
}
