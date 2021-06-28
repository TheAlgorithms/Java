import java.util.List;
import java.util.Stack;

public class DFS {

    private Stack<Vertex> stack;

    public DFS(){
        this.stack = new Stack<>();
    }

    public void dfs(List<Vertex> list){  // when we have several clustered it mean there may be some node that not connected

        for(Vertex v : list){
            if(!v.isVisited()){
                 dfsWithStack(v);
            }
        }

    }

    public  void dfsWithStack(Vertex root){
        this.stack.add(root);
        root.setVisited(true);

        while(!stack.isEmpty()){
            Vertex actualVertex = this.stack.pop();
            System.out.println(actualVertex.getName() + " ");

            for(Vertex v : actualVertex.getNeighbourList()){
                if(!v.isVisited()){
                    v.setVisited(true);
                    this.stack.push(v);
                }
            }
        }
    }

    public void dfsRecursive(Vertex vertex){

        System.out.print(vertex.getName()+" ");

        vertex.setVisited(true);
        for(Vertex v : vertex.getNeighbourList()){
            v.setVisited(true);
            dfsRecursive(v);
        }
    }


}

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String name;
    private boolean visited;
    private List<Vertex> neighbourList;

    public Vertex(String name){
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    public void addNeighbours(Vertex vertex){
        this.neighbourList.add(vertex);
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setNeighbourList(List<Vertex> neighbourList) {
        this.neighbourList = neighbourList;
    }

    public List<Vertex> getNeighbourList() {
        return neighbourList;
    }

    public void setName(String name) {
        this.name = name;
    }
}

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");

        v1.addNeighbours(v2);
        v1.addNeighbours(v3);
        v3.addNeighbours(v4);
        v4.addNeighbours(v5);
//        v1.addNeighbours(v2);

        List<Vertex> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        DFS dfs = new DFS();

        //dfs.dfs(list);

      //  dfs.dfsWithStack(v1);

      dfs.dfsRecursive(v1);
    }
}


