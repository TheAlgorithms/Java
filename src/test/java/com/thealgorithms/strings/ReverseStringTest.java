package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseStringTest {

    @Test
    public void ReverseStringTest() {
        String input1 = "Hello World";
        String input2 = "helloworld";
        String input3 = "123456789";
        String input4 = "";

        String expectedOutput1 = "dlroW olleH";
        String expectedOutput2 = "dlrowolleh";
        String expectedOutput3 = "987654321";
        String expectedOutput4 = "";

        assertEquals(ReverseString.reverse(input1), expectedOutput1);
        assertEquals(ReverseString.reverse(input2), expectedOutput2);
        assertEquals(ReverseString.reverse(input3), expectedOutput3);
        assertEquals(ReverseString.reverse(input4), expectedOutput4);

        assertEquals(ReverseString.reverse2(input1), expectedOutput1);
        assertEquals(ReverseString.reverse2(input2), expectedOutput2);
        assertEquals(ReverseString.reverse2(input3), expectedOutput3);
        assertEquals(ReverseString.reverse2(input4), expectedOutput4);
    }
}
