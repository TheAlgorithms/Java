package com.thealgorithms.datastructures.graphs;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// Link for reference: https://en.wikipedia.org/wiki/Parallel_breadth-first_search
class DistributedBFS {
    private final int numNodes;
    private final boolean[][] graph;
    private final boolean[] visited;

    public DistributedBFS(int numNodes, boolean[][] graph) {
        this.numNodes = numNodes;
        this.graph = graph;
        this.visited = new boolean[numNodes];
    }

    public void bfs(int startNode) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(numNodes);

        queue.add(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.println("Visited node: " + currentNode);

            for (int neighbor = 0; neighbor < numNodes; neighbor++) {
                if (graph[currentNode][neighbor] && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        executor.shutdown();
    }
    public static void main(String[] args) {
        int numNodes = 6; // Number of nodes in the graph
        boolean[][] graph = {
            {false, true, true, false, false, false}, {true, false, false, true, false, false}, {true, false, false, false, true, false}, {false, true, false, false, true, true}, {false, false, true, true, false, false}, {false, false, false, true, false, false}};

        DistributedBFS distributedBFS = new DistributedBFS(numNodes, graph);

        // Start BFS traversal from node 0
        System.out.println("Starting BFS from node 0:");
        distributedBFS.bfs(0);

        // You can start BFS from other nodes as well
        // Example: Start BFS from node 3
        System.out.println("\nStarting BFS from node 3:");
        distributedBFS.bfs(3);
    }
}
