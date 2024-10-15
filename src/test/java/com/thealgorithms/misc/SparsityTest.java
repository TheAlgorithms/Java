package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SparsityTest {

    private static final double DELTA = 1e-9;

    @ParameterizedTest(name = "Test case {index}: {2}")
    @MethodSource("provideTestCases")
    public void testSparsity(double[][] matrix, double expectedSparsity, String description) {
        assertEquals(expectedSparsity, Sparsity.sparsity(matrix), DELTA, description);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, 1.0, "Matrix with all zero elements"), Arguments.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 0.0, "Matrix with no zero elements"),
            Arguments.of(new double[][] {{0, 2, 0}, {4, 0, 6}, {0, 8, 0}}, 5.0 / 9.0, "Matrix with mixed elements"), Arguments.of(new double[][] {{0, 1, 0, 2, 0}}, 3.0 / 5.0, "Single-row matrix"), Arguments.of(new double[][] {{1}, {0}, {0}, {2}}, 2.0 / 4.0, "Single-column matrix"),
            Arguments.of(new double[][] {{0}}, 1.0, "Matrix with a single zero element"), Arguments.of(new double[][] {{5}}, 0.0, "Matrix with a single non-zero element"));
    }

    @ParameterizedTest(name = "Test case {index}: {1}")
    @MethodSource("provideExceptionTestCases")
    public void testSparsityExceptions(double[][] matrix, String description) {
        assertThrows(IllegalArgumentException.class, () -> Sparsity.sparsity(matrix), description);
    }

    private static Stream<Arguments> provideExceptionTestCases() {
        return Stream.of(Arguments.of(new double[][] {}, "Empty matrix should throw IllegalArgumentException"));
    }
}
