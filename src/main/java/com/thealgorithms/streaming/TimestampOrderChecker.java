package com.thealgorithms.streaming;

/**
 * Watches the timestamps of a stream for samples that arrive out of order or twice.
 *
 * <p>Every windowed algorithm assumes that time moves forward. When it does not, nothing crashes:
 * the window simply holds samples that never coexisted, a rate is computed over a negative interval,
 * and a duplicate counts twice in an average. Retries, buffers flushed after a reconnect, several
 * producers writing into one topic and a clock stepped backwards all produce exactly that, and the
 * only cheap defence is to check the order explicitly.
 *
 * <p>Each timestamp is compared with the one immediately before it and classified as
 * {@link Order#INCREASING}, {@link Order#DUPLICATE} or {@link Order#BACKWARDS}. The checker reports,
 * it does not reorder or drop anything, so {@link #lastTimestamp()} is always the most recent sample
 * and not the largest one seen.
 *
 * <p>Besides the counters it keeps the worst backwards step, which is what a caller needs in order to
 * size a reordering buffer: a stream whose worst violation is 20 milliseconds can be repaired by
 * holding samples for 20 milliseconds, while one that jumps back an hour cannot be repaired at all.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * TimestampOrderChecker checker = new TimestampOrderChecker();
 * for (double timestamp : timestamps) {
 *     if (checker.accept(timestamp).isViolation()) {
 *         quarantine(timestamp);
 *     }
 * }
 * }</pre>
 *
 * <p>Each sample costs O(1) time and the checker keeps O(1) state. This class is not thread-safe.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Monotonic_function">Monotonic function</a>
 */
public final class TimestampOrderChecker {

    private double lastTimestamp;
    private double worstBackwardsStep;
    private boolean started;
    private long count;
    private long duplicateCount;
    private long backwardsCount;
    private Order lastOrder = Order.INCREASING;

    /**
     * Feeds the timestamp of one sample.
     *
     * @param timestamp when the sample arrived
     * @return how this timestamp relates to the previous one; the first one is always
     *     {@link Order#INCREASING}
     * @throws IllegalArgumentException if {@code timestamp} is NaN or infinite
     */
    public Order accept(double timestamp) {
        if (!Double.isFinite(timestamp)) {
            throw new IllegalArgumentException("Timestamps must be finite, but was " + timestamp);
        }

        Order order = Order.INCREASING;
        if (started) {
            if (timestamp == lastTimestamp) {
                order = Order.DUPLICATE;
                duplicateCount++;
            } else if (timestamp < lastTimestamp) {
                order = Order.BACKWARDS;
                backwardsCount++;
                worstBackwardsStep = Math.max(worstBackwardsStep, lastTimestamp - timestamp);
            }
        }

        lastTimestamp = timestamp;
        started = true;
        count++;
        lastOrder = order;
        return order;
    }

    /**
     * Runs the checker over a whole series of timestamps.
     *
     * @param timestamps the timestamps to inspect
     * @return a new array of the same length holding the verdict for every timestamp
     * @throws IllegalArgumentException if a timestamp is NaN or infinite
     * @throws NullPointerException if {@code timestamps} is {@code null}
     */
    public Order[] scan(double[] timestamps) {
        Order[] verdicts = new Order[timestamps.length];
        for (int i = 0; i < timestamps.length; i++) {
            verdicts[i] = accept(timestamps[i]);
        }
        return verdicts;
    }

    /**
     * Returns the verdict on the most recent timestamp.
     *
     * @return the last verdict, {@link Order#INCREASING} before the first timestamp
     */
    public Order lastOrder() {
        return lastOrder;
    }

    /**
     * Tells whether every timestamp so far moved forward.
     *
     * @return {@code true} if neither a duplicate nor a backwards step has been seen
     */
    public boolean isOrdered() {
        return duplicateCount == 0 && backwardsCount == 0;
    }

    /**
     * Returns the timestamp of the most recent sample, which is not necessarily the largest one seen.
     *
     * @return the last timestamp
     * @throws IllegalStateException if no timestamp has been accepted yet
     */
    public double lastTimestamp() {
        if (!started) {
            throw new IllegalStateException("The checker has not seen any timestamp yet");
        }
        return lastTimestamp;
    }

    /**
     * Returns the largest distance a timestamp ever jumped backwards, which is how long a reordering
     * buffer would have to hold a sample to repair this stream.
     *
     * @return the worst backwards step, {@code 0} if the stream never went backwards
     */
    public double worstBackwardsStep() {
        return worstBackwardsStep;
    }

    /**
     * Returns how many timestamps have been inspected since the last reset.
     *
     * @return the sample count
     */
    public long count() {
        return count;
    }

    /**
     * Returns how many timestamps repeated the one before them.
     *
     * @return the duplicate count
     */
    public long duplicateCount() {
        return duplicateCount;
    }

    /**
     * Returns how many timestamps were smaller than the one before them.
     *
     * @return the backwards count
     */
    public long backwardsCount() {
        return backwardsCount;
    }

    /**
     * Forgets every timestamp seen so far.
     */
    public void reset() {
        lastTimestamp = 0.0;
        worstBackwardsStep = 0.0;
        started = false;
        count = 0;
        duplicateCount = 0;
        backwardsCount = 0;
        lastOrder = Order.INCREASING;
    }

    @Override
    public String toString() {
        return "TimestampOrderChecker{samples=" + count + ", duplicates=" + duplicateCount + ", backwards=" + backwardsCount + ", worstStep=" + worstBackwardsStep + "}";
    }

    /**
     * How a timestamp relates to the one before it.
     */
    public enum Order {

        /** The timestamp moved forward, which is the only healthy case. */
        INCREASING,

        /** The timestamp repeats the previous one. */
        DUPLICATE,

        /** The timestamp is older than the previous one. */
        BACKWARDS;

        /**
         * Tells whether this verdict reports a broken timeline.
         *
         * @return {@code true} for {@link #DUPLICATE} and {@link #BACKWARDS}
         */
        public boolean isViolation() {
            return this != INCREASING;
        }
    }
}
