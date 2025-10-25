package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BinaryToDecimalTest {

    @Test
    // Test converting binary to decimal
    public void testBinaryToDecimal() {
        // zeros at the starting should be removed
        assertEquals(0, BinaryToDecimal.binaryToDecimal(0));
        assertEquals(1, BinaryToDecimal.binaryToDecimal(1));
        assertEquals(5, BinaryToDecimal.binaryToDecimal(101));
        assertEquals(63, BinaryToDecimal.binaryToDecimal(111111));
        assertEquals(512, BinaryToDecimal.binaryToDecimal(1000000000));

        assertEquals(0, BinaryToDecimal.binaryStringToDecimal("0"));
        assertEquals(1, BinaryToDecimal.binaryStringToDecimal("1"));
        assertEquals(5, BinaryToDecimal.binaryStringToDecimal("101"));
        assertEquals(63, BinaryToDecimal.binaryStringToDecimal("111111"));
        assertEquals(512, BinaryToDecimal.binaryStringToDecimal("1000000000"));
    }

    @Test
    // Test converting negative binary numbers
    public void testNegativeBinaryToDecimal() {
        assertEquals(-1, BinaryToDecimal.binaryToDecimal(-1));
        assertEquals(-42, BinaryToDecimal.binaryToDecimal(-101010));

        assertEquals(-1, BinaryToDecimal.binaryStringToDecimal("-1"));
        assertEquals(-42, BinaryToDecimal.binaryStringToDecimal("-101010"));
    }

    @Test
    // Test converting binary numbers with large values
    public void testLargeBinaryToDecimal() {
        assertEquals(262144L, BinaryToDecimal.binaryToDecimal(1000000000000000000L));
        assertEquals(524287L, BinaryToDecimal.binaryToDecimal(1111111111111111111L));

        assertEquals(262144L, BinaryToDecimal.binaryStringToDecimal("1000000000000000000"));
        assertEquals(524287L, BinaryToDecimal.binaryStringToDecimal("1111111111111111111"));
    }

    @ParameterizedTest
    @CsvSource({"2", "1234", "11112", "101021"})
    void testNotCorrectBinaryInput(long binaryNumber) {
        assertThrows(IllegalArgumentException.class, () -> BinaryToDecimal.binaryToDecimal(binaryNumber));

        assertThrows(IllegalArgumentException.class, () -> BinaryToDecimal.binaryStringToDecimal(Long.toString(binaryNumber)));
    }
}
