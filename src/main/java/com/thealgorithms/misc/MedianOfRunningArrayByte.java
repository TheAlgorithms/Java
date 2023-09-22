package com.thealgorithms.misc;

final public class MedianOfRunningArrayByte extends MedianOfRunningArray<Byte> {
    @Override
    public Byte calculateAverage(final Byte a, final Byte b) {
        return (byte) ((a + b) / 2);
    }
}
