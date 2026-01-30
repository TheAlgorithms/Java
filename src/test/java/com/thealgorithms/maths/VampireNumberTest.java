package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VampireNumberTest {
    @Test
    void areVampireNumbers() {
        Assertions.assertTrue(VampireNumber.isVampireNumber(15, 93, true));
        Assertions.assertTrue(VampireNumber.isVampireNumber(135, 801, true));
        Assertions.assertTrue(VampireNumber.isVampireNumber(201, 600, true));
    }

    @Test
    void arePseudoVampireNumbers() {
        Assertions.assertTrue(VampireNumber.isVampireNumber(150, 93, false));
        Assertions.assertTrue(VampireNumber.isVampireNumber(546, 84, false));
        Assertions.assertTrue(VampireNumber.isVampireNumber(641, 65, false));
    }

    @Test
    void areNotVampireNumbers() {
        Assertions.assertFalse(VampireNumber.isVampireNumber(51, 39, false));
        Assertions.assertFalse(VampireNumber.isVampireNumber(51, 39, true));
    }

    @Test
    void testSplitIntoSortedDigits() {
        Assertions.assertEquals("123", VampireNumber.splitIntoSortedDigits(321));
        Assertions.assertEquals("02234", VampireNumber.splitIntoSortedDigits(20, 324));
    }
}
