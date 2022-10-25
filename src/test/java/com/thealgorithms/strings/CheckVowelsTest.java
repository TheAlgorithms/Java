package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CheckVowelsTest {

    @Test
    public void isVowel() {
        assertTrue(CheckVowels.hasVowels("foo"));
        assertTrue(CheckVowels.hasVowels("bar"));
        assertFalse(CheckVowels.hasVowels("why"));
        assertFalse(CheckVowels.hasVowels("myths"));
    }
}
