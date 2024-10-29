package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiAgentSchedulingTest {

    private MultiAgentScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new MultiAgentScheduling();
    }

    @Test
    public void testAddAgentAndAssignTask() {
        scheduler.addAgent("Agent1");
        scheduler.assignTask("Agent1", "Task1");
        Map<String, List<String>> expected = Map.of("Agent1", List.of("Task1"));
        assertEquals(expected, scheduler.getScheduledTasks());
    }

    @Test
    public void testMultipleAgentsWithTasks() {
        scheduler.addAgent("Agent1");
        scheduler.addAgent("Agent2");
        scheduler.assignTask("Agent1", "Task1");
        scheduler.assignTask("Agent2", "Task2");
        Map<String, List<String>> expected = Map.of("Agent1", List.of("Task1"), "Agent2", List.of("Task2"));
        assertEquals(expected, scheduler.getScheduledTasks());
    }

    @Test
    public void testAgentWithMultipleTasks() {
        scheduler.addAgent("Agent1");
        scheduler.assignTask("Agent1", "Task1");
        scheduler.assignTask("Agent1", "Task2");
        Map<String, List<String>> expected = Map.of("Agent1", List.of("Task1", "Task2"));
        assertEquals(expected, scheduler.getScheduledTasks());
    }

    @Test
    public void testEmptyAgentSchedule() {
        scheduler.addAgent("Agent1");
        Map<String, List<String>> expected = Map.of("Agent1", List.of());
        assertEquals(expected, scheduler.getScheduledTasks());
    }
}
