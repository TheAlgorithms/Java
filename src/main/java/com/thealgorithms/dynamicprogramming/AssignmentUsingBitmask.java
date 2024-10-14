package com.thealgorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The AssignmentUsingBitmask class is used to calculate the total number of ways
 * tasks can be distributed among people, given specific constraints on who can perform which tasks.
 * The approach uses bitmasking and dynamic programming to efficiently solve the problem.
 *
 * @author Hardvan
 */
public final class AssignmentUsingBitmask {

    private final int totalTasks;
    private final int[][] dp;
    private final List<List<Integer>> task;
    private final int finalMask;

    /**
     * Constructor for the AssignmentUsingBitmask class.
     *
     * @param taskPerformed a list of lists, where each inner list contains the tasks that a person can perform.
     * @param total        the total number of tasks.
     */
    public AssignmentUsingBitmask(List<List<Integer>> taskPerformed, int total) {
        this.totalTasks = total;
        this.dp = new int[1 << taskPerformed.size()][total + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        this.task = new ArrayList<>(totalTasks + 1);
        for (int i = 0; i <= totalTasks; i++) {
            this.task.add(new ArrayList<>());
        }

        // Final mask to check if all persons are included
        this.finalMask = (1 << taskPerformed.size()) - 1;

        // Fill the task list
        for (int i = 0; i < taskPerformed.size(); i++) {
            for (int j : taskPerformed.get(i)) {
                this.task.get(j).add(i);
            }
        }
    }

    /**
     * Counts the ways to assign tasks until the given task number with the specified mask.
     *
     * @param mask     the bitmask representing the current state of assignments.
     * @param taskNo   the current task number being processed.
     * @return the number of ways to assign tasks.
     */
    private int countWaysUntil(int mask, int taskNo) {
        if (mask == finalMask) {
            return 1;
        }
        if (taskNo > totalTasks) {
            return 0;
        }
        if (dp[mask][taskNo] != -1) {
            return dp[mask][taskNo];
        }

        int totalWays = countWaysUntil(mask, taskNo + 1);

        // Assign tasks to all possible persons
        for (int p : task.get(taskNo)) {
            // If the person is already assigned a task
            if ((mask & (1 << p)) != 0) {
                continue;
            }
            totalWays += countWaysUntil(mask | (1 << p), taskNo + 1);
        }

        dp[mask][taskNo] = totalWays;
        return dp[mask][taskNo];
    }

    /**
     * Counts the total number of ways to distribute tasks among persons.
     *
     * @return the total number of ways to distribute tasks.
     */
    public int countNoOfWays() {
        return countWaysUntil(0, 1);
    }
}
