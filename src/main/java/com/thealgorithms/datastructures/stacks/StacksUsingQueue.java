package com.thealgorithms.datastructures.stacks;

import java.util.LinkedList;
import java.util.Queue;

class StackUsingQueues {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();
    private int top;

    public StackUsingQueues() {
    }

    public void push(int x) {
        if (queue1.isEmpty()) {
            queue1.add(x);
        } else {
            queue2.add(x);
            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }
        top = x;
    }

    public int pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        int popValue = queue1.poll();
        if (!queue1.isEmpty()) {
            top = queue1.peek();
        }
        return popValue;
    }

    public int top() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return top;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

class Main {
    public static void main(String[] args) {
        StackUsingQueues stack = new StackUsingQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Top element: " + stack.top()); // Output:  Top element: 5
        System.out.println("Popped: " + stack.pop());     // Output: Popped: 5
        System.out.println("Is empty: " + stack.empty());  // Output: Is empty: false
    }
}
