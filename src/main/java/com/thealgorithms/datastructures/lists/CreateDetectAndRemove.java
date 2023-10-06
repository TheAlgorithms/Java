package com.thealgorithms.datastructures.lists;
public class CreateDetectAndRemove {
    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;
    public Node tail;

    // Print the linked list
    public void printLinkedList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    // Detect if a loop exists in the linked list using Floyd's Cycle Detection Learn More- fast and Slow Technique
    public boolean floydsCycleFinding() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // Remove the loop if it exists in the linked list
    public void removeLoop() {
        if (head == null || !floydsCycleFinding()) {
            return;
        }

        Node slow = head;
        Node fast = head;
        Node prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (prev == null) {
            // If the loop is at the beginning of the list
            slow = head;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
        } else {
            // If the loop is not at the beginning of the list
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            prev.next = null;
        }
    }

    public static void main(String[] args) {
        // Create an instance of the algoloop class
        CreateDetectAndRemove list = new CreateDetectAndRemove();

        // Create a sample linked list with a loop
        list.head = list.new Node(1);
        CreateDetectAndRemove.Node node2 = list.new Node(2);
        list.head.next = node2;
        CreateDetectAndRemove.Node node3 = list.new Node(3);
        node2.next = node3;
        CreateDetectAndRemove.Node node4 = list.new Node(4);
        node3.next = node4;
        CreateDetectAndRemove.Node node5 = list.new Node(5);
        node4.next = node5;
        node5.next = node2; // Create a loop between node 5 and node 2

        System.out.println("Detect Loop: " + list.floydsCycleFinding());

        // Remove the loop if it exists
        list.removeLoop();
        System.out.println("Detect Loop after removal: " + list.floydsCycleFinding());

        // Print the linked list after removing the loop
        System.out.println("Linked List After Removing the Loop:");
        list.printLinkedList();
    }
}
