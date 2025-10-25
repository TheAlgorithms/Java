package com.thealgorithms.datastructures.lists;
/*
 *
 * @aurthor - Prabhat-Kumar-42
 * @github - https://github.com/Prabhat-Kumar-42
 *
 * Problem :
 *    QuickSort on Linked List
 *
 * Note: Taking head as pivot in current implementation.
 *        N represents NULL node
 *    Example:
 *
 *      -> Given Linked List :
 *            5 -> 3 -> 8 -> 1 -> 10 -> 2 -> 7 -> 4 -> 9 -> 6
 *
 *      -> How Sorting will work according to the QuickSort Algo written below
 *
 *      current pivot : 5
 *      List lessThanPivot : 3 -> 1 -> 2 -> 4
 *      List greaterThanPivot : 5 -> 8 -> 10 -> 7 -> 9 -> 6
 *
 *      -> reccur for lessThanPivot and greaterThanPivot
 *
 *            lessThanPivot :
 *                current pivot : 3
 *                lessThanPivot : 1 -> 2
 *                greaterThanPivot : 4
 *
 *             greaterThanPivot:
 *                 current pivot : 5
 *                 lessThanPivot : null
 *                 greaterThanPivot : 8 -> 10 -> 7 -> 9 -> 6
 *
 *        By following the above pattern, reccuring tree will form like below :
 *
 *        List-> 5 -> 3 -> 8 -> 1 -> 10 -> 2 -> 7 -> 4 -> 9 -> 6
 *
 *        Pivot :                  5
 *                                /\
 *                               /  \
 *                              /    \
 *                             /      \
 *                            /        \
 *        List: (3 -> 1 -> 2 -> 4)   (5 -> 8 -> 10 -> 7 -> 9 -> 6)
 *        Pivot :          3               5
 *                        /\              /\
 *                       /  \            /  \
 *                      /    \          /    \
 *                     /      \        /      \
 *         List:   (1 -> 2)   (4)   (N)   (8 -> 10 -> 7 -> 9 -> 6)
 *         Pivot:     1        4                8
 *                   /\       /\               /\
 *                  /  \     /  \             /  \
 *                 /    \   /    \           /    \
 *         List:  (N)  (2) (N)   (N)   (6 -> 7)   (9 -> 10)
 *         Pivot:       2                  6         9
 *                     /\                 /\        /\
 *                    /  \               /  \      /  \
 *                   /    \             /    \    /    \
 *         List:   (N)   (N)          (N)   (7) (N)   (10)
 *         Pivot:                            7          10
 *                                          /\          /\
 *                                         /  \        /  \
 *                                        /    \      /    \
 *                                       (N)   (N)   (N)   (N)
 *
 *
 *      -> After this the tree will reccur back (or backtrack)
 *         and the returning list from left and right subtree will attach
 *         themselves around pivot.
 *         i.e. ,
 *                  (listFromLeftSubTree) -> (Pivot) -> (listFromRightSubtree)
 *
 *         This will continue until whole list is merged back
 *
 *          eg :
 *             Megring the above Tree back we get :
 *
 *          List: (1 -> 2)        (4)           (6 -> 7)         (9 -> 10)
 *                  \             /                  \             /
 *                   \           /                    \           /
 *                    \         /                      \         /
 *                     \       /                        \       /
 *                      \     /                          \     /
 *                       \   /                            \   /
 *                        \ /                              \ /
 *          Pivot:         3                                8
 *          List:   (1 -> 2 -> 3 -> 4)            (6 -> 7 -> 8 -> 9 -> 10)
 *                                  \              /
 *                                   \            /
 *                                    \          /
 *                                     \        /
 *                                      \      /
 *                                       \    /
 *                                        \  /
 *                                         \/
 *          Pivot:                          5
 *          List:      (1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10)
 *
 *
 *      -> This will result in a sorted Linked List
 */

public class QuickSortLinkedList {

    private final SinglyLinkedList list; // The linked list to be sorted
    private SinglyLinkedListNode head; // Head of the list

    /**
     * Constructor that initializes the QuickSortLinkedList with a given linked list.
     *
     * @param list The singly linked list to be sorted
     */
    public QuickSortLinkedList(SinglyLinkedList list) {
        this.list = list;
        this.head = list.getHead();
    }

    /**
     * Sorts the linked list using the QuickSort algorithm.
     * The sorted list replaces the original list within the SinglyLinkedList instance.
     */
    public void sortList() {
        head = sortList(head);
        list.setHead(head);
    }

    /**
     * Recursively sorts a linked list by partitioning it around a pivot element.
     *
     * <p>Each recursive call selects a pivot, partitions the list into elements less
     * than the pivot and elements greater than or equal to the pivot, then combines
     * the sorted sublists around the pivot.</p>
     *
     * @param head The head node of the list to sort
     * @return The head node of the sorted linked list
     */
    private SinglyLinkedListNode sortList(SinglyLinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        SinglyLinkedListNode pivot = head;
        head = head.next;
        pivot.next = null;

        SinglyLinkedListNode lessHead = new SinglyLinkedListNode();
        SinglyLinkedListNode lessTail = lessHead;
        SinglyLinkedListNode greaterHead = new SinglyLinkedListNode();
        SinglyLinkedListNode greaterTail = greaterHead;

        while (head != null) {
            if (head.value < pivot.value) {
                lessTail.next = head;
                lessTail = lessTail.next;
            } else {
                greaterTail.next = head;
                greaterTail = greaterTail.next;
            }
            head = head.next;
        }

        lessTail.next = null;
        greaterTail.next = null;

        SinglyLinkedListNode sortedLess = sortList(lessHead.next);
        SinglyLinkedListNode sortedGreater = sortList(greaterHead.next);

        if (sortedLess == null) {
            pivot.next = sortedGreater;
            return pivot;
        } else {
            SinglyLinkedListNode current = sortedLess;
            while (current.next != null) {
                current = current.next;
            }
            current.next = pivot;
            pivot.next = sortedGreater;
            return sortedLess;
        }
    }
}
