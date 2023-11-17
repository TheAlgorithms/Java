import java.util.LinkedList;
import java.util.Queue;

/**
 * Stack implementation using two queues.
 * Reference: https://en.wikipedia.org/wiki/Stack_(abstract_data_type)#Implementation
 */
public class StackUsingQueues {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    /**
     * Constructor to initialize the queues.
     */
    public StackUsingQueues() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /**
     * Push element x onto the stack.
     * 
     * @param x The element to be pushed onto the stack.
     */
    public void push(int x) {
        queue1.add(x); // Always push to queue1
        
        while (!queue2.isEmpty()) { // Move all elements from queue2 to queue1
            queue1.add(queue2.remove());
        }

        Queue<Integer> temp = queue1; // Swap the names of queue1 and queue2
        queue1 = queue2;
        queue2 = temp;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     * 
     * @return The top element of the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    public int pop() {
        if (queue2.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return queue2.remove();
    }

    /**
     * Get the top element of the stack.
     * 
     * @return The top element of the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    public int top() {
        if (queue2.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return queue2.peek();
    }

    /**
     * Returns whether the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise.
     */
    public boolean empty() {
        return queue2.isEmpty();
    }
}
