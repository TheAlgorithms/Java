package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowerTest {

    @Test
    public void toLowerCase() {
        String input1 = "lower case";
        String input2 = "LOWER CASE";
        String input3 = "LOWER case";

        assertEquals("lower case", Lower.toLowerCase(input1));
        assertEquals("lower case", Lower.toLowerCase(input2));
        assertEquals("lower case", Lower.toLowerCase(input3));
    }

}