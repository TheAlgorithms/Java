package com.thealgorithms.physics;

/**
 * Comprehensive test suite for SimplePendulumRK4.
 * Tests numerical accuracy, physical correctness, and edge cases.
 */
public class SimplePendulumRK4Test {

    private static final double EPSILON = 1e-6;

    // ANSI color codes for terminal output
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";

    private static int testCount = 0;
    private static int passCount = 0;

    public static void main(String[] args) {
        printHeader();

        // Basic functionality tests
        runTest("Constructor Validation", SimplePendulumRK4Test::testConstructor);
        runTest("Getters", SimplePendulumRK4Test::testGetters);
        runTest("Single Step RK4", SimplePendulumRK4Test::testSingleStep);

        // Physics tests
        runTest("Equilibrium Stability", SimplePendulumRK4Test::testEquilibrium);
        runTest("Small Angle Oscillation", SimplePendulumRK4Test::testSmallAngle);
        runTest("Large Angle Oscillation", SimplePendulumRK4Test::testLargeAngle);
        runTest("Energy Conservation (Small Angle)", SimplePendulumRK4Test::testEnergySmallAngle);
        runTest("Energy Conservation (Large Angle)", SimplePendulumRK4Test::testEnergyLargeAngle);

        // Method tests
        runTest("Simulate Method", SimplePendulumRK4Test::testSimulate);
        runTest("Energy Calculation", SimplePendulumRK4Test::testEnergyCalculation);

        // Edge cases and error handling
        runTest("Invalid Constructor Arguments", SimplePendulumRK4Test::testInvalidConstructor);
        runTest("Invalid Step Arguments", SimplePendulumRK4Test::testInvalidStep);
        runTest("Extreme Initial Conditions", SimplePendulumRK4Test::testExtremeConditions);

        printSummary();
    }

    private static void runTest(String name, TestCase test) {
        testCount++;
        System.out.print(BLUE + "Test " + testCount + ": " + RESET + name + " ... ");

        try {
            test.run();
            passCount++;
            System.out.println(GREEN + "✓ PASSED" + RESET);
        } catch (AssertionError e) {
            System.out.println(RED + "✗ FAILED" + RESET);
            System.out.println("  " + RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "✗ ERROR" + RESET);
            System.out.println("  " + RED + "Exception: " + e.getMessage() + RESET);
        }
    }

    private static void printHeader() {
        System.out.println("\n"
            + "=".repeat(60));
        System.out.println(YELLOW + "  SimplePendulumRK4 Test Suite" + RESET);
        System.out.println("=".repeat(60) + "\n");
    }

    private static void printSummary() {
        System.out.println("\n"
            + "=".repeat(60));
        double percentage = (passCount * 100.0) / testCount;
        String color = percentage == 100 ? GREEN : (percentage >= 80 ? YELLOW : RED);
        System.out.println(color + "  Results: " + passCount + "/" + testCount + " tests passed (" + String.format("%.1f", percentage) + "%)" + RESET);
        System.out.println("=".repeat(60) + "\n");
    }

    // ==================== Test Cases ====================

