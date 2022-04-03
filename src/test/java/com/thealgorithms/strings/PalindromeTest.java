package com.thealgorithms.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeTest {
    @Test
    public void palindrome() {
        String input1 = "kayak";
        String input2 = "kayaks";
        Assertions.assertTrue(Palindrome.isPalindrome(input1));
        Assertions.assertFalse(Palindrome.isPalindrome(input2));
        Assertions.assertTrue(Palindrome.isPalindromeRecursion(input1));
        Assertions.assertFalse(Palindrome.isPalindromeRecursion(input2));
        Assertions.assertTrue(Palindrome.isPalindrome1(input1));
        Assertions.assertFalse(Palindrome.isPalindrome1(input2));
    }
}
