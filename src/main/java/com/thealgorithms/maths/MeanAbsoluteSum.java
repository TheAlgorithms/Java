package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Average_absolute_deviation
 */

public class MeanAbsoluteDeviation {
    /**
     * Calculate the sum of a list of numbers
     * @param data array to store the list numbers
     * @return sum of the given list of numbers
     */
    public static double sum(double[] data){
        double sum = 0;
        for(double dataPoint: data)
            sum += dataPoint;
        return sum;
    }

    /**
     * Calculate mean of a list of numbers
     * @param data array to store the list numbers
     * @return mean of the given list of numbers
     */
    public static double mean(double[] data){
        return sum(data) / data.length;
    }

    /**
     * Calculate the mean absolute deviation of a list of numbers
     * @param data array to store the list numbers
     * @return mean absolute deviation of the given list of numbers
     */
    public static double meanAbsoluteDeviation(double[] data){
        double absoluteSum = 0;
        double mean = mean(data);
        for(double dataPoint : data){
            absoluteSum += Math.abs(dataPoint - mean);
        }
        return absoluteSum / data.length;
    }
}