    private static void testConstructor() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.5, 9.81);
        assertTrue(p != null, "Constructor should create object");
    }

    private static void testGetters() {
        SimplePendulumRK4 p = new SimplePendulumRK4(2.5, 10.0);
        assertEquals(2.5, p.getLength(), EPSILON, "Length getter");
        assertEquals(10.0, p.getGravity(), EPSILON, "Gravity getter");
    }

    private static void testSingleStep() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {0.1, 0.0};
        double[] newState = p.stepRK4(state, 0.01);

        assertTrue(newState != null, "Step should return non-null state");
        assertTrue(newState.length == 2, "State should have length 2");
        assertTrue(newState != state, "Step should return new array");
    }

    private static void testEquilibrium() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {0.0, 0.0};

        for (int i = 0; i < 100; i++) {
            state = p.stepRK4(state, 0.01);
        }

        assertTrue(Math.abs(state[0]) < EPSILON, "Theta should remain at equilibrium");
        assertTrue(Math.abs(state[1]) < EPSILON, "Omega should remain zero");
    }

    private static void testSmallAngle() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(5.0), 0.0};
        double dt = 0.01;

        // Theoretical period for small angles
        double expectedPeriod = 2 * Math.PI * Math.sqrt(1.0 / 9.81);
        int stepsPerPeriod = (int) (expectedPeriod / dt);

        double[][] trajectory = p.simulate(state, dt, stepsPerPeriod);
        double finalTheta = trajectory[stepsPerPeriod][0];

        // After one period, should return close to initial position
        double error = Math.abs(finalTheta - state[0]) / Math.abs(state[0]);
        assertTrue(error < 0.05, "Small angle approximation error: " + error);
    }

    private static void testLargeAngle() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(120.0), 0.0};

        double[][] trajectory = p.simulate(state, 0.01, 500);

        // Check that pendulum oscillates
        double maxTheta = Double.NEGATIVE_INFINITY;
        double minTheta = Double.POSITIVE_INFINITY;

        for (double[] s : trajectory) {
            maxTheta = Math.max(maxTheta, s[0]);
            minTheta = Math.min(minTheta, s[0]);
        }

        assertTrue(maxTheta > 0, "Should have positive excursions");
        assertTrue(minTheta < 0, "Should have negative excursions");
    }

    private static void testEnergySmallAngle() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(15.0), 0.0};

        testEnergyConservation(p, state, 0.01, 1000, 0.001);
    }

    private static void testEnergyLargeAngle() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(90.0), 0.0};

        testEnergyConservation(p, state, 0.01, 1000, 0.001);
    }

    private static void testEnergyConservation(SimplePendulumRK4 p, double[] initialState, double dt, int steps, double maxDrift) {
        double initialEnergy = p.calculateEnergy(initialState);
        double[] state = initialState.clone();

        for (int i = 0; i < steps; i++) {
            state = p.stepRK4(state, dt);
        }

        double finalEnergy = p.calculateEnergy(state);
        double drift = Math.abs(finalEnergy - initialEnergy) / initialEnergy;

        assertTrue(drift < maxDrift, String.format("Energy drift %.6f exceeds limit %.6f", drift, maxDrift));
    }

    private static void testPeriodAccuracy(double angleDegrees) {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] state = {Math.toRadians(angleDegrees), 0.0};
        double dt = 0.001;

        // Measure period by detecting zero crossings with positive velocity
        double prevTheta = state[0];
        int zeroCrossings = 0;
        int periodSteps = 0;

        for (int i = 0; i < 10000; i++) {
            state = p.stepRK4(state, dt);

            // Detect zero crossing with positive velocity (moving right)
            if (prevTheta < 0 && state[0] >= 0 && state[1] > 0) {
                zeroCrossings++;
                if (zeroCrossings == 2) {
                    // Two zero crossings = one complete period
                    periodSteps = i + 1;
                    break;
                }
            }

            prevTheta = state[0];
        }

        assertTrue(periodSteps > 0, "Could not measure period");

        double measuredPeriod = periodSteps * dt;
        double theoreticalPeriod = 2 * Math.PI * Math.sqrt(1.0 / 9.81);

        // For small angles, should match theoretical period closely
        if (angleDegrees < 10) {
            double error = Math.abs(measuredPeriod - theoreticalPeriod) / theoreticalPeriod;
            assertTrue(error < 0.02, "Period error: " + error);
        } else {
            // For larger angles, just verify we got a reasonable period
            assertTrue(measuredPeriod > theoreticalPeriod * 0.8, "Period too short");
            assertTrue(measuredPeriod < theoreticalPeriod * 1.5, "Period too long");
        }
    }

    private static void testSimulate() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);
        double[] initialState = {Math.toRadians(20.0), 0.0};
        int steps = 100;

        double[][] trajectory = p.simulate(initialState, 0.01, steps);

        assertEquals(steps + 1, trajectory.length, 0, "Trajectory length");
        assertEquals(initialState[0], trajectory[0][0], EPSILON, "Initial theta");
        assertEquals(initialState[1], trajectory[0][1], EPSILON, "Initial omega");

        // Verify state changes
        boolean changed = false;
        for (int i = 1; i <= steps; i++) {
            if (Math.abs(trajectory[i][0] - initialState[0]) > EPSILON) {
                changed = true;
                break;
            }
        }
        assertTrue(changed, "Simulation should progress");
    }

    private static void testEnergyCalculation() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);

        // At equilibrium with no velocity: E = 0
        double[] state1 = {0.0, 0.0};
        double E1 = p.calculateEnergy(state1);
        assertEquals(0.0, E1, EPSILON, "Energy at equilibrium");

        // At rest at max angle: E = potential only
        double[] state2 = {Math.PI / 2, 0.0};
        double E2 = p.calculateEnergy(state2);
        assertTrue(E2 > 0, "Energy should be positive at max angle");

        // Energy with angular velocity
        double[] state3 = {0.0, 1.0};
        double E3 = p.calculateEnergy(state3);
        assertTrue(E3 > 0, "Energy should be positive with velocity");
    }

    private static void testInvalidConstructor() {
        boolean caught = false;
        try {
            new SimplePendulumRK4(-1.0, 9.81);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught, "Should reject negative length");

        caught = false;
        try {
            new SimplePendulumRK4(1.0, -9.81);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught, "Should reject negative gravity");

        caught = false;
        try {
            new SimplePendulumRK4(0.0, 9.81);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught, "Should reject zero length");
    }

    private static void testInvalidStep() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);

        boolean caught = false;
        try {
            p.stepRK4(null, 0.01);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught, "Should reject null state");

        caught = false;
        try {
            p.stepRK4(new double[] {0.1}, 0.01);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught, "Should reject wrong state length");

        caught = false;
        try {
            p.stepRK4(new double[] {0.1, 0.2}, -0.01);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught, "Should reject negative dt");
    }

    private static void testExtremeConditions() {
        SimplePendulumRK4 p = new SimplePendulumRK4(1.0, 9.81);

        // Very large angle
        double[] state1 = {Math.toRadians(179.0), 0.0};
        double[] result1 = p.stepRK4(state1, 0.01);
        assertTrue(!Double.isNaN(result1[0]), "Should handle large angles");

        // Very high angular velocity
        double[] state2 = {0.0, 10.0};
        double[] result2 = p.stepRK4(state2, 0.01);
        assertTrue(!Double.isNaN(result2[0]), "Should handle high velocity");

        // Very small time step
        double[] state3 = {Math.toRadians(10.0), 0.0};
        double[] result3 = p.stepRK4(state3, 1e-6);
        assertTrue(!Double.isNaN(result3[0]), "Should handle small dt");
    }

    // ==================== Helper Methods ====================

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertEquals(double expected, double actual, double tolerance, String message) {
        if (Math.abs(expected - actual) > tolerance) {
            throw new AssertionError(message + String.format(": expected %.6f but got %.6f", expected, actual));
        }
    }

    @FunctionalInterface
    interface TestCase {
        void run();
    }
}
