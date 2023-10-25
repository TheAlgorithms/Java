package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatrixRankTest {

    private static Stream<Arguments> validInputStream() {
        return Stream.of(Arguments.of(3, new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}), Arguments.of(0, new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}), Arguments.of(1, new double[][] {{1}}), Arguments.of(2, new double[][] {{1, 2}, {3, 4}}),
            Arguments.of(2, new double[][] {{3, -1, 2}, {-3, 1, 2}, {-6, 2, 4}}), Arguments.of(3, new double[][] {{2, 3, 0, 1}, {1, 0, 1, 2}, {-1, 1, 1, -2}, {1, 5, 3, -1}}), Arguments.of(1, new double[][] {{1, 2, 3}, {3, 6, 9}}),
            Arguments.of(2, new double[][] {{0.25, 0.5, 0.75, 2}, {1.5, 3, 4.5, 6}, {1, 2, 3, 4}}));
    }

    private static Stream<Arguments> invalidInputStream() {
        return Stream.of(Arguments.of((Object) new double[][] {{1, 2}, {10}, {100, 200, 300}}), // jagged array
            Arguments.of((Object) new double[][] {}), // empty matrix
            Arguments.of((Object) new double[][] {{}, {}}), // empty row
            Arguments.of((Object) null), // null matrix
            Arguments.of((Object) new double[][] {{1, 2}, null}) // null row
        );
    }

    @ParameterizedTest
    @MethodSource("validInputStream")
    void computeRankTests(int expectedRank, double[][] matrix) {
        int originalHashCode = Arrays.deepHashCode(matrix);
        int rank = MatrixRank.computeRank(matrix);
        int newHashCode = Arrays.deepHashCode(matrix);

        assertEquals(expectedRank, rank);
        assertEquals(originalHashCode, newHashCode);
    }

    @ParameterizedTest
    @MethodSource("invalidInputStream")
    void computeRankWithInvalidMatrix(double[][] matrix) {
        assertThrows(IllegalArgumentException.class, () -> MatrixRank.computeRank(matrix));
    }
}
