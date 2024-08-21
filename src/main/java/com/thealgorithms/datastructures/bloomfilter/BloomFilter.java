package com.thealgorithms.datastructures.bloomfilter;

import java.util.BitSet;

/**
 * A generic BloomFilter implementation for probabilistic membership checking.
 *
 * @param <T> The type of elements to be stored in the Bloom filter.
 */
public class BloomFilter<T> {

    private final int numberOfHashFunctions;
    private final BitSet bitArray;
    private final Hash<T>[] hashFunctions;

    /**
     * Constructs a BloomFilter with a specified number of hash functions and bit array size.
     *
     * @param numberOfHashFunctions the number of hash functions to use
     * @param bitArraySize          the size of the bit array
     */
    @SuppressWarnings("unchecked")
    public BloomFilter(int numberOfHashFunctions, int bitArraySize) {
        this.numberOfHashFunctions = numberOfHashFunctions;
        this.bitArray = new BitSet(bitArraySize);
        this.hashFunctions = new Hash[numberOfHashFunctions];
        initializeHashFunctions();
    }

    /**
     * Initializes the hash functions with unique indices.
     */
    private void initializeHashFunctions() {
        for (int i = 0; i < numberOfHashFunctions; i++) {
            hashFunctions[i] = new Hash<>(i);
        }
    }

    /**
     * Inserts an element into the Bloom filter.
     *
     * @param key the element to insert
     */
    public void insert(T key) {
        for (Hash<T> hash : hashFunctions) {
            int position = Math.abs(hash.compute(key) % bitArray.size());
            bitArray.set(position);
        }
    }

    /**
     * Checks if an element might be in the Bloom filter.
     *
     * @param key the element to check
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
     *
     * @param <T> The type of elements to be hashed.
     */
    private static class Hash<T> {

        private final int index;

        /**
         * Constructs a Hash function with a specified index.
         *
         * @param index the index of this hash function
         */
        Hash(int index) {
            this.index = index;
        }

        /**
         * Computes the hash of the given key.
         *
         * @param key the element to hash
         * @return the hash value
         */
        public int compute(T key) {
            return index * asciiString(String.valueOf(key));
        }

        /**
         * Computes the ASCII value sum of the characters in a string.
         *
         * @param word the string to compute
         * @return the sum of ASCII values of the characters
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
