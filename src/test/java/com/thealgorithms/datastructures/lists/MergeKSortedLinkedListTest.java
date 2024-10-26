package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.thealgorithms.datastructures.lists.MergeKSortedLinkedList.Node;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MergeKSortedLinkedListTest {

    @Test
    void testMergeKLists() {
        Node list1 = new Node(1, new Node(4, new Node(5)));
        Node list2 = new Node(1, new Node(3, new Node(4)));
        Node list3 = new Node(2, new Node(6));
        Node[] lists = {list1, list2, list3};

        MergeKSortedLinkedList merger = new MergeKSortedLinkedList();
        Node mergedHead = merger.mergeKList(lists, lists.length);

        int[] expectedValues = {1, 1, 2, 3, 4, 4, 5, 6};
        int[] actualValues = getListValues(mergedHead);
        assertArrayEquals(expectedValues, actualValues, "Merged list values do not match the expected sorted order.");
    }

    @Test
    void testMergeEmptyLists() {
        Node[] lists = {null, null, null};

        MergeKSortedLinkedList merger = new MergeKSortedLinkedList();
        Node mergedHead = merger.mergeKList(lists, lists.length);

        assertNull(mergedHead, "Merged list should be null when all input lists are empty.");
    }

    @Test
    void testMergeSingleList() {
        Node list1 = new Node(1, new Node(3, new Node(5)));
        Node[] lists = {list1};

        MergeKSortedLinkedList merger = new MergeKSortedLinkedList();
        Node mergedHead = merger.mergeKList(lists, lists.length);

        int[] expectedValues = {1, 3, 5};
        int[] actualValues = getListValues(mergedHead);
        assertArrayEquals(expectedValues, actualValues, "Merged list should match the single input list when only one list is provided.");
    }

    @Test
    void testMergeListsOfDifferentLengths() {
        Node list1 = new Node(1, new Node(3, new Node(5)));
        Node list2 = new Node(2, new Node(4));
        Node list3 = new Node(6);
        Node[] lists = {list1, list2, list3};

        MergeKSortedLinkedList merger = new MergeKSortedLinkedList();
        Node mergedHead = merger.mergeKList(lists, lists.length);

        int[] expectedValues = {1, 2, 3, 4, 5, 6};
        int[] actualValues = getListValues(mergedHead);
        assertArrayEquals(expectedValues, actualValues, "Merged list values do not match expected sorted order for lists of different lengths.");
    }

    @Test
    void testMergeSingleElementLists() {
        Node list1 = new Node(1);
        Node list2 = new Node(3);
        Node list3 = new Node(2);
        Node[] lists = {list1, list2, list3};

        MergeKSortedLinkedList merger = new MergeKSortedLinkedList();
        Node mergedHead = merger.mergeKList(lists, lists.length);

        int[] expectedValues = {1, 2, 3};
        int[] actualValues = getListValues(mergedHead);
        assertArrayEquals(expectedValues, actualValues, "Merged list values do not match expected sorted order for single-element lists.");
    }

    /**
     * Helper method to extract values from the linked list into an array for assertion.
     */
    private int[] getListValues(Node head) {
        int[] values = new int[100]; // assuming max length for simplicity
        int i = 0;
        Node curr = head;
        while (curr != null) {
            values[i++] = curr.data;
            curr = curr.next;
        }
        return Arrays.copyOf(values, i); // return only filled part
    }
}
