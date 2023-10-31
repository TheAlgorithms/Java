package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class WordUpperCaseTest {

    @Test
    public void toUpperCase() {
        String input1 = "hello world";
        String input2 = "HelLO WoRld";
        String input3 = "HELLO WORLD";

        assertEquals("HELLO WORLD", WordUpperCase.toUpperCase(input1));
        assertEquals("HELLO WORLD", WordUpperCase.toUpperCase(input2));
        assertEquals("HELLO WORLD", WordUpperCase.toUpperCase(input3));
    }
}
