package com.thealgorithms.others;
import java.util.Collections;
import java.util.List;
public class SafetyResult {
    private final boolean safe;
    private final List<Integer> sequence;   // safe sequence; empty if not safe
    private final String message;

    SafetyResult(boolean safe, List<Integer> sequence, String message) {
        this.safe = safe;
        this.sequence = Collections.unmodifiableList(sequence);
        this.message = message;
    }

    public boolean isSafe() {
        return safe;
    }

    public List<Integer> getSequence() {
        return sequence;
    }

    public String getMessage() {
        return message;
    }
}
