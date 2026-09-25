package com.thealgorithms.streaming;

/**
 * <b>CUSUM</b> (cumulative sum) change detection: it notices that the level of a stream has shifted,
 * long before the shift is visible in a moving average.
 *
 * <p>A threshold on the raw samples can only catch a change that is large compared with the noise.
 * CUSUM instead accumulates evidence. Every sample is normalised into
 * {@code z = (x - target) / sigma} and pushed into two one-sided sums:
 *
 * <pre>
 * upper &lt;- max(0, upper + z - allowance)
 * lower &lt;- max(0, lower - z - allowance)
 * </pre>
 *
 * <p>The <i>allowance</i> {@code k} is a toll paid on every step. While the stream sits at its
 * target the toll exceeds the average evidence, both sums are pinned at zero and the detector stays
 * quiet no matter how long it runs. As soon as the mean shifts by more than {@code k} standard
 * deviations, the corresponding sum starts drifting upwards, and it keeps drifting: a small but
 * persistent bias accumulates until it crosses the decision threshold {@code h}. That is the whole
 * point of the method - a shift of half a standard deviation is invisible in any single sample, yet
 * unmistakable after twenty of them.
 *
 * <p>The classic tuning is {@code k = delta / 2} for the shift size {@code delta} one wants to catch
 * quickly, together with {@code h} between 4 and 5, which keeps false alarms rare while detecting a
 * one sigma shift within roughly ten samples. Both sums are cleared whenever an alarm fires, so the
 * detector immediately starts looking for the next change instead of latching.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * // Watch for a shift of one standard deviation around a target of 20.0.
 * CusumDetector detector = new CusumDetector(20.0, 0.5, 0.5, 5.0);
 * for (double sample : stream) {
 *     ShiftSignal signal = detector.accept(sample);
 *     if (signal.isAlarm()) {
 *         alert(signal, detector.count());
 *     }
 * }
 * }</pre>
 *
 * <p>Each sample costs O(1) time and the detector keeps O(1) state. This class is not thread-safe.
 *
 * @see <a href="https://en.wikipedia.org/wiki/CUSUM">CUSUM</a>
 */
public final class CusumDetector {

    /** Allowance used when none is given; it targets shifts of one standard deviation. */
    public static final double DEFAULT_ALLOWANCE = 0.5;

    /** Decision threshold used when none is given. */
    public static final double DEFAULT_THRESHOLD = 5.0;

    private final double target;
    private final double standardDeviation;
    private final double allowance;
    private final double threshold;

    private double upperSum;
    private double lowerSum;
    private long count;
    private long alarmCount;
    private ShiftSignal lastSignal = ShiftSignal.NONE;

    /**
     * Creates a detector tuned for shifts of about one standard deviation.
     *
     * @param target the level the stream is expected to sit at
     * @param standardDeviation the noise level of the stream, strictly positive
     * @throws IllegalArgumentException if {@code target} is not finite or {@code standardDeviation} is not strictly positive
     */
    public CusumDetector(double target, double standardDeviation) {
        this(target, standardDeviation, DEFAULT_ALLOWANCE, DEFAULT_THRESHOLD);
    }

    /**
     * Creates a detector.
     *
     * @param target the level the stream is expected to sit at
     * @param standardDeviation the noise level of the stream, strictly positive
     * @param allowance the toll subtracted on every step, in standard deviations; half of the shift
     *     size one wants to detect quickly
     * @param threshold how much accumulated evidence raises an alarm, in standard deviations
     * @throws IllegalArgumentException if any argument is not finite, if {@code standardDeviation} or
     *     {@code threshold} is not strictly positive, or if {@code allowance} is negative
     */
    public CusumDetector(double target, double standardDeviation, double allowance, double threshold) {
        requireFinite(target, "target");
        if (!(standardDeviation > 0.0) || !Double.isFinite(standardDeviation)) {
            throw new IllegalArgumentException("The standard deviation must be finite and strictly positive, but was " + standardDeviation);
        }
        if (!(allowance >= 0.0) || !Double.isFinite(allowance)) {
            throw new IllegalArgumentException("The allowance must be finite and non-negative, but was " + allowance);
        }
        if (!(threshold > 0.0) || !Double.isFinite(threshold)) {
            throw new IllegalArgumentException("The threshold must be finite and strictly positive, but was " + threshold);
        }
        this.target = target;
        this.standardDeviation = standardDeviation;
        this.allowance = allowance;
        this.threshold = threshold;
    }

