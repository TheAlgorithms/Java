package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SpeculativeExecutionScheduling runs multiple copies of the same task in parallel,
 * picking the first one that finishes successfully. It is used to mitigate the
 * impact of stragglers (slow tasks).
 *
 * Use Case: Big data systems like Hadoop and Spark, where it helps improve job completion time.
 *
 * @author Hardvan
 */
public final class SpeculativeExecutionScheduling {

    static class Task {
        String name;
        boolean completed;

        Task(String name) {
            this.name = name;
            this.completed = false;
        }

        void complete() {
            this.completed = true;
        }
    }

    private final Map<String, List<Task>> taskGroups;

    public SpeculativeExecutionScheduling() {
        taskGroups = new HashMap<>();
    }

    /**
     * Adds a task to the specified group.
     *
     * @param groupName the name of the group
     * @param taskName  the name of the task
     */
    public void addTask(String groupName, String taskName) {
        List<Task> tasks = taskGroups.computeIfAbsent(groupName, k -> new ArrayList<>());
        tasks.add(new Task(taskName));
    }

    /**
     * Executes the tasks in the specified group.
     *
     * @param groupName the name of the group
     * @return the name of the task that completed successfully
     */
    public String executeTasks(String groupName) {
        List<Task> tasks = taskGroups.get(groupName);
        if (tasks == null) {
            return null;
        }
        for (Task task : tasks) {
            if (!task.completed) {
                task.complete();
                return task.name;
            }
        }
        return null;
    }
}
