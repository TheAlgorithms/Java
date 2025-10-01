package com.thealgorithms.maths;

/**
 * Extended Euclidean Algorithm implementation.
 * 
 * The Extended Euclidean Algorithm not only computes the Greatest Common Divisor (GCD)
 * of two integers a and b, but also finds integer coefficients x and y such that:
 * ax + by = gcd(a, b)
 * 
 * This is particularly useful in:
 * - Finding modular multiplicative inverses
 * - Solving linear Diophantine equations
 * - Cryptographic applications (RSA, etc.)
 * - Number theory problems
 * 
 * Time Complexity: O(log(min(a, b)))
 * Space Complexity: O(log(min(a, b))) due to recursion stack
 * 
 * @author TheAlgorithms Contributors
 * @see <a href="https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm">Extended Euclidean Algorithm</a>
 */
public final class ExtendedEuclideanAlgorithm {
    
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private ExtendedEuclideanAlgorithm() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Result class to hold the extended GCD computation results.
     * Contains the GCD and the coefficients x and y such that ax + by = gcd(a, b).
     */
    public static class ExtendedGcdResult {
        private final long gcd;
        private final long x;
        private final long y;
        
        /**
         * Constructs a new ExtendedGcdResult.
         * 
         * @param gcd the greatest common divisor
         * @param x the coefficient for the first number
         * @param y the coefficient for the second number
         */
        public ExtendedGcdResult(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
        
        /**
         * Gets the greatest common divisor.
         * 
         * @return the GCD
         */
        public long getGcd() {
            return gcd;
        }
        
        /**
         * Gets the coefficient x such that ax + by = gcd(a, b).
         * 
         * @return the coefficient x
         */
        public long getX() {
            return x;
        }
        
        /**
         * Gets the coefficient y such that ax + by = gcd(a, b).
         * 
         * @return the coefficient y
         */
        public long getY() {
            return y;
        }
        
        /**
         * Verifies that the result satisfies the equation ax + by = gcd(a, b).
         * 
         * @param a the first input number
         * @param b the second input number
         * @return true if the equation is satisfied, false otherwise
         */
        public boolean verify(long a, long b) {
            return a * x + b * y == gcd;
        }
        
        @Override
        public String toString() {
            return String.format("ExtendedGcdResult{gcd=%d, x=%d, y=%d}", gcd, x, y);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ExtendedGcdResult that = (ExtendedGcdResult) obj;
            return gcd == that.gcd && x == that.x && y == that.y;
        }
        
        @Override
        public int hashCode() {
            return Long.hashCode(gcd) ^ Long.hashCode(x) ^ Long.hashCode(y);
        }
    }
    
    /**
     * Computes the extended GCD using the recursive approach.
     * 
     * Finds integers x and y such that ax + by = gcd(a, b).
     * 
     * @param a the first integer
     * @param b the second integer
     * @return ExtendedGcdResult containing gcd, x, and y
     * 
     * Example:
     * <pre>
     * ExtendedGcdResult result = extendedGcd(30, 18);
     * // result.getGcd() = 6
     * // result.getX() = -1, result.getY() = 2
     * // Verification: 30 * (-1) + 18 * 2 = -30 + 36 = 6 ✓
     * </pre>
     */
    public static ExtendedGcdResult extendedGcd(long a, long b) {
        // Base case: if b is 0, then gcd(a, 0) = a
        // and we have a * 1 + 0 * 0 = a
        if (b == 0) {
            return new ExtendedGcdResult(Math.abs(a), a >= 0 ? 1 : -1, 0);
        }
        
        // Recursive call
        ExtendedGcdResult result = extendedGcd(b, a % b);
        
        // Update coefficients using the recurrence relation:
        // If b * x1 + (a % b) * y1 = gcd(a, b), then
        // a * y1 + b * (x1 - (a / b) * y1) = gcd(a, b)
        long newX = result.getY();
        long newY = result.getX() - (a / b) * result.getY();
        
        return new ExtendedGcdResult(result.getGcd(), newX, newY);
    }
    
