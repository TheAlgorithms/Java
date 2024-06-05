package com.thealgorithms.maths;

/**
 * Class for linear cross-correlation of two discrete signals
 *
 * @author Athina-Frederiki Swinkels
 * @version 1.0
 */

public final class CrossCorrelation {
    private CrossCorrelation() {
    }

    /**
     * Discrete linear cross-correlation function.
     * Input and output signals have starting index 0.
     *
     * @param x The first discrete signal
     * @param y The second discrete signal
     * @return The result of the cross-correlation of signals x,y. The result is also a signal.
     */
    public static double[] crossCorrelation(double[] x, double[] y) {
        // The result signal's length is the sum of the input signals' lengths minus 1
        double[] result = new double[x.length + y.length - 1];
        int n = result.length;

        /*
        To find the cross-correlation between 2 discrete signals x & y, we start by "placing" the second signal
        y under the first signal x, shifted to the left so that the last value of y meets the first value of x
        and for every new position (i++) of the result signal, we shift y signal one position to the right, until
        the first y-value meets the last x-value. The result-value for each position is the sum of all x*y meeting
        values.
        Here's an example:
        x=[1,2,1,1]
        y=[1,1,2,1]

        i=0:      [1,2,1,1]
            [1,1,2,1]               result[0]=1*1=1

        i=1:       [1,2,1,1]
               [1,1,2,1]            result[1]=1*2+2*1=4

        i=2:       [1,2,1,1]
                 [1,1,2,1]          result[2]=1*1+2*2+1*1=6

        i=3:       [1,2,1,1]
                   [1,1,2,1]        result[3]=1*1+2*1+1*2+1*1=6

        i=4:       [1,2,1,1]
                     [1,1,2,1]      result[4]=2*1+1*1+1*2=5

        i=5:       [1,2,1,1]
                       [1,1,2,1]    result[5]=1*1+1*1=2

        i=1:       [1,2,1,1]
                         [1,1,2,1]  result[6]=1*1=1

        result=[1,4,6,6,5,2,1]




        To find the result[i] value for each i:0->n-1, the positions of x-signal in which the 2 signals meet
        are calculated: kMin<=k<=kMax.
        The variable 'yStart' indicates the starting index of y in each sum calculation.
        The variable 'count' increases the index of y-signal by 1, to move to the next value.
         */
        int yStart = y.length;
        for (int i = 0; i < n; i++) {
            result[i] = 0;

            int kMin = Math.max(i - (y.length - 1), 0);
            int kMax = Math.min(i, x.length - 1);

            if (i < y.length) {
                yStart--;
            }

            int count = 0;
            for (int k = kMin; k <= kMax; k++) {
                result[i] += x[k] * y[yStart + count];
                count++;
            }
        }

        // The calculated cross-correlation of x & y signals is returned here.
        return result;
    }
}
