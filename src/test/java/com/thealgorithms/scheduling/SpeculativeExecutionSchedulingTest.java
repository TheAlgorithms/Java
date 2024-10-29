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

        // Check for null before splitting
        if (result != null) {
            String[] parts = result.split(" started at ");

            // Validate task name and ensure start time is a valid timestamp
            assertEquals("Task1", parts[0]);
            assertTrue(Long.parseLong(parts[1]) > 0, "Start time should be greater than 0");
        } else {
            // Handle the case where result is null
            assertTrue(false, "The result should not be null after executing the task.");
        }
    }

    @Test
    public void testMultipleTasksInGroup() {
        scheduler.addTask("Group1", "Task1");
        scheduler.addTask("Group1", "Task2");

        // Execute the first task
        String result1 = scheduler.executeTasks("Group1");

        // Check for null before splitting
        if (result1 != null) {
            String[] parts1 = result1.split(" started at ");
            assertEquals("Task1", parts1[0]);
            assertTrue(Long.parseLong(parts1[1]) > 0, "Start time should be greater than 0");
        } else {
            // Handle the case where result1 is null
            assertTrue(false, "The result for Task1 should not be null.");
        }

        // Execute the second task
        String result2 = scheduler.executeTasks("Group1");

        // Check for null before splitting
        if (result2 != null) {
            String[] parts2 = result2.split(" started at ");
            assertEquals("Task2", parts2[0]);
            assertTrue(Long.parseLong(parts2[1]) > 0, "Start time should be greater than 0");
        } else {
            // Handle the case where result2 is null
            assertTrue(false, "The result for Task2 should not be null.");
        }
    }

    @Test
    public void testExecuteAllTasks() {
        scheduler.addTask("Group1", "Task1");
        scheduler.addTask("Group1", "Task2");

        scheduler.executeTasks("Group1");
        scheduler.executeTasks("Group1");

        // Confirm executing tasks again returns null
        assertNull(scheduler.executeTasks("Group1"));
    }

    @Test
    public void testEmptyTaskGroup() {
        // Confirm executing tasks on an empty group returns null
        assertNull(scheduler.executeTasks("Group2"));
    }
}
