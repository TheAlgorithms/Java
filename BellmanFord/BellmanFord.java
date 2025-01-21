import java.util.*;

class BellmanFord {
    // Function implementing the Bellman-Ford algorithm
    public static void bellmanFord(int vertices, int edges[][], int source) {
        // Array to hold the shortest distances
        int dist[] = new int[vertices];
        
        // Initialize all distances to infinity, except for the source vertex
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        
        // Step 1: The initial run over all edges in the graph
        for (int i = 1; i < vertices - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0]; // Starting vertex
                int v = edge[1]; // Ending vertex
                int weight = edge[2]; // Edge weight
                
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Step 2: Check for negative-weight cycles
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // Print the shortest distances for all vertices
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        // Number of vertices in the graph
        int vertices = 5; 
        int edges[][] = {
            {0, 1, -1}, // Edge from vertex 0 to vertex 1 with weight -1
            {0, 2, 4},  // Edge from vertex 0 to vertex 2 with weight 4
            {1, 2, 3},  // Edge from vertex 1 to vertex 2 with weight 3
            {1, 3, 2},  // Edge from vertex 1 to vertex 3 with weight 2
            {1, 4, 2},  // Edge from vertex 1 to vertex 4 with weight 2
            {3, 2, 5},  // Edge from vertex 3 to vertex 2 with weight 5
            {3, 1, 1},  // Edge from vertex 3 to vertex 1 with weight 1
            {4, 3, -3}  // Edge from vertex 4 to vertex 3 with weight -3
        };

        // The source vertex
        int source = 0;
        
        // Call the Bellman-Ford algorithm
        bellmanFord(vertices, edges, source);
    }
}
