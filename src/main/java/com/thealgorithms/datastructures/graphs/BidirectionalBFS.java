package com.thealgorithms.graphs;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Implementation of the Bidirectional Breadth-First Search (BiBFS) algorithm.
 * This algorithm performs BFS from both the start and goal nodes simultaneously,
 * greatly reducing search space in large unweighted graphs.
 *
 * Wikipedia reference: https://en.wikipedia.org/wiki/Bidirectional_search
 */
public class BidirectionalBFS {

    /**
     * Checks if a path exists between start and goal using bidirectional BFS.
     *
     * @param graph The adjacency list of the graph
     * @param start The starting node
     * @param goal  The goal node
     * @return true if a path exists, false otherwise
     */
    public static boolean bidirectionalBFS(
            Map<Integer, List<Integer>> graph,
            int start,
            int goal
    ) {
        if (start == goal) {
            return true;
        }

        Set<Integer> visitedStart = new HashSet<>();
        Set<Integer> visitedGoal = new HashSet<>();

        Queue<Integer> queueStart = new LinkedList<>();
        Queue<Integer> queueGoal = new LinkedList<>();

        queueStart.add(start);
        queueGoal.add(goal);

        visitedStart.add(start);
        visitedGoal.add(goal);

        while (!queueStart.isEmpty() && !queueGoal.isEmpty()) {
            // Expand from start side
            if (expandFrontier(graph, queueStart, visitedStart, visitedGoal)) {
                return true;
            }
            // Expand from goal side
            if (expandFrontier(graph, queueGoal, visitedGoal, visitedStart)) {
                return true;
            }
        }

        return false; // No path found
    }

    /**
     * Helper function to expand one level of BFS frontier.
     *
     * @param graph            The adjacency list of the graph
     * @param queue            The BFS queue for this side
     * @param visitedThisSide  Set of nodes visited from this side
     * @param visitedOtherSide Set of nodes visited from the other side
     * @return true if the frontiers meet, false otherwise
     */
    private static boolean expandFrontier(
            Map<Integer, List<Integer>> graph,
            Queue<Integer> queue,
            Set<Integer> visitedThisSide,
            Set<Integer> visitedOtherSide
    ) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int current = queue.poll();
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (visitedOtherSide.contains(neighbor)) {
                    return true;
                }
                if (!visitedThisSide.contains(neighbor)) {
                    visitedThisSide.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
}
