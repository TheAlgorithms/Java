package com.thealgorithms.maths;
// author: Vraj Prajapati @Rosander0

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PadovanSequenceTest {

    @Test
    public void testBaseCase() {
        assertEquals(1, PadovanSequence.padovan(0));
        assertEquals(1, PadovanSequence.padovan(1));
        assertEquals(1, PadovanSequence.padovan(2));
    }

    @Test
    public void testKnownValues() {
        assertEquals(2, PadovanSequence.padovan(3));
        assertEquals(2, PadovanSequence.padovan(4));
        assertEquals(3, PadovanSequence.padovan(5));
        assertEquals(4, PadovanSequence.padovan(6));
        assertEquals(5, PadovanSequence.padovan(7));
        assertEquals(7, PadovanSequence.padovan(8));
        assertEquals(9, PadovanSequence.padovan(9));
        assertEquals(12, PadovanSequence.padovan(10));
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class,
            () -> PadovanSequence.padovan(-1));
    }
}
