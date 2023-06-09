package com.thealgorithms.datastructures.hashmap;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.datastructures.hashmap.hashing.HashMapCuckooHashing;
import java.util.*;
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
        int initialSize = hashTable.getNumberOfKeysInTable();
        try {
            hashTable.deleteKeyFromHashTable(3);
        } catch (Exception e) {
            assertTrue(true);
            return;
        }
        fail();
    }

    @Test
    void reHashTableIncreasesTableSize() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        int initialSize = hashTable.getNumberOfKeysInTable();

        hashTable.reHashTableIncreasesTableSize();

        assertEquals(initialSize * 2, hashTable.getNumberOfKeysInTable());
    }

    @Test
    void hashFunctionsAreDifferent() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertKey2HashTable(33);

        assertNotEquals(hashTable.hashFunction1(3), hashTable.hashFunction2(3));
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

    private HashMapCuckooHashing createHashMapCuckooHashing() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        int[] values = {11, 22, 33, 44, 55, 66, 77, 88, 99, 111, 222};
        Arrays.stream(values).forEach(hashTable::insertKey2HashTable);
        return hashTable;
    }
}
