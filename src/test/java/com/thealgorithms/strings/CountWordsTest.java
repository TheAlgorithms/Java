package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CountWordsTest {

    @ParameterizedTest
    @MethodSource("wordCountTestCases")
    void testWordCount(String input, int expectedCount) {
        assertEquals(expectedCount, CountWords.wordCount(input));
    }

    @ParameterizedTest
    @MethodSource("secondaryWordCountTestCases")
    void testSecondaryWordCount(String input, int expectedCount) {
        assertEquals(expectedCount, CountWords.secondaryWordCount(input));
    }

    private static Stream<Arguments> wordCountTestCases() {
        return Stream.of(Arguments.of("", 0), Arguments.of(null, 0), Arguments.of("aaaa bbb cccc", 3), Arguments.of("note  extra     spaces   here", 4), Arguments.of(" a  b c d  e    ", 5));
    }

    private static Stream<Arguments> secondaryWordCountTestCases() {
        return Stream.of(Arguments.of("", 0), Arguments.of(null, 0), Arguments.of("aaaa bbb cccc", 3), Arguments.of("this-is-one-word!", 1), Arguments.of("What, about, this? Hmmm----strange", 4), Arguments.of("word1 word-2 word-3- w?o,r.d.@!@#$&*()<>4", 4));
    }
}
