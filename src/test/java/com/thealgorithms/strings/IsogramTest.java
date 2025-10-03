package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class IsogramTest {

    record IsogramTestCase(String input, boolean expected) {
    }

    private static Stream<IsogramTestCase> isAlphabeticIsogram() {
        return Stream.of(
            // Valid isograms (only checks letters)
            new IsogramTestCase("uncopyrightable", true), new IsogramTestCase("dermatoglyphics", true), new IsogramTestCase("background", true), new IsogramTestCase("python", true), new IsogramTestCase("keyboard", true), new IsogramTestCase("clipboard", true), new IsogramTestCase("flowchart", true),
            new IsogramTestCase("bankruptcy", true), new IsogramTestCase("computer", true), new IsogramTestCase("algorithms", true),

            // Not isograms - letters repeat
            new IsogramTestCase("hello", false), new IsogramTestCase("programming", false), new IsogramTestCase("java", false), new IsogramTestCase("coffee", false), new IsogramTestCase("book", false), new IsogramTestCase("letter", false), new IsogramTestCase("mississippi", false),
            new IsogramTestCase("google", false),

            // Edge cases
            new IsogramTestCase("", true), new IsogramTestCase("a", true), new IsogramTestCase("ab", true), new IsogramTestCase("abc", true), new IsogramTestCase("aa", false), new IsogramTestCase("abcdefghijklmnopqrstuvwxyz", true),

            // Case insensitive
            new IsogramTestCase("Python", true), new IsogramTestCase("BACKGROUND", true), new IsogramTestCase("Hello", false), new IsogramTestCase("PROGRAMMING", false));
    }

    private static Stream<IsogramTestCase> isFullIsogram() {
        return Stream.of(
            // Valid isograms (checks all characters)
            new IsogramTestCase("uncopyrightable", true), new IsogramTestCase("dermatoglyphics", true), new IsogramTestCase("background", true), new IsogramTestCase("python", true), new IsogramTestCase("keyboard", true), new IsogramTestCase("clipboard", true), new IsogramTestCase("flowchart", true),
            new IsogramTestCase("bankruptcy", true), new IsogramTestCase("computer", true), new IsogramTestCase("algorithms", true),

            // Not isograms - characters repeat
            new IsogramTestCase("hello", false), new IsogramTestCase("programming", false), new IsogramTestCase("java", false), new IsogramTestCase("coffee", false), new IsogramTestCase("book", false), new IsogramTestCase("letter", false), new IsogramTestCase("mississippi", false),
            new IsogramTestCase("google", false),

            // Edge cases
            new IsogramTestCase("", true), new IsogramTestCase("a", true), new IsogramTestCase("ab", true), new IsogramTestCase("abc", true), new IsogramTestCase("aa", false), new IsogramTestCase("abcdefghijklmnopqrstuvwxyz", true),

            // Case insensitive
            new IsogramTestCase("Python", true), new IsogramTestCase("BACKGROUND", true), new IsogramTestCase("Hello", false), new IsogramTestCase("PROGRAMMING", false),

            // Strings with symbols and numbers
            new IsogramTestCase("abc@def", true), // all characters unique
            new IsogramTestCase("test-case", false), // 't', 's', 'e' repeat
            new IsogramTestCase("python123", true), // all characters unique
            new IsogramTestCase("hello@123", false), // 'l' repeats
            new IsogramTestCase("abc123!@#", true), // all characters unique
            new IsogramTestCase("test123test", false), // 't', 'e', 's' repeat
            new IsogramTestCase("1234567890", true), // all digits unique
            new IsogramTestCase("12321", false), // '1' and '2' repeat
            new IsogramTestCase("!@#$%^&*()", true) // all special characters unique
        );
    }

    @ParameterizedTest
    @MethodSource("isAlphabeticIsogram")
    void testIsogramByArray(IsogramTestCase testCase) {
        assertEquals(testCase.expected(), Isogram.isAlphabeticIsogram(testCase.input()));
    }

    @ParameterizedTest
    @MethodSource("isFullIsogram")
    void testIsogramByLength(IsogramTestCase testCase) {
        assertEquals(testCase.expected(), Isogram.isFullIsogram(testCase.input()));
    }

    @Test
    void testNullInputByArray() {
        assertTrue(Isogram.isAlphabeticIsogram(null));
    }

    @Test
    void testNullInputByLength() {
        assertTrue(Isogram.isFullIsogram(null));
    }

    @Test
    void testEmptyStringByArray() {
        assertTrue(Isogram.isAlphabeticIsogram(""));
    }

    @Test
    void testEmptyStringByLength() {
        assertTrue(Isogram.isFullIsogram(""));
    }

    @Test
    void testAlphabeticIsogramThrowsException() {
        // Test that IllegalArgumentException is thrown for non-alphabetic characters
        assertThrows(IllegalArgumentException.class, () -> Isogram.isAlphabeticIsogram("1"));
        assertThrows(IllegalArgumentException.class, () -> Isogram.isAlphabeticIsogram("@"));
        assertThrows(IllegalArgumentException.class, () -> Isogram.isAlphabeticIsogram("python!"));
        assertThrows(IllegalArgumentException.class, () -> Isogram.isAlphabeticIsogram("123algorithm"));
        assertThrows(IllegalArgumentException.class, () -> Isogram.isAlphabeticIsogram("hello123"));
        assertThrows(IllegalArgumentException.class, () -> Isogram.isAlphabeticIsogram("!@@#$%^&*()"));
    }

    @Test
    void testFullIsogramWithMixedCharacters() {
        // Test that full isogram method handles all character types without exceptions
        assertTrue(Isogram.isFullIsogram("abc123"));
        assertFalse(Isogram.isFullIsogram("test@email")); // 'e' repeats
        assertFalse(Isogram.isFullIsogram("hello123")); // 'l' repeats
        assertTrue(Isogram.isFullIsogram("1234567890"));
        assertFalse(Isogram.isFullIsogram("12321")); // '1' and '2' repeat
        assertTrue(Isogram.isFullIsogram("!@#$%^&*()"));
    }
}
