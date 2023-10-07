package com.thealgorithms.bitmanipulation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinValueTest {
  @Test
    public void flipBitTest() {
        assertEquals(-1, MinValue.min(-1, 3));
        assertEquals(2, MinValue.min(3, 2));
    }
}
