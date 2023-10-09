import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphColoring {
    private int V; // Number of vertices
    private List<Integer>[] adjacencyList;

    public GraphColoring(int vertices) {
        this.V = vertices;
        adjacencyList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adjacencyList[u].add(v);
        adjacencyList[v].add(u);
    }

    public int[] greedyGraphColoring() {
        int[] color = new int[V];
        Arrays.fill(color, -1); // Initialize all vertices as uncolored
        boolean[] available = new boolean[V];
        
        // Assign the first color to the first vertex
        color[0] = 0;

        for (int u = 1; u < V; u++) {
            Arrays.fill(available, true);

            // Check colors of adjacent vertices
            for (int i : adjacencyList[u]) {
                if (color[i] != -1) {
                    available[color[i]] = false;
                }
            }

            // Find the first available color
            int clr;
            for (clr = 0; clr < V; clr++) {
                if (available[clr]) {
                    break;
                }
            }

            color[u] = clr;
        }

        return color;
    }

    public static void main(String[] args) {
        int V = 6; // Number of vertices
        GraphColoring graph = new GraphColoring(V);

        // Add edges between vertices
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        int[] colors = graph.greedyGraphColoring();

        System.out.println("Vertex\tColor");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + colors[i]);
        }
    }
}
