import java.util.Arrays;

public class GraphColoring {
    public static int[] greedyGraphColoring(int[][] graph) {
        int V = graph.length; // Number of vertices in the graph

        // Initialize color and availability arrays
        int[] color = new int[V]; // 0 indicates uncolored
        Arrays.fill(color, -1);
        boolean[] available = new boolean[V]; // True indicates the color is available

        // Assign the first color to the first vertex
        color[0] = 0;

        // Iterate over the vertices to assign colors
        for (int u = 1; u < V; u++) {
            Arrays.fill(available, true);

            for (int i = 0; i < V; i++) {
                // Check if there's an edge between u and i and color[i] is assigned
                if (graph[u][i] == 1 && color[i] != -1) {
                    available[color[i]] = false;
                }
            }

            // Find the first available color
            int clr = 0;
            while (clr < V && !available[clr]) {
                clr++;
            }

            // Assign the found color to vertex u
            color[u] = clr;
        }

        return color;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };

        int[] colors = greedyGraphColoring(graph);

        System.out.println("Vertex colors: " + Arrays.toString(colors));
    }
}
