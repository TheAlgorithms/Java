package com.thealgorithms.misc;

final public class MedianOfRunningArrayLong extends MedianOfRunningArray<Long> {
    @Override
    public Long calculateAverage(final Long a, final Long b) {
        return (a + b) / 2L;
    }
}
