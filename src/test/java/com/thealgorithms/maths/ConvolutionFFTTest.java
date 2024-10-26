package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ConvolutionFFTTest {

    /**
     * Helper method to create a complex signal from an array of doubles.
     */
    private ArrayList<FFT.Complex> createComplexSignal(double[] values) {
        ArrayList<FFT.Complex> signal = new ArrayList<>();
        for (double value : values) {
            signal.add(new FFT.Complex(value, 0));
        }
        return signal;
    }

    /**
     * Helper method to compare two complex signals for equality within a small margin of error.
     */
    private void assertComplexArrayEquals(List<FFT.Complex> expected, List<FFT.Complex> result, double delta) {
        assertEquals(expected.size(), result.size(), "Signal lengths are not equal.");
        for (int i = 0; i < expected.size(); i++) {
            FFT.Complex expectedValue = expected.get(i);
            FFT.Complex resultValue = result.get(i);
            assertEquals(expectedValue.real(), resultValue.real(), delta, "Real part mismatch at index " + i);
            assertEquals(expectedValue.imaginary(), resultValue.imaginary(), delta, "Imaginary part mismatch at index " + i);
        }
    }

    @ParameterizedTest(name = "Test case {index}: {3}")
    @MethodSource("provideTestCases")
    public void testConvolutionFFT(double[] a, double[] b, double[] expectedOutput, String testDescription) {
        ArrayList<FFT.Complex> signalA = createComplexSignal(a);
        ArrayList<FFT.Complex> signalB = createComplexSignal(b);

        ArrayList<FFT.Complex> expected = createComplexSignal(expectedOutput);
        ArrayList<FFT.Complex> result = ConvolutionFFT.convolutionFFT(signalA, signalB);

        assertComplexArrayEquals(expected, result, 1e-9); // Allow small margin of error
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new double[] {1, 2, 3}, new double[] {4, 5, 6}, new double[] {4, 13, 28, 27, 18}, "Basic test"), Arguments.of(new double[] {0, 0, 0}, new double[] {1, 2, 3}, new double[] {0, 0, 0, 0, 0}, "Test with zero elements"),
            Arguments.of(new double[] {1, 2}, new double[] {3, 4, 5}, new double[] {3, 10, 13, 10}, "Test with different sizes"), Arguments.of(new double[] {5}, new double[] {2}, new double[] {10}, "Test with single element"),
            Arguments.of(new double[] {1, -2, 3}, new double[] {-1, 2, -3}, new double[] {-1, 4, -10, 12, -9}, "Test with negative values"));
    }
}
