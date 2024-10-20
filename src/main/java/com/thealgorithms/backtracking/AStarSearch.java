package com.thealgorithms.backtracking;

// Author: Jivan Jamdar

/* A* Search
 Problem :  finds the shortest path between nodes in a weighted graph by combining actual travel costs and heuristic estimates. 

 Graph Structure:

 (0) --1.0--> (1) --1.0--> (2) --1.0--> (3) --1.0--> (4) --1.0--> (5)
 Coordinates:

 Node 0: (0, 0)
 Node 1: (1, 1)
 Node 2: (2, 2)
 Node 3: (3, 3)
 Node 4: (4, 4)
 Node 5: (5, 5)

finds the shortest path from Node 0 to Node 5 using the Euclidean distance as a heuristic.

Path Found:
[0, 1, 2, 3, 4, 5]

*/

import java.util.ArrayList;      
import java.util.HashMap;      
import java.util.List;          
import java.util.Map;
import java.util.PriorityQueue; 
import java.util.Comparator;     
import java.util.Collections;     

public class AStarSearch {

    // Helper class to represent graph edges
    static class Edge {
        int target;   // Target vertex
        double weight; // Weight of the edge

        Edge(int target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Helper class to represent a node with its fScore for the priority queue
    static class Node {
        int vertex;  // Vertex index
        double fScore; // Total estimated cost

        Node(int vertex, double fScore) {
            this.vertex = vertex;
            this.fScore = fScore;
        }
    }

    // Comparator for priority queue to prioritize based on fScore
    static class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return Double.compare(n1.fScore, n2.fScore);
        }
    }

    // Heuristic function (Euclidean distance between two points as an example)
    public double heuristic(int[] point1, int[] point2) {
        return Math.sqrt(Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2));
    }

    // A* Search algorithm
    public List<Integer> aStar(List<List<Edge>> graph, int[] start, int[] goal, Map<Integer, int[]> coordinates) {
        int V = graph.size();
        double[] gScore = new double[V]; 
        Arrays.fill(gScore, Double.MAX_VALUE);
        gScore[start[0]] = 0;

        double[] fScore = new double[V]; // (g + heuristic)
        Arrays.fill(fScore, Double.MAX_VALUE);
        fScore[start[0]] = heuristic(coordinates.get(start[0]), coordinates.get(goal[0]));

        PriorityQueue<Node> openSet = new PriorityQueue<>(new NodeComparator());
        openSet.add(new Node(start[0], fScore[start[0]]));

        Map<Integer, Integer> cameFrom = new HashMap<>(); 

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            // Check if the goal has been reached
            if (current.vertex == goal[0]) {
                return reconstructPath(cameFrom, current.vertex);
            }

            // Explore neighbors
            for (Edge neighbor : graph.get(current.vertex)) {
                double tentative_gScore = gScore[current.vertex] + neighbor.weight;

                // If the new path is better, update the scores
                if (tentative_gScore < gScore[neighbor.target]) {
                    cameFrom.put(neighbor.target, current.vertex);
                    gScore[neighbor.target] = tentative_gScore;
                    fScore[neighbor.target] = gScore[neighbor.target] + heuristic(coordinates.get(neighbor.target), coordinates.get(goal[0]));
                    openSet.add(new Node(neighbor.target, fScore[neighbor.target]));
                }
            }
        }

        return Collections.emptyList(); 
    }

    // Helper method to reconstruct the path from start to goal
    private List<Integer> reconstructPath(Map<Integer, Integer> cameFrom, int current) {
        List<Integer> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }
}
