package com.thealgorithms.datastructures.lists;

/**
 * Test cases for Reverse K Group LinkedList
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ReverseKGroupTest {

    @Test
    public void testReverseKGroupWithEmptyList() {
        ReverseKGroup reverser = new ReverseKGroup();
        assertNull(reverser.reverseKGroup(null, 3));
    }

    @Test
    public void testReverseKGroupWithSingleNodeList() {
        ReverseKGroup reverser = new ReverseKGroup();
        Node singleNode = new Node(5);
        Node result = reverser.reverseKGroup(singleNode, 2);
        assertEquals(5, result.value);
        assertNull(result.next);
    }

    @Test
    public void testReverseKGroupWithMultipleElementsList() {
        ReverseKGroup reverser = new ReverseKGroup();
        
        // Create a list with multiple elements (1 -> 2 -> 3 -> 4 -> 5)
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Test reverse with k=2
        Node result1 = reverser.reverseKGroup(head, 2);
        assertEquals(2, result1.value);
        assertEquals(1, result1.next.value);
        assertEquals(4, result1.next.next.value);
        assertEquals(3, result1.next.next.next.value);
        assertEquals(5, result1.next.next.next.next.value);
        assertNull(result1.next.next.next.next.next);
        
        // Test reverse with k=3
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        
        Node result2 = reverser.reverseKGroup(head2, 3);
        assertEquals(3, result2.value);
        assertEquals(2, result2.next.value);
        assertEquals(1, result2.next.next.value);
        assertEquals(4, result2.next.next.next.value);
        assertEquals(5, result2.next.next.next.next.value);
        assertNull(result2.next.next.next.next.next);
    }
}
