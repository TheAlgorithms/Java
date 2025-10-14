package com.thealgorithms.datastructures.queues;

/**
 * Implementation of a Double Ended Queue (Deque) using Array
 * A deque allows insertion and deletion from both front and rear ends.
 * Author: Sonu Dodwariya (Hacktoberfest 2025)
 */

public class DequeUsingArray {

    private int[] arr;
    private int front;
    private int rear;
    private int size;

    // Constructor
    public DequeUsingArray(int size) {
        arr = new int[size];
        front = -1;
        rear = 0;
        this.size = size;
    }

    // Check if deque is full
    public boolean isFull() {
        return (front == 0 && rear == size - 1) || (front == rear + 1);
    }

    // Check if deque is empty
    public boolean isEmpty() {
        return (front == -1);
    }

    // Insert element at front
    public void insertFront(int key) {
        if (isFull()) {
            System.out.println("Deque is full!");
            return;
        }

        // First element
        if (front == -1) {
            front = 0;
            rear = 0;
        } else if (front == 0)
            front = size - 1;
        else
            front = front - 1;

        arr[front] = key;
    }

    // Insert element at rear
    public void insertRear(int key) {
        if (isFull()) {
            System.out.println("Deque is full!");
            return;
        }

        // First element
        if (front == -1) {
            front = 0;
            rear = 0;
        } else if (rear == size - 1)
            rear = 0;
        else
            rear = rear + 1;

        arr[rear] = key;
    }

    // Delete element at front
    public void deleteFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
            return;
        }

        // Single element
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size - 1)
            front = 0;
        else
            front = front + 1;
    }

    // Delete element at rear
    public void deleteRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
            return;
        }

        // Single element
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (rear == 0)
            rear = size - 1;
        else
            rear = rear - 1;
    }

    // Get front element
    public int getFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
            return -1;
        }
        return arr[front];
    }

    // Get rear element
    public int getRear() {
        if (isEmpty() || rear < 0) {
            System.out.println("Deque is empty!");
            return -1;
        }
        return arr[rear];
    }

    // Display all elements
    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
            return;
        }

        System.out.print("Deque elements: ");
        int i = front;
        while (true) {
            System.out.print(arr[i] + " ");
            if (i == rear)
                break;
            i = (i + 1) % size;
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        DequeUsingArray dq = new DequeUsingArray(5);

        dq.insertRear(10);
        dq.insertRear(20);
        dq.insertFront(5);
        dq.insertFront(2);
        dq.display();

        System.out.println("Front element: " + dq.getFront());
        System.out.println("Rear element: " + dq.getRear());

        dq.deleteFront();
        dq.deleteRear();
        dq.display();
    }
}
