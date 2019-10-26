<<<<<<< HEAD
package DataStructures.Stacks;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

// An implementation of a Stack using a Linked List

class StackOfLinkedList {

    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.printStack();

        System.out.println("Size of stack currently is: " + stack.getSize());

        stack.pop();
        stack.pop();

        System.out.println("Top element of stack currently is: " + stack.peek());
    }
}

// A node class

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * A class which implements a stack using a linked list
 * <p>
 * Contains all the stack methods : push, pop, printStack, isEmpty
 **/

class LinkedListStack {

    Node head = null;

    public void push(int x) {
        Node n = new Node(x);
        if (head == null) {
            head = n;
        } else {
            Node temp = head;
            n.next = temp;
            head = n;
        }
    }

    public void pop() {
        if (head == null) {
            System.out.println("Empty stack. Nothing to pop");
        }

        Node temp = head;
        head = head.next;
        System.out.println("Popped element is: " + temp.data);
    }

    public int peek() {
        if (head == null) {
            return -1;
        }
        return head.data;
    }

    public void printStack() {
        Node temp = head;
        System.out.println("Stack is printed as below: ");
        while (temp != null) {
            if (temp.next == null) {
                System.out.print(temp.data);
            } else {
                System.out.print(temp.data + " -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        if (head == null)
            return 0;
        else {
            int size = 1;
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
                size++;
            }
            return size;
        }
    }
}
=======
package DataStructures.Stacks;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

// An implementation of a Stack using a Linked List

class StackOfLinkedList {

    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack);

        System.out.println("Size of stack currently is: " + stack.getSize());

        assert stack.pop() == 5;
        assert stack.pop() == 4;

        System.out.println("Top element of stack currently is: " + stack.peek());
    }
}

// A node class

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * A class which implements a stack using a linked list
 * <p>
 * Contains all the stack methods : push, pop, printStack, isEmpty
 **/

class LinkedListStack {

    /**
     * Top of stack
     */
    Node head;

    /**
     * Size of stack
     */
    private int size;

    /**
     * Init properties
     */
    public LinkedListStack() {
        head = null;
        size = 0;
    }

    /**
     * Add element at top
     *
     * @param x to be added
     * @return <tt>true</tt> if add successfully
     */
    public boolean push(int x) {
        Node newNode = new Node(x);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    /**
     * Pop element at top of stack
     *
     * @return element at top of stack
     * @throws NoSuchElementException if stack is empty
     */
    public int pop() {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack. Nothing to pop");
        }
        Node destroy = head;
        head = head.next;
        int retValue = destroy.data;
        destroy = null; // clear to let GC do it's work
        size--;
        return retValue;
    }

    /**
     * Peek element at top of stack
     *
     * @return element at top of stack
     * @throws NoSuchElementException if stack is empty
     */
    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack. Nothing to pop");
        }
        return head.data;
    }

    @Override
    public String toString() {
        Node cur = head;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append(cur.data).append("->");
            cur = cur.next;
        }
        return builder.replace(builder.length() - 2, builder.length(), "").toString();
    }

    /**
     * Check if stack is empty
     *
     * @return <tt>true</tt> if stack is empty, otherwise <tt>false</tt>
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return size of stack
     *
     * @return size of stack
     */
    public int getSize() {
        return size;
    }
}
>>>>>>> 66410c126e598a1f718fdba749fbecbdca8130b9
