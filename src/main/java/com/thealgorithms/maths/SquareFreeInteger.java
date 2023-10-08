package com.thealgorithms.maths;
/*
 * Java program for Square free integer
 * This class has a function which checks
 * if an integer has repeated prime factors
 * and will return false if the number has repeated prime factors.
 * true otherwise
 * Wikipedia: https://en.wikipedia.org/wiki/Square-free_integer
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */

import java.util.HashSet;
import java.util.List;

public class SquareFreeInteger {
    /**
     * This method returns whether an integer is square free
     *
     * @param number Integer value which is to be checked
     * @return false when number has repeated prime factors
     *         true when number has non repeated prime factors
     * @throws IllegalArgumentException when number is negative or zero
     */
    public static boolean isSquareFreeInteger(int number) {

        if (number <= 0) {
            // throw exception when number is less than or is zero
            throw new IllegalArgumentException("Number must be greater than zero.");
        }

        // Store prime factors of number which is passed as argument
        // in a list
        List<Integer> primeFactorsList = PrimeFactorization.pfactors(number);

        // Create set from list of prime factors of integer number
        // if size of list and set is equal then the argument passed to this method is square free
        // if size of list and set is not equal then the argument passed to this method is not
        // square free
        return primeFactorsList.size() == new HashSet<>(primeFactorsList).size();
    }
}
