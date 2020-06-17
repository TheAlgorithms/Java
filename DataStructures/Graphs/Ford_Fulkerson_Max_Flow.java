package Ford_Fulkerson_Max_Flow;

import java.util.*;

public class Ford_Fulkerson_Max_Flow {
    //Complexity of O(E * f'), where E is the number of edges,
    //and f' is the maximum flow of the network

    private static int graphNodes;
    private static int source;
    private static int sink;

    private static void initializeGraph(HashSet<String>[] graph, TreeMap<String, Integer> edges, ArrayList<Integer> graphData) {
        //edges are stored in a treeMap alongside their capacity, every edge's key is formed by their origin point, a hyphen, and
        //its destination. The hyphen is necessary to not confuse 11 -> 3 with 1 -> 13
        for (int i = 0; i < graphData.size(); i+=4) {
            String arc = graphData.get(i) + "-" + graphData.get(i+1);
            graph[graphData.get(i)].add(arc);
            edges.put(arc, graphData.get(i + 2));
        }
    }

    private static boolean bfsPathFinder(int from, int to, HashSet<String>[] graph, TreeMap<String, Integer> edges, ArrayList<String> path) {
        boolean[] visited = new boolean[graphNodes];
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[graphNodes];
        visited[from] = true;
        queue.add(from);
        while (!queue.isEmpty() && !visited[to]) {
            int ini = queue.poll();
            for (String edge: graph[ini]) {
                int fin = Integer.parseInt(edge.substring(edge.indexOf('-') + 1));
                //if the node has not been visited yet AND the edge connecting them has still capacity available
                if (!visited[fin] && edges.get(edge) > 0) {
                    parent[fin] = ini;
                    visited[fin] = true;
                    queue.add(fin);
                }
            }
        }
        //once a path has been found, the algorithm saves all the edges to update their flow back in fordFulkerson method
        if (visited[to]) {
            int ini = to;
            path.clear();
            while (ini != from) {
                int aux = parent[ini];
                path.add(0, aux + "-" + ini);
                ini = aux;
            }
        }
        return visited[to];
    }

    private static int fordFulkerson (HashSet<String>[] graph, TreeMap<String, Integer> edges, int source, int sink) {
        int maxFlow = 0;
        ArrayList<String> path = new ArrayList<>();
        //while there is a path between source and sink with capacity available, keep flow stream going
        while (bfsPathFinder(source, sink, graph, edges, path)) {
            int[] pathFlow = {Integer.MAX_VALUE};
            //int as an array to use lambda for loop
            path.forEach(edge -> pathFlow[0] = Math.min(pathFlow[0], edges.get(edge)));
            //by consulting the capacity available of every edge used in this new path,
            //bottleneck is calculated as the minimum capacity of the path. It corresponds
            //to the number in which the flow can be incremented
            path.forEach(edge -> edges.put(edge, edges.get(edge) - pathFlow[0]));
            //each used edge gets its capacity reduced by the path bottleneck value
            maxFlow += pathFlow[0];
        }
        return maxFlow;
    }

    private static ArrayList<Integer> pickExample(int choice) {
        //from, to, capacity, null (to visually separate edges)
        switch (choice) {
            case 0: {
                //From 0 (source) to 10 (sink) should return 7
                source = 0;
                sink = 10;
                graphNodes = 11;
                return new ArrayList<>(Arrays.asList(0, 1, 7, null, 0, 2, 2, null, 0, 3, 1, null, 1, 4, 2,
                        null, 1, 5, 4, null, 2, 5, 5, null, 2, 6, 6, null, 3, 4, 4, null, 3, 8, 8, null, 4, 7, 7, null, 4, 8, 1,
                        null, 5, 7, 3, null, 5, 9, 3, null, 5, 6, 8, null, 6, 9, 3, null, 7, 10, 1, null, 8, 10, 3, null,  9, 10, 4));
            }
            case 1: {
                //From 0 (source) to 5 (sink) should return 18
                source = 0;
                sink = 5;
                graphNodes = 6;
                return new ArrayList<>(Arrays.asList(0, 1, 15, null, 0, 2, 11, null, 1, 2, 8, null, 2, 1, 10,
                        null, 1, 4, 9, null, 2, 3, 9, null, 3, 1, 15, null, 3, 4, 8, null, 3, 5, 11, null, 4, 5, 10));
            }
            case 2: {
                //From 0 (source) to 5 (sink) should return 12
                source = 0;
                sink = 5;
                graphNodes = 6;
                return new ArrayList<>(Arrays.asList(0, 1, 9, null, 0, 2, 9, null, 1, 2, 10, null, 2, 3, 1,
                        null, 1, 3, 8, null, 2, 4, 3, null, 4, 3, 8, null, 3, 4, 8, null, 3, 5, 10, null, 4, 5, 7));
            }
            case 3: {
                //From 0 (source) to 5 (sink) should return 15
                source = 0;
                sink = 5;
                graphNodes = 6;
                return new ArrayList<>(Arrays.asList(0, 1, 13, null, 0, 2, 10,
                        null, 1, 3, 5, null, 2, 4, 35, null, 3, 2, 50, null, 3, 5, 3, null, 4, 5, 20));
            }
            case 4: {
                //From 0 (source) to 2 (sink) should return 4
                source = 0;
                sink = 2;
                graphNodes = 6;
                return new ArrayList<>(Arrays.asList(0, 1, 3, null, 1, 3, 3,
                        null, 0, 4, 2, null, 4, 5, 2, null, 5, 1, 1, null, 1, 2, 1, null, 3, 2, 3));
            }
            default : {
                //From 0 (source) to 6 (sink) should return 15
                source = 0;
                sink = 6;
                graphNodes = 7;
                return new ArrayList<>(Arrays.asList(0, 1, 10, null, 0, 3, 10, null, 1, 2, 10,
                        null, 2, 3, 15, null, 3, 5, 5, null, 2, 5, 5, null, 1, 4, 10, null, 4, 6, 10, null, 5, 6, 10));
            }
        }
    }

    public static void main(String[] args) {
        //6 examples available, from 0 to 4 and default
        ArrayList<Integer> graphData = pickExample(5);
        HashSet<String>[] graph = new HashSet[graphNodes];
        for (int i = 0; i < graphNodes; i++) { graph[i] = new HashSet<>(); }
        TreeMap<String, Integer> edges = new TreeMap<>();
        initializeGraph(graph, edges, graphData);
        System.out.println("Graph maximum flow = " + fordFulkerson(graph, edges, source, sink));
    }
}

