package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FairShareSchedulingTest {

    private FairShareScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new FairShareScheduling();
    }

    @Test
    public void testAllocateResourcesSingleUser() {
        scheduler.addUser("User1");
        scheduler.addTask("User1", 5);
        scheduler.allocateResources(100);
        Map<String, Integer> expected = Map.of("User1", 100);
        assertEquals(expected, scheduler.getAllocatedResources());
    }

    @Test
    public void testAllocateResourcesMultipleUsers() {
        scheduler.addUser("User1");
        scheduler.addUser("User2");
        scheduler.addUser("User3");
        scheduler.addTask("User1", 2);
        scheduler.addTask("User2", 3);
        scheduler.addTask("User3", 5);
        scheduler.allocateResources(100);
        Map<String, Integer> expected = Map.of("User1", 20, "User2", 30, "User3", 50);
        assertEquals(expected, scheduler.getAllocatedResources());
    }

    @Test
    public void testAllocateResourcesZeroWeightUser() {
        scheduler.addUser("User1");
        scheduler.addUser("User2");
        scheduler.addTask("User2", 5);
        scheduler.allocateResources(100);
        Map<String, Integer> expected = Map.of("User1", 0, "User2", 100);
        assertEquals(expected, scheduler.getAllocatedResources());
    }

    @Test
    public void testAllocateResourcesEqualWeights() {
        scheduler.addUser("User1");
        scheduler.addUser("User2");
        scheduler.addTask("User1", 1);
        scheduler.addTask("User2", 1);
        scheduler.allocateResources(100);
        Map<String, Integer> expected = Map.of("User1", 50, "User2", 50);
        assertEquals(expected, scheduler.getAllocatedResources());
    }
}
