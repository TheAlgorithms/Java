package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PangramTest {
    @Test
    public void isPangram() {
        String fullAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String notFullAlphabet = "abcdefghiklmnopqrstuvwxyz";
        String fullMixedCaseAlphabet = "a BCDE fghIjkLMnop qrSTuv WXYz";
        String sentence1 = "The quick brown fox jumps over the lazy dog";
        String sentence2 = "The quick brown fox jumps over the lazy gentleman";  // missing letter d

        assertTrue(Pangram.isPangram(fullAlphabet));
        assertFalse(Pangram.isPangram(notFullAlphabet));
        assertTrue(Pangram.isPangram(fullMixedCaseAlphabet));
        assertTrue(Pangram.isPangram(sentence1));
        assertFalse(Pangram.isPangram(sentence2));

    }
}
