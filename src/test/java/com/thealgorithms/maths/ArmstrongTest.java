package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Vivek
 * @since 15/03/22
 */
class ArmstrongTest {

    @Test
    void testisArmStrong() {
        Armstrong armstrong = new Armstrong();
        assertThat(armstrong.isArmStrong(153)).isTrue();
        assertThat(armstrong.isArmStrong(0)).isTrue();
        assertThat(armstrong.isArmStrong(1)).isTrue();
        assertThat(armstrong.isArmStrong(1634)).isFalse(); //this proves wrong v1.
        assertThat(armstrong.isArmStrong(371)).isTrue();
        assertThat(armstrong.isArmStrong(200)).isFalse();
    }
}