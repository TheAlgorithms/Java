import java.util.*;
class TopologicalSort{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		TopologicalSort ts = new TopologicalSort();
		int N = in.nextInt();
		int[][] graph = new int[N + 1][N + 1];
		for(int i = 1; i <= N; ++i){
			for(int j = 1; j <= N; ++j){
				graph[i][j] = in.nextInt();
			}
		}
		ts.sort(graph, N);
	}
	void sort(int[][] graph, int N){
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		boolean[] mark = new boolean[N + 1];
		for(int i = 1; i <= N; ++i){
			int zeroDegVertex = findZeroDegVertex(graph, mark, N);
			if(zeroDegVertex != -1){
				mark[zeroDegVertex] = true;
				sorted.add(zeroDegVertex);
				removeEdges(zeroDegVertex, graph, N);
			}
		}
		if(sorted.size() < N){
			System.out.println("No Topological sort possible");
		}else{
			System.out.println("Solution : " + sorted);
		}
	}
	void removeEdges(int zeroDegVertex, int[][] graph, int N){
		for(int i = 1; i <= N; ++i){
			graph[zeroDegVertex][i] = 0;
		}
	}
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
