package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Node class represents a graph node. Each node is associated with a color
 * (initially 1) and contains a set of edges representing its adjacent nodes.
 *
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
class Node {
    int color = 1; // Initial color for each node
    Set<Integer> edges = new HashSet<Integer>(); // Set of edges representing adjacent nodes
}

/**
 * MColoring class solves the M-Coloring problem where the goal is to determine
 * if it's possible to color a graph using at most M colors such that no two
 * adjacent nodes have the same color.
 */
public final class MColoring {

    private MColoring() {
    } // Prevent instantiation of utility class

    /**
     * Determines whether it is possible to color the graph using at most M colors.
     *
     * @param nodes List of nodes representing the graph.
     * @param n     The total number of nodes in the graph.
     * @param m     The maximum number of allowed colors.
     * @return true if the graph can be colored using M colors, false otherwise.
     */
    static boolean isColoringPossible(ArrayList<Node> nodes, int n, int m) {

        // Visited array keeps track of whether each node has been processed.
        ArrayList<Integer> visited = new ArrayList<Integer>();
        for (int i = 0; i < n + 1; i++) {
            visited.add(0); // Initialize all nodes as unvisited (0)
        }

        // The number of colors used so far (initially set to 1, since all nodes
        // start with color 1).
        int maxColors = 1;

        // Loop through all the nodes to ensure every node is visited, in case the
        // graph is disconnected.
        for (int sv = 1; sv <= n; sv++) {
            if (visited.get(sv) > 0) {
                continue; // Skip nodes that are already visited
            }

            // If the node is unvisited, mark it as visited and add it to the queue for BFS.
            visited.set(sv, 1);
            Queue<Integer> q = new LinkedList<>();
            q.add(sv);

            // Perform BFS to process all nodes and their adjacent nodes
            while (q.size() != 0) {
                int top = q.peek(); // Get the current node from the queue
                q.remove();

                // Check all adjacent nodes of the current node
                for (int it : nodes.get(top).edges) {

                    // If the adjacent node has the same color as the current node, increment its
                    // color to avoid conflict.
                    if (nodes.get(top).color == nodes.get(it).color) {
                        nodes.get(it).color += 1;
                    }

                    // Keep track of the maximum number of colors used so far
                    maxColors = Math.max(maxColors, Math.max(nodes.get(top).color, nodes.get(it).color));

                    // If the number of colors used exceeds the allowed limit M, return false.
                    if (maxColors > m) {
                        return false;
                    }

                    // If the adjacent node hasn't been visited yet, mark it as visited and add it
                    // to the queue for further processing.
                    if (visited.get(it) == 0) {
                        visited.set(it, 1);
                        q.add(it);
                    }
                }
            }
        }

        return true; // Possible to color the entire graph with M or fewer colors.
    }
}
