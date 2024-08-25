package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EditDistanceTest {

    @ParameterizedTest
    @CsvSource({"'', '', 0", "'abc', '', 3", "'', 'abcd', 4", "'same', 'same', 0", "'a', 'b', 1", "'abc', 'abd', 1"})
    void testMinDistance(String str1, String str2, int expected) {
        assertEquals(expected, EditDistance.minDistance(str1, str2));
    }
}
