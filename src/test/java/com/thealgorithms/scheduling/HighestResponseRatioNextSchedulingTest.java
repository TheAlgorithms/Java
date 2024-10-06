package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class HighestResponseRatioNextSchedulingTest {

    @Test
    public void testCalculateTurnAroundTime() {
        String[] processNames = {"A", "B", "C"};
        int[] arrivalTimes = {0, 2, 4};
        int[] burstTimes = {3, 1, 2};
        int noOfProcesses = 3;

        int[] expectedTurnAroundTimes = {3, 2, 2};
        int[] actualTurnAroundTimes = HighestResponseRatioNextScheduling.calculateTurnAroundTime(processNames, arrivalTimes, burstTimes, noOfProcesses);

        assertArrayEquals(expectedTurnAroundTimes, actualTurnAroundTimes, "Turn Around Times do not match");
    }

    @Test
    public void testCalculateWaitingTime() {
        int[] turnAroundTimes = {3, 1, 5};
        int[] burstTimes = {3, 1, 2};

        int[] expectedWaitingTimes = {0, 0, 3};
        int[] actualWaitingTimes = HighestResponseRatioNextScheduling.calculateWaitingTime(turnAroundTimes, burstTimes);

        assertArrayEquals(expectedWaitingTimes, actualWaitingTimes, "Waiting Times do not match");
    }

    @Test
    public void testCompleteSchedulingScenario() {
        String[] processNames = {"A", "B", "C"};
        int[] arrivalTimes = {0, 1, 2};
        int[] burstTimes = {5, 2, 1};

        int[] expectedTurnAroundTimes = {5, 7, 4};
        int[] turnAroundTimes = HighestResponseRatioNextScheduling.calculateTurnAroundTime(processNames, arrivalTimes, burstTimes, processNames.length);
        assertArrayEquals(expectedTurnAroundTimes, turnAroundTimes, "Turn Around Times do not match");

        int[] expectedWaitingTimes = {0, 5, 3};
        int[] waitingTimes = HighestResponseRatioNextScheduling.calculateWaitingTime(turnAroundTimes, burstTimes);
        assertArrayEquals(expectedWaitingTimes, waitingTimes, "Waiting Times do not match");
    }
}
