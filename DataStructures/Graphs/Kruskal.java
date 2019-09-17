import java.util.*;

class Graph{
	int vertex1;
	int vertex2;
	int weight;
}

class Kruskal{

	public static void main(String[] args){
		Kruskal kruskalObj = new Kruskal();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of Vertices:");
		int nVertices = in.nextInt();
		System.out.print("Enter the number of Edges:");
		int nEdges = in.nextInt();
		System.out.print("Input:\nVertex-1 Vertex-2 Weight [for all edges where weight != 0]\n");
		Graph[] graph = new Graph[nEdges];
		for(int i = 0; i < nEdges; i++){
			graph[i] = new Graph();
			graph[i].vertex1 = in.nextInt();
			graph[i].vertex2 = in.nextInt();
			graph[i].weight = in.nextInt();
		}
		kruskalObj.sort(graph, nEdges);
		kruskalObj.kruskalAlgo(graph, nVertices);
	}

	int findParent(int vertex, int[] parent){
		int i = vertex;
		while(parent[i] != -1){
			i = parent[i];
		}
		return i;
	}

	int isCycleFormed(Graph temp, int[] parent){
		int parent1 = findParent(temp.vertex1, parent);
		int parent2 = findParent(temp.vertex2, parent);
		if(parent1 == parent2){
			return 1;
		}
		else{
			parent[parent1] = parent2;
			return 0;
		}
	}

	void kruskalAlgo(Graph[] graph, int N){
		int[] parent = new int[N];
		Graph temp = new Graph();
		ArrayList<Graph> selected = new ArrayList<Graph>();
		int i = 0, j = 0, cycleFlag = 0;
		
		for(i = 0; i < N; i++){
			parent[i] = -1;
		}
		
		i = 0;
		j = 0;
		
		while(i < N - 1){
			cycleFlag = 0;
			temp = graph[j];
			cycleFlag = isCycleFormed(temp, parent);
			if(cycleFlag == 0){
				selected.add(graph[j]);
				i++;
			}
			j++;
		}
		display(selected);
	}
	
	void sort(Graph[] graph, int N){
		for(int i = 0; i < N; i++){
			int min = Integer.MAX_VALUE;
			int minPos = -1;
			for(int j = i; j < N; j++){
				if(graph[j].weight < min){
					min = graph[j].weight;
					minPos = j;
				}
			}
			Graph temp = new Graph();
			temp = graph[minPos];
			graph[minPos] = graph[i];
			graph[i] = temp;
		}
	}

	void display(ArrayList<Graph> selected){
		System.out.println("\nEdges selected:");
		for(int i = 0; i < selected.size(); i++){
			System.out.println(selected.get(i).vertex1 + "---" + selected.get(i).vertex2 + "\t" + selected.get(i).weight);
		}
	}
}
