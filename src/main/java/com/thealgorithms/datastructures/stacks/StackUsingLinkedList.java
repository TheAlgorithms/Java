package com.thealgorithms.datastructures.stacks;

/**
 * A generic stack implementation using a singly linked list.
 * This implementation provides O(1) time complexity for push, pop, and peek operations.
 *
 * @param <T> the type of elements held in this stack
 * @author Your Name
 */
public class StackUsingLinkedList < T > {

  /**
   * Inner class representing a node in the linked list.
   */
  private static class Node < T > {
    T data;
    Node < T > next;

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to store in this node
     */
    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private Node < T > top;
  private int size;

  /**
   * Constructs an empty stack.
   */
  public StackUsingLinkedList() {
    this.top = null;
    this.size = 0;
  }

  /**
   * Pushes an element onto the top of this stack.
   * Time Complexity: O(1)
   *
   * @param data the element to be pushed onto this stack
   */
  public void push(T data) {
    Node < T > newNode = new Node < > (data);
    newNode.next = top;
    top = newNode;
    size++;
  }

  /**
   * Removes and returns the element at the top of this stack.
   * Time Complexity: O(1)
   *
   * @return the element at the top of this stack
   * @throws IllegalStateException if this stack is empty
   */
  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty. Cannot pop element.");
    }
    T data = top.data;
    top = top.next;
    size--;
    return data;
  }

  /**
   * Returns the element at the top of this stack without removing it.
   * Time Complexity: O(1)
   *
   * @return the element at the top of this stack
   * @throws IllegalStateException if this stack is empty
   */
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty. Cannot peek element.");
    }
    return top.data;
  }

  /**
   * Checks if this stack is empty.
   * Time Complexity: O(1)
   *
   * @return {@code true} if this stack contains no elements, {@code false} otherwise
   */
  public boolean isEmpty() {
    return top == null;
  }

  /**
   * Returns the number of elements in this stack.
   * Time Complexity: O(1)
   *
   * @return the number of elements in this stack
   */
  public int size() {
    return size;
  }

  /**
   * Removes all elements from this stack.
   * Time Complexity: O(1)
   */
  public void clear() {
    top = null;
    size = 0;
  }

  /**
   * Returns a string representation of this stack.
   * The string representation consists of a list of the stack's elements
   * from top to bottom, enclosed in square brackets ("[]").
   *
   * @return a string representation of this stack
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "[]";
    }

    StringBuilder sb = new StringBuilder("[");
    Node < T > current = top;
    while (current != null) {
      sb.append(current.data);
      if (current.next != null) {
        sb.append(", ");
      }
      current = current.next;
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * Demonstration of the StackUsingLinkedList implementation.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    // Example 1: Stack of Integers
    System.out.println("=== Integer Stack Demo ===");
    StackUsingLinkedList < Integer > intStack = new StackUsingLinkedList < > ();

    // Push elements
    intStack.push(10);
    intStack.push(20);
    intStack.push(30);
    intStack.push(40);
    System.out.println("Stack after pushing 10, 20, 30, 40: " + intStack);
    System.out.println("Size: " + intStack.size());

    // Peek
    System.out.println("Top element (peek): " + intStack.peek());

    // Pop elements
    System.out.println("Popped: " + intStack.pop());
    System.out.println("Popped: " + intStack.pop());
    System.out.println("Stack after popping twice: " + intStack);
    System.out.println("Size: " + intStack.size());

    // Check if empty
    System.out.println("Is stack empty? " + intStack.isEmpty());

    // Pop remaining elements
    intStack.pop();
    intStack.pop();
    System.out.println("Is stack empty after popping all? " + intStack.isEmpty());

    // Example 2: Stack of Strings
    System.out.println("\n=== String Stack Demo ===");
    StackUsingLinkedList < String > stringStack = new StackUsingLinkedList < > ();

    stringStack.push("Hello");
    stringStack.push("World");
    stringStack.push("Java");
    System.out.println("Stack: " + stringStack);
    System.out.println("Top: " + stringStack.peek());
    System.out.println("Popped: " + stringStack.pop());
    System.out.println("Stack after pop: " + stringStack);

    // Example 3: Exception handling
    System.out.println("\n=== Exception Handling Demo ===");
    StackUsingLinkedList < Integer > emptyStack = new StackUsingLinkedList < > ();
    try {
      emptyStack.pop();
    } catch (IllegalStateException e) {
      System.out.println("Caught exception: " + e.getMessage());
    }

    try {
      emptyStack.peek();
    } catch (IllegalStateException e) {
      System.out.println("Caught exception: " + e.getMessage());
    }

    // Example 4: Clear operation
    System.out.println("\n=== Clear Operation Demo ===");
    StackUsingLinkedList < Integer > stack = new StackUsingLinkedList < > ();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println("Stack before clear: " + stack);
    stack.clear();
    System.out.println("Stack after clear: " + stack);
    System.out.println("Is empty? " + stack.isEmpty());
  }
}
