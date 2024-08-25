package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AnagramsTest {
    @ParameterizedTest
    @CsvSource({"late, tale, true", "late, teal, true", "listen, silent, true", "hello, olelh, true", "hello, world, false", "deal, lead, true", "binary, brainy, true", "adobe, abode, true", "cat, act, true", "cat, cut, false"})
    void testApproach1(String input1, String input2, boolean expected) {
        assertEquals(Anagrams.approach1(input1, input2), expected);
        assertEquals(Anagrams.approach2(input1, input2), expected);
        assertEquals(Anagrams.approach3(input1, input2), expected);
        assertEquals(Anagrams.approach4(input1, input2), expected);
        assertEquals(Anagrams.approach5(input1, input2), expected);
    }
}
