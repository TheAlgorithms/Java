package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Vivek
 * @since 15/03/22
 */
class ArmstrongTest {

    Armstrong armstrong = new Armstrong();

    @Test
    void testIsArmStrongV1() {
        assertThat(armstrong.isArmStrongV1(153)).isTrue();
        assertThat(armstrong.isArmStrongV1(0)).isTrue();
        assertThat(armstrong.isArmStrongV1(1)).isTrue();
        assertThat(armstrong.isArmStrongV1(1634)).isTrue();
        assertThat(armstrong.isArmStrongV1(371)).isTrue();
        assertThat(armstrong.isArmStrongV1(200)).isFalse();
    }

    @Test
    void testIsArmStrongV2() {
        assertThat(armstrong.isArmStrongV2(153)).isTrue();
        assertThat(armstrong.isArmStrongV2(0)).isTrue();
        assertThat(armstrong.isArmStrongV2(1)).isTrue();
        assertThat(armstrong.isArmStrongV2(1634)).isFalse(); //this proves wrong v1.
        assertThat(armstrong.isArmStrongV2(371)).isTrue();
        assertThat(armstrong.isArmStrongV2(200)).isFalse();
    }
}