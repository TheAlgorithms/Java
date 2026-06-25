package com.thealgorithms.others;
import java.util.Scanner;

public class BankersAlgorithmDemo {
    private BankersAlgorithmDemo() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter total number of processes: ");
            int numProcesses = scanner.nextInt();

            System.out.print("Enter total number of resources: ");
            int numResources = scanner.nextInt();

            // Available resources
            int[] available = new int[numResources];
            System.out.println("Enter available instances of each resource:");
            for (int j = 0; j < numResources; j++) {
                System.out.print("Resource " + j + ": ");
                available[j] = scanner.nextInt();
            }

            // Maximum matrix
            int[][] max = new int[numProcesses][numResources];
            System.out.println("Enter maximum demand matrix:");
            for (int i = 0; i < numProcesses; i++) {
                System.out.println("Process " + i + ":");
                for (int j = 0; j < numResources; j++) {
                    System.out.print("  Max of resource " + j + ": ");
                    max[i][j] = scanner.nextInt();
                }
            }

            // Allocation matrix
            int[][] allocation = new int[numProcesses][numResources];
            System.out.println("Enter allocation matrix:");
            for (int i = 0; i < numProcesses; i++) {
                System.out.println("Process " + i + ":");
                for (int j = 0; j < numResources; j++) {
                    System.out.print("  Allocated of resource " + j + ": ");
                    allocation[i][j] = scanner.nextInt();
                }
            }

            // Run algorithm and display result
            SafetyResult result = BankersAlgorithm.isSafe(available, max, allocation);
            System.out.println(result.getMessage());
            if (result.isSafe()) {
                System.out.print("Safe sequence: ");
                for (int p : result.getSequence()) {
                    System.out.print("P" + p + " ");
                }
                System.out.println();
            }
        }
    }
}
