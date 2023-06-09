package com.thealgorithms.maths;
/*
 * Java program to find 'twin prime' of a prime number
 * Twin Prime: Twin prime of a number n is (n+2)
 * if and only if n & (n+2) are prime.
 * Wikipedia: https://en.wikipedia.org/wiki/Twin_prime
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */

public class TwinPrime {

    /**
     * This method returns twin prime of the integer value passed as argument
     *
     * @param input_number Integer value of which twin prime is to be found
     * @return (number + 2) if number and (number + 2) are prime, -1 otherwise
     */
    static int getTwinPrime(int inputNumber) {

        // if inputNumber and (inputNumber + 2) are both prime
        // then return (inputNumber + 2) as a result
        if (PrimeCheck.isPrime(inputNumber) && PrimeCheck.isPrime(inputNumber + 2)) {
            return inputNumber + 2;
        }
        // if any one from inputNumber and (inputNumber + 2) or if both of them are not prime
        // then return -1 as a result
        return -1;
    }
}
