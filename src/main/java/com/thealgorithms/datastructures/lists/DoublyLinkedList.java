package com.thealgorithms.datastructures.lists;

/**
 * This class implements a DoublyLinkedList. This is done using the classes
 * LinkedList and Link.
 *
 * <p>
 * A linked list is similar to an array, it holds values. However, links in a
 * linked list do not have indexes. With a linked list you do not need to
 * predetermine it's size as it grows and shrinks as it is edited. This is an
 * example of a double ended, doubly linked list. Each link references the next
 * link and the previous one.
 *
 * @author Unknown
 */
public class DoublyLinkedList {

    /**
     * Head refers to the front of the list
     */
    protected Link head;
    /**
     * Tail refers to the back of the list
     */
    private Link tail;
    /**
     * Link Operations to perform operations on nodes of the list
     */
    private LinkOperations linkOperations;

    /**
     * Size refers to the number of elements present in the list
     */
    private int size;

    /**
     * Default Constructor
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Constructs a list containing the elements of the array
     *
     * @param array the array whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public DoublyLinkedList(int[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        for (int i : array) {
            linkOperations.insertTail(i, this);
        }
        size = array.length;
    }

    /**
     * Returns true if list is empty
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Prints contents of the list
     */
    public void display() { // Prints contents of the list
        Link current = head;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Prints the contents of the list in reverse order
     */
    public void displayBackwards() {
        Link current = tail;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println();
    }
}

/**
 * This class is used to implement the nodes of the linked list.
 *
 * @author Unknown
 */
class Link {

    /**
     * Value of node
     */
    public int value;
    /**
     * This points to the link in front of the new link
     */
    public Link next;
    /**
     * This points to the link behind the new link
     */
    public Link previous;

    /**
     * Constructor
     *
     * @param value Value of node
     */
    public Link(int value) {
        this.value = value;
    }

    /**
     * Displays the node
     */
    public void displayLink() {
        System.out.print(value + " ");
    }

    /**
     * Main Method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        DoublyLinkedList myList = new DoublyLinkedList();
        LinkOperations linkOperations = new LinkOperations();
        linkOperations.insertHead(13, myList);
        linkOperations.insertHead(7, myList);
        linkOperations.insertHead(10, myList);
        myList.display(); // <-- 10(head) <--> 7 <--> 13(tail) -->
        myList.displayBackwards();

        linkOperations.insertTail(11, myList);
        myList.display(); // <-- 10(head) <--> 7 <--> 13 <--> 11(tail) -->
        myList.displayBackwards();

        linkOperations.deleteTail();
        myList.display(); // <-- 10(head) <--> 7 <--> 13(tail) -->
        myList.displayBackwards();

        linkOperations.delete(7);
        myList.display(); // <-- 10(head) <--> 13(tail) -->
        myList.displayBackwards();

        linkOperations.insertOrdered(23, myList);
        linkOperations.insertOrdered(67, myList);
        linkOperations.insertOrdered(3, myList);
        myList.display(); // <-- 3(head) <--> 10 <--> 13 <--> 23 <--> 67(tail) -->
        linkOperations.insertElementByIndex(5, 1, myList);
        myList.display(); // <-- 3(head) <--> 5 <--> 10 <--> 13 <--> 23 <--> 67(tail) -->
        myList.displayBackwards();
        linkOperations.reverse(); // <-- 67(head) <--> 23 <--> 13 <--> 10 <--> 5 <--> 3(tail) -->
        myList.display();

        linkOperations.clearList();
        myList.display();
        myList.displayBackwards();
        linkOperations.insertHead(20, myList);
        myList.display();
        myList.displayBackwards();
    }
}

/*
 * This class implements the operations of the Link nodes.
 */
class LinkOperations {

    /**
     * Head refers to the front of the list
     */
    private Link head;
    /**
     * Tail refers to the back of the list
     */
    private Link tail;

    /**
     * Size refers to the number of elements present in the list
     */
    private int size;

    /**
     * Insert an element at the head
     *
     * @param x Element to be inserted
     */
    public void insertHead(int x, DoublyLinkedList doublyLinkedList) {
        Link newLink = new Link(x); // Create a new link with a value attached to it
        if (doublyLinkedList.isEmpty()) { // Set the first element added to be the tail
            tail = newLink;
        } else {
            head.previous = newLink; // newLink <-- currenthead(head)
        }
        newLink.next = head; // newLink <--> currenthead(head)
        head = newLink; // newLink(head) <--> oldhead
        ++size;
    }

    /**
     * Insert an element at the tail
     *
     * @param x Element to be inserted
     */
    public void insertTail(int x, DoublyLinkedList doublyLinkedList) {
        Link newLink = new Link(x);
        newLink.next = null; // currentTail(tail)     newlink -->
        if (doublyLinkedList.isEmpty()) { // Check if there are no elements in list then it adds first element
            tail = newLink;
            head = tail;
        } else {
            tail.next = newLink; // currentTail(tail) --> newLink -->
            newLink.previous = tail; // currentTail(tail) <--> newLink -->
            tail = newLink; // oldTail <--> newLink(tail) -->
        }
        ++size;
    }

