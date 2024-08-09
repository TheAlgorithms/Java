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
import java.util.List;

final class SleepSort {

    private SleepSort() {
    }

    /**
     * @param array the list of integers to sort
     * @return the sorted list of integers
     */
    public static List<Integer> sleepSort(List<Integer> array) {

        // List to collect sorted elements
        List<Integer> sortedList = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        // Validate that all numbers are non-negative before starting any threads
        for (int number : array) {
            if (number < 0) {
                throw new IllegalArgumentException("All numbers must be non-negative. Found: " + number);
            }
        }

        for (final int number : array) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(number); // Sleep for 'number' milliseconds
                    sortedList.add(number); // Add the number to the list after sleeping
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    e.printStackTrace();
                }
            });

            threads.add(thread); // Add the thread to the list
            thread.start(); // Start the thread
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join(); // Wait for each thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Return the sorted list
        // The list is synchronized, so no need for additional synchronization here
        Collections.sort(sortedList);
        return sortedList;
    }
}
