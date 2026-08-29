package com.thealgorithms.maths;
// author: Vraj Prajapati @Rosander0

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class JacobsthalNumberTest {

    @Test
    public void testBaseCases() {
        assertEquals(0, JacobsthalNumber.jacobsthal(0));
        assertEquals(1, JacobsthalNumber.jacobsthal(1));
    }

    @Test
    public void testKnownValues() {
        assertEquals(1, JacobsthalNumber.jacobsthal(2));
        assertEquals(3, JacobsthalNumber.jacobsthal(3));
        assertEquals(5, JacobsthalNumber.jacobsthal(4));
        assertEquals(11, JacobsthalNumber.jacobsthal(5));
        assertEquals(21, JacobsthalNumber.jacobsthal(6));
        assertEquals(43, JacobsthalNumber.jacobsthal(7));
        assertEquals(85, JacobsthalNumber.jacobsthal(8));
        assertEquals(171, JacobsthalNumber.jacobsthal(9));
        assertEquals(341, JacobsthalNumber.jacobsthal(10));
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> JacobsthalNumber.jacobsthal(-1));
    }
}
