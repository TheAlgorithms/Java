package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

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
    private void assertComplexArrayEquals(ArrayList<FFT.Complex> expected, ArrayList<FFT.Complex> result, double delta) {
        assertEquals(expected.size(), result.size(), "Signal lengths are not equal.");
        for (int i = 0; i < expected.size(); i++) {
            FFT.Complex expectedValue = expected.get(i);
            FFT.Complex resultValue = result.get(i);
            assertEquals(expectedValue.real(), resultValue.real(), delta, "Real part mismatch at index " + i);
            assertEquals(expectedValue.imaginary(), resultValue.imaginary(), delta, "Imaginary part mismatch at index " + i);
        }
    }

    @Test
    public void testConvolutionFFTBasic() {
        double[] a = {1, 2, 3};
        double[] b = {4, 5, 6};
        ArrayList<FFT.Complex> signalA = createComplexSignal(a);
        ArrayList<FFT.Complex> signalB = createComplexSignal(b);

        ArrayList<FFT.Complex> expected = createComplexSignal(new double[] {4, 13, 28, 27, 18}); // Expected output
        ArrayList<FFT.Complex> result = ConvolutionFFT.convolutionFFT(signalA, signalB);

        assertComplexArrayEquals(expected, result, 1e-9); // Allow small margin of error
    }

    @Test
    public void testConvolutionFFTWithZeroElements() {
        double[] a = {0, 0, 0};
        double[] b = {1, 2, 3};
        ArrayList<FFT.Complex> signalA = createComplexSignal(a);
        ArrayList<FFT.Complex> signalB = createComplexSignal(b);

        ArrayList<FFT.Complex> expected = createComplexSignal(new double[] {0, 0, 0, 0, 0}); // All values should be zero
        ArrayList<FFT.Complex> result = ConvolutionFFT.convolutionFFT(signalA, signalB);

        assertComplexArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testConvolutionFFTWithDifferentSizes() {
        double[] a = {1, 2};
        double[] b = {3, 4, 5};
        ArrayList<FFT.Complex> signalA = createComplexSignal(a);
        ArrayList<FFT.Complex> signalB = createComplexSignal(b);

        ArrayList<FFT.Complex> expected = createComplexSignal(new double[] {3, 10, 13, 10});
        ArrayList<FFT.Complex> result = ConvolutionFFT.convolutionFFT(signalA, signalB);

        assertComplexArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testConvolutionFFTWithSingleElement() {
        double[] a = {5};
        double[] b = {2};
        ArrayList<FFT.Complex> signalA = createComplexSignal(a);
        ArrayList<FFT.Complex> signalB = createComplexSignal(b);

        ArrayList<FFT.Complex> expected = createComplexSignal(new double[] {10});
        ArrayList<FFT.Complex> result = ConvolutionFFT.convolutionFFT(signalA, signalB);

        assertComplexArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testConvolutionFFTWithNegativeValues() {
        double[] a = {1, -2, 3};
        double[] b = {-1, 2, -3};
        ArrayList<FFT.Complex> signalA = createComplexSignal(a);
        ArrayList<FFT.Complex> signalB = createComplexSignal(b);

        ArrayList<FFT.Complex> expected = createComplexSignal(new double[] {-1, 4, -10, 12, -9});
        ArrayList<FFT.Complex> result = ConvolutionFFT.convolutionFFT(signalA, signalB);

        assertComplexArrayEquals(expected, result, 1e-9);
    }
}
