package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EnergyAwareSchedulingTest {

    private EnergyAwareScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new EnergyAwareScheduling();
    }

    @Test
    public void testAddAndScheduleSingleTask() {
        scheduler.addTask("Task1", 10);
        List<String> expected = List.of("Task1");
        assertEquals(expected, scheduler.scheduleTasks());
    }

    @Test
    public void testScheduleMultipleTasks() {
        scheduler.addTask("Task1", 10);
        scheduler.addTask("Task2", 5);
        scheduler.addTask("Task3", 15);
        List<String> expected = List.of("Task2", "Task1", "Task3");
        assertEquals(expected, scheduler.scheduleTasks());
    }

    @Test
    public void testScheduleTasksWithSameConsumption() {
        scheduler.addTask("Task1", 10);
        scheduler.addTask("Task2", 10);
        scheduler.addTask("Task3", 5);
        List<String> expected = List.of("Task3", "Task1", "Task2");
        assertEquals(expected, scheduler.scheduleTasks());
    }

    @Test
    public void testEmptyScheduler() {
        List<String> expected = List.of();
        assertEquals(expected, scheduler.scheduleTasks());
    }
}
