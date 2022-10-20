package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharactersUniqueTest {

    @Test
    public void CheckCharactersUniqueTest() {
        String input1 = "";
        String input2 = "This string has multiple repeat characters";
        String input3 = "qwertyuiop";
        String input4 = "A";
        String input5 = "1123124125123";
        String input6 = "Hello World!";
        String input7 = "Tt";

        assertTrue(CharactersUnique.allCharactersUnique(input1));
        assertFalse(CharactersUnique.allCharactersUnique(input2));
        assertTrue(CharactersUnique.allCharactersUnique(input3));
        assertTrue(CharactersUnique.allCharactersUnique(input4));
        assertFalse(CharactersUnique.allCharactersUnique(input5));
        assertFalse(CharactersUnique.allCharactersUnique(input6));
        assertFalse(CharactersUnique.allCharactersUnique(input7));
    }
}
