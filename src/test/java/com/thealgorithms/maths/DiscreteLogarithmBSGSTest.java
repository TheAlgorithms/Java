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
        // choose a true NO-SOLUTION example:
        // modulo 15, base 4 generates only {1,4}
        assertEquals(-1, DiscreteLogarithmBSGS.discreteLog(4, 2, 15));
    }

    @Test
    public void testLargeMod() {
        // use a valid solvable case
        long x = DiscreteLogarithmBSGS.discreteLog(5, 5, 1000003);
        assertEquals(1, x);
    }

    @Test
    public void testImmediateReturnCase() {
        // because a^0 ≡ 1 (mod m)
        assertEquals(0, DiscreteLogarithmBSGS.discreteLog(7, 1, 13));
    }

    @Test
    public void testModPowBranchCoverage() {
        assertEquals(1, DiscreteLogarithmBSGS.modPow(10, 0, 17)); // exp=0 branch
        assertEquals(10 % 17, DiscreteLogarithmBSGS.modPow(10, 1, 17)); // odd exp branch
        assertEquals(100 % 17, DiscreteLogarithmBSGS.modPow(10, 2, 17)); // even exp branch
    }

}
