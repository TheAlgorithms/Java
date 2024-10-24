package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GangSchedulingTest {

    private GangScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new GangScheduling();
    }

    @Test
    public void testAddGangAndTask() {
        scheduler.addGang("Gang1");
        scheduler.addTaskToGang("Gang1", "Task1");
        Map<String, List<String>> expected = Map.of("Gang1", List.of("Task1"));
        assertEquals(expected, scheduler.getGangSchedules());
    }

    @Test
    public void testMultipleGangs() {
        scheduler.addGang("Gang1");
        scheduler.addGang("Gang2");
        scheduler.addTaskToGang("Gang1", "Task1");
        scheduler.addTaskToGang("Gang2", "Task2");
        Map<String, List<String>> expected = Map.of("Gang1", List.of("Task1"), "Gang2", List.of("Task2"));
        assertEquals(expected, scheduler.getGangSchedules());
    }

    @Test
    public void testGangWithMultipleTasks() {
        scheduler.addGang("Gang1");
        scheduler.addTaskToGang("Gang1", "Task1");
        scheduler.addTaskToGang("Gang1", "Task2");
        Map<String, List<String>> expected = Map.of("Gang1", List.of("Task1", "Task2"));
        assertEquals(expected, scheduler.getGangSchedules());
    }

    @Test
    public void testEmptyGangSchedule() {
        scheduler.addGang("Gang1");
        Map<String, List<String>> expected = Map.of("Gang1", List.of());
        assertEquals(expected, scheduler.getGangSchedules());
    }
}
