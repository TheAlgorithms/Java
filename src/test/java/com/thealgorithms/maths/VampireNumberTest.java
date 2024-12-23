package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static com.thealgorithms.maths.VampireNumber.isVampireNumber;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VampireNumberTest {
    @Test
    void areVampireNumbers() {
        assertTrue(isVampireNumber(15, 93, true));
        assertTrue(isVampireNumber(135, 801, true));
        assertTrue(isVampireNumber(201, 600, true));
    }

    @Test
    void arePseudoVampireNumbers() {
        assertTrue(isVampireNumber(150, 93, false));
        assertTrue(isVampireNumber(546, 84, false));
        assertTrue(isVampireNumber(641, 65, false));
    }

    @Test
    void areNotVampireNumbers() {
        assertFalse(isVampireNumber(51, 39, false));
        assertFalse(isVampireNumber(51, 39, true));
    }
}
