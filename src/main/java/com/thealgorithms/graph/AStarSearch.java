package Astar;

import java.util.*;

/**
 * A* Search Algorithm for shortest pathfinding.
 *
 * <p>Commonly used in games, navigation, and robotics. Combines Dijkstra’s Algorithm and heuristic estimation.</p>
 *
 * 
 */
public class AStarSearch {

    static class Node implements Comparable<Node> {
        int id;
        double g; // Cost from start
        double h; // Heuristic to goal
        double f; // Total cost = g + h
        Node parent;

        Node(int id, double g, double h, Node parent) {
            this.id = id;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.f, o.f);
        }
    }

    private final Map<Integer, List<int[]>> graph;

    public AStarSearch() {
        graph = new HashMap<>();
    }

    public void addEdge(int u, int v, int weight) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, weight});
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, weight}); // if undirected
    }

    private double heuristic(int node, int goal) {
        return Math.abs(goal - node); // Simplified heuristic
    }

    public List<Integer> findPath(int start, int goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Integer, Double> gScore = new HashMap<>();
        Set<Integer> closedSet = new HashSet<>();

        openSet.add(new Node(start, 0, heuristic(start, goal), null));
        gScore.put(start, 0.0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.id == goal) {
                return reconstructPath(current);
            }

            closedSet.add(current.id);

            for (int[] edge : graph.getOrDefault(current.id, new ArrayList<>())) {
                int neighbor = edge[0];
                double tentativeG = current.g + edge[1];

                if (closedSet.contains(neighbor)) continue;

                if (tentativeG < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    gScore.put(neighbor, tentativeG);
                    Node next = new Node(neighbor, tentativeG, heuristic(neighbor, goal), current);
                    openSet.add(next);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Integer> reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();
        while (node != null) {
            path.add(node.id);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AStarSearch aStar = new AStarSearch();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        System.out.println("Enter edges in format: u v weight");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            aStar.addEdge(u, v, w);
        }

        System.out.print("Enter start node: ");
        int start = sc.nextInt();

        System.out.print("Enter goal node: ");
        int goal = sc.nextInt();

        List<Integer> path = aStar.findPath(start, goal);
        if (path.isEmpty()) {
            System.out.println("No path found from " + start + " → " + goal);
        } else {
            System.out.println("Shortest path from " + start + " → " + goal + ": " + path);
        }
        sc.close();
    }
}