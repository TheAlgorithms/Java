package com.thealgorithms.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Java program to find the minimum number of dice throws required to reach
// the last cell from the first cell of a given snake and ladder board.
public final class SnakeAndLadder {

    // Private constructor to prevent instantiation
    private SnakeAndLadder() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static class QueueEntry {
        // Cell number
        int cell;
        // Distance of the cell from source cell
        int distance;
    }

    public static int getMinimumDiceThrows(int numberOfCells, int[] graph) {
        // Array to keep track of visited cells: 0 means unvisited, 1 means visited
        int[] visited = new int[numberOfCells];
        for (int i = 0; i < numberOfCells; i++) {
            visited[i] = 0;
        }

        // Inserting the source node in queue for BFS
        Queue<QueueEntry> queue = new LinkedList<>();
        QueueEntry startingQueueEntry = new QueueEntry();
        startingQueueEntry.cell = 0;
        startingQueueEntry.distance = 0;
        visited[0] = 1;
        queue.add(startingQueueEntry);

        // Using BFS
        QueueEntry currentQueueEntry;
        while (!queue.isEmpty()) {
            currentQueueEntry = queue.remove();

            // Our goal is to reach the ending cell
            if (currentQueueEntry.cell == numberOfCells - 1) {
                return currentQueueEntry.distance;
            }

            for (int i = currentQueueEntry.cell + 1; i <= currentQueueEntry.cell + 6 && i < numberOfCells; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    QueueEntry queueEntry = new QueueEntry();
                    queueEntry.distance = currentQueueEntry.distance + 1;

                    // Check for snakes or ladders
                    queueEntry.cell = (graph[i] == -1) ? i : graph[i];
                    queue.add(queueEntry);
                }
            }
        }
        return -1; // Return -1 if the last cell is not reachable
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of cells on the board: ");
        int numberOfCells = scanner.nextInt();

        // Representation of the board as a graph
        int[] graph = new int[numberOfCells];
        for (int i = 0; i < numberOfCells; i++) {
            graph[i] = -1;
        }

        System.out.print("Enter the number of snakes and ladders: ");
        int n = scanner.nextInt();
        System.out.println("Enter the starting cell and ending cell of each snake or ladder:");
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            // Assign the ending position of the snake or ladder
            graph[start - 1] = end - 1;
        }

        int result = getMinimumDiceThrows(numberOfCells, graph);
        if (result != -1) {
            System.out.println("Minimum number of dice throws required to end the game is " + result);
        } else {
            System.out.println("The last cell is not reachable.");
        }

        scanner.close();
    }
}
