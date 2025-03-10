package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Class for calculating the Fast Fourier Transform (FFT) of a discrete signal
 * using the Cooley-Tukey algorithm.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 */
public final class FFT {
    private FFT() {
    }

    /**
     * This class represents a complex number and has methods for basic
     * operations.
     *
     * <p>
     * More info:
     * https://introcs.cs.princeton.edu/java/32class/Complex.java.html
     */
    static class Complex {

        private double real;
        private double img;

        /**
         * Default Constructor. Creates the complex number 0.
         */
        Complex() {
            real = 0;
            img = 0;
        }

        /**
         * Constructor. Creates a complex number.
         *
         * @param r The real part of the number.
         * @param i The imaginary part of the number.
         */
        Complex(double r, double i) {
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

        public double real() {
            return real;
        }

        public double imaginary() {
            return img;
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
        int n = x.size();
        int log2n = findLog2(n);
        x = fftBitReversal(n, log2n, x);
        int direction = inverse ? -1 : 1;

        /* Main loop of the algorithm */
        for (int len = 2; len <= n; len *= 2) {
            double angle = -2 * Math.PI / len * direction;
            Complex wlen = new Complex(Math.cos(angle), Math.sin(angle));
            for (int i = 0; i < n; i += len) {
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
        x = inverseFFT(n, inverse, x);
        return x;
    }

    /* Find the log2(n) */
    public static int findLog2(int n) {
        int log2n = 0;
        while ((1 << log2n) < n) {
            log2n++;
        }
        return log2n;
    }

    /* Swap the values of the signal with bit-reversal method */
    public static ArrayList<Complex> fftBitReversal(int n, int log2n, ArrayList<Complex> x) {
        int reverse;
        for (int i = 0; i < n; i++) {
            reverse = reverseBits(i, log2n);
            if (i < reverse) {
                Collections.swap(x, i, reverse);
            }
        }
        return x;
    }

    /* Divide by n if we want the inverse FFT */
    public static ArrayList<Complex> inverseFFT(int n, boolean inverse, ArrayList<Complex> x) {
        if (inverse) {
            for (int i = 0; i < x.size(); i++) {
                Complex z = x.get(i);
                x.set(i, z.divide(n));
            }
        }
        return x;
    }

    /**
     * This function reverses the bits of a number. It is used in Cooley-Tukey
     * FFT algorithm.
     *
     * <p>
     * E.g. num = 13 = 00001101 in binary log2n = 8 Then reversed = 176 =
     * 10110000 in binary
     *
     * <p>
     * More info: https://cp-algorithms.com/algebra/fft.html
     * https://www.geeksforgeeks.org/write-an-efficient-c-program-to-reverse-bits-of-a-number/
     *
     * @param num The integer you want to reverse its bits.
     * @param log2n The number of bits you want to reverse.
     * @return The reversed number
     */
    private static int reverseBits(int num, int log2n) {
        int reversed = 0;
        for (int i = 0; i < log2n; i++) {
            if ((num & (1 << i)) != 0) {
                reversed |= 1 << (log2n - 1 - i);
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
    private static void paddingPowerOfTwo(Collection<Complex> x) {
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
