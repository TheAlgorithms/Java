package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MapReduceTest {
    @ParameterizedTest
    @CsvSource({"'hello world', 'hello: 1,world: 1'", "'one one two', 'one: 2,two: 1'", "'a a a a', 'a: 4'", "'  spaced  out  ', 'spaced: 1,out: 1'"})
    void testCountWordFrequencies(String input, String expected) {
        String result = MapReduce.countWordFrequencies(input);
        assertEquals(expected, result);
    }
}
