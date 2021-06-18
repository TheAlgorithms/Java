package Others;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
@author: Shashank Sah (https://github.com/sahshank01)
*/

/*
 * This algorithm is using Adjacency list representation of graph for better performance and less time complexity.
 * Time complexity : O(V+E) 
 * V=number of vertices
 * E=number of edges
*/

/*
 * Vertex class stores the info about vertex like name, vertex which are adjacent to it and whether it is visited or not
*/
class Vertex {
	private String name;
	private boolean visited;
	private List<Vertex> neighbours;
	
	public Vertex(String name) {
		this.name=name;
		this.neighbours = new ArrayList<>();
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Vertex> getNeighbours() {
		return neighbours;
	}

	public void addNeighbours(Vertex neighbour) {
		this.neighbours.add(neighbour);
	}

	@Override
	public String toString() {
		return name;
	}
}

/*
 * TopologicalOrder class is responsible for finding the topological order for the given graph using `depth first search` algorithm
*/
public class TopologicalOrder {
private Stack<Vertex> stack;
	
	public TopologicalOrder() {
	this.stack = new Stack<Vertex>();
	}
	
	public void dfs(Vertex vertex) {
		vertex.setVisited(true);
		for(Vertex v:vertex.getNeighbours()) {
			if(!v.isVisited()) {
				dfs(v);
			}
		}
		stack.push(vertex);
	}
	
	public Stack<Vertex> getStack(){
		return this.stack;
	}
	
	public static void main(String[] args) {
		TopologicalOrder topologicalOrdering = new TopologicalOrder();
		
		//Creating Adjacency list
		List<Vertex> graph = new ArrayList<>();
		graph.add(new Vertex("0"));
		graph.add(new Vertex("1"));
		graph.add(new Vertex("2"));
		graph.add(new Vertex("3"));
		graph.add(new Vertex("4"));
		graph.add(new Vertex("5"));
		
		
		//adding adjacent nodes corresponding to each node
		graph.get(2).addNeighbours(graph.get(3));
		
		graph.get(3).addNeighbours(graph.get(1));
		
		graph.get(4).addNeighbours(graph.get(0));
		graph.get(4).addNeighbours(graph.get(1));
		
		graph.get(5).addNeighbours(graph.get(0));
		graph.get(5).addNeighbours(graph.get(2));
		
		
		// The loop will help to reach out disconnected components of the graph, if exist
		for(int i=0;i<graph.size();i++) {
			if(graph.get(i).isVisited()==false) {
				topologicalOrdering.dfs(graph.get(i));
			}
		}
		
		
		//printing the sequence of topological sort
		Stack<Vertex> stack = topologicalOrdering.getStack();
		while(!stack.isEmpty()) {
			Vertex vertex = stack.pop();
			System.out.print(vertex+" ");
		}
	}
}
