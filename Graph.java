import java.util.*;

public class Graph {
    private int V; // Number of vertices
    private List<Edge>[] adjList; // Adjacency list to store the graph

    // Class to represent a vertex
    class Vertex {
        boolean visited;
        int parent;

        Vertex() {
            visited = false;
            parent = -1;
        }
    }

    // Class to represent an edge
    class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // Add an undirected edge to the graph
    public void addEdge(int source, int destination, int weight) {
        adjList[source].add(new Edge(source, destination, weight));
        adjList[destination].add(new Edge(destination, source, weight));
    }

    // Prim's algorithm to find MST
    public List<Edge> primMST() {
        List<Edge> mst = new ArrayList<>();
        Vertex[] vertices = new Vertex[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < V; i++) {
            vertices[i] = new Vertex();
        }

        // Start with an arbitrary vertex (vertex 0 in this case)
        pq.offer(new Edge(-1, 0, 0));

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int u = currentEdge.destination;

            // Skip if this edge leads to an already visited vertex
            if (vertices[u].visited)
                continue;

            // Include the current edge in MST
            if (currentEdge.source != -1) {
                mst.add(currentEdge);
            }
            vertices[u].visited = true;

            // Add adjacent edges to the priority queue
            for (Edge neighbor : adjList[u]) {
                int v = neighbor.destination;
                if (!vertices[v].visited) {
                    pq.offer(neighbor);
                }
            }
        }

        return mst;
    }
}

public class PrimMSTExample {
    public static void main(String[] args) {
        int V = 5; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges to the graph
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        List<Graph.Edge> mst = graph.primMST();

        System.out.println("Edges in Minimum Spanning Tree:");
        for (Graph.Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " - " + edge.weight);
        }
    }
}
