package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that counts the number of different connected components in a graph
 *
 * @author Lukas Keul, Florian Mercks
 */
class Graph<E extends Comparable<E>> {

    class Node {

        E name;

        public Node(E name) {
            this.name = name;
        }
    }

    class Edge {

        Node startNode, endNode;

        public Edge(Node startNode, Node endNode) {
            this.startNode = startNode;
            this.endNode = endNode;
        }
    }

    ArrayList<Edge> edgeList;
    ArrayList<Node> nodeList;

    public Graph() {
        edgeList = new ArrayList<Edge>();
        nodeList = new ArrayList<Node>();
    }

    /**
     * Adds a new Edge to the graph. If the nodes aren't yet in nodeList, they
     * will be added to it.
     *
     * @param startNode the starting Node from the edge
     * @param endNode the ending Node from the edge
     */
    public void addEdge(E startNode, E endNode) {
        Node start = null, end = null;
        for (Node node : nodeList) {
            if (startNode.compareTo(node.name) == 0) {
                start = node;
            } else if (endNode.compareTo(node.name) == 0) {
                end = node;
            }
        }
        if (start == null) {
            start = new Node(startNode);
            nodeList.add(start);
        }
        if (end == null) {
            end = new Node(endNode);
            nodeList.add(end);
        }

        edgeList.add(new Edge(start, end));
    }

    /**
     * Main method used for counting the connected components. Iterates through
     * the array of nodes to do a depth first search to get all nodes of the
     * graph from the actual node. These nodes are added to the array
     * markedNodes and will be ignored if they are chosen in the nodeList.
     *
     * @return returns the amount of unconnected graphs
     */
    public int countGraphs() {
        int count = 0;
        Set<Node> markedNodes = new HashSet<Node>();

        for (Node n : nodeList) {
            if (!markedNodes.contains(n)) {
                markedNodes.add(n);
                markedNodes.addAll(depthFirstSearch(n, new ArrayList<Node>()));
                count++;
            }
        }

        return count;
    }

    /**
     * Implementation of depth first search.
     *
     * @param n the actual visiting node
     * @param visited A list of already visited nodes in the depth first search
     * @return returns a set of visited nodes
     */
    public ArrayList<Node> depthFirstSearch(Node n, ArrayList<Node> visited) {
        visited.add(n);
        for (Edge e : edgeList) {
            if (e.startNode.equals(n) && !visited.contains(e.endNode)) {
                depthFirstSearch(e.endNode, visited);
            }
        }
        return visited;
    }
}

public class ConnectedComponent {

    public static void main(String[] args) {
        Graph<Character> graphChars = new Graph<>();

        // Graph 1
        graphChars.addEdge('a', 'b');
        graphChars.addEdge('a', 'e');
        graphChars.addEdge('b', 'e');
        graphChars.addEdge('b', 'c');
        graphChars.addEdge('c', 'd');
        graphChars.addEdge('d', 'a');

        graphChars.addEdge('x', 'y');
        graphChars.addEdge('x', 'z');

        graphChars.addEdge('w', 'w');

        Graph<Integer> graphInts = new Graph<>();

        // Graph 2
        graphInts.addEdge(1, 2);
        graphInts.addEdge(2, 3);
        graphInts.addEdge(2, 4);
        graphInts.addEdge(3, 5);

        graphInts.addEdge(7, 8);
        graphInts.addEdge(8, 10);
        graphInts.addEdge(10, 8);

        System.out.println("Amount of different char-graphs: " + graphChars.countGraphs());
        System.out.println("Amount of different int-graphs: " + graphInts.countGraphs());
    }
}
