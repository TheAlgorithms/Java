package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Preemptive Priority Scheduling Algorithm
 * @author [Bama Charan Chhandogi](https://www.github.com/BamaCharanChhandogi)
 */

class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int priority;

    Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public final class PreemptivePriorityScheduling {
    private PreemptivePriorityScheduling() {
    }
    public static List<String> preemptivePriorityScheduling(List<Process> processes) {
        List<String> ganttChart = new ArrayList<>();
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(p -> - p.priority));

        int currentTime = 0;

        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            while (!processes.isEmpty() && processes.get(0).arrivalTime <= currentTime) {
                readyQueue.add(processes.remove(0));
            }

            if (!readyQueue.isEmpty()) {
                Process currentProcess = readyQueue.poll();

                ganttChart.add(currentProcess.name);
                currentProcess.burstTime--;

                if (currentProcess.burstTime > 0) {
                    readyQueue.add(currentProcess);
                }
            } else {
                ganttChart.add("Idle");
            }

            currentTime++;
        }

        return ganttChart;
    }
}
