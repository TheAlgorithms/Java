package com.thealgorithms.maths;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author madhavagarwal3012
 * @since 8/10/2023
 */
// ArmstrongTest.java
import org.junit.Test;
import static org.junit.Assert.*;

public class ArmstrongTest {
    @Test
    public void testArmstrong() {
        Armstrong armstrong = new Armstrong();
        assertTrue(armstrong.checkArmstrong(1634));
        assertFalse(armstrong.checkArmstrong(1234));
    }
}

