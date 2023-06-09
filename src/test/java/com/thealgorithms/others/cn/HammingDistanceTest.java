package com.thealgorithms.others.cn;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableTypeAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HammingDistanceTest {

    HammingDistance hd;

    @BeforeEach
    void initialize() {
        hd = new HammingDistance();
    }

    @Test
    public void checkForDifferentBits() {
        String senderBits = "000", receiverBits = "011";
        int answer = hd.getHammingDistanceBetweenBits(senderBits, receiverBits);
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
        String senderBits = "10101", receiverBits = "11110";
        int answer = hd.getHammingDistanceBetweenBits(senderBits, receiverBits);
        Assertions.assertThat(answer).isEqualTo(3);
    }

    @Test
    public void checkForSameBits() {
        String senderBits = "111", receiverBits = "111";
        int answer = hd.getHammingDistanceBetweenBits(senderBits, receiverBits);
        Assertions.assertThat(answer).isEqualTo(0);
    }

    @Test
    public void checkForLongDataBits() {
        String senderBits = "10010101101010000100110100", receiverBits = "00110100001011001100110101";
        int answer = hd.getHammingDistanceBetweenBits(senderBits, receiverBits);
        Assertions.assertThat(answer).isEqualTo(7);
    }

    @Test
    public void mismatchDataBits() {
        String senderBits = "100010", receiverBits = "00011";

        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> { int answer = hd.getHammingDistanceBetweenBits(senderBits, receiverBits); });

        Assertions.assertThat(ex.getMessage()).contains("bits should be same");
    }

    @Test
    public void checkForLongDataBitsSame() {
        String senderBits = "10010101101010000100110100", receiverBits = "10010101101010000100110100";
        int answer = hd.getHammingDistanceBetweenBits(senderBits, receiverBits);
        Assertions.assertThat(answer).isEqualTo(0);
    }
}
