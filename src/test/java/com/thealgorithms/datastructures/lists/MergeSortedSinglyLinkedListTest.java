package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MergeSortedSinglyLinkedListTest {

    @Test
    void testMergeTwoSortedLists() {
        SinglyLinkedList listA = new SinglyLinkedList();
        SinglyLinkedList listB = new SinglyLinkedList();

        for (int i = 2; i <= 10; i += 2) {
            listA.insert(i);
            listB.insert(i - 1);
        }

        SinglyLinkedList mergedList = MergeSortedSinglyLinkedList.merge(listA, listB);
        assertEquals("1->2->3->4->5->6->7->8->9->10", mergedList.toString(), "Merged list should contain all elements in sorted order.");
    }

    @Test
    void testMergeWithEmptyListA() {
        SinglyLinkedList listA = new SinglyLinkedList(); // Empty listA
        SinglyLinkedList listB = new SinglyLinkedList();
        listB.insert(1);
        listB.insert(3);
        listB.insert(5);

        SinglyLinkedList mergedList = MergeSortedSinglyLinkedList.merge(listA, listB);
        assertEquals("1->3->5", mergedList.toString(), "Merged list should match listB when listA is empty.");
    }

    @Test
    void testMergeWithEmptyListB() {
        SinglyLinkedList listA = new SinglyLinkedList();
        SinglyLinkedList listB = new SinglyLinkedList(); // Empty listB
        listA.insert(2);
        listA.insert(4);
        listA.insert(6);

        SinglyLinkedList mergedList = MergeSortedSinglyLinkedList.merge(listA, listB);
        assertEquals("2->4->6", mergedList.toString(), "Merged list should match listA when listB is empty.");
    }

    @Test
    void testMergeWithBothEmptyLists() {
        SinglyLinkedList listA = new SinglyLinkedList(); // Empty listA
        SinglyLinkedList listB = new SinglyLinkedList(); // Empty listB

        SinglyLinkedList mergedList = MergeSortedSinglyLinkedList.merge(listA, listB);
        assertEquals("", mergedList.toString(), "Merged list should be empty when both input lists are empty.");
    }

    @Test
    void testMergeWithDuplicateValues() {
        SinglyLinkedList listA = new SinglyLinkedList();
        SinglyLinkedList listB = new SinglyLinkedList();

        listA.insert(1);
        listA.insert(3);
        listA.insert(5);
        listB.insert(1);
        listB.insert(4);
        listB.insert(5);

        SinglyLinkedList mergedList = MergeSortedSinglyLinkedList.merge(listA, listB);
        assertEquals("1->1->3->4->5->5", mergedList.toString(), "Merged list should include duplicate values in sorted order.");
    }

    @Test
    void testMergeThrowsExceptionOnNullInput() {
        SinglyLinkedList listA = null;
        SinglyLinkedList listB = new SinglyLinkedList();
        listB.insert(1);
        listB.insert(2);

        SinglyLinkedList finalListA = listA;
        SinglyLinkedList finalListB = listB;
        assertThrows(NullPointerException.class, () -> MergeSortedSinglyLinkedList.merge(finalListA, finalListB), "Should throw NullPointerException if listA is null.");

        listA = new SinglyLinkedList();
        listB = null;
        SinglyLinkedList finalListA1 = listA;
        SinglyLinkedList finalListB1 = listB;
        assertThrows(NullPointerException.class, () -> MergeSortedSinglyLinkedList.merge(finalListA1, finalListB1), "Should throw NullPointerException if listB is null.");
    }
}
