package com.thealgorithms.streaming;

/**
 * An <b>EWMA control chart</b>: change detection built on the exponentially weighted moving average.
 *
 * <p>The detector smooths the stream with
 * {@link ExponentialMovingAverage} seeded at the target level and compares the smoothed value with a
 * band around that target:
 *
 * <pre>
 * z     &lt;- z + alpha * (x - target)
 * limit  = width * sigma * sqrt( alpha / (2 - alpha) * (1 - (1 - alpha)^(2n)) )
 * </pre>
 *
 * <p>The band is the exact standard deviation of {@code z} under the null hypothesis, multiplied by
 * the desired width in sigmas. It starts narrow and widens towards its asymptote
 * {@code width * sigma * sqrt(alpha / (2 - alpha))}, which keeps the false alarm rate steady during
 * the warm-up instead of letting the first few samples trip the alarm. An alarm fires as soon as the
 * smoothed value leaves the band; the average is then reset to the target so that the detector
 * starts fresh on the next change rather than latching.
 *
 * <p>Where {@link CusumDetector} accumulates evidence without limit and so excels at small,
 * persistent shifts, an EWMA chart looks at a decaying window of the recent past: {@code alpha}
 * around {@code 0.1 - 0.3} is a good compromise, larger values reacting faster to big jumps and
 * smaller ones being more sensitive to slow drifts. A width of 3 sigmas is the customary setting.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * EwmaChangeDetector detector = new EwmaChangeDetector(20.0, 0.5, 0.2, 3.0);
 * for (double sample : stream) {
 *     if (detector.accept(sample).isAlarm()) {
 *         alert(detector.statistic(), detector.controlLimit());
 *     }
 * }
 * }</pre>
 *
 * <p>Each sample costs O(1) time and the detector keeps O(1) state. This class is not thread-safe.
 *
 * @see CusumDetector
 * @see ExponentialMovingAverage
 * @see <a href="https://en.wikipedia.org/wiki/EWMA_chart">EWMA chart</a>
 */
public final class EwmaChangeDetector {

    /** Smoothing factor used when none is given. */
    public static final double DEFAULT_ALPHA = 0.2;

    /** Half-width of the control band, in sigmas, used when none is given. */
    public static final double DEFAULT_WIDTH = 3.0;

    private final double target;
    private final double standardDeviation;
    private final double alpha;
    private final double width;

    private final ExponentialMovingAverage average;
    private long count;
    private long stepsSinceAlarm;
    private long alarmCount;
    private ShiftSignal lastSignal = ShiftSignal.NONE;

    /**
     * Creates a detector with the customary smoothing factor of {@code 0.2} and a band of three
     * sigmas.
     *
     * @param target the level the stream is expected to sit at
     * @param standardDeviation the noise level of the stream, strictly positive
     * @throws IllegalArgumentException if {@code target} is not finite or {@code standardDeviation} is not strictly positive
     */
    public EwmaChangeDetector(double target, double standardDeviation) {
        this(target, standardDeviation, DEFAULT_ALPHA, DEFAULT_WIDTH);
    }

    /**
     * Creates a detector.
     *
     * @param target the level the stream is expected to sit at
     * @param standardDeviation the noise level of the stream, strictly positive
     * @param alpha smoothing factor in {@code (0, 1]}; larger values react faster but tolerate less noise
     * @param width half-width of the control band in sigmas, strictly positive
     * @throws IllegalArgumentException if any argument is not finite, if {@code standardDeviation} or
     *     {@code width} is not strictly positive, or if {@code alpha} is outside {@code (0, 1]}
     */
    public EwmaChangeDetector(double target, double standardDeviation, double alpha, double width) {
        if (!Double.isFinite(target)) {
            throw new IllegalArgumentException("The target must be finite, but was " + target);
        }
        if (!(standardDeviation > 0.0) || !Double.isFinite(standardDeviation)) {
            throw new IllegalArgumentException("The standard deviation must be finite and strictly positive, but was " + standardDeviation);
        }
        if (!(width > 0.0) || !Double.isFinite(width)) {
            throw new IllegalArgumentException("The width must be finite and strictly positive, but was " + width);
        }
        this.target = target;
        this.standardDeviation = standardDeviation;
        this.width = width;
        this.average = ExponentialMovingAverage.ofAlpha(alpha, target);
        this.alpha = this.average.alpha();
    }

    /**
     * Feeds one sample into the detector.
     *
     * @param value the incoming sample
     * @return {@link ShiftSignal#NONE} while the smoothed value stays inside the control band,
     *     otherwise the direction in which it left the band; the average is reset to the target on an alarm
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public ShiftSignal accept(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Samples must be finite, but was " + value);
        }
        count++;
        stepsSinceAlarm++;
        double smoothed = average.add(value);
        double deviation = smoothed - target;
        double limit = controlLimit();

        if (deviation > limit) {
            lastSignal = ShiftSignal.UPWARD;
        } else if (deviation < -limit) {
            lastSignal = ShiftSignal.DOWNWARD;
        } else {
            lastSignal = ShiftSignal.NONE;
        }

        if (lastSignal.isAlarm()) {
            alarmCount++;
            stepsSinceAlarm = 0;
            average.reset();
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
     * Returns the smoothed value the alarm decision is based on.
     *
     * @return the exponentially weighted average of the stream
     */
    public double statistic() {
        return average.value();
    }

    /**
     * Returns the current half-width of the control band, which widens during the warm-up and then
     * settles.
     *
     * @return the distance from the target at which an alarm fires
     */
    public double controlLimit() {
        double asymptotic = alpha / (2.0 - alpha);
        double warmUp = -Math.expm1(2.0 * stepsSinceAlarm * Math.log1p(-alpha));
        return width * standardDeviation * Math.sqrt(asymptotic * warmUp);
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
     * Returns the smoothing factor in use.
     *
     * @return alpha
     */
    public double alpha() {
        return alpha;
    }

    /**
     * Returns the configured band half-width.
     *
     * @return the width in sigmas given at construction time
     */
    public double width() {
        return width;
    }

    /**
     * Returns the average to the target and clears the counters.
     */
    public void reset() {
        average.reset();
        count = 0;
        stepsSinceAlarm = 0;
        alarmCount = 0;
        lastSignal = ShiftSignal.NONE;
    }

    @Override
    public String toString() {
        return "EwmaChangeDetector{target=" + target + ", statistic=" + statistic() + ", limit=" + controlLimit() + ", alarms=" + alarmCount + '}';
    }
}
