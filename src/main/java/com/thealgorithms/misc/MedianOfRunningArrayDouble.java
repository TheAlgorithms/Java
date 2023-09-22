package com.thealgorithms.misc;

import java.text.DecimalFormat;

final public class MedianOfRunningArrayDouble extends MedianOfRunningArray<Double> {
	public static final DecimalFormat decfor=new DecimalFormat("0.00");
    @Override
    public Double calculateAverage(final Double a, final Double b) {
        return Double.parseDouble(decfor.format((a + b) / 2.0d));
    }
}
