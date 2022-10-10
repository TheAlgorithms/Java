package com.thealgorithms.datastructures.lists;

/**
 * Algorithm to Reverse a Singly Linked List (SLL).
 * It can be done in 2 ways : Iteration and Recursion.
 * This class gives example of doing it using Iteration in place.
 */
public class ReverseLinkedListIteration {
    public static void main(String[] args) {
        // Initialize a SLL as 85 -> 15 -> 4 -> 20
        Node list;
        list = new Node(85);
        list.next = new Node(15);
        list.next.next = new Node(4);
        list.next.next.next = new Node(20);

        // print the SLL before reversing
        System.out.println("Given Linked list");
        printList(list);

        list = reverseListIterate(list);

        // print the SLL after reversing
        System.out.println("Reversed linked list ");
        printList(list);
    }


    /**
     * Prints the SLL
     *
     * @param node : the head of the SLL
     */
    static void printList(Node node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * Algorithm to reverse the SLL in place
     *
     * @param head : the head of the original SLL
     * @return : the head of the reversed SLL
     */
    static Node reverseListIterate(Node head) {
        Node prev = null;
        Node curr = head;
        if (curr == null) return null;
        Node nxt = curr.next;

        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = nxt;
            if (curr != null) nxt = curr.next;
        }
        return prev;
    }
}
