package DataStructures.Lists;

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
 */
public class SinglyLinkedList {
    /**
     * Head refer to the front of the list
     */
    private Node head;

    /**
     * This method inserts an element at the head
     *
     * @param x Element to be added
     */
    public void insertHead(int x) {
        Node newNode = new Node(x);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Inserts a new node at a specified position
     *
     * @param data     data to be stored in a new node
     * @param position position at which a new node is to be inserted
     */

    public void insertNth(int data, int position) {
        if (position < 0 || position > getSize()) {
            throw new RuntimeException("position less than zero or position more than the count of list");
        } else if (position == 0)
            insertHead(data);
        else {
            Node cur = head;
            Node node = new Node(data);
            for (int i = 1; i < position; ++i) {
                cur = cur.next;
            }
            node.next = cur.next;
            cur.next = node;
        }
    }

    /**
     * This method deletes an element at the head
     *
     * @return The element deleted
     */
    public void deleteHead() {
        if (isEmpty()) {
            throw new RuntimeException("The list is empty!");
        }

        head = head.next;
    }

    /**
     * This method deletes an element at Nth position
     */
    public void deleteNth(int position) {
        if (position < 0 || position >= getSize()) {
            throw new RuntimeException("position less than zero or position more than the count of list");
        } else if (position == 0)
            deleteHead();
        else {
            Node cur = head;
            for (int i = 1; i < position; ++i) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
    }

    /**
     * Checks if the list is empty
     *
     * @return true is list is empty
     */
    public boolean isEmpty() {
        return getSize() == 0;
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
     * Returns the size of the linked list
     */
    public int getSize() {
        if (head == null)
            return 0;
        else {
            Node current = head;
            int size = 1;
            while (current.next != null) {
                current = current.next;
                size++;
            }
            return size;
        }
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

        myList.deleteNth(1);

        myList.display(); // 7-> 11

    }
}

/**
 * This class is the nodes of the SinglyLinked List.
 * They consist of a value and a pointer to the node
 * after them.
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
        this.next = null;
    }
}
