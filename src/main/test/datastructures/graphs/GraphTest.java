package datastructures.graphs;

/**
 * This class is used to test the Graph
 * class above.
 *
 * @author Zachary Jones
 */
class GraphTest {
    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>();
        assert graph.addEdge(1, 2);
        assert graph.addEdge(1, 5);
        assert graph.addEdge(2, 5);
        assert !graph.addEdge(1, 2);
        assert graph.addEdge(2, 3);
        assert graph.addEdge(3, 4);
        assert graph.addEdge(4, 1);
        assert !graph.addEdge(2, 3);
        System.out.println(graph);
    }
}