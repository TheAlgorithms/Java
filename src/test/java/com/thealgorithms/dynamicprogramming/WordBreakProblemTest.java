package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sachin Baghel (https://github.com/sachin10fi/)
 */
class WordBreakProblemTest {

    @Test
    public void testInputs(){
        String[] dictionary = {"wait", "here", "i", "told", "you", "to", " "};
        List<String> dictionaryList = Arrays.asList(dictionary);
        assertTrue(WordBreakProblem.wordBreak("hereyou", dictionaryList));
        assertTrue(WordBreakProblem.wordBreak("i told you to wait here", dictionaryList));
        assertFalse(WordBreakProblem.wordBreak("i told you to not wait here", dictionaryList));
        assertFalse(WordBreakProblem.wordBreak("noproblem", dictionaryList));
    }
}