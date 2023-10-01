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

    // Linked List Node Structure
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head = null; // head of Linked list
    private ListNode tail = null; // tail of Linked list

    /*
     *  function - insert : insert the values in the Linked List
     *
     *  @param  num : number to be inserted in Linked List
     */
    public void insert(int num) {
        if (head == null) {
            head = new ListNode(num);
            tail = head;
            return;
        }
        tail.next = new ListNode(num);
        tail = tail.next;
    }

    // function - clear : clears the list
    public boolean clear() {
        head = tail = null;
        return true;
    }

    // returns the Stored List
    public ListNode getList() {
        return head;
    }

    // Function to sort a linked list using the Quick Sort algorithm
    public void sortList() {
        head = sortList(head);
    }
    // helper function to apply QuickSort to the stored list
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Choose the first element as the pivot
        ListNode pivot = head;
        head = head.next;
        pivot.next = null;

        ListNode lessHead = new ListNode(0); // stores the nodes cantaining data less than pivot node
        ListNode lessTail = lessHead; // tail of lessHead
        ListNode greaterHead = new ListNode(0); // stores the nodes cantaining data greater than pivot node
        ListNode greaterTail = greaterHead; // tail of greaterHead

        // Partition the list around the pivot
        while (head != null) {
            if (head.val < pivot.val) {
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
        ListNode sortedLess = sortList(lessHead.next);
        ListNode sortedGreater = sortList(greaterHead.next);

        // Combine the sorted sublists and pivot
        if (sortedLess == null) {
            pivot.next = sortedGreater;
            return pivot;
        } else {
            ListNode current = sortedLess;
            while (current.next != null) {
                current = current.next;
            }
            current.next = pivot;
            pivot.next = sortedGreater;
            return sortedLess;
        }
    }

    public static void main(String[] args) {
        // Example usage
        QuickSortLinkedList sorter = new QuickSortLinkedList();
        sorter.insert(5);
        sorter.insert(3);
        sorter.insert(8);
        sorter.insert(1);
        sorter.insert(10);
        sorter.insert(2);
        sorter.insert(7);
        sorter.insert(4);
        sorter.insert(9);
        sorter.insert(6);

        QuickSortLinkedList.ListNode unsortedHead = sorter.getList();

        // Print the sorted linked list
        System.out.print("List before Applying QuickSort : ");
        while (unsortedHead != null) {
            System.out.print(unsortedHead.val + " ");
            unsortedHead = unsortedHead.next;
        }
        System.out.println();

        sorter.sortList();

        QuickSortLinkedList.ListNode sortedHead = sorter.getList();

        System.out.print("List before Applying QuickSort : ");
        // Print the sorted linked list
        while (sortedHead != null) {
            System.out.print(sortedHead.val + " ");
            sortedHead = sortedHead.next;
        }
        System.out.println();
    }
}
