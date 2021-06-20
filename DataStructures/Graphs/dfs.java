import java.util.*;
import java.io.*;
class Dfs_algo
{
static void dfsUtil(int path_matrix[][] , int current , int nodes , int visited[])
 {
   visited[current]=0;
   System.out.println("Visiting Node: "+ current);
   for(int i =0;i<nodes;i++)
   {
    if(path_matrix[current][i]==1 && visited[i]==-1)
    {
      dfsUtil(path_matrix , i , nodes , visited);
    }
   }
}
static void dfs(int nodes , int path_matrix[][])
{
  int visited[]= new int[nodes];
  Arrays.fill(visited,-1);
  for(int i =0;i<nodes;i++)
  {
    if(visited[i]==-1)
    {
      dfsUtil(path_matrix , i , nodes , visited);
    }
  }
}

public static void main(String args[])throws IOException
{
  //int nodes =4;
 // path_matrix[][]=[{0,1,1,0},{1,0,1,0},{0,1,0,1},{0,0,1,0}]
 dfs(nodes , path_matrix);
}
}
