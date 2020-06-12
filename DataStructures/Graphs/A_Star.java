/*
	Time Complexity = O(E), where E is equal to the number of edges
*/

package A_Star;

import java.util.*;

public class A_Star {

    private static class Graph {
        //Graph's structure can be changed only applying changes to this class.
        private ArrayList<Edge> [] graph;

        //Initialise ArrayLists in Constructor
        public Graph(int size) {
            this.graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                this.graph[i] = new ArrayList<>();
            }
        }

        private ArrayList<Edge> getNeighbours(int from) { return this.graph[from]; }

        //Graph is bidirectional, for just one direction remove second instruction of this method.
        private void addEdge (Edge edge) {
            this.graph[edge.getFrom()].add(new Edge(edge.getFrom(), edge.getTo(), edge.getWeight()));
            this.graph[edge.getTo()].add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
        }

        private void initializeGraph() {
            this.addEdge(new Edge(0, 19, 75));  this.addEdge(new Edge(0, 15, 140));
            this.addEdge(new Edge(0, 16, 118)); this.addEdge(new Edge(19, 12, 71));
            this.addEdge(new Edge(12, 15, 151));this.addEdge(new Edge(16, 9, 111));
            this.addEdge(new Edge(9, 10, 70));  this.addEdge(new Edge(10, 3, 75));
            this.addEdge(new Edge(3, 2, 120));  this.addEdge(new Edge(2, 14, 146));
            this.addEdge(new Edge(2, 13, 138)); this.addEdge(new Edge(2, 6, 115));
            this.addEdge(new Edge(15, 14, 80)); this.addEdge(new Edge(15, 5, 99));
            this.addEdge(new Edge(14, 13, 97)); this.addEdge(new Edge(5, 1, 211));
            this.addEdge(new Edge(13, 1, 101)); this.addEdge(new Edge(6, 1, 160));
            this.addEdge(new Edge(1, 17, 85));  this.addEdge(new Edge(17, 7, 98));
            this.addEdge(new Edge(7, 4, 86));   this.addEdge(new Edge(17, 18, 142));
            this.addEdge(new Edge(18, 8, 92));  this.addEdge(new Edge(8, 11, 87));

        /*
        .x. node
        (y) cost
        - or | or / bidirectional connection

                              ( 98)- .7. -(86)- .4.
                                |
                        ( 85)- .17. -(142)- .18. -(92)- .8. -(87)- .11.
                          |
                         . 1. -------------------- (160)
                          |  \                       |
                        (211) \                     .6.
                          |    \                     |
                         . 5.  (101)-.13. -(138)   (115)
                          |           |     |     /
                        ( 99)       ( 97)   |    /
                          |           |     |   /
            .12. -(151)- .15. -(80)- .14.   |  /
             |            |           |     | /
           ( 71)        (140)       (146)- .2. -(120)
             |            |                       |
            .19. -( 75)- . 0.        .10. -(75)- .3.
                          |            |
                        (118)        ( 70)
                          |            |
                         .16. -(111)- .9.
         */
        }
    }

    private static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() { return from; }

        public int getTo() { return to; }

        public int getWeight() { return weight; }
    }

    //class to iterate during the algorithm execution, and also used to return the solution.
    private static class PathAndDistance {
        private int distance; //distance advanced so far.
        private ArrayList<Integer> path; //list of visited nodes in this path.
        private int estimated; //heuristic value associated to the last node od the path (current node).

        public PathAndDistance(int distance, ArrayList<Integer> path, int estimated) {
            this.distance = distance;
            this.path = path;
            this.estimated = estimated;
        }

        public int getDistance() { return distance; }

        public ArrayList<Integer> getPath() { return path; }

        public int getEstimated() { return estimated; }

        private void printSolution () {
            if (this.path != null)
                System.out.println("Optimal path: " + this.path.toString() +
                        ", distance: " + this.distance);
            else
                System.out.println("There is no path available to connect the points");
        }
    }

    public static void main(String[] args) {
        //heuristic function optimistic values
        int[] heuristic = {366, 0, 160, 242, 161, 178, 77, 151, 226,
                244, 241, 234, 380, 98, 193, 253, 329, 80, 199, 374};

        Graph graph = new Graph(20);
        graph.initializeGraph();

        PathAndDistance solution = aStar(3, 1, graph, heuristic);
        solution.printSolution();

    }

    public static PathAndDistance aStar(int from, int to, Graph graph, int[] heuristic) {
        //nodes are prioritised by the less value of the current distance of their paths, and the estimated value
        //given by the heuristic function to reach the destination point from the current point.
        PriorityQueue<PathAndDistance> queue = new PriorityQueue<>
                (Comparator.comparingInt(a -> (a.getDistance() + a.getEstimated())));

        //dummy data to start the algorithm from the beginning point
        queue.add(new PathAndDistance(0, new ArrayList<>(Arrays.asList(from)), 0));

        boolean solutionFound = false;
        PathAndDistance currentData = new PathAndDistance(-1, null, -1);
        while (!queue.isEmpty() && !solutionFound) {
            currentData = queue.poll(); //first in the queue, best node so keep exploring.
            int currentPosition = currentData.getPath().get(currentData.getPath().size() - 1); //current node.
            if (currentPosition == to)
                solutionFound = true;
            else
                for (Edge edge : graph.getNeighbours(currentPosition))
                    if (!currentData.getPath().contains(edge.getTo())) { //Avoid Cycles
                        ArrayList<Integer> updatedPath = new ArrayList<>(currentData.getPath());
                        updatedPath.add(edge.getTo()); //Add the new node to the path, update the distance,
                        // and the heuristic function value associated to that path.
                        queue.add(new PathAndDistance(currentData.getDistance() + edge.getWeight(),
                                updatedPath, heuristic[edge.getTo()]));
                    }
        }
        return (solutionFound) ? currentData : new PathAndDistance(-1, null, -1);
        //Out of while loop, if there is a solution, the current Data stores the optimal path, and its distance
    }
}