    /**
     * Computes the extended GCD using the iterative approach.
     * This version is more space-efficient as it doesn't use recursion.
     * 
     * @param a the first integer
     * @param b the second integer
     * @return ExtendedGcdResult containing gcd, x, and y
     */
    public static ExtendedGcdResult extendedGcdIterative(long a, long b) {
        // Handle the case where one or both numbers are zero
        if (a == 0) {
            return new ExtendedGcdResult(Math.abs(b), 0, b >= 0 ? 1 : -1);
        }
        if (b == 0) {
            return new ExtendedGcdResult(Math.abs(a), a >= 0 ? 1 : -1, 0);
        }
        
        // Keep track of signs for final result
        int signA = a >= 0 ? 1 : -1;
        int signB = b >= 0 ? 1 : -1;
        a = Math.abs(a);
        b = Math.abs(b);
        
        // Initialize coefficients
        long x0 = 1, x1 = 0;
        long y0 = 0, y1 = 1;
        
        while (b != 0) {
            long quotient = a / b;
            
            // Update a and b
            long temp = b;
            b = a % b;
            a = temp;
            
            // Update x coefficients
            temp = x1;
            x1 = x0 - quotient * x1;
            x0 = temp;
            
            // Update y coefficients
            temp = y1;
            y1 = y0 - quotient * y1;
            y0 = temp;
        }
        
        // Adjust for original signs
        x0 *= signA;
        y0 *= signB;
        
        return new ExtendedGcdResult(a, x0, y0);
    }
    
    /**
     * Finds the modular multiplicative inverse of a modulo m.
     * Returns x such that (a * x) ≡ 1 (mod m), or throws an exception if no inverse exists.
     * 
     * @param a the number to find the inverse of
     * @param m the modulus
     * @return the modular multiplicative inverse of a modulo m
     * @throws IllegalArgumentException if gcd(a, m) ≠ 1 (inverse doesn't exist)
     * 
     * Example:
     * <pre>
     * long inverse = modularInverse(3, 7);
     * // inverse = 5, because (3 * 5) % 7 = 1
     * </pre>
     */
    public static long modularInverse(long a, long m) {
        if (m <= 0) {
            throw new IllegalArgumentException("Modulus must be positive");
        }
        
        ExtendedGcdResult result = extendedGcd(a, m);
        
        if (result.getGcd() != 1) {
            throw new IllegalArgumentException(
                String.format("Modular inverse does not exist: gcd(%d, %d) = %d ≠ 1", a, m, result.getGcd())
            );
        }
        
        // Ensure the result is positive
        long inverse = result.getX() % m;
        return inverse < 0 ? inverse + m : inverse;
    }
    
    /**
     * Solves the linear Diophantine equation ax + by = c.
     * Returns a solution if one exists, or null if no solution exists.
     * 
     * A solution exists if and only if gcd(a, b) divides c.
     * If a solution exists, there are infinitely many solutions given by:
     * x = x0 + k * (b / gcd(a, b))
     * y = y0 - k * (a / gcd(a, b))
     * for any integer k.
     * 
     * @param a coefficient of x
     * @param b coefficient of y
     * @param c the target value
     * @return a solution as ExtendedGcdResult where gcd represents the scaling factor,
     *         or null if no solution exists
     * 
     * Example:
     * <pre>
     * ExtendedGcdResult solution = solveDiophantine(6, 9, 21);
     * // One solution: x = 0, y = 7/3, but we need integer solutions
     * // Since gcd(6, 9) = 3 and 3 divides 21, solutions exist
     * // One solution: x = 3, y = 1 (since 6*3 + 9*1 = 27, need to scale)
     * </pre>
     */
    public static ExtendedGcdResult solveDiophantine(long a, long b, long c) {
        ExtendedGcdResult gcdResult = extendedGcd(a, b);
        
        // Check if solution exists
        if (c % gcdResult.getGcd() != 0) {
            return null; // No solution exists
        }
        
        // Scale the solution
        long scale = c / gcdResult.getGcd();
        long x = gcdResult.getX() * scale;
        long y = gcdResult.getY() * scale;
        
        return new ExtendedGcdResult(gcdResult.getGcd(), x, y);
    }
    
    /**
     * Computes the GCD of two numbers using the standard Euclidean algorithm.
     * This is provided for comparison and when only the GCD is needed.
     * 
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of a and b
     */
    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        
        return a;
    }
}