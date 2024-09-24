package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ManacherTest {

    @ParameterizedTest
    @MethodSource("provideTestCasesForLongestPalindrome")
    public void testLongestPalindrome(String input, String expected) {
        assertEquals(expected, Manacher.longestPalindrome(input));
    }

    private static Stream<Arguments> provideTestCasesForLongestPalindrome() {
        return Stream.of(Arguments.of("abracadabraabcdefggfedcbaabracadabra", "aabcdefggfedcbaa"), Arguments.of("somelongtextwithracecarmiddletext", "racecar"), Arguments.of("bananananananana", "ananananananana"), Arguments.of("qwertydefgfedzxcvbnm", "defgfed"),
            Arguments.of("abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba", "abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba"));
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForEmptyAndSingle")
    public void testEmptyAndSingle(String input, String expected) {
        assertEquals(expected, Manacher.longestPalindrome(input));
    }

    private static Stream<Arguments> provideTestCasesForEmptyAndSingle() {
        return Stream.of(Arguments.of("", ""), Arguments.of("a", "a"));
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForComplexCases")
    public void testComplexCases(String input, String expected) {
        assertEquals(expected, Manacher.longestPalindrome(input));
    }

    private static Stream<Arguments> provideTestCasesForComplexCases() {
        return Stream.of(Arguments.of("abcdefghijklmnopqrstuvwxyzttattarrattatabcdefghijklmnopqrstuvwxyz", "tattarrattat"), Arguments.of("aaaaabaaaaacbaaaaa", "aaaaabaaaaa"), Arguments.of("sometextrandomabcdefgabcdefghhgfedcbahijklmnopqrstuvwxyz", "abcdefghhgfedcba"),
            Arguments.of("therewasasignthatsaidmadaminedenimadamitwasthereallalong", "madaminedenimadam"));
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForSentencePalindromes")
    public void testSentencePalindromes(String input, String expected) {
        assertEquals(expected, Manacher.longestPalindrome(input));
    }

    private static Stream<Arguments> provideTestCasesForSentencePalindromes() {
        return Stream.of(Arguments.of("XThisisalongtextbuthiddeninsideisAmanaplanacanalPanamaWhichweknowisfamous", "lanacanal"), Arguments.of("AverylongstringthatcontainsNeveroddoreveninahiddenmanner", "everoddoreve"));
    }
}
