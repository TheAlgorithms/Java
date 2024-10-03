package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NonPreemptivePrioritySchedulingTest {

    @Test
    public void testScheduleProcesses() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 10, 2), new NonPreemptivePriorityScheduling.Process(2, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 8, 3)};

        NonPreemptivePriorityScheduling.Process[] expectedOrder = {new NonPreemptivePriorityScheduling.Process(2, 5, 1), new NonPreemptivePriorityScheduling.Process(1, 10, 2), new NonPreemptivePriorityScheduling.Process(3, 8, 3)};

        NonPreemptivePriorityScheduling.Process[] actualOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        assertArrayEquals(expectedOrder, actualOrder, "Processes should be scheduled by priority.");
    }

    @Test
    public void testCalculateAverageWaitingTime() {
        NonPreemptivePriorityScheduling.Process[] processes = {new NonPreemptivePriorityScheduling.Process(1, 10, 2), new NonPreemptivePriorityScheduling.Process(2, 5, 1), new NonPreemptivePriorityScheduling.Process(3, 8, 3)};

        NonPreemptivePriorityScheduling.Process[] executionOrder = NonPreemptivePriorityScheduling.scheduleProcesses(processes);

        double expectedAvgWaitingTime = 6.666666666666667;
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
}
