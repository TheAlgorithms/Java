import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

/**
 * Simple test for BidirectionalBFS.
 */
public class BidirectionalBFSTest
{
    public static void main(String[] args)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3, 4));
        graph.put(3, Arrays.asList(1, 2, 5));
        graph.put(4, Arrays.asList(2, 5));
        graph.put(5, Arrays.asList(3, 4));

        // Test 1
        boolean result1 = BidirectionalBFS.bidirectionalBFS(graph, 0, 5);
        System.out.println("Path 0->5 exists: " + result1); // true

        // Test 2
        boolean result2 = BidirectionalBFS.bidirectionalBFS(graph, 0, 6);
        System.out.println("Path 0->6 exists: " + result2); // false
    }
}
