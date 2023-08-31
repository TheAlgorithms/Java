package com.thealgorithms.datastructures.lists;

/**
 * Test case of Rotate a list
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RotateSinglyLinkedListsTest{

    @Test
    public void testRotateRight() {
        RotateSinglyLinkedLists rotator = new RotateSinglyLinkedLists();

        // Test case 1: Rotate an empty list
        assertNull(rotator.rotateRight(null, 2));

        // Test case 2: Rotate a list with one element
        Node singleNode = new Node(5);
        Node rotatedSingleNode = rotator.rotateRight(singleNode, 3);
        assertEquals(5, rotatedSingleNode.value);
        assertNull(rotatedSingleNode.next);

        // Test case 3: Rotate a list with multiple elements
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Rotate by 2
        Node rotated1 = rotator.rotateRight(head, 2);
        assertEquals(4, rotated1.value);
        assertEquals(5, rotated1.next.value);
        assertEquals(1, rotated1.next.next.value);
        assertEquals(2, rotated1.next.next.next.value);
        assertEquals(3, rotated1.next.next.next.next.value);
        assertNull(rotated1.next.next.next.next.next);

        
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        
        // Rotate by 1
        Node rotated2 = rotator.rotateRight(head, 1);
        assertEquals(5, rotated2.value);
        assertEquals(1, rotated2.next.value);
        assertEquals(2, rotated2.next.next.value);
        assertEquals(3, rotated2.next.next.next.value);
        assertEquals(4, rotated2.next.next.next.next.value);
        assertNull(rotated2.next.next.next.next.next);

        // rotate by 7
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        
        // Rotate by 1
        Node rotated3 = rotator.rotateRight(head, 7);
        assertEquals(4, rotated3.value);
        assertEquals(5, rotated3.next.value);
        assertEquals(1, rotated3.next.next.value);
        assertEquals(2, rotated3.next.next.next.value);
        assertEquals(3, rotated3.next.next.next.next.value);
        assertNull(rotated3.next.next.next.next.next);
    }
}