package com.thealgorithms.datastructures.lists;

import java.util.StringJoiner;

/**
 * https://en.wikipedia.org/wiki/Linked_list
 */
public class SinglyLinkedList extends Node {

    /**
     * Head refer to the front of the list
     */
    private Node head;

    /**
     * Size of SinglyLinkedList
     */
    private int size;

    /**
     * Init SinglyLinkedList
     */
    public SinglyLinkedList() {
        head = null;
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
     * Detects if there is a loop in the singly linked list using floy'd turtle
     * and hare algorithm.
     *
     */
    public boolean detectLoop() {
        Node currentNodeFast = head;
        Node currentNodeSlow = head;
        while (currentNodeFast != null && currentNodeFast.next != null) {
            currentNodeFast = currentNodeFast.next.next;
            currentNodeSlow = currentNodeSlow.next;
            if (currentNodeFast == currentNodeSlow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the node in the middle of the list
     * If the length of the list is even then return item number length/2
     * @return middle node of the list
     */
    public Node middle() {
        if (head == null) {
            return null;
        }
        Node firstCounter = head;
        Node secondCounter = firstCounter.next;
        while (secondCounter != null && secondCounter.next != null) {
            firstCounter = firstCounter.next;
            secondCounter = secondCounter.next.next;
        }
        return firstCounter;
    }

    /**
     * Swaps nodes of two given values a and b.
     *
     */
    public void swapNodes(int valueFirst, int valueSecond) {
        if (valueFirst == valueSecond) {
            return;
        }
        Node previousA = null, currentA = head;
        while (currentA != null && currentA.value != valueFirst) {
            previousA = currentA;
            currentA = currentA.next;
        }

        Node previousB = null, currentB = head;
        while (currentB != null && currentB.value != valueSecond) {
            previousB = currentB;
            currentB = currentB.next;
        }
        /** If either of 'a' or 'b' is not present, then return */
        if (currentA == null || currentB == null) {
            return;
        }

        // If 'a' is not head node of list
        if (previousA != null) {
            previousA.next = currentB;
        } else {
            // make 'b' as the new head
            head = currentB;
        }

        // If 'b' is not head node of list
        if (previousB != null) {
            previousB.next = currentA;
        } else {
            // Make 'a' as new head
            head = currentA;
        }
        // Swap next pointer

        Node temp = currentA.next;
        currentA.next = currentB.next;
        currentB.next = temp;
    }

    /**
     * Reverse a singly linked list from a given node till the end
     *
     */
    public Node reverseList(Node node) {
        Node prev = null;
        Node curr = node;

        while (curr != null && curr.next != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // when curr.next==null, the current element is left without pointing it to its prev,so
        if (curr != null) {
            curr.next = prev;
            prev = curr;
        }
        // prev will be pointing to the last element in the Linkedlist, it will be the new head of
        // the reversed linkedlist
        return prev;
    }

    /**
     * Clear all nodes in the list
     */
    public void clear() {
        Node cur = head;
        while (cur != null) {
            Node prev = cur;
            cur = cur.next;
            prev = null; // clear to let GC do its work
        }
        head = null;
        size = 0;
    }

    /**
     * Checks if the list is empty
     *
     * @return {@code true} if list is empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the linked list.
     *
     * @return the size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Get head of the list.
     *
     * @return head of the list.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Set head of the list.
     *
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Calculate the count of the list manually
     *
     * @return count of the list
     */
    public int count() {
        int count = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        return count;
    }

    /**
     * Test if the value key is present in the list.
     *
     * @param key the value to be searched.
     * @return {@code true} if key is present in the list, otherwise
     * {@code false}.
     */
    public boolean search(int key) {
        Node cur = head;
        while (cur != null) {
            if (cur.value == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("->");
        Node cur = head;
        while (cur != null) {
            joiner.add(cur.value + "");
            cur = cur.next;
        }
        return joiner.toString();
    }

    public void deleteDuplicates() {
        Node pred = head;
        // predecessor = the node
        // having sublist of its duplicates
        Node newHead = head;
        while (newHead != null) {
            // if it's a beginning of duplicates sublist
            // skip all duplicates
            if (newHead.next != null && newHead.value == newHead.next.value) {
                // move till the end of duplicates sublist
                while (newHead.next != null && newHead.value == newHead.next.value) {
                    newHead = newHead.next;
                }
                // skip all duplicates
                pred.next = newHead.next;
                newHead = null;
                // otherwise, move predecessor
            }
            // move forward
            pred = pred.next;
            newHead = pred;
        }
    }

    public void print() {
        Node temp = head;
        while (temp != null && temp.next != null) {
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        if (temp != null) {
            System.out.print(temp.value);
            System.out.println();
        }
    }

    /**
     * Inserts an element at the head of the list
     *
     * @param x element to be added
     */
    public void insertHead(int x) {
        insertNth(x, 0);
    }

    /**
     * Insert an element at the tail of the list
     *
     * @param data element to be added
     */
    public void insert(int data) {
        insertNth(data, size);
    }

    /**
     * Inserts a new node at a specified position of the list
     *
     * @param data data to be stored in a new node
     * @param position position at which a new node is to be inserted
     */
    public void insertNth(int data, int position) {
        checkBounds(position, 0, size);
        Node newNode = new Node(data);
        if (head == null) {
            /* the list is empty */
            head = newNode;
            size++;
            return;
        }
        if (position == 0) {
            /* insert at the head of the list */
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        Node cur = head;
        for (int i = 0; i < position - 1; ++i) {
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    /**
     * Swaps nodes of two given values a and b.
     *
     */

    /**
     * Deletes a node at the head
     */
    public void deleteHead() {
        deleteNth(0);
    }

    /**
     * Deletes an element at the tail
     */
    public void delete() {
        deleteNth(size - 1);
    }

    /**
     * Deletes an element at Nth position
     */
    public void deleteNth(int position) {
        checkBounds(position, 0, size - 1);
        if (position == 0) {
            Node destroy = head;
            head = head.next;
            destroy = null;
            /* clear to let GC do its work */
            size--;
            return;
        }
        Node cur = head;
        for (int i = 0; i < position - 1; ++i) {
            cur = cur.next;
        }

        Node destroy = cur.next;
        cur.next = cur.next.next;
        destroy = null; // clear to let GC do its work

        size--;
    }

    /**
     * Return element at special index.
     *
     * @param index given index of element
     * @return element at special index.
     */
    public int getNth(int index) {
        checkBounds(index, 0, size - 1);
        Node cur = head;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        return cur.value;
    }

    /**
     * @param position to check position
     * @param low low index
     * @param high high index
     * @throws IndexOutOfBoundsException if {@code position} not in range
     * {@code low} to {@code high}
     */
    public void checkBounds(int position, int low, int high) {
        if (position > high || position < low) {
            throw new IndexOutOfBoundsException(position + "");
        }
    }

    /**
     * Driver Code
     */
    public static void main(String[] arg) {
        SinglyLinkedList list = new SinglyLinkedList();
        assert list.isEmpty();
        assert list.size() == 0 && list.count() == 0;
        assert list.toString().equals("");

        /* Test insert function */
        list.insertHead(5);
        list.insertHead(7);
        list.insertHead(10);
        list.insert(3);
        list.insertNth(1, 4);
        assert list.toString().equals("10->7->5->3->1");
        System.out.println(list);
        /* Test search function */
        assert list.search(10) && list.search(5) && list.search(1) && !list.search(100);

        /* Test get function */
        assert list.getNth(0) == 10 && list.getNth(2) == 5 && list.getNth(4) == 1;

        /* Test delete function */
        list.deleteHead();
        list.deleteNth(1);
        list.delete();
        assert list.toString().equals("7->3");
        System.out.println(list);
        assert list.size == 2 && list.size() == list.count();

        list.clear();
        assert list.isEmpty();

        try {
            list.delete();
            assert false;
            /* this should not happen */
        } catch (Exception e) {
            assert true;
            /* this should happen */
        }

        SinglyLinkedList instance = new SinglyLinkedList();
        Node head = new Node(0, new Node(2, new Node(3, new Node(3, new Node(4)))));
        instance.setHead(head);
        instance.deleteDuplicates();
        instance.print();
    }
}

/**
 * This class is the nodes of the SinglyLinked List. They consist of a value and
 * a pointer to the node after them.
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

    /**
     * Constructor
     *
     * @param value Value to be put in the node
     * @param next Reference to the next node
     */
    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
