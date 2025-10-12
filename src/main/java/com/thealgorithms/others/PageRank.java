package com.thealgorithms.others;

import java.util.Scanner;

/**
 * PageRank Algorithm Implementation
 *
 * <p>
 * The PageRank algorithm is used by Google Search to rank web pages in their
 * search engine
 * results. It was named after Larry Page, one of the founders of Google.
 * PageRank is a way of
 * measuring the importance of website pages.
 *
 * <p>
 * Algorithm: 1. Initialize PageRank values for all pages to 1/N (where N is the
 * total number
 * of pages) 2. For each iteration: - For each page, calculate the new PageRank
 * by summing the
 * contributions from all incoming links - Apply the damping factor: PR(page) =
 * (1-d) + d *
 * sum(PR(incoming_page) / outgoing_links(incoming_page)) 3. Repeat until
 * convergence
 *
 * @see <a href="https://en.wikipedia.org/wiki/PageRank">PageRank Algorithm</a>
 */
public final class PageRank {

    private static final int MAX_NODES = 10;
    private static final double DEFAULT_DAMPING_FACTOR = 0.85;
    private static final int DEFAULT_ITERATIONS = 2;

    private int[][] adjacencyMatrix;
    private double[] pageRankValues;
    private int nodeCount;

    /**
     * Constructor to initialize PageRank with specified number of nodes
     *
     * @param numberOfNodes the number of nodes/pages in the graph
     * @throws IllegalArgumentException if numberOfNodes is less than 1 or greater
     *                                  than MAX_NODES
     */
    public PageRank(int numberOfNodes) {
        if (numberOfNodes < 1 || numberOfNodes > MAX_NODES) {
            throw new IllegalArgumentException("Number of nodes must be between 1 and " + MAX_NODES);
        }
        this.nodeCount = numberOfNodes;
        this.adjacencyMatrix = new int[MAX_NODES][MAX_NODES];
        this.pageRankValues = new double[MAX_NODES];
    }

    /**
     * Default constructor for interactive mode
     */
    public PageRank() {
        this.adjacencyMatrix = new int[MAX_NODES][MAX_NODES];
        this.pageRankValues = new double[MAX_NODES];
    }

    /**
     * Main method for interactive PageRank calculation
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the Number of WebPages: ");
            int nodes = scanner.nextInt();

            PageRank pageRank = new PageRank(nodes);
            System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two WebPages: ");

            for (int i = 1; i <= nodes; i++) {
                for (int j = 1; j <= nodes; j++) {
                    int value = scanner.nextInt();
                    pageRank.setEdge(i, j, value);
                }
            }

            pageRank.calculatePageRank(nodes, DEFAULT_DAMPING_FACTOR, DEFAULT_ITERATIONS, true);
        }
    }

    /**
     * Sets an edge in the adjacency matrix
     *
     * @param from  source node (1-indexed)
     * @param to    destination node (1-indexed)
     * @param value 1 if edge exists, 0 otherwise
     */
    public void setEdge(int from, int to, int value) {
        if (from == to) {
            adjacencyMatrix[from][to] = 0; // No self-loops
        } else {
            adjacencyMatrix[from][to] = value;
        }
    }

    /**
     * Sets the adjacency matrix for the graph
     *
     * @param matrix the adjacency matrix (1-indexed)
     */
    public void setAdjacencyMatrix(int[][] matrix) {
        for (int i = 1; i <= nodeCount; i++) {
            for (int j = 1; j <= nodeCount; j++) {
                setEdge(i, j, matrix[i][j]);
            }
        }
    }

    /**
     * Gets the PageRank value for a specific node
     *
     * @param node the node index (1-indexed)
     * @return the PageRank value
     */
    public double getPageRank(int node) {
        if (node < 1 || node > nodeCount) {
            throw new IllegalArgumentException("Node index out of bounds");
        }
        return pageRankValues[node];
    }

    /**
     * Gets all PageRank values
     *
     * @return array of PageRank values (1-indexed)
     */
    public double[] getAllPageRanks() {
        return pageRankValues.clone();
    }

    /**
     * Calculates PageRank using the default damping factor and iterations
     *
     * @param totalNodes the total number of nodes
     * @return array of PageRank values
     */
    public double[] calculatePageRank(int totalNodes) {
        return calculatePageRank(totalNodes, DEFAULT_DAMPING_FACTOR, DEFAULT_ITERATIONS, false);
    }

