package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EulerMethodTest {

    private static class EulerFullTestCase {
        double[] params;
        BiFunction<Double, Double, Double> equation;
        int expectedSize;
        double[] expectedFirstPoint;
        double[] expectedLastPoint;

        EulerFullTestCase(double[] params, BiFunction<Double, Double, Double> equation, int expectedSize, double[] expectedFirstPoint, double[] expectedLastPoint) {
            this.params = params;
            this.equation = equation;
            this.expectedSize = expectedSize;
            this.expectedFirstPoint = expectedFirstPoint;
            this.expectedLastPoint = expectedLastPoint;
        }
    }

    @ParameterizedTest
    @MethodSource("eulerStepTestCases")
    void testEulerStep(double x, double h, double y, BiFunction<Double, Double, Double> equation, double expected) {
        double result = EulerMethod.eulerStep(x, h, y, equation);
        assertEquals(expected, result, 1e-9, "Euler step failed for the given equation.");
    }

    static Stream<Arguments> eulerStepTestCases() {
        return Stream.of(Arguments.of(0.0, 0.1, 1.0, (BiFunction<Double, Double, Double>) ((x, y) -> x + y), 1.1));
    }

    @ParameterizedTest
    @MethodSource("eulerStepInvalidCases")
    void testEulerStepInvalidInput(double x, double h, double y, BiFunction<Double, Double, Double> equation, Class<? extends Exception> expectedExceptionClass) {
        assertThrows(expectedExceptionClass, () -> EulerMethod.eulerStep(x, h, y, equation));
    }

    static Stream<Arguments> eulerStepInvalidCases() {
        BiFunction<Double, Double, Double> dummyEquation = (x, y) -> x + y;
        return Stream.of(Arguments.of(0.0, -0.1, 1.0, dummyEquation, IllegalArgumentException.class), Arguments.of(0.0, 0.0, 1.0, dummyEquation, IllegalArgumentException.class));
    }

    @ParameterizedTest
    @MethodSource("eulerFullTestCases")
    void testEulerFull(EulerFullTestCase testCase) {
        ArrayList<double[]> result = EulerMethod.eulerFull(testCase.params[0], testCase.params[1], testCase.params[2], testCase.params[3], testCase.equation);
        assertEquals(testCase.expectedSize, result.size(), "Incorrect number of points in the result.");
        assertArrayEquals(testCase.expectedFirstPoint, result.get(0), 1e-9, "Incorrect first point.");
        assertArrayEquals(testCase.expectedLastPoint, result.get(result.size() - 1), 1e-9, "Incorrect last point.");
    }

    static Stream<Arguments> eulerFullTestCases() {
        return Stream.of(Arguments.of(new EulerFullTestCase(new double[] {0.0, 1.0, 0.5, 0.0}, (x, y) -> x, 3, new double[] {0.0, 0.0}, new double[] {1.0, 0.25})),
            Arguments.of(new EulerFullTestCase(new double[] {0.0, 1.0, 0.1, 1.0}, (x, y) -> y, 12, new double[] {0.0, 1.0}, new double[] {1.0999999999999999, 2.8531167061100002})),
            Arguments.of(new EulerFullTestCase(new double[] {0.0, 0.1, 0.1, 1.0}, (x, y) -> x + y, 2, new double[] {0.0, 1.0}, new double[] {0.1, 1.1})));
    }

    @ParameterizedTest
    @MethodSource("eulerFullInvalidCases")
    void testEulerFullInvalidInput(double xStart, double xEnd, double stepSize, double yInitial, BiFunction<Double, Double, Double> equation, Class<? extends Exception> expectedExceptionClass) {
        assertThrows(expectedExceptionClass, () -> EulerMethod.eulerFull(xStart, xEnd, stepSize, yInitial, equation));
    }

    static Stream<Arguments> eulerFullInvalidCases() {
        BiFunction<Double, Double, Double> dummyEquation = (x, y) -> x + y;
        return Stream.of(Arguments.of(1.0, 0.0, 0.1, 1.0, dummyEquation, IllegalArgumentException.class), Arguments.of(0.0, 1.0, 0.0, 1.0, dummyEquation, IllegalArgumentException.class), Arguments.of(0.0, 1.0, -0.1, 1.0, dummyEquation, IllegalArgumentException.class));
    }
}
