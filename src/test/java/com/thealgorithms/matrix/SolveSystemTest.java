package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolveSystemTest {

    @ParameterizedTest
    @MethodSource({"matrixGenerator"})
    void solveSystem(double[][] matrix, double[] constants, double[] solution) {
        double[] expected = SolveSystem.solveSystem(matrix, constants);
        assertArrayEquals(expected, solution, 1.0E-10, "Solution does not match expected");
    }
    private static Stream<Arguments> matrixGenerator() {
        return Stream.of(Arguments.of(new double[][] {{-5, 8, -4}, {0, 6, 3}, {0, 0, -4}}, new double[] {38, -9, 20}, new double[] {-2, 1, -5}), Arguments.of(new double[][] {{-2, -1, -1}, {3, 4, 1}, {3, 6, 5}}, new double[] {-11, 19, 43}, new double[] {2, 2, 5}));
    }
}
