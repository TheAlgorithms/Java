import java.util.*;

class Solution {
    // Function to perform DFS traversal
    public void dfs(int v, List<Integer>[] adj, 
                    boolean[] visited, 
                    List<Integer> result) {
        
        // Mark current node as visited
        visited[v] = true;

        // Store node in result
        result.add(v);

        // Traverse all neighbours
        for (int u : adj[v]) {
            if (!visited[u]) {
                dfs(u, adj, visited, result);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Number of vertices
        int V = 5;

        // Adjacency list
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        adj[0].addAll(Arrays.asList(1, 2));
        adj[1].addAll(Arrays.asList(0, 3));
        adj[2].addAll(Arrays.asList(0, 4));
        adj[3].add(1);
        adj[4].add(2);

        // Visited array
        boolean[] visited = new boolean[V];

        // Result list
        List<Integer> result = new ArrayList<>();

        // Create object
        Solution sol = new Solution();

        // Run DFS from node 0
        sol.dfs(0, adj, visited, result);

        // Print traversal
        for (int x : result) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
