package com.thealgorithms.scheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The HighestResponseRatioNextScheduling class implements
 * the Highest Response Ratio Next (HRRN) scheduling algorithm.
 * HRRN is a non-preemptive scheduling algorithm that
 * selects the process with the highest response ratio for execution.
 * The response ratio is calculated as (waiting time + burst time) / burst time.
 * This algorithm aims to reduce the average waiting time
 * and improve overall system performance by balancing short and long processes.
 */
public final class HighestResponseRatioNextScheduling {
    private HighestResponseRatioNextScheduling() {
    }

    /**
     * Calculates the Turn Around Time (TAT) for each process.
     *
     * @param processNames Array of process names.
     * @param arrivalTimes Array of arrival times corresponding to each process.
     * @param burstTimes   Array of burst times for each process.
     * @param noOfProcesses The number of processes.
     * @return An array of Turn Around Times for each process.
     */
    public static int[] calculateTurnAroundTime(String[] processNames, int[] arrivalTimes, int[] burstTimes, int noOfProcesses) {
        int currentTime = 0;
        int[] turnAroundTime = new int[noOfProcesses];
        boolean[] finishedProcess = new boolean[noOfProcesses];
        int finishedProcessCount = 0;

        // Sort by arrival times using a simple bubble sort for demonstration
        int[] sortedIndices = sortIndicesByArrival(arrivalTimes);
        arrivalTimes = Arrays.copyOf(arrivalTimes, arrivalTimes.length);
        burstTimes = Arrays.copyOf(burstTimes, burstTimes.length);
        processNames = Arrays.copyOf(processNames, processNames.length);

        // Reorder the arrays based on sorted indices
        int[] tempArrival = new int[noOfProcesses];
        int[] tempBurst = new int[noOfProcesses];
        String[] tempProcess = new String[noOfProcesses];

        for (int i = 0; i < noOfProcesses; i++) {
            tempArrival[i] = arrivalTimes[sortedIndices[i]];
            tempBurst[i] = burstTimes[sortedIndices[i]];
            tempProcess[i] = processNames[sortedIndices[i]];
        }

        arrivalTimes = tempArrival;
        burstTimes = tempBurst;
        processNames = tempProcess;

        while (finishedProcessCount < noOfProcesses) {
            currentTime = Math.max(currentTime, arrivalTimes[findNextProcess(arrivalTimes, finishedProcess, currentTime)]);
            int loc = findHighestResponseRatio(processNames, arrivalTimes, burstTimes, finishedProcess, currentTime);

            turnAroundTime[loc] = currentTime + burstTimes[loc] - arrivalTimes[loc];
            currentTime += burstTimes[loc];
            finishedProcess[loc] = true;
            finishedProcessCount++;
        }

        return turnAroundTime;
    }

    /**
     * Calculates the Waiting Time (WT) for each process.
     *
     * @param turnAroundTime The Turn Around Times for each process.
     * @param burstTimes     The burst times for each process.
     * @return An array of Waiting Times for each process.
     */
    public static int[] calculateWaitingTime(int[] turnAroundTime, int[] burstTimes) {
        int[] waitingTime = new int[turnAroundTime.length];
        for (int i = 0; i < turnAroundTime.length; i++) {
            waitingTime[i] = turnAroundTime[i] - burstTimes[i];
        }
        return waitingTime;
    }

    /**
     * Finds the next process to be scheduled based on arrival times and the current time.
     *
     * @param arrivalTimes    Array of arrival times for each process.
     * @param finishedProcess Array indicating whether each process has finished.
     * @param currentTime     The current time in the scheduling process.
     * @return The index of the next process to be scheduled.
     */
    private static int findNextProcess(int[] arrivalTimes, boolean[] finishedProcess, int currentTime) {
        for (int i = 0; i < arrivalTimes.length; i++) {
            if (!finishedProcess[i] && arrivalTimes[i] <= currentTime) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Finds the process with the highest response ratio.
     *
     * @param processNames    Array of process names.
     * @param arrivalTimes    Array of arrival times for each process.
     * @param burstTimes      Array of burst times for each process.
     * @param finishedProcess Array indicating whether each process has finished.
     * @param currentTime     The current time in the scheduling process.
     * @return The index of the process with the highest response ratio.
     */
    private static int findHighestResponseRatio(String[] processNames, int[] arrivalTimes, int[] burstTimes, boolean[] finishedProcess, int currentTime) {
        double maxResponseRatio = -1;
        int loc = -1;

        for (int i = 0; i < processNames.length; i++) {
            if (!finishedProcess[i] && arrivalTimes[i] <= currentTime) {
                double responseRatio = (double) (burstTimes[i] + (currentTime - arrivalTimes[i])) / burstTimes[i];
                if (responseRatio > maxResponseRatio) {
                    maxResponseRatio = responseRatio;
                    loc = i;
                }
            }
        }
        return loc;
    }

    /**
     * Sorts the indices of the arrival times array in ascending order.
     *
     * @param arrivalTimes Array of arrival times for each process.
     * @return An array of indices sorted by the corresponding arrival times.
     */
    private static int[] sortIndicesByArrival(int[] arrivalTimes) {
        Integer[] indices = new Integer[arrivalTimes.length];
        for (int i = 0; i < arrivalTimes.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, Comparator.comparingInt(a -> arrivalTimes[a]));
        return Arrays.stream(indices).mapToInt(Integer::intValue).toArray();
    }
}
