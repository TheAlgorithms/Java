package com.thealgorithms.sorts;

/*
 *  @author Aiswarya PM (https://github.com/aishuarya)
 * The SleepSort class demonstrates the Sleep Sort algorithm.
 * Sleep Sort is a sorting algorithm that works by leveraging the concurrency of threads.
 * For each number in the list, a thread is created that sleeps for a duration proportional
 * to the value of the number. After waking up, the thread prints the number.
 * Thus, numbers with smaller values wake up and are printed earlier than those with larger values.
 *
 * Note: The sleep duration is not always precise due to system scheduling and thread management.
 * As a result, this algorithm may not always produce correct results for all inputs, especially
 * with closely adjacent numbers. For such cases, using a BlockingQueue to store and retrieve
 * numbers in the order they are processed can ensure correct output.
 */

import java.util.ArrayList;
import java.util.Collections;

final class SleepSort {

    private SleepSort() {
    }

    public static void sleepSort(ArrayList<Integer> array) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int numbers : array) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(numbers); // Sleep for 'num' milliseconds
                    System.out.print(numbers + " "); // Print the number after sleeping
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            threads.add(thread); // Add the thread to the ArrayList
            thread.start(); // Start the thread
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // Wait for each thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        ArrayList<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 15, 23, 8, 41, 30);

        System.out.println("Sorting numbers using Sleep Sort:");
        sleepSort(numbers);
    }
}
