package com.thealgorithms.datastructures.lists;

/**
 * Returns the middle node of a singly linked list using the two-pointer technique.
 *
 * <p>The {@code slow} pointer advances by one node per iteration while {@code fast} advances by two.
 * When {@code fast == null} or {@code fast.next == null}, {@code slow} points to the middle node.
 * For even-length lists, this returns the <em>second</em> middle node.</p>
 *
 * <p>This method does not modify the input list.</p>
 *
 * <p>Reference: https://en.wikipedia.org/wiki/Cycle_detection#Floyd's_tortoise_and_hare</p>
 *
 * <p>Complexity:</p>
 * <ul>
 *   <li>Time: {@code O(n)}</li>
 *   <li>Space: {@code O(1)}</li>
 * </ul>
 */
public final class MiddleOfLinkedList {

    private MiddleOfLinkedList() {
    }

    /**
     * Returns the middle node of the list.
     *
     * @param head the head of the singly linked list; may be {@code null}
     * @return the middle node (second middle for even-sized lists), or {@code null} if {@code head} is {@code null}
     */
    public static SinglyLinkedListNode middleNode(final SinglyLinkedListNode head) {
        if (head == null) {
            return null;
        }

        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
