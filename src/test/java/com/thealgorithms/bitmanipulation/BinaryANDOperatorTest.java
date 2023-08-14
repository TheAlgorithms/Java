package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test Cases for Binary AND Operator
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

class BinaryANDOperatorTest {
    @Test
    void BinaryAND() {
        assertEquals("0b1000", BinaryANDOperator.binaryAnd(10, 12));
        assertEquals("0b000010", BinaryANDOperator.binaryAnd(34, 22));
        assertEquals("0b00", BinaryANDOperator.binaryAnd(0, 2));
    }
}