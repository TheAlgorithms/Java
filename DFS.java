import java.util.*;

// Data structure to store graph edges
class Edge
{
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

// Class to represent a graph object
class Graph
{
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (int i = 0; i < edges.size(); i++)
		{
			int src = edges.get(i).source;
			int dest = edges.get(i).dest;

			adjList.get(src).add(dest);
			adjList.get(dest).add(src);
		}
	}
}

class DFS
{
	// Perform iterative DFS on graph g starting from vertex v
	public static void iterativeDFS(Graph graph, int v, boolean[] discovered)
	{
		// create a stack used to do iterative DFS
		Stack<Integer> stack = new Stack<>();

		// push the source node into stack
		stack.push(v);

		// run till stack is not empty
		while (!stack.empty())
		{
			// Pop a vertex from stack
			v = stack.pop();

			// if the vertex is already discovered yet, ignore it
			if (discovered[v])
				continue;

			// we will reach here if the popped vertex v
			// is not discovered yet. We print it and process
			// its undiscovered adjacent nodes into stack
			discovered[v] = true;
			System.out.print(v + " ");

			// do for every edge (v -> u)
			List<Integer> adj = graph.adjList.get(v);
			for (int i = adj.size() - 1; i >= 0; i--)
			{
				int u = adj.get(i);
				if (!discovered[u]) {
					stack.push(u);
				}
			}
		}
	}

	// Iterative Java implementation of Depth first search
	public static void main(String[] args)
	{
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(
				// Notice that node 0 is unconnected node
				new Edge(1, 2), new Edge(1, 7), new Edge(1, 8),
				new Edge(2, 3), new Edge(2, 6), new Edge(3, 4),
				new Edge(3, 5), new Edge(8, 9), new Edge(8, 12),
				new Edge(9, 10), new Edge(9, 11)
				// , new Edge(6, 9) // introduce cycle
		);

		// Set number of vertices in the graph (0-12)
		final int N = 13;

		// create a graph from edges
		Graph graph = new Graph(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// Do iterative DFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph
		for (int i = 0; i < N; i++) {
			if (!discovered[i]) {
				iterativeDFS(graph, i, discovered);
			}
		}
	}
}
