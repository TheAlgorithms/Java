import java.util.*;

/**
 * Adjacency Matrix Implementation of Kruskal's Minimum Spanning Tree algorithm
 * 
 * @author nikhilbarar
 *
 */

public class Kruskal {
	private List<Edge> edges;
	private int numberOfVertices;
	public static final int MAX_VALUE = 999;
	private int[] visited;
	private int[][] spanningTree;

	public Kruskal(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		edges = new LinkedList<>();
		visited = new int[this.numberOfVertices + 1];
		spanningTree = new int[numberOfVertices + 1][numberOfVertices + 1];
	}

	// Function to create and print MST using adjacency matrix
	public void kruskal(int[][] adjacencyMatrix) {
		boolean finished = false;
		for (int source = 1; source <= numberOfVertices; source++) {
			for (int destination = 1; destination <= numberOfVertices; destination++) {
				if (adjacencyMatrix[source][destination] != MAX_VALUE && source != destination) {
					Edge edge = new Edge();
					edge.sourcevertex = source;
					edge.destinationvertex = destination;
					edge.weight = adjacencyMatrix[source][destination];
					adjacencyMatrix[destination][source] = MAX_VALUE;
					edges.add(edge);
				}
			}
		}
		Collections.sort(edges, new EdgeComparator());
		CheckCycle checkCycle = new CheckCycle();
		for (Edge edge : edges) {
			spanningTree[edge.sourcevertex][edge.destinationvertex] = edge.weight;
			spanningTree[edge.destinationvertex][edge.sourcevertex] = edge.weight;
			if (checkCycle.checkCycle(spanningTree, edge.sourcevertex)) {
				spanningTree[edge.sourcevertex][edge.destinationvertex] = 0;
				spanningTree[edge.destinationvertex][edge.sourcevertex] = 0;
				edge.weight = -1;
				continue;
			}
			visited[edge.sourcevertex] = 1;
			visited[edge.destinationvertex] = 1;
			for (int i = 0; i < visited.length; i++) {
				if (visited[i] == 0) {
					finished = false;
					break;
				} else {
					finished = true;
				}
			}
			if (finished)
				break;
		}
		System.out.println("The spanning tree is ");
		for (int i = 1; i <= numberOfVertices; i++)
			System.out.print("\t" + i);
		System.out.println();
		for (int source = 1; source <= numberOfVertices; source++) {
			System.out.print(source + "\t");
			for (int destination = 1; destination <= numberOfVertices; destination++) {
				System.out.print(spanningTree[source][destination] + "\t");
			}
			System.out.println();
		}
	}

	// The main function to read adjacency matrix and construct MST using Kruskal's
	// algorithm
	public static void main(String... arg) {
		int adjacencyMatrix[][];
		int noOfVertices;

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of vertices");
		noOfVertices = scan.nextInt();
		adjacencyMatrix = new int[noOfVertices + 1][noOfVertices + 1];

		System.out.println("Enter the Weighted Matrix for the graph");
		for (int i = 1; i <= noOfVertices; i++) {
			for (int j = 1; j <= noOfVertices; j++) {
				adjacencyMatrix[i][j] = scan.nextInt();
				if (i == j) {
					adjacencyMatrix[i][j] = 0;
					continue;
				}
				if (adjacencyMatrix[i][j] == 0) {
					adjacencyMatrix[i][j] = MAX_VALUE;
				}
			}
		}
		Kruskal kruskalAlgorithm = new Kruskal(noOfVertices);
		kruskalAlgorithm.kruskal(adjacencyMatrix);
		scan.close();
	}
}

//A class to represent a graph edge
class Edge {
	int sourcevertex;
	int destinationvertex;
	int weight;
}

//Comparator class used for sorting edges 
//based on their weight
class EdgeComparator implements Comparator<Edge> {
	@Override
	public int compare(Edge edge1, Edge edge2) {
		if (edge1.weight < edge2.weight)
			return -1;
		if (edge1.weight > edge2.weight)
			return 1;
		return 0;
	}
}

//Class to check if a cycle is formed with the spanning tree formed so far.
class CheckCycle {
	private Stack<Integer> stack;
	private int[][] adjacencyMatrix;

	public CheckCycle() {
		stack = new Stack<Integer>();
	}

	// Function to detect cycle in graph
	public boolean checkCycle(int[][] adjacencyMatrix, int source) {
		boolean cyclepresent = false;
		int noOfNodes = adjacencyMatrix[source].length - 1;

		this.adjacencyMatrix = new int[noOfNodes + 1][noOfNodes + 1];
		for (int sourcevertex = 1; sourcevertex <= noOfNodes; sourcevertex++) {
			for (int destinationvertex = 1; destinationvertex <= noOfNodes; destinationvertex++) {
				this.adjacencyMatrix[sourcevertex][destinationvertex] = adjacencyMatrix[sourcevertex][destinationvertex];
			}
		}

		int visited[] = new int[noOfNodes + 1];
		int element = source;
		int i = source;
		visited[source] = 1;
		stack.push(source);

		while (!stack.isEmpty()) {
			element = stack.peek();
			i = element;
			while (i <= noOfNodes) {
				if (adjacencyMatrix[element][i] >= 1 && visited[i] == 1) {
					if (stack.contains(i)) {
						cyclepresent = true;
						return cyclepresent;
					}
				}
				if (adjacencyMatrix[element][i] >= 1 && visited[i] == 0) {
					stack.push(i);
					visited[i] = 1;
					adjacencyMatrix[element][i] = 0; // Mark as labelled
					adjacencyMatrix[i][element] = 0;
					element = i;
					i = 1;
					continue;
				}
				i++;
			}
			stack.pop();
		}
		return cyclepresent;
	}
}