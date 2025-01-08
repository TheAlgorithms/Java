package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MatrixTransposeTest {

    private static Stream<Arguments> provideValidMatrixTestCases() {
        return Stream.of(Arguments.of(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new int[][] {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}, "Transpose of square matrix"), Arguments.of(new int[][] {{1, 2}, {3, 4}, {5, 6}}, new int[][] {{1, 3, 5}, {2, 4, 6}}, "Transpose of rectangular matrix"),
            Arguments.of(new int[][] {{1, 2, 3}}, new int[][] {{1}, {2}, {3}}, "Transpose of single-row matrix"), Arguments.of(new int[][] {{1}, {2}, {3}}, new int[][] {{1, 2, 3}}, "Transpose of single-column matrix"));
    }

    private static Stream<Arguments> provideInvalidMatrixTestCases() {
        return Stream.of(Arguments.of(new int[0][0], "Empty matrix should throw IllegalArgumentException"), Arguments.of(null, "Null matrix should throw IllegalArgumentException"));
    }

    @ParameterizedTest(name = "Test case {index}: {2}")
    @MethodSource("provideValidMatrixTestCases")
    void testValidMatrixTranspose(int[][] input, int[][] expected, String description) {
        assertArrayEquals(expected, MatrixTranspose.transpose(input), description);
    }

    @ParameterizedTest(name = "Test case {index}: {1}")
    @MethodSource("provideInvalidMatrixTestCases")
    void testInvalidMatrixTranspose(int[][] input, String description) {
        assertThrows(IllegalArgumentException.class, () -> MatrixTranspose.transpose(input), description);
    }
}
