package com.thealgorithms.datastructures.lists;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMiddleNodeTest {

    @Test
    public void testOddLengthList() {
        ListNode head = createList(new int[] { 1, 2, 3, 4, 5 });
        assertEquals(3, FindMiddleNode.findMiddle(head).value);
    }

    @Test
    public void testEvenLengthList() {
        ListNode head = createList(new int[] { 1, 2, 3, 4, 5, 6 });
        assertEquals(4, FindMiddleNode.findMiddle(head).value);
    }

    private ListNode createList(int[] values) {
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }
}
