import java.util.*;

public class sccOptimized {

    public void btrack(HashMap<Integer, List<Integer>> adjList, int[] visited, Stack<Integer> dfsCallsNodes, int currentNode) {
        visited[currentNode] = 1;
        for (int i = 0; i < adjList.get(currentNode).size(); i++) {
            int neighbor = adjList.get(currentNode).get(i);
            if (visited[neighbor] == -1) {
                btrack(adjList, visited, dfsCallsNodes, neighbor);
            }
        }
        dfsCallsNodes.add(currentNode); // Add node after finishing DFS on all neighbors
    }

    public void btrack2(HashMap<Integer, List<Integer>> adjRevList, int[] visited, int currentNode, List<Integer> newScc) {
        visited[currentNode] = 1;
        newScc.add(currentNode); // Add node to the current SCC
        for (int i = 0; i < adjRevList.get(currentNode).size(); i++) {
            int neighbor = adjRevList.get(currentNode).get(i);
            if (visited[neighbor] == -1) {
                btrack2(adjRevList, visited, neighbor, newScc);
            }
        }
    }

    public int getOutput(HashMap<Integer, List<Integer>> adjList, int N) {
        int[] visited = new int[N];
        Arrays.fill(visited, -1);
        Stack<Integer> dfsCallsNodes = new Stack<>();

        // First DFS pass to fill the stack with the finishing times of nodes
        for (int i = 0; i < N; i++) {
            if (visited[i] == -1) {
                btrack(adjList, visited, dfsCallsNodes, i);
            }
        }

        System.out.println("Stack of nodes by finish time: " + dfsCallsNodes);

        // Reverse the graph
        HashMap<Integer, List<Integer>> adjRevList = new HashMap<>();
        for (int i = 0; i < N; i++) {
            adjRevList.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int neighbor : adjList.get(i)) {
                adjRevList.get(neighbor).add(i); // Reverse edge
            }
        }

        // Second DFS on the reversed graph
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

        // Print the found SCCs
        System.out.println("Strongly Connected Components: " + sccs);
        return stronglyConnectedComponents;
    }

    public static void main(String[] args) {
        Scanner rs = new Scanner(System.in);
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        int N = rs.nextInt(); // Number of nodes

        // Initialize adjacency list
        for (int i = 0; i < N; i++) {
            adjList.put(i, new ArrayList<>());
        }

        // Input graph data
        for (int i = 0; i < N; i++) {
            System.out.println("Number of neighbor vertices for node " + i + ": ");
            int nVertices = rs.nextInt();
            for (int j = 0; j < nVertices; j++) {
                int neighbor = rs.nextInt();
                adjList.get(i).add(neighbor);
            }
        }

        System.out.println("Adjacency list: " + adjList);

        sccOptimized obj = new sccOptimized();
        System.out.println("Number of SCCs: " + obj.getOutput(adjList, N));
        rs.close();
    }
}
