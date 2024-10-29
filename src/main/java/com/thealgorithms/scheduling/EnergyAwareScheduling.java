package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EnergyAwareScheduling schedules tasks based on their energy consumption profile and execution time,
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
        int executionTime;

        Task(String name, int energyConsumption, int executionTime) {
            this.name = name;
            this.energyConsumption = energyConsumption;
            this.executionTime = executionTime;
        }
    }

    private final List<Task> tasks;

    public EnergyAwareScheduling() {
        tasks = new ArrayList<>();
    }

    public void addTask(String name, int energyConsumption, int executionTime) {
        tasks.add(new Task(name, energyConsumption, executionTime));
    }

    /**
     * Schedules tasks in an order that minimizes cumulative energy consumption and
     * returns both the scheduled order and the total energy consumption.
     * Steps:
     * 1. Sort tasks by energy consumption per time unit to minimize cumulative energy cost.
     * 2. Schedule tasks in the order of sorted tasks.
     * 3. Calculate the total energy consumption.
     * 4. Return the scheduled order and total energy consumption.
     *
     * @return a map containing the scheduled order and total energy consumption.
     */
    public Map<String, Object> scheduleTasks() {
        tasks.sort(Comparator.comparingInt(t -> t.energyConsumption));

        List<String> scheduledOrder = new ArrayList<>();
        int currentTime = 0;
        int totalEnergyConsumption = 0;
        for (Task task : tasks) {
            scheduledOrder.add(task.name);
            currentTime += task.executionTime;
            totalEnergyConsumption += currentTime * task.energyConsumption;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("scheduledOrder", scheduledOrder);
        result.put("totalEnergyConsumption", totalEnergyConsumption);
        return result;
    }
}
