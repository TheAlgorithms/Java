package com.thealgorithms.misc;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

/**
 * TestCase of The Four Sum Problem.
 * @author md-shamim-ahmad
 */
public class FourSumProblemTest {

    // multiple solution
    @Test
    public void fourSum1() {
        int value[] = {1, 0, -1, 0, -2, 2};
        final int target = 0;
        List<List<Integer>> solution = new ArrayList<>(Arrays.asList(Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2), Arrays.asList(-1, 0, 0, 1)));
        List<List<Integer>> result = FourSumProblem.fourSum(value, target);
        assertEquals(solution, result);
    }

    // one solution
    @Test
    public void fourSum2() {
        int value[] = {2, 2, 2, 2, 2};
        final int target = 8;
        List<List<Integer>> solution = new ArrayList<>(Arrays.asList(Arrays.asList(2, 2, 2, 2)));
        List<List<Integer>> result = FourSumProblem.fourSum(value, target);
        assertEquals(solution, result);
    }

    // one solution
    @Test
    public void fourSum3() {
        int value[] = {-2, 5, 0, 6};
        final int target = 9;
        List<List<Integer>> solution = new ArrayList<>(Arrays.asList(Arrays.asList(-2, 0, 5, 6)));
        List<List<Integer>> result = FourSumProblem.fourSum(value, target);
        assertEquals(solution, result);
    }

    // no solution
    @Test
    public void fourSum4() {
        int value[] = {-2, 5, 0, 6};
        final int target = 3;
        List<List<Integer>> solution = new ArrayList<>();
        List<List<Integer>> result = FourSumProblem.fourSum(value, target);
        assertEquals(solution, result);
    }

    // no solution
    @Test
    public void fourSum5() {
        int value[] = {-2, 5, 0, 6};
        final int target = 3;
        List<List<Integer>> solution = new ArrayList<>();
        List<List<Integer>> result = FourSumProblem.fourSum(value, target);
        assertEquals(solution, result);
    }

    // time limit check
    @Test
    public void fourSum6() {
        int value[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        final int target = 8;
        List<List<Integer>> solution = new ArrayList<>(Arrays.asList(Arrays.asList(2, 2, 2, 2)));
        List<List<Integer>> result = FourSumProblem.fourSum(value, target);
        assertEquals(solution, result);
    }
}
