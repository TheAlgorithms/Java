package com.thealgorithms.maths;

/**
 * Checksum implementation for data validation.
 * Should calculate an 8 bit checksum of a given byte array
 */
public final class Checksum {

    private Checksum() { //priv constructor
    }

//Calc an 8 bit checksum for the specified input data.
    public static int calculateChecksum(byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        int checksum = 0;
        for (byte b : data) {
            checksum = (checksum + b) & 0xFF;
        }
        return checksum;
    }
}