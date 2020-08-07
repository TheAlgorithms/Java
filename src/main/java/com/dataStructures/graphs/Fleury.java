package com.dataStructures.graphs;

/** 
 *  Problem: Given a connected and undirected graph G(V,E)
 *  find the Eulerian cycle or trail.
 *  A Eulerian graph has a Eulerian cycle  (start == terminus vertex)
 *  A semi Eulerian graph has a Eulerian trail (start != terminus vertex).
 *  Time complexity: O(|E|^2) where |E| is the number of edges on the graph.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Fleury {
	
	/** 
	 * Represents an undirected graph as an adjacency list. 
	 */
	static class Graph {
		
		private HashMap<Integer, ArrayList<Integer>> graph;
		private int E; //number of edges
		public Graph() {
			graph = new HashMap<>() ;
			E = 0;
		}
		/** 
		 * @return The number of edges (|E|) of the graph.
		 */
		public int getE() {
			return E;
		}
		/**
		 * @return The set of the vertices (V)
		 */
		public Set<Integer> getVertices() {
			return graph.keySet();
		}
		//--------------------------------------
		/**
		 * Adds the edges (u,v) and (v,u) to the graph.
		 * @param u, v : Two vertices connected by an edge.
		 */
		public void addEdge(int u, int v) {
			++E;
			addEdge1(u,v);
			addEdge1(v, u);
		}
		private void addEdge1(int u, int v) {
			if (!graph.containsKey(u)) 
				graph.put(u, new ArrayList<>());
			graph.get(u).add(v);
		}
		//---------------------------------------
		/**
		 * Removes the edges (u,v) and (v,u) from the graph.
		 * @param u, v : Two vertices connected by an edge.
		 */
		public void deleteEdge(int u, int v) {
			deleteEdge1(u, v);
			deleteEdge1(v, u);
		}
		private void deleteEdge1(int u, Integer v) {
			try {
				graph.get(u).remove(v);
			}catch(NullPointerException e) { 
				//edge (u,v) doesn't exist
				return; }
		}
		//---------------------------------------
		/**
		 * @param u : A vertex of the graph.
		 * @return The number of neighboring vertices of the given
		 * vertex u (it's degree)
		 */
		public int vertexDegree(int u) {
			return graph.get(u).size();
		}
		/**
		 * @param u : A vertex of the graph.
		 * @return The list of neighboring vertices of the given vertex u.
		 */
		public ArrayList<Integer> getNeighbors(int u) {
			return graph.get(u);
		}
	}
	
	
	/**
	 * Represents an edge of the graph as a pair of neighboring vertices.
	 */
	class Edge{
		int previous, v;
		public Edge(int previous, int v) {
			this.previous = previous;
			this.v = v;
		}
		/**
		 * @param previous, v : Two vertices. The terminal points of the edge.
		 * @return true if given edge equals to this edge.
		 */
		public boolean equals(int previous, int v) {
			return (previous == this.previous && v == this.v) ; 
		}
	}
//____________________________________________________________
		
	private Graph graph;

	public Fleury(Graph graph) {
		this.graph = graph;	
	}
	
	
	/** 
	 *  Implements the main body of the algorithm.
	 *  @return A sequence of vertices that make up the Eulerian
	 *  cycle (or trail). If such cycle or trail doesn't exist on given
	 *  graph, returns null.
	 */
	public ArrayList<Integer> runFleury() {
		
		int currentVertex = chooseStartVertex();;
		if (currentVertex == -1)
			return null;
		
		ArrayList<Integer> path = new ArrayList<>();
		path.add(currentVertex);
		
		while (path.size() <= graph.getE()) { //while you haven't used every edge
			
			ArrayList<Integer> neighbors = graph.getNeighbors(currentVertex);
			for(int nextVertex : neighbors) {
				//move to a neighboring vertex who isn't connected with the current through a bridge
				if ( edgeNotBridge(currentVertex, nextVertex) ) {
					path.add(nextVertex);
					graph.deleteEdge(currentVertex, nextVertex); //delete the edge you used from the graph
					currentVertex = nextVertex;
					break;
			}}
		}
		return path;
	}
	

	/** 
	 *  Finds the start vertex for the algorithm. If graph not Eulerian set as -1.
	 *  If the graph has a vertex with odd degree we must start from there.
	 *  If the graph has no vertices with odd degree we can start from any vertex.
	 *  If the graph has more than two vertices with odd degree is not Eulerian graph.
	 */
	private int chooseStartVertex() {
		int startVertex = -1;
		Set<Integer> vertices = graph.getVertices();
		int oddDegree = 0;
		
		for(int u : vertices) {
			if (graph.vertexDegree(u) % 2 != 0) {
				startVertex = u;
				++oddDegree;
		}}
		
		if (oddDegree == 0) {
			startVertex = vertices.iterator().next();
		}else if (oddDegree != 2)
			startVertex = -1; 
		return startVertex;
	}
	
	
	/** 
	 *  Determines whether a given edge (i,j) is a bridge or not.
	 *  Bridge is an edge whose deletion increases the number of connected components
	 *  (meaning it's removal would disconnect the graph).
	 *  If we can detect a cycle i-> j ->...-> (k!=j) -> i using DFS, 
	 *  that means the given edge is not a bridge.
	 *  More info: https://en.wikipedia.org/wiki/Bridge_(graph_theory)  
	 * @param i,j : two vertices connected by an edge.
	 * @return true if given edge is not a bridge.
	 */
	private boolean edgeNotBridge(int i, int j) {
		if (graph.vertexDegree(i) == 1 )
			return true;
		
		//vertices already visited by the DFS
		Set<Integer> visited = new HashSet<>();
		//DFS stack
		Stack<Edge> agenda = new Stack<>();
		
		visited.add(i);
		agenda.push(new Edge(i,j)) ;
		
		while(!agenda.empty()) {
			
			Edge currentEdge = agenda.pop();
			//we can not reuse the given edge
			if (!currentEdge.equals(j,i)) {
				if (currentEdge.v == i)
					return true; //cycle detected
			
				//if you haven't visited the current vertex v before
				//then add it's neighbors to the stack to visit them next
				if (!visited.contains(currentEdge.v)) {
					visited.add(currentEdge.v);
					ArrayList<Integer> neighbors = graph.getNeighbors(currentEdge.v);
					for (int vertex : neighbors) 
						agenda.push(new Edge(currentEdge.v, vertex));				
				}
			}
		}
		return false;
	}
	
	/**
	 * Prints the solution.
	 * @param path The sequence of vertices that make up the Eulerian
	 * cycle (or trail). The output of the algorithm.
	 */
	public void printResults(ArrayList<Integer> path) {

		if (path != null) {
			if(path.get(0) == path.get(path.size()-1))  //start==terminus vertex
				System.out.println("Eulerian Graph. Eulerial cycle:");
			else
				System.out.println("Semi-Eulerian Graph. Eulerial trail:");
			for(int i=0 ; i<path.size()-1 ; ++i) 
				System.out.print(path.get(i) + " -> ");
			System.out.println(path.get(path.size()-1));
		}else {
			System.out.println("Graph is not Eulerian.");
		}	
		System.out.println("_________________________________");
	}

}
