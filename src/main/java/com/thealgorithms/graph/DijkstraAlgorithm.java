import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Finds the shortest paths from a source node to all other nodes in a graph using Dijkstra's algorithm.
 *
 * @param adjList The adjacency list representation of the graph where each edge has a weight.
 * @param n       The number of nodes in the graph.
 * @param source  The starting node for finding the shortest path.
 * @return An array where the value at each index represents the shortest distance from the source to that node.
 */
public class DijkstraAlgorithm {

    static class Pair {
        int node;
        int weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int[] dijkstra(HashMap<Integer, List<Pair>> adjList, int n, int source) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int currentNode = current.node;
            int currentWeight = current.weight;

            List<Pair> neighbors = adjList.get(currentNode);
            if (neighbors != null) {
                for (Pair neighbor : neighbors) {
                    int newDist = currentWeight + neighbor.weight;
                    if (newDist < distances[neighbor.node]) {
                        distances[neighbor.node] = newDist;
                        pq.add(new Pair(neighbor.node, newDist));
                    }
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        HashMap<Integer, List<Pair>> adjList = new HashMap<>();
        adjList.put(0, Arrays.asList(new Pair(1, 4), new Pair(2, 1)));
        adjList.put(1, Arrays.asList(new Pair(3, 1)));
        adjList.put(2, Arrays.asList(new Pair(1, 2), new Pair(3, 5)));
        adjList.put(3, new ArrayList<>());

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        int[] distances = dijkstra.dijkstra(adjList, 4, 0);

        System.out.println("Shortest distances from source 0:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To node " + i + ": " + distances[i]);
        }
    }
}
