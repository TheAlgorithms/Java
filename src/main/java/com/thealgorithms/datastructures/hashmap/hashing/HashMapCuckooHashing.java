package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.*;

import java.lang.Math;

/**
 * This class is an implementation of a hash table using Cuckoo Hashing It uses
 * // * a dynamic array to lengthen the size of the hash table when load factor > .7 ?????????????
 */
public class HashMapCuckooHashing {

    private int hsize; // size of the hash table
    private Integer[] buckets; // array representing the table
    private final Integer AVAILABLE;
    private int size; // amount of elements in the hash table

    private int thresh; // threshhold for infinite loop checking

    /**
     * Constructor initializes buckets array, hsize, and creates dummy object
     * for AVAILABLE
     *
     * @param hsize the desired size of the hash map
     */
    public HashMapCuckooHashing(int hsize) {
        this.buckets = new Integer[hsize];
        this.hsize = hsize;
        this.AVAILABLE = Integer.MIN_VALUE;
        this.size = 0;
        this.thresh = (int) (Math.log(hsize) / Math.log(2)) + 2;
    }

    /**
     * The 2 Hash Functions takes a given key and finds an index based on its data, 2 distinctive ways to minimize collisions
     *
     * @param key the desired key to be converted
     * @return int an index corresponding to the key
     */

    public int hashing1(int key) {
        int hash = key % hsize;
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    public int hashing2(int key) {
        int hash = key / hsize;
        hash %= hsize;
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    /**
     * inserts the key into the hash map by wrapping it as an Integer object, then uses first hash function to insert
     *
     * @param key the desired key to be inserted in the hash map
     */

    public void insertHash(int key) {
        if (isFull()) System.out.println("Hash table is full\n");
        else {
            size++;
            ReHash(key, 2);
        }
    }

    /**
     * rehashes a key with current position relating to hash function indicated by currHash.
     * inserts the key into the hash map by wrapping it as an Integer object, then uses other hash function to push to next position.
     * If next pos is not empty, it rehashes the current occupant to his other hash, and does that recursively until all keys have good indices.
     *
     * @param key the desired key to be inserted in the hash map
     */
    public void ReHash(int key, int currHash) {
        Integer wrappedInt = key;
        int hash;

        if (currHash != 1 && currHash != 2) {
            System.out.println("curr hash should be 1 or 2\n");
            return;
        }
        if (isFull()) {
            System.out.println("Hash table is full\n");
            return;
        }

        if (currHash == 1) hash = hashing2(key);
        else hash = hashing1(key);

        if (buckets[hash] == null || buckets[hash] == AVAILABLE) {
            buckets[hash] = wrappedInt;
        } else {
            int pushedInt = buckets[hash];
            buckets[hash] = wrappedInt;
            if (hash == hashing1(pushedInt)) {
                ReHash(pushedInt, 1);
            } else if (hash == hashing2(pushedInt)) {
                ReHash(pushedInt, 2);
            }
        }
    }

    public void insertHash0(int key) {
        Integer wrappedInt = key, temp;
        int hash, maxloop = 0;

        if (isFull()) {
            System.out.println("Hash table is full\n");
            return;
        }

        if (contains(key)) {
            System.out.println("Key already inside, no duplicates allowed\n");
            return;
        }

        while (maxloop <= thresh) {
            maxloop++;
            hash = hashing1(key);

            if (buckets[hash] == null || buckets[hash] == AVAILABLE) {
                buckets[hash] = wrappedInt;
                size++;
                return;
            }

            temp = buckets[hash];
            buckets[hash] = wrappedInt;
            wrappedInt = temp;
            hash = hashing2(temp);
            if (buckets[hash] == null || buckets[hash] == AVAILABLE) {
                buckets[hash] = wrappedInt;
                size++;
                return;
            }

            temp = buckets[hash];
            buckets[hash] = wrappedInt;
            wrappedInt = temp;
        }
        System.out.println("Need to rehash table\n");
        ReHashTable();
        insertHash0(key);
    }

    public void ReHashTable() {
        HashMapCuckooHashing newT = new HashMapCuckooHashing(hsize * 2);
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null && buckets[i] != AVAILABLE) {
                newT.insertHash0(this.buckets[i]);
            }
        }
        this.hsize *= 2;
        this.buckets = newT.buckets;
        this.thresh = (int) (Math.log(hsize) / Math.log(2)) + 2;
    }


    /**
     * deletes a key from the hash map and adds an available placeholder
     *
     * @param key the desired key to be deleted
     */
    public void deleteHash(int key) {
        Integer wrappedInt = key;
        int hash = hashing1(key);
        if (isEmpty()) {
            System.out.println("Table is empty");
            return;
        }

        if (buckets[hash] == wrappedInt) {
            buckets[hash] = AVAILABLE;
            return;
        }

        hash = hashing2(key);
        if (buckets[hash] == wrappedInt) {
            buckets[hash] = AVAILABLE;
            return;
        }

        System.out.println("Key " + key + " not found\n");
    }

    /**
     * Displays the hash table line by line
     */
    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i] == AVAILABLE) {
                System.out.println("Bucket " + i + ": Empty");
            } else {
                System.out.println("Bucket " + i + ": " + buckets[i].toString());
            }
        }
        System.out.println();
    }

    /**
     * Finds the index of location based on an inputed key
     *
     * @param key the desired key to be found
     * @return int the index where the key is located
     */
    public int findHash(int key) {
        Integer wrappedInt = key;
        int hash = hashing1(key);

        if (isEmpty()) {
            System.out.println("Table is empty");
            return -1;
        }

        if (buckets[hash] == wrappedInt) return hash;

        hash = hashing2(key);
        if (buckets[hash] == wrappedInt) return hash;

        System.out.println("Key " + key + " not found\n");
        return -1;
    }

    public boolean contains(int key){
        if (buckets[hashing1(key)] != null) if (buckets[hashing1(key)] == key || buckets[hashing1(key)] == key) return true;
        return false;
    }

    /**
     * Checks the load factor of the hash table if greater than .7,
     * automatically lengthens table to prevent further collisions
     */
    public void checkLoadFactor() {
        double factor = (double) size / hsize;
        if (factor > .7) {
            System.out.println("Load factor is " + factor + ",  lengthening table");
            System.out.println("Need to Rehash Table\n");
            ReHashTable();
        } else {
            System.out.println("Load factor is " + factor);
        }
    }

    /**
     * isFull returns true if the hash map is full and false if not full
     *
     * @return boolean is Empty
     */
    public boolean isFull() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i] == AVAILABLE) {
                response = false;
                break;
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
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }
}
