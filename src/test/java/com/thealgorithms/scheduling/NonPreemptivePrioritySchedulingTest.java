package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NonPreemptivePrioritySchedulingTest {

    @Test
    public void testCalculateAverageWaitingTime() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 0, 10, 2), // id, arrivalTime, burstTime, priority
            new NonPreemptivePriorityScheduling.Process(2, 0, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 0, 8, 3)};
        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        double expectedAvgWaitingTime = (0 + 5 + 15) / 3.0; // Waiting times: 0 for P2, 5 for P1, 15 for P3
        double actualAvgWaitingTime = NonPreemptivePriorityScheduling.calculateAverageWaitingTime(processes, executionOrder);

        assertEquals(expectedAvgWaitingTime, actualAvgWaitingTime, 0.01, "Average waiting time should be calculated correctly.");
    }

    @Test
    public void testCalculateAverageTurnaroundTime() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 0, 10, 2), // id, arrivalTime, burstTime, priority
            new NonPreemptivePriorityScheduling.Process(2, 0, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 0, 8, 3)};

        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        double expectedAvgTurnaroundTime = (5 + 15 + 23) / 3.0; // Turnaround times: 5 for P2, 15 for P1, 23 for P3
        double actualAvgTurnaroundTime = NonPreemptivePriorityScheduling.calculateAverageTurnaroundTime(processes, executionOrder);

        assertEquals(expectedAvgTurnaroundTime, actualAvgTurnaroundTime, 0.01, "Average turnaround time should be calculated correctly.");
    }

    @Test
    public void testStartTimeIsCorrect() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 0, 10, 2), // id, arrivalTime, burstTime, priority
            new NonPreemptivePriorityScheduling.Process(2, 0, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 0, 8, 3)};
        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        // Check that the start time for each process is correctly set
        assertEquals(0, executionOrder[0].startTime, "First process (P2) should start at time 0."); // Process 2 has the highest priority
        assertEquals(5, executionOrder[1].startTime, "Second process (P1) should start at time 5.");
        assertEquals(15, executionOrder[2].startTime, "Third process (P3) should start at time 15.");
    }

    @Test
    public void testWithDelayedArrivalTimes() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 0, 4, 1), // id, arrivalTime, burstTime, priority
            new NonPreemptivePriorityScheduling.Process(2, 2, 3, 2), new NonPreemptivePriorityScheduling.Process(3, 4, 2, 3)};
        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        // Test the start times considering delayed arrivals
        assertEquals(0, executionOrder[0].startTime, "First process (P1) should start at time 0.");
        assertEquals(4, executionOrder[1].startTime, "Second process (P2) should start at time 4."); // After P1 finishes
        assertEquals(7, executionOrder[2].startTime, "Third process (P3) should start at time 7."); // After P2 finishes
    }

    @Test
    public void testWithGapsInArrivals() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 0, 6, 2), // id, arrivalTime, burstTime, priority
            new NonPreemptivePriorityScheduling.Process(2, 8, 4, 1), new NonPreemptivePriorityScheduling.Process(3, 12, 5, 3)};

        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        // Test the start times for processes with gaps in arrival times
        assertEquals(0, executionOrder[0].startTime, "First process (P1) should start at time 0.");
        assertEquals(8, executionOrder[1].startTime, "Second process (P2) should start at time 8."); // After P1 finishes, arrives at 8
        assertEquals(12, executionOrder[2].startTime, "Third process (P3) should start at time 12."); // After P2 finishes, arrives at 12
    }
}
