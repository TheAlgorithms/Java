import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Edge {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class KruskalMST {
    public static List<Edge> kruskalMST(List<Edge> edges, int vertices) {
        List<Edge> minimumSpanningTree = new ArrayList<>();
        // Sort edges by weight
        Collections.sort(edges, Comparator.comparingInt(edge -> edge.weight));

        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++)
            parent[i] = i;  // Initialize each vertex as its own parent

        for (Edge edge : edges) {
            int sourceParent = findParent(parent, edge.source);
            int destinationParent = findParent(parent, edge.destination);

            // Check if adding this edge forms a cycle
            if (sourceParent != destinationParent) {
                minimumSpanningTree.add(edge);
                // Union the sets of source and destination
                parent[sourceParent] = destinationParent;
            }
        }

        return minimumSpanningTree;
    }

    private static int findParent(int[] parent, int vertex) {
        // Find the representative of the set (parent) to which the vertex belongs
        if (parent[vertex] != vertex)
            parent[vertex] = findParent(parent, parent[vertex]);  // Path compression
        return parent[vertex];
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 6));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 9));

        List<Edge> result = kruskalMST(edges, vertices);
        System.out.println("Minimum Spanning Tree (Kruskal's algorithm):");
        for (Edge edge : result)
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
    }
}
