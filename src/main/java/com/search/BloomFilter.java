package com.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * A simple implementation of Bloom filter.
 * 
 * Bloom filter have a chance of being wrong.
 * 
 * The Bloom filter assert that elements that do not exist must not exist,
 * if assert an element exists, but not necessarily.
 * 
 * The accuracy rate depends on capacity and hash functions.
 *
 */
public class BloomFilter implements Serializable {
    private static final long serialVersionUID = -4466610350741278658L;

    private static final int LONG_SHIFT = 6;
    private static final int LONG_MASK = 63;

    /**
     * hash functions
     */
    private final List<Function<String, Integer>> hashFunctions;

    private final long[] table;
    private final int tableMask;
    private int size;

    /**
     * @param capacity      the filter capacity
     * @param hashFunctions hash functions
     * @see Builder must be build by {@link Builder}.
     */
    private BloomFilter(int capacity, List<Function<String, Integer>> hashFunctions) {
        this.hashFunctions = hashFunctions;
        int cap = nextPowerOf2(capacity);
        tableMask = (cap << LONG_SHIFT) - 1;
        table = new long[cap];
    }

    public static Builder builder(int capacity) {
        if (capacity < 1) {
            throw new IllegalStateException("capacity must be > 0");
        }

        return new Builder(capacity);
    }

    /**
     * Add an element to the Bloom filter.
     */
    public void add(String element) {
        checkNotNull(element, "element");

        for (Function<String, Integer> hashFunction : hashFunctions) {
            int key = hashFunction.apply(element) & tableMask;
            table[key >>> LONG_SHIFT] |= (1 << (key & LONG_MASK));
        }
        size++;
    }

    /**
     * @return true if the element exists, otherwise false.
     */
    public boolean contains(String element) {
        if (element == null) {
            return false;
        }

        for (Function<String, Integer> hashFunction : hashFunctions) {
            int key = hashFunction.apply(element) & tableMask;
            if ((table[key >>> LONG_SHIFT] & (1 << (key & LONG_MASK))) == 0) {
                return false;
            }
        }
        return true;
    }

    public List<Function<String, Integer>> getHashFunctions() {
        return hashFunctions;
    }

    public int size() {
        return size;
    }

    private static void checkNotNull(String element, String msg) {
        if (element == null) {
            throw new NullPointerException(msg + " must be not null");
        }
    }

    private static int nextPowerOf2(int i) {
        int n = i - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 0x40000000) ? 0x40000000 : n + 1;
    }

    /**
     * We need a list of unmodifiable hash functions.
     */
    public static class Builder {
        private int capacity;
        private List<Function<String, Integer>> hashFunctions = new ArrayList<>();

        private Builder(int capacity) {
            this.capacity = capacity;
        }

        public Builder addHashFunction(Function<String, Integer> function) {
            hashFunctions.add(function);
            return this;
        }

        public BloomFilter build() {
            if (hashFunctions.isEmpty()) {
                addDefaultHashFunction();
            }
            return new BloomFilter(capacity, Collections.unmodifiableList(hashFunctions));
        }

        /**
         * I provides several default hash functions
         */
        private void addDefaultHashFunction() {
            // Java String Hash Function
            hashFunctions.add(String::hashCode);

            // SDBM Hash Function
            hashFunctions.add(key -> {
                if (key == null || key.isEmpty()) {
                    return 0;
                }

                int hash = 0;
                for (int i = 0; i < key.length(); i++) {
                    hash = key.charAt(i) + (hash << 6) + (hash << 16) - hash;
                }
                hash &= 0x7ffffff;
                return hash;
            });

            // Robert Sedgwicks Hash Function
            hashFunctions.add(key -> {
                if (key == null || key.isEmpty()) {
                    return 0;
                }

                int hash = 0;
                int magic = 63689;
                for (int i = 0; i < key.length(); i++) {
                    hash = hash * magic + key.charAt(i);
                    magic *= 378551;
                }
                return hash;
            });

            // Arash Partow Hash Function
            hashFunctions.add(key -> {
                if (key == null || key.isEmpty()) {
                    return 0;
                }

                int hash = 0;
                for (int i = 0; i < key.length(); i++) {
                    char ch = key.charAt(i);
                    if ((i & 1) == 0) {
                        hash ^= ((hash << 7) ^ ch ^ (hash >> 3));
                    } else {
                        hash ^= (~((hash << 11) ^ ch ^ (hash >> 5)));
                    }
                }
                return hash;
            });
        }

    }

}
