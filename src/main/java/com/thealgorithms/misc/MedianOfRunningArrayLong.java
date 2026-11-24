package com.thealgorithms.misc;

import java.math.BigInteger;

public final class MedianOfRunningArrayLong extends MedianOfRunningArray<Long> {
    @Override
    public Long calculateAverage(final Long a, final Long b) {
        // a + b overflows a long near its bounds and there is no wider primitive to widen to.
        return BigInteger.valueOf(a).add(BigInteger.valueOf(b)).divide(BigInteger.TWO).longValueExact();
    }
}
