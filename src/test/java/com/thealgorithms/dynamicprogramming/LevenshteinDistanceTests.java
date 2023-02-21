package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevenshteinDistanceTests {

    @ParameterizedTest
    @CsvSource({"dog,cat,3", "sunday,saturday,3", "cat,cats,1", "rain,train,1"})
    void levenshteinDistanceTest(String str1, String str2, int distance) {
        int result = LevenshteinDistance.calculateLevenshteinDistance(str1, str2);
        assertEquals(distance, result);
    }

}
