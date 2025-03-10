package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AnagramsTest {

    record AnagramTestCase(String input1, String input2, boolean expected) {
    }

    private static Stream<AnagramTestCase> anagramTestData() {
        return Stream.of(new AnagramTestCase("late", "tale", true), new AnagramTestCase("late", "teal", true), new AnagramTestCase("listen", "silent", true), new AnagramTestCase("hello", "olelh", true), new AnagramTestCase("hello", "world", false), new AnagramTestCase("deal", "lead", true),
            new AnagramTestCase("binary", "brainy", true), new AnagramTestCase("adobe", "abode", true), new AnagramTestCase("cat", "act", true), new AnagramTestCase("cat", "cut", false));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach1(AnagramTestCase testCase) {
        assertEquals(testCase.expected(), Anagrams.approach1(testCase.input1(), testCase.input2()));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach2(AnagramTestCase testCase) {
        assertEquals(testCase.expected(), Anagrams.approach2(testCase.input1(), testCase.input2()));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach3(AnagramTestCase testCase) {
        assertEquals(testCase.expected(), Anagrams.approach3(testCase.input1(), testCase.input2()));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach4(AnagramTestCase testCase) {
        assertEquals(testCase.expected(), Anagrams.approach4(testCase.input1(), testCase.input2()));
    }

    @ParameterizedTest
    @MethodSource("anagramTestData")
    void testApproach5(AnagramTestCase testCase) {
        assertEquals(testCase.expected(), Anagrams.approach5(testCase.input1(), testCase.input2()));
    }
}
