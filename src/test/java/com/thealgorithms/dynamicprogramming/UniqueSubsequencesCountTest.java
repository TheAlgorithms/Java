package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UniqueSubsequencesCountTest {

    @ParameterizedTest
    @CsvSource({"abc, 7", "abcdashgdhas, 3592", "a, 1", "'a b', 7", "a1b2, 15", "AaBb, 15", "abab, 11"})
    void subseqCountParameterizedTest(String input, int expected) {
        assertEquals(expected, UniqueSubsequencesCount.countSubseq(input));
    }
}
