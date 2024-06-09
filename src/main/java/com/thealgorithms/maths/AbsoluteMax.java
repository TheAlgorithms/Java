public final class AbsoluteMax {
    private AbsoluteMax() {}

    /**
     * Finds the absolute maximum value among the given numbers.
     *
     * @param numbers The numbers to compare.
     * @return The absolute maximum value.
     * @throws IllegalArgumentException If the input array is empty or null.
     */
    public static int getMaxValue(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty or null");
        }

        int max = Integer.MIN_VALUE;
        for (int number : numbers) {
            max = Math.max(max, Math.abs(number));
        }
        return max;
    }
}