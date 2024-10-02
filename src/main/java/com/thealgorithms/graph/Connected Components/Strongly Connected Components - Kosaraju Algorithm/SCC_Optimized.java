import java.util.*;
public class SCC_Optimized
{

    public void btrack(HashMap<Integer,List<Integer>> adj_list,int visited[],Stack<Integer> dfs_calls_nodes,int current_node)
    {
        visited[current_node] = 1;
        for(int i = 0;i < adj_list.get(current_node).size();i++)
        {
            if(visited[adj_list.get(current_node).get(i)] == -1)
            {
                btrack(adj_list, visited, dfs_calls_nodes, current_node);
            }
        }
        dfs_calls_nodes.add(current_node);
    }
    public void btrack2(HashMap<Integer,List<Integer>> adj_rev_list,int visited[],int current_node,List<Integer> new_scc)
    {
        visited[current_node] = 1;
        for(int i = 0;i < adj_rev_list.get(current_node).size();i++)
        {
            if(visited[adj_rev_list.get(current_node).get(i)] == -1)
            {
                btrack2(adj_rev_list, visited, current_node,new_scc);
            }
        }
    }
    public int getOutput(HashMap<Integer,List<Integer>> adj_list,int N)
    {
        int visited[] = new int[N+1];
        Arrays.fill(visited,-1);
        Stack<Integer> dfs_calls_nodes = new Stack<Integer>();
        for(int i = 0;i < N;i++)
        {
            if(visited[i]==-1)
            {
            btrack(adj_list, visited,dfs_calls_nodes,i);
            }
        }
        System.out.println(dfs_calls_nodes);
        HashMap<Integer,List<Integer>> adj_rev_list = new HashMap<>();
        for(int i = 0;i < N;i++)
        {
            visited[i] = -1;
            adj_rev_list.put(i,new ArrayList<Integer>());
        }
        //Reversing the adjacency lists
        for(int i = 0;i < N;i++)
        {
            List<Integer> lists = adj_list.get(i);
            for(Integer num: lists)
            {
                List<Integer> list1 = adj_rev_list.get(num);
                list1.add(i);
                adj_rev_list.put(num,list1);
            }
        }
        //Popping out the stack in the descending order and counting the number of SCC
        int strongly_connected_components = 0;
        List<List<Integer>> sccs = new ArrayList<>();
        while(!dfs_calls_nodes.isEmpty())
        {
            int node = dfs_calls_nodes.pop();
            if(visited[node] == -1)
            {
                ++strongly_connected_components;
                List<Integer> new_scc = new ArrayList<>();
                new_scc.add(node);
                btrack2(adj_rev_list, visited, node,new_scc);
                sccs.add(new_scc);
            }
        }
        System.out.println(sccs.get(sccs.size()-1).size());
        return strongly_connected_components;
    }
    public static void main(String[] args) {
        Scanner rs = new Scanner(System.in);
        HashMap<Integer,List<Integer>> adj_list = new HashMap<>();
        int N = rs.nextInt();
        for(int i = 0;i < N;i++)
        {
            adj_list.put(i,new ArrayList<>());
        }
        for(int i = 0;i < N;i++)
        {
            System.out.println("Number of neighbour vertices");
            int n_vertices = rs.nextInt();
            for(int j = 0;j < n_vertices;j++)
            {
                int vertices = rs.nextInt();
                List<Integer> lists = adj_list.get(i);
                lists.add(vertices);
            }
        }
        System.out.println(adj_list);
        SCC_Optimized obj = new SCC_Optimized();
        System.out.print(obj.getOutput(adj_list,N));
        rs.close();
    }
}