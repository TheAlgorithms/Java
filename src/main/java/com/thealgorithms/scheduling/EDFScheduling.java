package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The Earliest Deadline First (EDF) Scheduling class implements a dynamic scheduling algorithm.
 * It assigns the CPU to processes with the earliest deadlines, ensuring that deadlines are met if possible.
 * This scheduling algorithm is ideal for real-time systems where meeting deadlines is critical.
 */
public final class EDFScheduling {
    private EDFScheduling() {
    }

    private List<Process> processes;

    /**
     * Constructs an EDFScheduling object with a list of processes.
     *
     * @param processes List of processes to be scheduled.
     */
    public EDFScheduling(final List<Process> processes) {
        this.processes = processes;
    }

    /**
     * Schedules the processes using Earliest Deadline First (EDF) scheduling.
     * Processes are sorted by their deadlines, and the method simulates their execution.
     * It calculates the waiting time and turnaround time for each process.
     *
     * @return List of processes after they have been executed in order of earliest deadline first.
     */
    public List<Process> scheduleProcesses() {
        processes.sort(Comparator.comparingInt(Process::getDeadline));

        int currentTime = 0;
        List<Process> executedProcesses = new ArrayList<>();

        for (Process process : processes) {
            process.setWaitingTime(currentTime);
            currentTime += process.getBurstTime();
            process.setTurnAroundTime(process.getWaitingTime() + process.getBurstTime());

            if (currentTime > process.getDeadline()) {
                System.out.println("Warning: Process " + process.getProcessId() + " missed its deadline.");
            }

            executedProcesses.add(process);
        }

        return executedProcesses;
    }

    /**
     * The Process class represents a process with an ID, burst time, deadline, waiting time, and turnaround time.
     */
    public static class Process {
        private String processId;
        private int burstTime;
        private int deadline;
        private int waitingTime;
        private int turnAroundTime;

        public Process(String processId, int burstTime, int deadline) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.deadline = deadline;
        }

        public String getProcessId() {
            return processId;
        }

        public int getBurstTime() {
            return burstTime;
        }

        public int getDeadline() {
            return deadline;
        }

        public int getWaitingTime() {
            return waitingTime;
        }

        public void setWaitingTime(int waitingTime) {
            this.waitingTime = waitingTime;
        }

        public int getTurnAroundTime() {
            return turnAroundTime;
        }

        public void setTurnAroundTime(int turnAroundTime) {
            this.turnAroundTime = turnAroundTime;
        }
    }
}
