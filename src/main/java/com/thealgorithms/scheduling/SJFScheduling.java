package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Shortest Job First Algorithm: The algorithm allows the waiting process with the
 * minimal burst time to be executed first. see more here:
 * https://www.guru99.com/shortest-job-first-sjf-scheduling.html
 */

public class SJFScheduling {
    protected ArrayList<ProcessDetails> processes;
    protected ArrayList<String> schedule;

    private static void sortProcessesByArrivalTime(List<ProcessDetails> processes) {
        for (int i = 0; i < processes.size(); i++) {
            for (int j = i + 1; j < processes.size() - 1; j++) {
                if (processes.get(j).getArrivalTime() > processes.get(j + 1).getArrivalTime()) {
                    final var temp = processes.get(j);
                    processes.set(j, processes.get(j + 1));
                    processes.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * a simple constructor
     * @param processes a list of processes the user wants to schedule
     *  it also sorts the processes based on the time of their arrival
     */
    SJFScheduling(final ArrayList<ProcessDetails> processes) {
        this.processes = processes;
        schedule = new ArrayList<>();
        sortProcessesByArrivalTime(this.processes);
    }
    protected void sortByArrivalTime() {
        sortProcessesByArrivalTime(processes);
    }

    /**
     * this functions returns the order of the executions
     */

    public void scheduleProcesses() {
        ArrayList<ProcessDetails> ready = new ArrayList<>();

        int size = processes.size();
        int runtime;
        int time = 0;
        int executed = 0;
        int j;
        int k = 0;
        ProcessDetails running;

        if (size == 0) {
            return;
        }

        while (executed < size) {
            while (k < size && processes.get(k).getArrivalTime() <= time) // here we find the processes that have arrived.
            {
                ready.add(processes.get(k));
                k++;
            }

            running = findShortestJob(ready);
            if (running == null) {
                time++;
            } else {
                runtime = running.getBurstTime();
                for (j = 0; j < runtime; j++) {
                    time++;
                }
                schedule.add(running.getProcessId());
                ready.remove(running);
                executed++;
            }
        }
    }

    /**
     * this function evaluates the shortest job of all the ready processes (based on  a process
     * burst time)
     * @param readyProcesses an array list of ready processes
     * @return returns the process' with the shortest burst time OR NULL if there are no ready
     *     processes
     */
    private ProcessDetails findShortestJob(List<ProcessDetails> readyProcesses) {
        if (readyProcesses.isEmpty()) {
            return null;
        }
        int i;
        int size = readyProcesses.size();
        int minBurstTime = readyProcesses.get(0).getBurstTime();
        int temp;
        int positionOfShortestJob = 0;

        for (i = 1; i < size; i++) {
            temp = readyProcesses.get(i).getBurstTime();
            if (minBurstTime > temp) {
                minBurstTime = temp;
                positionOfShortestJob = i;
            }
        }

        return readyProcesses.get(positionOfShortestJob);
    }
}
