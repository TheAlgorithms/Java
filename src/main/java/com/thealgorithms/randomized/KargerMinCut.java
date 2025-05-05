package com.thealgorithms.randomized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of Karger's Minimum Cut algorithm.
 *
 * <p>Karger's algorithm is a randomized algorithm to compute the minimum cut of a connected graph.
 * A minimum cut is the smallest set of edges that, if removed, would split the graph into two
 * disconnected components.
 *
 * <p>The algorithm works by repeatedly contracting random edges in the graph until only two
 * nodes remain. The edges between these two nodes represent a cut. By running the algorithm
 * multiple times and keeping track of the smallest cut found, the probability of finding the
 * true minimum cut increases.
 *
 * <p>Key steps of the algorithm:
 * <ol>
 *   <li>Randomly select an edge and contract it, merging the two nodes into one.</li>
 *   <li>Repeat the contraction process until only two nodes remain.</li>
 *   <li>Count the edges between the two remaining nodes to determine the cut size.</li>
 *   <li>Repeat the process multiple times to improve the likelihood of finding the true minimum cut.</li>
 * </ol>
 * <p>
 * See more: <a href="https://en.wikipedia.org/wiki/Karger%27s_algorithm">Karger's algorithm</a>
 *
 * @author MuhammadEzzatHBK
 */
public final class KargerMinCut {

    /**
     * Output of the Karger algorithm.
     *
     * @param first  The first set of nodes in the cut.
     * @param second The second set of nodes in the cut.
     * @param minCut The size of the minimum cut.
     */
    public record KargerOutput(Set<Integer> first, Set<Integer> second, int minCut) {
    }

    private KargerMinCut() {
    }

    public static KargerOutput findMinCut(Collection<Integer> nodeSet, List<int[]> edges) {
        return findMinCut(nodeSet, edges, 100);
    }

    /**
     * Finds the minimum cut of a graph using Karger's algorithm.
     *
     * @param nodeSet:    Input graph nodes
     * @param edges:      Input graph edges
     * @param iterations: Iterations to run the algorithms for, more iterations = more accuracy
     * @return A KargerOutput object containing the two sets of nodes and the size of the minimum cut.
     */
    public static KargerOutput findMinCut(Collection<Integer> nodeSet, List<int[]> edges, int iterations) {
        Graph graph = new Graph(nodeSet, edges);
        KargerOutput minCut = new KargerOutput(new HashSet<>(), new HashSet<>(), Integer.MAX_VALUE);
        KargerOutput output;

        // Run the algorithm multiple times to increase the probability of finding
        for (int i = 0; i < iterations; i++) {
            Graph clone = graph.copy();
            output = clone.findMinCut();
            if (output.minCut < minCut.minCut) {
                minCut = output;
            }
        }
        return minCut;
    }

    private static class DisjointSetUnion {
        private final int[] parent;
        int setCount;

        DisjointSetUnion(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            setCount = size;
        }

        int find(int i) {
            // If it's not its own parent, then it's not the root of its set
            if (parent[i] != i) {
                // Recursively find the root of its parent
                // and update i's parent to point directly to the root (path compression)
                parent[i] = find(parent[i]);
            }

            // Return the root (representative) of the set
            return parent[i];
        }

        void union(int u, int v) {
            // Find the root of each node
            int rootU = find(u);
            int rootV = find(v);

            // If they belong to different sets, merge them
            if (rootU != rootV) {
                // Make rootV point to rootU — merge the two sets
                parent[rootV] = rootU;

                // Reduce the count of disjoint sets by 1
                setCount--;
            }
        }

        boolean inSameSet(int u, int v) {
            return find(u) == find(v);
        }

        /*
          This is a verbosity method, it's not a part of the core algorithm,
          But it helps us provide more useful output.
        */
        Set<Integer> getAnySet() {
            int aRoot = find(0); // Get one of the two roots

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < parent.length; i++) {
                if (find(i) == aRoot) {
                    set.add(i);
                }
            }

            return set;
        }
    }

    private static class Graph {
        private final List<Integer> nodes;
        private final List<int[]> edges;

        Graph(Collection<Integer> nodeSet, List<int[]> edges) {
            this.nodes = new ArrayList<>(nodeSet);
            this.edges = new ArrayList<>();
            for (int[] e : edges) {
                this.edges.add(new int[] {e[0], e[1]});
            }
        }

        Graph copy() {
            return new Graph(this.nodes, this.edges);
        }

        KargerOutput findMinCut() {
            DisjointSetUnion dsu = new DisjointSetUnion(nodes.size());
            List<int[]> workingEdges = new ArrayList<>(edges);

            Random rand = new Random();

            while (dsu.setCount > 2) {
                int[] e = workingEdges.get(rand.nextInt(workingEdges.size()));
                if (!dsu.inSameSet(e[0], e[1])) {
                    dsu.union(e[0], e[1]);
                }
            }

            int cutEdges = 0;
            for (int[] e : edges) {
                if (!dsu.inSameSet(e[0], e[1])) {
                    cutEdges++;
                }
            }

            return collectResult(dsu, cutEdges);
        }

        /*
            This is a verbosity method, it's not a part of the core algorithm,
            But it helps us provide more useful output.
        */
        private KargerOutput collectResult(DisjointSetUnion dsu, int cutEdges) {
            Set<Integer> firstIndices = dsu.getAnySet();
            Set<Integer> firstSet = new HashSet<>();
            Set<Integer> secondSet = new HashSet<>();
            for (int i = 0; i < nodes.size(); i++) {
                if (firstIndices.contains(i)) {
                    firstSet.add(nodes.get(i));
                } else {
                    secondSet.add(nodes.get(i));
                }
            }
            return new KargerOutput(firstSet, secondSet, cutEdges);
        }
    }
}
