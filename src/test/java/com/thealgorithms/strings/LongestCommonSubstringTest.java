package com.thealgorithms.strings;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestCommonSubstringTest {

    private record TestData(String a, String b, String expected) {
    }

    private static Stream<TestData> provideTestCases() {
        return Stream.of(
            new TestData(null, "abc", ""),
            new TestData("abc", null, ""),
            new TestData("", "abc", ""),
            new TestData("abc", "", ""),
            new TestData("abcdef", "zcdemf", "cde"),
            new TestData("abc", "abc", "abc"),
            new TestData("abc", "xyz", ""),
            new TestData("abcdef", "cdefgh", "cdef"),
            new TestData("a", "a", "a")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testLongestCommonSubstring(TestData testData) {
        Assertions.assertEquals(testData.expected(),
            LongestCommonSubstring.longestCommonSubstring(testData.a(), testData.b()));
    }
}