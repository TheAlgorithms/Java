import java.util.*;
class Graph{

    static HashMap<String,List<Node>> adj; //creating a adjacency List {source ->destinations}
    
    public Graph(){
        this.adj=new HashMap<>(); //constructor initializes the adjacency list
    }
    
    static class Node{ //Node class to store the destination place and the distance frome source to destination
	String dest;
	int dist;

	Node(String dest,int dist){
            this.dest=dest;
	    this.dist=dist;
	}
    }

    public static void insert(String src,String dest,int dist){
           insert(src,new Node(dest,dist));  //Undirected weighted graph
	   insert(dest,new Node(src,dist));
    }

    private static void insert(String src,Node n){
         if(adj.isEmpty() || !adj.containsKey(src)){
               adj.put(src,new ArrayList<>());
	 }
	 adj.get(src).add(n);
    } 

    public static void BFS(String src){ // BREADTH-FIRST-SEARCH
          if(adj.isEmpty()) return;

	  HashSet<String> set=new HashSet<>();
	  Queue<String> q=new LinkedList<>();
	  q.offer(src);
	  set.add(src);
	  
	  while(!q.isEmpty()){
             String node=q.poll();
	     System.out.print(node+" ");
	     List<Node> neighbours=adj.get(src);
	     if(neighbours!=null){
                for(Node neighbour:neighbours){
                    if(neighbour!=null && !set.contains(neighbour.dest)){
                        q.offer(neighbour.dest);
                        set.add(neighbour.dest);
		    }
		}
	     }
	  }
    }

}

class Main{
  public static void main(String[] args){
      Graph graph=new Graph();              //instantiating Graph by making an object graph of GRAPH
      graph.insert("DELHI","MUMBAI",990);
      graph.insert("MUMBAI","PUNE",100);
      graph.insert("PUNE","GOA",140);
      graph.insert("BANGALORE","PUNE",453);
      graph.insert("DELHI","PUNE",1056);
      graph.insert("DELHI","HYDERABAD",1099);
      graph.insert("VISHAKHAPATNAM","HYDERABAD",230);
      graph.insert("BANGALORE","DELHI",1000);
      graph.insert("CHENNAI","BANGALORE",600);
      graph.insert("CHENNAI","DELHI",1300);

      graph.BFS("DELHI");
  }
}
