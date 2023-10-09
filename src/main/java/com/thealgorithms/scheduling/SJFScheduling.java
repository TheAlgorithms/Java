package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;

/**
 * Implementation of Shortest Job First Algorithm: The algorithm allows the waiting process with the
 * minimal burst time to be executed first. see more here:
 * https://www.guru99.com/shortest-job-first-sjf-scheduling.html
 */

public class SJFScheduling {
    protected ArrayList<ProcessDetails> processes;
    protected ArrayList<String> schedule;

    /**
     * a simple constructor
     * @param processes a list of processes the user wants to schedule
     *  it also sorts the processes based on the time of their arrival
     */
    SJFScheduling(final ArrayList<ProcessDetails> processes) {
        this.processes = processes;
        schedule = new ArrayList<>();
        sortByArrivalTime();
    }
    protected void sortByArrivalTime() {
        int size = processes.size(), i, j;
        ProcessDetails temp;
        for (i = 0; i < size; i++) {
            for (j = i + 1; j < size - 1; j++) {
                if (processes.get(j).getArrivalTime() > processes.get(j + 1).getArrivalTime()) {
                    temp = processes.get(j);
                    processes.set(j, processes.get(j + 1));
                    processes.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * this functions returns the order of the executions
     */

    public void scheduleProcesses() {
        ArrayList<ProcessDetails> ready = new ArrayList<>();

        int size = processes.size(), runtime, time = 0;
        int executed = 0, j, k = 0;
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
     * @param ReadyProcesses an array list of ready processes
     * @return returns the process' with the shortest burst time OR NULL if there are no ready
     *     processes
     */
    private ProcessDetails findShortestJob(ArrayList<ProcessDetails> ReadyProcesses) {
        if (ReadyProcesses.isEmpty()) {
            return null;
        }
        int i, size = ReadyProcesses.size();
        int minBurstTime = ReadyProcesses.get(0).getBurstTime(), temp, positionOfShortestJob = 0;

        for (i = 1; i < size; i++) {
            temp = ReadyProcesses.get(i).getBurstTime();
            if (minBurstTime > temp) {
                minBurstTime = temp;
                positionOfShortestJob = i;
            }
        }

        return ReadyProcesses.get(positionOfShortestJob);
    }
}
