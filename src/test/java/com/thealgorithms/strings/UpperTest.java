package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
