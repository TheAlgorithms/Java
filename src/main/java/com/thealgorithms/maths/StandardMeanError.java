package com.thealgorithms.maths;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Standard_error
 */

public class StandardMeanError {

    /**
     * Calculate standard mean error = standard deviation / root(num_of_observations)
     * @param values numbers to find Standard Mean Error of
     * @return Standard Mean Error of given {@code values}
     */

    public static double standard_mean_error(int[] values){
        //function for calculating the standard mean error of integer array
        double stdDev = calculateStandardDeviation(values);
        double sem = stdDev / Math.sqrt(values.length);
        return sem;// here sem stands for the standard mean error 
    }

    public static double calculateMean(int[] data) {
        //function calculates the arithmetic mean of an integer array
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    public static double calculateStandardDeviation(int[] data) {
        //function to calculate the standard deviation about the mean of an integer array
        double mean = calculateMean(data);
        double sumOfSquaredDifferences = 0;
        for (double value : data) {
            double diff = value - mean;
            sumOfSquaredDifferences += diff * diff;
        }
        return Math.sqrt(sumOfSquaredDifferences / (data.length - 1));
    }
    


    // using polymorphism to calculate the same for a double array
    public static double standard_mean_error(double[] values){

        double stdDev = calculateStandardDeviation(values);
        double sem = stdDev / Math.sqrt(values.length);
        return sem;
     }

    public static double calculateMean(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    public static double calculateStandardDeviation(double[] data) {
        double mean = calculateMean(data);
        double sumOfSquaredDifferences = 0;
        for (double value : data) {
            double diff = value - mean;
            sumOfSquaredDifferences += diff * diff;
        }
        return Math.sqrt(sumOfSquaredDifferences / (data.length - 1));
    }
}