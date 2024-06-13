package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
class Node {
    int color = 1;
    Set<Integer> edges = new HashSet<Integer>();
}

public final class MColoring {
    private MColoring() {
    }
    static int possiblePaint(ArrayList<Node> nodes, int n, int m) {

        // Create a visited array of n nodes
        ArrayList<Integer> visited = new ArrayList<Integer>();
        for (int i = 0; i < n + 1; i++) {
            visited.add(0);
        }

        // maxColors used till now are 1 as
        // all nodes are painted color 1
        int maxColors = 1;

        for (int sv = 1; sv <= n; sv++) {
            if (visited.get(sv) > 0) {
                continue;
            }

            // If the starting point is unvisited,
            // mark it visited and push it in queue
            visited.set(sv, 1);
            Queue<Integer> q = new LinkedList<>();
            q.add(sv);

            // BFS
            while (q.size() != 0) {
                int top = q.peek();
                q.remove();

                // Checking all adjacent nodes
                // to "top" edge in our queue
                for (int it : nodes.get(top).edges) {

                    // If the color of the
                    // adjacent node is same, increase it by
                    // 1
                    if (nodes.get(top).color == nodes.get(it).color) {
                        nodes.get(it).color += 1;
                    }

                    // If number of colors used exceeds m,
                    // return 0
                    maxColors = Math.max(maxColors, Math.max(nodes.get(top).color, nodes.get(it).color));
                    if (maxColors > m) {
                        return 0;
                    }

                    // If the adjacent node is not visited,
                    // mark it visited and push it in queue
                    if (visited.get(it) == 0) {
                        visited.set(it, 1);
                        q.add(it);
                    }
                }
            }
        }
        return 1;
    }
}
