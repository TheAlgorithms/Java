package com.thealgorithms.stacks;

import java.util.LinkedList;
import java.util.Queue;

public class QueueBasedStack {

    private Queue<Object> queue1;
    private Queue<Object> queue2;

    public QueueBasedStack() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    public void push(Object element) {
        queue1.add(element);
        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }

        Queue<Object> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public Object pop() {
        if (queue2.isEmpty()) {
            return -1;
        } else {
            return queue2.remove();
        }
    }

    public Object top() {
        if (queue2.isEmpty()) {
            return -1;
        } else {
            return queue2.peek();
        }
    }

    public static void main(String[] args) {
        QueueBasedStack stack = new QueueBasedStack();
        stack.push(1);
        stack.push(2);
        stack.push("Juice");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
