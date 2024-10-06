package com.thealgorithms.datastructures.lists;

// Given two numbers in the form of reversed linked lists.
// Add those two numbers and then return the answer in the form of reversed linked list only.
// also known as the implementation of sumlist 

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class AddTwoNumbers {

    public Node addTwoNumbers(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        Node temp1 = head1;
        Node temp2 = head2;

        // sumlist
        Node dummyNode = new Node(-1);
        Node temp = dummyNode;
        int carry = 0;

        while (temp1 != null || temp2 != null) {
            int sum = carry;
            if (temp1 != null)
                sum += temp1.data;
            if (temp2 != null)
                sum += temp2.data;

            Node newNode = new Node(sum % 10);
            carry = sum / 10;
            temp.next = newNode;
            temp = temp.next;

            if (temp1 != null)
                temp1 = temp1.next;
            if (temp2 != null)
                temp2 = temp2.next;
        }

        if (carry != 0) {
            Node carryNode = new Node(carry);
            temp.next = carryNode;
        }
        return dummyNode.next;
    }
}
