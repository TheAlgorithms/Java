package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
