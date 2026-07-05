package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// ─────────────────────────────────────────────────────────────────────────────
// 1. Graph abstraction  (DIP: high-level code depends on this, not on HashSet)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Read-only view of an undirected graph used by the clique enumeration algorithm.
 */
interface Graph {
    /** Returns the number of vertices. */
    int size();

    /**
     * Returns an unmodifiable set of neighbors for vertex {@code u}.
     *
     * @throws IndexOutOfBoundsException if {@code u} is out of range
     */
    Set<Integer> neighbours(int u);
}

// ─────────────────────────────────────────────────────────────────────────────
// 2. Concrete Graph implementation
//    (SRP: only responsible for storing and exposing adjacency)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Immutable adjacency-list graph built from a raw {@code List<Set<Integer>>}.
 */
final class AdjacencyListGraph implements Graph {

    private final List<Set<Integer>> adjacency;

    /**
     * Constructs a validated, defensive copy of the supplied adjacency list.
     *
     * @param raw the adjacency list to copy; already validated by {@link GraphValidator}
     */
    AdjacencyListGraph(List<Set<Integer>> raw) {
        int n = raw.size();
        List<Set<Integer>> copy = new ArrayList<>(n);
        for (int u = 0; u < n; u++) {
            Set<Integer> neighbours = new HashSet<>(raw.get(u));
            neighbours.remove(u);                 // strip self-loops
            copy.add(Collections.unmodifiableSet(neighbours));
        }
        this.adjacency = Collections.unmodifiableList(copy);
    }

    @Override
    public int size() {
        return adjacency.size();
    }

