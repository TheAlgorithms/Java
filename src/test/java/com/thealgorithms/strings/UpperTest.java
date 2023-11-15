package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UpperTest {

    @Test
    public void toUpperCase() {
        String input1 = "hello world";
        String input2 = "hElLo WoRlD";
        String input3 = "HELLO WORLD";
        assertEquals("HELLO WORLD", Upper.toUpperCase(input1));
        assertEquals("HELLO WORLD", Upper.toUpperCase(input2));
        assertEquals("HELLO WORLD", Upper.toUpperCase(input3));
    }
    
    @Test
    public void testToUpperCase() {
        String[] strings = {"ABC", "ABC123", "abcABC", "abc123ABC"};
        String[] expected = {"ABC", "ABC123", "ABCABC", "ABC123ABC"};

        for (int i = 0; i < strings.length; i++) {
            String result = Upper.toUpperCase(strings[i]);
            assertEquals(expected[i], result);
        }
    }

    @Test
    public void testToUpperCaseWithNull() {
        String result = Upper.toUpperCase(null);
        assertNull(result);
    }

    @Test
    public void testToUpperCaseWithEmptyString() {
        String result = Upper.toUpperCase("");
        assertEquals("", result);
    }
}
