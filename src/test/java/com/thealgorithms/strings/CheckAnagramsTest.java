package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CheckAnagramsTest {

    @Test
    public void CheckAnagrams() {
        String testString1 = "STUDY";
        String testString2 = "DUSTY";
        assertTrue(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    @Test
    public void CheckFalseAnagrams() {
        String testString1 = "STUDY";
        String testString2 = "random";
        assertFalse(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    @Test
    public void CheckSameWordAnagrams() {
        String testString1 = "STUDY";
        assertTrue(CheckAnagrams.isAnagrams(testString1, testString1));
    }

    @Test
    public void CheckDifferentCasesAnagram() {
        String testString1 = "STUDY";
        String testString2 = "dusty";
        assertTrue(CheckAnagrams.isAnagrams(testString1, testString2));
    }
}
