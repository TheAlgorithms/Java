import java.util.*;
public class SCC_Naive
{
    //Implementing the conventional DFS
    public boolean isPath(HashMap<Integer,List<Integer>> adj_list,int N,int visited[],int current_node,int destination)
    {
        if(current_node == destination)
        {
            return true;  
        }
        List<Integer> lists = adj_list.get(current_node);
        int lists_size = lists.size();
        visited[current_node] = 1;
        for(int i = 0;i < lists_size;i++)
        {
            if(visited[lists.get(i)] == 0)
            {
                if(isPath(adj_list,N,visited,lists.get(i),destination))
                {
                    return true;
                }

            }
        }
        return false;  
    }
    public Set<List<Integer>> getOutput(HashMap<Integer,List<Integer>> adj_list,int N)
    {
        Set<List<Integer>> SCC_lists = new HashSet<>();
        for(int i = 1;i <= N;i++)
        {
            List<Integer> neighbour_vertices = new ArrayList<>();
            for(int j = 1;j <= N;j++)
            {
                if(i != j)
                {
                    int visited[] = new int[N+1];
                    if(isPath(adj_list, N,visited,i,j))
                    {
                        visited = new int[N+1];
                        if(isPath(adj_list, N, visited, j, i))
                        {
                            neighbour_vertices.add(j);
                        }
                    }
                }
                else
                {
                    neighbour_vertices.add(i);

                }
            }
            SCC_lists.add(new ArrayList<>(neighbour_vertices));  
        }
        return SCC_lists;
    }
    public static void main(String[] args) {
        Scanner rs = new Scanner(System.in);
        HashMap<Integer,List<Integer>> adj_list = new HashMap<>();
        int N = rs.nextInt();
        for(int i = 1;i <= N;i++)
        {
            adj_list.put(i,new ArrayList<>());
        }
        for(int i = 1;i <= N;i++)
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
        SCC_Naive obj = new SCC_Naive();
        System.out.print(obj.getOutput(adj_list,N));
        rs.close();
    }
}