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

    static class Process implements Comparable<Process> {
        int id; // Process ID
        int burstTime; // Time required by the process for execution
        int priority; // Priority of the process (lower value indicates higher priority)

        Process(int id, int burstTime, int priority) {
            this.id = id;
            this.burstTime = burstTime;
            this.priority = priority;
        }

        /**
         * Compare based on priority for scheduling. The process with the lowest
         * priority is selected first.
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

        // Execute processes based on their priority
        while (!pq.isEmpty()) {
            executionOrder[index++] = pq.poll(); // Poll the process with the highest priority
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

    public static void main(String[] args) {
        Process[] processes = {new Process(1, 10, 2), new Process(2, 5, 1), new Process(3, 8, 3)};

        Process[] executionOrder = scheduleProcesses(processes);

        System.out.println("Process ID | Burst Time | Priority");
        for (Process process : executionOrder) {
            System.out.printf("%d          %d           %d%n", process.id, process.burstTime, process.priority);
        }

        System.out.printf("Average Waiting Time: %.2f%n", calculateAverageWaitingTime(processes, executionOrder));
        System.out.printf("Average Turnaround Time: %.2f%n", calculateAverageTurnaroundTime(processes, executionOrder));
    }
}
