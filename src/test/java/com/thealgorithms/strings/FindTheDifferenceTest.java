package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindTheDifferenceTest {

    @Test
    public void testExample() {
        assertEquals('e', FindTheDifference.findTheDifference("abcd", "abcde"));
    }

    @Test
    public void testEmptyS() {
        assertEquals('a', FindTheDifference.findTheDifference("", "a"));
    }

    @Test
    public void testSameCharAdded() {
        assertEquals('a', FindTheDifference.findTheDifference("a", "aa"));
    }

    @Test
    public void testShuffled() {
        assertEquals('y', FindTheDifference.findTheDifference("ae", "eay"));
    }
}
