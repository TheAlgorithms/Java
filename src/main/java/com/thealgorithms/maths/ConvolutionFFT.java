package com.thealgorithms.maths;

import java.util.ArrayList;

/**
 * Class for linear convolution of two discrete signals using the convolution
 * theorem.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 */
public class ConvolutionFFT {

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
     * Discrete linear convolution function. It uses the convolution theorem for
     * discrete signals convolved: = IDFT(DFT(a)*DFT(b)). This is true for
     * circular convolution. In order to get the linear convolution of the two
     * signals we first pad the two signals to have the same size equal to the
     * convolved signal (a.size() + b.size() - 1). Then we use the FFT algorithm
     * for faster calculations of the two DFTs and the final IDFT.
     *
     * <p>
     * More info: https://en.wikipedia.org/wiki/Convolution_theorem
     * https://ccrma.stanford.edu/~jos/ReviewFourier/FFT_Convolution.html
     *
     * @param a The first signal.
     * @param b The other signal.
     * @return The convolved signal.
     */
    public static ArrayList<FFT.Complex> convolutionFFT(
        ArrayList<FFT.Complex> a,
        ArrayList<FFT.Complex> b
    ) {
        int convolvedSize = a.size() + b.size() - 1; // The size of the convolved signal
        padding(a, convolvedSize); // Zero padding both signals
        padding(b, convolvedSize);

        /* Find the FFTs of both signals (Note that the size of the FFTs will be bigger than the convolvedSize because of the extra zero padding in FFT algorithm) */
        FFT.fft(a, false);
        FFT.fft(b, false);
        ArrayList<FFT.Complex> convolved = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            convolved.add(a.get(i).multiply(b.get(i))); // FFT(a)*FFT(b)
        }
        FFT.fft(convolved, true); // IFFT
        convolved.subList(convolvedSize, convolved.size()).clear(); // Remove the remaining zeros after the convolvedSize. These extra zeros came from
        // paddingPowerOfTwo() method inside the fft() method.

        return convolved;
    }
}
