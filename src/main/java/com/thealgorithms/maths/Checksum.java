package com.thealgorithms.maths;

/**
 * A simple Checksum implementation for data validation.
 * Calculates an 8-bit checksum of a given byte array.
 */
public final class Checksum {

    private Checksum() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates an 8-bit checksum for the specified input data.
     *
     * @param data the byte array to checksum
     * @return the 8-bit checksum value as an integer
     */
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