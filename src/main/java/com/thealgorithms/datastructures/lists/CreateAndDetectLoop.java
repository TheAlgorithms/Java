package com.thealgorithms.datastructures.lists;

import java.util.Scanner;

public class CreateAndDetectLoop {

    /**
     * Prints the linked list.
     *
     * @param	head	head node of the linked list
     */
    static void printList(Node head) {
        Node cur = head;

        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }

    /**
     * Creates a loop in the linked list.
     *
     * @see
     * 	<a href="https://www.geeksforgeeks.org/make-loop-k-th-position-linked-list/">
     * GeeksForGeeks: Make a loop at K-th position</a>
     * @param	head	head node of the linked list
     * @param	k	position of node where loop is to be created
     */
    static void createLoop(Node head, int k) {
        if (head == null) {
            return;
        }
        Node temp = head;
        int count = 1;
        while (count < k) { 		// Traverse the list till the kth node
            temp = temp.next;
            count++;
        }

        Node connectedPoint = temp;

        while (temp.next != null) // Traverse remaining nodes
        {
            temp = temp.next;
        }

        temp.next = connectedPoint; // Connect last node to k-th element
    }

    /**
     * Detects the presence of a loop in the linked list.
     *
     * @see
     * 	<a href="https://en.wikipedia.org/wiki/Cycle_detection#Floyd's_tortoise_and_hare">
     * Floyd's Cycle Detection Algorithm</a>
     *
     * @param head the head node of the linked list
     *
     * @return true if loop exists else false
     */
    static boolean detectLoop(Node head) {
        Node sptr = head;
        Node fptr = head;

        while (fptr != null && fptr.next != null) {
            sptr = sptr.next;
            fptr = fptr.next.next;
            if (fptr == sptr) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements to be inserted: ");
        int n = sc.nextInt();
        System.out.printf("Enter the %d elements: \n", n);
        while (n-- > 0) {
            singlyLinkedList.insert(sc.nextInt());
        }

        System.out.print("Given list: ");
        printList(singlyLinkedList.getHead());
        System.out.println();

        System.out.println("Enter the location to generate loop: ");
        int k = sc.nextInt();

        createLoop(singlyLinkedList.getHead(), k);

        if (detectLoop(singlyLinkedList.getHead())) {
            System.out.println("Loop found");
        } else {
            System.out.println("No loop found");
        }

        sc.close();
    }
}
