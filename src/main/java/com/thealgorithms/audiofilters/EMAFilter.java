package com.thealgorithms.audiofilters;

import java.util.Arrays;
import java.util.Objects;

/**
 * EMAFilter - Exponential Moving Average filter for smoothing audio signals.
 *
 * Think of this as a "smart smoothing tool" for audio: it looks at each new sample
 * and gently blends it with the previous smoothed value to reduce sudden spikes or noise.
 *
 * The smoothing factor (alpha) determines how responsive the filter is:
 * - High alpha (close to 1) → reacts quickly to new samples (less smoothing)
 * - Low alpha (close to 0) → reacts slowly (more smoothing)
 *
 * This class supports both:
 * 1. Batch processing (arrays of samples)
 * 2. Streaming / real-time processing (sample by sample)
 */
public final class EMAFilter {

    /** How "responsive" the filter is to new data */
    private final double alpha;

    /** Stores the last EMA value for continuous processing */
    private double lastEma;

    /**
     * Constructor: sets the smoothing factor (alpha) for the filter.
     *
     * @param alpha smoothing factor between 0 (exclusive) and 1 (inclusive)
     * @throws IllegalArgumentException if alpha is invalid
     */
    public EMAFilter(double alpha) {
        if (alpha <= 0.0 || alpha > 1.0) {
            throw new IllegalArgumentException("Alpha must be between 0 (exclusive) and 1 (inclusive). Got: " + alpha);
        }
        this.alpha = alpha;
        this.lastEma = Double.NaN; // Indicates that no sample has been processed yet
    }

    /**
     * Smooths a whole array of audio samples and returns a new array.
     *
     * Original samples remain unchanged.
     *
     * @param samples input audio samples
     * @return new array with smoothed samples
     */
    public double[] apply(double[] samples) {
        Objects.requireNonNull(samples, "Input samples cannot be null.");
        if (samples.length == 0) return new double[0];

        double[] smoothed = new double[samples.length];
        double ema = samples[0]; // Start with the first sample
        smoothed[0] = ema;

        for (int i = 1; i < samples.length; i++) {
            // EMA formula: current = alpha * newSample + (1 - alpha) * previousEMA
            ema = alpha * samples[i] + (1 - alpha) * ema;
            smoothed[i] = ema;
        }

        lastEma = ema; // Save last EMA for streaming
        return smoothed;
    }

    /**
     * Smooths the array **in-place** to save memory.
     *
     * Useful for large audio arrays or memory-sensitive applications.
     *
     * @param samples array to be smoothed (will be overwritten)
     */
    public void applyInPlace(double[] samples) {
        Objects.requireNonNull(samples, "Input samples cannot be null.");
        if (samples.length == 0) return;

        double ema = samples[0];
        for (int i = 1; i < samples.length; i++) {
            ema = alpha * samples[i] + (1 - alpha) * ema;
            samples[i] = ema; // overwrite original array
        }

        lastEma = ema;
    }

    /**
     * Returns the last EMA value computed.
     *
     * Useful for streaming or continuous processing.
     *
     * @return last EMA value, or NaN if filter hasn't processed any data yet
     */
    public double getLastEma() {
        return lastEma;
    }

    /**
     * Updates the EMA with a single new sample (streaming / real-time processing).
     *
     * @param sample next input value
     * @return updated EMA value
     */
    public double next(double sample) {
        if (Double.isNaN(lastEma)) {
            lastEma = sample; // Initialize with first sample
        } else {
            lastEma = alpha * sample + (1 - alpha) * lastEma;
        }
        return lastEma;
    }

    @Override
    public String toString() {
        return "EMAFilter{alpha=" + alpha + ", lastEma=" + lastEma + "}";
    }
}
