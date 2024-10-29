import java.util.*;

public class DijkstraShortestPath {

    public int[] shortestPath(int numNodes, Map<Integer, List<int[]>> adjList, int startNode) {
        int[] distances = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[] {startNode, 0});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[currentNode]) {
                continue;
            }

            for (int[] edge : adjList.getOrDefault(currentNode, Collections.emptyList())) {
                int nextNode = edge[0];
                int edgeWeight = edge[1];
                int newDistance = distances[currentNode] + edgeWeight;
                if (newDistance < distances[nextNode]) {
                    distances[nextNode] = newDistance;
                    minHeap.offer(new int[] {nextNode, newDistance});
                }
            }
        }
        return distances;
    }
}

// wikipedia link for algorithm
// https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#:~:text=Dijkstra's%20algorithm%20(/%CB%88da%C9%AA,and%20published%20three%20years%20later.
