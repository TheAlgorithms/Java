package com.thealgorithms.datastructures.lists;

public class LengthOfLoopInList {
    // Inner static Node class
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method to find the length of the loop
    public static int findLength(Node slow, Node fast) {
        int count = 1;
        slow = slow.next;
        while (slow != fast) {
            slow = slow.next;
            count++;
        }
        return count;
    }

    // Method to find the length of the loop in the linked list
    public static int lengthOfLoop(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // If both meet, a loop is found
            if (slow == fast) {
                return findLength(slow, fast);
            }
        }
        return 0; // No loop found
    }

    // Method to create a linked list with a loop of length 5
    public static Node createLinkedListWithLoop() {
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);
        Node seventh = new Node(7);

        // Linking nodes together to form a linear list
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = seventh;

        // Creating a loop: the next of the 7th node points to the 3rd node
        seventh.next = third;

        return head;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        Node head = createLinkedListWithLoop();
        int loopLength = lengthOfLoop(head);
        if (loopLength != 0) {
            System.out.println("Length of the loop is: " + loopLength);
        } else {
            System.out.println("No loop found.");
        }
    }
}
