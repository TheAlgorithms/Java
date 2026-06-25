package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to check and generate palindromic prime numbers.
 * A palindromic prime is a prime number that is also a palindromic number.
 *
 * @see com.thealgorithms.strings.Palindrome
 * @see com.thealgorithms.stacks.PalindromeWithStack
 * @see com.thealgorithms.bitmanipulation.BinaryPalindromeCheck
 * @see com.thealgorithms.maths.LowestBasePalindrome
 * @see com.thealgorithms.datastructures.lists.PalindromeSinglyLinkedList
 * @see com.thealgorithms.maths.PalindromeNumber
 */
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

    public static int reverse(int n) {
        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10 + (n % 10);
            n /= 10;
        }
        return reverse;
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
            if (num == reverse(num) && prime(num)) {
                palindromicPrimes.add(num);
                count++;
            }
            num += 2; // Skip even numbers
        }
        return palindromicPrimes;
    }
}
