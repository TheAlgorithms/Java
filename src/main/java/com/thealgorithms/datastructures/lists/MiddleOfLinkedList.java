// Definition for singly-linked list node
class ListNode {
    int val;          // Value stored in the node
    ListNode next;    // Pointer to the next node

    // Constructor to initialize node with a value
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class MiddleOfLinkedList {

    /**
     * Finds the middle node of a singly linked list.
     * If there are two middle nodes, returns the second one.
     */
    public ListNode middleNode(ListNode head) {
        // If the list is empty, just return null
        if (head == null) return head;

        // Initialize two pointers: slow and fast
        ListNode slow = head; // moves one step at a time
        ListNode fast = head; // moves two steps at a time

        // Traverse the list
        // When 'fast' reaches the end, 'slow' will be at the middle
        while (fast != null && fast.next != null) {
            slow = slow.next;         // move slow by one node
            fast = fast.next.next;    // move fast by two nodes
        }

        // When loop ends, slow is pointing at the middle node
        return slow;
    }

    /**
     * Helper method to create a linked list from an array.
     * Example: [1,2,3,4] → 1 → 2 → 3 → 4
     */
    public static ListNode createList(int[] values) {
        // If the array is empty, return null
        if (values.length == 0) return null;

        // Create the head node
        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        // Loop through the rest of the array to build the list
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]); // create next node
            current = current.next;                 // move pointer forward
        }

        return head; // return the head of the linked list
    }

    /**
     * Helper method to print the linked list starting from any node.
     * Example: prints "3 4 5"
     */
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " "); // print current node value
            node = node.next;                 // move to next node
        }
        System.out.println(); // print newline after list
    }

    public static void main(String[] args) {
        // Create an instance of this class to access non-static methods
        MiddleOfLinkedList sol = new MiddleOfLinkedList();

        // Input array for the linked list
        int[] values = {1, 2, 3, 4, 5};  // Odd-length list

        // Create linked list from array
        ListNode head = createList(values);

        // Find middle node
        ListNode middle = sol.middleNode(head);

        // Print all nodes from middle to end
        System.out.print("Middle node and following nodes: ");
        printList(middle); // Expected output: 3 4 5
    }
}