    /**
     * Feeds one sample into the detector.
     *
     * @param value the incoming sample
     * @return {@link ShiftSignal#NONE} while the stream stays in control, otherwise the direction of
     *     the detected shift; the accumulated sums are cleared on an alarm
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public ShiftSignal accept(double value) {
        requireFinite(value, "sample");
        count++;

        double normalized = (value - target) / standardDeviation;
        upperSum = Math.max(0.0, upperSum + normalized - allowance);
        lowerSum = Math.max(0.0, lowerSum - normalized - allowance);

        if (upperSum > threshold) {
            lastSignal = ShiftSignal.UPWARD;
        } else if (lowerSum > threshold) {
            lastSignal = ShiftSignal.DOWNWARD;
        } else {
            lastSignal = ShiftSignal.NONE;
        }

        if (lastSignal.isAlarm()) {
            alarmCount++;
            upperSum = 0.0;
            lowerSum = 0.0;
        }
        return lastSignal;
    }

    /**
     * Runs the detector over a whole signal.
     *
     * @param signal the samples to inspect
     * @return a new array of the same length holding the verdict for every sample
     * @throws IllegalArgumentException if any sample is NaN or infinite
     * @throws NullPointerException if {@code signal} is {@code null}
     */
    public ShiftSignal[] scan(double[] signal) {
        ShiftSignal[] signals = new ShiftSignal[signal.length];
        for (int i = 0; i < signal.length; i++) {
            signals[i] = accept(signal[i]);
        }
        return signals;
    }

    /**
     * Returns the evidence accumulated in favour of an upward shift.
     *
     * @return the one-sided upper sum, never negative
     */
    public double upperSum() {
        return upperSum;
    }

    /**
     * Returns the evidence accumulated in favour of a downward shift.
     *
     * @return the one-sided lower sum, never negative
     */
    public double lowerSum() {
        return lowerSum;
    }

    /**
     * Returns the verdict on the most recent sample.
     *
     * @return the last signal, {@link ShiftSignal#NONE} before the first sample
     */
    public ShiftSignal lastSignal() {
        return lastSignal;
    }

    /**
     * Returns how many samples have been inspected since the last reset.
     *
     * @return the sample count
     */
    public long count() {
        return count;
    }

    /**
     * Returns how many alarms have been raised since the last reset.
     *
     * @return the alarm count
     */
    public long alarmCount() {
        return alarmCount;
    }

    /**
     * Returns the expected level of the stream.
     *
     * @return the target given at construction time
     */
    public double target() {
        return target;
    }

    /**
     * Returns the assumed noise level.
     *
     * @return the standard deviation given at construction time
     */
    public double standardDeviation() {
        return standardDeviation;
    }

    /**
     * Returns the per-step allowance.
     *
     * @return the allowance given at construction time
     */
    public double allowance() {
        return allowance;
    }

    /**
     * Returns the decision threshold.
     *
     * @return the threshold given at construction time
     */
    public double threshold() {
        return threshold;
    }

    /**
     * Clears the accumulated evidence and the counters.
     */
    public void reset() {
        upperSum = 0.0;
        lowerSum = 0.0;
        count = 0;
        alarmCount = 0;
        lastSignal = ShiftSignal.NONE;
    }

    @Override
    public String toString() {
        return "CusumDetector{target=" + target + ", upperSum=" + upperSum + ", lowerSum=" + lowerSum + ", alarms=" + alarmCount + '}';
    }

    private static void requireFinite(double value, String name) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("The " + name + " must be finite, but was " + value);
        }
    }
}
