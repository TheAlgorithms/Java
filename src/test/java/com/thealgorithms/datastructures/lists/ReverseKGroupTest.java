package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
/**
 * Test cases for Reverse K Group LinkedList
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
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
    public void testReverseKGroupWithKEqualTo2() {
        ReverseKGroup reverser = new ReverseKGroup();

        // Create a list with multiple elements (1 -> 2 -> 3 -> 4 -> 5)
        Node head;
        head = new Node(1);
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
    }

    @Test
    public void testReverseKGroupWithKEqualTo3() {
        ReverseKGroup reverser = new ReverseKGroup();

        // Create a list with multiple elements (1 -> 2 -> 3 -> 4 -> 5)
        Node head;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Test reverse with k=3
        Node result = reverser.reverseKGroup(head, 3);
        assertEquals(3, result.value);
        assertEquals(2, result.next.value);
        assertEquals(1, result.next.next.value);
        assertEquals(4, result.next.next.next.value);
        assertEquals(5, result.next.next.next.next.value);
        assertNull(result.next.next.next.next.next);
    }
}
