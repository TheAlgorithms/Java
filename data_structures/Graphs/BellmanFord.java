import java.util.Scanner;
import java.util.ArrayList;

public class BellmanFord {
	
	private class edge{
		int src,dst,weight;
	}
	
	int V,E;
	ArrayList<edge> graph = new ArrayList<edge>();
	
	BellmanFord(int V, int E){
		this.V = V;
		this.E = E;

	}
	
	public void addEdge(int src, int dst, int weight) { 
		edge newEdge = new edge();
		newEdge.src = src;
		newEdge.dst = dst;
		newEdge.weight = weight;
		this.graph.add(newEdge);
	}
	
	public void print(int dist[]) {
		System.out.print("\nVertex  Distance\n");
		for(int i = 0; i < V; i++)
			System.out.println(i+"\t"+dist[i]);
	}
	
	
	
	public void getShortestPath(int src) {
		int V = this.V;
		int mdist[] = new int[V];
		
		//Initialize mdist
		for(int i=0;i<V;i++)
			mdist[i] = Integer.MAX_VALUE;
		mdist[src] = 0;
		
		//Find distances
		for(int i=0; i<V-1; i++)
			for(int j=0; j<this.graph.size(); j++) {
				edge e = graph.get(j);
				int u = e.src;
				int v = e.dst;
				int w = e.weight;
				
				if(mdist[u]!=Integer.MAX_VALUE && mdist[u]+w<mdist[v])
					mdist[v] = mdist[u] + w;
				
			}

		this.print(mdist);
	}
	

	public static void main(String[] args) {
		int V,E,Gsrc;
		int src,dst,weight;
		Scanner console = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		V = console.nextInt();
		System.out.print("Enter number of edges: ");
		E = console.nextInt();
		BellmanFord graph = new BellmanFord(V,E);
		for(int i=0; i<E; i++){
			System.out.print("\nEdge "+(i+1)+" \nEnter source: ");
			src = console.nextInt();
			System.out.print("Enter destination: ");
			dst = console.nextInt();
			System.out.print("Enter weight: ");
			weight = console.nextInt();
			graph.addEdge(src, dst, weight);
		}
		System.out.print("\nEnter source for shortest paths: ");
		Gsrc = console.nextInt();
		graph.getShortestPath(Gsrc);
		console.close();
	}

}
