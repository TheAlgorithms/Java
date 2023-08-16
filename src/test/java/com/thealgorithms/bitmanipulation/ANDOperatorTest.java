package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ANDOperatorTest {

    @Test
    void andOperator() {
        assertEquals(8, ANDOperator.andOperator(10, 12));
        assertEquals(0, ANDOperator.andOperator(3, 8));
        assertEquals(18, ANDOperator.andOperator(22, 27));
        assertEquals(0, ANDOperator.andOperator(-3, 2));
    }
}