package com.thealgorithms.maths.Prime;

import java.util.ArrayList;
import java.util.List;

import com.thealgorithms.maths.ReverseNumber;

public final class PalindromePrime {
    private PalindromePrime() {
    }

    public static boolean prime(int num) {
        if (num < 2) {
            return false; // Handle edge case for numbers < 2
        }
        if (num == 2) {
            return true; // 2 is prime
        }
        if (num % 2 == 0) {
            return false; // Even numbers > 2 are not prime
        }

        for (int divisor = 3; divisor <= Math.sqrt(num); divisor += 2) {
            if (num % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> generatePalindromePrimes(int n) {
        List<Integer> palindromicPrimes = new ArrayList<>();
        if (n <= 0) {
            return palindromicPrimes; // Handle case for 0 or negative input
        }

        palindromicPrimes.add(2); // 2 is the first palindromic prime
        int count = 1;
        int num = 3;

        while (count < n) {
            if (num == ReverseNumber.reverseNumber(num) && prime(num)) {
                palindromicPrimes.add(num);
                count++;
            }
            num += 2; // Skip even numbers
        }
        return palindromicPrimes;
    }
}
