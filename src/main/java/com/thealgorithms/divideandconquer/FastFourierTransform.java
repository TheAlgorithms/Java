package com.thealgorithms.divideandconquer;

// Java Program to Implement Fat Fourier Transform

/*
* This algorithm works by recursively dividing the sequence to be transformed into smaller and smaller subsequences,
* computing the FFT of each subsequence, and then combining the results to compute the FFT of the original sequence.
* author : sheetal neeraj (https://github.com/sheetalneeraj)
 * Reference:
 * https://en.wikipedia.org/wiki/Fast_Fourier_transform
 */
public class FastFourierTransform {

    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        // Base case
        if (n == 1) {
            return x;
        }

        // Recursively compute the FFT of the even and odd terms
        Complex[] even = new Complex[n / 2];
        Complex[] odd = new Complex[n / 2];
        for (int i = 0; i < n / 2; i++) {
            even[i] = x[2 * i];
            odd[i] = x[2 * i + 1];
        }
        Complex[] evenFFT = fft(even);
        Complex[] oddFFT = fft(odd);

        // Combine the results to compute the FFT of the original sequence
        Complex[] result = new Complex[n];
        for (int k = 0; k < n / 2; k++) {
            result[k] = evenFFT[k].add(oddFFT[k].multiply(Complex.polar(1.0, -2.0 * Math.PI * k / n)));
            result[k + n / 2] = evenFFT[k].subtract(oddFFT[k].multiply(Complex.polar(1.0, -2.0 * Math.PI * k / n)));
        }
        return result;
    }
}
