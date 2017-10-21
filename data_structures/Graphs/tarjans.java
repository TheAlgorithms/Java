// A Java program to find articulation points in an undirected graph
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Graph {

	// This class represents an undirected graph using adjacency list
	// representation

	private int V; // No. of vertices

	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];
	int time = 0;
	static final int NIL = -1;

	// Constructor
	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list.
		adj[w].add(v); // Add v to w's list
	}

	// A recursive function that find articulation points using DFS
	// u --> The vertex to be visited next
	// visited[] --> keeps tract of visited vertices
	// disc[] --> Stores discovery times of visited vertices
	// parent[] --> Stores parent vertices in DFS tree
	// ap[] --> Store articulation points
	void APUtil(int u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]) {

		// Count of children in DFS Tree
		int children = 0;

		// Mark the current node as visited
		visited[u] = true;

		// Initialize discovery time and low value
		disc[u] = low[u] = ++time;

		// Go through all vertices aadjacent to this
		Iterator<Integer> i = adj[u].iterator();
		while (i.hasNext()) {
			int v = i.next(); // v is current adjacent of u

			// If v is not visited yet, then make it a child of u
			// in DFS tree and recur for it
			if (!visited[v]) {
				children++;
				parent[v] = u;
				APUtil(v, visited, disc, low, parent, ap);

				// Check if the subtree rooted with v has a connection to
				// one of the ancestors of u
				low[u] = Math.min(low[u], low[v]);

				// u is an articulation point in following cases

				// (1) u is root of DFS tree and has two or more chilren.
				if (parent[u] == NIL && children > 1)
					ap[u] = true;

				// (2) If u is not root and low value of one of its child
				// is more than discovery value of u.
				if (parent[u] != NIL && low[v] >= disc[u])
					ap[u] = true;
			}

			// Update low value of u for parent function calls.
			else if (v != parent[u])
				low[u] = Math.min(low[u], disc[v]);
		}
	}

	void DFS(int u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]) {
		int children = 0;
		visited[u] = true;
		disc[u] = low[u] = ++time;
		Iterator<Integer> i = adj[u].iterator();
		while (i.hasNext()) {
			int v = i.next();
			if (!visited[v]) {
				children++;
				parent[v] = u;
				DFS(v, visited, disc, low, parent, ap) ;
				low[u] = Math.min(low[u], low[v]) ;
				if (parent[u] == NIL && children >= 2) {
					ap[u] = true ;
				}
				if (parent[u] != NIL && low[v] >= disc[u]) {
					ap[u] = true ;
				}

			} else if (v != parent[u]) {
				low[u] = Math.min(low[u], disc[v]);
			}
		}

	}

	// The function to do DFS traversal. It uses recursive function APUtil()
	void AP() {
		// Mark all the vertices as not visited
		boolean visited[] = new boolean[V];
		int disc[] = new int[V];
		int low[] = new int[V];
		int parent[] = new int[V];
		boolean ap[] = new boolean[V]; // To store articulation points

		// Initialize parent and visited, and ap(articulation point)
		// arrays
		for (int i = 0; i < V; i++) {
			parent[i] = NIL;
			visited[i] = false;
			ap[i] = false;
		}

		// Call the recursive helper function to find articulation
		// points in DFS tree rooted with vertex 'i'
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				APUtil(i, visited, disc, low, parent, ap);

		// Now ap[] contains articulation points, print them
		for (int i = 0; i < V; i++)
			if (ap[i] == true)
				System.out.print(i + " ");
	}

	// Driver method
	public static void main(String args[]) {
		// Create graphs given in above diagrams
		System.out.println("Articulation points in first graph ");
		Graph g1 = new Graph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		g1.AP();
		System.out.println();

		System.out.println("Articulation points in Second graph");
		Graph g2 = new Graph(4);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.AP();
		System.out.println();

		System.out.println("Articulation points in Third graph ");
		Graph g3 = new Graph(7);
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		g3.addEdge(2, 0);
		g3.addEdge(1, 3);
		g3.addEdge(1, 4);
		g3.addEdge(1, 6);
		g3.addEdge(3, 5);
		g3.addEdge(4, 5);
		g3.AP();
	}
}
