package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CheckVowelsTest {

    @Test
    public void StringHasVowels() {
        assertTrue(CheckVowels.hasVowels("Hello World!"));
        assertTrue(CheckVowels.hasVowels("a"));
        assertTrue(CheckVowels.hasVowels("e"));
        assertTrue(CheckVowels.hasVowels("i"));
        assertTrue(CheckVowels.hasVowels("o"));
        assertTrue(CheckVowels.hasVowels("u"));
        assertTrue(CheckVowels.hasVowels("A"));
        assertTrue(CheckVowels.hasVowels("E"));
        assertTrue(CheckVowels.hasVowels("This has vowels"));
        assertTrue(CheckVowels.hasVowels("ThIs hAs UppErcasE vOwEls"));
        assertTrue(CheckVowels.hasVowels("SupEr long test strIng with A lot of vowEls and spaces."));
        assertTrue(CheckVowels.hasVowels("Goodbye"));
    }

    @Test
    public void StringHasNoVowels() {
        assertFalse(CheckVowels.hasVowels("Why"));
        assertFalse(CheckVowels.hasVowels("qwrtypsdfghjklzxcvbnm"));
        assertFalse(CheckVowels.hasVowels(""));
        assertFalse(CheckVowels.hasVowels("???????????"));
        assertFalse(CheckVowels.hasVowels("Why"));
    }
}
