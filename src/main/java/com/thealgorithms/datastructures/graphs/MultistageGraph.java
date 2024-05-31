package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

public final class MultistageGraph {

    private MultistageGraph() {
    }
    private int k; // number of partitions (k > 2)

    // Adjacency list to store edges between different stages
    public List<List<Integer>> adjacencyList;

    public MultistageGraph(int k) {
        this.k = k;
        this.adjacencyList = new ArrayList<>();

        // Initialize the adjacency list with empty lists for each stage
        for (int i = 0; i < k; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int fromStage, int toStage) throws IllegalArgumentException {
        if (!isValidStageNumber(fromStage)) {
            throw new IllegalArgumentException("Invalid stage number: " + fromStage);
        }

        if (!isValidStageNumber(toStage)) {
            throw new IllegalArgumentException("Invalid stage number: " + toStage);
        }

        // Check if the edge exists between the given stages, and add it if not
        if (adjacencyList.get(fromStage - 1).contains(toStage)) {
            System.out.println("Edge already exists between stage " + fromStage + " and stage " + toStage);
        } else {
            adjacencyList.get(fromStage - 1).add(toStage);
            System.out.println("Added edge between stage " + fromStage + " and stage " + toStage);
        }
    }

    private boolean isValidStageNumber(int stage) {
        return (stage >= 1 && stage <= k);
    }

    public void printGraph() {
        System.out.println("Multistage Graph:");

        for (int i = 0; i < k; i++) {
            System.out.print(i + " -> ");

            for (Integer adjacentStage : adjacencyList.get(i)) {
                if (!adjacentStage.equals(i)) {
                    System.out.println(" -> " + adjacentStage);
                }
            }
        }
    }

    public static void main(String[] args) {
        MultistageGraph graph = new MultistageGraph(4); // Create a multistage graph with k = 4 partitions

        try {
            graph.addEdge(1, 2);
            graph.addEdge(1, 3);
            graph.addEdge(2, 3);
            graph.printGraph();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
