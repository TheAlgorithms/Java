package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BoothsAlgorithmTest {

    @Test
    public void testMinimalRotationIndexForExample() {
        assertEquals(3, BoothsAlgorithm.minimalRotationIndex("baca"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, BoothsAlgorithm.minimalRotationIndex(""));
    }

    @Test
    public void testSingleCharacter() {
        assertEquals(0, BoothsAlgorithm.minimalRotationIndex("x"));
    }

    @Test
    public void testRepeatedCharacters() {
        assertEquals(0, BoothsAlgorithm.minimalRotationIndex("aaaaa"));
    }

    @Test
    public void testPeriodicString() {
        assertEquals(1, BoothsAlgorithm.minimalRotationIndex("baba"));
        assertEquals(1, BoothsAlgorithm.minimalRotationIndex("cabcab"));
    }

    @Test
    public void testStringAlreadyHasMinimalRotationAtStart() {
        assertEquals(0, BoothsAlgorithm.minimalRotationIndex("abc"));
    }

    @Test
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> BoothsAlgorithm.minimalRotationIndex(null));
    }
}
