package com.thealgorithms.datastructures.stacks;

/**
 * Implementation of a stack using nodes. Unlimited size, no arraylist.
 *
 * @author Kyler Smith, 2017
 */
public class NodeStack<Item> {

    /**
     * Entry point for the program.
     */
    public static void main(String[] args) {
        NodeStack<Integer> Stack = new NodeStack<Integer>();

        Stack.push(3);
        Stack.push(4);
        Stack.push(5);
        System.out.println("Testing :");
        Stack.print(); // prints : 5 4 3

        Integer x = Stack.pop(); // x = 5
        Stack.push(1);
        Stack.push(8);
        Integer y = Stack.peek(); // y = 8
        System.out.println("Testing :");
        Stack.print(); // prints : 8 1 4 3

        System.out.println("Testing :");
        System.out.println("x : " + x);
        System.out.println("y : " + y);
    }

    /**
     * Information each node should contain.
     *
     * @value data : information of the value in the node
     * @value head : the head of the stack
     * @value next : the next value from this node
     * @value previous : the last value from this node
     * @value size : size of the stack
     */
    private Item data;

    private static NodeStack<?> head;
    private NodeStack<?> next;
    private NodeStack<?> previous;
    private static int size = 0;

    /**
     * Constructors for the NodeStack.
     */
    public NodeStack() {
    }

    private NodeStack(Item item) {
        this.data = item;
    }

    /**
     * Put a value onto the stack.
     *
     * @param item : value to be put on the stack.
     */
    public void push(Item item) {

        NodeStack<Item> newNs = new NodeStack<Item>(item);

        if (this.isEmpty()) {
            NodeStack.setHead(new NodeStack<>(item));
            newNs.setNext(null);
            newNs.setPrevious(null);
        } else {
            newNs.setPrevious(NodeStack.head);
            NodeStack.head.setNext(newNs);
            NodeStack.head.setHead(newNs);
        }

        NodeStack.setSize(NodeStack.getSize() + 1);
    }

    /**
     * Value to be taken off the stack.
     *
     * @return item : value that is returned.
     */
    public Item pop() {

        Item item = (Item) NodeStack.head.getData();

        NodeStack.head.setHead(NodeStack.head.getPrevious());
        NodeStack.head.setNext(null);

        NodeStack.setSize(NodeStack.getSize() - 1);

        return item;
    }

    /**
     * Value that is next to be taken off the stack.
     *
     * @return item : the next value that would be popped off the stack.
     */
    public Item peek() {
        return (Item) NodeStack.head.getData();
    }

    /**
     * If the stack is empty or there is a value in.
     *
     * @return boolean : whether or not the stack has anything in it.
     */
    public boolean isEmpty() {
        return NodeStack.getSize() == 0;
    }

    /**
     * Returns the size of the stack.
     *
     * @return int : number of values in the stack.
     */
    public int size() {
        return NodeStack.getSize();
    }

    /**
     * Print the contents of the stack in the following format.
     *
     * <p>
     * x <- head (next out) y z <- tail (first in) . . .
     */
    public void print() {
        for (NodeStack<?> n = NodeStack.head; n != null; n = n.previous) {
            System.out.println(n.getData().toString());
        }
    }

    /**
     * Getters and setters (private)
     */
    private NodeStack<?> getHead() {
        return NodeStack.head;
    }

    private static void setHead(NodeStack<?> ns) {
        NodeStack.head = ns;
    }

    private NodeStack<?> getNext() {
        return next;
    }

    private void setNext(NodeStack<?> next) {
        this.next = next;
    }

    private NodeStack<?> getPrevious() {
        return previous;
    }

    private void setPrevious(NodeStack<?> previous) {
        this.previous = previous;
    }

    private static int getSize() {
        return size;
    }

    private static void setSize(int size) {
        NodeStack.size = size;
    }

    private Item getData() {
        return this.data;
    }

    private void setData(Item item) {
        this.data = item;
    }
}
