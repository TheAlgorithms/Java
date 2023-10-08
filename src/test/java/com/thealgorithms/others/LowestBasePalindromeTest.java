package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class LowestBasePalindromeTest {
    @Test
    public void testIsPalindromicPositive() {
        assertTrue(LowestBasePalindrome.isPalindromic(new ArrayList<Integer>()));
        assertTrue(LowestBasePalindrome.isPalindromic(new ArrayList<Integer>(Arrays.asList(1))));
        assertTrue(LowestBasePalindrome.isPalindromic(new ArrayList<Integer>(Arrays.asList(1, 1))));
        assertTrue(LowestBasePalindrome.isPalindromic(new ArrayList<Integer>(Arrays.asList(1, 2, 1))));
        assertTrue(LowestBasePalindrome.isPalindromic(new ArrayList<Integer>(Arrays.asList(1, 2, 2, 1))));
    }

    @Test
    public void testIsPalindromicNegative() {
        assertFalse(LowestBasePalindrome.isPalindromic(new ArrayList<Integer>(Arrays.asList(1, 2))));
        assertFalse(LowestBasePalindrome.isPalindromic(new ArrayList<Integer>(Arrays.asList(1, 2, 1, 1))));
    }

    @Test
    public void testIsPalindromicInBasePositive() {
        assertTrue(LowestBasePalindrome.isPalindromicInBase(101, 10));
        assertTrue(LowestBasePalindrome.isPalindromicInBase(1, 190));
        assertTrue(LowestBasePalindrome.isPalindromicInBase(0, 11));
        assertTrue(LowestBasePalindrome.isPalindromicInBase(10101, 10));
        assertTrue(LowestBasePalindrome.isPalindromicInBase(23, 22));
    }

    @Test
    public void testIsPalindromicInBaseNegative() {
        assertFalse(LowestBasePalindrome.isPalindromicInBase(1010, 10));
        assertFalse(LowestBasePalindrome.isPalindromicInBase(123, 10));
    }

    @Test
    public void testIsPalindromicInBaseThrowsExceptionForNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> LowestBasePalindrome.isPalindromicInBase(-1, 5));
    }

    @Test
    public void testIsPalindromicInBaseThrowsExceptionForWrongBases() {
        assertThrows(IllegalArgumentException.class, () -> LowestBasePalindrome.isPalindromicInBase(10, 1));
    }

    @Test
    public void testLowestBasePalindrome() {
        HashMap<Integer, Integer> testCases = new HashMap<>();
        testCases.put(0, 2);
        testCases.put(1, 2);
        testCases.put(2, 3);
        testCases.put(3, 2);
        testCases.put(10, 3);
        testCases.put(11, 10);
        testCases.put(15, 2);
        testCases.put(39, 12);
        testCases.put(44, 10);
        testCases.put(58, 28);
        testCases.put(69, 22);
        testCases.put(79, 78);
        testCases.put(87, 28);
        testCases.put(90, 14);
        testCases.put(5591, 37);
        testCases.put(5895, 130);
        testCases.put(9950, 198);
        testCases.put(9974, 4986);

        for (final var tc : testCases.entrySet()) {
            assertEquals(LowestBasePalindrome.lowestBasePalindrome(tc.getKey()), tc.getValue());
        }
    }
}
