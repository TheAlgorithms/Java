package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for calculating the Fast Fourier Transform (FFT) of a discrete signal
 * using the Bluestein's algorithm.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 */
public final class FFTBluestein {
    private FFTBluestein() {
    }

    /**
     * Bluestein's FFT Algorithm.
     *
     * <p>
     * More info:
     * https://en.wikipedia.org/wiki/Chirp_Z-transform#Bluestein.27s_algorithm
     * http://tka4.org/materials/lib/Articles-Books/Numerical%20Algorithms/Hartley_Trasform/Bluestein%27s%20FFT%20algorithm%20-%20Wikipedia,%20the%20free%20encyclopedia.htm
     *
     * @param x The discrete signal which is then converted to the FFT or the
     * IFFT of signal x.
     * @param inverse True if you want to find the inverse FFT.
     */
    public static void fftBluestein(List<FFT.Complex> x, boolean inverse) {
        int n = x.size();
        int bnSize = 2 * n - 1;
        int direction = inverse ? -1 : 1;
        ArrayList<FFT.Complex> an = new ArrayList<>();
        ArrayList<FFT.Complex> bn = new ArrayList<>();

        /* Initialization of the b(n) sequence (see Wikipedia's article above for the symbols
         * used)*/
        for (int i = 0; i < bnSize; i++) {
            bn.add(new FFT.Complex());
        }

        for (int i = 0; i < n; i++) {
            double angle = (i - n + 1) * (i - n + 1) * Math.PI / n * direction;
            bn.set(i, new FFT.Complex(Math.cos(angle), Math.sin(angle)));
            bn.set(bnSize - i - 1, new FFT.Complex(Math.cos(angle), Math.sin(angle)));
        }

        /* Initialization of the a(n) sequence */
        for (int i = 0; i < n; i++) {
            double angle = -i * i * Math.PI / n * direction;
            an.add(x.get(i).multiply(new FFT.Complex(Math.cos(angle), Math.sin(angle))));
        }

        ArrayList<FFT.Complex> convolution = ConvolutionFFT.convolutionFFT(an, bn);

        /* The final multiplication of the convolution with the b*(k) factor  */
        for (int i = 0; i < n; i++) {
            double angle = -1 * i * i * Math.PI / n * direction;
            FFT.Complex bk = new FFT.Complex(Math.cos(angle), Math.sin(angle));
            x.set(i, bk.multiply(convolution.get(i + n - 1)));
        }

        /* Divide by n if we want the inverse FFT */
        if (inverse) {
            for (int i = 0; i < n; i++) {
                FFT.Complex z = x.get(i);
                x.set(i, z.divide(n));
            }
        }
    }
}
