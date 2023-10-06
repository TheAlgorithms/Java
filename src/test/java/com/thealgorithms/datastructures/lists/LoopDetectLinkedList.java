package com.thealgorithms.datastructures.lists;

import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LoopDetectLinkedList {

    // Detects and removes a loop in a linked list, if present
    public static boolean detectAndRemoveLoop(Node head) {
        if (head == null || head.next == null) {
            return false; // No loop if the list is empty or has only one node
        }

        Node slow = head;
        Node fast = head;

        // Move slow pointer by one step and fast pointer by two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If they meet at the same node, there's a loop
            if (slow == fast) {
                removeLoop(head, slow);
                return true;
            }
        }

        return false; // No loop found
    }

    // Removes the loop by breaking the link at the loop node
    private static void removeLoop(Node head, Node loopNode) {
        Node ptr1 = head;
        Node ptr2 = loopNode;

        // Move one pointer to the head and keep the other at the meeting point
        while (ptr1.next != ptr2.next) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // Break the link at the loop node to remove the loop
        ptr2.next = null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to input the number of elements
        System.out.print("Enter the number of elements in the linked list: ");
        int n = scanner.nextInt();
        
        Node head = null;
        Node tail = null;

        // Input the elements of the linked list
        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            int element = scanner.nextInt();
            Node newNode = new Node(element);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Prompt the user to create a loop (optional)
        System.out.print("Do you want to create a loop? (yes/no): ");
        String createLoopChoice = scanner.next().toLowerCase();
        
        if (createLoopChoice.equals("yes")) {
            System.out.print("Enter the position of the node to which the last node should point (1-" + n + "): ");
            int loopPosition = scanner.nextInt();
            if (loopPosition >= 1 && loopPosition <= n) {
                Node loopNode = head;
                for (int i = 1; i < loopPosition; i++) {
                    loopNode = loopNode.next;
                }
                tail.next = loopNode;
            } else {
                System.out.println("Invalid position. No loop created.");
            }
        }

        // Detect and remove the loop
        boolean loopDetected = LoopDetectLinkedList.detectAndRemoveLoop(head);

        if (loopDetected) {
            System.out.println("Loop detected and removed.");
        } else {
            System.out.println("No loop found.");
        }
        
        scanner.close();
    }
}

