/**
 * This class implements a SinglyLinked List. This is done
 * using SinglyLinkedList class and a LinkForLinkedList Class.
 * <p>
 * A linked list is similar to an array, it hold values.
 * However, links in a linked list do not have indexes. With
 * a linked list you do not need to predetermine it's size as
 * it grows and shrinks as it is edited. This is an example of
 * a singly linked list. Elements can only be added/removed
 * at the head/front of the list.
 *
 * @author yanglbme
 */
class SinglyLinkedList {
    /**
     * Head refer to the front of the list
     */
    private Node head;

    /**
     * Count of nodes
     */
    private int count;

    /**
     * This method inserts an element at the head
     *
     * @param x Element to be added
     */
    public void insertHead(int x) {
        Node newNode = new Node(x);
        newNode.next = head;
        head = newNode;
        ++count;
    }

    /**
     * Inserts a new node at a specified position
     *
     * @param data     data to be stored in a new node
     * @param position position at which a new node is to be inserted
     */

    public void insertNth(int data, int position) {
        if (position < 0 || position > count) {
            throw new RuntimeException("position less than zero or position more than the count of list");
        }
        Node node = new Node(data);
        Node dummy = new Node(-1);
        dummy.next = head;
        Node cur = dummy;
        for (int i = 0; i < position; ++i) {
            cur = cur.next;
        }
        node.next = cur.next;
        cur.next = node;
        ++count;
    }

    /**
     * This method deletes an element at the head
     *
     * @return The element deleted
     */
    public Node deleteHead() {
        if (isEmpty()) {
            throw new RuntimeException("The list is empty!");
        }

        Node temp = head;
        head = head.next;
        --count;
        return temp;
    }

    /**
     * Checks if the list is empty
     *
     * @return true is list is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Prints contents of the list
     */
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        SinglyLinkedList myList = new SinglyLinkedList();

        assert myList.isEmpty();

        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);

        myList.display(); // 10 -> 7 -> 5

        myList.deleteHead();

        myList.display(); // 7 -> 5

        myList.insertNth(11, 2);

        myList.display(); // 7 -> 5 -> 11
    }
}

/**
 * This class is the nodes of the SinglyLinked List.
 * They consist of a value and a pointer to the node
 * after them.
 *
 * @author yanglbme
 */
class Node {
    /**
     * The value of the node
     */
    int value;

    /**
     * Point to the next node
     */
    Node next;

    /**
     * Constructor
     *
     * @param value Value to be put in the node
     */
    Node(int value) {
        this.value = value;
    }
}
