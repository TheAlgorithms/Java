package com.thealgorithms.others;

/**
 * FNV-1a (Fowler-Noll-Vo) is a non-cryptographic hash function created by Glenn Fowler, Landon
 * Curt Noll, and Kiem-Phong Vo.
 *
 * <p>The FNV-1a variant provides better avalanche characteristics (bit changes distribute more
 * uniformly) compared to the original FNV-1.
 *
 * <p>Key properties:
 * <ul>
 *   <li>Fast computation - simple operations (XOR and multiply)</li>
 *   <li>Good distribution - minimizes hash collisions</li>
 *   <li>Non-cryptographic - not suitable for security purposes</li>
 *   <li>Widely used in hash tables, checksums, and data structures</li>
 * </ul>
 *
 * <p>Algorithm: hash = FNV_offset_basis for each byte in data: hash = hash XOR byte hash = hash *
 * FNV_prime
 *
 * <p>FNV-1a 32-bit: FNV_offset_basis = 2166136261 (0x811c9dc5) FNV_prime = 16777619 (0x01000193)
 *
 * <p>FNV-1a 64-bit: FNV_offset_basis = 14695981039346656037 (0xcbf29ce484222325) FNV_prime =
 * 1099511628211 (0x100000001b3)
 *
 * <p>Time Complexity: O(n) where n is the length of the input
 * <p>Space Complexity: O(1)
 *
 * @author dinilH
 * @see <a href="http://www.isthe.com/chongo/tech/comp/fnv/">FNV Hash</a>
 * @see <a href="https://en.wikipedia.org/wiki/Fowler%E2%80%93Noll%E2%80%93Vo_hash_function">FNV on Wikipedia</a>
 */
public final class FNV1aHash {

    // FNV-1a 32-bit parameters
    private static final int FNV_32_INIT = 0x811c9dc5;
    private static final int FNV_32_PRIME = 0x01000193;

    // FNV-1a 64-bit parameters
    private static final long FNV_64_INIT = 0xcbf29ce484222325L;
    private static final long FNV_64_PRIME = 0x100000001b3L;

    private FNV1aHash() {
        // Utility class - prevent instantiation
    }

    /**
     * Computes the 32-bit FNV-1a hash of the input string.
     *
     * @param data the input string to hash
     * @return the 32-bit hash value as an integer
     * @throws IllegalArgumentException if data is null
     */
    public static int hash32(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        return hash32(data.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    /**
     * Computes the 32-bit FNV-1a hash of the input byte array.
     *
     * @param data the input byte array to hash
     * @return the 32-bit hash value as an integer
     * @throws IllegalArgumentException if data is null
     */
    public static int hash32(byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input byte array cannot be null");
        }

        int hash = FNV_32_INIT;

        for (byte b : data) {
            hash ^= (b & 0xff); // XOR with byte (ensure unsigned)
            hash *= FNV_32_PRIME; // Multiply by FNV prime
        }

        return hash;
    }

    /**
     * Computes the 64-bit FNV-1a hash of the input string.
     *
     * @param data the input string to hash
     * @return the 64-bit hash value as a long
     * @throws IllegalArgumentException if data is null
     */
    public static long hash64(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        return hash64(data.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    /**
     * Computes the 64-bit FNV-1a hash of the input byte array.
     *
     * @param data the input byte array to hash
     * @return the 64-bit hash value as a long
     * @throws IllegalArgumentException if data is null
     */
    public static long hash64(byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input byte array cannot be null");
        }

        long hash = FNV_64_INIT;

        for (byte b : data) {
            hash ^= (b & 0xff); // XOR with byte (ensure unsigned)
            hash *= FNV_64_PRIME; // Multiply by FNV prime
        }

        return hash;
    }

    /**
     * Computes the 32-bit FNV-1a hash and returns it as a hex string.
     *
     * @param data the input string to hash
     * @return the hash value as an 8-character hex string
     * @throws IllegalArgumentException if data is null
     */
    public static String hash32Hex(String data) {
        return String.format("%08x", hash32(data));
    }

    /**
     * Computes the 64-bit FNV-1a hash and returns it as a hex string.
     *
     * @param data the input string to hash
     * @return the hash value as a 16-character hex string
     * @throws IllegalArgumentException if data is null
     */
    public static String hash64Hex(String data) {
        return String.format("%016x", hash64(data));
    }
}
