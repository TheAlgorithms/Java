/*
BidirectionalBFS.java
 
This class implements the Bidirectional Breadth-First Search (BFS) algorithm to efficiently
determine whether a path exists between two nodes in an unweighted graph. Instead of
searching from the start node alone, it simultaneously explores the graph from both the
start and goal nodes, meeting in the middle. This approach often reduces the number of
nodes visited compared to traditional BFS, making it faster for large graphs. The main
method provides an example graph and demonstrates usage by printing whether a path
exists between the specified start and goal nodes.
 */



import java.util.*;

public class BidirectionalBFS {

    public static boolean bidirectionalBFS(Map<Integer, List<Integer>> graph, int start, int goal) {
        if (start == goal) return true;

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
            if (expandFrontier(graph, queueStart, visitedStart, visitedGoal)) return true;
            // Expand from goal side
            if (expandFrontier(graph, queueGoal, visitedGoal, visitedStart)) return true;
        }

        return false; // no path found
    }

    private static boolean expandFrontier(Map<Integer, List<Integer>> graph, Queue<Integer> queue,
                                          Set<Integer> visitedThisSide, Set<Integer> visitedOtherSide) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int current = queue.poll();
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (visitedOtherSide.contains(neighbor)) return true;
                if (!visitedThisSide.contains(neighbor)) {
                    visitedThisSide.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3, 4));
        graph.put(3, Arrays.asList(1, 2, 5));
        graph.put(4, Arrays.asList(2, 5));
        graph.put(5, Arrays.asList(3, 4));

        int start = 0;
        int goal = 5;

        boolean pathExists = bidirectionalBFS(graph, start, goal);
        System.out.println("Path from " + start + " to " + goal + ": " + pathExists);
    }
}
