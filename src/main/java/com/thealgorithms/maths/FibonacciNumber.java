import java.math.BigInteger;

public final class FibonacciNumber {
    private FibonacciNumber() {
    }

    // Compute the limit for n that fits in a long
    private static final int argLimit = 92;

    public static BigInteger nthFibonacci(int n) {
        if (n > argLimit) {
            throw new IllegalArgumentException("Input 'n' is too large to fit into a long.");
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            BigInteger next = a.add(b);
            a = b;
            b = next;
        }
        return b;
    }
}
