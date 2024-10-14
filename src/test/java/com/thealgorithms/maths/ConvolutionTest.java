package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ConvolutionTest {

    record ConvolutionTestCase(String description, double[] signalA, double[] signalB, double[] expected) {
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideTestCases")
    void testConvolution(ConvolutionTestCase testCase) {
        double[] result = Convolution.convolution(testCase.signalA, testCase.signalB);
        assertArrayEquals(testCase.expected, result, 1e-9, testCase.description);
    }

    private static Stream<ConvolutionTestCase> provideTestCases() {
        return Stream.of(new ConvolutionTestCase("Basic convolution", new double[] {1, 2, 3}, new double[] {4, 5, 6}, new double[] {4, 13, 28, 27, 18}), new ConvolutionTestCase("Convolution with zero elements", new double[] {0, 0, 0}, new double[] {1, 2, 3}, new double[] {0, 0, 0, 0, 0}),
            new ConvolutionTestCase("Convolution with single element", new double[] {2}, new double[] {3}, new double[] {6}), new ConvolutionTestCase("Convolution with different sizes", new double[] {1, 2}, new double[] {3, 4, 5}, new double[] {3, 10, 13, 10}),
            new ConvolutionTestCase("Convolution with negative values", new double[] {1, -2, 3}, new double[] {-1, 2, -3}, new double[] {-1, 4, -10, 12, -9}),
            new ConvolutionTestCase("Convolution with large numbers", new double[] {1e6, 2e6}, new double[] {3e6, 4e6}, new double[] {3e12, 1e13, 8e12}));
    }
}
