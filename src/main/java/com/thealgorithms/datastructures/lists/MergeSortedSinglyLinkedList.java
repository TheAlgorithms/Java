package com.thealgorithms.datastructures.lists;

/**
 * Utility class for merging two sorted singly linked lists.
 *
 * <p>This class extends the {@link SinglyLinkedList} class to support the merging of two sorted linked lists.
 * It provides a static method, `merge`, that takes two sorted singly linked lists, merges them into a single sorted linked list,
 * and returns the result.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * SinglyLinkedList listA = new SinglyLinkedList();
 * SinglyLinkedList listB = new SinglyLinkedList();
 * for (int i = 2; i <= 10; i += 2) {
 *     listA.insert(i);   // listA: 2->4->6->8->10
 *     listB.insert(i - 1); // listB: 1->3->5->7->9
 * }
 * SinglyLinkedList mergedList = MergeSortedSinglyLinkedList.merge(listA, listB);
 * System.out.println(mergedList); // Output: 1->2->3->4->5->6->7->8->9->10
 * </pre>
 *
 * <p>The `merge` method assumes that both input lists are already sorted in ascending order.
 * It returns a new singly linked list that contains all elements from both lists in sorted order.</p>
 *
 * @see SinglyLinkedList
 */
public class MergeSortedSinglyLinkedList extends SinglyLinkedList {

    /**
     * Merges two sorted singly linked lists into a single sorted singly linked list.
     *
     * <p>This method does not modify the input lists; instead, it creates a new merged linked list
     * containing all elements from both lists in sorted order.</p>
     *
     * @param listA The first sorted singly linked list.
     * @param listB The second sorted singly linked list.
     * @return A new singly linked list containing all elements from both lists in sorted order.
     * @throws NullPointerException if either input list is null.
     */
    public static SinglyLinkedList merge(SinglyLinkedList listA, SinglyLinkedList listB) {
        if (listA == null || listB == null) {
            throw new NullPointerException("Input lists must not be null.");
        }

        SinglyLinkedListNode headA = listA.getHead();
        SinglyLinkedListNode headB = listB.getHead();
        int size = listA.size() + listB.size();

        SinglyLinkedListNode head = new SinglyLinkedListNode();
        SinglyLinkedListNode tail = head;
        while (headA != null && headB != null) {
            if (headA.value <= headB.value) {
                tail.next = headA;
                headA = headA.next;
            } else {
                tail.next = headB;
                headB = headB.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes
        tail.next = (headA == null) ? headB : headA;

        return new SinglyLinkedList(head.next, size);
    }
}
