package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WordUpperCaseTest {

    @Test
    public void testToUpperCase() {
        String input1 = "hello world";
        String expected1 = "Hello World";
        assertEquals(expected1, WordUpperCase.toUpperCase(input1));

        String input2 = "this is a test";
        String expected2 = "This Is A Test";
        assertEquals(expected2, WordUpperCase.toUpperCase(input2));

        String input3 = "hello";
        String expected3 = "Hello";
        assertEquals(expected3, WordUpperCase.toUpperCase(input3));
    }
}
