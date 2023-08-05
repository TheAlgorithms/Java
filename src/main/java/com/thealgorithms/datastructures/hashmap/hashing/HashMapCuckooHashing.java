package com.thealgorithms.datastructures.hashmap.hashing;

import java.lang.Math;
import java.util.Objects;

/**
 * This class is an implementation of a hash table using Cuckoo Hashing It uses
 * a dynamic array to lengthen the size of the hash table when load factor > .7
 *
 * <a href="https://en.wikipedia.org/wiki/Cuckoo_hashing">...</a>
 */
public class HashMapCuckooHashing {

    private int tableSize; // size of the hash table
    private Integer[] buckets; // array representing the table
    private final Integer AVAILABLE;
    private int size; // number of elements in the hash table

    private int thresh; // threshold for infinite loop checking

    /**
     * Constructor initializes buckets array, hsize, and creates dummy object
     * for AVAILABLE
     *
     * @param tableSize the desired size of the hash map
     */
    public HashMapCuckooHashing(int tableSize) {
        this.buckets = new Integer[tableSize];
        this.tableSize = tableSize;
        this.AVAILABLE = Integer.MIN_VALUE;
        this.size = 0;
        this.thresh = (int) (Math.log(tableSize) / Math.log(2)) + 2;
    }

    /**
     * The 2 Hash Functions takes a given key and finds an index based on its data, 2 distinctive
     * ways to minimize collisions
     *
     * @param key the desired key to be converted
     * @return int an index corresponding to the key
     */

    public int hashFunction1(int key) {
        int hash = key % tableSize;
        if (hash < 0) {
            hash += tableSize;
        }
        return hash;
    }

    public int hashFunction2(int key) {
        int hash = key / tableSize;
        hash %= tableSize;
        if (hash < 0) {
            hash += tableSize;
        }
        return hash;
    }

    /**
     * inserts the key into the hash map by wrapping it as an Integer object, then uses while loop
     * to insert new key if desired place is empty, return. if already occupied, continue while loop
     * over the new key that has just been pushed out. if while loop continues more than Thresh,
     * rehash table to new size, then push again.
     *
     * @param key the desired key to be inserted in the hash map
     */

    public void insertKey2HashTable(int key) {
        Integer wrappedInt = key, temp;
        int hash, loopCounter = 0;

        if (isFull()) {
            System.out.println("Hash table is full, lengthening & rehashing table");
            reHashTableIncreasesTableSize();
        }

        if (checkTableContainsKey(key)) {
            throw new IllegalArgumentException("Key already inside, no duplicates allowed");
        }

        while (loopCounter <= thresh) {
            loopCounter++;
            hash = hashFunction1(key);

            if ((buckets[hash] == null) || Objects.equals(buckets[hash], AVAILABLE)) {
                buckets[hash] = wrappedInt;
                size++;
                checkLoadFactor();
                return;
            }

            temp = buckets[hash];
            buckets[hash] = wrappedInt;
            wrappedInt = temp;
            hash = hashFunction2(temp);
            if (Objects.equals(buckets[hash], AVAILABLE)) {
                buckets[hash] = wrappedInt;
                size++;
                checkLoadFactor();
                return;
            } else if (buckets[hash] == null) {
                buckets[hash] = wrappedInt;
                size++;
                checkLoadFactor();
                return;
            }

            temp = buckets[hash];
            buckets[hash] = wrappedInt;
            wrappedInt = temp;
        }
        System.out.println("Infinite loop occurred, lengthening & rehashing table");
        reHashTableIncreasesTableSize();
        insertKey2HashTable(key);
    }

    /**
     * creates new HashMapCuckooHashing object, then inserts each of the elements in the previous
     * table to it with its new hash functions. then refers current array to new table.
     *
     */
    public void reHashTableIncreasesTableSize() {
        HashMapCuckooHashing newT = new HashMapCuckooHashing(tableSize * 2);
        for (int i = 0; i < tableSize; i++) {
            if (buckets[i] != null && !Objects.equals(buckets[i], AVAILABLE)) {
                newT.insertKey2HashTable(this.buckets[i]);
            }
        }
        this.tableSize *= 2;
        this.buckets = newT.buckets;
        this.thresh = (int) (Math.log(tableSize) / Math.log(2)) + 2;
    }

    /**
     * deletes a key from the hash map and adds an available placeholder
     *
     * @param key the desired key to be deleted
     */
    public void deleteKeyFromHashTable(int key) {
        Integer wrappedInt = key;
        int hash = hashFunction1(key);
        if (isEmpty()) {
            throw new IllegalArgumentException("Table is empty");
        }

        if (Objects.equals(buckets[hash], wrappedInt)) {
            buckets[hash] = AVAILABLE;
            size--;
            return;
        }

        hash = hashFunction2(key);
        if (Objects.equals(buckets[hash], wrappedInt)) {
            buckets[hash] = AVAILABLE;
            size--;
            return;
        }
        throw new IllegalArgumentException("Key " + key + " already inside, no duplicates allowed");
    }

    /**
     * Displays the hash table line by line
     */
    public void displayHashtable() {
        for (int i = 0; i < tableSize; i++) {
            if ((buckets[i] == null) || Objects.equals(buckets[i], AVAILABLE)) {
                System.out.println("Bucket " + i + ": Empty");
            } else {
                System.out.println("Bucket " + i + ": " + buckets[i].toString());
            }
        }
        System.out.println();
    }

    /**
     * Finds the index of location based on an inputted key
     *
     * @param key the desired key to be found
     * @return int the index where the key is located
     */
    public int findKeyInTable(int key) {
        Integer wrappedInt = key;
        int hash = hashFunction1(key);

        if (isEmpty()) {
            throw new IllegalArgumentException("Table is empty");
        }

        if (Objects.equals(buckets[hash], wrappedInt)) return hash;

        hash = hashFunction2(key);
        if (!Objects.equals(buckets[hash], wrappedInt))
            throw new IllegalArgumentException("Key " + key + " not found in table");
        else {
            return hash;
        }
    }

    /**
     * checks if key is inside without any output other than returned boolean.
     *
     * @param key the desired key to be found
     * @return int the index where the key is located
     */
    public boolean checkTableContainsKey(int key) {
        return ((buckets[hashFunction1(key)] != null && buckets[hashFunction1(key)].equals(key)) || (buckets[hashFunction2(key)] != null && buckets[hashFunction2(key)] == key));
    }

    /**
     * Checks the load factor of the hash table if greater than .7,
     * automatically lengthens table to prevent further collisions
     */
    public double checkLoadFactor() {
        double factor = (double) size / tableSize;
        if (factor > .7) {
            System.out.printf("Load factor is %.2f , rehashing table\n", factor);
            reHashTableIncreasesTableSize();
        }
        return factor;
    }

    /**
     * isFull returns true if the hash map is full and false if not full
     *
     * @return boolean is Empty
     */
    public boolean isFull() {
        boolean response = true;
        for (int i = 0; i < tableSize; i++) {
            if (buckets[i] == null || Objects.equals(buckets[i], AVAILABLE)) {
                return false;
            }
        }
        return response;
    }

    /**
     * isEmpty returns true if the hash map is empty and false if not empty
     *
     * @return boolean is Empty
     */
    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < tableSize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

    public int getNumberOfKeysInTable() {
        return size;
    }
}
