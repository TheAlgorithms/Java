package com.thealgorithms.maths;

public final class AbsoluteMax {
    private AbsoluteMax() {
    }

    /**
     * Finds the absolute maximum value among the given numbers.
     *
     * @param numbers The numbers to compare.
     * @return The absolute maximum value.
     * @throws IllegalArgumentException If the input array is empty or null.
     */
    public static Optional<Integer> getMaxValue(List<Integer> numbers) {
        if (numbers.isEmpty()) {
        throw new IllegalArgumentException("Numbers array cannot be empty or null");
        }
        return numbers.stream()
            .max(Comparator.naturalOrder());
    }
}
