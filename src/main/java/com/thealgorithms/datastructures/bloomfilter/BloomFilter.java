package com.thealgorithms.datastructures.bloomfilter;

import java.util.BitSet;

/**
 * A generic BloomFilter implementation for probabilistic membership checking.
 * <p>
 * Bloom filters are space-efficient data structures that provide a fast way to test whether an
 * element is a member of a set. They may produce false positives, indicating an element is
 * in the set when it is not, but they will never produce false negatives.
 * </p>
 *
 * @param <T> The type of elements to be stored in the Bloom filter.
 */
@SuppressWarnings("rawtypes")
public class BloomFilter<T> {

    private final int numberOfHashFunctions;
    private final BitSet bitArray;
    private final Hash<T>[] hashFunctions;

    /**
     * Constructs a BloomFilter with a specified number of hash functions and bit array size.
     *
     * @param numberOfHashFunctions the number of hash functions to use
     * @param bitArraySize          the size of the bit array, which determines the capacity of the filter
     * @throws IllegalArgumentException if numberOfHashFunctions or bitArraySize is less than 1
     */
    @SuppressWarnings("unchecked")
    public BloomFilter(int numberOfHashFunctions, int bitArraySize) {
        if (numberOfHashFunctions < 1 || bitArraySize < 1) {
            throw new IllegalArgumentException("Number of hash functions and bit array size must be greater than 0");
        }
        this.numberOfHashFunctions = numberOfHashFunctions;
        this.bitArray = new BitSet(bitArraySize);
        this.hashFunctions = new Hash[numberOfHashFunctions];
        initializeHashFunctions();
    }

    /**
     * Initializes the hash functions with unique indices to ensure different hashing.
     */
    private void initializeHashFunctions() {
        for (int i = 0; i < numberOfHashFunctions; i++) {
            hashFunctions[i] = new Hash<>(i);
        }
    }

    /**
     * Inserts an element into the Bloom filter.
     * <p>
     * This method hashes the element using all defined hash functions and sets the corresponding
     * bits in the bit array.
     * </p>
     *
     * @param key the element to insert into the Bloom filter
     */
    public void insert(T key) {
        for (Hash<T> hash : hashFunctions) {
            int position = Math.abs(hash.compute(key) % bitArray.size());
            bitArray.set(position);
        }
    }

    /**
     * Checks if an element might be in the Bloom filter.
     * <p>
     * This method checks the bits at the positions computed by each hash function. If any of these
     * bits are not set, the element is definitely not in the filter. If all bits are set, the element
     * might be in the filter.
     * </p>
     *
     * @param key the element to check for membership in the Bloom filter
     * @return {@code true} if the element might be in the Bloom filter, {@code false} if it is definitely not
     */
    public boolean contains(T key) {
        for (Hash<T> hash : hashFunctions) {
            int position = Math.abs(hash.compute(key) % bitArray.size());
            if (!bitArray.get(position)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Inner class representing a hash function used by the Bloom filter.
     * <p>
     * Each instance of this class represents a different hash function based on its index.
     * </p>
     *
     * @param <T> The type of elements to be hashed.
     */
    private static class Hash<T> {

        private final int index;

        /**
         * Constructs a Hash function with a specified index.
         *
         * @param index the index of this hash function, used to create a unique hash
         */
        Hash(int index) {
            this.index = index;
        }

        /**
         * Computes the hash of the given key.
         * <p>
         * The hash value is calculated by multiplying the index of the hash function
         * with the ASCII sum of the string representation of the key.
         * </p>
         *
         * @param key the element to hash
         * @return the computed hash value
         */
        public int compute(T key) {
            return index * asciiString(String.valueOf(key));
        }

        /**
         * Computes the ASCII value sum of the characters in a string.
         * <p>
         * This method iterates through each character of the string and accumulates
         * their ASCII values to produce a single integer value.
         * </p>
         *
         * @param word the string to compute
         * @return the sum of ASCII values of the characters in the string
         */
        private int asciiString(String word) {
            int sum = 0;
            for (char c : word.toCharArray()) {
                sum += c;
            }
            return sum;
        }
    }
}
