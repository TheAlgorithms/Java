package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DigitalRootTest {

    @Test
    void testDigitalroot() {
        assertEquals(4, DigitalRoot.digitalRoot(4));
        assertEquals(9, DigitalRoot.digitalRoot(9));
        assertEquals(4, DigitalRoot.digitalRoot(49));
        assertEquals(6, DigitalRoot.digitalRoot(78));
        assertEquals(4, DigitalRoot.digitalRoot(1228));
        assertEquals(5, DigitalRoot.digitalRoot(71348));
    }
}
