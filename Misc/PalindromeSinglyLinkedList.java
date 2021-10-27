package Misc;

import java.util.Stack;

/**
 * A simple way of knowing if a singly linked list is palindrome is to
 * push all the values into a Stack and then compare the list to popped
 * vales from the Stack.
 * 
 * See more: https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
public class PalindromeSinglyLinkedList {
    public static void main(String[] args) {
        Node node5 = new Node(3, null);
        Node node4 = new Node(2, node5);
        Node node3 = new Node(1, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(3, node2);
        // Node node1 = new Node(2, node2);

        if (isPalindrome(node1)) {
            System.out.println("It's a palindrome list");
        } else {
            System.out.println("It's NOT a palindrome list");
        }
    }

    public static boolean isPalindrome(Node head) {
        boolean ret = true;
        Node tempNode = head;
        Stack<Integer> linkedListValues = new Stack<>();

        while (tempNode != null) {
            linkedListValues.push(tempNode.data);
            tempNode = tempNode.nextNode;
        }

        while (head != null) {
            if (head.data != linkedListValues.pop()) {
                ret = false;
                break;
            }

            head = head.nextNode;
        }

        return ret;
    }
}

class Node {
    int data;
    Node nextNode;

    public Node(int data, Node nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }
}
