package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgingSchedulingTest {

    private AgingScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new AgingScheduling();
    }

    @Test
    public void testAddAndScheduleSingleTask() {
        scheduler.addTask("Task1", 5);
        assertEquals("Task1", scheduler.scheduleNext());
    }

    @Test
    public void testAddMultipleTasks() {
        scheduler.addTask("Task1", 1);
        scheduler.addTask("Task2", 1);
        assertEquals("Task1", scheduler.scheduleNext());
        assertEquals("Task2", scheduler.scheduleNext());
    }

    @Test
    public void testPriorityAdjustmentWithWait() {
        scheduler.addTask("Task1", 1);
        scheduler.addTask("Task2", 1);
        scheduler.scheduleNext();
        scheduler.scheduleNext();
        assertEquals("Task1", scheduler.scheduleNext());
    }

    @Test
    public void testEmptyScheduler() {
        assertNull(scheduler.scheduleNext());
    }

    @Test
    public void testMultipleRounds() {
        scheduler.addTask("Task1", 1);
        scheduler.addTask("Task2", 2);
        scheduler.scheduleNext();
        scheduler.scheduleNext();
        assertEquals("Task1", scheduler.scheduleNext());
    }
}
