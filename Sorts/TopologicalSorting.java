import java.util.*;
class Graph{
	int v;
	LinkedList<Integer> adj[];
	@SuppressWarnings("unchecked")
	Graph(int v){
		this.v=v;
		adj=new LinkedList[v];
		for(int i=0;i<v;i++)
			adj[i]=new LinkedList<>();
	}
}
class TopologicalSorting{
	public static void addEdge(Graph g,int u,int v){
		g.adj[u].add(v);
	}
	public static void toposortutil(Graph g,int node,boolean visit[],Stack st)
	{
		visit[node]=true;
		int i;
		Iterator<Integer>it=g.adj[node].iterator();
		while(it.hasNext())
		{
			i=it.next();
			System.out.println(i);
			if(!visit[i])
				toposortutil(g,i,visit,st);
		}
		System.out.println("node"+node);
		st.push(node);
	}
	public static void toposort(Graph g){
		Stack st=new Stack();
		boolean visit[]=new boolean[g.v];
		for(int i=0;i<g.v;i++)
			if(visit[i]==false)
				toposortutil(g,i,visit,st);
		while(st.empty()==false)
			System.out.println(st.pop()+" ");
	}
	public static void main(String[] args){
		 Graph g = new Graph(6); 
		TopologicalSorting tp=new TopologicalSorting();
		tp.addEdge(g,5, 2); 
        tp.addEdge(g,5, 0); 
        tp.addEdge(g,4, 0); 
        tp.addEdge(g,4, 1); 
        tp.addEdge(g,2, 3); 
        tp.addEdge(g,3, 1);
		tp.toposort(g);
	}
}
	