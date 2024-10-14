package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The LotteryScheduling class implements the Lottery Scheduling algorithm, which is
 * a probabilistic CPU scheduling algorithm. Processes are assigned tickets, and
 * the CPU is allocated to a randomly selected process based on ticket count.
 * Processes with more tickets have a higher chance of being selected.
 */
public final class LotteryScheduling {
    private LotteryScheduling() {
    }

    private List<Process> processes;
    private Random random;

    /**
     * Constructs a LotteryScheduling object with the provided list of processes.
     *
     * @param processes List of processes to be scheduled using Lottery Scheduling.
     */
    public LotteryScheduling(final List<Process> processes) {
        this.processes = processes;
        this.random = new Random();
    }

    /**
     * Constructs a LotteryScheduling object with the provided list of processes and a Random object.
     *
     * @param processes List of processes to be scheduled using Lottery Scheduling.
     * @param random    Random object used for generating random numbers.
     */
    public LotteryScheduling(final List<Process> processes, Random random) {
        this.processes = processes;
        this.random = random;
    }

    /**
     * Schedules the processes using the Lottery Scheduling algorithm.
     * Each process is assigned a certain number of tickets, and the algorithm randomly
     * selects a process to execute based on ticket count. The method calculates the
     * waiting time and turnaround time for each process and simulates their execution.
     */
    public List<Process> scheduleProcesses() {
        int totalTickets = processes.stream().mapToInt(Process::getTickets).sum();
        int currentTime = 0;
        List<Process> executedProcesses = new ArrayList<>();

        while (!processes.isEmpty()) {
            int winningTicket = random.nextInt(totalTickets) + 1;
            Process selectedProcess = selectProcessByTicket(winningTicket);

            if (selectedProcess == null) {
                // This should not happen in normal circumstances, but we'll handle it just in case
                System.err.println("Error: No process selected. Recalculating total tickets.");
                totalTickets = processes.stream().mapToInt(Process::getTickets).sum();
                continue;
            }

            selectedProcess.setWaitingTime(currentTime);
            currentTime += selectedProcess.getBurstTime();
            selectedProcess.setTurnAroundTime(selectedProcess.getWaitingTime() + selectedProcess.getBurstTime());

            executedProcesses.add(selectedProcess);
            processes.remove(selectedProcess);

            totalTickets = processes.stream().mapToInt(Process::getTickets).sum();
        }

        return executedProcesses;
    }

    /**
     * Selects a process based on a winning ticket. The method iterates over the
     * list of processes, and as the ticket sum accumulates, it checks if the
     * current process holds the winning ticket.
     *
     * @param winningTicket The randomly generated ticket number that determines the selected process.
     * @return The process associated with the winning ticket.
     */
    private Process selectProcessByTicket(int winningTicket) {
        int ticketSum = 0;
        for (Process process : processes) {
            ticketSum += process.getTickets();
            if (ticketSum >= winningTicket) {
                return process;
            }
        }
        return null;
    }

    /**
     * The Process class represents a process in the scheduling system. Each process has
     * an ID, burst time (CPU time required for execution), number of tickets (used in
     * lottery selection), waiting time, and turnaround time.
     */
    public static class Process {
        private String processId;
        private int burstTime;
        private int tickets;
        private int waitingTime;
        private int turnAroundTime;

        public Process(String processId, int burstTime, int tickets) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.tickets = tickets;
        }

        public String getProcessId() {
            return processId;
        }

        public int getBurstTime() {
            return burstTime;
        }

        public int getTickets() {
            return tickets;
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
