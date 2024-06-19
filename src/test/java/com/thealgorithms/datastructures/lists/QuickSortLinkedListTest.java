package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
/**
 * Test cases for QuickSortLinkedList
 * Author: Prabhat-Kumar-42
 * GitHub: https://github.com/Prabhat-Kumar-42
 */
public class QuickSortLinkedListTest {

    @Test
    public void testSortEmptyList() {
        SinglyLinkedList emptyList = new SinglyLinkedList();
        QuickSortLinkedList sorter = new QuickSortLinkedList(emptyList);

        // Test case: Sorting an empty list should result in an empty list
        sorter.sortList();
        assertNull(emptyList.getHead());
    }

    @Test
    public void testSortSingleNodeList() {
        SinglyLinkedList singleNodeList = new SinglyLinkedList();
        singleNodeList.insert(5);
        QuickSortLinkedList sorter = new QuickSortLinkedList(singleNodeList);

        // Test case: Sorting a list with a single node should result in the same list
        sorter.sortList();
        assertEquals(5, singleNodeList.getHead().value);
        assertNull(singleNodeList.getHead().next);
    }

    @Test
    public void testSortMultipleElementsList() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insert(5);
        list.insert(3);
        list.insert(8);
        list.insert(1);
        list.insert(10);
        list.insert(2);
        list.insert(7);
        list.insert(4);
        list.insert(9);
        list.insert(6);
        QuickSortLinkedList sorter = new QuickSortLinkedList(list);

        // Test case: Sorting a list with multiple elements
        sorter.sortList();
        assertEquals(1, list.getHead().value);
        assertEquals(2, list.getHead().next.value);
        assertEquals(3, list.getHead().next.next.value);
        assertEquals(4, list.getHead().next.next.next.value);
        assertEquals(5, list.getHead().next.next.next.next.value);
        assertEquals(6, list.getHead().next.next.next.next.next.value);
        assertEquals(7, list.getHead().next.next.next.next.next.next.value);
        assertEquals(8, list.getHead().next.next.next.next.next.next.next.value);
        assertEquals(9, list.getHead().next.next.next.next.next.next.next.next.value);
        assertEquals(10, list.getHead().next.next.next.next.next.next.next.next.next.value);
        assertNull(list.getHead().next.next.next.next.next.next.next.next.next.next);
    }
}
