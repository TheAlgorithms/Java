package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AlphabeticalTest {
    @Test
    public void isAlphabetical() {
        // expected to be true
        String input1 = "abcdefghijklmno";
        String input2 = "abcdxxxyzzzz";
        String input3 = "fpw";

        // expected to be false
        String input4 = "123a";
        String input5 = "abcABC";
        String input6 = "abcdefghikjlmno";

        assertTrue(Alphabetical.isAlphabetical(input1));
        assertTrue(Alphabetical.isAlphabetical(input2));
        assertTrue(Alphabetical.isAlphabetical(input3));

        assertFalse(Alphabetical.isAlphabetical(input4));
        assertFalse(Alphabetical.isAlphabetical(input5));
        assertFalse(Alphabetical.isAlphabetical(input6));
    }

}
