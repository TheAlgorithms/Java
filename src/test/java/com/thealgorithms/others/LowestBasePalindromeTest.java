package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;

import com.thealgorithms.sorts.LinkListSort;
import org.junit.jupiter.api.Test;

public class LowestBasePalindromeTest {

    @Test
    void testLowestBasePalindrome() {
        HashMap<Integer, Integer> testCases = new HashMap<>();
        //testCases.put(1, 2);
        //testCases.put(2, 3);
        testCases.put(3, 2);
        testCases.put(10, 3);
        testCases.put(11, 10);
        testCases.put(15, 2);
        testCases.put(39, 12);
        testCases.put(44, 10);
        testCases.put(58, 28);
        
        for (final var tc : testCases.entrySet()) {
            assertEquals(LowestBasePalindrome.lowestBasePalindrome(tc.getKey()), tc.getValue());
        }
    }
}
