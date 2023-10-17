package com.thealgorithms.stacks;

import java.util.LinkedList;
import java.util.Queue;

// Class to implement stack operations using queues
class StackUsingQueue {

    // Two queues to manage the stack operations
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    // Constructor initializes the queues
    public StackUsingQueue() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    // Recursive function to manage the content of the queues
    public void manageQueue(Queue<Integer> queue1, Queue<Integer> queue2) {
        // Base case: if queue1 is empty, return
        if (queue1.isEmpty()) {
            return;
        }
        // Remove an element from queue1
        int val = queue1.remove();
        // Recursive call for the rest of the elements in queue1
        manageQueue(queue1, queue2);
        // Add the removed element to queue2
        queue2.add(val);
    }

    // Function to add an element to the stack, which is implemented by queue1
    public void push(int x) {
        queue1.add(x);
    }

    // Function to remove the top element from the stack
    public int pop() {
        // Transfer elements from queue1 to queue2 in reverse order
        manageQueue(queue1, queue2);
        // Remove and store the top element from queue2 (which was the bottom element of queue1)
        int ans = queue2.remove();
        // Transfer elements back from queue2 to queue1, preserving the stack order
        manageQueue(queue2, queue1);
        // Return the removed element
        return ans;
    }

    // Function to get the top element of the stack without removing it
    public int top() {
        // Transfer elements from queue1 to queue2 in reverse order
        manageQueue(queue1, queue2);
        // Get and store the top element from queue2 (which was the bottom element of queue1)
        int ans = queue2.peek();
        // Transfer elements back from queue2 to queue1, preserving the stack order
        manageQueue(queue2, queue1);
        // Return the top element
        return ans;
    }

    // Function to check if the stack is empty
    public boolean empty() {
        // The stack is empty if queue1 is empty
        return queue1.isEmpty();
    }

    // Main function to test the stack operations
    public static void main(String[] args) {
        // Create a new StackUsingQueue object
        StackUsingQueue obj = new StackUsingQueue();
        // Perform operations on the stack
        obj.push(1);
        obj.push(2);
        // Display the top element of the stack
        System.out.println("Stack top element: " + obj.top());
        // Remove and display the top element of the stack
        System.out.println("Stack popped element: " + obj.pop());
        // Check and display whether the stack is empty
        System.out.println("Check if Stack is Empty: " + obj.empty());
    }
}