    /**
     * Insert an element at the index
     *
     * @param x Element to be inserted
     * @param index Index(from start) at which the element x to be inserted
     */
    public void insertElementByIndex(
        int x,
        int index,
        DoublyLinkedList doublyLinkedList
    ) {
        if (index > size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
        if (index == 0) {
            insertHead(x, doublyLinkedList);
        } else {
            if (index == size) {
                insertTail(x, doublyLinkedList);
            } else {
                Link newLink = new Link(x);
                Link previousLink = head; //
                for (int i = 1; i < index; i++) { // Loop to reach the index
                    previousLink = previousLink.next;
                }
                // previousLink is the Link at index - 1 from start
                previousLink.next.previous = newLink;
                newLink.next = previousLink.next;
                newLink.previous = previousLink;
                previousLink.next = newLink;
            }
        }
        ++size;
    }

    /**
     * Delete the element at the head
     *
     * @return The new head
     */
    public Link deleteHead() {
        Link temp = head;
        head = head.next; // oldHead <--> 2ndElement(head)

        if (head == null) {
            tail = null;
        } else {
            head.previous = null; // oldHead --> 2ndElement(head) nothing pointing at old head so will be removed
        }
        --size;
        return temp;
    }

    /**
     * Delete the element at the tail
     *
     * @return The new tail
     */
    public Link deleteTail() {
        Link temp = tail;
        tail = tail.previous; // 2ndLast(tail) <--> oldTail --> null

        if (tail == null) {
            head = null;
        } else {
            tail.next = null; // 2ndLast(tail) --> null
        }
        --size;
        return temp;
    }

    /**
     * Delete the element from somewhere in the list
     *
     * @param x element to be deleted
     * @return Link deleted
     */
    public void delete(int x) {
        Link current = head;

        while (current.value != x) { // Find the position to delete
            if (current != tail) {
                current = current.next;
            } else { // If we reach the tail and the element is still not found
                throw new RuntimeException(
                    "The element to be deleted does not exist!"
                );
            }
        }

        if (current == head) {
            deleteHead();
        } else if (current == tail) {
            deleteTail();
        } else { // Before: 1 <--> 2(current) <--> 3
            current.previous.next = current.next; // 1 --> 3
            current.next.previous = current.previous; // 1 <--> 3
        }
        --size;
    }

    /**
     * Inserts element and reorders
     *
     * @param x Element to be added
     */
    public void insertOrdered(int x, DoublyLinkedList doublyLinkedList) {
        Link newLink = new Link(x);
        Link current = head;
        while (current != null && x > current.value) { // Find the position to insert
            current = current.next;
        }

        if (current == head) {
            insertHead(x, doublyLinkedList);
        } else if (current == null) {
            insertTail(x, doublyLinkedList);
        } else { // Before: 1 <--> 2(current) <--> 3
            newLink.previous = current.previous; // 1 <-- newLink
            current.previous.next = newLink; // 1 <--> newLink
            newLink.next = current; // 1 <--> newLink --> 2(current) <--> 3
            current.previous = newLink; // 1 <--> newLink <--> 2(current) <--> 3
        }
        ++size;
    }

    /**
     * Deletes the passed node from the current list
     *
     * @param z Element to be deleted
     */
    public void deleteNode(Link z) {
        if (z.next == null) {
            deleteTail();
        } else if (z == head) {
            deleteHead();
        } else { // before <-- 1 <--> 2(z) <--> 3 -->
            z.previous.next = z.next; // 1 --> 3
            z.next.previous = z.previous; // 1 <--> 3
        }
        --size;
    }

    public void removeDuplicates(DoublyLinkedList l) {
        Link linkOne = l.head;
        while (linkOne.next != null) { // list is present
            Link linkTwo = linkOne.next; // second link for comparison
            while (linkTwo.next != null) {
                if (linkOne.value == linkTwo.value) { // if there are duplicates values then
                    delete(linkTwo.value); // delete the link
                }
                linkTwo = linkTwo.next; // go to next link
            }
            linkOne = linkOne.next; // go to link link to iterate the whole list again
        }
    }

    /**
     * Reverses the list in place
     */
    public void reverse() {
        // Keep references to the head and tail
        Link thisHead = this.head;
        Link thisTail = this.tail;

        // Flip the head and tail references
        this.head = thisTail;
        this.tail = thisHead;

        // While the link we're visiting is not null, flip the
        // next and previous links
        Link nextLink = thisHead;
        while (nextLink != null) {
            Link nextLinkNext = nextLink.next;
            Link nextLinkPrevious = nextLink.previous;
            nextLink.next = nextLinkPrevious;
            nextLink.previous = nextLinkNext;

            // Now, we want to go to the next link
            nextLink = nextLinkNext;
        }
    }

    /**
     * Clears List
     */
    public void clearList() {
        head = null;
        tail = null;
        size = 0;
    }
}
