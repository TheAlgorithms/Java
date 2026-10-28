package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * This class implements a solution to the 2-SAT (2-Satisfiability) problem
 * using Kosaraju's algorithm to find strongly connected components (SCCs)
 * in the implication graph.
 *
 * <p>
 * <strong>Brief Idea:</strong>
 * </p>
 *
 * <pre>
 * 1. From each clause (a ∨ b), we can derive implications:
 *      (¬a → b) and (¬b → a)
 *
 * 2. We construct an implication graph using these implications.
 *
 * 3. For each variable x, its negation ¬x is also represented as a node.
 *    If x and ¬x belong to the same SCC, the expression is unsatisfiable.
 *
 * 4. Otherwise, we assign truth values based on the SCC order:
 *    If SCC(x) > SCC(¬x), then x = true; otherwise, x = false.
 * </pre>
 *
 * <p>
 * <strong>Complexities:</strong>
 * </p>
 * <ul>
 * <li>Time Complexity: O(n + m)</li>
 * <li>Space Complexity: O(n + m)</li>
 * </ul>
 * where {@code n} is the number of variables and {@code m} is the number of
 * clauses.
 *
 * <p>
 * <strong>Usage Example:</strong>
 * </p>
 *
 * <pre>
 * TwoSat twoSat = new TwoSat(5); // Initialize with 5 variables: x1, x2, x3, x4, x5
 *
 * // Add clauses
 * twoSat.addClause(1, false, 2, false); // (x1 ∨ x2)
 * twoSat.addClause(3, true, 2, false); // (¬x3 ∨ x2)
 * twoSat.addClause(4, false, 5, true); // (x4 ∨ ¬x5)
 *
 * twoSat.solve(); // Solve the problem
 *
 * if (twoSat.isSolutionExists()) {
 *     boolean[] solution = twoSat.getSolutions();
 *     for (int i = 1; i <= 5; i++) {
 *         System.out.println("x" + i + " = " + solution[i]);
 *     }
 * }
 * </pre>
 * <p><strong>Reference</strong></p>
 * <a href="https://cp-algorithms.com/graph/2SAT.html">CP Algorithm</a> <br></br>
 * <a href="https://en.wikipedia.org/wiki/2-satisfiability">Wikipedia - 2 SAT</a>
 * @author Shoyeb Ansari
 *
 * @see Kosaraju
 */
class TwoSat {

    /** Number of variables in the boolean expression. */
    private final int numberOfVariables;

    /** Implication graph built from the boolean clauses. */
    private final ArrayList<Integer>[] graph;

    /** Transposed implication graph used in Kosaraju's algorithm. */
    private final ArrayList<Integer>[] graphTranspose;

    /** Stores one valid truth assignment for all variables (1-indexed). */
    private final boolean[] variableAssignments;

    /** Indicates whether a valid solution exists. */
    private boolean hasSolution = true;

    /** Tracks whether the {@code solve()} method has been called. */
    private boolean isSolved = false;

    /**
     * Initializes the TwoSat solver with the given number of variables.
     *
     * @param numberOfVariables the number of boolean variables
     * @throws IllegalArgumentException if the number of variables is negative
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    TwoSat(int numberOfVariables) {
        if (numberOfVariables < 0) {
            throw new IllegalArgumentException("Number of variables cannot be negative.");
        }
        this.numberOfVariables = numberOfVariables;
        int n = 2 * numberOfVariables + 1;

        graph = (ArrayList<Integer>[]) new ArrayList[n];
        graphTranspose = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            graphTranspose[i] = new ArrayList<>();
        }
        variableAssignments = new boolean[numberOfVariables + 1];
    }

    /**
     * Adds a clause of the form (a ∨ b) to the boolean expression.
     *
     * <p>
     * Example: To add (¬x₁ ∨ x₂), call:
     * </p>
     *
     * <pre>{@code
     * addClause(1, true, 2, false);
     * }</pre>
     *
     * @param a         the first variable (1 ≤ a ≤ numberOfVariables)
     * @param isNegateA {@code true} if variable {@code a} is negated
     * @param b         the second variable (1 ≤ b ≤ numberOfVariables)
     * @param isNegateB {@code true} if variable {@code b} is negated
     * @throws IllegalArgumentException if {@code a} or {@code b} are out of range
     */
    void addClause(int a, boolean isNegateA, int b, boolean isNegateB) {
        if (a <= 0 || a > numberOfVariables) {
            throw new IllegalArgumentException("Variable number must be between 1 and " + numberOfVariables);
        }
        if (b <= 0 || b > numberOfVariables) {
            throw new IllegalArgumentException("Variable number must be between 1 and " + numberOfVariables);
        }

        a = isNegateA ? negate(a) : a;
        b = isNegateB ? negate(b) : b;
        int notA = negate(a);
        int notB = negate(b);

        // Add implications: (¬a → b) and (¬b → a)
        graph[notA].add(b);
        graph[notB].add(a);

        // Build transpose graph
        graphTranspose[b].add(notA);
        graphTranspose[a].add(notB);
    }

