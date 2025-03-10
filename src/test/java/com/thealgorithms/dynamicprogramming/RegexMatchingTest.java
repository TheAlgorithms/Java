package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RegexMatchingTest {

    private record RegexTestCase(String s, String p, boolean expected) {
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new RegexTestCase("aa", "*", true)), Arguments.of(new RegexTestCase("aa", "a*", true)), Arguments.of(new RegexTestCase("aa", "a", false)), Arguments.of(new RegexTestCase("cb", "?b", true)), Arguments.of(new RegexTestCase("cb", "?a", false)),
            Arguments.of(new RegexTestCase("adceb", "*a*b", true)), Arguments.of(new RegexTestCase("acdcb", "a*c?b", false)), Arguments.of(new RegexTestCase("", "*", true)), Arguments.of(new RegexTestCase("", "", true)));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testRegexRecursionMethod1(RegexTestCase testCase) {
        assertEquals(testCase.expected(), RegexMatching.regexRecursion(testCase.s(), testCase.p()));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testRegexRecursionMethod2(RegexTestCase testCase) {
        assertEquals(testCase.expected(), RegexMatching.regexRecursion(testCase.s(), testCase.p(), 0, 0));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testRegexRecursionMethod3(RegexTestCase testCase) {
        assertEquals(testCase.expected(), RegexMatching.regexRecursion(testCase.s(), testCase.p(), 0, 0, new int[testCase.s().length()][testCase.p().length()]));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testRegexBottomUp(RegexTestCase testCase) {
        assertEquals(testCase.expected(), RegexMatching.regexBU(testCase.s(), testCase.p()));
    }
}
