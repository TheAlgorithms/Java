package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseStringRecursiveTest {
    ReverseStringRecursive stringRecursive = new ReverseStringRecursive();

    @Test
    void shouldAcceptWhenEmptyStringIsPassed() {
        String expected = "";
        String reversed  = stringRecursive.reverse("");

        assertEquals(expected,reversed);
    }

    @Test
    void shouldAcceptNotWhenWhenSingleCharacterIsPassed() {
        String expected = "a";
        String reversed  = stringRecursive.reverse("a");

        assertEquals(expected,reversed);
    }

    @Test
    void shouldAcceptWhenStringIsPassed() {
        String expected = "dlroWolleH";
        String reversed  = stringRecursive.reverse("HelloWorld");

        assertEquals(expected,reversed);
    }
}
