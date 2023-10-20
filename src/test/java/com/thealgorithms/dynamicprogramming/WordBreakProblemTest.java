package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author Sachin Baghel (https://github.com/sachin10fi/)
 */
public class WordBreakProblemTest {
    @Test
    public void testInputs() {
        String[] dictionary = {"wait", "here", "i", "told", "you", "to", " "};
        List<String> dictionaryList = Arrays.asList(dictionary);
        assertTrue(WordBreakProblem.wordBreak("hereyou", dictionaryList));
        assertTrue(WordBreakProblem.wordBreak("i told you to wait here", dictionaryList));
        assertFalse(WordBreakProblem.wordBreak("i told you to not wait here", dictionaryList));
        assertFalse(WordBreakProblem.wordBreak("noproblem", dictionaryList));
    }}