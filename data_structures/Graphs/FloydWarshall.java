import java.util.Scanner;

public class FloydWarshall {
	int V,E;
	int graph[][];
	
	FloydWarshall(int V, int E){
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
	
	public void print(int dist[][]) {
		System.out.println("Shortest distance matrix given by Floyd Warshall:");
		for(int i = 0; i < this.V; i++){
			for(int j=0; j< this.V; j++){

			if(dist[i][j] != Integer.MAX_VALUE)	
				System.out.print(dist[i][j]+"\t");
			else
				System.out.print("INF\t");
			}
			System.out.print("\n");
		}
	}
	
	
	public void getShortestPath() {
		int V = this.V;
		int dist[][] = new int[V][V];
		
		for(int i = 0; i < this.V; i++)
			for(int j=0; j< this.V; j++)
				dist[i][j] = this.graph[i][j];
		
		//Calculate distances
		for(int k=0; k<V; k++)
		//Choose an intermediate vertex

			for(int i=0; i<V; i++)	
			//Choose a source vertex for given intermediate

				for(int j=0; j<V; j++)
				//Choose a destination vertex for above source vertex

					if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j])
					//If the distance through intermediate vertex is less than direct edge then update value in distance array
						dist[i][j] = dist[i][k] + dist[k][j];
		this.print(dist);
	}
	

	public static void main(String[] args) {
		int V,E;
		int src,dst,weight;
		Scanner console = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		V = console.nextInt();
		System.out.print("Enter number of edges: ");
		E = console.nextInt();
		FloydWarshall graph = new FloydWarshall(V,E);
		for(int i=0; i<E; i++){
			System.out.print("\nEdge "+(i+1)+" \nEnter source: ");
			src = console.nextInt();
			System.out.print("Enter destination: ");
			dst = console.nextInt();
			System.out.print("Enter weight: ");
			weight = console.nextInt();
			graph.addEdge(src, dst, weight);
		}
		graph.getShortestPath();
		console.close();

	}

}
