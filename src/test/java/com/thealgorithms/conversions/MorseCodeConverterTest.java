package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MorseCodeConverterTest {

    @Test
    public void testTextToMorse() {
        assertEquals(".- -...", MorseCodeConverter.textToMorse("AB"));
        assertEquals(".... . .-.. .-.. --- | .-- --- .-. .-.. -..", MorseCodeConverter.textToMorse("HELLO WORLD"));
    }

    @Test
    public void testMorseToText() {
        assertEquals("AB", MorseCodeConverter.morseToText(".- -..."));
    }
}
