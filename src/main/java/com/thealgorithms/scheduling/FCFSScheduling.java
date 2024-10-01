package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.List;

/**
 * Non-preemptive First Come First Serve (FCFS)
 * scheduling algorithm.
 * In FCFS scheduling, processes are executed in the order they arrive in the
 * ready queue.
 * The first process that arrives gets executed first, followed by the next
 * process, and so on.
 */
public class FCFSScheduling {

    // Attributes
    private List<ProcessDetails> processes;// List of processes to be scheduled

    /**
     * Constructor to initialize the FCFSScheduling class with a list of processes.
     *
     * @param processes a list of ProcessDetails representing the processes to be
     *                  scheduled
     */
    FCFSScheduling(final List<ProcessDetails> processes) {
        this.processes = processes;
    }

    /**
     * Schedules the processes using FCFS algorithm by evaluating the waiting time
     * and turnaround time for each process.
     */
    public void scheduleProcesses() {
        evaluateWaitingTime(); // Calculate waiting time for each process
        evaluateTurnAroundTime(); // Calculate turnaround time for each process
    }

    /**
     * Evaluates the waiting time for each process in the list.
     * The waiting time for the first process is set to 0, and each subsequent
     * process's waiting time is calculated based on the
     * burst time of the previous processes.
     */
    private void evaluateWaitingTime() {
        int processesNumber = processes.size(); // Get the no. of processes

        if (processesNumber == 0) {
            return; // no processes to schedule
        }

        int waitingTime = 0;
        int burstTime = processes.get(0).getBurstTime(); // Burst time of the 1st process

        // Set the waiting time for the 1st process to 0
        processes.get(0).setWaitingTime(waitingTime);

        // Calculate waiting time for each process starting from the second
        for (int i = 1; i < processesNumber; i++) {
            // Waiting time for the current process = waiting time of the previous
            // process plus its burst time
            processes.get(i).setWaitingTime(waitingTime + burstTime);

            // Update the waiting time and burst time for the next process
            waitingTime = processes.get(i).getWaitingTime();
            burstTime = processes.get(i).getBurstTime();
        }
    }

    /**
     * Evaluates the turnaround time for each process in the list.
     * Turnaround time is calculated as the sum of the burst time and the waiting
     * time for each process.
     */
    private void evaluateTurnAroundTime() {
        for (final var process : processes) {
            // Calculate the turnaround time for the current process
            process.setTurnAroundTimeTime(process.getBurstTime() + process.getWaitingTime());
        }
    }
}
