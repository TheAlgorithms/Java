package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LowestBasePalindromeTest {

    @ParameterizedTest
    @MethodSource("provideListsForIsPalindromicPositive")
    public void testIsPalindromicPositive(List<Integer> list) {
        assertTrue(LowestBasePalindrome.isPalindromic(list));
    }

    @ParameterizedTest
    @MethodSource("provideListsForIsPalindromicNegative")
    public void testIsPalindromicNegative(List<Integer> list) {
        assertFalse(LowestBasePalindrome.isPalindromic(list));
    }

    @ParameterizedTest
    @MethodSource("provideNumbersAndBasesForIsPalindromicInBasePositive")
    public void testIsPalindromicInBasePositive(int number, int base) {
        assertTrue(LowestBasePalindrome.isPalindromicInBase(number, base));
    }

    @ParameterizedTest
    @MethodSource("provideNumbersAndBasesForIsPalindromicInBaseNegative")
    public void testIsPalindromicInBaseNegative(int number, int base) {
        assertFalse(LowestBasePalindrome.isPalindromicInBase(number, base));
    }

    @ParameterizedTest
    @MethodSource("provideNumbersAndBasesForExceptions")
    public void testIsPalindromicInBaseThrowsException(int number, int base) {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> LowestBasePalindrome.isPalindromicInBase(number, base));
    }

    @ParameterizedTest
    @MethodSource("provideNumbersForLowestBasePalindrome")
    public void testLowestBasePalindrome(int number, int expectedBase) {
        assertEquals(expectedBase, LowestBasePalindrome.lowestBasePalindrome(number));
    }

    private static Stream<Arguments> provideListsForIsPalindromicPositive() {
        return Stream.of(Arguments.of(new ArrayList<>()), Arguments.of(new ArrayList<>(List.of(1))), Arguments.of(new ArrayList<>(Arrays.asList(1, 1))), Arguments.of(new ArrayList<>(Arrays.asList(1, 2, 1))), Arguments.of(new ArrayList<>(Arrays.asList(1, 2, 2, 1))));
    }

    private static Stream<Arguments> provideListsForIsPalindromicNegative() {
        return Stream.of(Arguments.of(new ArrayList<>(Arrays.asList(1, 2))), Arguments.of(new ArrayList<>(Arrays.asList(1, 2, 1, 1))));
    }

    private static Stream<Arguments> provideNumbersAndBasesForIsPalindromicInBasePositive() {
        return Stream.of(Arguments.of(101, 10), Arguments.of(1, 190), Arguments.of(0, 11), Arguments.of(10101, 10), Arguments.of(23, 22));
    }

    private static Stream<Arguments> provideNumbersAndBasesForIsPalindromicInBaseNegative() {
        return Stream.of(Arguments.of(1010, 10), Arguments.of(123, 10));
    }

    private static Stream<Arguments> provideNumbersAndBasesForExceptions() {
        return Stream.of(Arguments.of(-1, 5), Arguments.of(10, 1));
    }

    private static Stream<Arguments> provideNumbersForLowestBasePalindrome() {
        return Stream.of(Arguments.of(0, 2), Arguments.of(1, 2), Arguments.of(2, 3), Arguments.of(3, 2), Arguments.of(10, 3), Arguments.of(11, 10), Arguments.of(15, 2), Arguments.of(39, 12), Arguments.of(44, 10), Arguments.of(58, 28), Arguments.of(69, 22), Arguments.of(79, 78), Arguments.of(87, 28),
            Arguments.of(90, 14), Arguments.of(5591, 37), Arguments.of(5895, 130), Arguments.of(9950, 198), Arguments.of(9974, 4986));
    }
}
