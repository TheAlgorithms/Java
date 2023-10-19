package com.thealgorithms.stacks;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

// Class to implement stack operations using queues
class StackUsingQueue {

    // Two queues to manage the stack operations
    Queue<Object> queue1;
    Queue<Object> queue2;

    // Constructor initializes the queues
    public StackUsingQueue() {
        queue1 = new LinkedList<Object>();
        queue2 = new LinkedList<Object>();
    }

    // Recursive function to manage the content of the queues
    public void manageQueue(Queue<Object> queue1, Queue<Object> queue2) {
        // Base case: if queue1 is empty, return
        if (queue1.isEmpty()) {
            return;
        }
        // Remove an element from queue1
        Object val = queue1.remove();
        // Recursive call for the rest of the elements in queue1
        manageQueue(queue1, queue2);
        // Add the removed element to queue2
        queue2.add(val);
    }

    // Function to add an element to the stack, which is implemented by queue1
    public void push(Object x) {
        try {
            if (x == null) {
                throw new IllegalArgumentException("Null elements cannot be pushed onto the stack.");
            }
            queue1.add(x);
        } catch (Exception e) {
            // You can choose to print the stack trace, log the error, or handle it in a
            // manner appropriate for your application.
            System.err.println("Error occurred while pushing onto the stack: " + e.getMessage());
            // Optional: rethrow the exception after logging or handling.
            throw e;
        }
    }

    // Function to remove the top element from the stack
    public Object pop() {
        if (empty()) { // Check whether the stack is empty before attempting to pop elements.
            throw new NoSuchElementException("Stack is empty. Cannot pop element.");
        }
        // Transfer elements from queue1 to queue2 in reverse order
        manageQueue(queue1, queue2);
        // Remove and store the top element from queue2 (which was the bottom element of
        // queue1)
        Object ans = queue2.remove();
        // Transfer elements back from queue2 to queue1, preserving the stack order
        manageQueue(queue2, queue1);
        // Return the removed element
        return ans;
    }

    // Function to get the top element of the stack without removing it
    public Object top() {
        if (empty()) { // Check whether the stack is empty before attempting to peek at the top
                       // element.
            throw new NoSuchElementException("Stack is empty. Cannot retrieve top element.");
        }
        // Transfer elements from queue1 to queue2 in reverse order
        manageQueue(queue1, queue2);
        // Get and store the top element from queue2 (which was the bottom element of
        // queue1)
        Object ans = queue2.peek();
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
        try {
            // Perform operations on the stack
            obj.push(1);
            obj.push(2);
            obj.push("Hello");
            // Display the top element of the stack
            System.out.println("Stack top element: " + obj.top()); // "Hello"
            // Remove and display the top element of the stack
            System.out.println("Stack popped element: " + obj.pop()); // "Hello"
            // Check and display whether the stack is empty
            System.out.println("Check if Stack is Empty: " + obj.empty()); // false

            // The following scenario is expected to throw an exception because the stack
            // will be empty.
            obj.pop(); // "2"
            obj.pop(); // "1"
            System.out.println("Stack popped element: " + obj.pop()); // NoSuchElementException
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }
}
