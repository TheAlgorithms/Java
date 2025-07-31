package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ReverseStringUsingStackTest {

    @Test
    void testRegularString() {
        assertEquals("olleh", ReverseStringUsingStack.reverse("hello"));
    }

    @Test
    void testEmptyString() {
        assertEquals("", ReverseStringUsingStack.reverse(""));
    }

    @Test
    void testPalindromeString() {
        assertEquals("madam", ReverseStringUsingStack.reverse("madam"));
    }

    @Test
    void testSpecialCharacters() {
        assertEquals("#@!321cba", ReverseStringUsingStack.reverse("abc123!@#"));
    }

    @Test
    void testSingleCharacter() {
        assertEquals("x", ReverseStringUsingStack.reverse("x"));
    }

    @Test
    void testWhitespaceHandling() {
        assertEquals("dlroW olleH", ReverseStringUsingStack.reverse("Hello World"));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> { ReverseStringUsingStack.reverse(null); });
    }
}
