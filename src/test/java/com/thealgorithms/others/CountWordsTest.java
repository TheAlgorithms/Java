package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class CountWordsTest {
    @Test
    public void testWordCount() {
        HashMap<String, Integer> testCases = new HashMap<>();
        testCases.put("", 0);
        testCases.put(null, 0);
        testCases.put("aaaa bbb cccc", 3);
        testCases.put("note  extra     spaces   here", 4);
        testCases.put(" a  b c d  e    ", 5);

        for (final var tc : testCases.entrySet()) {
            assertEquals(CountWords.wordCount(tc.getKey()), tc.getValue());
        }
    }

    @Test
    public void testSecondaryWordCount() {
        HashMap<String, Integer> testCases = new HashMap<>();
        testCases.put("", 0);
        testCases.put(null, 0);
        testCases.put("aaaa bbb cccc", 3);
        testCases.put("this-is-one-word!", 1);
        testCases.put("What, about, this? Hmmm----strange", 4);
        testCases.put("word1 word-2 word-3- w?o,r.d.@!@#$&*()<>4", 4);

        for (final var tc : testCases.entrySet()) {
            assertEquals(CountWords.secondaryWordCount(tc.getKey()), tc.getValue());
        }
    }
}
