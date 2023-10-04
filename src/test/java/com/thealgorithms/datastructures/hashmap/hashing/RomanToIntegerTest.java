package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RomanToIntegerTest {
    @Test
    void romanToInteger_Test1() {
        String s = "LVIII";
        int expected = 58;
        int actual = RomanToInteger.romanToInt(s);
        assertEquals(expected, actual);
    }

    @Test
    void romanToInteger_Test2() {
        String s = "MCMXCIV";
        int expected = 1994;
        int actual = RomanToInteger.romanToInt(s);
        assertEquals(expected, actual);
    }

    @Test
    void romanToInteger_Test3() {
        String s = "MMM";
        int expected = 3000;
        int actual = RomanToInteger.romanToInt(s);
        assertEquals(expected, actual);
    }

    @Test
    void romanToInteger_Test4() {
        String s = "I";
        int expected = 1;
        int actual = RomanToInteger.romanToInt(s);
        assertEquals(expected, actual);
    }
}
