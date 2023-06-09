package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DudeneyNumberTest {

    @Test
    void isDudeney() {
        final int validDudeneyNumber = 512;
        final int invalidDudeneyNumber = 125;

        assertTrue(() -> DudeneyNumber.isDudeney(validDudeneyNumber));
        assertFalse(() -> DudeneyNumber.isDudeney(invalidDudeneyNumber));
    }
}