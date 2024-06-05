package com.thealgorithms.others;

import java.util.Scanner;

class PageRank {

    public static void main(String[] args) {
        int nodes;
        int i;
        int j;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Number of WebPages: ");
        nodes = in.nextInt();
        PageRank p = new PageRank();
        System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two WebPages: ");
        for (i = 1; i <= nodes; i++) {
            for (j = 1; j <= nodes; j++) {
                p.path[i][j] = in.nextInt();
                if (j == i) {
                    p.path[i][j] = 0;
                }
            }
        }
        p.calc(nodes);
    }

    public int[][] path = new int[10][10];
    public double[] pagerank = new double[10];

    public void calc(double totalNodes) {
        double initialPageRank;
        double outgoingLinks = 0;
        double dampingFactor = 0.85;
        double[] tempPageRank = new double[10];
        int externalNodeNumber;
        int internalNodeNumber;
        int k = 1; // For Traversing
        int iterationStep = 1;
        initialPageRank = 1 / totalNodes;
        System.out.printf(" Total Number of Nodes :" + totalNodes + "\t Initial PageRank  of All Nodes :" + initialPageRank + "\n");

        // 0th ITERATION _ OR _ INITIALIZATION PHASE //
        for (k = 1; k <= totalNodes; k++) {
            this.pagerank[k] = initialPageRank;
        }
        System.out.print("\n Initial PageRank Values , 0th Step \n");

        for (k = 1; k <= totalNodes; k++) {
            System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
        }

        while (iterationStep <= 2) { // Iterations
            // Store the PageRank for All Nodes in Temporary Array
            for (k = 1; k <= totalNodes; k++) {
                tempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (internalNodeNumber = 1; internalNodeNumber <= totalNodes; internalNodeNumber++) {
                for (externalNodeNumber = 1; externalNodeNumber <= totalNodes; externalNodeNumber++) {
                    if (this.path[externalNodeNumber][internalNodeNumber] == 1) {
                        k = 1;
                        outgoingLinks = 0; // Count the Number of Outgoing Links for each externalNodeNumber
                        while (k <= totalNodes) {
                            if (this.path[externalNodeNumber][k] == 1) {
                                outgoingLinks = outgoingLinks + 1; // Counter for Outgoing Links
                            }
                            k = k + 1;
                        }
                        // Calculate PageRank
                        this.pagerank[internalNodeNumber] += tempPageRank[externalNodeNumber] * (1 / outgoingLinks);
                    }
                }
                System.out.printf("\n After " + iterationStep + "th Step \n");

                for (k = 1; k <= totalNodes; k++) {
                    System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
                }

                iterationStep = iterationStep + 1;
            }

            // Add the Damping Factor to PageRank
            for (k = 1; k <= totalNodes; k++) {
                this.pagerank[k] = (1 - dampingFactor) + dampingFactor * this.pagerank[k];
            }

            // Display PageRank
            System.out.print("\n Final Page Rank : \n");
            for (k = 1; k <= totalNodes; k++) {
                System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
            }
        }
    }
}
