package com.thealgorithms.datastructures.dictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DictionaryTest {

    @Test
    void test() {
        Dictionary dict = new Dictionary("asdf", "a");
        Assertions.assertEquals(dict.get("asdf"), "a");
        dict.put("aa", "hello");
        Assertions.assertEquals(dict.get("aa"), "hello");
        dict.put("aa", "aaa");
        Assertions.assertEquals(dict.get("aa"), "aaa");
        dict.remove("aa");
        Assertions.assertEquals(dict.get("aa"), null);
        Assertions.assertEquals(dict.containsKey("aa"), false);
    }
}
