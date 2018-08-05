import java.util.*;
class MaxFlowDinic
{
    private int V,level[];
    private ArrayList<Edge> adj[];
    MaxFlowDinic(int v)
    {
        V = v;
        adj = new ArrayList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new ArrayList<>();
        level = new int[V];
    }
    /**
     * @param u Start vertex
     * @param v end vertex
     * @param C Capacity of edge
    */
    void addEdge(int u, int v, double C)
    {
        Edge a = new Edge(v, 0, C, adj[v].size());// Forward edge : 0 flow and C capacity       
        Edge b = new Edge(u, 0, 0, adj[u].size());// Back edge : 0 flow and 0 capacity
        adj[u].add(a);
        adj[v].add(b); // reverse edge
    }
    /**
     * @param s=Source vertex
     * @param t=Sink Vertex
     */
    private boolean bfs(int s,int t)
    {
        for (int i = 0 ; i < V ; i++)
            level[i] = -1;
        level[s] = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty())
        {
            s = queue.poll();
            Iterator<Edge> i = adj[s].listIterator();
            while (i.hasNext())
            {
                Edge n = i.next();
                if (level[n.v]<0&&n.flow<n.cap)
                {
                    level[n.v] = level[s]+1;
                    queue.add(n.v);
                }
            }
        }
        return (level[t]>0) ;
    }
    /** A DFS based function to send flow after BFS has
     * figured out that there is a possible flow and
     * constructed levels. This function called multiple
     * times for a single call of BFS.
     * @param flow Current flow send by parent function call
     * @param start To keep track of next edge to be explored; start[i] stores  count of edges explored from i.
     * @param u Current vertex
     * @param t Sink 
     */
    private double sendFlow(int u, double flow, int t, int start[])
    {
        // Sink reached
        if (u == t)
            return flow;

        // Traverse all adjacent edges one -by - one.
        for (  ; start[u] < adj[u].size(); start[u]++)
        {
            // Pick next edge from adjacency list of u
            Edge e = adj[u].get(start[u]); 
            if (level[e.v] == level[u]+1 && e.flow < e.cap)
            {
                // find minimum flow from u to t
                double curr_flow = Math.min(flow, e.cap - e.flow);
                double temp_flow = sendFlow(e.v, curr_flow, t, start);
                // flow is greater than zero
                if (temp_flow > 0)
                {
                    // add flow  to current edge
                    e.flow += temp_flow;
                    // subtract flow from reverse edge
                    // of current edge
                    adj[e.v].get(e.index).flow -= temp_flow;
                    return temp_flow;
                }
            }
        }
        return 0;
    }
    /**
     * @param s=Source vertex
     * @param t=Sink Vertex
     */
    double DinicMaxflow(int s, int t)
    {
        // Corner case
        if (s == t)
        {
            return -1;
        }
        double total = 0;  // Initialize result
        // Augment the flow while there is path from source to sink
        while (bfs(s, t))
        {
            // store how many edges are visited
            // from V { 0 to V }
            int start[] = new int[V+1];
            // while flow is not zero in graph from S to D
            while (true)
            {
                double flow = sendFlow(s, Integer.MAX_VALUE, t, start);
                if(flow<=0)
                    break;
                total += flow;
            }
        }
        return total;
    }
    public static void main(String args[])
    {
        MaxFlowDinic obj = new MaxFlowDinic(0);//Dummy object to access the non-static methods
        obj.go();//The expected output is 23.0
    }
    private void go()
    {
        MaxFlowDinic g = new MaxFlowDinic(6);
        //Sample example. In order to use this class, user should create an object, populte the graph via addEdge() method and call the DinicMaxFlow() method on it
        g.addEdge(0, 1, 16 );
        g.addEdge(0, 2, 13 );
        g.addEdge(1, 2, 10 );
        g.addEdge(1, 3, 12 );
        g.addEdge(2, 1, 4 );
        g.addEdge(2, 4, 14);
        g.addEdge(3, 2, 9 );
        g.addEdge(3, 5, 20 );
        g.addEdge(4, 3, 7 );
        g.addEdge(4, 5, 4);
        System.out.println(" Maximum flow is: "+g.DinicMaxflow(0, 5));    
    }
}
class Edge
{
    double flow,cap;
    int index,v;
     /**
         * @param a end vertex
         * @param b current flow
         * @param c capacity of edge
         * @param d index of reverse edge for easy lookup
     */
   Edge(int a,double b,double c,int d)
   {
         v = a;
         flow = b;
         cap = c;
         index = d;
    }
}    
