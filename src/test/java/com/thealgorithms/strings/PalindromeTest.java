package com.thealgorithms.strings;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PalindromeTest {
    private static Stream<TestData> provideTestCases() {
        return Stream.of(new TestData(null, true), new TestData("", true), new TestData("aba", true), new TestData("123321", true), new TestData("kayak", true), new TestData("abb", false), new TestData("abc", false), new TestData("abc123", false), new TestData("kayaks", false));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testPalindrome(TestData testData) {
        Assertions.assertEquals(testData.expected, Palindrome.isPalindrome(testData.input) && Palindrome.isPalindromeRecursion(testData.input) && Palindrome.isPalindromeTwoPointer(testData.input));
    }

    private record TestData(String input, boolean expected) {
    }
}
