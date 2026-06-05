package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Preemptive Shortest Job First (SJF) Scheduling Algorithm:
 * Executes processes with the shortest remaining burst time first among the ones that have arrived.
 * When a new process arrives with a shorter remaining time than the currently running process,
 * the CPU is preempted and given to the new process.
 */
public class PreemptiveSJFScheduling {

    private List<ProcessDetails> processes;

    public PreemptiveSJFScheduling(final List<ProcessDetails> processes) {
        this.processes = new ArrayList<>(processes);
    }

    /**
     * Evaluates waiting time for all processes and sets it in each ProcessDetails object.
     * Waiting time is calculated as the total time a process spends waiting in the ready queue.
     *
     * @param processes List of processes to evaluate
     */
    public static void evaluateWaitingTime(List<ProcessDetails> processes) {
        evaluateTurnAroundTime(processes);
        for (ProcessDetails process : processes) {
            process.setWaitingTime(process.getTurnAroundTimeTime() - process.getBurstTime());
        }
    }

    /**
     * Evaluates turn around time for all processes and sets it in each ProcessDetails object.
     * Turn around time is calculated as the total time from arrival to completion.
     *
     * @param processes List of processes to evaluate
     */
    public static void evaluateTurnAroundTime(List<ProcessDetails> processes) {
        int n = processes.size();
        if (n == 0) {
            return;
        }

        int[] remainingTime = new int[n];
        int[] completionTime = new int[n];
        boolean[] completed = new boolean[n];
        int completedCount = 0;
        int currentTime = 0;

        for (int i = 0; i < n; i++) {
            remainingTime[i] = processes.get(i).getBurstTime();
        }

        while (completedCount < n) {
            int shortestIndex = -1;
            int minRemainingTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!completed[i] && processes.get(i).getArrivalTime() <= currentTime) {
                    if (remainingTime[i] < minRemainingTime ||
                        (remainingTime[i] == minRemainingTime &&
                         processes.get(i).getArrivalTime() < processes.get(shortestIndex).getArrivalTime()) ||
                        (remainingTime[i] == minRemainingTime &&
                         processes.get(i).getArrivalTime() == processes.get(shortestIndex).getArrivalTime() &&
                         processes.get(i).getProcessId().compareTo(processes.get(shortestIndex).getProcessId()) < 0)) {
                        minRemainingTime = remainingTime[i];
                        shortestIndex = i;
                    }
                }
            }

            if (shortestIndex != -1) {
                remainingTime[shortestIndex]--;
                currentTime++;

                if (remainingTime[shortestIndex] == 0) {
                    completionTime[shortestIndex] = currentTime;
                    completed[shortestIndex] = true;
                    completedCount++;
                }
            } else {
                currentTime++;
            }
        }

        for (int i = 0; i < n; i++) {
            processes.get(i).setTurnAroundTimeTime(completionTime[i] - processes.get(i).getArrivalTime());
        }
    }

    /**
     * Schedules the processes using Preemptive SJF algorithm and returns the average waiting time.
     *
     * @param processes List of processes to schedule
     * @return Average waiting time of all processes
     */
    public static double schedule(List<ProcessDetails> processes) {
        evaluateWaitingTime(processes);
        int totalWaitingTime = 0;
        for (ProcessDetails process : processes) {
            totalWaitingTime += process.getWaitingTime();
        }
        return (double) totalWaitingTime / processes.size();
    }
}
