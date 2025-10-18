package com.thealgorithms.graph;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ArticulationPoint {
    // Inner class to represent an edge
    class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, int par, int[] dt, int[] low, boolean[] vis, int time,
            List<List<Integer>> res) {
        vis[curr] = true;
        dt[curr] = low[curr] = ++time;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            int neigh = e.dest;
            if (neigh == par) {
                continue;
            } else if (!vis[neigh]) {
                dfs(graph, neigh, curr, dt, low, vis, time, res);
                low[curr] = Math.min(low[neigh], low[curr]);
                if (dt[curr] < low[neigh]) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(curr);
                    pair.add(neigh);
                    res.add(pair);
                }
            } else {
                low[curr] = Math.min(low[curr], dt[neigh]);
            }
        }
    }

    public static List<List<Integer>> tarjans(ArrayList<Edge>[] graph, int V) {
        List<List<Integer>> res = new ArrayList<>();
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1, dt, low, vis, time, res);
            }
        }
        return res;
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> list : connections) {
            int u = list.get(0);
            int v = list.get(1);
            graph[u].add(new Edge(u, v));
            graph[v].add(new Edge(v, u));
        }
        return tarjans(graph, n);
    }

}
