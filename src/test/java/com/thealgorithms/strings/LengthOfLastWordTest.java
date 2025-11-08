package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthOfLastWordTest {
    @Test
    public void testLengthOfLastWord() {
        assertEquals(5, new LengthOfLastWord().lengthOfLastWord("Hello World"));
        assertEquals(4, new LengthOfLastWord().lengthOfLastWord("  fly me   to   the moon  "));
        assertEquals(6, new LengthOfLastWord().lengthOfLastWord("luffy is still joyboy"));
        assertEquals(5, new LengthOfLastWord().lengthOfLastWord("Hello"));
        assertEquals(0, new LengthOfLastWord().lengthOfLastWord("     "));
        assertEquals(0, new LengthOfLastWord().lengthOfLastWord(""));
        assertEquals(3, new LengthOfLastWord().lengthOfLastWord("JUST LIE  "));
    }
}
