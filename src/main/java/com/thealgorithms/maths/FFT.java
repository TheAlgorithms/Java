package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for calculating the Fast Fourier Transform (FFT) of a discrete signal
 * using the Cooley-Tukey algorithm.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 */
public class FFT {

    /**
     * This class represents a complex number and has methods for basic
     * operations.
     *
     * <p>
     * More info:
     * https://introcs.cs.princeton.edu/java/32class/Complex.java.html
     */
    static class Complex {

        private double real, img;

        /**
         * Default Constructor. Creates the complex number 0.
         */
        public Complex() {
            real = 0;
            img = 0;
        }

        /**
         * Constructor. Creates a complex number.
         *
         * @param r The real part of the number.
         * @param i The imaginary part of the number.
         */
        public Complex(double r, double i) {
            real = r;
            img = i;
        }

        /**
         * Returns the real part of the complex number.
         *
         * @return The real part of the complex number.
         */
        public double getReal() {
            return real;
        }

        /**
         * Returns the imaginary part of the complex number.
         *
         * @return The imaginary part of the complex number.
         */
        public double getImaginary() {
            return img;
        }

        /**
         * Adds this complex number to another.
         *
         * @param z The number to be added.
         * @return The sum.
         */
        public Complex add(Complex z) {
            Complex temp = new Complex();
            temp.real = this.real + z.real;
            temp.img = this.img + z.img;
            return temp;
        }

        /**
         * Subtracts a number from this complex number.
         *
         * @param z The number to be subtracted.
         * @return The difference.
         */
        public Complex subtract(Complex z) {
            Complex temp = new Complex();
            temp.real = this.real - z.real;
            temp.img = this.img - z.img;
            return temp;
        }

        /**
         * Multiplies this complex number by another.
         *
         * @param z The number to be multiplied.
         * @return The product.
         */
        public Complex multiply(Complex z) {
            Complex temp = new Complex();
            temp.real = this.real * z.real - this.img * z.img;
            temp.img = this.real * z.img + this.img * z.real;
            return temp;
        }

        /**
         * Multiplies this complex number by a scalar.
         *
         * @param n The real number to be multiplied.
         * @return The product.
         */
        public Complex multiply(double n) {
            Complex temp = new Complex();
            temp.real = this.real * n;
            temp.img = this.img * n;
            return temp;
        }

        /**
         * Finds the conjugate of this complex number.
         *
         * @return The conjugate.
         */
        public Complex conjugate() {
            Complex temp = new Complex();
            temp.real = this.real;
            temp.img = -this.img;
            return temp;
        }

        /**
         * Finds the magnitude of the complex number.
         *
         * @return The magnitude.
         */
        public double abs() {
            return Math.hypot(this.real, this.img);
        }

        /**
         * Divides this complex number by another.
         *
         * @param z The divisor.
         * @return The quotient.
         */
        public Complex divide(Complex z) {
            Complex temp = new Complex();
            double d = z.abs() * z.abs();
            d = (double) Math.round(d * 1000000000d) / 1000000000d;
            temp.real = (this.real * z.real + this.img * z.img) / (d);
            temp.img = (this.img * z.real - this.real * z.img) / (d);
            return temp;
        }

        /**
         * Divides this complex number by a scalar.
         *
         * @param n The divisor which is a real number.
         * @return The quotient.
         */
        public Complex divide(double n) {
            Complex temp = new Complex();
            temp.real = this.real / n;
            temp.img = this.img / n;
            return temp;
        }
    }

    /**
     * Iterative In-Place Radix-2 Cooley-Tukey Fast Fourier Transform Algorithm
     * with Bit-Reversal. The size of the input signal must be a power of 2. If
     * it isn't then it is padded with zeros and the output FFT will be bigger
     * than the input signal.
     *
     * <p>
     * More info:
     * https://www.algorithm-archive.org/contents/cooley_tukey/cooley_tukey.html
     * https://www.geeksforgeeks.org/iterative-fast-fourier-transformation-polynomial-multiplication/
     * https://en.wikipedia.org/wiki/Cooley%E2%80%93Tukey_FFT_algorithm
     * https://cp-algorithms.com/algebra/fft.html
     *  @param x The discrete signal which is then converted to the FFT or the
     * IFFT of signal x.
     * @param inverse True if you want to find the inverse FFT.
     * @return
     */
    public static ArrayList<Complex> fft(ArrayList<Complex> x, boolean inverse) {
        /* Pad the signal with zeros if necessary */
        paddingPowerOfTwo(x);
        int N = x.size();
        int log2N = findLog2(N);
        x = fftBitReversal(N, log2N, x);
        int direction = inverse ? -1 : 1;

        /* Main loop of the algorithm */
        for (int len = 2; len <= N; len *= 2) {
            double angle = -2 * Math.PI / len * direction;
            Complex wlen = new Complex(Math.cos(angle), Math.sin(angle));
            for (int i = 0; i < N; i += len) {
                Complex w = new Complex(1, 0);
                for (int j = 0; j < len / 2; j++) {
                    Complex u = x.get(i + j);
                    Complex v = w.multiply(x.get(i + j + len / 2));
                    x.set(i + j, u.add(v));
                    x.set(i + j + len / 2, u.subtract(v));
                    w = w.multiply(wlen);
                }
            }
        }
        x = inverseFFT(N, inverse, x);
        return x;
    }

    /* Find the log2(N) */
    public static int findLog2(int N) {
        int log2N = 0;
        while ((1 << log2N) < N) {
            log2N++;
        }
        return log2N;
    }

    /* Swap the values of the signal with bit-reversal method */
    public static ArrayList<Complex> fftBitReversal(int N, int log2N, ArrayList<Complex> x) {
        int reverse;
        for (int i = 0; i < N; i++) {
            reverse = reverseBits(i, log2N);
            if (i < reverse) {
                Collections.swap(x, i, reverse);
            }
        }
        return x;
    }

    /* Divide by N if we want the inverse FFT */
    public static ArrayList<Complex> inverseFFT(int N, boolean inverse, ArrayList<Complex> x) {
        if (inverse) {
            for (int i = 0; i < x.size(); i++) {
                Complex z = x.get(i);
                x.set(i, z.divide(N));
            }
        }
        return x;
    }

    /**
     * This function reverses the bits of a number. It is used in Cooley-Tukey
     * FFT algorithm.
     *
     * <p>
     * E.g. num = 13 = 00001101 in binary log2N = 8 Then reversed = 176 =
     * 10110000 in binary
     *
     * <p>
     * More info: https://cp-algorithms.com/algebra/fft.html
     * https://www.geeksforgeeks.org/write-an-efficient-c-program-to-reverse-bits-of-a-number/
     *
     * @param num The integer you want to reverse its bits.
     * @param log2N The number of bits you want to reverse.
     * @return The reversed number
     */
    private static int reverseBits(int num, int log2N) {
        int reversed = 0;
        for (int i = 0; i < log2N; i++) {
            if ((num & (1 << i)) != 0) {
                reversed |= 1 << (log2N - 1 - i);
            }
        }
        return reversed;
    }

    /**
     * This method pads an ArrayList with zeros in order to have a size equal to
     * the next power of two of the previous size.
     *
     * @param x The ArrayList to be padded.
     */
    private static void paddingPowerOfTwo(ArrayList<Complex> x) {
        int n = 1;
        int oldSize = x.size();
        while (n < oldSize) {
            n *= 2;
        }
        for (int i = 0; i < n - oldSize; i++) {
            x.add(new Complex());
        }
    }
}
