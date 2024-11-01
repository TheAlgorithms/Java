package com.thealgorithms.dynamicprogramming;

import static java.util.Collections.singletonList;
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

        List<List<Integer>> taskPerformed = Arrays.asList(singletonList(2), singletonList(3));

        AssignmentUsingBitmask assignment = new AssignmentUsingBitmask(taskPerformed, totalTasks);
        int ways = assignment.countNoOfWays();
        assertEquals(1, ways);
    }

    @Test
    public void testSinglePersonMultipleTasks() {
        int totalTasks = 3;

        List<List<Integer>> taskPerformed = singletonList(Arrays.asList(1, 2, 3));

        AssignmentUsingBitmask assignment = new AssignmentUsingBitmask(taskPerformed, totalTasks);
        int ways = assignment.countNoOfWays();
        assertEquals(3, ways);
    }

    @Test
    public void testMultiplePeopleSingleTask() {
        int totalTasks = 1;

        List<List<Integer>> taskPerformed = Arrays.asList(singletonList(1), singletonList(1));

        AssignmentUsingBitmask assignment = new AssignmentUsingBitmask(taskPerformed, totalTasks);
        int ways = assignment.countNoOfWays();
        assertEquals(0, ways);
    }
}
