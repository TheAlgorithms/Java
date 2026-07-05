package com.thealgorithms.others;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This file contains an implementation of BANKER'S ALGORITHM Wikipedia:
 * <a href="https://en.wikipedia.org/wiki/Banker%27s_algorithm">...</a>
 * <p>
 * The algorithm for finding out whether or not a system is in a safe state can
 * be described as follows: 1. Let Work and Finish be vectors of length ‘m’ and
 * ‘n’ respectively. Initialize: Work= Available Finish [i]=false; for
 * i=1,2,……,n 2. Find an i such that both a) Finish [i]=false b) Need_i<=work
 * <p>
 * if no such i exists goto step (4) 3. Work=Work + Allocation_i Finish[i]= true
 * goto step(2) 4. If Finish[i]=true for all i, then the system is in safe
 * state.
 * <p>
 * Time Complexity: O(n*n*m) Space Complexity: O(n*m) where n = number of
 * processes and m = number of resources.
 *
 * @author AMRI<a href="TESH">ANAND (https://github.co</a>m/amritesh19)
 */
public final class BankersAlgorithm {
    private BankersAlgorithm() {
    }

    /**
     * Checks whether the system is in a safe state.
     *
     * @param available  available instances of each resource
     * @param max        maximum demand of each process
     * @param allocation currently allocated resources to each process
     * @return SafetyResult indicating safe state and the safe sequence (if any)
     * @throws IllegalArgumentException if input is invalid
     */
    public static SafetyResult isSafe(int[] available, int[][] max, int[][] allocation) {
        validateInput(available, max, allocation);
        int totalProcesses = allocation.length;
        int totalResources = available.length;

        int[][] need = calculateNeed(max, allocation);

        boolean[] finished = new boolean[totalProcesses];
        int[] work = Arrays.copyOf(available, totalResources);
        List<Integer> safeSequence = new ArrayList<>();
        // While not all processes are finished, look for a process whose needs can be satisfied
        while (safeSequence.size() < totalProcesses) {
            boolean found = false;
            for (int p = 0; p < totalProcesses; p++) {
                if (!finished[p]) {
                    boolean canAllocate = true;
                    for (int r = 0; r < totalResources; r++) {
                        if (need[p][r] > work[r]) {
                            canAllocate = false;
                            break;
                        }
                    }
                    if (canAllocate) {
                        // Process can finish; release its allocated resources
                        for (int r = 0; r < totalResources; r++) {
                            work[r] += allocation[p][r];
                        }
                        finished[p] = true;
                        safeSequence.add(p);
                        found = true;
                    }
                }
            }
            if (!found) {
                return new SafetyResult(false, List.of(),
                        "The system is not in a safe state because of lack of resources.");
            }
        }
        return new SafetyResult(true, safeSequence, "The system is in a safe state.");
    }

    private static int[][] calculateNeed(int[][] max, int[][] allocation) {
        int processes = max.length;
        int resources = max[0].length;
        int[][] need = new int[processes][resources];
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        return need;
    }

    private static void validateInput(int[] available, int[][] max, int[][] allocation) {
        if (available == null || max == null || allocation == null) {
            throw new IllegalArgumentException("Arguments must not be null.");
        }
        int processes = allocation.length;
        if (max.length != processes) {
            throw new IllegalArgumentException("max and allocation must have the same number of processes.");
        }
        if (processes == 0) {
            throw new IllegalArgumentException("At least one process is required.");
        }
        int resources = available.length;
        if (resources == 0) {
            throw new IllegalArgumentException("At least one resource is required.");
        }
        for (int i = 0; i < processes; i++) {
            if (max[i].length != resources || allocation[i].length != resources) {
                throw new IllegalArgumentException("Resource count mismatch in max/allocation for process " + i);
            }
            for (int j = 0; j < resources; j++) {
                if (max[i][j] < 0 || allocation[i][j] < 0) {
                    throw new IllegalArgumentException("Negative values not allowed.");
                }
                if (allocation[i][j] > max[i][j]) {
                    throw new IllegalArgumentException(
                            "Process " + i + " has allocated more than maximum for resource " + j);
                }
            }
        }
        for(int j = 0; j < resources; j++) {
            if (available[j] < 0) {
                throw new IllegalArgumentException("Available resources cannot be negative.");
            }
        }
    }
}
/*
    Example:
    n = 5
    m = 3

    Process     Allocation      Max       Available
                0   1   2    0   1   2    0   1   2

        0       0   1   0    7   5   3    3   3   2
        1       2   0   0    3   2   2
        2       3   0   2    9   0   2
        3       2   1   1    2   2   2
        4       0   0   2    4   3   3

    Result: The system is in safe sequence and the sequence is as follows: P1, P3, P4, P0, P2
 */
