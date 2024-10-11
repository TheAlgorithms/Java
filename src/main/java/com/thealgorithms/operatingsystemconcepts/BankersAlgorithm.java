package com.thealgorithms.operatingsystemconcepts;


import java.util.Arrays;
import java.util.Scanner;

public class BankersAlgorithm {
    private int processes; // Number of processes
    private int resources; // Number of resources
    private int[] available; // Available instances of each resource
    private int[][] max; // Maximum R of each resource
    private int[][] allot; // Allocation R of each resource
    private int[][] need; // Remaining R needed by each process

    public BankersAlgorithm(int processes, int resources) {
        this.processes = processes;
        this.resources = resources;
        available = new int[resources];
        max = new int[processes][resources];
        allot = new int[processes][resources];
        need = new int[processes][resources];
    }

    // Function to calculate the need matrix
    private void calculateNeed() {
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = max[i][j] - allot[i][j];
            }
        }
    }

    // Function to find if the system is in a safe state
    private boolean isSafe() {
        boolean[] finish = new boolean[processes];
        int[] safeSequence = new int[processes];
        int count = 0;

        int[] work = Arrays.copyOf(available, resources);

        while (count < processes) {
            boolean found = false;

            for (int p = 0; p < processes; p++) {
                if (!finish[p]) {
                    int j;
                    for (j = 0; j < resources; j++) {
                        if (need[p][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == resources) {
                        for (int k = 0; k < resources; k++) {
                            work[k] += allot[p][k];
                        }
                        safeSequence[count++] = p;
                        finish[p] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is not in a safe state.");
                return false;
            }
        }

        System.out.println("System is in a safe state.");
        System.out.print("Safe sequence is: ");
        for (int i = 0; i < processes; i++) {
            System.out.print(safeSequence[i] + " ");
        }
        System.out.println();
        return true;
    }

    // Function to request resources
    public void requestResources(int processNum, int[] request) {
        // Check if request is less than need
        for (int i = 0; i < resources; i++) {
            if (request[i] > need[processNum][i]) {
                System.out.println("Error: Process has exceeded its maximum claim.");
                return;
            }
        }

        // Check if request is less than available
        for (int i = 0; i < resources; i++) {
            if (request[i] > available[i]) {
                System.out.println("Process is waiting.");
                return;
            }
        }

        // Pretend to allocate resources
        for (int i = 0; i < resources; i++) {
            available[i] -= request[i];
            allot[processNum][i] += request[i];
            need[processNum][i] -= request[i];
        }

        // Check system state
        if (isSafe()) {
            System.out.println("Resources allocated successfully.");
        } else {
            // Rollback
            for (int i = 0; i < resources; i++) {
                available[i] += request[i];
                allot[processNum][i] -= request[i];
                need[processNum][i] += request[i];
            }
            System.out.println("Resources allocation leads to unsafe state, request denied.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int processes = scanner.nextInt();
        System.out.print("Enter number of resources: ");
        int resources = scanner.nextInt();

        BankersAlgorithm bankersAlgorithm = new BankersAlgorithm(processes, resources);

        System.out.println("Enter maximum resource matrix:");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                bankersAlgorithm.max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter allocation matrix:");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                bankersAlgorithm.allot[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter available resources:");
        for (int i = 0; i < resources; i++) {
            bankersAlgorithm.available[i] = scanner.nextInt();
        }

        bankersAlgorithm.calculateNeed();

        System.out.print("Request resources for process (0 to " + (processes - 1) + "): ");
        int processNum = scanner.nextInt();
        System.out.println("Enter request:");
        int[] request = new int[resources];
        for (int i = 0; i < resources; i++) {
            request[i] = scanner.nextInt();
        }

        bankersAlgorithm.requestResources(processNum, request);

        scanner.close();
    }
}
