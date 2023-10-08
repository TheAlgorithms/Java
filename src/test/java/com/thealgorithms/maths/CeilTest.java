package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CeilTest {

    @Test
    void testCeil() {
        assertEquals(8, Ceil.ceil(7.057));
        assertEquals(8, Ceil.ceil(7.004));
        assertEquals(-13, Ceil.ceil(-13.004));
        assertEquals(1, Ceil.ceil(.98));
        assertEquals(-11, Ceil.ceil(-11.357));
    }
}
