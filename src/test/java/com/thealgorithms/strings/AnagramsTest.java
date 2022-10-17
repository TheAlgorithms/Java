package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AnagramsTest {

    @Test
    public void isAlphabetical() {
        String input1 = "late";
        Anagrams anagrams = new Anagrams();
        assertTrue(anagrams.approach1(input1, "tale"));
        assertTrue(anagrams.approach1(input1, "teal"));
        assertTrue(anagrams.approach2(input1, "tale"));
        assertTrue(anagrams.approach2(input1, "teal"));
        assertTrue(anagrams.approach3(input1, "tale"));
        assertTrue(anagrams.approach3(input1, "teal"));
        assertTrue(anagrams.approach4(input1, "tale"));
        assertTrue(anagrams.approach4(input1, "teal"));
    }
}
