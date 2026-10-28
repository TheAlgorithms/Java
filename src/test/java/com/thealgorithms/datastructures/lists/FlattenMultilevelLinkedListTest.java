package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
/**
 * Unit tests for the FlattenMultilevelLinkedList class.
 * This class tests the flattening logic with various list structures,
 * including null lists, simple lists, and complex multilevel lists.
 */
final class FlattenMultilevelLinkedListTest {

    // A helper function to convert a flattened list (connected by child pointers)
    // into a standard Java List for easy comparison.
    private List<Integer> toList(FlattenMultilevelLinkedList.Node head) {
        List<Integer> list = new ArrayList<>();
        FlattenMultilevelLinkedList.Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.child;
        }
        return list;
    }

    @Test
    @DisplayName("Test with a null list")
    void testFlattenNullList() {
        assertNull(FlattenMultilevelLinkedList.flatten(null));
    }

    @Test
    @DisplayName("Test with a simple, single-level list")
    void testFlattenSingleLevelList() {
        // Create a simple list: 1 -> 2 -> 3
        FlattenMultilevelLinkedList.Node head = new FlattenMultilevelLinkedList.Node(1);
        head.next = new FlattenMultilevelLinkedList.Node(2);
        head.next.next = new FlattenMultilevelLinkedList.Node(3);

        // Flatten the list
        FlattenMultilevelLinkedList.Node flattenedHead = FlattenMultilevelLinkedList.flatten(head);

        // Expected output: 1 -> 2 -> 3 (vertically)
        List<Integer> expected = List.of(1, 2, 3);
        assertEquals(expected, toList(flattenedHead));
    }

    @Test
    @DisplayName("Test with a complex multilevel list")
    void testFlattenComplexMultilevelList() {
        // Create the multilevel structure from the problem description
        // 5 -> 10 -> 19 -> 28
        // |    |     |     |
        // 7    20    22    35
        // |          |     |
        // 8          50    40
        // |                |
        // 30               45
        FlattenMultilevelLinkedList.Node head = new FlattenMultilevelLinkedList.Node(5);
        head.child = new FlattenMultilevelLinkedList.Node(7);
        head.child.child = new FlattenMultilevelLinkedList.Node(8);
        head.child.child.child = new FlattenMultilevelLinkedList.Node(30);

        head.next = new FlattenMultilevelLinkedList.Node(10);
        head.next.child = new FlattenMultilevelLinkedList.Node(20);

        head.next.next = new FlattenMultilevelLinkedList.Node(19);
        head.next.next.child = new FlattenMultilevelLinkedList.Node(22);
        head.next.next.child.child = new FlattenMultilevelLinkedList.Node(50);

        head.next.next.next = new FlattenMultilevelLinkedList.Node(28);
        head.next.next.next.child = new FlattenMultilevelLinkedList.Node(35);
        head.next.next.next.child.child = new FlattenMultilevelLinkedList.Node(40);
        head.next.next.next.child.child.child = new FlattenMultilevelLinkedList.Node(45);

        // Flatten the list
        FlattenMultilevelLinkedList.Node flattenedHead = FlattenMultilevelLinkedList.flatten(head);

        // Expected sorted output
        List<Integer> expected = List.of(5, 7, 8, 10, 19, 20, 22, 28, 30, 35, 40, 45, 50);
        assertEquals(expected, toList(flattenedHead));
    }

    @Test
    @DisplayName("Test with some empty child lists")
    void testFlattenWithEmptyChildLists() {
        // Create a list: 5 -> 10 -> 12
        // |           |
        // 7           11
        // |
        // 9
        FlattenMultilevelLinkedList.Node head = new FlattenMultilevelLinkedList.Node(5);
        head.child = new FlattenMultilevelLinkedList.Node(7);
        head.child.child = new FlattenMultilevelLinkedList.Node(9);

        head.next = new FlattenMultilevelLinkedList.Node(10); // No child list
        head.next.child = null;

        head.next.next = new FlattenMultilevelLinkedList.Node(12);
        head.next.next.child = new FlattenMultilevelLinkedList.Node(16);

        // Flatten the list
        FlattenMultilevelLinkedList.Node flattenedHead = FlattenMultilevelLinkedList.flatten(head);

        // Expected sorted output
        List<Integer> expected = List.of(5, 7, 9, 10, 12, 16);
        assertEquals(expected, toList(flattenedHead));
    }
}
