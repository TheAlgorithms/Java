import java.util.LinkedList;

import java.util.Queue;


import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private int data;
    private boolean visited;
    private List<Vertex> neighbourList;


    public Vertex(int data){
        this.data = data;
        this.neighbourList = new ArrayList<>();
    }

public void addNeighbours(Vertex vertex){
        this.neighbourList.add(vertex);
}

    public int getData() {
        return data;
    }

    public List<Vertex> getNeighbourList() {
        return neighbourList;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNeighbourList(List<Vertex> neighbourList) {
        this.neighbourList = neighbourList;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}

public class BFS {

public void bfs(Vertex root) {
    Queue<Vertex> queue = new LinkedList<>();

    root.setVisited(true);
    queue.add(root);

        while(!queue.isEmpty()){

            Vertex actualVertex = queue.remove();
            System.out.println(actualVertex.getData()+" ");

            for(Vertex v : actualVertex.getNeighbourList()){
                if(!v.isVisited()){
                    v.setVisited(true);
                    queue.add(v);
                }
            }

        }

    }
}

public class BFSimplement {

    public static void main(String[] args) {
       Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5= new Vertex(5);

        v1.addNeighbours(v2);
        v1.addNeighbours(v4);
        v4.addNeighbours(v5);
        v2.addNeighbours(v3);
        v1.addNeighbours(v4);

        BFS bfs = new BFS();
        bfs.bfs(v1);

    }
}


