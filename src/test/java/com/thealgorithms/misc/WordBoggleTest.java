package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WordBoggleTest {
    private char[][] board;

    @BeforeEach
    void setup() {
        board = new char[][] {
            {'t', 'h', 'i', 's', 'i', 's', 'a'},
            {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
            {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
            {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
            {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
            {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
            {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
            {'N', 'O', 'T', 'R', 'E', '_', 'P'},
            {'x', 'x', 'D', 'E', 'T', 'A', 'E'},
        };
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testBoggleBoard(String[] words, List<String> expectedWords, String testDescription) {
        List<String> result = WordBoggle.boggleBoard(board, words);
        assertEquals(expectedWords.size(), result.size(), "Test failed for: " + testDescription);
        assertTrue(expectedWords.containsAll(result), "Test failed for: " + testDescription);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new String[] {"this", "is", "not", "a", "simple", "test", "boggle", "board", "REPEATED", "NOTRE_PEATED"}, Arrays.asList("this", "is", "a", "simple", "board", "boggle", "NOTRE_PEATED"), "All words"),
            Arguments.of(new String[] {"xyz", "hello", "world"}, List.of(), "No matching words"), Arguments.of(new String[] {}, List.of(), "Empty words array"), Arguments.of(new String[] {"this", "this", "board", "board"}, Arrays.asList("this", "board"), "Duplicate words in input"));
    }

    @ParameterizedTest
    @MethodSource("provideSpecialCases")
    void testBoggleBoardSpecialCases(char[][] specialBoard, String[] words, Collection<String> expectedWords, String testDescription) {
        List<String> result = WordBoggle.boggleBoard(specialBoard, words);
        assertEquals(expectedWords.size(), result.size(), "Test failed for: " + testDescription);
        assertTrue(expectedWords.containsAll(result), "Test failed for: " + testDescription);
    }

    private static Stream<Arguments> provideSpecialCases() {
        return Stream.of(Arguments.of(new char[0][0], new String[] {"this", "is", "a", "test"}, List.of(), "Empty board"));
    }
}
