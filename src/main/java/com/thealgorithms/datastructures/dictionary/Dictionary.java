package com.thealgorithms.datastructures.dictionary;

/**
 * A clone of a Python dictionary in a Java class that simulates a key-value store using a hash table
 * implemented from scratch without using standard collections like Map.
 */
public class Dictionary {
    private static final int SIZE = 10; // Number of buckets
    private Bucket[] buckets;

    /**
     * Inner class to represent a key-value pair.
     */
    private static class Entry {
        String key;
        Object value;

        Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Inner class to represent a bucket in the hash table.
     */
    private static class Bucket {
        Entry entry;
        Bucket next;

        Bucket(Entry entry) {
            this.entry = entry;
            this.next = null;
        }
    }

    /**
     * Constructs an empty dictionary.
     */
    public Dictionary() {
        this.buckets = new Bucket[SIZE];
    }

    /**
     * Constructs a dictionary with initial key-value pairs.
     * 
     * @param keyValuePairs An even number of arguments where each pair consists of a key and a value.
     * @throws IllegalArgumentException if the number of arguments is odd.
     */
    public Dictionary(Object... keyValuePairs) {
        this();
        if (keyValuePairs.length % 2 != 0) {
            throw new IllegalArgumentException("Key-value pairs must be even.");
        }
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = (String) keyValuePairs[i];
            Object value = keyValuePairs[i + 1];
            put(key, value);
        }
    }

    /**
     * Computes the index in the hash table for a given key.
     * 
     * @param key The key for which the index is computed.
     * @return The index in the hash table.
     */
    private int getIndex(String key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    /**
     * Adds or updates a key-value pair in the dictionary.
     * 
     * @param key   The key to be added or updated.
     * @param value The value associated with the key.
     */
    public void put(String key, Object value) {
        int index = getIndex(key);
        Bucket bucket = buckets[index];

        // Traverse the bucket to find if the key already exists
        while (bucket != null) {
            if (bucket.entry.key.equals(key)) {
                // Update the value if the key is found
                bucket.entry.value = value;
                return;
            }
            bucket = bucket.next;
        }

        // If key is not found, add a new entry to the beginning of the bucket
        Bucket newBucket = new Bucket(new Entry(key, value));
        newBucket.next = buckets[index];
        buckets[index] = newBucket;
    }

    /**
     * Retrieves the value associated with a given key.
     * 
     * @param key The key whose value is to be retrieved.
     * @return The value associated with the key, or null if the key is not found.
     */
    public Object get(String key) {
        int index = getIndex(key);
        Bucket bucket = buckets[index];

        // Traverse the bucket to find the key
        while (bucket != null) {
            if (bucket.entry.key.equals(key)) {
                return bucket.entry.value;
            }
            bucket = bucket.next;
        }
        return null; // Return null if the key is not found
    }

    /**
     * Removes the key-value pair associated with the given key.
     * 
     * @param key The key to be removed.
     */
    public void remove(String key) {
        int index = getIndex(key);
        Bucket bucket = buckets[index];
        Bucket prev = null;

        // Traverse the bucket to find the key
        while (bucket != null) {
            if (bucket.entry.key.equals(key)) {
                if (prev == null) {
                    // Removing the first bucket in the slot
                    buckets[index] = bucket.next;
                } else {
                    // Removing a bucket from the middle or end
                    prev.next = bucket.next;
                }
                return;
            }
            prev = bucket;
            bucket = bucket.next;
        }
    }

    /**
     * Checks if the dictionary contains the specified key.
     * 
     * @param key The key to check for.
     * @return true if the dictionary contains the key, false otherwise.
     */
    public boolean containsKey(String key) {
        int index = getIndex(key);
        Bucket bucket = buckets[index];

        // Traverse the bucket to find the key
        while (bucket != null) {
            if (bucket.entry.key.equals(key)) {
                return true;
            }
            bucket = bucket.next;
        }
        return false;
    }

    /**
     * Returns a string representation of the dictionary in a JSON-like format without formatting.
     * 
     * @return A JSON-like string representation of the dictionary.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (Bucket bucket : buckets) {
            while (bucket != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append("\"").append(bucket.entry.key).append("\": ")
                  .append("\"").append(bucket.entry.value.toString()).append("\"");
                first = false;
                bucket = bucket.next;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Returns a string representation of the dictionary in a formatted JSON-like format.
     * 
     * @return A formatted JSON-like string representation of the dictionary.
     */
    public String toFString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        boolean first = true;
        for (Bucket bucket : buckets) {
            while (bucket != null) {
                if (!first) {
                    sb.append(",\n");
                }
                sb.append("  \"").append(bucket.entry.key).append("\": ")
                  .append("\"").append(bucket.entry.value.toString()).append("\"");
                first = false;
                bucket = bucket.next;
            }
        }
        sb.append("\n}");
        return sb.toString();
    }
}
