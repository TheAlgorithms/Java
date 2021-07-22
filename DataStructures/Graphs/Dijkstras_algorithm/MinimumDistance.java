import java.util.Scanner;

public class MinimumDistance {

	public static void main(String[] args) {
		int n, m;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of vertices: ");
		n = scanner.nextInt();
		System.out.println("Enter number of edges: ");
		m = scanner.nextInt();
		
		Graph graph = new Graph(n, m);
		graph.readGraph(scanner);
		
		int s;
		System.out.println("Enter source vertex:");
		s = scanner.nextInt();
		long[] minDist = graph.dijkstra(s-1);	//Assuming that the user considers vertex 1 as the first vertex
		graph.printMinDist(minDist, s);
	}

}
