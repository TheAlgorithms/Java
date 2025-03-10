package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Test cases for QuickSortLinkedList.
 * Author: Prabhat-Kumar-42
 * GitHub: https://github.com/Prabhat-Kumar-42
 */
public class QuickSortLinkedListTest {

    @Test
    public void testSortEmptyList() {
        SinglyLinkedList emptyList = new SinglyLinkedList();
        QuickSortLinkedList sorter = new QuickSortLinkedList(emptyList);

        sorter.sortList();
        assertNull(emptyList.getHead(), "Sorted empty list should have no elements.");
    }

    @Test
    public void testSortSingleNodeList() {
        SinglyLinkedList singleNodeList = new SinglyLinkedList();
        singleNodeList.insert(5);
        QuickSortLinkedList sorter = new QuickSortLinkedList(singleNodeList);

        sorter.sortList();
        assertEquals(5, singleNodeList.getHead().value, "Single node list should remain unchanged after sorting.");
        assertNull(singleNodeList.getHead().next, "Single node should not have a next node.");
    }

    @Test
    public void testSortAlreadySorted() {
        SinglyLinkedList sortedList = new SinglyLinkedList();
        sortedList.insert(1);
        sortedList.insert(2);
        sortedList.insert(3);
        sortedList.insert(4);
        sortedList.insert(5);
        QuickSortLinkedList sorter = new QuickSortLinkedList(sortedList);

        sorter.sortList();
        assertEquals("1->2->3->4->5", sortedList.toString(), "Already sorted list should remain unchanged.");
    }

    @Test
    public void testSortReverseOrderedList() {
        SinglyLinkedList reverseList = new SinglyLinkedList();
        reverseList.insert(5);
        reverseList.insert(4);
        reverseList.insert(3);
        reverseList.insert(2);
        reverseList.insert(1);
        QuickSortLinkedList sorter = new QuickSortLinkedList(reverseList);

        sorter.sortList();
        assertEquals("1->2->3->4->5", reverseList.toString(), "Reverse ordered list should be sorted in ascending order.");
    }

    @Test
    public void testSortWithDuplicates() {
        SinglyLinkedList listWithDuplicates = new SinglyLinkedList();
        listWithDuplicates.insert(3);
        listWithDuplicates.insert(1);
        listWithDuplicates.insert(3);
        listWithDuplicates.insert(2);
        listWithDuplicates.insert(2);
        QuickSortLinkedList sorter = new QuickSortLinkedList(listWithDuplicates);

        sorter.sortList();
        assertEquals("1->2->2->3->3", listWithDuplicates.toString(), "List with duplicates should be sorted correctly.");
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

        sorter.sortList();
        assertEquals("1->2->3->4->5->6->7->8->9->10", list.toString(), "List should be sorted in ascending order.");
    }
}
