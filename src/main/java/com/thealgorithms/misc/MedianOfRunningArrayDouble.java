package com.thealgorithms.misc;

public final class MedianOfRunningArrayDouble extends MedianOfRunningArray<Double> {
    @Override
    public Double calculateAverage(final Double a, final Double b) {
        return (a + b) / 2.0d;
    }
}
