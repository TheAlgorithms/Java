import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Graph {
    private int vertices;
    private List<List<Edge>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++)
            adjList.add(new ArrayList<>());
    }

    public void addEdge(int source, int destination, int weight) {
        adjList.get(source).add(new Edge(source, destination, weight));
        adjList.get(destination).add(new Edge(destination, source, weight));
    }

    public void dijkstra(int startVertex) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        distance[startVertex] = 0;
        minHeap.offer(new Edge(startVertex, startVertex, 0));

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int currentVertex = edge.destination;

            if (edge.weight > distance[currentVertex])
                continue;

            for (Edge adjacentEdge : adjList.get(currentVertex)) {
                int newDistance = distance[currentVertex] + adjacentEdge.weight;
                if (newDistance < distance[adjacentEdge.destination]) {
                    distance[adjacentEdge.destination] = newDistance;
                    minHeap.offer(new Edge(currentVertex, adjacentEdge.destination, newDistance));
                }
            }
        }

        System.out.println("Shortest distances from vertex " + startVertex + " using Dijkstra's Algorithm:");
        for (int i = 0; i < vertices; i++)
            System.out.println("Vertex " + i + ": " + distance[i]);
    }
}

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        int vertices = 5;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1, 9);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 4, 3);
        graph.addEdge(2, 1, 2);
        graph.addEdge(3, 2, 1);
        graph.addEdge(4, 3, 4);

        graph.dijkstra(0);
    }
}
