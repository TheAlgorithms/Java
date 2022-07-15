package com.thealgorithms.datastructures.hashmap;

import com.thealgorithms.datastructures.hashmap.hashing.HashMapCuckooHashing;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MainCuckooHashingTest {

    @Test
    void insertKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        assertEquals(0, hashTable.getSize());

        hashTable.insertHash(3);

        hashTable.displayHashtable();
        assertEquals(1, hashTable.getSize());
    }

    @Test
    void getKeyIndex() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertHash(8);
        hashTable.insertHash(4);

        int val = hashTable.findHash(8);
        hashTable.displayHashtable();
        assertNotEquals(-1, hashTable.findHash(8));
    }

    @Test
    void containsKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.displayHashtable();
        hashTable.insertHash(8);
        boolean contains = hashTable.contains(8);

        assertTrue(contains);
    }

    @Test
    void removeKey() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertHash(3);
        hashTable.displayHashtable();
        int initialSize = hashTable.getSize();

        hashTable.deleteHash(3);

        hashTable.displayHashtable();
        assertEquals(initialSize - 1, hashTable.getSize());
    }

    @Test
    void removeNone() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.displayHashtable();
        int initialSize = hashTable.getSize();

        hashTable.deleteHash(3);

        hashTable.displayHashtable();
        assertEquals(initialSize, hashTable.getSize());
    }

    @Test
    void reHashTableIncreasesTableSize() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.displayHashtable();
        int initialSize = hashTable.getSize();

        hashTable.reHashTable();

        hashTable.displayHashtable();
        assertEquals(initialSize * 2, hashTable.getSize());
    }

    @Test
    void hashFunctionsAreDifferent() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertHash(33);

        assertNotEquals(hashTable.hashing1(3), hashTable.hashing2(3));
    }

    @Test
    void avoidInfiniteLoops() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        hashTable.insertHash(0);
        hashTable.displayHashtable();
        hashTable.insertHash(10);
        hashTable.displayHashtable();
        hashTable.insertHash(100);
        hashTable.displayHashtable();

        assertTrue(hashTable.contains(0));
        assertTrue(hashTable.contains(10));
        assertTrue(hashTable.contains(100));
    }


    private HashMapCuckooHashing createHashMapCuckooHashing() {
        HashMapCuckooHashing hashTable = new HashMapCuckooHashing(10);
        int[] values = {11, 22, 33, 44, 55, 66, 77, 88, 99, 111, 222};
        Arrays.stream(values).forEach(hashTable::insertHash);
        return hashTable;
    }

}