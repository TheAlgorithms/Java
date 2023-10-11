package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A multilevel queue scheduling algorithm divides the ready queue into multiple levels or tiers, each with a different priority.
 * The appropriate level is then assigned to the processes based on their characteristics, such as priority, memory requirements, and CPU usage.
 * Scheduling algorithm. This can be understood here -
 * <a href="https://www.geeksforgeeks.org/multilevel-queue-mlq-cpu-scheduling/">...</a>
 */

class ProcessTask {
    String name;
    int priority;
    int burstTime;

    public ProcessTask(String name, int priority, int burstTime) {
        this.name = name;
        this.priority = priority;
        this.burstTime = burstTime;
    }
}

public class MLQScheduling {
    ArrayList<Queue<ProcessTask>> queues;
    int numQueues;

    public MLQScheduling(int numQueues) {
        this.numQueues = numQueues;
        queues = new ArrayList<>(numQueues);
        for (int i = 0; i < numQueues; i++) {
          queues.add(new LinkedList<>());
        }
    }

    public void addProcess(ProcessTask process) {
        // Add a process to the highest-priority queue (Queue 0)
        queues.get(0).add(process);
    }

    public void run() {
        while (!queues.get(0).isEmpty() || !queues.get(numQueues - 1).isEmpty()) {
          // Start from the highest-priority queue (Queue 0)
            for (int i = 0; i < numQueues; i++) {
                Queue<ProcessTask> currentQueue = queues.get(i);
                if (!currentQueue.isEmpty()) {
                // Get the next process from the current queue
                ProcessTask currentProcess = currentQueue.poll();
                System.out.println("Running process: " + currentProcess.name);
    
                // Simulate process execution (reduce burst time)
                int remainingBurstTime = currentProcess.burstTime - 1;
                    if (remainingBurstTime > 0) {
                        // Move the process to a lower-priority queue
                        int nextQueueIndex = Math.min(i + 1, numQueues - 1);
                        currentProcess.burstTime = remainingBurstTime;
                        currentProcess.priority = nextQueueIndex; // Update priority
                        queues.get(nextQueueIndex).add(currentProcess);
                        System.out.println("Moving process to Queue " + nextQueueIndex);
                    } else {
                        // Process has completed
                        System.out.println("-> Process " + currentProcess.name + " has completed.");
                    }
                }
            }
        }
        System.out.println("**All Process are completed**");
    }

    public static void main(String[] args) {
        MLQScheduling scheduler = new MLQScheduling(3); // Three priority queues
        // Add processes to the scheduler with different priorities and burst times
        scheduler.addProcess(new ProcessTask("P1", 0, 5));
        scheduler.addProcess(new ProcessTask("P2", 1, 4));
        scheduler.addProcess(new ProcessTask("P3", 2, 3));
        scheduler.addProcess(new ProcessTask("P4", 0, 2));
    
        // Run the scheduler
        scheduler.run();
    }
}
