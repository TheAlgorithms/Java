import java.util.HashMap;
import java.util.Map;

/**
 * A data structure that stores a collection of disjoint (non-overlapping) sets
 * See: https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 *
 * @author Alexander Dadukin (https://github.com/st235)
 */
public class DisjointSet {

    private final Map<Integer, Integer> parents = new HashMap<>();
    private final Map<Integer, Integer> weights = new HashMap<>();

    /**
     * Puts node to disjoint set as a singleton set { node }
     */
    public boolean add(int node) {
        int parent = find(node);

        if (parent == node) {
            parents.put(node, node);
            weights.put(node, 1);
            return true;
        }
        
        return false;
    }

    /**
     * Finds a parent of given node
     * Two nodes should have the same parent if connected directly or indirectly
     */
    public int find(int node) {
        if (parents.getOrDefault(node, node) == node) {
            return node;
        }

        parents.put(node, find(node));
        return parents.get(node);
    }

    /**
     * Union two nodes from if it belong to different sets,
     * otherwise do nothing
     * 
     * @return true if union completed successfully, false otherwise 
     */
    public boolean union(int nodeA, int nodeB) {
        int parentA = find(nodeA);
        int parentB = find(nodeB);

        if (parentA == parentB) {
            return false;
        }

        int weightA = weights.getOrDefault(parentA, 1);
        int weightB = weights.getOrDefault(parentB, 1);

        if (weightA > weightB) {
            int t = parentA;
            parentA = parentB;
            parentB = t;
        }

        parents.put(parentA, parentB);
        weights.put(parentB, weights.get(parentB) + weights.get(parentA));

        return true;
    }

}
