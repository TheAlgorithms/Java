/**
 * Implementation of Stack using two Queues.
 * 
 * A Stack follows LIFO (Last In First Out) order.
 * We can simulate stack behavior using two queues (q1 and q2).
 * 
 * Approach:
 * - For every push operation:
 *     1. Add element to q2.
 *     2. Move all elements from q1 to q2.
 *     3. Swap q1 and q2.
 * - For pop operation:
 *     - Remove the front element from q1.
 * - For top operation:
 *     - Return the front element from q1 without removing it.
 * 
 * Time Complexity:
 *     push(x): O(n)
 *     pop():   O(1)
 *     top():   O(1)
 * 
 * Space Complexity: O(n)
 * 
 * Author: Pradyumn Pratap Singh (Strange)
 * For: Hacktoberfest / Open Source Contribution
 */

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    /**
     * Push an element onto the stack.
     * 
     * @param x element to be pushed
     */
    public void push(int x) {
        // Step 1: Add new element to q2
        q2.add(x);

        // Step 2: Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }

        // Step 3: Swap the references of q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    /**
     * Pop (remove) the top element from the stack.
     * 
     * @return the popped element, or -1 if stack is empty
     */
    public int pop() {
        if (q1.isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        return q1.poll();
    }

    /**
     * Get the top element of the stack.
     * 
     * @return the top element, or -1 if stack is empty
     */
    public int top() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return q1.peek();
    }

    /**
     * Check if the stack is empty.
     * 
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return q1.isEmpty();
    }

    // ---------------- TEST CASES ----------------
    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();

        System.out.println("Pushing elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.top()); // 30
        System.out.println("Pop element: " + stack.pop());  // 30
        System.out.println("Top after pop: " + stack.top()); // 20
        System.out.println("Is stack empty? " + stack.isEmpty()); // false

        stack.pop();
        stack.pop();
        System.out.println("Pop on empty stack: " + stack.pop()); // -1 (Underflow)
        System.out.println("Is stack empty? " + stack.isEmpty()); // true
    }
}
