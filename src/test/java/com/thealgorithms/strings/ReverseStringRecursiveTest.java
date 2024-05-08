package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseStringRecursiveTest {
    @Test
    void shouldAcceptWhenEmptyStringIsPassed() {
        String expected = "";
        String reversed = ReverseStringRecursive.reverse("");

        assertEquals(expected, reversed);
    }

    @Test
    void shouldAcceptNotWhenWhenSingleCharacterIsPassed() {
        String expected = "a";
        String reversed = ReverseStringRecursive.reverse("a");

        assertEquals(expected, reversed);
    }

    @Test
    void shouldAcceptWhenStringIsPassed() {
        String expected = "dlroWolleH";
        String reversed = ReverseStringRecursive.reverse("HelloWorld");

        assertEquals(expected, reversed);
    }
}
