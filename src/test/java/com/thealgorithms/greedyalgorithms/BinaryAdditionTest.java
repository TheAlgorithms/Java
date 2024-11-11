package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryAdditionTest {

    BinaryAddition binaryAddition = new BinaryAddition();

    @Test
    public void testEqualLengthNoCarry() {
        String a = "1010";
        String b = "1101";
        String expected = "10111";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testEqualLengthWithCarry() {
        String a = "1111";
        String b = "1111";
        String expected = "11110";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testDifferentLengths() {
        String a = "101";
        String b = "11";
        String expected = "1000";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testAllZeros() {
        String a = "0";
        String b = "0";
        String expected = "0";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testAllOnes() {
        String a = "1111";
        String b = "1111";
        String expected = "11110";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testOneZeroString() {
        String a = "0";
        String b = "10101";
        String expected = "10101";
        assertEquals(expected, binaryAddition.addBinary(a, b));

        // Test the other way around
        a = "10101";
        b = "0";
        expected = "10101";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testLargeBinaryNumbers() {
        String a = "101010101010101010101010101010";
        String b = "110110110110110110110110110110";
        String expected = "1100001100001100001100001100000";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testOneMuchLonger() {
        String a = "1";
        String b = "11111111";
        String expected = "100000000";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testEmptyStrings() {
        String a = "";
        String b = "";
        String expected = ""; // Adding two empty strings should return 0
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }

    @Test
    public void testAlternatingBits() {
        String a = "10101010";
        String b = "01010101";
        String expected = "11111111";
        assertEquals(expected, binaryAddition.addBinary(a, b));
    }
}
