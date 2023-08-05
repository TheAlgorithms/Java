package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author georgioct@csd.auth.gr
 */
public class OptimalJobSchedulingTest {

    @Test
    public void testOptimalJobScheduling1() {

        int numberProcesses = 5;
        int numberMachines = 4;

        int[][] Run = {{5, 1, 3, 2}, {4, 2, 1, 3}, {1, 5, 2, 1}, {2, 3, 4, 2}, {1, 1, 3, 1}};

        int[][] Transfer = {{0, 1, 2, 4}, {1, 0, 2, 3}, {2, 2, 0, 1}, {4, 3, 1, 0}};

        OptimalJobScheduling opt = new OptimalJobScheduling(numberProcesses, numberMachines, Run, Transfer);

        opt.execute();

        int[][] costs = {{5, 1, 3, 2}, {6, 3, 4, 5}, {5, 8, 6, 6}, {7, 9, 10, 8}, {8, 9, 12, 9}};

        for (int i = 0; i < numberProcesses; i++) {

            for (int j = 0; j < numberMachines; j++) {

                assertEquals(costs[i][j], opt.getCost(i, j));
            }
        }
    }

    @Test
    public void testOptimalJobScheduling2() {

        int numberProcesses = 3;
        int numberMachines = 3;

        int[][] Run = {{5, 1, 3}, {4, 2, 1}, {1, 5, 2}};

        int[][] Transfer = {{0, 1, 2}, {1, 0, 2}, {2, 2, 0}};

        OptimalJobScheduling opt = new OptimalJobScheduling(numberProcesses, numberMachines, Run, Transfer);

        opt.execute();

        int[][] costs = {{5, 1, 3}, {6, 3, 4}, {5, 8, 6}};

        for (int i = 0; i < numberProcesses; i++) {

            for (int j = 0; j < numberMachines; j++) {

                assertEquals(costs[i][j], opt.getCost(i, j));
            }
        }
    }

    @Test
    public void testOptimalJobScheduling3() {

        int numberProcesses = 6;
        int numberMachines = 4;

        int[][] Run = {
            {5, 1, 3, 2},
            {4, 2, 1, 1},
            {1, 5, 2, 6},
            {1, 1, 2, 3},
            {2, 1, 4, 6},
            {3, 2, 2, 3},
        };

        int[][] Transfer = {
            {0, 1, 2, 1},
            {1, 0, 2, 3},
            {2, 2, 0, 2},
            {1, 3, 2, 0},
        };

        OptimalJobScheduling opt = new OptimalJobScheduling(numberProcesses, numberMachines, Run, Transfer);

        opt.execute();

        int[][] costs = {{5, 1, 3, 2}, {6, 3, 4, 3}, {5, 8, 6, 9}, {6, 7, 8, 9}, {8, 8, 12, 13}, {11, 10, 12, 12}};

        for (int i = 0; i < numberProcesses; i++) {

            for (int j = 0; j < numberMachines; j++) {

                assertEquals(costs[i][j], opt.getCost(i, j));
            }
        }
    }
}