package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Vivek
 * @since 15/03/22
 */
class ArmstrongTest {

    @Test
    void testIsArmstrong() {
        Armstrong armstrong = new Armstrong();
        assertThat(armstrong.isArmstrong(0)).isTrue();
        assertThat(armstrong.isArmstrong(1)).isTrue();
        assertThat(armstrong.isArmstrong(153)).isTrue();
        assertThat(armstrong.isArmstrong(371)).isTrue();
        assertThat(armstrong.isArmstrong(1634)).isFalse();
        assertThat(armstrong.isArmstrong(200)).isFalse();
    }
}
