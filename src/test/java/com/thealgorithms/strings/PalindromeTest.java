package com.thealgorithms.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeTest {

    @Test
    public void palindrome() {
        String input1 = "kayak";
        String input2 = "kayaks";
        Assertions.assertTrue(Palindrome.isPalindromeStringBuilder(input1));
        Assertions.assertFalse(Palindrome.isPalindromeStringBuilder(input2));
        Assertions.assertTrue(Palindrome.isPalindromeRecursion(input1));
        Assertions.assertFalse(Palindrome.isPalindromeRecursion(input2));
        Assertions.assertTrue(Palindrome.isPalindromeTwoPointer(input1));
        Assertions.assertFalse(Palindrome.isPalindromeTwoPointer(input2));
    }
}
