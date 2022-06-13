package com.thealgorithms.maths;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ArmstrongTest {
    ArmstrongTest() {
    }

    @Test
    void testIsArmstrong() {
        Armstrong armstrong = new Armstrong();
        Assertions.assertThat(armstrong.isArmstrong(0)).isTrue();
        Assertions.assertThat(armstrong.isArmstrong(1)).isTrue();
        Assertions.assertThat(armstrong.isArmstrong(153)).isTrue();
        Assertions.assertThat(armstrong.isArmstrong(371)).isTrue();
        Assertions.assertThat(armstrong.isArmstrong(1634)).isFalse();
        Assertions.assertThat(armstrong.isArmstrong(200)).isFalse();
    }
}
