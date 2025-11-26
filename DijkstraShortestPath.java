import java.util.*;

public class DijkstraAlgorithm {
    static class Edge {
        int to;
        int weight;
        Edge(int t, int w) {
            to = t;
            weight = w;
        }
    }

    public static int[] dijkstra(int n, List<List<Edge>> graph, int source) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});

        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int w = e.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 6;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 2));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(1, 1));
        graph.get(2).add(new Edge(3, 8));
        graph.get(3).add(new Edge(4, 6));
        graph.get(4).add(new Edge(5, 3));

        int[] dist = dijkstra(n, graph, 0);

        for (int i = 0; i < n; i++) {
            System.out.println("Distance to " + i + " = " + dist[i]);
        }
    }
}
