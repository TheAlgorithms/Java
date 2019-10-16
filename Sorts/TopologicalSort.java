/*
 @author: Mrinal Pandey (https://github.com/mrinal-pandey)
*/

import java.util.*;

class TopologicalSort{
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		TopologicalSort ts = new TopologicalSort();
		
		//Number of nodes in the graph
		int N = in.nextInt();

		//Graph as adjacency matrix
		int[][] graph = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; ++i){
			for(int j = 1; j <= N; ++j){
				graph[i][j] = in.nextInt();
			}
		}
		
		//function to perform toplogical sort
		ts.sort(graph, N);
	}

	void sort(int[][] graph, int N){
		
		//list containing sorted vertices
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		
		boolean[] mark = new boolean[N + 1];
		for(int i = 1; i <= N; ++i){
			
			//find vertex with indegree 0
			int zeroDegVertex = findZeroDegVertex(graph, mark, N);
			
			//if a vertex with 0 indegree found
			if(zeroDegVertex != -1){

				//mark the vertex with 0 indegree as visited
				mark[zeroDegVertex] = true;

				//add it into the list of sorted elements
				sorted.add(zeroDegVertex);

				//remove all the edges from the vertex selected
				removeEdges(zeroDegVertex, graph, N);
			}
		}
		
		if(sorted.size() < N){
			System.out.println("No Topological sort possible");
		}else{
			System.out.println("Solution : " + sorted);
		}
	}

	//utility function to remove the edges starting from a node
	void removeEdges(int zeroDegVertex, int[][] graph, int N){
		for(int i = 1; i <= N; ++i){
			graph[zeroDegVertex][i] = 0;
		}
	}

	//utility function to find vertices with indegree 0
	int findZeroDegVertex(int[][] graph, boolean[] mark, int N){
		for(int i = 1; i <= N; ++i){
			int count = 0;
			for(int j = 1; j <= N; ++j){
				if(graph[j][i] == 0){
					++count;
				}
				if(count == N && mark[i] == false){
					return i;
				}
			}
		}
		return -1;
	}
}