    /**
     * Solves the 2-SAT problem using Kosaraju's algorithm to find SCCs
     * and determines whether a satisfying assignment exists.
     */
    void solve() {
        isSolved = true;
        int n = 2 * numberOfVariables + 1;

        boolean[] visited = new boolean[n];
        int[] component = new int[n];
        Stack<Integer> topologicalOrder = new Stack<>();

        // Step 1: Perform DFS to get topological order
        for (int i = 1; i < n; i++) {
            if (!visited[i]) {
                dfsForTopologicalOrder(i, visited, topologicalOrder);
            }
        }

        Arrays.fill(visited, false);
        int sccId = 0;

        // Step 2: Find SCCs on transposed graph
        while (!topologicalOrder.isEmpty()) {
            int node = topologicalOrder.pop();
            if (!visited[node]) {
                dfsForScc(node, visited, component, sccId);
                sccId++;
            }
        }

        // Step 3: Check for contradictions and assign values
        for (int i = 1; i <= numberOfVariables; i++) {
            int notI = negate(i);
            if (component[i] == component[notI]) {
                hasSolution = false;
                return;
            }
            // If SCC(i) > SCC(¬i), then variable i is true.
            variableAssignments[i] = component[i] > component[notI];
        }
    }

    /**
     * Returns whether the given boolean formula is satisfiable.
     *
     * @return {@code true} if a solution exists; {@code false} otherwise
     * @throws Error if called before {@link #solve()}
     */
    boolean isSolutionExists() {
        if (!isSolved) {
            throw new Error("Please call solve() before checking for a solution.");
        }
        return hasSolution;
    }

    /**
     * Returns one valid assignment of variables that satisfies the boolean formula.
     *
     * @return a boolean array where {@code result[i]} represents the truth value of
     *         variable {@code xᵢ}
     * @throws Error if called before {@link #solve()} or if no solution exists
     */
    boolean[] getSolutions() {
        if (!isSolved) {
            throw new Error("Please call solve() before fetching the solution.");
        }
        if (!hasSolution) {
            throw new Error("No satisfying assignment exists for the given expression.");
        }
        return variableAssignments.clone();
    }

    /** Performs DFS to compute topological order. */
    private void dfsForTopologicalOrder(int u, boolean[] visited, Stack<Integer> topologicalOrder) {
        visited[u] = true;
        for (int v : graph[u]) {
            if (!visited[v]) {
                dfsForTopologicalOrder(v, visited, topologicalOrder);
            }
        }
        topologicalOrder.push(u);
    }

    /** Performs DFS on the transposed graph to identify SCCs. */
    private void dfsForScc(int u, boolean[] visited, int[] component, int sccId) {
        visited[u] = true;
        component[u] = sccId;
        for (int v : graphTranspose[u]) {
            if (!visited[v]) {
                dfsForScc(v, visited, component, sccId);
            }
        }
    }

    /**
     * Returns the index representing the negation of the given variable.
     *
     * <p>
     * Mapping rule:
     * </p>
     *
     * <pre>
     * For a variable i:
     *     negate(i) = i + n
     * For a negated variable (i + n):
     *     negate(i + n) = i
     * where n = numberOfVariables
     * </pre>
     *
     * @param a the variable index
     * @return the index representing its negation
     */
    private int negate(int a) {
        return a <= numberOfVariables ? a + numberOfVariables : a - numberOfVariables;
    }
}
