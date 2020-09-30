package Hacktoberfest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Kahns {
  static  ArrayList<Integer> arr[];
    public static void main(String args[])
  {
      Scanner s=new Scanner(System.in);
      //Enter the number of vertices -The vertices will be like 0,1,2,___n-1
      System.out.println("Enter the numer of vetrices: ");
      int n=s.nextInt();

      arr=new ArrayList[n];
      for(int i=0;i<n;i++)
      {
          arr[i]=new ArrayList<>();
      }
      System.out.println("Enter the numer of edges: ");


      //Enter the number of edges
      int e=s.nextInt();
      //The graph should be directed for this algorithm to work

      int indegree[]=new int[n];
      //
      System.out.println("Enter e edges  ");

      for(int i=0;i<e;i++)
      {
          //for edge src-->dest
          int src=s.nextInt();
          int dest=s.nextInt();
          arr[src].add(dest);
           indegree[dest]++;
      }

      Queue<Integer> q=new LinkedList<>();
      for(int i=0;i<n;i++)
      {
          if(indegree[i]==0)
              q.add(i);
      }

      int c=0;
      ArrayList<Integer> topo=new ArrayList<>();
      while(!q.isEmpty())
      {
          int v=q.remove();
          topo.add(v);
          c++;
          for(Integer child:arr[v])
          {
              indegree[child]--;
              if(indegree[child]==0)
                  q.add(child);
          }



      }

      if(c<n)
          System.out.println("The graph has cycle");

      else
      {
          for(Integer x:topo)
              System.out.print(x+" ");
      }


  }
}
