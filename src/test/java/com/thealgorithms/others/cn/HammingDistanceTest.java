package com.thealgorithms.others.cn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HammingDistanceTest {
    @Test
    public void checkForDifferentBits() {
        int answer = HammingDistance.compute("000", "011");
        Assertions.assertThat(answer).isEqualTo(2);
    }

    /*

    1 0 1 0 1
    1 1 1 1 0
    ----------
    0 1 0 1 1


 */
    @Test
    public void checkForDifferentBitsLength() {
        int answer = HammingDistance.compute("10101", "11110");
        Assertions.assertThat(answer).isEqualTo(3);
    }

    @Test
    public void checkForSameBits() {
        String someBits = "111";
        int answer = HammingDistance.compute(someBits, someBits);
        Assertions.assertThat(answer).isEqualTo(0);
    }

    @Test
    public void checkForLongDataBits() {
        int answer = HammingDistance.compute("10010101101010000100110100", "00110100001011001100110101");
        Assertions.assertThat(answer).isEqualTo(7);
    }

    @Test
    public void mismatchDataBits() {
        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> { HammingDistance.compute("100010", "00011"); });

        Assertions.assertThat(ex.getMessage()).contains("must have the same length");
    }

    @Test
    public void mismatchDataBits2() {
        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> { HammingDistance.compute("1", "11"); });

        Assertions.assertThat(ex.getMessage()).contains("must have the same length");
    }

    @Test
    public void checkForLongDataBitsSame() {
        String someBits = "10010101101010000100110100";
        int answer = HammingDistance.compute(someBits, someBits);
        Assertions.assertThat(answer).isEqualTo(0);
    }

    @Test
    public void checkForEmptyInput() {
        String someBits = "";
        int answer = HammingDistance.compute(someBits, someBits);
        Assertions.assertThat(answer).isEqualTo(0);
    }

    @Test
    public void checkForInputOfLength1() {
        String someBits = "0";
        int answer = HammingDistance.compute(someBits, someBits);
        Assertions.assertThat(answer).isEqualTo(0);
    }

    @Test
    public void computeThrowsExceptionWhenInputsAreNotBitStrs() {
        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> { HammingDistance.compute("1A", "11"); });

        Assertions.assertThat(ex.getMessage()).contains("must be a binary string");
    }
}
