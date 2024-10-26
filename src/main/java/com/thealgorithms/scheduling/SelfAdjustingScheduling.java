package com.thealgorithms.scheduling;

import java.util.PriorityQueue;

/**
 * SelfAdjustingScheduling is an algorithm where tasks dynamically adjust
 * their priority based on real-time feedback, such as wait time and CPU usage.
 * Tasks that wait longer will automatically increase their priority,
 * allowing for better responsiveness and fairness in task handling.
 *
 * Use Case: Real-time systems that require dynamic prioritization
 * of tasks to maintain system responsiveness and fairness.
 *
 * @author Hardvan
 */
public final class SelfAdjustingScheduling {

    private static class Task implements Comparable<Task> {
        String name;
        int waitTime;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.waitTime = 0;
            this.priority = priority;
        }

        void incrementWaitTime() {
            waitTime++;
            priority = priority + waitTime;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    private final PriorityQueue<Task> taskQueue;

    public SelfAdjustingScheduling() {
        taskQueue = new PriorityQueue<>();
    }

    public void addTask(String name, int priority) {
        taskQueue.offer(new Task(name, priority));
    }

    public String scheduleNext() {
        if (taskQueue.isEmpty()) {
            return null;
        }
        Task nextTask = taskQueue.poll();
        nextTask.incrementWaitTime();
        taskQueue.offer(nextTask);
        return nextTask.name;
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}
