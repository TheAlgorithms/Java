package remainder;

public class ChineseRemainderTheorem {

    // Helper function to compute the greatest common divisor (GCD)
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Helper function to compute the modular inverse of a modulo m
    // Uses the extended Euclidean algorithm
    public static int modInverse(int a, int m) {
        int m0 = m;
        int t;
        int q;
        int x0 = 0;
        int x1 = 1;

        if (m == 1) {
            return 0;
        }

        while (a > 1) {
            // q is quotient
            q = a / m;
            t = m;

            // m is remainder now, process same as Euclid's algo
            m = a % m;
            a = t;
            t = x0;

            x0 = x1 - q * x0;
            x1 = t;
        }

        // Make x1 positive
        if (x1 < 0) {
            x1 += m0;
        }

        return x1;
    }

    // Function to find the smallest x that satisfies
    // the given system of congruences using the Chinese Remainder Theorem
    public static int findMinX(int[] num, int[] rem, int k) {
        // Compute product of all numbers
        int prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num[i];
        }

        // Initialize result
        int result = 0;

        // Apply the formula:
        // result = (a1 * N1 * m1 + a2 * N2 * m2 + ... + ak * Nk * mk) % prod
        for (int i = 0; i < k; i++) {
            int pp = prod / num[i]; // Compute Ni = N / ni
            result += rem[i] * pp * modInverse(pp, num[i]);
        }

        return result % prod;
    }

    public static void main(String[] args) {
        // Example case
        int[] num = {3, 5, 7}; // Moduli
        int[] rem = {2, 3, 2}; // Remainders
        int k = num.length;

        // Calculate and print the result
        int result = findMinX(num, rem, k);
        System.out.println("The smallest x that satisfies the given system of congruences is: " + result);
    }
}
