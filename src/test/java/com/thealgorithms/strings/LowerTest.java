package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowerTest {
    @Test
    public void toLowerCase() {
        String expected = "hello world";

        assertEquals(expected, Lower.toLowerCase("HELLO WORLD"));
        assertEquals(expected, Lower.toLowerCase("Hello World"));
        assertEquals(expected, Lower.toLowerCase("HEllo woRLD"));
        assertEquals("", Lower.toLowerCase(""));
    }
}
