package com.thealgorithms.datastructures.dictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DictionaryTest {

    @Test
    void testDictionaryOperations() {
        // Create dictionary with initial values
        DictionarySort dict = new DictionarySort("key1", "value1", "key2", "value2");

        // Test put and get
        Assertions.assertEquals("value1", dict.get("key1"));
        Assertions.assertEquals("value2", dict.get("key2"));
        dict.put("key3", "value3");
        Assertions.assertEquals("value3", dict.get("key3"));
        dict.put("key1", "updatedValue1");
        Assertions.assertEquals("updatedValue1", dict.get("key1"));

        // Test remove
        dict.remove("key1");
        Assertions.assertNull(dict.get("key1"));
        dict.remove("key1"); // Removing again should not cause issues
        Assertions.assertNull(dict.get("key1"));

        // Test containsKey
        Assertions.assertTrue(dict.containsKey("key2"));
        Assertions.assertFalse(dict.containsKey("key1"));

        // Test setSortStrategy and toString
        dict.setSortStrategy(DictionarySort.SortStrategy.KEY);
        String expectedKeySort = "{\n  \"key2\": \"value2\",\n  \"key3\": \"value3\"}"; // Adjusted based on your actual sorting implementation
        Assertions.assertEquals(expectedKeySort, dict.toString(true));

        dict.setSortStrategy(DictionarySort.SortStrategy.VALUE);
        String expectedValueSort = "{\n  \"key2\": \"value2\",\n  \"key3\": \"value3\"}"; // Adjusted based on your actual sorting implementation
        Assertions.assertEquals(expectedValueSort, dict.toString(true)); // Order might differ based on value sort

        // Test toString without formatting
        String expectedNoFormat = "{\"key2\": \"value2\", \"key3\": \"value3\"}"; // Adjusted based on your actual implementation
        Assertions.assertEquals(expectedNoFormat, dict.toString());

        // Test invalid constructor
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DictionarySort("key1", "value1", "key2");
        });
    }
}
