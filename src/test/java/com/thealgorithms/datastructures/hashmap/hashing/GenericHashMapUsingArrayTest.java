package com.thealgorithms.datastructures.hashmap.hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GenericHashMapUsingArrayTest {

    @Test
    void testGenericHashmapWhichUsesArrayAndBothKeyAndValueAreStrings() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("USA", "Washington DC");
        map.put("Nepal", "Kathmandu");
        map.put("India", "New Delhi");
        map.put("Australia", "Sydney");
        Assertions.assertNotNull(map);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals("Kathmandu", map.get("Nepal"));
        Assertions.assertEquals("Sydney", map.get("Australia"));
    }

    @Test
    void testGenericHashmapWhichUsesArrayAndKeyIsStringValueIsInteger() {
        GenericHashMapUsingArray<String, Integer> map = new GenericHashMapUsingArray<>();
        map.put("USA", 87);
        map.put("Nepal", 25);
        map.put("India", 101);
        map.put("Australia", 99);
        Assertions.assertNotNull(map);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals(25, map.get("Nepal"));
        Assertions.assertEquals(99, map.get("Australia"));
        map.remove("Nepal");
        Assertions.assertFalse(map.containsKey("Nepal"));
    }

    @Test
    void testGenericHashmapWhichUsesArrayAndKeyIsIntegerValueIsString() {
        GenericHashMapUsingArray<Integer, String> map = new GenericHashMapUsingArray<>();
        map.put(101, "Washington DC");
        map.put(34, "Kathmandu");
        map.put(46, "New Delhi");
        map.put(89, "Sydney");
        Assertions.assertNotNull(map);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals("Sydney", map.get(89));
        Assertions.assertEquals("Washington DC", map.get(101));
        Assertions.assertTrue(map.containsKey(46));
    }

    @Test
    void testRemoveNonExistentKey() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("USA", "Washington DC");
        map.remove("Nepal"); // Attempting to remove a non-existent key
        Assertions.assertEquals(1, map.size()); // Size should remain the same
    }

    @Test
    void testRehashing() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        for (int i = 0; i < 20; i++) {
            map.put("Key" + i, "Value" + i);
        }
        Assertions.assertEquals(20, map.size()); // Ensure all items were added
        Assertions.assertEquals("Value5", map.get("Key5")); // Check retrieval after rehash
    }

    @Test
    void testUpdateValueForExistingKey() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("USA", "Washington DC");
        map.put("USA", "New Washington DC"); // Updating value for existing key
        Assertions.assertEquals("New Washington DC", map.get("USA"));
    }

    @Test
    void testToStringMethod() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("USA", "Washington DC");
        map.put("Nepal", "Kathmandu");
        String expected = "{USA : Washington DC, Nepal : Kathmandu}";
        Assertions.assertEquals(expected, map.toString());
    }

    @Test
    void testContainsKey() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("USA", "Washington DC");
        Assertions.assertTrue(map.containsKey("USA"));
        Assertions.assertFalse(map.containsKey("Nepal"));
    }

    // ======= Added tests from the new version =======

    @Test
    void shouldThrowNullPointerExceptionForNullKey() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        String nullKey = null; // Use variable to avoid static analysis false positive
        Assertions.assertThrows(NullPointerException.class, () -> map.put(nullKey, "value"));
    }

    @Test
    void shouldStoreNullValueForKey() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("keyWithNullValue", null);
        Assertions.assertEquals(1, map.size());
        Assertions.assertNull(map.get("keyWithNullValue"));
        // Note: containsKey returns false for null values due to implementation
        Assertions.assertFalse(map.containsKey("keyWithNullValue"));
    }

    @Test
    void shouldHandleCollisionWhenKeysHashToSameBucket() {
        GenericHashMapUsingArray<Integer, Integer> map = new GenericHashMapUsingArray<>();
        Integer key1 = 1;
        Integer key2 = 17;
        map.put(key1, 100);
        map.put(key2, 200);
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(100, map.get(key1));
        Assertions.assertEquals(200, map.get(key2));
        Assertions.assertTrue(map.containsKey(key1));
        Assertions.assertTrue(map.containsKey(key2));
    }

    @Test
    void shouldHandleEmptyStringAsKey() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("", "valueForEmptyKey");
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals("valueForEmptyKey", map.get(""));
        Assertions.assertTrue(map.containsKey(""));
    }

    @Test
    void shouldHandleEmptyStringAsValue() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("keyForEmptyValue", "");
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals("", map.get("keyForEmptyValue"));
        Assertions.assertTrue(map.containsKey("keyForEmptyValue"));
    }

    @Test
    void shouldHandleNegativeIntegerKeys() {
        GenericHashMapUsingArray<Integer, Integer> map = new GenericHashMapUsingArray<>();
        map.put(-1, 100);
        map.put(-100, 200);
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(100, map.get(-1));
        Assertions.assertEquals(200, map.get(-100));
        Assertions.assertTrue(map.containsKey(-1));
        Assertions.assertTrue(map.containsKey(-100));
    }

    @Test
    void shouldHandleZeroAsKey() {
        GenericHashMapUsingArray<Integer, Integer> map = new GenericHashMapUsingArray<>();
        map.put(0, 100);
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(100, map.get(0));
        Assertions.assertTrue(map.containsKey(0));
    }

    @Test
    void shouldHandleStringWithSpecialCharacters() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("key!@#$%^&*()", "value<>?/\\|");
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals("value<>?/\\|", map.get("key!@#$%^&*()"));
        Assertions.assertTrue(map.containsKey("key!@#$%^&*()"));
    }

    @Test
    void shouldHandleLongStrings() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        StringBuilder longKey = new StringBuilder();
        StringBuilder longValue = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longKey.append("a");
            longValue.append("b");
        }
        String key = longKey.toString();
        String value = longValue.toString();
        map.put(key, value);
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(value, map.get(key));
        Assertions.assertTrue(map.containsKey(key));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "test", "longerString"})
    void shouldHandleKeysOfDifferentLengths(String key) {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put(key, "value");
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals("value", map.get(key));
        Assertions.assertTrue(map.containsKey(key));
    }

    @Test
    void shouldHandleUpdateOnExistingKeyInCollisionBucket() {
        GenericHashMapUsingArray<Integer, Integer> map = new GenericHashMapUsingArray<>();
        Integer key1 = 1;
        Integer key2 = 17;
        map.put(key1, 100);
        map.put(key2, 200);
        Assertions.assertEquals(2, map.size());
        map.put(key2, 999);
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(100, map.get(key1));
        Assertions.assertEquals(999, map.get(key2));
        Assertions.assertTrue(map.containsKey(key1));
        Assertions.assertTrue(map.containsKey(key2));
    }

    @Test
    void shouldHandleExactlyLoadFactorBoundary() {
        GenericHashMapUsingArray<Integer, Integer> map = new GenericHashMapUsingArray<>();
        // Fill exactly to load factor (12 items with capacity 16 and 0.75 load factor)
        for (int i = 0; i < 12; i++) {
            map.put(i, i * 10);
        }
        Assertions.assertEquals(12, map.size());
        // Act - This should trigger rehash on 13th item
        map.put(12, 120);
        // Assert - Rehash should have happened
        Assertions.assertEquals(13, map.size());
        Assertions.assertEquals(120, map.get(12));
        Assertions.assertTrue(map.containsKey(12));
    }
}
