package com.thealgorithms.maths;

/*
 * Java program for Binomial Cofficients
 * Binomial Cofficients: A binomial cofficient C(n,k) gives number ways
 * in which k objects can be chosen from n objects.
 * Wikipedia: https://en.wikipedia.org/wiki/Binomial_coefficient
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */

public class BinomialCoefficient {

    /**
     * This method returns the number of ways in which k objects can be chosen from n objects
     *
     * @param totalObjects    Total number of objects
     * @param numberOfObjects Number of objects to be chosen from total_objects
     * @return number of ways in which no_of_objects objects can be chosen from total_objects objects
     */

    public static int binomialCoefficient(
        int totalObjects,
        int numberOfObjects
    ) {
        // Base Case
        if (numberOfObjects > totalObjects) {
            return 0;
        }

        // Base Case
        if (numberOfObjects == 0 || numberOfObjects == totalObjects) {
            return 1;
        }

        // Recursive Call
        return (
            binomialCoefficient(totalObjects - 1, numberOfObjects - 1) +
            binomialCoefficient(totalObjects - 1, numberOfObjects)
        );
    }
}
