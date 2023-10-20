package com.thealgorithms.stacks;

import java.util.LinkedList;
import java.util.Queue;

public class QueueBasedStack {

    private Queue<Object> queue1;
    private Queue<Object> queue2;

    public QueueBasedStack() {
        // Initialize two queues
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    public void push(Object element) {
        // To simulate a stack push operation, add the element to queue1.
        queue1.add(element);

        // To maintain the order of elements as in a stack, move all elements from queue2 to queue1.
        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }

        // Swap the references of queue1 and queue2, making queue2 the new 'top' of the stack.
        Queue<Object> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public Object pop() {
        // To simulate a stack pop operation, check if queue2 is empty. If it's empty, the stack is also empty.
        if (queue2.isEmpty()) {
            return -1; // You might consider using a different value or exception for an empty stack.
        } else {
            // Remove and return the element from queue2, which is the top of the stack.
            return queue2.remove();
        }
    }

    public Object top() {
        // To simulate a stack top operation, check if queue2 is empty. If it's empty, the stack is also empty.
        if (queue2.isEmpty()) {
            return -1; // You might consider using a different value or exception for an empty stack.
        } else {
            // Return the element from queue2, which is the top of the stack (without removing it).
            return queue2.peek();
        }
    }

    public static void main(String[] args) {
        // Create an instance of QueueBasedStack
        QueueBasedStack stack = new QueueBasedStack();

        // Push some elements onto the stack
        stack.push(1);
        stack.push(2);
        stack.push("Juice");

        // Pop elements from the stack and print them
        System.out.println(stack.pop()); // Should print "Juice"
        System.out.println(stack.pop()); // Should print "2"
        System.out.println(stack.pop()); // Should print "1"
    }
}

