package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AliquotSumTest {

    @Test
    void testGetMaxValue() {
        assertEquals(0, AliquotSum.getAliquotValue(1));
        assertEquals(6, AliquotSum.getAliquotValue(6));
        assertEquals(9, AliquotSum.getAliquotValue(15));
        assertEquals(1, AliquotSum.getAliquotValue(19));
    }
}
