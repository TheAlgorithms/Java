// Java program to swap the nodes of a linked list rather
// than swapping the data from the nodes.
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SwapNodes {

    // Function to swap nodes x and y in linked list by changing links
    static Node swapNodes(Node head, int x, int y) {

        // Nothing to do if x and y are the same
        if (x == y) {
            return head;
        }

        Node prevX = null, currX = null;
        Node prevY = null, currY = null;
        Node curr = head;

        // First loop to find x
        while (curr != null) {
            if (curr.data == x) {
                currX = curr;
                break;
            }
            prevX = curr;
            curr = curr.next;
        }

        curr = head;

        // Second loop to find y
        while (curr != null) {
            if (curr.data == y) {
                currY = curr;
                break;
            }
            prevY = curr;
            curr = curr.next;
        }

        // If either x or y is not present, nothing to do
        if (currX == null || currY == null) {
            return head;
        }

        // If x is not head of the linked list
        if (prevX != null) {
            prevX.next = currY;
        } else {
            head = currY;
        }

        // If y is not head of the linked list
        if (prevY != null) {
            prevY.next = currX;
        } else {
            head = currX;
        }

        // Swap next pointers
        Node temp = currY.next;
        currY.next = currX.next;
        currX.next = temp;

        return head;
    }

    static void printList(Node curr) {
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Constructed linked list: 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        head = swapNodes(head, 4, 3);
        printList(head);
    }
}
