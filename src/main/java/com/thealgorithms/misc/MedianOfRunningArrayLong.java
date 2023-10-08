package com.thealgorithms.misc;

public final class MedianOfRunningArrayLong extends MedianOfRunningArray<Long> {
    @Override
    public Long calculateAverage(final Long a, final Long b) {
        return (a + b) / 2L;
    }
}
