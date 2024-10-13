package com.thealgorithms.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// Java program to find minimum number of dice
// throws required to reach last cell from first
// cell of a given snake and ladder board
public class SnakeAndLadder {
    public static class QueueEntry {
        //cell number
        int cell;
        //distance of the cell from source cell
        int distance;
    }

    public static int getMinimumDiceThrows(int numberOfCells, int[] graph) {
        //Array to keep track of which cells we have visited, 0 means unvisited, 1 means visited
        int[] visited = new int[numberOfCells];
        for (int i = 0; i < numberOfCells; i++) {
            visited[i] = 0;
        }
        //Inserting the source node in queue for BFS
        Queue<QueueEntry> queue = new LinkedList<>();
        QueueEntry startingQueueEntry = new QueueEntry();
        startingQueueEntry.cell = 0;
        startingQueueEntry.distance = 0;
        visited[0] = 1;
        queue.add(startingQueueEntry);

        //Using BFS
        QueueEntry currentQueueEntry = null;
        while (!queue.isEmpty()) {
            currentQueueEntry = queue.remove();
            //Our goal is to reach the ending cell, so we terminate the loop when it's reached
            if (currentQueueEntry.cell == numberOfCells - 1) {
                break;
            }
            for (int i = currentQueueEntry.cell + 1; i <= currentQueueEntry.cell + 6 && i < numberOfCells; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    QueueEntry queueEntry = new QueueEntry();
                    queueEntry.distance = currentQueueEntry.distance + 1;

                    if (graph[i] == -1) {
                        queueEntry.cell = i;
                    } else {
                        queueEntry.cell = graph[i];
                    }
                    queue.add(queueEntry);
                }
            }
        }
        return currentQueueEntry.distance;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of cells in board");
        int numberOfCells = in.nextInt();

        //Representation of the board as graph
        //If there are no snakes or ladders starting from a cell, we mark it as -1
        //If there is a snake or ladder, we will assign the ending position of the snake and ladder
        int[] graph = new int[numberOfCells];
        for (int i = 0; i < numberOfCells; i++) {
            graph[i] = -1;
        }

        System.out.println("Enter number of snakes and ladders");
        int n = in.nextInt();
        System.out.println("Enter starting cell and ending cell of snake or ladder");
        int[] positions = new int[2 * n];
        for (int i = 0; i < n; i++) {
            int startingCell = in.nextInt();
            int endingCell = in.nextInt();
            positions[2 * i] = startingCell;
            positions[2 * i + 1] = endingCell;
            //If there is a snake or ladder, we assign the ending position of the snake and ladder
            graph[startingCell - 1] = endingCell - 1;
        }
        System.out.println("Minimum number of dice throws required to end the game is " + getMinimumDiceThrows(numberOfCells, graph));

    }
}
