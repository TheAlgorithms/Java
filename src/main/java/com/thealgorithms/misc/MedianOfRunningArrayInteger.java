package com.thealgorithms.misc;

public final class MedianOfRunningArrayInteger extends MedianOfRunningArray<Integer> {
    @Override
    public Integer calculateAverage(final Integer a, final Integer b) {
        // Widen to long so that summing values near Integer.MAX_VALUE does not overflow.
        final long sum = (long) a + b;
        return (int) (sum / 2);
    }
}
