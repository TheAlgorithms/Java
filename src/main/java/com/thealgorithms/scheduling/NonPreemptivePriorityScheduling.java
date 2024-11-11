package com.thealgorithms.scheduling;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class implements the Non-Preemptive Priority Scheduling algorithm.
 * Processes are executed in order of their priority. The process with the
 * highest priority (lower priority number) is executed first,
 * and once a process starts executing, it cannot be preempted.
 */
public final class NonPreemptivePriorityScheduling {

    private NonPreemptivePriorityScheduling() {
    }

    /**
     * Represents a process with an ID, burst time, priority, arrival time, and start time.
     */
    static class Process implements Comparable<Process> {
        int id;
        int arrivalTime;
        int startTime;
        int burstTime;
        int priority;

        /**
         * Constructs a Process instance with the specified parameters.
         *
         * @param id          Unique identifier for the process
         * @param arrivalTime Time when the process arrives in the system
         * @param burstTime   Time required for the process execution
         * @param priority    Priority of the process
         */
        Process(int id, int arrivalTime, int burstTime, int priority) {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.startTime = -1;
            this.burstTime = burstTime;
            this.priority = priority;
        }

        /**
         * Compare based on priority for scheduling. The process with the lowest
         * priority is selected first.
         * If two processes have the same priority, the one that arrives earlier is selected.
         *
         * @param other The other process to compare against
         * @return A negative integer, zero, or a positive integer as this process
         *         is less than, equal to, or greater than the specified process.
         */
        @Override
        public int compareTo(Process other) {
            if (this.priority == other.priority) {
                return Integer.compare(this.arrivalTime, other.arrivalTime);
            }
            return Integer.compare(this.priority, other.priority);
        }
    }

    /**
     * Schedules processes based on their priority in a non-preemptive manner, considering their arrival times.
     *
     * @param processes Array of processes to be scheduled.
     * @return Array of processes in the order they are executed.
     */
    public static Process[] scheduleProcesses(Process[] processes) {
        PriorityQueue<Process> pq = new PriorityQueue<>();
        Queue<Process> waitingQueue = new LinkedList<>();
        int currentTime = 0;
        int index = 0;
        Process[] executionOrder = new Process[processes.length];

        Collections.addAll(waitingQueue, processes);

        while (!waitingQueue.isEmpty() || !pq.isEmpty()) {
            // Add processes that have arrived to the priority queue
            while (!waitingQueue.isEmpty() && waitingQueue.peek().arrivalTime <= currentTime) {
                pq.add(waitingQueue.poll());
            }

            if (!pq.isEmpty()) {
                Process currentProcess = pq.poll();
                currentProcess.startTime = currentTime;
                executionOrder[index++] = currentProcess;
                currentTime += currentProcess.burstTime;
            } else {
                // If no process is ready, move to the next arrival time
                currentTime = waitingQueue.peek().arrivalTime;
            }
        }

        return executionOrder;
    }

    /**
     * Calculates the average waiting time of the processes.
     *
     * @param processes      Array of processes.
     * @param executionOrder Array of processes in execution order.
     * @return Average waiting time.
     */
    public static double calculateAverageWaitingTime(Process[] processes, Process[] executionOrder) {
        int totalWaitingTime = 0;

        for (Process process : executionOrder) {
            int waitingTime = process.startTime - process.arrivalTime;
            totalWaitingTime += waitingTime;
        }

        return (double) totalWaitingTime / processes.length;
    }

    /**
     * Calculates the average turn-around time of the processes.
     *
     * @param processes      Array of processes.
     * @param executionOrder Array of processes in execution order.
     * @return Average turn-around time.
     */
    public static double calculateAverageTurnaroundTime(Process[] processes, Process[] executionOrder) {
        int totalTurnaroundTime = 0;

        for (Process process : executionOrder) {
            int turnaroundTime = process.startTime + process.burstTime - process.arrivalTime;
            totalTurnaroundTime += turnaroundTime;
        }

        return (double) totalTurnaroundTime / processes.length;
    }
}
