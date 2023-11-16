import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    // Constructor
    public StackUsingQueues() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    // Push element x onto stack
    public void push(int x) {
        // Always push to queue1
        queue1.add(x);
        
        // Move all elements from queue2 to queue1
        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }

        // Swap the names of queue1 and queue2
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    // Removes the element on top of the stack and returns that element
    public int pop() {
        if (queue2.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return queue2.remove();
    }

    // Get the top element
    public int top() {
        if (queue2.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return queue2.peek();
    }

    // Returns whether the stack is empty
    public boolean empty() {
        return queue2.isEmpty();
    }

    public static void main(String[] args) {
        StackUsingQueues stack = new StackUsingQueues();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());    // returns 2
        System.out.println(stack.pop());    // returns 2
        System.out.println(stack.empty());  // returns false
    }
}
