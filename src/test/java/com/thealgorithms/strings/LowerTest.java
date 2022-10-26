package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LowerTest {
    @Test
    public void toLowerCase() {
        String input1 = "hello world";
        String input2 = "HelLO WoRld";
        String input3 = "HELLO WORLD";
        
        assertEquals("hello world", Lower.toLowerCase(input1));
        assertEquals("hello world", Lower.toLowerCase(input2));
        assertEquals("hello world", Lower.toLowerCase(input3));
    }
}
