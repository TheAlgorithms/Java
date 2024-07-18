package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class LeonardoHeapTest {

    @Test
    public void testAddElement() {
        LeonardoHeap<Integer> heap = new LeonardoHeap<>();
        heap.addElement(5);
        heap.addElement(3);
        heap.addElement(8);

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
    public void testAddElementStrings() {
        LeonardoHeap<String> heap = new LeonardoHeap<String>();
        heap.addElement("z");
        heap.addElement("a");
        heap.addElement("x");
        heap.addElement("b");
        heap.addElement("y");

        assertEquals("z", heap.removeElement()); // Max element should be z
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

        heap.addElement(5);
        heap.addElement(33);
        heap.addElement(40);
        heap.addElement(28);
        
        heap.addElement(95);
        heap.addElement(29);
        heap.addElement(88);
        heap.addElement(94);

        heap.addElement(12);
        heap.addElement(84);
        heap.addElement(15);
        heap.addElement(33);

        heap.addElement(2);
        heap.addElement(52);
        heap.addElement(37);
        heap.addElement(62);

        heap.addElement(48);
        heap.addElement(13);
        heap.addElement(61);
        heap.addElement(59);

        // Assert the top 4 elemets are extracted correctly
        assertEquals(95, heap.removeElement());
        assertEquals(94, heap.removeElement());
        assertEquals(88, heap.removeElement());
        assertEquals(84, heap.removeElement());
    }


}
