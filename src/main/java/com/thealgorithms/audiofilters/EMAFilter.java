package com.thealgorithms.audiofilters;

/**
 * EMA Filter for audio signal smoothing.
 * Formula: EMA[i] = alpha * signal[i] + (1-alpha) * EMA[i-1]
 */
public class EMAFilter {
    private final double alpha;
    private double ema;

    public EMAFilter(double alpha) {
        if (alpha <= 0 || alpha > 1) throw new IllegalArgumentException("Alpha must be in (0,1]");
        this.alpha = alpha;
    }

    public double[] apply(double[] signal) {
        if (signal.length == 0) return new double[0];
        
        double[] result = new double[signal.length];
        ema = result[0] = signal[0];
        
        for (int i = 1; i < signal.length; i++) {
            ema = alpha * signal[i] + (1 - alpha) * ema;
            result[i] = ema;
        }
        return result;
    }
}
