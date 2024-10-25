package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public final class AssignmentUsingBitmaskTest {

    @Test
    public void testCountNoOfWays() {
        int totalTasks = 5;

        List<List<Integer>> taskPerformed = Arrays.asList(Arrays.asList(1, 3, 4), Arrays.asList(1, 2, 5), Arrays.asList(3, 4));

        AssignmentUsingBitmask assignment = new AssignmentUsingBitmask(taskPerformed, totalTasks);
        int ways = assignment.countNoOfWays();
        assertEquals(10, ways);
    }

    @Test
    public void testNoPossibleAssignments() {
        int totalTasks = 3;

        List<List<Integer>> taskPerformed = Arrays.asList(Arrays.asList(2), Arrays.asList(3));

        AssignmentUsingBitmask assignment = new AssignmentUsingBitmask(taskPerformed, totalTasks);
        int ways = assignment.countNoOfWays();
        assertEquals(1, ways);
    }

    @Test
    public void testSinglePersonMultipleTasks() {
        int totalTasks = 3;

        List<List<Integer>> taskPerformed = Arrays.asList(Arrays.asList(1, 2, 3));

        AssignmentUsingBitmask assignment = new AssignmentUsingBitmask(taskPerformed, totalTasks);
        int ways = assignment.countNoOfWays();
        assertEquals(3, ways);
    }

    @Test
    public void testMultiplePeopleSingleTask() {
        int totalTasks = 1;

        List<List<Integer>> taskPerformed = Arrays.asList(Arrays.asList(1), Arrays.asList(1));

        AssignmentUsingBitmask assignment = new AssignmentUsingBitmask(taskPerformed, totalTasks);
        int ways = assignment.countNoOfWays();
        assertEquals(0, ways);
    }
}
