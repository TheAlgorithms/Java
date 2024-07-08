package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LeonardoHeapTest {

    @Test
    public void testEmpty() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<Integer>();
        assertEquals(0, heap.getHeapsize());
    }

    @Test
    public void testAddElement() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<>();
        heap.addElement(5);
        heap.addElement(3);
        heap.addElement(8);

        assertEquals(3, heap.getHeapsize());
        assertEquals(8, heap.removeElement()); // Max element should be 8
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
    public void testHeapSize() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<>();
        assertEquals(0, heap.getHeapsize());

        heap.addElement(1);
        assertEquals(1, heap.getHeapsize());

        heap.addElement(2);
        assertEquals(2, heap.getHeapsize());

        heap.removeElement();
        assertEquals(1, heap.getHeapsize());

        heap.removeElement();
        assertEquals(0, heap.getHeapsize());
    }

    @Test
    public void testAddElementStrings() {
        LeonardoHeap<String> heap = new LeonardoHeap<String>();
        heap.addElement("z");
        heap.addElement("a");
        heap.addElement("x");
        heap.addElement("b");
        heap.addElement("y");

        assertEquals(5, heap.getHeapsize());
        assertEquals("z", heap.removeElement()); // Max element should be 8
    }

    @Test
    public void testRemoveElementString() {
        LeonardoHeap<String> heap = new LeonardoHeap<String>();
        heap.addElement("z");
        heap.addElement("a");
        heap.addElement("x");

        assertEquals("z", heap.removeElement());
        assertEquals("x", heap.removeElement());
        assertEquals("a", heap.removeElement());
    }

    @Test
    public void testHeapSizeString() {
        LeonardoHeap<String> heap = new LeonardoHeap<String>();
        assertEquals(0, heap.getHeapsize());

        heap.addElement("z");
        assertEquals(1, heap.getHeapsize());

        heap.addElement("a");
        assertEquals(2, heap.getHeapsize());

        heap.removeElement();
        assertEquals(1, heap.getHeapsize());

        heap.removeElement();
        assertEquals(0, heap.getHeapsize());
    }
}
