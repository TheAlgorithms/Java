package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParityCheckTest {
    @Test
    public void testIsOddParity() {
        assertTrue(ParityCheck.checkParity(5)); // 101 has 2 ones (even parity)
        assertFalse(ParityCheck.checkParity(7)); // 111 has 3 ones (odd parity)
        assertFalse(ParityCheck.checkParity(8)); // 1000 has 1 one (odd parity)
    }
}
