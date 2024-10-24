package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.List;

/**
 * ProportionalFairScheduling allocates resources to processes based on their
 * proportional weight or importance. It aims to balance fairness with
 * priority, ensuring that higher-weight processes receive a larger share of resources.
 *
 * Use Case: Network bandwidth allocation in cellular networks (4G/5G),
 * where devices receive a proportional share of bandwidth.
 *
 * @author Hardvan
 */
public final class ProportionalFairScheduling {

    static class Process {
        String name;
        int weight;
        int allocatedResources;

        Process(String name, int weight) {
            this.name = name;
            this.weight = weight;
            this.allocatedResources = 0;
        }
    }

    private final List<Process> processes;

    public ProportionalFairScheduling() {
        processes = new ArrayList<>();
    }

    public void addProcess(String name, int weight) {
        processes.add(new Process(name, weight));
    }

    public void allocateResources(int totalResources) {
        int totalWeight = processes.stream().mapToInt(p -> p.weight).sum();
        for (Process process : processes) {
            process.allocatedResources = (int) ((double) process.weight / totalWeight * totalResources);
        }
    }

    public List<String> getAllocatedResources() {
        List<String> allocation = new ArrayList<>();
        for (Process process : processes) {
            allocation.add(process.name + ": " + process.allocatedResources);
        }
        return allocation;
    }
}
