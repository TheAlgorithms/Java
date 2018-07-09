/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
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
 *
 * Contains all the stack methods : push, pop, printStack, isEmpty
 **/

class LinkedListStack {

    Node head = null;
    int size = 0;

    public void push(int x) {
        Node n = new Node(x);
        if (getSize() == 0) {
            head = n;
        }
        else {
            Node temp = head;
            n.next = temp;
            head = n;
        }
        size++;
    }

    public void pop() {
        if (getSize() == 0) {
            System.out.println("Empty stack. Nothing to pop");
        }

        Node temp = head;
        head = head.next;
        size--;

        System.out.println("Popped element is: " + temp.data);
    }

    public int peek() {
      if (getSize() == 0) {
        return -1;
      }

      return head.data;
    }

    public void printStack() {

        Node temp = head;
        System.out.println("Stack is printed as below: ");
        while (temp != null) {
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();

    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return size;
    }

}
