package Others;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This implements Stack using single Queue.
 *
 * <p>Big O Runtime: insert(): O(n) remove(): O(1) amortized isEmpty(): O(1)
 *
 * <p>A stack data structure functions the same as a real world stack. The elements that are added
 * last are the first to be removed. New elements are added to the top of the stack.
 *
 * @author munikatoch (https://www.github.com/munikatoch)
 */

/**
 * References - 1. https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Stack.html
 * 2. https://leetcode.com/problems/implement-stack-using-queues/solution/
 */
public class StackUsingQueue {

  // Queue that will keep track of elements inserted and removed
  private Queue<Integer> queue;

  // Init Queue
  public StackUsingQueue() {
    queue = new LinkedList<>();
  }

  /**
   * Inserts an item onto the top of the stack.
   *
   * @param item - The item to be added onto top of the stack
   */
  public void push(int item) {
    queue.add(item);
    int size = queue.size();
    while (size > 1) {
      queue.add(queue.remove());
      size--;
    }
  }

  /**
   * Removes the item at the top of the stack and returns that item as the value of this function.
   *
   * @return The item at the top of the stack
   * @throws EmptyStackException - If stack is empty
   */
  public int pop() {
    if (!queue.isEmpty()) {
      return queue.remove();
    }
    throw new EmptyStackException();
  }

  /**
   * Looks at the item at the top of the stack without removing it from the stack.
   *
   * @return The item at the top of the stack
   * @throws EmptyStackException - If stack is empty
   */
  public int peek() {
    if (!queue.isEmpty()) {
      return queue.peek();
    }
    throw new EmptyStackException();
  }

  /**
   * Tests if this stack is empty.
   *
   * @return true if and only if this stack contains no items; false otherwise
   */
  public boolean empty() {
    return queue.isEmpty();
  }

  /**
   * Returns the number of items in the stack.
   *
   * @return The number of items in the stack
   */
  public int size() {
    return queue.size();
  }

  /**
   * Main method.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    int one = 1;
    int two = 2;
    int three = 3;
    StackUsingQueue myStack = new StackUsingQueue();
    myStack.push(one);
    myStack.push(two);
    myStack.push(three);
    assert myStack.peek() == three;
    assert myStack.pop() == three;
    assert myStack.pop() == two;
    assert !myStack.empty();
    assert myStack.size() == 1;
  }
}
