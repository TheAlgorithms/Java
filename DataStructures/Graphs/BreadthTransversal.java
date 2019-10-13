import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * DepthTransversal
 */
public class BreadthTransversal {

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 42);
        g.addEdge(1, 2, 7);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 4, 2);
        g.addEdge(2, 5, 3);
        g.addEdge(4, 5, 6);
        System.out.print(g.iteration);
    }

    
    static class Graph{

        int[][] graph;

        Graph(int size){
            graph = int[size][size];
            for(int i = 0; i < size; i++){
                Arrays.fill(graph[i], Integer.MAX_VALUE);
                graph[i][i] = 0;
            }
        }

        void addEdge(int first, int second, int value){
            if(first >= graph.length || second >= graph.length) System.err.print("Out of bounds");
            if(first == second) System.err.print("Nodes are equal");
            graph[first][second] = value;
            graph[second][first] = value;
        }

        ArrayList<Intger> iteration(int start){
            int act;
            // List which collects the index of the nodes in the correct order
            ArrayList<Integer> list = new ArrayList<>();
            // Stack element to simulate a memory-stack
            ArrayDeque<Integer> queue = new ArrayDeque()<>();
            queue.push(start);
            // Set to controll, that no node was added twice. (Possible to do this with the ArrayList "list" too).
            Set<Integer> controll = new HashSet<>();

            while(!queue.isEmpty()){
                act = queue.poll();
                for(int i = 0; i < graph.length; i++){
                    if(i != act && !controll.contains(i) && graph[act] != Integer.MAX_VALUE){
                        doSomething(i);
                        list.add(i);
                        stack.push(i);
                    }
                }
            }
            return list;
            
        }

        private void doSomething(int index){
            // Do something with the node    
        }
    }
}