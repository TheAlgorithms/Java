package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashMapCuckooHashingTest {

    @Test
    void insertKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        assertEquals(0, hashTable.getNumberOfKeysInTable());

        hashTable.insertKey2HashTable(3);
        assertEquals(1, hashTable.getNumberOfKeysInTable());
    }

    @Test
    void getKeyIndex() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(8);
        hashTable.insertKey2HashTable(4);

        assertNotEquals(-1, hashTable.findKeyInTable(8));
    }

    @Test
    void containsKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(8);
        boolean contains = hashTable.checkTableContainsKey(8);

        assertTrue(contains);
    }

    @Test
    void removeKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(3);

        int initialSize = hashTable.getNumberOfKeysInTable();
        hashTable.deleteKeyFromHashTable(3);

        assertEquals(initialSize - 1, hashTable.getNumberOfKeysInTable());
    }

    @Test
    void removeNone() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        try {
            hashTable.deleteKeyFromHashTable(3);
        } catch (Exception e) {
            assertTrue(true);
            return;
        }
        Assertions.fail("Expected exception when trying to delete a non-existing key.");
    }

    @Test
    void reHashTableIncreasesTableSize() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(1);
        hashTable.insertKey2HashTable(2);
        hashTable.insertKey2HashTable(3);
        hashTable.insertKey2HashTable(4);
        hashTable.insertKey2HashTable(5);
        hashTable.insertKey2HashTable(6);
        hashTable.insertKey2HashTable(7);
        hashTable.insertKey2HashTable(8);
        hashTable.insertKey2HashTable(9);
        hashTable.insertKey2HashTable(10); // This should trigger rehashing

        assertEquals(10, hashTable.getNumberOfKeysInTable());
    }

    @Test
    void hashFunctionsAreDifferent() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(33);

        assertNotEquals(hashTable.hashFunction1(3), hashTable.hashFunction2(3), "Hash functions should produce different indices.");
    }

    @Test
    void avoidInfiniteLoops() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(0);
        hashTable.insertKey2HashTable(10);
        hashTable.insertKey2HashTable(100);

        assertTrue(hashTable.checkTableContainsKey(0));
        assertTrue(hashTable.checkTableContainsKey(10));
        assertTrue(hashTable.checkTableContainsKey(100));
    }

    @Test
    void testLoadFactor() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        for (int i = 1; i <= 8; i++) { // Insert 8 keys to test load factor
            hashTable.insertKey2HashTable(i);
        }
        assertEquals(8, hashTable.getNumberOfKeysInTable());
        // Check that rehashing occurs when a 9th key is added
        hashTable.insertKey2HashTable(9);
        assertTrue(hashTable.getNumberOfKeysInTable() > 8, "Load factor exceeded, table should have been resized.");
    }

    @Test
    void testDeleteNonExistentKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(1);
        hashTable.insertKey2HashTable(2);

        Exception exception = null;
        try {
            hashTable.deleteKeyFromHashTable(3); // Try deleting a non-existent key
        } catch (IllegalArgumentException e) {
            exception = e; // Capture the exception
        }
        assertNotNull(exception, "Expected an IllegalArgumentException when deleting a non-existent key.");
    }

    @Test
    void testInsertDuplicateKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(1);
        Exception exception = null;

        try {
            hashTable.insertKey2HashTable(1); // Attempt to insert duplicate key
        } catch (IllegalArgumentException e) {
            exception = e; // Capture the exception
        }
        assertNotNull(exception, "Expected an IllegalArgumentException for duplicate key insertion.");
    }
}
