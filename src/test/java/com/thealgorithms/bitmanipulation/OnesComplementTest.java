package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

/**
 * Test case for Highest Set Bit
 * @author Abhinay Verma(https://github.com/Monk-AbhinayVerma)
 */
public class OnesComplementTest {

    @Test
    public void testOnesComplementAllZeroes() {

        // Test cases with all-zero binary strings
        assertEquals("1111", OnesComplement.onesComplement("0000"));
        assertEquals("111", OnesComplement.onesComplement("000"));
        assertEquals("11", OnesComplement.onesComplement("00"));
        assertEquals("1", OnesComplement.onesComplement("0"));
    }

    @Test
    public void testOnesComplementAllOnes() {
        // Test cases with all-one binary strings
        assertEquals("0000", OnesComplement.onesComplement("1111"));
        assertEquals("000", OnesComplement.onesComplement("111"));
        assertEquals("00", OnesComplement.onesComplement("11"));
        assertEquals("0", OnesComplement.onesComplement("1"));
    }

    @Test
    public void testOnesComplementMixedBits() {
        // Test more mixed binary patterns
        assertEquals("1010", OnesComplement.onesComplement("0101"));
        assertEquals("0101", OnesComplement.onesComplement("1010"));
        assertEquals("1100", OnesComplement.onesComplement("0011"));
        assertEquals("0011", OnesComplement.onesComplement("1100"));
        assertEquals("1001", OnesComplement.onesComplement("0110"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testOnesComplementNullOrEmptyInputThrowsException(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> OnesComplement.onesComplement(input));
        assertEquals("Input must be a non-empty binary string.", exception.getMessage());
    }

    @Test
    public void testOnesComplementInvalidCharactersThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OnesComplement.onesComplement("10a1"));
        assertTrue(exception.getMessage().startsWith("Input must contain only '0' and '1'"));
    }
}
