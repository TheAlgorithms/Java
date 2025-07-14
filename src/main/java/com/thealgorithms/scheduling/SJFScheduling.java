package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Shortest Job First (SJF) Scheduling Algorithm:
 * Executes processes with the shortest burst time first among the ones that have arrived.
 */
public class SJFScheduling {
    private final List<ProcessDetails> processes;
    private final List<String> schedule;

    public SJFScheduling(final List<ProcessDetails> processes) {
        this.processes = new ArrayList<>(processes);
        this.schedule = new ArrayList<>();
        sortProcessesByArrivalTime(this.processes);
    }

    private static void sortProcessesByArrivalTime(List<ProcessDetails> processes) {
        processes.sort(Comparator.comparingInt(ProcessDetails::getArrivalTime));
    }

    /**
     * Executes the SJF scheduling algorithm and builds the execution order.
     */
    public void scheduleProcesses() {
        List<ProcessDetails> ready = new ArrayList<>();
        int size = processes.size();
        int time = 0;
        int executed = 0;

        Iterator<ProcessDetails> processIterator = processes.iterator();

        // This will track the next process to be checked for arrival time
        ProcessDetails nextProcess = null;
        if (processIterator.hasNext()) {
            nextProcess = processIterator.next();
        }

        while (executed < size) {
            // Load all processes that have arrived by current time
            while (nextProcess != null && nextProcess.getArrivalTime() <= time) {
                ready.add(nextProcess);
                if (processIterator.hasNext()) {
                    nextProcess = processIterator.next();
                } else {
                    nextProcess = null;
                }
            }

            ProcessDetails running = findShortestJob(ready);
            if (running == null) {
                time++;
            } else {
                time += running.getBurstTime();
                schedule.add(running.getProcessId());
                ready.remove(running);
                executed++;
            }
        }
    }

    /**
     * Finds the process with the shortest job of all the ready processes (based on  a process
     * @param readyProcesses an array list of ready processes
     * @return returns the process' with the shortest burst time OR NULL if there are no ready
     *     processes
     */
    private ProcessDetails findShortestJob(Collection<ProcessDetails> readyProcesses) {
        return readyProcesses.stream().min(Comparator.comparingInt(ProcessDetails::getBurstTime)).orElse(null);
    }

    /**
     * Returns the computed schedule after calling scheduleProcesses().
     */
    public List<String> getSchedule() {
        return schedule;
    }

    public List<ProcessDetails> getProcesses() {
        return List.copyOf(processes);
    }
}
