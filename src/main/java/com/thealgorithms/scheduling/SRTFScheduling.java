package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Shortest Remaining Time First Scheduling Algorithm.
 * In the SRTF scheduling algorithm, the process with the smallest amount of time remaining until completion is selected to execute.
 * Example:
 * Consider the processes p1, p2 and the following table with info about their arrival and burst time:
 * Process | Burst Time | Arrival Time
 * P1      | 6 ms        | 0 ms
 * P2      | 2 ms        | 1 ms
 * In this example, P1 will be executed at time = 0 until time = 1 when P2 arrives. At time = 2, P2 will be executed until time = 4. At time 4, P2 is done, and P1 is executed again to be done.
 * That's a simple example of how the algorithm works.
 * More information you can find here -> https://en.wikipedia.org/wiki/Shortest_remaining_time
 */
public class SRTFScheduling {
    protected List<ProcessDetails> processes;
    protected List<String> ready;

    /**
     * Constructor
     * @param processes ArrayList of ProcessDetails given as input
     */
    public SRTFScheduling(ArrayList<ProcessDetails> processes) {
        this.processes = new ArrayList<>();
        ready = new ArrayList<>();
        this.processes = processes;
    }

    public void evaluateScheduling() {
        int time = 0;
        int cr = 0; // cr=current running process, time= units of time
        int n = processes.size();
        int[] remainingTime = new int[n];

        /* calculating remaining time of every process and total units of time */
        for (int i = 0; i < n; i++) {
            remainingTime[i] = processes.get(i).getBurstTime();
            time += processes.get(i).getBurstTime();
        }

        /* if the first process doesn't arrive at 0, we have more units of time */
        if (processes.get(0).getArrivalTime() != 0) {
            time += processes.get(0).getArrivalTime();
        }

        /* printing id of the process which is executed at every unit of time */
        // if the first process doesn't arrive at 0, we print only \n until it arrives
        if (processes.get(0).getArrivalTime() != 0) {
            for (int i = 0; i < processes.get(0).getArrivalTime(); i++) {
                ready.add(null);
            }
        }

        for (int i = processes.get(0).getArrivalTime(); i < time; i++) {
            /* checking if there's a process with remaining time less than current running process.
               If we find it, then it executes. */
            for (int j = 0; j < n; j++) {
                if (processes.get(j).getArrivalTime() <= i && (remainingTime[j] < remainingTime[cr] && remainingTime[j] > 0 || remainingTime[cr] == 0)) {
                    cr = j;
                }
            }
            ready.add(processes.get(cr).getProcessId());
            remainingTime[cr]--;
        }
    }
}
