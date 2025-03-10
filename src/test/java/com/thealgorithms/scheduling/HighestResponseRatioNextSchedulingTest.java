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

    @Test
    public void testZeroProcesses() {
        String[] processNames = {};
        int[] arrivalTimes = {};
        int[] burstTimes = {};
        int noOfProcesses = 0;

        int[] expectedTurnAroundTimes = {};
        int[] actualTurnAroundTimes = HighestResponseRatioNextScheduling.calculateTurnAroundTime(processNames, arrivalTimes, burstTimes, noOfProcesses);

        assertArrayEquals(expectedTurnAroundTimes, actualTurnAroundTimes, "Turn Around Times for zero processes should be an empty array");
    }

    @Test
    public void testAllProcessesArriveAtSameTime() {
        String[] processNames = {"A", "B", "C", "D"};
        int[] arrivalTimes = {0, 0, 0, 0};
        int[] burstTimes = {4, 3, 1, 2};
        int noOfProcesses = 4;

        int[] expectedTurnAroundTimes = {4, 10, 5, 7};
        int[] actualTurnAroundTimes = HighestResponseRatioNextScheduling.calculateTurnAroundTime(processNames, arrivalTimes, burstTimes, noOfProcesses);

        assertArrayEquals(expectedTurnAroundTimes, actualTurnAroundTimes, "Turn Around Times for processes arriving at the same time do not match");
    }

    @Test
    public void testProcessesWithZeroBurstTime() {
        String[] processNames = {"A", "B", "C"};
        int[] arrivalTimes = {0, 1, 2};
        int[] burstTimes = {3, 0, 2};
        int noOfProcesses = 3;

        int[] expectedTurnAroundTimes = {3, 2, 3};
        int[] actualTurnAroundTimes = HighestResponseRatioNextScheduling.calculateTurnAroundTime(processNames, arrivalTimes, burstTimes, noOfProcesses);

        assertArrayEquals(expectedTurnAroundTimes, actualTurnAroundTimes, "Turn Around Times for processes with zero burst time do not match");
    }

    @Test
    public void testProcessesWithLargeGapsBetweenArrivals() {
        String[] processNames = {"A", "B", "C"};
        int[] arrivalTimes = {0, 100, 200};
        int[] burstTimes = {10, 10, 10};
        int noOfProcesses = 3;

        int[] expectedTurnAroundTimes = {10, 10, 10};
        int[] actualTurnAroundTimes = HighestResponseRatioNextScheduling.calculateTurnAroundTime(processNames, arrivalTimes, burstTimes, noOfProcesses);

        assertArrayEquals(expectedTurnAroundTimes, actualTurnAroundTimes, "Turn Around Times for processes with large gaps between arrivals do not match");
    }

    @Test
    public void testProcessesWithVeryLargeBurstTimes() {
        String[] processNames = {"A", "B"};
        int[] arrivalTimes = {0, 1};
        int[] burstTimes = {Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2};
        int noOfProcesses = 2;

        int[] expectedTurnAroundTimes = {Integer.MAX_VALUE / 2, Integer.MAX_VALUE - 2};
        int[] actualTurnAroundTimes = HighestResponseRatioNextScheduling.calculateTurnAroundTime(processNames, arrivalTimes, burstTimes, noOfProcesses);

        assertArrayEquals(expectedTurnAroundTimes, actualTurnAroundTimes, "Turn Around Times for processes with very large burst times do not match");
    }
}
