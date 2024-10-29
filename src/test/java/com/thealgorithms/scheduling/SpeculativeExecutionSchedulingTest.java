package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpeculativeExecutionSchedulingTest {

    private SpeculativeExecutionScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new SpeculativeExecutionScheduling();
    }

    @Test
    public void testAddAndExecuteTask() {
        scheduler.addTask("Group1", "Task1");

        String result = scheduler.executeTasks("Group1");
        String[] parts = result.split(" started at ");

        // Validate task name and ensure start time is a valid timestamp
        assertEquals("Task1", parts[0]);
        assertTrue(Long.parseLong(parts[1]) > 0, "Start time should be greater than 0");
    }

    @Test
    public void testMultipleTasksInGroup() {
        scheduler.addTask("Group1", "Task1");
        scheduler.addTask("Group1", "Task2");

        // Execute the first task
        String result1 = scheduler.executeTasks("Group1");
        String[] parts1 = result1.split(" started at ");
        assertEquals("Task1", parts1[0]);
        assertTrue(Long.parseLong(parts1[1]) > 0, "Start time should be greater than 0");

        // Execute the second task
        String result2 = scheduler.executeTasks("Group1");
        String[] parts2 = result2.split(" started at ");
        assertEquals("Task2", parts2[0]);
        assertTrue(Long.parseLong(parts2[1]) > 0, "Start time should be greater than 0");
    }

    @Test
    public void testExecuteAllTasks() {
        scheduler.addTask("Group1", "Task1");
        scheduler.addTask("Group1", "Task2");

        scheduler.executeTasks("Group1");
        scheduler.executeTasks("Group1");

        assertNull(scheduler.executeTasks("Group1"));
    }

    @Test
    public void testEmptyTaskGroup() {
        // Confirm executing tasks on an empty group returns null
        assertNull(scheduler.executeTasks("Group2"));
    }
}
