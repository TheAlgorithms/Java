package com.thealgorithms.scheduling;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AgingScheduling is an algorithm designed to prevent starvation
 * by gradually increasing the priority of waiting tasks.
 * The longer a process waits, the higher its priority becomes.
 *
 * Use Case: Useful in systems with mixed workloads to avoid
 * lower-priority tasks being starved by higher-priority tasks.
 *
 * @author Hardvan
 */
public final class AgingScheduling {

    static class Task {
        String name;
        int waitTime;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
            this.waitTime = 0;
        }
    }

    private final Queue<Task> taskQueue;

    public AgingScheduling() {
        taskQueue = new LinkedList<>();
    }

    /**
     * Adds a task to the scheduler with a given priority.
     *
     * @param name name of the task
     * @param priority priority of the task
     */
    public void addTask(String name, int priority) {
        taskQueue.offer(new Task(name, priority));
    }

    /**
     * Schedules the next task based on the priority and wait time.
     * The priority of a task increases with the time it spends waiting.
     *
     * @return name of the next task to be executed
     */
    public String scheduleNext() {
        if (taskQueue.isEmpty()) {
            return null;
        }
        Task nextTask = taskQueue.poll();
        nextTask.waitTime++;
        nextTask.priority += nextTask.waitTime;
        taskQueue.offer(nextTask);
        return nextTask.name;
    }
}