    @Override
    public Set<Integer> neighbours(int u) {
        return adjacency.get(u);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 3. Input validation
//    (SRP: one class, one job — catch bad input before it enters the algorithm)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Validates a raw adjacency list before it is used to construct a {@link Graph}.
 */
final class GraphValidator {

    private GraphValidator() {}

    /**
     * Throws {@link IllegalArgumentException} if the adjacency list is malformed.
     *
     * @param adjacency the list to validate
     */
    static void validate(List<Set<Integer>> adjacency) {
        if (adjacency == null) {
            throw new IllegalArgumentException("Adjacency list must not be null");
        }
        int n = adjacency.size();
        for (Set<Integer> neighbours : adjacency) {
            if (neighbours == null) {
                throw new IllegalArgumentException("Adjacency list must not contain null sets");
            }
            for (int v : neighbours) {
                if (v < 0 || v >= n) {
                    throw new IllegalArgumentException("Neighbour index out of bounds: " + v);
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 4. Pivot selection strategy
//    (OCP: swap out strategies without touching the solver;
//     DIP: solver depends on the interface, not a concrete choice)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Strategy for choosing a pivot vertex during Bron–Kerbosch enumeration.
 */
interface PivotStrategy {
    /**
     * Selects a pivot from {@code candidates ∪ excluded}.
     *
     * @param candidates the current P set
     * @param excluded   the current X set
     * @param graph      the graph being searched
     * @return the chosen pivot, or {@code -1} if the union is empty
     */
    int choosePivot(Set<Integer> candidates, Set<Integer> excluded, Graph graph);
}

/**
 * Picks the vertex from {@code P ∪ X} with the highest degree in the full graph.
 * This is the classic Tomita et al. (2006) heuristic.
 */
final class MaxDegreePivotStrategy implements PivotStrategy {

    @Override
    public int choosePivot(Set<Integer> candidates, Set<Integer> excluded, Graph graph) {
        int pivot = -1;
        int maxDegree = -1;
        for (int v : union(candidates, excluded)) {
            int degree = graph.neighbours(v).size();
            if (degree > maxDegree) {
                maxDegree = degree;
                pivot = v;
            }
        }
        return pivot;
    }

    private static Set<Integer> union(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 5. Search-state value object
//    (removes the "data clump" smell: R/P/X always travelled together)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Immutable snapshot of one recursive frame in the Bron–Kerbosch search:
 * the current clique {@code R}, candidate vertices {@code P}, and
 * already-processed vertices {@code X}.
 *
 * <p>Mutation methods return new instances, keeping each frame independent.</p>
 */
final class SearchState {

    final Set<Integer> clique;      // R
    final Set<Integer> candidates;  // P
    final Set<Integer> excluded;    // X

    SearchState(Set<Integer> clique, Set<Integer> candidates, Set<Integer> excluded) {
        this.clique = Collections.unmodifiableSet(new HashSet<>(clique));
        this.candidates = Collections.unmodifiableSet(new HashSet<>(candidates));
        this.excluded = Collections.unmodifiableSet(new HashSet<>(excluded));
    }

    /** Returns the initial state for a graph with {@code n} vertices. */
    static SearchState initial(int n) {
        Set<Integer> allVertices = new HashSet<>();
        for (int v = 0; v < n; v++) {
            allVertices.add(v);
        }
        return new SearchState(new HashSet<>(), allVertices, new HashSet<>());
    }

    /** Returns a new state with {@code vertex} added to the clique and both sets intersected with its neighbours. */
    SearchState expandWith(int vertex, Graph graph) {
        Set<Integer> neighbours = graph.neighbours(vertex);
        Set<Integer> newClique = new HashSet<>(clique);
        newClique.add(vertex);
        return new SearchState(newClique, intersection(candidates, neighbours), intersection(excluded, neighbours));
    }

    /** Returns a new state with {@code vertex} moved from candidates to excluded. */
    SearchState processVertex(int vertex) {
        Set<Integer> newCandidates = new HashSet<>(candidates);
        newCandidates.remove(vertex);
        Set<Integer> newExcluded = new HashSet<>(excluded);
        newExcluded.add(vertex);
        return new SearchState(clique, newCandidates, newExcluded);
    }

    boolean isMaximalClique() {
        return candidates.isEmpty() && excluded.isEmpty();
    }

    private static Set<Integer> intersection(Set<Integer> base, Set<Integer> filter) {
        Set<Integer> result = new HashSet<>();
        for (int v : base) {
            if (filter.contains(v)) {
                result.add(v);
            }
        }
        return result;
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 6. Algorithm orchestrator
//    (SRP: only responsible for the recursive enumeration logic;
//     DIP: depends on Graph and PivotStrategy abstractions)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Enumerates all maximal cliques using the Bron–Kerbosch algorithm with pivoting.
 *
 * <p>The solver is constructed with a {@link PivotStrategy}, making the pivot
 * heuristic interchangeable without modifying this class (OCP).</p>
 */
final class BronKerboschSolver {

    private final PivotStrategy pivotStrategy;

    /**
     * Creates a solver using the supplied pivot strategy.
     *
     * @param pivotStrategy the strategy for choosing a pivot vertex; must not be null
     */
    BronKerboschSolver(PivotStrategy pivotStrategy) {
        if (pivotStrategy == null) {
            throw new IllegalArgumentException("PivotStrategy must not be null");
        }
        this.pivotStrategy = pivotStrategy;
    }

    /**
     * Finds all maximal cliques in {@code graph}.
     *
     * @param graph the graph to search
     * @return a list of maximal cliques, each as an unmodifiable set of vertices
     */
    List<Set<Integer>> findMaximalCliques(Graph graph) {
        List<Set<Integer>> cliques = new ArrayList<>();
        enumerate(SearchState.initial(graph.size()), graph, cliques);
        return cliques;
    }

    private void enumerate(SearchState state, Graph graph, List<Set<Integer>> cliques) {
        if (state.isMaximalClique()) {
            cliques.add(state.clique);
            return;
        }

        int pivot = pivotStrategy.choosePivot(state.candidates, state.excluded, graph);
        Set<Integer> pivotNeighbours = pivot == -1 ? new HashSet<>() : graph.neighbours(pivot);

        // Iterate over P \ N(pivot) — a snapshot copy is needed since state is immutable anyway
        Set<Integer> toExpand = new HashSet<>(state.candidates);
        toExpand.removeAll(pivotNeighbours);

        SearchState current = state;
        for (int vertex : toExpand) {
            enumerate(current.expandWith(vertex, graph), graph, cliques);
            current = current.processVertex(vertex);
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 7. Public façade  (preserves the original public API surface exactly)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Public entry point for maximal-clique enumeration via the Bron–Kerbosch algorithm.
 *
 * <p>This façade wires together {@link GraphValidator}, {@link AdjacencyListGraph},
 * {@link MaxDegreePivotStrategy}, and {@link BronKerboschSolver}, keeping all
 * callers insulated from the internal decomposition.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm">
 *     Wikipedia: Bron–Kerbosch algorithm</a>
 */
public final class BronKerbosch {

    private BronKerbosch() {}

    /**
     * Finds all maximal cliques of the provided graph.
     *
     * @param adjacency adjacency list where {@code adjacency.size()} equals the number of vertices
     * @return a list containing every maximal clique, each represented as a {@link Set} of vertices
     * @throws IllegalArgumentException if the adjacency list is {@code null}, contains {@code null}
     *         entries, or references invalid vertices
     */
    public static List<Set<Integer>> findMaximalCliques(List<Set<Integer>> adjacency) {
        GraphValidator.validate(adjacency);
        Graph graph = new AdjacencyListGraph(adjacency);
        BronKerboschSolver solver = new BronKerboschSolver(new MaxDegreePivotStrategy());
        return solver.findMaximalCliques(graph);
    }
}