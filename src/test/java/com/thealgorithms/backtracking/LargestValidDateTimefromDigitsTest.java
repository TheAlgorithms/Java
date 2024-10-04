package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LargestValidDateTimefromDigitsTest {
    @Test
    public void testLargestValidDateTime() {
        // Test case 1: All digits can form a valid date and time
        List<Integer> digits1 = Arrays.asList(2, 0, 2, 1, 1, 2, 3, 4); // Example: 12/31 23:42
        String expected1 = "12/31 23:42";
        String result1 = LargestValidDateTimefromDigits.findLargestValidDateTime(digits1);
        assertEquals(expected1, result1);

        // Test case 2: No valid date can be formed (invalid month)
        List<Integer> digits2 = Arrays.asList(4, 5, 6, 7, 8, 9); // No valid month can be formed
        String expected2 = "0"; // No valid datetime
        String result2 = LargestValidDateTimefromDigits.findLargestValidDateTime(digits2);
        assertEquals(expected2, result2);

        // Test case 3: Valid month but invalid day
        List<Integer> digits3 = Arrays.asList(1, 2, 3, 4, 7, 9); // Valid month, but day > 31
        String expected3 = "0"; // No valid datetime
        String result3 = LargestValidDateTimefromDigits.findLargestValidDateTime(digits3);
        assertEquals(expected3, result3);

        // Test case 4: Valid datetime with unused digits
        List<Integer> digits4 = Arrays.asList(2, 0, 2, 1, 1, 9, 5, 6); // Example: 12/19 21:50
        String expected4 = "12/19 21:50";
        String result4 = LargestValidDateTimefromDigits.findLargestValidDateTime(digits4);
        assertEquals(expected4, result4);
    }
}
