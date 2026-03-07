package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TopKFrequentWordsTest {

    @ParameterizedTest
    @MethodSource("validTestCases")
    void testFindTopKFrequentWords(String[] words, int k, List<String> expected) {
        assertEquals(expected, TopKFrequentWords.findTopKFrequentWords(words, k));
    }

    static Stream<Arguments> validTestCases() {
        return Stream.of(Arguments.of(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2, List.of("i", "love")), Arguments.of(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4, List.of("the", "is", "sunny", "day")),
            Arguments.of(new String[] {"bbb", "aaa", "bbb", "aaa", "ccc"}, 2, List.of("aaa", "bbb")), Arguments.of(new String[] {"one", "two", "three"}, 10, List.of("one", "three", "two")), Arguments.of(new String[] {}, 3, List.of()), Arguments.of(new String[] {"x", "x", "y"}, 0, List.of()));
    }

    @ParameterizedTest
    @MethodSource("invalidTestCases")
    void testFindTopKFrequentWordsInvalidInput(String[] words, int k) {
        assertThrows(IllegalArgumentException.class, () -> TopKFrequentWords.findTopKFrequentWords(words, k));
    }

    static Stream<Arguments> invalidTestCases() {
        return Stream.of(Arguments.of((String[]) null, 1), Arguments.of(new String[] {"a", null, "b"}, 2), Arguments.of(new String[] {"a"}, -1));
    }
}
