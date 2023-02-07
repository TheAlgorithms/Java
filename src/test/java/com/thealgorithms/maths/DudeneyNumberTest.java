package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DudeneyNumberTest {

    @Test
    void isDudeney() {
        final int validDudeneyNumber = 512;
        final int invalidDudeneyNumber = 125;

        assertTrue(() -> DudeneyNumber.isDudeney(validDudeneyNumber));
        assertFalse(() -> DudeneyNumber.isDudeney(invalidDudeneyNumber));

    }
}