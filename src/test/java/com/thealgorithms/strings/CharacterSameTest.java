package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CharacterSameTest {

    @Test
    public void isAllCharactersSame() {
        String input1 = "aaa";
        String input2 = "abc";
        String input3 = "1  1  1  1";
        String input4 = "111";
        String input5 = "";
        String input6 = "           ";
        String input7 = ".       ";

        assertTrue(CharactersSame.isAllCharactersSame(input1));
        assertFalse(CharactersSame.isAllCharactersSame(input2));
        assertFalse(CharactersSame.isAllCharactersSame(input3));
        assertTrue(CharactersSame.isAllCharactersSame(input4));
        assertTrue(CharactersSame.isAllCharactersSame(input5));
        assertTrue(CharactersSame.isAllCharactersSame(input6));
        assertFalse(CharactersSame.isAllCharactersSame(input7));
    }
}
