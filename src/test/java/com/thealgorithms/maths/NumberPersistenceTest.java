package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberPersistenceTest {

    @ParameterizedTest(name = "multiplicativePersistence({0}) = {1}")
    @CsvSource({"0, 0", "7, 0", "217, 2", "39, 3", "999, 4"})
    @DisplayName("Test multiplicative persistence with valid inputs")
    void testMultiplicativePersistenceValid(int input, int expected) {
        assertEquals(expected, NumberPersistence.multiplicativePersistence(input));
    }

    @ParameterizedTest(name = "multiplicativePersistence({0}) throws IllegalArgumentException")
    @ValueSource(ints = {-1, -100, -9999})
    @DisplayName("Test multiplicative persistence with negative numbers")
    void testMultiplicativePersistenceNegative(int input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NumberPersistence.multiplicativePersistence(input));
        assertEquals("multiplicativePersistence() does not accept negative values", exception.getMessage());
    }

    @ParameterizedTest(name = "additivePersistence({0}) = {1}")
    @CsvSource({"0, 0", "5, 0", "199, 3", "999, 2", "1234, 2"})
    @DisplayName("Test additive persistence with valid inputs")
    void testAdditivePersistenceValid(int input, int expected) {
        assertEquals(expected, NumberPersistence.additivePersistence(input));
    }

    @ParameterizedTest(name = "additivePersistence({0}) throws IllegalArgumentException")
    @ValueSource(ints = {-1, -100, -9999})
    @DisplayName("Test additive persistence with negative numbers")
    void testAdditivePersistenceNegative(int input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NumberPersistence.additivePersistence(input));
        assertEquals("additivePersistence() does not accept negative values", exception.getMessage());
    }
}
