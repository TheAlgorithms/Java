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
     * size of SinglyLinkedList
     */
    private int size;

    /**
     * init SinglyLinkedList
     */
    public SinglyLinkedList() {
        head = new Node(0);
        size = 0;
    }

    /**
     * Init SinglyLinkedList with specified head node and size
     *
     * @param head the head node of list
     * @param size the size of list
     */
    public SinglyLinkedList(Node head, int size) {
        this.head = head;
        this.size = size;
    }

    /**
     * This method inserts an element at the head
     *
     * @param x Element to be added
     */
    public void insertHead(int x) {
        insertNth(x, 0);
    }

    /**
     * insert an element at the tail of list
     *
     * @param data Element to be added
     */
    public void insert(int data) {
        insertNth(data, size);
    }

    /**
     * Inserts a new node at a specified position
     *
     * @param data     data to be stored in a new node
     * @param position position at which a new node is to be inserted
     */
    public void insertNth(int data, int position) {
        checkBounds(position, 0, size);
        Node cur = head;
        for (int i = 0; i < position; ++i) {
            cur = cur.next;
        }
        Node node = new Node(data);
        node.next = cur.next;
        cur.next = node;
        size++;
    }

    /**
     * Insert element to list, always sorted
     *
     * @param data to be inserted
     */
    public void insertSorted(int data) {
        Node cur = head;
        while (cur.next != null && data > cur.next.value) {
            cur = cur.next;
        }

        Node newNode = new Node(data);
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    /**
     * This method deletes an element at the head
     *
     * @return The element deleted
     */
    public void deleteHead() {
        deleteNth(0);
    }

    /**
     * This method deletes an element at the tail
     */
    public void delete() {
        deleteNth(size - 1);
    }

    /**
     * This method deletes an element at Nth position
     */
    public void deleteNth(int position) {
        checkBounds(position, 0, size - 1);
        Node cur = head;
        for (int i = 0; i < position; ++i) {
            cur = cur.next;
        }

        Node destroy = cur.next;
        cur.next = cur.next.next;
        destroy = null; // clear to let GC do its work

        size--;
    }

    /**
     * @param position to check position
     * @param low      low index
     * @param high     high index
     * @throws IndexOutOfBoundsException if {@code position} not in range {@code low} to {@code high}
     */
    public void checkBounds(int position, int low, int high) {
        if (position > high || position < low) {
            throw new IndexOutOfBoundsException(position + "");
        }
    }

    /**
     * clear all nodes in list
     */
    public void clear() {
        if (size == 0) {
            return;
        }
        Node prev = head.next;
        Node cur = prev.next;
        while (cur != null) {
            prev = null; // clear to let GC do its work
            prev = cur;
            cur = cur.next;
        }
        prev = null;
        head.next = null;
        size = 0;
    }

    /**
     * Checks if the list is empty
     *
     * @return true is list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the linked list
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Node cur = head.next;
        while (cur != null) {
            builder.append(cur.value).append("->");
            cur = cur.next;
        }
        return builder.replace(builder.length() - 2, builder.length(), "").toString();
    }

    /**
     * Merge two sorted SingleLinkedList
     *
     * @param listA the first sorted list
     * @param listB the second sored list
     * @return merged sorted list
     */
    public static SinglyLinkedList merge(SinglyLinkedList listA, SinglyLinkedList listB) {
        Node headA = listA.head.next;
        Node headB = listB.head.next;

        int size = listA.size() + listB.size();

        Node head = new Node();
        Node tail = head;
        while (headA != null && headB != null) {
            if (headA.value <= headB.value) {
                tail.next = headA;
                headA = headA.next;
            } else {
                tail.next = headB;
                headB = headB.next;
            }
            tail = tail.next;
        }
        if (headA == null) {
            tail.next = headB;
        }
        if (headB == null) {
            tail.next = headA;
        }
        return new SinglyLinkedList(head, size);
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        SinglyLinkedList myList = new SinglyLinkedList();
        assert myList.isEmpty();
        assert myList.toString().equals("");

        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);
        assert myList.toString().equals("10->7->5");

        myList.deleteHead();
        assert myList.toString().equals("7->5");

        myList.insertNth(11, 2);
        assert myList.toString().equals("7->5->11");

        myList.deleteNth(1);
        assert myList.toString().equals("7->11");

        myList.clear();
        assert myList.isEmpty();

        /* Test MergeTwoSortedLinkedList */
        SinglyLinkedList listA = new SinglyLinkedList();
        SinglyLinkedList listB = new SinglyLinkedList();

        for (int i = 10; i >= 2; i -= 2) {
            listA.insertSorted(i);
            listB.insertSorted(i - 1);
        }
        assert listA.toString().equals("2->4->6->8->10");
        assert listB.toString().equals("1->3->5->7->9");
        assert SinglyLinkedList.merge(listA, listB).toString().equals("1->2->3->4->5->6->7->8->9->10");

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

    Node() {

    }

    /**
     * Constructor
     *
     * @param value Value to be put in the node
     */
    Node(int value) {
        this(value, null);
    }

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
