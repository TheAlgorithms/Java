package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestCommonPrefixTest {

    @ParameterizedTest(name = "{index} => input={0}, expected=\"{1}\"")
    @MethodSource("provideTestCases")
    @DisplayName("Test Longest Common Prefix")
    void testLongestCommonPrefix(String[] input, String expected) {
        assertEquals(expected, LongestCommonPrefix.longestCommonPrefix(input));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new String[] {"flower", "flow", "flight"}, "fl"), Arguments.of(new String[] {"dog", "racecar", "car"}, ""), Arguments.of(new String[] {}, ""), Arguments.of(null, ""), Arguments.of(new String[] {"single"}, "single"), Arguments.of(new String[] {"ab", "a"}, "a"),
            Arguments.of(new String[] {"test", "test", "test"}, "test"), Arguments.of(new String[] {"abcde", "abcfgh", "abcmnop"}, "abc"), Arguments.of(new String[] {"Flower", "flow", "flight"}, ""));
    }
}
