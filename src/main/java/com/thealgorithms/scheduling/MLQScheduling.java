package com.thealgorithms.scheduling;

import java.util.*;

/**
 * MLQ scheduling, processes are divided into multiple queues based on their priority, with each queue having a different priority level.
 * Higher-priority processes are placed in queues with higher priority levels, while lower-priority processes are placed in queues with lower priority levels.
 * MLQ Scheduling algorithm. This can be understood here -
 * https://www.geeksforgeeks.org/multilevel-queue-mlq-cpu-scheduling/
 */

class Process {

  String name;
  int priority;
  int burstTime;

  public Process(String name, int priority, int burstTime) {
    this.name = name;
    this.priority = priority;
    this.burstTime = burstTime;
  }
}

public class MLQScheduler {

  ArrayList<Queue<Process>> queues;
  int numQueues;

  public MLQScheduler(int numQueues) {
    this.numQueues = numQueues;
    queues = new ArrayList<>(numQueues);
    for (int i = 0; i < numQueues; i++) {
      queues.add(new LinkedList<>());
    }
  }

  public void addProcess(Process process) {
    // Add a process to the highest-priority queue (Queue 0)
    System.out.println("Process " + process.name + " Added");
    queues.get(0).add(process);
  }

  public void start() {
    while (!queues.get(0).isEmpty() || !queues.get(numQueues - 1).isEmpty()) {
      // Start from the highest-priority queue (Queue 0)
      for (int i = 0; i < numQueues; i++) {
        Queue<Process> currentQueue = queues.get(i);
        if (!currentQueue.isEmpty()) {
          // Get the next process from the current queue
          Process currentProcess = currentQueue.poll();
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
            System.out.println(
              "Process " + currentProcess.name + " has completed."
            );
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    MLQScheduler scheduler = new MLQScheduler(3); // Three priority queues
    // Add processes to the scheduler with different priorities and burst times
    scheduler.addProcess(new Process("P1", 0, 5));
    scheduler.addProcess(new Process("P2", 1, 4));
    scheduler.addProcess(new Process("P3", 2, 3));
    scheduler.addProcess(new Process("P4", 0, 2));

    // Run the scheduler
    scheduler.start();
  }
}
