package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnergyAwareSchedulingTest {

    private EnergyAwareScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new EnergyAwareScheduling();
    }

    @Test
    public void testAddAndScheduleSingleTask() {
        scheduler.addTask("Task1", 10, 5);

        List<String> expectedOrder = List.of("Task1");
        int expectedEnergyConsumption = 10 * 5;

        Map<String, Object> result = scheduler.scheduleTasks();
        assertEquals(expectedOrder, result.get("scheduledOrder"));
        assertEquals(expectedEnergyConsumption, result.get("totalEnergyConsumption"));
    }

    @Test
    public void testScheduleMultipleTasks() {
        scheduler.addTask("Task1", 10, 3);
        scheduler.addTask("Task2", 5, 2);
        scheduler.addTask("Task3", 15, 4);

        List<String> expectedOrder = List.of("Task2", "Task1", "Task3");

        int expectedEnergyConsumption = 195;

        Map<String, Object> result = scheduler.scheduleTasks();
        assertEquals(expectedOrder, result.get("scheduledOrder"));
        assertEquals(expectedEnergyConsumption, result.get("totalEnergyConsumption"));
    }

    @Test
    public void testScheduleTasksWithSameConsumption() {
        scheduler.addTask("Task1", 10, 2);
        scheduler.addTask("Task2", 10, 1);
        scheduler.addTask("Task3", 5, 3);

        List<String> expectedOrder = List.of("Task3", "Task1", "Task2");
        int expectedEnergyConsumption = 125;

        Map<String, Object> result = scheduler.scheduleTasks();
        assertEquals(expectedOrder, result.get("scheduledOrder"));
        assertEquals(expectedEnergyConsumption, result.get("totalEnergyConsumption"));
    }

    @Test
    public void testEmptyScheduler() {
        Map<String, Object> result = scheduler.scheduleTasks();

        List<String> expectedOrder = List.of();
        int expectedEnergyConsumption = 0;

        assertEquals(expectedOrder, result.get("scheduledOrder"));
        assertEquals(expectedEnergyConsumption, result.get("totalEnergyConsumption"));
    }
}
