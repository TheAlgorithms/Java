package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseStringRecursiveTest {
    ReverseStringRecursive stringRecursive = new ReverseStringRecursive();

    @Test
    void shouldAcceptWhenEmptyStringIsPassed() {
        String expected = "";
        String reversed = stringRecursive.reverse("");

        assertEquals(expected, reversed);
    }

    @Test
    void shouldAcceptNotWhenWhenSingleCharacterIsPassed() {
        String expected = "a";
        String reversed = stringRecursive.reverse("a");

        assertEquals(expected, reversed);
    }

    @Test
    void shouldAcceptWhenStringIsPassed() {
        String expected = "dlroWolleH";
        String reversed = stringRecursive.reverse("HelloWorld");

        assertEquals(expected, reversed);
    }
}
