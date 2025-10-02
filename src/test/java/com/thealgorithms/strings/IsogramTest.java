package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class IsogramTest {

    record IsogramTestCase(String input, boolean expected) {
    }

    private static Stream<IsogramTestCase> isogramArrayTestData() {
        return Stream.of(
                // Valid isograms (only checks letters)
                new IsogramTestCase("uncopyrightable", true), new IsogramTestCase("dermatoglyphics", true),
                new IsogramTestCase("background", true), new IsogramTestCase("python", true),
                new IsogramTestCase("keyboard", true), new IsogramTestCase("clipboard", true),
                new IsogramTestCase("flowchart", true),
                new IsogramTestCase("bankruptcy", true), new IsogramTestCase("computer", true),
                new IsogramTestCase("algorithms", true),

                // Not isograms - letters repeat
                new IsogramTestCase("hello", false), new IsogramTestCase("programming", false),
                new IsogramTestCase("java", false), new IsogramTestCase("coffee", false),
                new IsogramTestCase("book", false), new IsogramTestCase("letter", false),
                new IsogramTestCase("mississippi", false),
                new IsogramTestCase("google", false),

                // Edge cases
                new IsogramTestCase("", true), new IsogramTestCase("a", true), new IsogramTestCase("ab", true),
                new IsogramTestCase("abc", true), new IsogramTestCase("aa", false),
                new IsogramTestCase("abcdefghijklmnopqrstuvwxyz", true), // All 26 letters

                // Case insensitive
                new IsogramTestCase("Python", true), new IsogramTestCase("BACKGROUND", true),
                new IsogramTestCase("Hello", false), new IsogramTestCase("PROGRAMMING", false));
    }

    private static Stream<IsogramTestCase> isogramLengthTestData() {
        return Stream.of(
                // Valid isograms (checks all characters)
                new IsogramTestCase("uncopyrightable", true), new IsogramTestCase("dermatoglyphics", true),
                new IsogramTestCase("background", true), new IsogramTestCase("python", true),
                new IsogramTestCase("keyboard", true), new IsogramTestCase("clipboard", true),
                new IsogramTestCase("flowchart", true),
                new IsogramTestCase("bankruptcy", true), new IsogramTestCase("computer", true),
                new IsogramTestCase("algorithms", true),

                // Not isograms - characters repeat
                new IsogramTestCase("hello", false), new IsogramTestCase("programming", false),
                new IsogramTestCase("java", false), new IsogramTestCase("coffee", false),
                new IsogramTestCase("book", false), new IsogramTestCase("letter", false),
                new IsogramTestCase("mississippi", false),
                new IsogramTestCase("google", false),

                // Edge cases
                new IsogramTestCase("", true), new IsogramTestCase("a", true), new IsogramTestCase("ab", true),
                new IsogramTestCase("abc", true), new IsogramTestCase("aa", false),
                new IsogramTestCase("abcdefghijklmnopqrstuvwxyz", true), // All 26 letters

                // Case insensitive
                new IsogramTestCase("Python", true), new IsogramTestCase("BACKGROUND", true),
                new IsogramTestCase("Hello", false), new IsogramTestCase("PROGRAMMING", false),

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
    @MethodSource("isogramArrayTestData")
    void testIsogramByArray(IsogramTestCase testCase) {
        assertEquals(testCase.expected(), Isogram.isIsogramByArray(testCase.input()));
    }

    @ParameterizedTest
    @MethodSource("isogramLengthTestData")
    void testIsogramByLength(IsogramTestCase testCase) {
        assertEquals(testCase.expected(), Isogram.isIsogramByLength(testCase.input()));
    }

    @Test
    void testNullInputByArray() {
        assertEquals(true, Isogram.isIsogramByArray(null));
    }

    @Test
    void testNullInputByLength() {
        assertEquals(true, Isogram.isIsogramByLength(null));
    }

    @Test
    void testEmptyStringByArray() {
        assertEquals(true, Isogram.isIsogramByArray(""));
    }

    @Test
    void testEmptyStringByLength() {
        assertEquals(true, Isogram.isIsogramByLength(""));
    }