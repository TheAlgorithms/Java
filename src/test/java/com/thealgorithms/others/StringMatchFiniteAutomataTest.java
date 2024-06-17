package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringMatchFiniteAutomataTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void searchPattern(String text, String pattern, Set<Integer> expectedOutput) {
        assertEquals(expectedOutput, StringMatchFiniteAutomata.searchPattern(text, pattern));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("abcbcabc", "abc", Set.of(0, 5)), Arguments.of("", "abc", Set.of()), Arguments.of("", "", Set.of()), Arguments.of("a", "b", Set.of()), Arguments.of("a", "a", Set.of(0)), Arguments.of("abcdabcabcabcd", "abcd", Set.of(0, 10)), Arguments.of("abc", "bcd", Set.of()),
            Arguments.of("abcdefg", "xyz", Set.of()), Arguments.of("abcde", "", Set.of(1, 2, 3, 4, 5)), Arguments.of("abcabcabc", "abc", Set.of(0, 3, 6)), Arguments.of("abcabcabc", "abcabcabc", Set.of(0)), Arguments.of("aaabbbaaa", "aaa", Set.of(0, 6)), Arguments.of("abcdefg", "efg", Set.of(4)));
    }
}
