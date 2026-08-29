package com.thealgorithms.maths;
// author: Vraj Prajapati @Rosander0

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PerrinNumberTest {

    @Test
    public void testBaseCases() {
        assertEquals(3, PerrinNumber.perrin(0));
        assertEquals(0, PerrinNumber.perrin(1));
        assertEquals(2, PerrinNumber.perrin(2));
    }

    @Test
    public void testKnownValues() {
        assertEquals(3, PerrinNumber.perrin(3));
        assertEquals(2, PerrinNumber.perrin(4));
        assertEquals(5, PerrinNumber.perrin(5));
        assertEquals(5, PerrinNumber.perrin(6));
        assertEquals(7, PerrinNumber.perrin(7));
        assertEquals(10, PerrinNumber.perrin(8));
        assertEquals(12, PerrinNumber.perrin(9));
        assertEquals(17, PerrinNumber.perrin(10));
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> PerrinNumber.perrin(-1));
    }
}
