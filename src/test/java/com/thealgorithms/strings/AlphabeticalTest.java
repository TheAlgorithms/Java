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

        Throwable exc1 = assertThrows(IllegalArgumentException.class, () -> Alphabetical.isAlphabetical(""));
        Throwable exc2 = assertThrows(IllegalArgumentException.class, () -> Alphabetical.isAlphabetical(null));
        assertEquals("parameter cannot be null or empty.", exc1.getMessage());
        assertEquals("parameter cannot be null or empty.", exc2.getMessage());
    }

}
