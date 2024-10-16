package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelfAdjustingSchedulingTest {

    private SelfAdjustingScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new SelfAdjustingScheduling();
    }

    @Test
    public void testAddAndScheduleSingleTask() {
        scheduler.addTask("Task1", 5);
        assertEquals("Task1", scheduler.scheduleNext());
    }

    @Test
    public void testAddMultipleTasks() {
        scheduler.addTask("Task1", 5);
        scheduler.addTask("Task2", 1);
        scheduler.addTask("Task3", 3);
        assertEquals("Task2", scheduler.scheduleNext());
        assertEquals("Task2", scheduler.scheduleNext());
        assertEquals("Task3", scheduler.scheduleNext());
    }

    @Test
    public void testPriorityAdjustment() {
        scheduler.addTask("Task1", 1);
        scheduler.addTask("Task2", 1);
        scheduler.scheduleNext();
        scheduler.scheduleNext();
        scheduler.scheduleNext();
        assertEquals("Task2", scheduler.scheduleNext());
    }

    @Test
    public void testEmptyScheduler() {
        assertNull(scheduler.scheduleNext());
    }

    @Test
    public void testTaskReschedulingAfterWait() {
        scheduler.addTask("Task1", 1);
        scheduler.addTask("Task2", 2);
        scheduler.scheduleNext();
        scheduler.scheduleNext();
        assertEquals("Task1", scheduler.scheduleNext());
    }
}
