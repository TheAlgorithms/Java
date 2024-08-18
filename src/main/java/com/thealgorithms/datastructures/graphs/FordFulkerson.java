package  com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;



@Data
public class FordFulkerson<T> {

    // List to store all the paths found from source to destination
    private final List<List<Vertex<T>>> paths = new ArrayList<>();

    /**
     * Runs the Ford-Fulkerson algorithm to calculate the maximum flow from the source to the destination.
     * 
     * @param source      The source vertex.
     * @param destination The destination vertex.
     * @return The maximum possible flow in the network.
     */
    public int run(Vertex<T> source, Vertex<T> destination) {
        // Find all possible paths from source to destination
        findAllPaths(source, destination, new ArrayList<>(Collections.singleton(source)));

        // Atomic integer to hold the maximum flow value
        AtomicInteger maxFlow = new AtomicInteger();

        // Iterate over each path, sorted by the minimum flow in descending order
        paths.stream()
                .sorted(Comparator.comparingInt(this::getMinFlowInPath).reversed())
                .forEach(path -> {
                    // Get the minimum flow (bottleneck) in the current path
                    Integer minimum = getMinFlowInPath(path);

                    // Subtract the minimum flow from all edges in the path
                    IntStream.range(0, path.size() - 1)
                            .forEach(vertexIdx -> removeMinFlowFromVerticesInPath(path, minimum, vertexIdx));

                    // Add the minimum flow to the total max flow
                    maxFlow.addAndGet(minimum);
                });

        // Return the calculated maximum flow
        return maxFlow.get();
    }

    /**
     * Finds the minimum flow in a given path.
     * 
     * @param path The path in which to find the minimum flow.
     * @return The minimum flow in the path.
     */
    private Integer getMinFlowInPath(List<Vertex<T>> path) {
        return IntStream.range(0, path.size() - 1)
                .mapToObj(vertexIdx -> getNeighborEdgeWeight(path, vertexIdx))
                .min(Integer::compareTo).orElse(0);
    }

    /**
     * Reduces the flow of each edge in the path by the minimum flow value.
     * 
     * @param path      The path where the flow should be reduced.
     * @param min       The minimum flow value to subtract.
     * @param vertexIdx The index of the current vertex in the path.
     */
    private void removeMinFlowFromVerticesInPath(List<Vertex<T>> path, Integer min, int vertexIdx) {
        // Update the edge's flow by subtracting the minimum flow
        path.get(vertexIdx).getNeighbors().put(
                path.get(vertexIdx + 1),
                getNeighborEdgeWeight(path, vertexIdx) - min
        );
    }

    /**
     * Gets the weight of the edge between two adjacent vertices in a path.
     * 
     * @param path      The path containing the vertices.
     * @param vertexIdx The index of the current vertex in the path.
     * @return The weight of the edge between the current vertex and the next vertex in the path.
     */
    private Integer getNeighborEdgeWeight(List<Vertex<T>> path, int vertexIdx) {
        return path.get(vertexIdx).getNeighbors().get(path.get(vertexIdx + 1));
    }

    /**
     * Recursively finds all paths from the current vertex to the destination vertex.
     * 
     * @param current      The current vertex being explored.
     * @param destination  The destination vertex.
     * @param currentPath  The current path being built.
     */
    private void findAllPaths(Vertex<T> current, Vertex<T> destination, List<Vertex<T>> currentPath) {
        // If the current vertex is the destination, add the current path to the list of paths
        if (current.equals(destination)) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }

        // Mark the current vertex as visited to avoid revisiting
        current.setVisited(true);

        // Explore all unvisited neighbors of the current vertex
        current.getNeighbors().keySet().stream()
                .filter(neighbor -> !neighbor.isVisited())
                .forEach(neighbor -> {
                    // Add the neighbor to the current path
                    currentPath.add(neighbor);

                    // Recursively find paths from the neighbor to the destination
                    findAllPaths(neighbor, destination, currentPath);

                    // Backtrack by removing the neighbor from the current path
                    currentPath.remove(neighbor);
                });

        // Mark the current vertex as unvisited to allow for other paths
        current.setVisited(false);
    }
}

