package com.thealgorithms.maths;

/**
 * A class to check if a given number is a palindrome.
 * A palindromic number is a number that remains the same when its digits are reversed.
 *
 * @see com.thealgorithms.strings.Palindrome
 * @see com.thealgorithms.stacks.PalindromeWithStack
 * @see com.thealgorithms.bitmanipulation.BinaryPalindromeCheck
 * @see com.thealgorithms.maths.LowestBasePalindrome
 * @see com.thealgorithms.datastructures.lists.PalindromeSinglyLinkedList
 * @see com.thealgorithms.maths.PalindromePrime
 */
public final class PalindromeNumber {
    private PalindromeNumber() {
    }
    /**
     * Check if {@code n} is palindrome number or not
     *
     * @param number the number
     * @return {@code true} if {@code n} is palindrome number, otherwise
     * {@code false}
     */
    public static boolean isPalindrome(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Input parameter must not be negative!");
        }
        int numberCopy = number;
        int reverseNumber = 0;
        while (numberCopy != 0) {
            int remainder = numberCopy % 10;
            reverseNumber = reverseNumber * 10 + remainder;
            numberCopy /= 10;
        }
        return number == reverseNumber;
    }
}
