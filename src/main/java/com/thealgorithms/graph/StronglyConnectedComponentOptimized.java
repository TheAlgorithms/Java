import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StronglyConnectedComponentOptimized {

    public void btrack(HashMap<Integer, List<Integer>> adjList, int[] visited, Stack<Integer> dfsCallsNodes, int currentNode) {
        visited[currentNode] = 1;
        for (int i = 0; i < adjList.get(currentNode).size(); i++) {
            int neighbor = adjList.get(currentNode).get(i);
            if (visited[neighbor] == -1) {
                btrack(adjList, visited, dfsCallsNodes, neighbor);
            }
        }
        dfsCallsNodes.add(currentNode);
    }

    public void btrack2(HashMap<Integer, List<Integer>> adjRevList, int[] visited, int currentNode, List<Integer> newScc) {
        visited[currentNode] = 1;
        newScc.add(currentNode);
        for (int i = 0; i < adjRevList.get(currentNode).size(); i++) {
            int neighbor = adjRevList.get(currentNode).get(i);
            if (visited[neighbor] == -1) {
                btrack2(adjRevList, visited, neighbor, newScc);
            }
        }
    }

    public int getOutput(HashMap<Integer, List<Integer>> adjList, int n) {
        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        Stack<Integer> dfsCallsNodes = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (visited[i] == -1) {
                btrack(adjList, visited, dfsCallsNodes, i);
            }
        }

        HashMap<Integer, List<Integer>> adjRevList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjRevList.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int neighbor : adjList.get(i)) {
                adjRevList.get(neighbor).add(i);
            }
        }

        Arrays.fill(visited, -1);
        int stronglyConnectedComponents = 0;
        List<List<Integer>> sccs = new ArrayList<>();

        while (!dfsCallsNodes.isEmpty()) {
            int node = dfsCallsNodes.pop();
            if (visited[node] == -1) {
                List<Integer> newScc = new ArrayList<>();
                btrack2(adjRevList, visited, node, newScc);
                sccs.add(newScc);
                stronglyConnectedComponents++;
            }
        }

        return stronglyConnectedComponents;
    }

}
