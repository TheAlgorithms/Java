import java.util.*;
import java.io.*;

/**
 * Breadth First Search Algorithm
 * Used to find all nodes reachable from a source and also the distances from source
 * theta(|V|+|E|) time complexity
 * @author Aftaab
 */
public class BreadthFirstSearch {
    static int level[];
    static int currentLevel=0;
    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter no. of nodes(vertices)");
        int V=in.nextInt();
        System.out.println("Enter your graph as an Adjacency List");
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<ArrayList<Integer>>();
        adjList=input(V);
        driver(0,V,adjList);
           
        
    }
    public static void printLevel(int[] level){
        System.out.println("Distances of all vertices from source(0) are(-1 indicates unreachable vertices):");
        for(int i=0;i<level.length;i++){
        System.out.println("Vertex "+i+": "+level[i]);
    }
    }
    
    public static void driver(int s, int V, ArrayList<ArrayList<Integer>> adjList){
        level=new int[V];
        for(int i=0;i<V;i++)
            level[i]=-1;
        level=doBFS(s,V, adjList, level);
        printLevel(level);
    
    }
    
    public static int[] doBFS(int s, int V, ArrayList<ArrayList<Integer>> adjList,int[] level){
        boolean visited[]=new boolean[V];
        Queue<Integer> queue=new LinkedList<Integer>();
        
        visited[s]=true;
        queue.add(s);
        if(level[s]==-1)
        level[s]=currentLevel;
        
        while(queue.size()!=0){
            s=queue.poll();
            Iterator<Integer> i=adjList.get(s).listIterator();
            currentLevel++;
            while(i.hasNext()){
                int n=i.next();
                if(!visited[n]){
                    queue.add(n);
                    visited[n]=true;
                    
                    level[n]=currentLevel;
                }
            }
       
        }
        return level;
    }
  
    public static ArrayList<ArrayList<Integer>> input(int V){
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<ArrayList<Integer>>(V);
        Scanner in=new Scanner(System.in);
        for(int i=0;i<V;i++){
            System.out.print("Vertex "+i+":");
            String x=in.nextLine();
            String temp[]=new String[x.split(" ").length];
            temp=x.split(" ");
            int temp1[]=new int[temp.length];
            ArrayList temp2=new ArrayList<Integer>();
            for(int y=0;y<temp1.length;y++){
                if(!temp[y].equals("")){
                temp1[y]=Integer.parseInt(temp[y]);
                temp2.add(temp1[y]);
                }
            }
            if(temp2!=null)
            adjList.add(temp2);
            
        }
        return adjList;
    }
}