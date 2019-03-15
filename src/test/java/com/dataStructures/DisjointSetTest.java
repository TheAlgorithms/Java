package src.test.java.com.dataStructures;

import org.junit.Test;
import src.main.java.com.dataStructures.DisjointSet;

import static org.junit.Assert.*;

public class DisjointSetTest {
    @Test
    public void test() {
        DisjointSet<Object> set = new DisjointSet<>();

        set.makeSet("flink");
        set.makeSet("c++");
        set.makeSet("java");
        set.makeSet("py");
        set.makeSet("spark");

        set.union("java", "c++");

        assertTrue(set.isConnected("java", "c++"));
        assertFalse(set.isConnected("java", "py"));

        set.union("c++", "py");
        assertTrue(set.isConnected("java", "py"));

        set.makeSet("lisp");
        set.union("lisp", "py");

        assertTrue(set.isConnected("c++", "lisp"));

        set.show();
    }
}