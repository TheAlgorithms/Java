package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PangramTest {

    @Test
    public void testPangram() {
        assertTrue(Pangram.isPangram("The quick brown fox jumps over the lazy dog"));
        assertFalse(Pangram.isPangram("The quick brown fox jumps over the azy dog")); // L is missing
        assertFalse(Pangram.isPangram("+-1234 This string is not alphabetical"));
        assertFalse(Pangram.isPangram("\u0000/\\ Invalid characters are alright too"));

        assertTrue(Pangram.isPangram2("The quick brown fox jumps over the lazy dog"));
        assertFalse(Pangram.isPangram2("The quick brown fox jumps over the azy dog")); // L is missing
        assertFalse(Pangram.isPangram2("+-1234 This string is not alphabetical"));
        assertFalse(Pangram.isPangram2("\u0000/\\ Invalid characters are alright too"));
    }
}
