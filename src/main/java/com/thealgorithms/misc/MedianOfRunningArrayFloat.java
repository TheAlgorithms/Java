package com.thealgorithms.misc;

import java.text.DecimalFormat;

final public class MedianOfRunningArrayFloat extends MedianOfRunningArray<Float> {
	public static final DecimalFormat decfor = new DecimalFormat("0.00");
    @Override
    public Float calculateAverage(final Float a, final Float b) {
        return Float.parseFloat(decfor.format((a + b) / 2f));
    }
}
