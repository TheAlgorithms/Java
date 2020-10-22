//https://en.wikipedia.org/wiki/Bipartite_graph
import java.util.*;
class Solution{
//finding if bipartite or not using DFS algo
	boolean dfs(int root,ArrayList<ArrayList<Integer>>adjacent,int[]visited,int[]color,int c)
	{
		visited[root]=1;
		color[root]=c;
		for(int i=0;i<adjacent.get(root).size();i++)
		{
			int v=adjacent.get(root).get(i);
			if(visited[v]==0)
			{	if(dfs(v,adjacent,visited,color,~c)==false){return false;}

			}
			else if(visited[v]==1 && color[root]==color[v]){return false;}
		}return true;
	}

}
//main class
public class BiPartite{
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		//arraylist for storing the graph
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(n + 1);
        for (int a = 0; a <= n; a++) {
            graph.add(new ArrayList<Integer>());
}
//adding edges in the graph
        for (int i = 0; i < m; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        int []visited=new int[n+1];
        int[]color=new int[n+1];
        int root=in.nextInt();
        
        Solution obj=new Solution();
        //printing if the graph is bipartite or not- true if bipartite and false if not
        System.out.println(obj.dfs(root,graph,visited,color,1));
	}
}