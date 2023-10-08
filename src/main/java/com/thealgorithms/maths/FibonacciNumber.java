public final class FibonacciNumber {
    private FibonacciNumber() {
    }

    // Function to find the nth Fibonacci number using the golden ratio formula
    public static long nthFibonacci(int n) {
        // Find the last 'n' for which the nth Fibonacci number fits into a long
        int lastFibThatFitsInLong = 0;
        long prev = 0;
        long current = 1;
        while (current <= Long.MAX_VALUE) {
            long next = prev + current;
            prev = current;
            current = next;
            lastFibThatFitsInLong++;
        }

        // If 'n' is beyond what fits in a long, use a different method
        if (n > lastFibThatFitsInLong) {
            throw new IllegalArgumentException("Input 'n' is too large to fit into a long.");
        }

        // Calculate the nth Fibonacci number using the golden ratio formula
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        long fibonacci = (long) ((1.0 / sqrt5) * (Math.pow(phi, n) - Math.pow(psi, n)));

        // Return the result as a long
        return fibonacci;
    }
}
