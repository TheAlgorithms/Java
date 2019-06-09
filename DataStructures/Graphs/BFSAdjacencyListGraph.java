

import java.util.LinkedList;
import java.util.Queue;

/**
 * Adjacency List implementation of Graph
 * @author Abhishek
 *
 */
public class BFSAdjacencyListGraph {
	LinkedList<Vertex> adjacencyList;

	public BFSAdjacencyListGraph() {
		adjacencyList = new LinkedList<Vertex>();
	}

	/**
	 * Add vertex in the graph.
	 * 
	 * @param vertex vertex instance
	 */
	public void addVertex(Vertex vertex) {
		adjacencyList.add(vertex);
	}

	/**
	 * Add neighbor for the vertex at vertexIndex in the adjacency list.
	 * 
	 * @param vertexIndex   - index of vertex where neighbor to be added.
	 * @param neighborIndex - index of neighboring vertex.
	 */
	public void addNeighbor(int vertexIndex, int neighborIndex) {
		adjacencyList.get(vertexIndex).addNeighbor(neighborIndex);
	}

	/**
	 * BFS all the connected vertices.
	 *
	 * @param startVertex starting vertex
	 * @param visited     store all the visited vertices
	 */
	private void bfs(int startVertex, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startVertex);
		while (!queue.isEmpty()) {
			int vertexIndex = queue.poll();
			if (!visited[vertexIndex]) {
				Vertex vertex = adjacencyList.get(vertexIndex);
				System.out.print(vertex.getVertexName() + "-->");
				visited[vertexIndex] = true;
				// Traverse all neighbors and add to queue
				for (int neighborIndex : vertex.getNeighbours()) {
					queue.add(neighborIndex);
				}
			}
		}
	}

	/**
	 * BFS Runner for traversing all the disconnected vertices.
	 */
	public void bfs() {
		boolean[] visited = new boolean[adjacencyList.size()];
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false) {
				bfs(i, visited);
			}
		}
	}

	/**
	 * Main method.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		//1st run
		BFSAdjacencyListGraph graph = new BFSAdjacencyListGraph();
		graph.addVertex(new Vertex("California")); // 0th index
		graph.addVertex(new Vertex("Detroit")); // 1st index
		graph.addVertex(new Vertex("New York")); // 2nd index
		graph.addVertex(new Vertex("Texas")); // 3rd index
		graph.addNeighbor(0, 1);
		graph.addNeighbor(0, 2);
		graph.addNeighbor(1, 2);
		graph.addNeighbor(2, 0);
		graph.bfs();
		// 2nd run 
		System.out.println();
		graph = new BFSAdjacencyListGraph();
		graph.addVertex(new Vertex("One"));
		graph.addVertex(new Vertex("Two"));
		graph.addVertex(new Vertex("Three"));
		graph.addVertex(new Vertex("Four"));
		graph.addVertex(new Vertex("Five"));
		graph.addVertex(new Vertex("Six"));
		graph.addVertex(new Vertex("Seven"));
		graph.addVertex(new Vertex("Eight"));
		graph.addVertex(new Vertex("Nine"));
		graph.addNeighbor(0, 1);
		graph.addNeighbor(1, 5);
		graph.addNeighbor(0, 4);
		graph.addNeighbor(5, 2);
		graph.addNeighbor(4, 7);
		graph.addNeighbor(4, 8);
		graph.addNeighbor(3, 6);
		graph.bfs();
	}
}

/**
 * Class represent vertex in a Graph.
 *
 * @author Abhishek
 */
class Vertex {
	private String vertexName;

	private LinkedList<Integer> neighbours;

	public Vertex(final String vertexName, final LinkedList<Integer> neighbours) {
		this.vertexName = vertexName;
		this.neighbours = neighbours;
	}

	public Vertex(final String vertexName) {
		this.vertexName = vertexName;
		this.neighbours = new LinkedList<Integer>();
	}

	public String getVertexName() {
		return vertexName;
	}

	public void setVertexName(final String vertexName) {
		this.vertexName = vertexName;
	}

	public LinkedList<Integer> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(final LinkedList<Integer> neighbours) {
		this.neighbours = neighbours;
	}

	/**
	 * Add neighbors in the list.
	 *
	 * @param neighborIndex index of neighbor in adjacency list
	 */
	public void addNeighbor(int neighborIndex) {
		this.neighbours.add(neighborIndex);
	}
}
