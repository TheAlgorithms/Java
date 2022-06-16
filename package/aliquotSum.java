package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotSumTest {
    public AliquotSumTest() {
    }

    @Test
    void testGetMaxValue() {
        Assertions.assertEquals(0, AliquotSum.getAliquotValue(1));
        Assertions.assertEquals(6, AliquotSum.getAliquotValue(6));
        Assertions.assertEquals(9, AliquotSum.getAliquotValue(15));
        Assertions.assertEquals(1, AliquotSum.getAliquotValue(19));
    }
}
