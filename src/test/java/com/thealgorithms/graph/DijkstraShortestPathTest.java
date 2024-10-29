import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DijkstraShortestPathTest {

    private DijkstraShortestPath dijkstra;

    @BeforeEach
    public void setUp() {
        dijkstra = new DijkstraShortestPath();
    }

    @Test
    public void testSinglePath() {
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        adjList.put(0, List.of(new int[]{1, 1}));
        adjList.put(1, List.of(new int[]{2, 1}));
        adjList.put(2, List.of(new int[]{3, 1}));
        adjList.put(3, new ArrayList<>());

        int[] result = dijkstra.shortestPath(4, adjList, 0);

        int[] expected = {0, 1, 2, 3};
        assertArrayEquals(expected, result, "Shortest path distances should match.");
    }

    @Test
    public void testDisconnectedGraph() {
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        adjList.put(0, List.of(new int[]{1, 2}));
        adjList.put(1, List.of(new int[]{2, 2}));
        adjList.put(2, new ArrayList<>());
        adjList.put(3, new ArrayList<>());

        int[] result = dijkstra.shortestPath(4, adjList, 0);

        int[] expected = {0, 2, 4, Integer.MAX_VALUE};
        assertArrayEquals(expected, result, "Shortest path should indicate unreachable nodes.");
    }

    @Test
    public void testComplexGraph() {
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        adjList.put(0, List.of(new int[]{1, 4}, new int[]{2, 1}));
        adjList.put(1, List.of(new int[]{3, 1}));
        adjList.put(2, List.of(new int[]{1, 2}, new int[]{3, 5}));
        adjList.put(3, new ArrayList<>());

        int[] result = dijkstra.shortestPath(4, adjList, 0);

        int[] expected = {0, 3, 1, 4};
        assertArrayEquals(expected, result, "Distances should match expected shortest path distances.");
    }

    @Test
    public void testRevisitedNodeWithHigherDistance() {
        // This graph is set up to test the condition where a node is revisited with a higher distance.
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        adjList.put(0, List.of(new int[]{1, 5}));
        adjList.put(1, List.of(new int[]{2, 1}));
        adjList.put(2, List.of(new int[]{0, 3}, new int[]{3, 1}));
        adjList.put(3, new ArrayList<>());

        int[] result = dijkstra.shortestPath(4, adjList, 0);

        int[] expected = {0, 5, 6, 7};
        assertArrayEquals(expected, result, "Distances should match expected shortest path distances.");
    }
}
//added test cases
