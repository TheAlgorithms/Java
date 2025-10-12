/**
 * Stack implementation using singly linked list.
 * Supports push, pop, peek, isEmpty, and size operations in O(1) time.
 *
 * Issue: #6715
 * Author: @Kamal1023
 */
public class StackUsingLinkedList<T> {
  private Node<T> top;
  private int size;

  // Inner Node class
  private static class Node<T> {
    T data;
    Node<T> next;
    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  /** Pushes an element onto the stack */
  public void push(T data) {
    Node<T> node = new Node<>(data);
    node.next = top;
    top = node;
    size++;
  }

  /** Removes and returns the top element of the stack */
  public T pop() {
    if (isEmpty()) throw new RuntimeException("Stack is empty");
    T data = top.data;
    top = top.next;
    size--;
    return data;
  }

  /** Returns the top element without removing it */
  public T peek() {
    if (isEmpty()) throw new RuntimeException("Stack is empty");
    return top.data;
  }

  /** Checks if the stack is empty */
  public boolean isEmpty() {
    return top == null;
  }

  /** Returns the number of elements in the stack */
  public int size() {
    return size;
  }

  /** Demo/test for StackUsingLinkedList */
  public static void main(String[] args) {
    StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
    stack.push(10);
    stack.push(20);
    stack.push(30);

    System.out.println("Top: " + stack.peek()); // 30
    System.out.println("Size: " + stack.size()); // 3

    while (!stack.isEmpty()) {
      System.out.println("Pop: " + stack.pop());
    }
  }
}
