package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * EnergyAwareScheduling schedules tasks based on their energy consumption profile,
 * aiming to minimize the overall energy usage of the system.
 *
 * Use Case: Mobile devices, embedded systems, and data centers that need to save power
 * while maintaining performance.
 *
 * @author Hardvan
 */
public final class EnergyAwareScheduling {

    static class Task {
        String name;
        int energyConsumption;

        Task(String name, int energyConsumption) {
            this.name = name;
            this.energyConsumption = energyConsumption;
        }
    }

    private final List<Task> tasks;

    public EnergyAwareScheduling() {
        tasks = new ArrayList<>();
    }

    public void addTask(String name, int energyConsumption) {
        tasks.add(new Task(name, energyConsumption));
    }

    public List<String> scheduleTasks() {
        tasks.sort(Comparator.comparingInt(t -> t.energyConsumption));
        List<String> scheduledOrder = new ArrayList<>();
        for (Task task : tasks) {
            scheduledOrder.add(task.name);
        }
        return scheduledOrder;
    }
}
