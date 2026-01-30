package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

class Cycle {

    private final int nodes;
    private final int[][] adjacencyMatrix;
    private final boolean[] visited;
    private final List<List<Integer>> cycles = new ArrayList<>();

    Cycle(int nodes, int[][] adjacencyMatrix) {
        this.nodes = nodes;
        this.adjacencyMatrix = new int[nodes][nodes];
        // Deep copy matrix to avoid side-effects
        for (int i = 0; i < nodes; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, this.adjacencyMatrix[i], 0, nodes);
        }
        this.visited = new boolean[nodes];
    }

    public void start() {
        for (int i = 0; i < nodes; i++) {
            dfs(i, i, new ArrayList<>());
            for (int j = 0; j < nodes; j++) {
                this.adjacencyMatrix[i][j] = 0;
                this.adjacencyMatrix[j][i] = 0;
            }
        }
    }

    private void dfs(int start, int curr, List<Integer> temp) {
        temp.add(curr);
        visited[curr] = true;
        for (int i = 0; i < nodes; i++) {
            if (adjacencyMatrix[curr][i] == 1) {
                if (i == start) {
                    cycles.add(new ArrayList<>(temp));
                } else {
                    if (!visited[i]) {
                        dfs(start, i, temp);
                    }
                }
            }
        }

        if (!temp.isEmpty()) {
            temp.remove(temp.size() - 1);
        }
        visited[curr] = false;
    }

    public List<List<Integer>> getCycles() {
        return cycles;
    }

    public void printAll() {
        for (List<Integer> cycle : cycles) {
            for (Integer node : cycle) {
                System.out.print(node + " -> ");
            }
            if (!cycle.isEmpty()) {
                System.out.println(cycle.get(0));
            }
            System.out.println();
        }
    }
}

public final class Cycles {
    private Cycles() {
    }

    public static void main(String[] args) {
        // Example usage with a triangle graph: 0 -> 1 -> 2 -> 0
        int nodes = 3;
        int[][] matrix = {
                { 0, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 0 }
        };

        Cycle c = new Cycle(nodes, matrix);
        c.start();
        c.printAll();
    }
}
