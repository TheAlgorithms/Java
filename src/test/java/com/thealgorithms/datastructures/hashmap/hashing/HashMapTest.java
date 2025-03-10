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
        assertNull(hashMap.search(45)); // Test for non-existent key
    }

    @Test
    public void testDelete() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, "Value15");
        hashMap.insert(25, "Value25");
        hashMap.insert(35, "Value35");

        assertEquals("Value25", hashMap.search(25));
        hashMap.delete(25);
        assertNull(hashMap.search(25)); // Confirm deletion
    }

    @Test
    public void testDisplay() {
        HashMap<Integer, String> hashMap = new HashMap<>(5);
        hashMap.insert(15, "Value15");
        hashMap.insert(25, "Value25");
        hashMap.insert(35, "Value35");
        // Optionally verify display functionality if it returns a string
        hashMap.display(); // Manual check during test execution
    }

    @Test
    public void testInsertNullKey() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(null, "NullValue");
        assertEquals("NullValue", hashMap.search(null)); // Verify null key handling
    }

    @Test
    public void testInsertNullValue() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, null);
        assertNull(hashMap.search(15)); // Verify null value handling
    }

    @Test
    public void testUpdateExistingKey() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, "Value15");
        hashMap.insert(15, "UpdatedValue15");

        assertEquals("UpdatedValue15", hashMap.search(15)); // Verify update
    }

    @Test
    public void testHandleCollisions() {
        HashMap<Integer, String> hashMap = new HashMap<>(3); // Create a small bucket size to force collisions
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
        assertNull(hashMap.search(10)); // Confirm search returns null in empty map
    }

    @Test
    public void testDeleteNonExistentKey() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(15, "Value15");
        hashMap.delete(25); // Delete non-existent key

        assertEquals("Value15", hashMap.search(15)); // Ensure existing key remains
        assertNull(hashMap.search(25)); // Confirm non-existent key remains null
    }

    @Test
    public void testInsertLargeNumberOfElements() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        for (int i = 0; i < 100; i++) {
            hashMap.insert(i, "Value" + i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("Value" + i, hashMap.search(i)); // Verify all inserted values
        }
    }

    @Test
    public void testDeleteHeadOfBucket() {
        HashMap<Integer, String> hashMap = new HashMap<>(3);
        hashMap.insert(1, "Value1");
        hashMap.insert(4, "Value4");
        hashMap.insert(7, "Value7");

        hashMap.delete(1);
        assertNull(hashMap.search(1)); // Verify head deletion
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
        assertNull(hashMap.search(7)); // Verify tail deletion
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
        assertNull(hashMap.search(4)); // Verify middle element deletion
        assertEquals("Value1", hashMap.search(1));
        assertEquals("Value7", hashMap.search(7));
    }

    @Test
    public void testResizeHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>(2); // Small initial size to force rehashing
        for (int i = 0; i < 10; i++) {
            hashMap.insert(i, "Value" + i);
        }

        // Verify all values after resizing
        for (int i = 0; i < 10; i++) {
            assertEquals("Value" + i, hashMap.search(i));
        }
    }

    @Test
    public void testCollisionResolution() {
        HashMap<String, String> hashMap = new HashMap<>(3);
        hashMap.insert("abc", "Value1"); // Hash index 0
        hashMap.insert("cab", "Value2"); // Hash index 0 (collision)
        hashMap.insert("bac", "Value3"); // Hash index 0 (collision)

        assertEquals("Value1", hashMap.search("abc"));
        assertEquals("Value2", hashMap.search("cab"));
        assertEquals("Value3", hashMap.search("bac"));
    }

    @Test
    public void testClearHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        hashMap.insert(1, "Value1");
        hashMap.insert(2, "Value2");

        hashMap.clear(); // Assuming clear method resets the hash map
        assertNull(hashMap.search(1));
        assertNull(hashMap.search(2));
        assertEquals(0, hashMap.size()); // Verify size is reset
    }
}
