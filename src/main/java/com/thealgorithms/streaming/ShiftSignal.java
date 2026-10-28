package com.thealgorithms.streaming;

/**
 * What a change detector reports after looking at one sample: either the stream still behaves as
 * expected, or its level has shifted, in one direction or the other.
 *
 * @see CusumDetector
 * @see EwmaChangeDetector
 */
public enum ShiftSignal {

    /** No evidence of a change; the stream is in control. */
    NONE,

    /** The level of the stream has moved above the target. */
    UPWARD,

    /** The level of the stream has moved below the target. */
    DOWNWARD;

    /**
     * Tells whether this signal reports a change.
     *
     * @return {@code true} for {@link #UPWARD} and {@link #DOWNWARD}
     */
    public boolean isAlarm() {
        return this != NONE;
    }
}
