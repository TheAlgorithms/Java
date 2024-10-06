package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NonPreemptivePrioritySchedulingTest {

    @Test
    public void testCalculateAverageWaitingTime() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 10, 2), new NonPreemptivePriorityScheduling.Process(2, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 8, 3)};
        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        double expectedAvgWaitingTime = 6.666666666666667; // (0 + 10 + 5) / 3
        double actualAvgWaitingTime = NonPreemptivePriorityScheduling.calculateAverageWaitingTime(processes, executionOrder);

        assertEquals(expectedAvgWaitingTime, actualAvgWaitingTime, 0.01, "Average waiting time should be calculated correctly.");
    }

    @Test
    public void testCalculateAverageTurnaroundTime() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 10, 2), new NonPreemptivePriorityScheduling.Process(2, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 8, 3)};
        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        double expectedAvgTurnaroundTime = (5 + 15 + 23) / 3.0; // (5 + (5 + 10) + (5 + 10 + 8)) / 3
        double actualAvgTurnaroundTime = NonPreemptivePriorityScheduling.calculateAverageTurnaroundTime(processes, executionOrder);

        assertEquals(expectedAvgTurnaroundTime, actualAvgTurnaroundTime, 0.01, "Average turnaround time should be calculated correctly.");
    }

    @Test
    public void testStartTimeIsCorrect() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 10, 2), new NonPreemptivePriorityScheduling.Process(2, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 8, 3)};
        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        // Check that the start time for each process is correctly set
        assertEquals(0, executionOrder[0].startTime, "First process should start at time 0.");
        assertEquals(5, executionOrder[1].startTime, "Second process should start after the first process.");
        assertEquals(15, executionOrder[2].startTime, "Third process should start after the second process.");
    }
}
