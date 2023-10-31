package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WordUpperCaseTest {
    @Test
    public void testToUpperCase() {
        // Test a basic case
        String input1 = "hii my name is abhishek";
        String expected1 = "Hii My Name Is Abhishek";
        assertEquals(expected1, WordUpperCase.toUpperCase(input1));

        // Test with an empty string
        String input2 = "";
        String expected2 = "";
        assertEquals(expected2, WordUpperCase.toUpperCase(input2));

        // Test with a string that has only one word
        String input3 = "hello";
        String expected3 = "Hello";
        assertEquals(expected3, WordUpperCase.toUpperCase(input3));

    }
}
