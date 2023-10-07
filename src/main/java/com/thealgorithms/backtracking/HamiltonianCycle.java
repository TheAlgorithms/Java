package com.thealgorithms.backtracking;

import java.util.*;

/**
 * @author Vanshika Dargan (<a href="https://github.com/Vanshika-Dargan"></a>)
 *
 * Problem Statement:
 *
 * Given an undirected graph, the task is to determine whether the
 * graph contains a Hamiltonian cycle or not. If it contains, then
 * print the path.
 *
 * Hamiltonian Cycle in a graph is a cycle that visits every vertex
 * of the graph exactly once and returns to the starting vertex.
 *
 * Example:
 * Input:- graph:
 * (0)--(1)--(2)
 * |   / \    |
 * |  /   \   |
 * | /     \  |
 * (3)       (4)
 *
 * vertices: 5
 *
 * Output: 0 -> 1 -> 2 -> 4 -> 3 -> 0
 *
 * Wikipedia link:- https://en.wikipedia.org/wiki/Hamiltonian_path
 */
public
class HamiltonianCycle {

private
    final int V;
public
    static int path[];
private
    static Set<Integer> visited;

public
    HamiltonianCycle(int V) {
        this.V = V;
        path = new int[V];
        visited = new HashSet<>();
    }

    /*
     * This function is a utility function to print the path.
     */
public
    void printPath() {
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " -> ");
        }
        System.out.print(path[0]);
    }

    /*
     * This function is a helper function that finds the Hamiltonian cycle
     * and populates the path array.
     */
private
    boolean hamiltonianCycleUtil(int[][] graph, int curr) {
        // If all vertices are included in the Hamiltonian cycle
        if (curr == V) {
            // and if there is an edge from the last vertex in the path to the start vertex
            if (graph[path[curr - 1]][path[0]] == 1) {
                return true;
            }
            return false;
        }

        // Find the next feasible vertex that can be included in the path
        for (int i = 0; i < V; i++) {
            // If the vertex is not already included in the path
            if (!visited.contains(i)) {
                // and if there is an edge from the last added vertex to the current vertex
                if (graph[path[curr - 1]][i] == 1) {
                    path[curr] = i;
                    visited.add(i);
                    // Look for the next vertex to construct the path
                    if (hamiltonianCycleUtil(graph, curr + 1)) {
                        return true;
                    }
                    // Remove the vertex from the path if it doesn't yield the solution
                    visited.remove(visited.size() - 1);
                }
            }
        }
        // If no further vertex can be added to the path, no Hamiltonian cycle is found.
        return false;
    }

    /*
     * This function finds the Hamiltonian cycle, if any, and prints its path.
     */
public
    static int findHamiltonianCycle(int[][] graph, int vertices) {
        HamiltonianCycle hm = new HamiltonianCycle(vertices);

        path[0] = 0;
        visited.add(0);

        if (hm.hamiltonianCycleUtil(graph, 1)) {
            hm.printPath();
            return 1;
        }
        System.out.println("No Hamiltonian cycle found");
        return 0;
    }
}


