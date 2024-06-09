public final class AbsoluteMin {
    private AbsoluteMin() {}

    /**
     * Compares the numbers given as arguments to get the absolute min value.
     *
     * @param numbers The numbers to compare
     * @return The absolute min value
     * @throws IllegalArgumentException If the input array is empty
     */
    public static int getMinValue(int... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty");
        }

        int min = Math.abs(numbers[0]);
        for (int number : numbers) {
            int absValue = Math.abs(number);
            if (absValue < min) {
                min = absValue;
            }
        }

        // Determine the sign of the minimum value
        for (int number : numbers) {
            if (Math.abs(number) == min) {
                return number;
            }
        }

        // This line should never be reached
        throw new IllegalStateException("Invalid state");
    }
}