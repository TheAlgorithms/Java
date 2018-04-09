import java.util.Scanner;

public class Dijkstra {
	int V,E;
	int graph[][];
	
	Dijkstra(int V, int E){
		this.V = V;
		this.E = E;
		this.graph = new int[V][V];
		for(int i = 0; i < this.V; i++)
			for(int j=0; j< this.V; j++)
				if(i != j)
					graph[i][j] = Integer.MAX_VALUE;

	}
	
	public void addEdge(int src, int dst, int weight) {
		this.graph[src][dst] = weight;
	}
	
	public void print(int dist[]) {
		System.out.print("\nVertex  Distance\n");
		for(int i = 0; i < V; i++){
			if(dist[i] != Integer.MAX_VALUE)
				System.out.println(i+"\t"+dist[i]);
			else
				System.out.println(i+"\tINF");
		}
	}
	
	public int minDistance(int mdist[], boolean vset[], int V){
		int minVal = Integer.MAX_VALUE, minInd = -1;
		for(int i=0; i<V;i++)
			if(!vset[i] && mdist[i] < minVal){
			minVal = mdist[i];
			minInd = i;
			}
				
		return minInd;
	}
	
	
	public void getShortestPath(int src) {
		int V = this.V;
		int mdist[] = new int[V];
		boolean vset[] = new boolean[V];
		
		//Initialize mdist and vset
		for(int i=0;i<V;i++) {
			mdist[i] = Integer.MAX_VALUE;
			vset[i]=false;
		}
		mdist[src] = 0;
		
		for(int i=0;i< V-1;i++) {
			int u = this.minDistance(mdist, vset, V);
			vset[u] = true;
			
			for(int v=0; v<V; v++)
				if(!vset[v] && graph[u][v]!=Integer.MAX_VALUE && mdist[u] + graph[u][v] < mdist[v])
					mdist[v] = mdist[u] + graph[u][v];
			
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
		Dijkstra graph = new Dijkstra(V,E);
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
