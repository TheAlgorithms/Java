package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The Proportional Share Scheduling (PSS) class implements a dynamic scheduling algorithm.
 * It assigns the CPU to processes based on their weight, ensuring that each process gets CPU time proportional to its weight.
 * The priority boost prevents starvation, ensuring that lower-weight processes still receive CPU time.
 */
public final class ProportionalShareScheduling {
    
    private ProportionalShareScheduling() {
    }

    private List<Process> processes;

    /**
     * Constructs a ProportionalShareScheduling object with a list of processes.
     *
     * @param processes List of processes to be scheduled.
     */
    public ProportionalShareScheduling(final List<Process> processes) {
        this.processes = processes;
    }

    /**
     * Schedules the processes using Proportional Share Scheduling (PSS).
     * Processes are scheduled based on their weight and priority boost.
     * It simulates their execution, calculates the waiting time, and ensures no starvation.
     *
     * @return List of processes after they have been executed in proportion to their weight.
     */
    public List<Process> scheduleProcesses(int totalTimeQuantum) {
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(new ProcessComparator());
        readyQueue.addAll(processes);
        int currentTime = 0;
        List<Process> executedProcesses = new ArrayList<>();

        while (!readyQueue.isEmpty()) {
            Process current = readyQueue.poll();

            // Determine time quantum to allocate to the current process
            int timeGiven = Math.min(current.getWeight(), current.getBurstTime() - current.getTimeReceived());
            currentTime += timeGiven;
            current.setTimeReceived(current.getTimeReceived() + timeGiven);

            System.out.println("Process " + current.getProcessId() + " received " + timeGiven + " units at time " + currentTime);

            // If the process has finished execution
            if (current.getTimeReceived() >= current.getBurstTime()) {
                System.out.println("Process " + current.getProcessId() + " completed at time " + currentTime);
            } else {
                // Starvation prevention: increase priority boost for unfinished processes
                current.setPriorityBoost(current.getPriorityBoost() + 1);
                readyQueue.add(current); // Reinsert into the queue for future execution
            }

            // Starvation prevention logic for all other processes in the queue
            for (Process p : readyQueue) {
                p.setPriorityBoost(p.getPriorityBoost() + 1); // Increase the boost to prevent starvation
            }

            executedProcesses.add(current);
        }

        return executedProcesses;
    }

    /**
     * The Process class represents a process with an ID, weight, burst time, arrival time, time received, and priority boost.
     */
    public static class Process {
        private String processId;
        private int weight;
        private int burstTime;
        private int arrivalTime;
        private int timeReceived;
        private int priorityBoost;

        public Process(String processId, int weight, int burstTime, int arrivalTime) {
            this.processId = processId;
            this.weight = weight;
            this.burstTime = burstTime;
            this.arrivalTime = arrivalTime;
            this.timeReceived = 0;
            this.priorityBoost = 0;
        }

        public String getProcessId() {
            return processId;
        }

        public int getWeight() {
            return weight;
        }

        public int getBurstTime() {
            return burstTime;
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public int getTimeReceived() {
            return timeReceived;
        }

        public void setTimeReceived(int timeReceived) {
            this.timeReceived = timeReceived;
        }

        public int getPriorityBoost() {
            return priorityBoost;
        }

        public void setPriorityBoost(int priorityBoost) {
            this.priorityBoost = priorityBoost;
        }
    }

    /**
     * The ProcessComparator class compares processes based on their effective weight (weight + priority boost).
     */
    public static class ProcessComparator implements Comparator<Process> {
        @Override
        public int compare(Process p1, Process p2) {
            int effectiveWeight1 = p1.getWeight() + p1.getPriorityBoost();
            int effectiveWeight2 = p2.getWeight() + p2.getPriorityBoost();
            return Integer.compare(effectiveWeight2, effectiveWeight1); // Higher weight gets higher priority
        }
    }
}
