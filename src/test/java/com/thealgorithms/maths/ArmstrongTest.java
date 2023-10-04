package com.thealgorithms.maths;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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
        assertThat(armstrong.isArmstrong(2)).isTrue();
        assertThat(armstrong.isArmstrong(3)).isTrue();
        assertThat(armstrong.isArmstrong(4)).isTrue();
        assertThat(armstrong.isArmstrong(5)).isTrue();
        assertThat(armstrong.isArmstrong(6)).isTrue();
        assertThat(armstrong.isArmstrong(7)).isTrue();
        assertThat(armstrong.isArmstrong(8)).isTrue();
        assertThat(armstrong.isArmstrong(9)).isTrue();
        assertThat(armstrong.isArmstrong(153)).isTrue();
        assertThat(armstrong.isArmstrong(370)).isTrue();
        assertThat(armstrong.isArmstrong(371)).isTrue();
        assertThat(armstrong.isArmstrong(407)).isTrue();
        assertThat(armstrong.isArmstrong(1634)).isTrue();
        assertThat(armstrong.isArmstrong(8208)).isTrue();
        assertThat(armstrong.isArmstrong(9474)).isTrue();
        assertThat(armstrong.isArmstrong(54748)).isTrue();
        assertThat(armstrong.isArmstrong(92727)).isTrue();
        assertThat(armstrong.isArmstrong(93084)).isTrue();
        assertThat(armstrong.isArmstrong(548834)).isTrue();
        assertThat(armstrong.isArmstrong(1741725)).isTrue();
        assertThat(armstrong.isArmstrong(4210818)).isTrue();
        assertThat(armstrong.isArmstrong(9800817)).isTrue();
        assertThat(armstrong.isArmstrong(9926315)).isTrue();
        assertThat(armstrong.isArmstrong(24678050)).isTrue();
        assertThat(armstrong.isArmstrong(24678051)).isTrue();
        assertThat(armstrong.isArmstrong(88593477)).isTrue();
        assertThat(armstrong.isArmstrong(146511208)).isTrue();
        assertThat(armstrong.isArmstrong(472335975)).isTrue();
        assertThat(armstrong.isArmstrong(534494836)).isTrue();
        assertThat(armstrong.isArmstrong(912985153)).isTrue();
        assertThat(armstrong.isArmstrong(200)).isFalse();
    }
}
