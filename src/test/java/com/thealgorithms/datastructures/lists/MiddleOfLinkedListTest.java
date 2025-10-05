package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MiddleOfLinkedListTest {

    @Test
    void testMiddleNodeOddLength() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertEquals(3, middle.val);
    }

    @Test
    void testMiddleNodeEvenLength() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {1, 2, 3, 4, 5, 6};
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertEquals(4, middle.val);
    }

    @Test
    void testMiddleNodeSingleElement() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {1};
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertEquals(1, middle.val);
    }

    @Test
    void testMiddleNodeEmptyList() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {};
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertNull(middle);
    }
}
