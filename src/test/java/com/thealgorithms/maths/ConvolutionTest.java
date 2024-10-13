package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ConvolutionTest {

    @Test
    public void testConvolutionBasic() {
        double[] signalA = {1, 2, 3};
        double[] signalB = {4, 5, 6};
        double[] expected = {4, 13, 28, 27, 18}; // Expected output

        double[] result = Convolution.convolution(signalA, signalB);

        assertArrayEquals(expected, result, 1e-9); // Allowing for floating-point precision
    }

    @Test
    public void testConvolutionWithZeroElements() {
        double[] signalA = {0, 0, 0};
        double[] signalB = {1, 2, 3};
        double[] expected = {0, 0, 0, 0, 0}; // All values should remain zero

        double[] result = Convolution.convolution(signalA, signalB);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testConvolutionSingleElement() {
        double[] signalA = {2};
        double[] signalB = {3};
        double[] expected = {6}; // 2 * 3 = 6

        double[] result = Convolution.convolution(signalA, signalB);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testConvolutionWithDifferentSizes() {
        double[] signalA = {1, 2};
        double[] signalB = {3, 4, 5};
        double[] expected = {3, 10, 13, 10}; // Expected output

        double[] result = Convolution.convolution(signalA, signalB);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testConvolutionWithNegativeValues() {
        double[] signalA = {1, -2, 3};
        double[] signalB = {-1, 2, -3};
        double[] expected = {-1, 4, -10, 12, -9}; // Expected output

        double[] result = Convolution.convolution(signalA, signalB);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testConvolutionWithLargeNumbers() {
        double[] signalA = {1e6, 2e6};
        double[] signalB = {3e6, 4e6};
        double[] expected = {3e12, 1e13, 8e12}; // Expected output with large numbers

        double[] result = Convolution.convolution(signalA, signalB);

        assertArrayEquals(expected, result, 1e-9);
    }
}