    /**
     * Calculates PageRank with custom parameters
     *
     * @param totalNodes    the total number of nodes
     * @param dampingFactor the damping factor (typically 0.85)
     * @param iterations    number of iterations to perform
     * @param verbose       whether to print detailed output
     * @return array of PageRank values
     */
    public double[] calculatePageRank(int totalNodes, double dampingFactor, int iterations, boolean verbose) {
        validateInputParameters(totalNodes, dampingFactor, iterations);

        this.nodeCount = totalNodes;
        double initialPageRank = 1.0 / totalNodes;

        if (verbose) {
            System.out.printf("Total Number of Nodes: %d\tInitial PageRank of All Nodes: %.6f%n", totalNodes, initialPageRank);
        }

        initializePageRanks(totalNodes, initialPageRank, verbose);
        performIterations(totalNodes, dampingFactor, iterations, verbose);

        if (verbose) {
            System.out.println("\nFinal PageRank:");
            printPageRanks(totalNodes);
        }

        return pageRankValues.clone();
    }

    /**
     * Validates input parameters for PageRank calculation
     *
     * @param totalNodes    the total number of nodes
     * @param dampingFactor the damping factor
     * @param iterations    number of iterations
     * @throws IllegalArgumentException if parameters are invalid
     */
    private void validateInputParameters(int totalNodes, double dampingFactor, int iterations) {
        if (totalNodes < 1 || totalNodes > MAX_NODES) {
            throw new IllegalArgumentException("Total nodes must be between 1 and " + MAX_NODES);
        }
        if (dampingFactor < 0 || dampingFactor > 1) {
            throw new IllegalArgumentException("Damping factor must be between 0 and 1");
        }
        if (iterations < 1) {
            throw new IllegalArgumentException("Iterations must be at least 1");
        }
    }

    /**
     * Initializes PageRank values for all nodes
     *
     * @param totalNodes      the total number of nodes
     * @param initialPageRank the initial PageRank value
     * @param verbose         whether to print output
     */
    private void initializePageRanks(int totalNodes, double initialPageRank, boolean verbose) {
        for (int i = 1; i <= totalNodes; i++) {
            pageRankValues[i] = initialPageRank;
        }

        if (verbose) {
            System.out.println("\nInitial PageRank Values, 0th Step");
            printPageRanks(totalNodes);
        }
    }

    /**
     * Performs the iterative PageRank calculation
     *
     * @param totalNodes    the total number of nodes
     * @param dampingFactor the damping factor
     * @param iterations    number of iterations
     * @param verbose       whether to print output
     */
    private void performIterations(int totalNodes, double dampingFactor, int iterations, boolean verbose) {
        for (int iteration = 1; iteration <= iterations; iteration++) {
            double[] tempPageRank = storeCurrentPageRanks(totalNodes);
            calculateNewPageRanks(totalNodes, tempPageRank);
            applyDampingFactor(totalNodes, dampingFactor);

            if (verbose) {
                System.out.printf("%nAfter %d iteration(s)%n", iteration);
                printPageRanks(totalNodes);
            }
        }
    }

    /**
     * Stores current PageRank values in a temporary array
     *
     * @param totalNodes the total number of nodes
     * @return temporary array with current PageRank values
     */
    private double[] storeCurrentPageRanks(int totalNodes) {
        double[] tempPageRank = new double[MAX_NODES];
        for (int i = 1; i <= totalNodes; i++) {
            tempPageRank[i] = pageRankValues[i];
            pageRankValues[i] = 0;
        }
        return tempPageRank;
    }

    /**
     * Calculates new PageRank values based on incoming links
     *
     * @param totalNodes   the total number of nodes
     * @param tempPageRank temporary array with previous PageRank values
     */
    private void calculateNewPageRanks(int totalNodes, double[] tempPageRank) {
        for (int targetNode = 1; targetNode <= totalNodes; targetNode++) {
            for (int sourceNode = 1; sourceNode <= totalNodes; sourceNode++) {
                if (adjacencyMatrix[sourceNode][targetNode] == 1) {
                    int outgoingLinks = countOutgoingLinks(sourceNode, totalNodes);
                    if (outgoingLinks > 0) {
                        pageRankValues[targetNode] += tempPageRank[sourceNode] / outgoingLinks;
                    }
                }
            }
        }
    }

    /**
     * Applies the damping factor to all PageRank values
     *
     * @param totalNodes    the total number of nodes
     * @param dampingFactor the damping factor
     */
    private void applyDampingFactor(int totalNodes, double dampingFactor) {
        for (int i = 1; i <= totalNodes; i++) {
            pageRankValues[i] = (1 - dampingFactor) + dampingFactor * pageRankValues[i];
        }
    }

    /**
     * Counts the number of outgoing links from a node
     *
     * @param node       the source node (1-indexed)
     * @param totalNodes total number of nodes
     * @return the count of outgoing links
     */
    private int countOutgoingLinks(int node, int totalNodes) {
        int count = 0;
        for (int i = 1; i <= totalNodes; i++) {
            if (adjacencyMatrix[node][i] == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * Prints the PageRank values for all nodes
     *
     * @param totalNodes the total number of nodes
     */
    private void printPageRanks(int totalNodes) {
        for (int i = 1; i <= totalNodes; i++) {
            System.out.printf("PageRank of %d: %.6f%n", i, pageRankValues[i]);
        }
    }
}
