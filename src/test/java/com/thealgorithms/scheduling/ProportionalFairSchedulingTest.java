package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProportionalFairSchedulingTest {

    private ProportionalFairScheduling scheduler;

    @BeforeEach
    public void setup() {
        scheduler = new ProportionalFairScheduling();
    }

    @Test
    public void testAllocateResourcesSingleProcess() {
        scheduler.addProcess("Process1", 5);
        scheduler.allocateResources(100);
        List<String> expected = List.of("Process1: 100");
        assertEquals(expected, scheduler.getAllocatedResources());
    }

    @Test
    public void testAllocateResourcesMultipleProcesses() {
        scheduler.addProcess("Process1", 2);
        scheduler.addProcess("Process2", 3);
        scheduler.addProcess("Process3", 5);
        scheduler.allocateResources(100);
        List<String> expected = List.of("Process1: 20", "Process2: 30", "Process3: 50");
        assertEquals(expected, scheduler.getAllocatedResources());
    }

    @Test
    public void testAllocateResourcesZeroWeightProcess() {
        scheduler.addProcess("Process1", 0);
        scheduler.addProcess("Process2", 5);
        scheduler.allocateResources(100);
        List<String> expected = List.of("Process1: 0", "Process2: 100");
        assertEquals(expected, scheduler.getAllocatedResources());
    }

    @Test
    public void testAllocateResourcesEqualWeights() {
        scheduler.addProcess("Process1", 1);
        scheduler.addProcess("Process2", 1);
        scheduler.allocateResources(100);
        List<String> expected = List.of("Process1: 50", "Process2: 50");
        assertEquals(expected, scheduler.getAllocatedResources());
    }
}
