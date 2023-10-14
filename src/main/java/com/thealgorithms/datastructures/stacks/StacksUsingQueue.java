package com.thealgorithms.datastructures.stacks;

import java.util.LinkedList;
import java.util.Queue;

class StackUsingQueues {
    /* To implement stacks using queues,
        the idea is to make use of 2 queues - one for push operation and the other for pop operation
     */ 
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();
    private int top;

    //Initializing the data structure - Constructor
    public StackUsingQueues() {
    }


    // PUSH ELEMENT x ONTO STACK
    /* If queue1 is empty, simply add x to queue1
        If queue2 is not empty, then add x to queue2 and poll(dequeue) each element from queue1
        and add it to queue2
        (This is necessary to ensure LIFO property of the stack)
        Lastly, using temp, swap queue1 and queue2
        And keep a track of the newly pushed element in top variable
     */
    public void push(int x) {
        // Add the new element to the empty queue
        if (queue1.isEmpty()) {
            queue1.add(x);
        } 
        else {
            queue2.add(x);
            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }
        top = x; // Update the top element
    }

    
    //REMOVES THE ELEMENT ON THE TOP OF THE STACK AND RETURNS THAT ELEMENT
    /* First check of the stack is empty. If empty, throw an exception
        If it is not empty, return the element from poll() on queue1
        And move top to the next element of queue1 using peek()
     */
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


    //GET THE TOP ELEMENT
    /* Simply return the value of top if the stack is not empty.
        If the stack is empty, throw an exception
    */
    public int top() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return top;
    }


    //RETURNS WHETHER THE STACK IS EMPTY OR NOT
    /* Simply return the value of the inbuilt function isEmpty() on queue1*/
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