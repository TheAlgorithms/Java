package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 01/07/2023
 */
public class PalindromeNumberTest {
    @Test
    public void testNumbersArePalindromes() {
        Assertions.assertTrue(PalindromeNumber.isPalindrome(0));
        Assertions.assertTrue(PalindromeNumber.isPalindrome(1));
        Assertions.assertTrue(PalindromeNumber.isPalindrome(2332));
        Assertions.assertTrue(PalindromeNumber.isPalindrome(12321));
    }

    @Test
    public void testNumbersAreNotPalindromes() {
        Assertions.assertFalse(PalindromeNumber.isPalindrome(12));
        Assertions.assertFalse(PalindromeNumber.isPalindrome(990));
        Assertions.assertFalse(PalindromeNumber.isPalindrome(1234));
    }

    @Test
    public void testIfNegativeInputThenExceptionExpected() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> PalindromeNumber.isPalindrome(-1));
        Assertions.assertEquals(exception.getMessage(), "Input parameter must not be negative!");
    }
}
