package com.thealgorithms.datastructures.lists;

/**
 * The ReverseKGroup class provides functionality to reverse nodes in a
 * linked list in groups of k nodes.
 * <p>
 * This algorithm follows the approach of reversing the linked list in segments of
 * size k. If the remaining nodes are fewer than k, they remain unchanged.
 * </p>
 * <p>
 * Example:
 * Given a linked list: 1 -> 2 -> 3 -> 4 -> 5 and k = 3,
 * the output will be: 3 -> 2 -> 1 -> 4 -> 5.
 * </p>
 * <p>
 * The implementation contains:
 * - {@code length(SinglyLinkedListNode head)}: A method to calculate the length of the linked list.
 * - {@code reverse(SinglyLinkedListNode head, int count, int k)}: A helper method that reverses the nodes
 *   in the linked list in groups of k.
 * - {@code reverseKGroup(SinglyLinkedListNode head, int k)}: The main method that initiates the reversal
 *   process by calling the reverse method.
 * </p>
 * <p>
 * Complexity:
 * <ul>
 *   <li>Time Complexity: O(n), where n is the number of nodes in the linked list.</li>
 *   <li>Space Complexity: O(1), as we are reversing in place.</li>
 * </ul>
 * </p>
 *
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public class ReverseKGroup {

    /**
     * Calculates the length of the linked list.
     *
     * @param head The head node of the linked list.
     * @return The total number of nodes in the linked list.
     */
    public int length(SinglyLinkedListNode head) {
        SinglyLinkedListNode curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }

    /**
     * Reverses the linked list in groups of k nodes.
     *
     * @param head The head node of the linked list.
     * @param count The remaining number of nodes.
     * @param k The size of the group to reverse.
     * @return The new head of the reversed linked list segment.
     */
    public SinglyLinkedListNode reverse(SinglyLinkedListNode head, int count, int k) {
        if (count < k) {
            return head;
        }
        SinglyLinkedListNode prev = null;
        int count1 = 0;
        SinglyLinkedListNode curr = head;
        SinglyLinkedListNode next = null;
        while (curr != null && count1 < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count1++;
        }

        if (next != null) {
            head.next = reverse(next, count - k, k);
        }
        return prev;
    }

    /**
     * Reverses the linked list in groups of k nodes.
     *
     * @param head The head node of the linked list.
     * @param k The size of the group to reverse.
     * @return The head of the modified linked list after reversal.
     */
    public SinglyLinkedListNode reverseKGroup(SinglyLinkedListNode head, int k) {
        int count = length(head);
        return reverse(head, count, k);
    }
}
