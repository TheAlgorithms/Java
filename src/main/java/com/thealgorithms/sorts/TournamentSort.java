package com.thealgorithms.sorts;
import java.util.Arrays;

/**
 * Tournament Sort (Winner Tree) – comparison-based sorting that repeatedly
 * selects the minimum via a "winner tree" and removes it,
 * updating winners along the path.
 *
 * Time: O(n log m) where m is next power-of-two ≥ n
 */
public final class TournamentSort {

    private TournamentSort() {
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null || a.length < 2) return;

        final int n = a.length;
        int m = 1;
        while (m < n) m <<= 1;

        // Winner-tree nodes;
        Node<T>[] tree = new Node[2 * m];

        for (int i = 0; i < n; i++) {
            int leafPos = m + i;
            tree[leafPos] = new Node<>(a[i], leafPos);
        }

        for (int i = m - 1; i >= 1; i--) {
            tree[i] = minNode(tree[2 * i], tree[2 * i + 1]);
        }

        T[] out = Arrays.copyOf(a, n);
        for (int k = 0; k < n; k++) {
            Node<T> winner = tree[1];
            out[k] = winner.value;

            // remove winner leaf by setting it to null (acts like +∞)
            int pos = winner.leafPos;
            tree[pos] = null;

            // re-compute winners up the path
            pos >>= 1;
            while (pos >= 1) {
                tree[pos] = minNode(tree[2 * pos], tree[2 * pos + 1]);
                pos >>= 1;
            }
        }

        System.arraycopy(out, 0, a, 0, n);
    }
    
    public static void sort(int[] a) {
        if (a == null || a.length < 2) return;
        Integer[] boxed = new Integer[a.length];
        for (int i = 0; i < a.length; i++) boxed[i] = a[i];
        sort(boxed);
        for (int i = 0; i < a.length; i++) a[i] = boxed[i];
    }

    private static <T extends Comparable<T>> Node<T> minNode(Node<T> left, Node<T> right) {
        if (left == null) return right;
        if (right == null) return left;
        return (left.value.compareTo(right.value) <= 0) ? left : right;
    }

    private static final class Node<T extends Comparable<T>> {
        final T value;
        final int leafPos;
        Node(T v, int p) {
            this.value = v;
            this.leafPos = p;
        }
    }
}
