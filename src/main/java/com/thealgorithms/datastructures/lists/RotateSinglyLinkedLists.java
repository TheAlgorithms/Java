package com.thealgorithms.datastructures.lists;

/**
 * The RotateSinglyLinkedLists class provides a method to rotate a singly linked list
 * to the right by a specified number of positions.
 * <p>
 * In a right rotation by `k` steps, each node in the list moves `k` positions to the right.
 * Nodes that are rotated off the end of the list are placed back at the beginning.
 * </p>
 * <p>
 * Example:
 * Given linked list: 1 -> 2 -> 3 -> 4 -> 5 and k = 2, the output will be:
 * 4 -> 5 -> 1 -> 2 -> 3.
 * </p>
 * <p>
 * Edge Cases:
 * <ul>
 *   <li>If the list is empty, returns null.</li>
 *   <li>If `k` is 0 or a multiple of the list length, the list remains unchanged.</li>
 * </ul>
 * </p>
 * <p>
 * Complexity:
 * <ul>
 *   <li>Time Complexity: O(n), where n is the number of nodes in the linked list.</li>
 *   <li>Space Complexity: O(1), as we only use a constant amount of additional space.</li>
 * </ul>
 * </p>
 *
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public class RotateSinglyLinkedLists {

    /**
     * Rotates a singly linked list to the right by `k` positions.
     *
     * @param head The head node of the singly linked list.
     * @param k The number of positions to rotate the list to the right.
     * @return The head of the rotated linked list.
     */
    public SinglyLinkedListNode rotateRight(SinglyLinkedListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        SinglyLinkedListNode curr = head;
        int len = 1;
        while (curr.next != null) {
            curr = curr.next;
            len++;
        }

        curr.next = head;
        k = k % len;
        k = len - k;
        while (k > 0) {
            curr = curr.next;
            k--;
        }

        head = curr.next;
        curr.next = null;
        return head;
    }
}
