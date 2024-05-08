package com.thealgorithms.maths;

/**
 * Class for linear auto-correlation of a discrete signal
 *
 * @author Athina-Frederiki Swinkels
 * @version 2.0
 */

public final class AutoCorrelation {
    private AutoCorrelation() {
    }

    /**
     * Discrete linear auto-correlation function.
     * Input and output signals have starting index 0.
     *
     * @param x The discrete signal
     * @return The result of the auto-correlation of signals x. The result is also a signal.
     */
    public static double[] autoCorrelation(double[] x) {

        /*
        To find the auto-correlation of a discrete signal x, we perform cross-correlation between x signal and itself.
        Here's an example:
        x=[1,2,1,1]
        y=[1,2,1,1]

        i=0:      [1,2,1,1]
            [1,2,1,1]               result[0]=1*1=1

        i=1:       [1,2,1,1]
               [1,2,1,1]            result[1]=1*1+2*1=3

        i=2:       [1,2,1,1]
                 [1,2,1,1]          result[2]=1*2+2*1+1*1=5

        i=3:       [1,2,1,1]
                   [1,2,1,1]        result[3]=1*1+2*2+1*1+1*1=7

        i=4:       [1,2,1,1]
                     [1,2,1,1]      result[4]=2*1+1*2+1*1=5

        i=5:       [1,2,1,1]
                       [1,2,1,1]    result[5]=1*1+1*2=3

        i=1:       [1,2,1,1]
                         [1,2,1,1]  result[6]=1*1=1

        result=[1,3,5,7,5,3,1]


         */

        return CrossCorrelation.crossCorrelation(x, x);
    }
}
