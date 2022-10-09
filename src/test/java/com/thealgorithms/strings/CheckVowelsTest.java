package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckVowelsTest {
    @Test
    public void hasVowels() {
        assertTrue(CheckVowels.hasVowels("hello world"));
        assertFalse(CheckVowels.hasVowels("hll wrld"));
        assertFalse(CheckVowels.hasVowels("?!"));
    }
}
