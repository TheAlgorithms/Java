package com.thealgorithms.stacks;

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

public class StackUsingLinkedList<T> {
    private Node<T> top;
    private int size;

    public StackUsingLinkedList() {
        top = null;
        size = 0;
    }

    // Push operation
    public void push(T data) {
        Node<T> temp = new Node<>(data);
        temp.next = top;
        top = temp;
        size++;
    }

    // Pop operation
    public T pop() {
        if (top == null) {
            throw new RuntimeException("Stack Underflow");
        }
        T value = top.data;
        Node<T> temp = top;
        top = top.next;
        temp.next = null; // help GC
        size--;
        return value;
    }

    // Peek operation
    public T peek() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    // Size operation (O(1))
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}
class demo{
    public static void main(String[] args) {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();

        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Initial size: " + stack.size());


        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("After pushes:");
        System.out.println("Top element: " + stack.peek()); // 30
        System.out.println("Size: " + stack.size());        // 3

        // Pop elements
        System.out.println("Popped: " + stack.pop()); // 30
        System.out.println("Popped: " + stack.pop()); // 20

        System.out.println("After pops:");
        System.out.println("Top element: " + stack.peek()); // 10
        System.out.println("Size: " + stack.size());        // 1

        System.out.println("Is stack empty? " + stack.isEmpty());


        stack.pop();
        System.out.println("Is stack empty after final pop? " + stack.isEmpty());
    }
}
