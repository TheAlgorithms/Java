package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to compute the bitwise AND of all integers within a given range [m, n].
 *
 * The Bitwise AND of Numbers Range is a useful algorithm that computes the bitwise AND of all integers within a given range [m, n].
 *
 ** Use Cases and Applications:
 * <ul>
 *   <li><b>Finding Common Prefix in Binary Representation:</b>
 *     <p>The bitwise AND operation identifies common bit patterns among numbers. When applied to a range of numbers, it effectively highlights the highest common bits, essentially finding the "common prefix" of the binary representations within the range.</p>
 *     <p>This is useful in cases where you need to compress data or perform range-based operations, such as in networking (for subnet calculations), IP range checks, or optimizing certain computations that rely on binary prefixes.</p>
 *   </li>
 *   <li><b>Efficient Range Queries:</b>
 *     <p>In some algorithmic problems, you might want to check commonalities between consecutive numbers in a range. Instead of iterating over the entire range and performing individual AND operations, this algorithm helps compress that into a single step, improving performance.</p>
 *   </li>
 *   <li><b>Logical Grouping of Numbers:</b>
 *     <p>In certain mathematical or data compression problems, grouping numbers based on their common binary patterns can be helpful. The bitwise AND of numbers over a range can act as a first step toward identifying such groups.</p>
 *   </li>
 *   <li><b>Optimization in Programming:</b>
 *     <p>This operation can save time in situations where you're performing repetitive bitwise operations over large data sets, as it helps identify the "stable" bits across numbers in a range without computing it for every single pair.</p>
 *   </li>
 * </ul>
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/Bitwise_operation">Bitwise operation</a> Wikipedia page.
 *
 * <b>Example usage:</b>
 * <pre>
 * int result = BitWiseAndInRange.rangeBitwiseAnd(5, 7);
 * System.out.println("Bitwise AND of range [5, 7]: " + result); // Output: 4
 * </pre>
 * @author Tanmay Singh
 */
public final class BitWiseAndInRange {
    private BitWiseAndInRange() {
    }

    /**
     * Computes the bitwise AND of all integers within the given range [m, n].
     *
     * @param m the start of the range (inclusive)
     * @param n the end of the range (inclusive)
     * @return the bitwise AND of all integers in the range [m, n]
     */
    public static int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            // Turn off the rightmost 1-bit of n
            n = n & (n - 1);
        }
        return n;
    }
}
