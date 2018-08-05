import java.util.*;
class TopologicalSort
{
    private int V;   // No. of vertices
    private ArrayList<Integer> adj[]; // Adjacency List
    /**
     * @param  v Number of vertices
     */
    TopologicalSort(int v)
    {
        V = v;
        adj = new ArrayList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new ArrayList<>();
    }
    // Function to add an edge into the graph
    /**
     * @param v Source vertex
     * @param w Destination vertex
     */
    public void addEdge(int v,int w)
    { 
        adj[v].add(w);//Unidirectional, as topological sort is meaningless for bidirectional/undirected graphs
    }
    // A recursive function used by topologicalSort
    private void topologicalSortUtil(int v, boolean visited[], Stack stack)
    {       
        visited[v] = true;// Marking the current node as visited.
        int i=0;  
        Iterator<Integer> itr = adj[v].iterator();// Recur for all the vertices adjacent to current vertex
        while (itr.hasNext())
        {
            i = itr.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
        stack.push(v);
    }   
    public void topologicalSort()// The function to do Topological Sort. It uses recursive topologicalSortUtil(). 
    {
        if(isCyclic())
        {
            System.out.println("Sorry, Topological Sort is possible only for Directed Acyclic Graphs");
            return;
        }       
        System.out.println("Following is a Topological sort of the given graph: ");
        Stack stack = new Stack();//Stack to hold contents of Sort
        boolean visited[] = new boolean[V];        
        for (int i = 0; i < V; i++)
            visited[i] = false; 
        // Calling the recursive helper function to store Topological Sort starting from all vertices one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);
        // Printing contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
        System.out.println();//Tidy printing
    }
    /**
     * @param i Current vertex
     * @param visited checks if this vertex has been visited
     * @param recStack Checks if this vertex is in current recursion stack
     */
    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack)//Utilised by isCyclic() 
    {         
        // Mark the current node as visited and part of recursion stack
        if (recStack[i])
            return true;//Condition for cycle
        if (visited[i])
            return false; //No need to continue DFSing on visoted node so return          
        visited[i] = true;
        recStack[i] = true;
        List<Integer> children = adj[i];         
        for (int c: children)
            if (isCyclicUtil(c, visited, recStack))
                return true;                
        recStack[i] = false;//Clear the vertex from current recursion stack
        return false;//If we reached here, this vertex can't be part of any cycle
    }
    /*Method for checking if graph is acyclic, as only then Topological sort is possible*/
    private boolean isCyclic() 
    {         
        // Marking all the vertices as not visited and not part of recursion stack
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
                 
        // Calling the recursive helper function to detect cycle in different DFS trees
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;
        return false;
    }
    // Driver method for interactive printing. Creates a simple graph for checking the correctness
    public static void main(String args[])
    {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.topologicalSort();//The output is 5 4 2 3 1 0, which is a valid topological sort of the graph
    }
}
