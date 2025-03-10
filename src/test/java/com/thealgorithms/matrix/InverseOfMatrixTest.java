package com.thealgorithms.matrix;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InverseOfMatrixTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testInvert(double[][] matrix, double[][] expectedInverse) {
        double[][] result = InverseOfMatrix.invert(matrix);
        assertMatrixEquals(expectedInverse, result);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}), Arguments.of(new double[][] {{4, 7}, {2, 6}}, new double[][] {{0.6, -0.7}, {-0.2, 0.4}}));
    }

    private void assertMatrixEquals(double[][] expected, double[][] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], 1.0E-10, "Row " + i + " is not equal");
        }
    }
}
