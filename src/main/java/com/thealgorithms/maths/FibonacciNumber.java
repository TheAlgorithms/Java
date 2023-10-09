public final class FibonacciNumber {
    private FibonacciNumber() {
    }

    // Compute the limit for n that fits in a long
    private static final int argLimit;
    
    static {
        long prev = 0;
        long current = 1;
        int limit = 0;
        while (current <= Long.MAX_VALUE) {
            long next = prev + current;
            prev = current;
            current = next;
            limit++;
        }
        argLimit = limit;
    }

    public static long nthFibonacci(int n) {
        if (n > argLimit) {
            throw new IllegalArgumentException("Input 'n' is too large to fit into a long.");
        }

        // Calculate the nth Fibonacci number using the golden ratio formula
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        long fibonacci = (long) ((1.0 / sqrt5) * (Math.pow(phi, n) - Math.pow(psi, n)));

        return fibonacci;
    }
}

