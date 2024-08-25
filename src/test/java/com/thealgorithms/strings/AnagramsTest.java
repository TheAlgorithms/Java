package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AnagramsTest {

    private static Stream<Arguments> anagramTestData() {
        return Stream.of(Arguments.of("late", "tale", true), Arguments.of("late", "teal", true), Arguments.of("listen", "silent", true), Arguments.of("hello", "olelh", true), Arguments.of("hello", "world", false), Arguments.of("deal", "lead", true), Arguments.of("binary", "brainy", true), Arguments.of("adobe", "abode", true), Arguments.of("cat", "act", true), Arguments.of("cat", "cut", false));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach1(String s, String t, boolean expected) {
        assertEquals(expected, Anagrams.approach1(s, t));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach2(String s, String t, boolean expected) {
        assertEquals(expected, Anagrams.approach2(s, t));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach3(String s, String t, boolean expected) {
        assertEquals(expected, Anagrams.approach3(s, t));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach4(String s, String t, boolean expected) {
        assertEquals(expected, Anagrams.approach4(s, t));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach5(String s, String t, boolean expected) {
        assertEquals(expected, Anagrams.approach5(s, t));
    }
}
