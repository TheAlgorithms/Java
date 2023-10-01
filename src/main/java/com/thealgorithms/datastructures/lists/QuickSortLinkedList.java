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

    private SinglyLinkedList list = null; // Linked list
    private Node head = null; // head of the list
    // Counstructor
    public QuickSortLinkedList(SinglyLinkedList list) {
        this.list = list;
        this.head = list.getHead();
    }

    // Function to sort a linked list using the Quick Sort algorithm
    public void sortList() {
        head = sortList(head);
        list.setHead(head);
    }
    // helper function to apply QuickSort to the stored list
    public Node sortList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Choose the first element as the pivot
        Node pivot = head;
        head = head.next;
        pivot.next = null;

        Node lessHead = new Node(); // stores the nodes cantaining data less than pivot node
        Node lessTail = lessHead; // tail of lessHead
        Node greaterHead = new Node(); // stores the nodes cantaining data greater than pivot node
        Node greaterTail = greaterHead; // tail of greaterHead

        // Partition the list around the pivot
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

        // Seperating lessHead and greaterHead to form two seperate linkedList
        lessTail.next = null;
        greaterTail.next = null;

        // Recursively sort the sublists
        Node sortedLess = sortList(lessHead.next);
        Node sortedGreater = sortList(greaterHead.next);

        // Combine the sorted sublists and pivot
        if (sortedLess == null) {
            pivot.next = sortedGreater;
            return pivot;
        } else {
            Node current = sortedLess;
            while (current.next != null) {
                current = current.next;
            }
            current.next = pivot;
            pivot.next = sortedGreater;
            return sortedLess;
        }
    }
}
