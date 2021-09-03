import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph {
	private ArrayList<ArrayList<Pair>> g;	//ArrayList for storing adjacency list for a directed graph
	private int vertexSetSize;	//Number of vertices
	private int edgeSetSize;	//Number of vertices
	final long UNREACHABLE_DISTANCE = (long) 1e14;
	
	/*
	* @brief
	* A parameterized constructor which takes number of vertices n and number of edges m as inputs
	*/
	public Graph(int n, int m) {
		vertexSetSize = n;
		edgeSetSize = m;
		g = new ArrayList<>();
		for(int i = 0;i < n; i++) {
			g.add(new ArrayList<Pair>());
		}
	}

	//A function for getting edge weight between two vertices
	public long getEdgeWeight(int u, int v) {
		return g.get(u).get(v).getDist();
	}
	
	//A helper function for reading user input
	public void readGraph(Scanner scanner) {
		System.out.println("Enter end points and edge weights of " + edgeSetSize + " edges:");
		int u,v,w;
		
		for(int i = 0; i < edgeSetSize; i++) {
			u = scanner.nextInt();
			v = scanner.nextInt();
			w = scanner.nextInt();
			/**
				Assigning edge from u to v for the directed graph. 
				If we intend to work with undirected graphs, we also need to mark an edge from v to u. Rest of the things remain the same.
			 */
			g.get(u - 1).add(new Pair(v - 1, w));
		}
	}
	
	/*
		@param s: Source vertex
		@brief
		   This function uses Dijkstra's algorithm for calculating minimum distance.

		@description
		 * This function appplies Dijkstra's algorithm for finding minimum distance from a source vertex to all other vertices,
		   given that all the weights are non-negative. Minimum distance values are stored in dist[] array, processed[] array
		   is used to check if minimum distance to a vertex has already been determined.

		 * PriorityQueue pq is constructed as a min heap for extracting the vertex which has minimum dist value.

		 * For every vertex u extracted from pq, we relax all the outgoing edges from vertex u iff processed[u] is false. 

		 * Consider w(u,v) = weight of the edge from u to v, dist(u) = current minimum distance from source vertex to vertex u. 
		   Relaxation procedure is defined as follows:
		   if(dist(v) > dist(u) + w(u,v)){
		   		dist(v) = dist(u) + w(u,v)
				add v to the priority queue.
			}
		 @see https://www.freecodecamp.org/news/dijkstras-shortest-path-algorithm-visual-introduction/ for reference
		
	*/
	public long[] dijkstra(int s) {
		long[] dist = new long[vertexSetSize];
		boolean[] processed = new boolean[vertexSetSize];
		
		for(int i = 0; i < vertexSetSize; i++) {
			dist[i] = UNREACHABLE_DISTANCE;
			processed[i] = false;
		}
		
		dist[s] = 0;
		//PriorityQueue declared with custom comparator. See Pair.java file for reference.
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Pair());

		//Source vertex added to pq
		pq.add(new Pair(s,0));
		
		while(!pq.isEmpty()) {
			//Vertex extracted from pq
			Pair p = pq.poll();
			int source = p.getNode();

			//Relax edges if minimum distance has not already been determined
			if(processed[p.getNode()]) continue;
			
			else {
				processed[source] = true;
				
				for(int i = 0; i < g.get(source).size(); i++) {
					int destination = g.get(source).get(i).getNode();
					//Relax the edge from source to destination
					if(!processed[destination] && dist[source] != UNREACHABLE_DISTANCE && dist[destination] > (dist[source] + g.get(source).get(i).getDist())) {
						dist[destination] = p.getDist() + g.get(source).get(i).getDist();
						pq.add(new Pair(destination, dist[destination]));
					}
				}
			}
		}
		return dist;
	}

	public void printMinDist(long[] dist, int s){
		System.out.println("Minimum distance from:");
		for(int i = 0; i < dist.length; i++) {
			if(dist[i] != UNREACHABLE_DISTANCE) 
				System.out.println((s) + " - " + (i + 1) + " = " + dist[i]);
		}
	}
}
