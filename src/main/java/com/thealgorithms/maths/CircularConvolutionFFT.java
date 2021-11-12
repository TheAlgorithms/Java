package com.thealgorithms.maths;

import java.util.ArrayList;

/**
 * Class for circular convolution of two discrete signals using the convolution
 * theorem.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 */
public class CircularConvolutionFFT {

    /**
     * This method pads the signal with zeros until it reaches the new size.
     *
     * @param x The signal to be padded.
     * @param newSize The new size of the signal.
     */
    private static void padding(ArrayList<FFT.Complex> x, int newSize) {
        if (x.size() < newSize) {
            int diff = newSize - x.size();
            for (int i = 0; i < diff; i++) {
                x.add(new FFT.Complex());
            }
        }
    }

    /**
     * Discrete circular convolution function. It uses the convolution theorem
     * for discrete signals: convolved = IDFT(DFT(a)*DFT(b)). Then we use the
     * FFT algorithm for faster calculations of the two DFTs and the final IDFT.
     *
     * <p>
     * More info: https://en.wikipedia.org/wiki/Convolution_theorem
     *
     * @param a The first signal.
     * @param b The other signal.
     * @return The convolved signal.
     */
    public static ArrayList<FFT.Complex> fftCircularConvolution(
            ArrayList<FFT.Complex> a, ArrayList<FFT.Complex> b) {
        int convolvedSize
                = Math.max(
                        a.size(), b.size()); // The two signals must have the same size equal to the bigger one
        padding(a, convolvedSize); // Zero padding the smaller signal
        padding(b, convolvedSize);

        /* Find the FFTs of both signal. Here we use the Bluestein algorithm because we want the FFT to have the same length with the signal and not bigger */
        FFTBluestein.fftBluestein(a, false);
        FFTBluestein.fftBluestein(b, false);
        ArrayList<FFT.Complex> convolved = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            convolved.add(a.get(i).multiply(b.get(i))); // FFT(a)*FFT(b)
        }
        FFTBluestein.fftBluestein(convolved, true); // IFFT

        return convolved;
    }
}
