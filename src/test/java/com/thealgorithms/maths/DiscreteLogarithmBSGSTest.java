package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiscreteLogarithmBSGSTest {

    @Test
    public void testSmallValues() {
        assertEquals(3, DiscreteLogarithmBSGS.discreteLog(2, 8, 13));
        assertEquals(4, DiscreteLogarithmBSGS.discreteLog(3, 81, 1000000007));
    }

    @Test
    public void testNoSolution() {
        assertEquals(-1, DiscreteLogarithmBSGS.discreteLog(10, 5, 17));
    }

    @Test
    public void testLargeMod() {
        long x = DiscreteLogarithmBSGS.discreteLog(5, 243, 1000003);
        assertEquals(5, x);
    }
}
