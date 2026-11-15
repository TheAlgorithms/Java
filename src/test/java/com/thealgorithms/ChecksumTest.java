package com.thealgorithms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.thealgorithms.maths.Checksum;

public class ChecksumTest {

    @Test
    public void testCalculateChecksumNormal() {
        byte[] data = {1, 2, 3, 4, 5};
        int result = Checksum.calculateChecksum(data);
        assertThat(result).isEqualTo(15);
    }

    @Test
    public void testCalculateChecksumEmpty() {
        byte[] data = {};
        int result = Checksum.calculateChecksum(data);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCalculateChecksumNull() {
        assertThatThrownBy(() -> Checksum.calculateChecksum(null))
            .isInstanceOf(IllegalArgumentException.class);
    }



    @Test
    public void testCalculateChecksumSingleByte() {
        byte[] data = {100};
        int result = Checksum.calculateChecksum(data);
        assertThat(result).isEqualTo(100);
    }

    @Test
    public void testCalculateChecksumMaxByteValue() {
        // Tests handling of high byte values close to 255
        byte[] data = {(byte) 255};
        int result = Checksum.calculateChecksum(data);
        assertThat(result).isEqualTo(255);
    }
}