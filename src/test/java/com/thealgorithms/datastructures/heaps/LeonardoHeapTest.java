package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LeonardoHeapTest {

    @Test
    public void testAddElement() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<>();
        heap.addElement(5);
        heap.addElement(3);
        heap.addElement(8);

        assertEquals(8, heap.removeElement()); // Max element should be 8
        assertEquals(5, heap.removeElement());
        assertEquals(3, heap.removeElement());
    }

    @Test
    public void testRemoveElement() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<>();
        heap.addElement(10);
        heap.addElement(20);
        heap.addElement(5);

        assertEquals(20, heap.removeElement());
        assertEquals(10, heap.removeElement());
        assertEquals(5, heap.removeElement());
    }

    @Test
    public void testAddElementStrings() {
        LeonardoHeap<String> heap = new LeonardoHeap<>();
        heap.addElement("z");
        heap.addElement("a");
        heap.addElement("x");
        heap.addElement("b");
        heap.addElement("y");

        assertEquals("z", heap.removeElement()); // Max element should be z
        assertEquals("y", heap.removeElement());
        assertEquals("x", heap.removeElement());
        assertEquals("b", heap.removeElement());
        assertEquals("a", heap.removeElement());
    }

    @Test
    public void testRemoveElementString() {
        LeonardoHeap<String> heap = new LeonardoHeap<>();
        heap.addElement("z");
        heap.addElement("a");
        heap.addElement("x");

        assertEquals("z", heap.removeElement());
        assertEquals("x", heap.removeElement());
        assertEquals("a", heap.removeElement());
    }

    @Test
    public void testAlwaysCurrentMaxElementIsRemoved() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<>();
        heap.addElement(5);
        heap.addElement(8);
        heap.addElement(7);
        heap.addElement(3);

        heap.addElement(4);
        heap.addElement(4);
        heap.addElement(4);
        heap.addElement(6);

        heap.addElement(8);
        heap.addElement(8);

        assertEquals(8, heap.removeElement());
        assertEquals(8, heap.removeElement());
        assertEquals(8, heap.removeElement());
        assertEquals(7, heap.removeElement());

        assertEquals(6, heap.removeElement());
        assertEquals(5, heap.removeElement());
        assertEquals(4, heap.removeElement());
        assertEquals(4, heap.removeElement());

        assertEquals(4, heap.removeElement());
        assertEquals(3, heap.removeElement());
    }

    @Test
    public void testForCompareChildAndSwap() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<>();
        Integer[] elements = {5, 33, 40, 28, 95, 29, 88, 94, 12, 84, 15, 33, 2, 52, 37, 62, 48, 13, 61, 59};

        for (Integer element : elements) {
            heap.addElement(element);
        }

        assertEquals(95, heap.removeElement());
        assertEquals(94, heap.removeElement());
        assertEquals(88, heap.removeElement());
        assertEquals(84, heap.removeElement());
        assertEquals(62, heap.removeElement());
        assertEquals(61, heap.removeElement());
        assertEquals(59, heap.removeElement());
        assertEquals(52, heap.removeElement());
        assertEquals(48, heap.removeElement());
        assertEquals(40, heap.removeElement());
        assertEquals(37, heap.removeElement());
        assertEquals(33, heap.removeElement());
        assertEquals(33, heap.removeElement());
        assertEquals(29, heap.removeElement());
        assertEquals(28, heap.removeElement());
        assertEquals(15, heap.removeElement());
        assertEquals(13, heap.removeElement());
        assertEquals(12, heap.removeElement());
        assertEquals(5, heap.removeElement());
        assertEquals(2, heap.removeElement());
    }
}
