package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SlackTimeSchedulingTest {

    private SlackTimeScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new SlackTimeScheduling();
    }

    @Test
    public void testAddAndScheduleSingleTask() {
        scheduler.addTask("Task1", 2, 5);
        List<String> expected = List.of("Task1");
        assertEquals(expected, scheduler.scheduleTasks());
    }

    @Test
    public void testScheduleMultipleTasks() {
        scheduler.addTask("Task1", 2, 5);
        scheduler.addTask("Task2", 1, 4);
        scheduler.addTask("Task3", 3, 7);
        List<String> expected = List.of("Task1", "Task2", "Task3");
        assertEquals(expected, scheduler.scheduleTasks());
    }

    @Test
    public void testScheduleTasksWithSameSlackTime() {
        scheduler.addTask("Task1", 2, 5);
        scheduler.addTask("Task2", 3, 6);
        scheduler.addTask("Task3", 1, 4);
        List<String> expected = List.of("Task1", "Task2", "Task3");
        assertEquals(expected, scheduler.scheduleTasks());
    }

    @Test
    public void testEmptyScheduler() {
        List<String> expected = List.of();
        assertEquals(expected, scheduler.scheduleTasks());
    }
}
