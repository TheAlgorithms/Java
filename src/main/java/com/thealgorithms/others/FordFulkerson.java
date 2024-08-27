package com.thealgorithms.others;
import java.util.*;

public class FordFulkerson {

  // Method to perform Breadth-First Search to find an augmenting path
  boolean bfs(int rGraph[][], int source, int sink, int parent[]) {
      // Create a visited array and mark all vertices as not visited
      boolean visited[] = new boolean[rGraph.length];
      Arrays.fill(visited, false);

      // Create a queue, enqueue source vertex and mark it as visited
      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(source);
      visited[source] = true;
      parent[source] = -1;

      // Standard BFS loop
      while (!queue.isEmpty()) {
          int u = queue.poll();

          for (int v = 0; v < rGraph.length; v++) {
              if (!visited[v] && rGraph[u][v] > 0) { // if not yet visited and there is an edge
                  if (v == sink) { // if we found the sink
                      parent[v] = u;
                      return true;
                  }
                  queue.add(v);
                  parent[v] = u;
                  visited[v] = true;
              }
          }
      }

      return false; // No augmenting path found
  }

  // Returns the maximum flow from source to sink in the given graph
  int fordFulkerson(int graph[][], int source, int sink) {
      int u, v;

      // Create a residual graph and fill the residual graph with given capacities
      int rGraph[][] = new int[graph.length][graph.length];

      for (u = 0; u < graph.length; u++) {
          for (v = 0; v < graph.length; v++) {
              rGraph[u][v] = graph[u][v];
          }
      }

      // This array is filled by BFS and to store the path
      int parent[] = new int[graph.length];

      int maxFlow = 0;  // Initially, no flow

      // Augment the flow while there is a path from source to sink
      while (bfs(rGraph, source, sink, parent)) {
          // Find the maximum flow through the path found by BFS
          int pathFlow = Integer.MAX_VALUE;
          for (v = sink; v != source; v = parent[v]) {
              u = parent[v];
              pathFlow = Math.min(pathFlow, rGraph[u][v]);
          }

          // Update residual capacities of the edges and reverse edges along the path
          for (v = sink; v != source; v = parent[v]) {
              u = parent[v];
              rGraph[u][v] -= pathFlow;
              rGraph[v][u] += pathFlow;
          }

          // Add the path flow to the overall flow
          maxFlow += pathFlow;
      }

      return maxFlow;
  }

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter the number of vertices:");
      int V = scanner.nextInt();

      int graph[][] = new int[V][V];

      System.out.println("Enter the number of edges:");
      int E = scanner.nextInt();

      System.out.println("Enter the edges with capacities (u v capacity):");
      for (int i = 0; i < E; i++) {
          int u = scanner.nextInt();
          int v = scanner.nextInt();
          int capacity = scanner.nextInt();
          graph[u][v] = capacity;
      }

      System.out.println("Enter the source vertex:");
      int source = scanner.nextInt();

      System.out.println("Enter the sink vertex:");
      int sink = scanner.nextInt();

      FordFulkerson maxFlow = new FordFulkerson();
      System.out.println("The maximum possible flow is: " + maxFlow.fordFulkerson(graph, source, sink));

      scanner.close();
  }
}