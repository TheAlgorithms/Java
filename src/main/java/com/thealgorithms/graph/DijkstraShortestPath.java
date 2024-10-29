import java.util.*;

public class DijkstraShortestPath {

    /**
     * Finds the shortest path distances from the startNode to all other nodes in a weighted graph.
     *
     * @param n The number of nodes in the graph.
     * @param adjList The adjacency list where each entry contains pairs of neighboring nodes and edge weights.
     * @param startNode The starting node for the shortest path calculation.
     * @return An array where each index i contains the shortest distance from startNode to node i.
     */
    public int[] shortestPath(int n, Map<Integer, List<int[]>> adjList, int startNode) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[]{startNode, 0});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[currentNode]) {
                continue;
            }

            List<int[]> neighbors = adjList.getOrDefault(currentNode, new ArrayList<>());
            for (int[] neighbor : neighbors) {
                int nextNode = neighbor[0];
                int edgeWeight = neighbor[1];

                int newDistance = distances[currentNode] + edgeWeight;
                if (newDistance < distances[nextNode]) {
                    distances[nextNode] = newDistance;
                    minHeap.offer(new int[]{nextNode, newDistance});
                }
            }
        }

        return distances;
    }
}
