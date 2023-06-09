package com.thealgorithms.others;

/**
 * This file contains an implementation of BANKER'S ALGORITM Wikipedia:
 * https://en.wikipedia.org/wiki/Banker%27s_algorithm
 *
 * The algorithm for finding out whether or not a system is in a safe state can
 * be described as follows: 1. Let Work and Finish be vectors of length ‘m’ and
 * ‘n’ respectively. Initialize: Work= Available Finish [i]=false; for
 * i=1,2,……,n 2. Find an i such that both a) Finish [i]=false b) Need_i<=work
 *
 * if no such i exists goto step (4) 3. Work=Work + Allocation_i Finish[i]= true
 * goto step(2) 4. If Finish[i]=true for all i, then the system is in safe
 * state.
 *
 * Time Complexity: O(n*n*m) Space Complexity: O(n*m) where n = number of
 * processes and m = number of resources.
 *
 * @author AMRITESH ANAND (https://github.com/amritesh19)
 */
import java.util.Scanner;

public class BankersAlgorithm {

    /**
     * This method finds the need of each process
     */
    static void calculateNeed(int[][] needArray, int[][] maxArray, int[][] allocationArray, int totalProcess, int totalResources) {
        for (int i = 0; i < totalProcess; i++) {
            for (int j = 0; j < totalResources; j++) {
                needArray[i][j] = maxArray[i][j] - allocationArray[i][j];
            }
        }
    }

    /**
     * This method find the system is in safe state or not
     *
     * @param processes[] int array of processes (0...n-1), size = n
     * @param availableArray[] int array of number of instances of each
     * resource, size = m
     * @param maxArray[][] int matrix(2-D array) of maximum demand of each
     * process in a system, size = n*m
     * @param allocationArray[][] int matrix(2-D array) of the number of
     * resources of each type currently allocated to each process, size = n*m
     * @param totalProcess number of total processes, n
     * @param totalResources number of total resources, m
     *
     * @return boolean if the system is in safe state or not
     */
    static boolean checkSafeSystem(int[] processes, int[] availableArray, int[][] maxArray, int[][] allocationArray, int totalProcess, int totalResources) {
        int[][] needArray = new int[totalProcess][totalResources];

        calculateNeed(needArray, maxArray, allocationArray, totalProcess, totalResources);

        boolean[] finishProcesses = new boolean[totalProcess];

        int[] safeSequenceArray = new int[totalProcess];

        int[] workArray = new int[totalResources];

        for (int i = 0; i < totalResources; i++) {
            workArray[i] = availableArray[i];
        }

        int count = 0;

        // While all processes are not finished or system is not in safe state.
        while (count < totalProcess) {
            boolean foundSafeSystem = false;
            for (int m = 0; m < totalProcess; m++) {
                if (!finishProcesses[m]) {
                    int j;

                    for (j = 0; j < totalResources; j++) {
                        if (needArray[m][j] > workArray[j]) {
                            break;
                        }
                    }

                    if (j == totalResources) {
                        for (int k = 0; k < totalResources; k++) {
                            workArray[k] += allocationArray[m][k];
                        }

                        safeSequenceArray[count++] = m;

                        finishProcesses[m] = true;

                        foundSafeSystem = true;
                    }
                }
            }

            // If we could not find a next process in safe sequence.
            if (!foundSafeSystem) {
                System.out.print("The system is not in the safe state because lack of resources");
                return false;
            }
        }

        System.out.print("The system is in safe sequence and the sequence is as follows: ");
        for (int i = 0; i < totalProcess; i++) {
            System.out.print("P" + safeSequenceArray[i] + " ");
        }

        return true;
    }

    /**
     * This is main method of Banker's Algorithm
     */
    public static void main(String[] args) {
        int numberOfProcesses, numberOfResources;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total number of processes");
        numberOfProcesses = sc.nextInt();

        System.out.println("Enter total number of resources");
        numberOfResources = sc.nextInt();

        int[] processes = new int[numberOfProcesses];
        for (int i = 0; i < numberOfProcesses; i++) {
            processes[i] = i;
        }

        System.out.println("--Enter the availability of--");

        int[] availableArray = new int[numberOfResources];
        for (int i = 0; i < numberOfResources; i++) {
            System.out.println("resource " + i + ": ");
            availableArray[i] = sc.nextInt();
        }

        System.out.println("--Enter the maximum matrix--");

        int[][] maxArray = new int[numberOfProcesses][numberOfResources];
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println("For process " + i + ": ");
            for (int j = 0; j < numberOfResources; j++) {
                System.out.println("Enter the maximum instances of resource " + j);
                maxArray[i][j] = sc.nextInt();
            }
        }

        System.out.println("--Enter the allocation matrix--");

        int[][] allocationArray = new int[numberOfProcesses][numberOfResources];
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println("For process " + i + ": ");
            for (int j = 0; j < numberOfResources; j++) {
                System.out.println("Allocated instances of resource " + j);
                allocationArray[i][j] = sc.nextInt();
            }
        }

        checkSafeSystem(processes, availableArray, maxArray, allocationArray, numberOfProcesses, numberOfResources);

        sc.close();
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
