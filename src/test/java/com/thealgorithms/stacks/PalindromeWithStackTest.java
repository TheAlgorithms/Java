package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PalindromeWithStackTest {

    private PalindromeWithStack palindromeChecker;

    @BeforeEach
    public void setUp() {
        palindromeChecker = new PalindromeWithStack();
    }

    @Test
    public void testValidOne() {
        String testString = "Racecar";
        assertTrue(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testInvalidOne() {
        String testString = "James";
        assertFalse(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testValidTwo() {
        String testString = "madam";
        assertTrue(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testInvalidTwo() {
        String testString = "pantry";
        assertFalse(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testValidThree() {
        String testString = "RaDar";
        assertTrue(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testInvalidThree() {
        String testString = "Win";
        assertFalse(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testBlankString() {
        String testString = "";
        assertTrue(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testStringWithNumbers() {
        String testString = "12321";
        assertTrue(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testStringWithNumbersTwo() {
        String testString = "12325";
        assertFalse(palindromeChecker.checkPalindrome(testString));
    }

    @Test
    public void testStringWithNumbersAndLetters() {
        String testString = "po454op";
        assertTrue(palindromeChecker.checkPalindrome(testString));
    }
}
