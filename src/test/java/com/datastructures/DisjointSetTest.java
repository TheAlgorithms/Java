package com.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DisjointSetTest {
    @Test
    void test() {
        DisjointSet<Object> set = new DisjointSet<>();

        set.makeSet("flink");
        set.makeSet("c++");
        set.makeSet("java");
        set.makeSet("py");
        set.makeSet("spark");

        set.union("java", "c++");

        Assertions.assertTrue(set.isConnected("java", "c++"));
        Assertions.assertFalse(set.isConnected("java", "py"));

        set.union("c++", "py");
        Assertions.assertTrue(set.isConnected("java", "py"));

        set.makeSet("lisp");
        set.union("lisp", "py");

        Assertions.assertTrue(set.isConnected("c++", "lisp"));

        set.show();
    }
}
