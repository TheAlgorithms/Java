package com.dataStructures.graphs;


/**
 * Problem : Given a graph find it's minimum spanning tree.
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class ReverseDeleteMST {

	/** 
	 * Represents an undirected graph as an adjacency list. 
	 */
	static class Graph {
		
		private HashMap<Integer, HashMap<Integer, Double>> graph;
		private int E;
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
		public void addEdge(int u, int v, double weight) {
			++E;
			addEdge1(u, v, weight);
			addEdge1(v, u, weight);
		}
		private void addEdge1(int u, int v, double weight) {
			if (!graph.containsKey(u)) 
				graph.put(u, new HashMap<>());
			graph.get(u).put(v, weight);
		}
		//---------------------------------------
		/**
		 * Removes the edges (u,v) and (v,u) from the graph.
		 * @param u, v : Two vertices connected by an edge.
		 */
		public void deleteEdge(int u, int v) {
			--E;
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
		public HashMap<Integer, Double> getNeighbors(int u) {
			return graph.get(u);
		}
		
		/**
		 * Adds every edge of the graph to the maxheap
		 * @param edges maxheap used to sort the edges
		 */
		public void makeHeap (PriorityQueue<Edge> edges) {
			for(int u : graph.keySet()) 
				for(int v : graph.get(u).keySet()) 
					if (u < v)
						edges.add(new Edge(u, v, graph.get(u).get(v)));	
		}	
		
		/**
		 * Prints every edge of the graph as "(u,v) : weight"
		 * Can be used to print the resulting MST
		 * @param the total weight of the graph
		 */
		public double printGraph () {
			double weightSum = 0;
			for (int u : graph.keySet() ) {
				for (int v: graph.get(u).keySet() ) {
					if (u < v) {
						weightSum += graph.get(u).get(v);
						System.out.println( "("+u + ", " + v + ") : " + graph.get(u).get(v));
			}}}
			System.out.println("Total Weight = " + weightSum+"\n______________________");
			return weightSum;
		}
		
	}
/*__________________________________________________________________________*/	
	
	/**
	 * Represents a weighted edge (u,v) of the graph.
	 */
	static class Edge {
		int u, v;
		double weight;
		public Edge (int u, int v, double weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		/**
		 * @param u, v : Two vertices. The terminal points of the edge.
		 * @return true if given edge equals to this edge.
		 */
		public boolean equals(int u, int v) {
			return (u == this.u && v == this.v) ; 
		}
	}
	
	private Graph graph;
	
	private PriorityQueue<Edge> edges; //maxheap used to sort the edges
	private class ElementComparatorMax implements Comparator<Edge> { 
        // Overriding compare() method of Comparator  
        // for descending order of edge weight
        public int compare(Edge e1, Edge e2) { 
            if (e1.weight < e2.weight) 
                return 1; 
            else if (e1.weight > e2.weight) 
                return -1; 
            return 0; 
        } 
    } 
	
	/**
	 * Given a graph delete non bridge edges on descending weight order
	 * until the graph becomes a tree. The remaining edges make up the
	 * minimum spanning tree.
	 */
	public ReverseDeleteMST(Graph graph) {
		this.graph = graph;
		edges = new PriorityQueue<>(new ElementComparatorMax());
		graph.makeHeap(edges);
		
		int V = graph.getVertices().size(); //number of vertices
		
		while ( graph.getE() > V-1) { //while graph is not a tree
			Edge edge = edges.poll();
			if ( edgeNotBridge(edge.u, edge.v) ) 
				graph.deleteEdge(edge.u, edge.v);
		}
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
		agenda.push(new Edge(i,j, 0)) ;
		
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
					HashMap<Integer, Double> neighbors = graph.getNeighbors(currentEdge.v);
					for (int vertex : neighbors.keySet()) 
						agenda.push(new Edge(currentEdge.v, vertex, 0));				
				}
			}
		}
		return false;
	}
	

}

