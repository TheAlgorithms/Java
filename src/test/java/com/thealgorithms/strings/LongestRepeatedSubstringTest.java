package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestRepeatedSubstringTest {

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @MethodSource("provideTestCases")
    void testLongestRepeatedSubstring(String input, String expected) {
        assertEquals(expected, LongestRepeatedSubstring.longestRepeatedSubstring(input));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("banana", "ana"), Arguments.of("abcabc", "abc"), Arguments.of("aaaa", "aaa"), Arguments.of("abcd", ""), Arguments.of("a", ""), Arguments.of("", ""), Arguments.of(null, ""), Arguments.of("aab", "a"), Arguments.of("aa", "a"), Arguments.of("mississippi", "issi"));
    }

    @ParameterizedTest(name = "\"{0}\" -> LCP={1}")
    @MethodSource("provideLcpTestCases")
    void testBuildLcpArray(String input, int[] expectedLcp) {
        int[] suffixArray = SuffixArray.buildSuffixArray(input);
        assertArrayEquals(expectedLcp, LongestRepeatedSubstring.buildLcpArray(input, suffixArray));
    }

    private static Stream<Arguments> provideLcpTestCases() {
        return Stream.of(Arguments.of("banana", new int[] {1, 3, 0, 0, 2}), Arguments.of("ab", new int[] {0}));
    }
}
