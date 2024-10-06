package com.thealgorithms.scheduling;

import java.util.PriorityQueue;

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
     * Represents a process with an ID, burst time, priority, and start time.
     */
    static class Process implements Comparable<Process> {
        int id;
        int startTime;
        int burstTime;
        int priority;

        /**
         * Constructs a Process instance with the specified parameters.
         *
         * @param id        Unique identifier for the process
         * @param burstTime Time required for the process execution
         * @param priority  Priority of the process
         */
        Process(int id, int burstTime, int priority) {
            this.id = id;
            this.startTime = -1;
            this.burstTime = burstTime;
            this.priority = priority;
        }

        /**
         * Compare based on priority for scheduling. The process with the lowest
         * priority is selected first.
         *
         * @param other The other process to compare against
         * @return A negative integer, zero, or a positive integer as this process
         *         is less than, equal to, or greater than the specified process.
         */
        @Override
        public int compareTo(Process other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    /**
     * Schedules processes based on their priority in a non-preemptive manner.
     *
     * @param processes Array of processes to be scheduled.
     * @return Array of processes in the order they are executed.
     */
    public static Process[] scheduleProcesses(Process[] processes) {
        PriorityQueue<Process> pq = new PriorityQueue<>();
        for (Process process : processes) {
            pq.add(process);
        }

        Process[] executionOrder = new Process[processes.length];
        int index = 0;
        int currentTime = 0;

        while (!pq.isEmpty()) {
            Process currentProcess = pq.poll();
            currentProcess.startTime = currentTime;
            executionOrder[index++] = currentProcess;
            currentTime += currentProcess.burstTime;
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
        int currentTime = 0;

        for (Process process : executionOrder) {
            totalWaitingTime += currentTime;
            currentTime += process.burstTime;
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
        int currentTime = 0;

        for (Process process : executionOrder) {
            currentTime += process.burstTime;
            totalTurnaroundTime += currentTime;
        }

        return (double) totalTurnaroundTime / processes.length;
    }
}
