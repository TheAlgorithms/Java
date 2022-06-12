package com.thealgorithms.datastructures.graphs;

/**
 * Java program for Hamiltonian Cycle (https://en.wikipedia.org/wiki/Hamiltonian_path).
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public class HamiltonianCycle {

	private int V, pathCount;
	private int[] path;
	private int[][] graph;

	/**
	 * Find hamiltonian cycle for given graph G(V,E)
	 *
	 * @param graph Adjacency matrix of a graph G(V, E) 
	 * for which hamiltonian path is to be found
	 * @return Array containing hamiltonian cycle else returns 1D array with value -1.
	 */
	public int[] findHamiltonianCycle(int[][] graph){

		this.V = graph.length;
		this.path = new int[this.V];

		//Initialize path array with -1 value
		for(int i=0 ; i<this.path.length ; i++) {
			this.path[i] = -1;
		}

		this.graph = graph;        

		try{            
			this.path[0] = 0;
			this.pathCount = 1;            

			solve(0);

			for(int i=0 ; i<this.path.length ; i++) {
				this.path[i] = -1;
			}

		}

		catch (Exception e){

		}
		return path;
	}

	/** function to find paths recursively **/
	/**
	 * Find paths recursively from given vertex
	 *
	 * @param vertex Vertex from which path is to be found
	 */
	public void solve(int vertex) throws Exception{

		if (this.graph[vertex][0] == 1 && this.pathCount == this.V) {

			throw new Exception("Solution found");

		}

		/** all vertices selected but last vertex not linked to 0 **/

		if (this.pathCount == this.V) {

			return;
		}



		for (int v = 0; v < this.V; v++){

			/** if connected **/

			if (this.graph[vertex][v] == 1 ){

				/** add to path **/            
				this.path[this.pathCount++] = v;    

				/** remove connection **/            
				this.graph[vertex][v] = 0;
				this.graph[v][vertex] = 0;



				/** if vertex not already selected  solve recursively **/
				if (!isPresent(v)) {

					solve(v);
				}



				/** restore connection **/
				this.graph[vertex][v] = 1;
				this.graph[v][vertex] = 1;

				/** remove path **/
				this.path[--this.pathCount] = -1;                    

			}

		}

	}    

	/** function to check if path is already selected **/
	/**
	 * Check if path is already selected
	 *
	 * @param vertex Starting vertex 
	 */
	public boolean isPresent(int vertex){

		for (int i = 0; i < pathCount - 1; i++) {
			if (path[i] == vertex) {
				return true;
			}
		}

		return false;                

	}

}
