package com.thealgorithms.datastructures.lists;

public class RemoveDuplicateNodes {

    public Node deleteDuplicates(Node head) {
        // sentinel
        Node sentinel = new Node(0, head);

        // predecessor = the last node 
        // before the sublist of duplicates
        Node pred = sentinel;

        while (head != null) {
            // if it's a beginning of duplicates sublist 
            // skip all duplicates
            if (head.next != null && head.value == head.next.value) {
                // move till the end of duplicates sublist
                while (head.next != null && head.value == head.next.value) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }

    public void print(Node head) {
        Node temp = head;
        while (temp != null && temp.next != null) {
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        if (temp != null) {
            System.out.print(temp.value);
        }
    }

    public static void main(String arg[]) {
        RemoveDuplicateNodes instance = new RemoveDuplicateNodes();
        Node head = new Node(0, new Node(2, new Node(3, new Node(3, new Node(4)))));
        head = instance.deleteDuplicates(head);
        instance.print(head);
    }
}
