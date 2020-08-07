package com.dataStructures.graphs;

/**
 * Problem : Given a graph find it's minimum spanning tree.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class BoruvkaMST {

	
	/**
	 * Implements the Disjoint-set (or Union Find) data structure.
	 * Each set is represented as a tree. It's nodes contain the elements of 
	 * the set with the root element being the sets representative.
	 * For example given the elements 1,2,4,5
	 * we define the sets   {1}, {2}, {4}, {5}
	 * parent: {<1,-1>, <2,-1>,<4,-1>, <5,-1>}
	 * where the value -1 indicates the root of the tree.
	 * 
	 * union(1,4) : parent: {<1,-1>, <2,-1>, <4,1>, <5,-1>}
	 * 1 2 5        sets  : {1,4}, {2}, {5}
	 * |
	 * 4
     *
	 * union(5,2) : parent: {<1,-1>, <2,5>, <4,1>, <5,-1>}
	 * 1  5         sets  : {1,4}, 5:{5,2}
	 * |  |
	 * 4  2

	 * union(4,5) : parent: {<1,-1>, <2,5>, <4,1>, <5,1>}
	 *   1          sets  : {1,4,5,2}
	 *  / \
	 * 4   5
	 *     |
	 *     2
    */
	private class UnionFind {
		private HashMap<Integer, Integer> parent; 
		// <Set's representative:the root of the tree, Set of Vertices>
		HashMap<Integer, ArrayList<Integer>> sets; 
	
		/**
		 * For each vertex u we define a set as:
		 * set = {u} 
		 * parent[u] = -1 : u is the root of the tree
		 * @param vertices : The set of vertices (V)
		 */
		public UnionFind (Set<Integer> vertices) {
			parent = new HashMap<>();
			sets = new HashMap<>();
		
			for(int v : vertices) {
				parent.put(v, -1);
			
				ArrayList<Integer> v_set = new ArrayList<>();
				v_set.add(v);
				sets.put(v, v_set);
			}
		}
		
		public int numberOfSets () {
			return sets.size();
		}
		
	  /**
	   * Follows the chain of parent pointers from x up the tree until 
	   * it reaches the root element, whose parent is -1. 
	   * @param x: A vertex of the graph
	   * @return The root element of the tree
	   */
		public int find (int x) {
			if (parent.get(x) == -1)
				return x;
			else 
				return find(parent.get(x));
		}
		
		/**
		 * @param x, y vertices
		 * @return true if they don't belong to the same set
		 */
		public boolean differentSets(int x, int y) {
			return ( find(x) != find(y) );
		}
	
		/**
		 * Merges the set that contains x with the set that contains y.
		 * @param x, y : vertices
		 * @return true if x and y didn't previously belonged to the same set 
		 * so their sets are now merged.
		 */
		public boolean union (int x, int y) {
			int parX= find(x);          // the root of x's tree
			int parY= find(y);          // the root of y's tree
			if (parX != parY) {         // x and y don't belong to the same set
				parent.put(parY, parX); // root of y's tree = root of x's tree
				
				//merge the sets
				sets.get(parX).addAll(sets.get(parY)) ; 
				sets.remove(parY);   //delete the set that contained y
			
				return true; // merge performed
			}
			return false; //x and y belonged to the same set
		}
	}
/*_____________________________________________________________________*/	
	
	/**
	 * Represents a pair of vertices.
	 */
	private static class Pair {
		int u, v;
		public Pair (int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
	
	/** 
	 * Represents a graph as an adjacency list. 
	 */
	static class Graph {
		
		private HashMap<Integer, HashMap<Integer, Double> > graph;
		private boolean isUndirected;
		
		/**
		 * @param isUndirected: true if (u,v) is the same as (v,u)
		 */
		public Graph(boolean isUndirected) {
			graph = new HashMap<>() ;
			this.isUndirected = isUndirected;
		}
		
		/**
		 * Adds the edges (u,v) -and (v,u) if graph is undirected- to the graph.
		 * @param u, v : Two vertices connected by an edge.
		 * @param weight of the edge (u,v)
		 */
		public void addEdge(int u, int v, double weight) {
			addEdge1(u,v, weight);
			if (isUndirected)
				addEdge1(v, u, weight);
		}
		private void addEdge1(int u, int v, double weight) {
			if (!graph.containsKey(u)) 
				graph.put(u, new HashMap<>());
			graph.get(u).put( v, weight);
		}

		/**
		 * @return The set of the vertices (V)
		 */
		public Set<Integer> getVertices() {
			return graph.keySet();
		}
		
		/**
		 * @param u : A vertex of the graph.
		 * @return The list of neighboring vertices of the given vertex u.
		 */
		public HashMap<Integer, Double> getNeighbors(int u) {
			return graph.get(u);
		}
		
		/**
		 * @param u,v vertices
		 * @return true if the graph contains the edge (u,v)
		 */
		public boolean contains(int u, int v) {
			try {
				return graph.get(u).containsKey(v);
			}catch (NullPointerException e) {
				return false; }
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
					weightSum += graph.get(u).get(v);
					System.out.println( "("+u + ", " + v + ") : " + graph.get(u).get(v));
			}}
			System.out.println("Total Weight = " + weightSum+"\n______________________");
			return weightSum;
		}
	}
/*_____________________________________________________________________*/
	
	private Graph graph;
	private Graph mst; //Output of the algorithm
		
	
	public BoruvkaMST(Graph graph) {
		this.graph = graph;
		//mst is set as directed since we want to include 
		//the edge (u,v) once (not (v,u) too)
		mst = new Graph(false);
	}
	
	/**
	 * Implements Boruvka's algorithm.
	 * @param mst as a graph
	 */
	public Graph runBoruvka () {
		
		UnionFind uf = new UnionFind (graph.getVertices()) ;
		ArrayList<Pair> toMerge = new ArrayList<>();
		
		//while there are more than one set of vertices,
		//we must choose the minimun edges that connect the sets
		while (uf.numberOfSets() > 1) { 
			
			//for every set
			for (ArrayList<Integer> set : uf.sets.values()) { 
				//find the edge with the minimum weight
				Pair minEdge = new Pair(-1, -1);
				double minWeight = Double.MAX_VALUE;
				
				//for each vertex u on this set
				for (int u : set) { 
					HashMap<Integer, Double> neighbors = graph.getNeighbors(u);
					for( int v : neighbors.keySet() ) {
					  //if weight of edge (u,v) < min and u,v don't belong to the same set
						if ( neighbors.get(v) < minWeight && uf.differentSets(u, v)) {
							minEdge = new Pair(u, v);
							minWeight = neighbors.get(v); 
					}}
				}
				
				//if we haven't already added (v,u) to the mst
				if (! mst.contains(minEdge.v, minEdge.u) ) {
					mst.addEdge(minEdge.u, minEdge.v, minWeight);
					toMerge.add(minEdge); 
				}
			}
			//For the next iteration we must merge the sets that contain u and v
			//for every added edge (u,v)
			for(Pair edge : toMerge)
				uf.union(edge.u, edge.v);
			
		}
		return mst;
	}

}
