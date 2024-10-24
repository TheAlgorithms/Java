package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals("Task1", scheduler.executeTasks("Group1"));
    }

    @Test
    public void testMultipleTasksInGroup() {
        scheduler.addTask("Group1", "Task1");
        scheduler.addTask("Group1", "Task2");
        assertEquals("Task1", scheduler.executeTasks("Group1"));
        assertEquals("Task2", scheduler.executeTasks("Group1"));
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
        assertNull(scheduler.executeTasks("Group2"));
    }
}
