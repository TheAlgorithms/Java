package DataStructures.Queues;

import java.util.Stack;

public class QueueUsingStack {
  private Stack<Integer> stack1;
  private Stack<Integer> stack2;
  private int front;

  public QueueUsingStack() {
    stack1 = new Stack<Integer>();
    stack2 = new Stack<Integer>();
  }

  public void push(int x) {
    if (stack1.isEmpty()) {
      front = x;
    }
    stack1.push(x);
  }

  public int pop() {
    if (stack2.isEmpty()) {
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
    }
    return stack2.pop();
  }

  public int peek() {
    if (!stack2.isEmpty()) {
      return stack2.peek();
    }
    return front;
  }

  public boolean empty() {
    return stack1.isEmpty() && stack2.isEmpty();
  }
}
// https://leetcode.com/problems/implement-queue-using-stacks/solution/