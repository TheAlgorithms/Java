// Definition for singly-linked list node
class ListNode {
    int val; // Value stored in the node
    ListNode next; // Pointer to the next node

    /**
     * Constructor to initialize a node with a value.
     *
     * @param val Value to store in the node.
     */
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class MiddleOfLinkedList {

    /**
     * Finds the middle node of a singly linked list.
     * If there are two middle nodes, returns the second one.
     *
     * @param head Head node of the linked list.
     * @return Middle node of the list.
     */
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode slow = head; // moves one step at a time
        ListNode fast = head; // moves two steps at a time

        while (fast != null && fast.next != null) {
            slow = slow.next; // move slow by one node
            fast = fast.next.next; // move fast by two nodes
        }

        return slow;
    }

    /**
     * Helper method to create a linked list from an array.
     *
     * @param values Array of integer values.
     * @return Head node of the created linked list.
     */
    public static ListNode createList(int[] values) {
        if (values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]); // create next node
            current = current.next; // move pointer forward
        }

        return head;
    }

    /**
     * Helper method to print the linked list starting from any node.
     *
     * @param node Starting node to print from.
     */
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " "); // print current node value
            node = node.next; // move to next node
        }
        System.out.println();
    }
}
