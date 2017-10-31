import java.util.Arrays;

public class BellmanFord {

    private static final int INF  = Integer.MAX_VALUE;

    public void findShortestsPathsFrom(int srcVertex, Graph graph) {

        // dp[ALL] = INF
        int [] dpDistances = new int[graph.nVertices];
        Arrays.fill(dpDistances, INF);

        // Distance from srcVertex to itself must be 0
        dpDistances[srcVertex] = 0;

        for (int i=0; i < graph.nVertices; i++) {
            for (int j=0; j < graph.nEdges; j++) {

                int src = graph.edges[j].src;
                int tar = graph.edges[j].target;
                int weight = graph.edges[j].weight;

                if (dpDistances[src] != INF && dpDistances[src] + weight < dpDistances[tar])
                    dpDistances[tar] = dpDistances[src] + weight;
            }
        }

        boolean hasNegativeCycle = false;
        // Find if there is a negative cycle
        for (int i=0; i < graph.nEdges; i++) {

            int src = graph.edges[i].src;
            int tar = graph.edges[i].target;
            int weight = graph.edges[i].weight;

            if (dpDistances[src] != INF && dpDistances[src] + weight < dpDistances[tar]) {
                hasNegativeCycle = true;
                i = graph.nEdges;
            }
        }

        if (hasNegativeCycle)
            System.out.println("There is a negative cycle.");

        printDistancesFromSrc(dpDistances);

    }

    public void printDistancesFromSrc(int distances[]) {
        int nVertices = distances.length;

        System.out.println("Vertex  -  Distance from src");
        for (int i=0; i < nVertices; ++i)
            System.out.println(
                    String.format("%6s", i) + "  : " + String.format("%2s", distances[i])
            );
    }

    public static void main(String args[]) {

        //{INF,  -1,   4, INF, INF}
        //{INF, INF,   3,   2,   2}
        //{INF, INF, INF, INF, INF}
        //{INF,   1,   5, INF, INF}
        //{INF, INF, INF,  -3, INF}

        int nVertices = 5;
        int nEdges = 8;
        Graph graph = new Graph(nVertices, nEdges);

        graph.connectVertices(0, 1, -1);
        graph.connectVertices(0, 2, 4);
        graph.connectVertices(1, 2, 3);
        graph.connectVertices(1, 3, 2);
        graph.connectVertices(1, 4, 2);
        graph.connectVertices(3, 2, 5);
        graph.connectVertices(3, 1, 1);
        graph.connectVertices(4, 3, -3);

        int srcVertex = 0;
        BellmanFord bmf = new BellmanFord();
        bmf.findShortestsPathsFrom(srcVertex, graph);

    }

}

class Graph {

    class Edge {

        int src, target, weight;

        public Edge(int src, int target, int weight) {
            this.src = src;
            this.target = target;
            this.weight = weight;
        }
    }

    private int lastAdded;
    int nVertices, nEdges;
    Edge edges[];

    public Graph(int nVertices, int nEdges) {
        this.nVertices = nVertices;
        this.nEdges = nEdges;
        this.edges = new  Edge[nEdges];
        this.lastAdded = -1;
    }

    public void connectVertices(int v1, int v2, int w) {
        if (this.lastAdded < nEdges) {
            this.lastAdded++;
            Edge edge = new Edge(v1, v2, w);
            edges[lastAdded] = edge;
        }
    }

}