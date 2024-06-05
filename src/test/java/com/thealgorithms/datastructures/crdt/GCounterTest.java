package com.thealgorithms.datastructures.crdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GCounterTest {
    @Test
    void increment() {
        GCounter counter = new GCounter(0, 3);
        counter.increment();
        counter.increment();
        counter.increment();
        assertEquals(3, counter.value());
    }

    @Test
    void merge() {
        GCounter counter1 = new GCounter(0, 3);
        counter1.increment();
        GCounter counter2 = new GCounter(1, 3);
        counter2.increment();
        counter2.increment();
        GCounter counter3 = new GCounter(2, 3);
        counter3.increment();
        counter3.increment();
        counter3.increment();
        counter1.merge(counter2);
        counter1.merge(counter3);
        counter2.merge(counter1);
        counter3.merge(counter2);
        assertEquals(6, counter1.value());
        assertEquals(6, counter2.value());
        assertEquals(6, counter3.value());
    }

    @Test
    void compare() {
        GCounter counter1 = new GCounter(0, 5);
        GCounter counter2 = new GCounter(3, 5);
        counter1.increment();
        counter1.increment();
        counter2.merge(counter1);
        counter2.increment();
        counter2.increment();
        assertTrue(counter1.compare(counter2));
        counter1.increment();
        counter2.increment();
        counter2.merge(counter1);
        assertTrue(counter1.compare(counter2));
        counter1.increment();
        assertFalse(counter1.compare(counter2));
    }
}
