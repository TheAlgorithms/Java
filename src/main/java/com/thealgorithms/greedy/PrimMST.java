import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Edge {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class PrimMST {
    public static List<Edge> primMST(List<List<Edge>> graph, int vertices) {
        List<Edge> minimumSpanningTree = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        Arrays.fill(visited, false);

        // Create a min-heap for edges (sorted by weight)
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);

        // Start from vertex 0
        minHeap.offer(new Edge(0, 0, 0));

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int source = edge.source;
            int destination = edge.destination;
            int weight = edge.weight;

            if (visited[destination])
                continue;

            visited[destination] = true;
            minimumSpanningTree.add(new Edge(source, destination, weight));

            for (Edge adjacentEdge : graph.get(destination)) {
                if (!visited[adjacentEdge.destination])
                    minHeap.offer(adjacentEdge);
            }
        }

        return minimumSpanningTree;
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++)
            graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(0, 1, 2));
        graph.get(1).add(new Edge(1, 0, 2));
        graph.get(1).add(new Edge(1, 2, 3));
        graph.get(2).add(new Edge(2, 1, 3));
        graph.get(0).add(new Edge(0, 3, 6));
        graph.get(3).add(new Edge(3, 0, 6));
        graph.get(1).add(new Edge(1, 3, 8));
        graph.get(3).add(new Edge(3, 1, 8));
        graph.get(1).add(new Edge(1, 4, 5));
        graph.get(4).add(new Edge(4, 1, 5));

        List<Edge> result = primMST(graph, vertices);
        System.out.println("Minimum Spanning Tree (Prim's algorithm):");
        for (Edge edge : result)
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
    }
}
