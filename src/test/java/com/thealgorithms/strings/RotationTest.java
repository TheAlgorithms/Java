package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotationTest {
    @Test
    public void RotationTests() {
        String input1 = "";
        String input2 = "qwerty";
        String input3 = "String with spaces and Uppercase Letters";
        String input4 = "A";
        String input5 = "12345678";
        String input6 = "Mix 0f L3TT3RS and Numbers";

        String expectedOutput1 = "";
        String expectedOutput2 = "ertyqw";
        String expectedOutput3 = " with spaces and Uppercase LettersString";
        String expectedOutput4 = "A";
        String expectedOutput5 = "45678123";
        String expectedOutput6 = "L3TT3RS and NumbersMix 0f ";

        assertEquals(Rotation.rotation(input1, 10), expectedOutput1);
        assertEquals(Rotation.rotation(input2, 2), expectedOutput2);
        assertEquals(Rotation.rotation(input3, 6), expectedOutput3);
        assertEquals(Rotation.rotation(input4, 100), expectedOutput4);
        assertEquals(Rotation.rotation(input5, 3), expectedOutput5);
        assertEquals(Rotation.rotation(input6, 7), expectedOutput6);


    }
}
