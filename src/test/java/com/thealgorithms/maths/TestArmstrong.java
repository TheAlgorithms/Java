package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestArmstrong {

    @Test
    public void testArmstrong() {
        Armstrong armstrong = new Armstrong();
        assertThat(armstrong.isArmstrong(371)).isTrue();
        assertThat(armstrong.isArmstrong(200)).isFalse();
    }
}
