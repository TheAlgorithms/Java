import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Adjacency Matrix Implementation of Prim's Minimum Spanning Tree algorithm
 * 
 * @author nikhilbarar
 */

public class Prims {
	private boolean[] unsettled;
	private boolean[] settled;
	private int numberofvertices;
	private int[][] adjacencyMatrix;
	private int[] key;
	public static final int INFINITE = 999;
	private int[] parent;

	public Prims(int numberofvertices) {
		this.numberofvertices = numberofvertices;
		unsettled = new boolean[numberofvertices + 1];
		settled = new boolean[numberofvertices + 1];
		adjacencyMatrix = new int[numberofvertices + 1][numberofvertices + 1];
		key = new int[numberofvertices + 1];
		parent = new int[numberofvertices + 1];
	}

	public int getUnsettledCount(boolean[] unsettled) {
		int count = 0;
		for (int index = 0; index < unsettled.length; index++) {
			if (unsettled[index]) {
				count++;
			}
		}
		return count;
	}

	// Function to create the MST from the adjacency matrix
	public void primsAlgorithm(int[][] adjacencyMatrix) {
		int evaluationVertex;
		for (int source = 1; source <= numberofvertices; source++) {
			for (int destination = 1; destination <= numberofvertices; destination++) {
				this.adjacencyMatrix[source][destination] = adjacencyMatrix[source][destination];
			}
		}

		for (int index = 1; index <= numberofvertices; index++) {
			key[index] = INFINITE;
		}
		key[1] = 0;
		unsettled[1] = true;
		parent[1] = 1;

		while (getUnsettledCount(unsettled) != 0) {
			evaluationVertex = getMimumKeyVertexFromUnsettled();
			unsettled[evaluationVertex] = false;
			settled[evaluationVertex] = true;
			evaluateNeighbours(evaluationVertex);
		}
	}

	// A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
	private int getMimumKeyVertexFromUnsettled() {
		int min = Integer.MAX_VALUE;
		int node = 0;
		for (int vertex = 1; vertex <= numberofvertices; vertex++) {
			if (unsettled[vertex] && key[vertex] < min) {
				node = vertex;
				min = key[vertex];
			}
		}
		return node;
	}

	// Function to evaluate the neighbours of the current node in adjacency matrix
	public void evaluateNeighbours(int evaluationVertex) {
		for (int destinationvertex = 1; destinationvertex <= numberofvertices; destinationvertex++) {
			if (!settled[destinationvertex] && adjacencyMatrix[evaluationVertex][destinationvertex] != INFINITE) {
				if (adjacencyMatrix[evaluationVertex][destinationvertex] < key[destinationvertex]) {
					key[destinationvertex] = adjacencyMatrix[evaluationVertex][destinationvertex];
					parent[destinationvertex] = evaluationVertex;
				}
				unsettled[destinationvertex] = true;
			}
		}
	}

	// A utility function to print the constructed MST stored in parent[]
	public void printMST() {
		System.out.println("SOURCE  : DESTINATION = WEIGHT");
		for (int vertex = 2; vertex <= numberofvertices; vertex++) {
			System.out.println(parent[vertex] + "\t:\t" + vertex + "\t=\t" + adjacencyMatrix[parent[vertex]][vertex]);
		}
	}
	 
	// Function to construct and print MST for a graph represented using adjacency
	// matrix representation
	public static void main(String... arg) {
		int[][] adjacencyMatrix;
		int noOfVertices;
		Scanner scan = new Scanner(System.in);

		try {
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
						adjacencyMatrix[i][j] = INFINITE;
					}
				}
			}

			Prims prims = new Prims(noOfVertices);
			prims.primsAlgorithm(adjacencyMatrix);
			prims.printMST();

		} catch (InputMismatchException inputMismatch) {
			System.out.println("Wrong Input Format");
		}
		scan.close();
	}
}