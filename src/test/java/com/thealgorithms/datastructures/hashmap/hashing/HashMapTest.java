package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class HashMapTest {

    @Test
    public void testInsertAndSearch() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, "Value15");
        hashMap.insert(25, "Value25");
        hashMap.insert(35, "Value35");

        assertEquals("Value15", hashMap.search(15));
        assertEquals("Value25", hashMap.search(25));
        assertEquals("Value35", hashMap.search(35));
        assertNull(hashMap.search(45));
    }

    @Test
    public void testDelete() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, "Value15");
        hashMap.insert(25, "Value25");
        hashMap.insert(35, "Value35");

        assertEquals("Value25", hashMap.search(25));
        hashMap.delete(25);
        assertNull(hashMap.search(25));
    }

    @Test
    public void testDisplay() {
        HashMap<Integer, String> hashMap = new HashMap<>(5);
        hashMap.insert(15, "Value15");
        hashMap.insert(25, "Value25");
        hashMap.insert(35, "Value35");
        hashMap.display();
    }

    @Test
    public void testInsertNullKey() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(null, "NullValue");
        assertEquals("NullValue", hashMap.search(null));
    }

    @Test
    public void testInsertNullValue() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, null);
        assertNull(hashMap.search(15));
    }

    @Test
    public void testUpdateExistingKey() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, "Value15");
        hashMap.insert(15, "UpdatedValue15");

        assertEquals("UpdatedValue15", hashMap.search(15));
    }

    @Test
    public void testHandleCollisions() {
        HashMap<Integer, String> hashMap = new HashMap<>(3);
        // These keys should collide if the hash function is modulo 3
        hashMap.insert(1, "Value1");
        hashMap.insert(4, "Value4");
        hashMap.insert(7, "Value7");

        assertEquals("Value1", hashMap.search(1));
        assertEquals("Value4", hashMap.search(4));
        assertEquals("Value7", hashMap.search(7));
    }

    @Test
    public void testSearchInEmptyHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        assertNull(hashMap.search(10));
    }

    @Test
    public void testDeleteNonExistentKey() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, "Value15");
        hashMap.delete(25);

        assertEquals("Value15", hashMap.search(15));
        assertNull(hashMap.search(25));
    }

    @Test
    public void testInsertLargeNumberOfElements() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        for (int i = 0; i < 100; i++) {
            hashMap.insert(i, "Value" + i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("Value" + i, hashMap.search(i));
        }
    }

    @Test
    public void testDeleteHeadOfBucket() {
        HashMap<Integer, String> hashMap = new HashMap<>(3);
        hashMap.insert(1, "Value1");
        hashMap.insert(4, "Value4");
        hashMap.insert(7, "Value7");

        hashMap.delete(1);
        assertNull(hashMap.search(1));
        assertEquals("Value4", hashMap.search(4));
        assertEquals("Value7", hashMap.search(7));
    }

    @Test
    public void testDeleteTailOfBucket() {
        HashMap<Integer, String> hashMap = new HashMap<>(3);
        hashMap.insert(1, "Value1");
        hashMap.insert(4, "Value4");
        hashMap.insert(7, "Value7");

        hashMap.delete(7);
        assertNull(hashMap.search(7));
        assertEquals("Value1", hashMap.search(1));
        assertEquals("Value4", hashMap.search(4));
    }

    @Test
    public void testDeleteMiddleElementOfBucket() {
        HashMap<Integer, String> hashMap = new HashMap<>(3);
        hashMap.insert(1, "Value1");
        hashMap.insert(4, "Value4");
        hashMap.insert(7, "Value7");

        hashMap.delete(4);
        assertNull(hashMap.search(4));
        assertEquals("Value1", hashMap.search(1));
        assertEquals("Value7", hashMap.search(7));
    }
}
