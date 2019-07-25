package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * A Java program for Kruscal's Minimum Spanning Tree (MST) algorithm.
 * adjacency matrix representation of the graph
 */
public class KruscalMST {
	
	//The amount of the edges in this graph
	private final int edgesize = 5;
	
	/**
	 * Create the minimum spanning tree by Kruscal's algorithm. 
	 * 
	 * @param edges a List of the edge
	 */
	public void createMinSpanTreeKruskal(List<Edge> edges) {
		
		//Define an array, the index is the start of the edge, the value is the end of the edge.
	    int[] parent = new int[edgesize];
	    for (int i = 0; i < edgesize; i++) {
	        parent[i] = 0;
	    }

	    int sum = 0;
	    for (Edge edge : edges) {
	    	//Find the final connected node of the start node and the end node.
	        int start = find(parent, edge.getStart());
	        int end = find(parent, edge.getEnd());

	        //Judge if the start and the end is the same. If yes, it is a circle.
	        if (start != end) {

	            //If not
	            parent[start] = end;
	            System.out.println("The start is " + start + ", the end is "
	            		+ end + ", the weight is " + edge.getWeight());
	            sum += edge.getWeight();
	        }
	    }
	    System.out.println("The sum weight of the MST is " + sum);
	}
	
	/**
	 * Find the last node in the set.
	 * 
	 * @param parent the array of all the parents
	 * 
	 * @param index the index you wanted to get
	 * 
	 * @return the final result you want to get
	 */
	private int find(int parent[], int index) {
	    while (parent[index] > 0) {
	        index = parent[index];
	    }
	    return index;
	}

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<Edge>();
        Edge edge1 = new Edge(0, 1, 2); edges.add(edge1);
        Edge edge2 = new Edge(0, 3, 6); edges.add(edge2);
        Edge edge3 = new Edge(1, 2, 3); edges.add(edge3);
        Edge edge4 = new Edge(1, 3, 8); edges.add(edge4);
        Edge edge5 = new Edge(1, 4, 5); edges.add(edge5);
        Edge edge6 = new Edge(2, 4, 7); edges.add(edge6);
        Edge edge7 = new Edge(3, 4, 9); edges.add(edge7);
        
        new KruscalMST().createMinSpanTreeKruskal(edges);
    }
}
class Edge {

    private final int start;
    private final int end;
    private final int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    
    public int getEnd() {
		return end;
	}
    
    public int getStart() {
		return start;
	}
    
    public int getWeight() {
		return weight;
	}
}
