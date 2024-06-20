package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test Cases of Preemptive Priority Scheduling Algorithm
 * @author [Bama Charan Chhandogi](https://www.github.com/BamaCharanChhandogi)
 */
class PreemptivePrioritySchedulingTest {

    @Test
    void testPreemptivePriorityScheduling() {
        // Arrange
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 0, 5, 10));
        processes.add(new Process("P2", 1, 4, 20));
        processes.add(new Process("P3", 2, 2, 30));
        processes.add(new Process("P4", 4, 1, 40));

        List<String> expectedGanttChart = Arrays.asList("P1", "P2", "P3", "P3", "P4", "P2", "P2", "P2", "P1", "P1", "P1", "P1");

        // Act
        List<String> actualGanttChart = PreemptivePriorityScheduling.preemptivePriorityScheduling(processes);

        // Assert
        assertEquals(expectedGanttChart, actualGanttChart);
    }
}
