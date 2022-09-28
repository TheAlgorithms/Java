package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.thealgorithms.strings.Pangram.isPangram;


public class PangramTest {
    @Test
    public void testPangram() {
        assertTrue(isPangram("The quick brown fox jumps over the lazy dog"));
        assertFalse(isPangram("The quick brown fox jumps over the azy dog")); // L is missing
        assertFalse(isPangram("+-1234 This string is not alphabetical"));
        assertFalse(isPangram("\u0000/\\ Invalid characters are alright too"));
    }
}
