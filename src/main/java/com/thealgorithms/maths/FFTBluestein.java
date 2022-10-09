package com.thealgorithms.maths;

import java.util.ArrayList;

/**
 * Class for calculating the Fast Fourier Transform (FFT) of a discrete signal
 * using the Bluestein's algorithm.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 */
public class FFTBluestein {

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
    public static void fftBluestein(ArrayList<FFT.Complex> x, boolean inverse) {
        int N = x.size();
        int bnSize = 2 * N - 1;
        int direction = inverse ? -1 : 1;
        ArrayList<FFT.Complex> an = new ArrayList<>();
        ArrayList<FFT.Complex> bn = new ArrayList<>();

        /* Initialization of the b(n) sequence (see Wikipedia's article above for the symbols used)*/
        for (int i = 0; i < bnSize; i++) {
            bn.add(new FFT.Complex());
        }

        for (int i = 0; i < N; i++) {
            double angle = (i - N + 1) * (i - N + 1) * Math.PI / N * direction;
            bn.set(i, new FFT.Complex(Math.cos(angle), Math.sin(angle)));
            bn.set(
                bnSize - i - 1,
                new FFT.Complex(Math.cos(angle), Math.sin(angle))
            );
        }

        /* Initialization of the a(n) sequence */
        for (int i = 0; i < N; i++) {
            double angle = -i * i * Math.PI / N * direction;
            an.add(
                x
                    .get(i)
                    .multiply(new FFT.Complex(Math.cos(angle), Math.sin(angle)))
            );
        }

        ArrayList<FFT.Complex> convolution = ConvolutionFFT.convolutionFFT(
            an,
            bn
        );

        /* The final multiplication of the convolution with the b*(k) factor  */
        for (int i = 0; i < N; i++) {
            double angle = -1 * i * i * Math.PI / N * direction;
            FFT.Complex bk = new FFT.Complex(Math.cos(angle), Math.sin(angle));
            x.set(i, bk.multiply(convolution.get(i + N - 1)));
        }

        /* Divide by N if we want the inverse FFT */
        if (inverse) {
            for (int i = 0; i < N; i++) {
                FFT.Complex z = x.get(i);
                x.set(i, z.divide(N));
            }
        }
    }
}
