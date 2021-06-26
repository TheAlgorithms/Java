package DataStructures.Trees;

/**
 * Data structure that can efficiently update elements and calculate prefix sums in a table of numbers
 * See: https://en.wikipedia.org/wiki/Fenwick_tree
 */
public class BinaryIndexedTree {

    private final int[] values;
    private final int[] tree;

    BinaryIndexedTree(int[] array) {
        this.values = new int[array.length];
        // the first one node is dummy one
        this.tree = new int[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            updateInternally(i, array[i]);
        }
    }

    /**
     * Updates value at given index with a new value
     * Complexity: O(ln(n))
     */
    public int query(int lowerBound, int upperBound) {
        return queryInternally(upperBound) - queryInternally(lowerBound - 1);
    }

    /**
     * Calculates sum of the given interval.
     * Inteval given by [from - to] inclusive
     * Complexity: O(ln(n))
     */
    public void update(int atIndex, int value) {
        updateInternally(atIndex, value - values[atIndex]);
    }

    private void updateInternally(int atIndex, int diff) {
        values[atIndex] += diff;

        int current = atIndex + 1;
        while (isValid(current)) {
            tree[current] += diff;
            current = sibling(current);
        }
    }

    private int queryInternally(int upperBound) {
        int sum = 0;

        int current = upperBound + 1;
        while (isValid(current)) {
            sum += tree[current];
            current = parent(current);
        }

        return sum;
    }

    private int parent(int forIndex) {
        return forIndex - (forIndex & (~forIndex + 1));
    }

    private int sibling(int forIndex) {
        return forIndex + (forIndex & (~forIndex + 1));
    }

    private boolean isValid(int index) {
        return index > 0 && index < tree.length;
    }
}
