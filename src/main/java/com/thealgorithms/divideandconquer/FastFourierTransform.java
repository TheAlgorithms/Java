package com.thealgorithms.divideandconquer;

// Java Program to Implement Fast Fourier Transform

//This algorithm works by recursively dividing the sequence to be transformed into smaller and smaller subsequences,computing the FFT of each subsequence, 
//and then combining the results to compute the FFT of the original sequence.
//author : sheetal neeraj (https://github.com/sheetalneeraj)
// Reference: https://en.wikipedia.org/wiki/Fast_Fourier_transform


public static double[][] FastFourierTransform(double[][] x) {
    int n = x.length;

    // Base case
    if (n == 1) {
        return x;
    }

    // Recursively compute the FFT of the even and odd terms
    double[][] even = new double[n / 2][2];
    double[][] odd = new double[n / 2][2];
    for (int i = 0; i < n / 2; i++) {
        even[i][0] = x[2 * i][0];
        even[i][1] = x[2 * i][1];
        odd[i][0] = x[2 * i + 1][0];
        odd[i][1] = x[2 * i + 1][1];
    }
    double[][] evenFFT = FastFourierTransform(even);
    double[][] oddFFT = FastFourierTransform(odd);

    // Combine the results to compute the FFT of the original sequence
    double[][] result = new double[n][2];
    for (int k = 0; k < n / 2; k++) {
        double angle = -2.0 * Math.PI * k / n;
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        result[k][0] = evenFFT[k][0] + oddFFT[k][0] * cos - oddFFT[k][1] * sin;
        result[k][1] = evenFFT[k][1] + oddFFT[k][0] * sin + oddFFT[k][1] * cos;
        result[k + n / 2][0] = evenFFT[k][0] - oddFFT[k][0] * cos + oddFFT[k][1] * sin;
        result[k + n / 2][1] = evenFFT[k][1] - oddFFT[k][0] * sin - oddFFT[k][1] * cos;
    }
    return result;
}
