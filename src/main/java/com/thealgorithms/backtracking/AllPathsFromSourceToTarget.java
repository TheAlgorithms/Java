package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {

    private final int v;
    private final List<List<Integer>> allPaths = new ArrayList<>();
    private ArrayList<Integer>[] adjList;

    public AllPathsFromSourceToTarget(int vertices) {
        this.v = vertices;
        initAdjList();
    }

    @SuppressWarnings("unchecked")
    private void initAdjList() {
        adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    public void findAllPaths(int s, int d) {
        boolean[] isVisited = new boolean[v];
        List<Integer> path = new ArrayList<>();
        path.add(s);
        findAllPathsUtil(s, d, isVisited, path);
    }

    private void findAllPathsUtil(int u, int d, boolean[] isVisited, List<Integer> currentPath) {
        if (u == d) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        isVisited[u] = true;

        for (Integer neighbor : adjList[u]) {
            if (!isVisited[neighbor]) {
                currentPath.add(neighbor);
                findAllPathsUtil(neighbor, d, isVisited, currentPath);
                currentPath.remove(neighbor);
            }
        }

        isVisited[u] = false;
    }

    public static List<List<Integer>> allPathsFromSourceToTarget(int vertices, int[][] edges, int source, int destination) {
        AllPathsFromSourceToTarget graph = new AllPathsFromSourceToTarget(vertices);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        graph.findAllPaths(source, destination);
        return graph.allPaths;
    }
}
