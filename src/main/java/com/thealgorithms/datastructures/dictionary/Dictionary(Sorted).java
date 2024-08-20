package com.thealgorithms.datastructures.dictionary;

/**
 * A clone of a Python dictionary in a Java class that simulates a key-value store using a hash table
 * implemented from scratch without using standard collections like Map.
 */
public class DictionarySort {
    private static final int SIZE = 10; // Number of buckets
    private Bucket[] buckets;
    private SortStrategy sortStrategy = SortStrategy.KEY; // Default sorting strategy

    /**
     * Enum to represent sorting strategies.
     */
    public enum SortStrategy {
        KEY, VALUE
    }

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
    public DictionarySort() {
        this.buckets = new Bucket[SIZE];
    }

    /**
     * Constructs a dictionary with initial key-value pairs.
     * 
     * @param keyValuePairs An even number of arguments where each pair consists of a key and a value.
     * @throws IllegalArgumentException if the number of arguments is odd.
     */
    public DictionarySort(Object... keyValuePairs) {
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
     * Sets the sorting strategy for the dictionary.
     * 
     * @param strategy The sorting strategy to use.
     */
    public void setSortStrategy(SortStrategy strategy) {
        this.sortStrategy = strategy;
    }

    /**
     * Returns a string representation of the dictionary in a JSON-like format without formatting.
     * 
     * @return A JSON-like string representation of the dictionary.
     */
    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a string representation of the dictionary in a JSON-like format with optional formatting.
     * 
     * @param formatted If true, returns the string in a formatted JSON-like format.
     * @return A JSON-like string representation of the dictionary.
     */
    public String toString(boolean formatted) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatted ? "{\n" : "{");

        Entry[] entries = getAllEntries();
        if (sortStrategy == SortStrategy.KEY) {
            sortByKey(entries);
        } else if (sortStrategy == SortStrategy.VALUE) {
            sortByValue(entries);
        }

        boolean first = true;
        for (Entry entry : entries) {
            if (!first) {
                sb.append(formatted ? ",\n" : ", ");
            }
            sb.append(formatted ? "  " : "")
              .append("\"").append(entry.key).append("\": ")
              .append("\"").append(entry.value.toString()).append("\"");
            first = false;
        }
        sb.append(formatted ? "\n}" : "}");
        return sb.toString();
    }

    /**
     * Retrieves all entries from the dictionary.
     * 
     * @return An array of all entries.
     */
    private Entry[] getAllEntries() {
        // Gather all entries from all buckets
        int count = 0;
        for (Bucket bucket : buckets) {
            while (bucket != null) {
                count++;
                bucket = bucket.next;
            }
        }

        Entry[] entries = new Entry[count];
        int index = 0;
        for (Bucket bucket : buckets) {
            while (bucket != null) {
                entries[index++] = bucket.entry;
                bucket = bucket.next;
            }
        }
        return entries;
    }

    /**
     * Sorts entries by key using quicksort.
     * 
     * @param entries The array of entries to be sorted.
     */
    private void sortByKey(Entry[] entries) {
        quickSort(entries, 0, entries.length - 1, true);
    }

    /**
     * Sorts entries by value using quicksort.
     * 
     * @param entries The array of entries to be sorted.
     */
    private void sortByValue(Entry[] entries) {
        quickSort(entries, 0, entries.length - 1, false);
    }

    /**
     * Quicksort implementation to sort entries by key or value.
     * 
     * @param entries The array of entries to be sorted.
     * @param low     The starting index.
     * @param high    The ending index.
     * @param byKey   True to sort by key, false to sort by value.
     */
    private void quickSort(Entry[] entries, int low, int high, boolean byKey) {
        if (low < high) {
            int pivotIndex = partition(entries, low, high, byKey);
            quickSort(entries, low, pivotIndex - 1, byKey);
            quickSort(entries, pivotIndex + 1, high, byKey);
        }
    }

    /**
     * Partition method for quicksort.
     * 
     * @param entries The array of entries to be partitioned.
     * @param low     The starting index.
     * @param high    The ending index.
     * @param byKey   True to sort by key, false to sort by value.
     * @return The index of the pivot element.
     */
    private int partition(Entry[] entries, int low, int high, boolean byKey) {
        Entry pivot = entries[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            boolean condition;
            if (byKey) {
                condition = entries[j].key.compareTo(pivot.key) < 0;
            } else {
                condition = entries[j].value.toString().compareTo(pivot.value.toString()) < 0;
            }
            if (condition) {
                i++;
                Entry temp = entries[i];
                entries[i] = entries[j];
                entries[j] = temp;
            }
        }
        Entry temp = entries[i + 1];
        entries[i + 1] = entries[high];
        entries[high] = temp;
        return i + 1;
    }
}
