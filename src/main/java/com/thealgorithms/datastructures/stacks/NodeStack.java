package com.thealgorithms.datastructures.stacks;

/**
 * Implementation of a stack using nodes. Unlimited size, no arraylist.
 *
 */
public class NodeStack<Item> {

    private Item data;
    private NodeStack<Item> previous;
    private NodeStack<Item> head;
    private int size = 0;

    public NodeStack() {
    }

    private NodeStack(Item item) {
        this.data = item;
    }

    public void push(Item item) {
        NodeStack<Item> newNode = new NodeStack<>(item);

        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.previous = head;
            head = newNode;
        }
        size++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty, cannot peek element");
        }

        Item item = head.data;
        head = head.previous;
        size--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty, cannot peek element");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
