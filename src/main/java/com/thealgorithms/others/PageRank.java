package com.thealgorithms.others;

import java.util.*;

class PageRank {

    public static void main(String[] args) {
        int nodes, i, j;
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
        double InitialPageRank;
        double OutgoingLinks = 0;
        double DampingFactor = 0.85;
        double[] TempPageRank = new double[10];
        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k = 1; // For Traversing
        int ITERATION_STEP = 1;
        InitialPageRank = 1 / totalNodes;
        System.out.printf(" Total Number of Nodes :" + totalNodes + "\t Initial PageRank  of All Nodes :" + InitialPageRank + "\n");

        // 0th ITERATION _ OR _ INITIALIZATION PHASE //
        for (k = 1; k <= totalNodes; k++) {
            this.pagerank[k] = InitialPageRank;
        }
        System.out.print("\n Initial PageRank Values , 0th Step \n");

        for (k = 1; k <= totalNodes; k++) {
            System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
        }

        while (ITERATION_STEP <= 2) { // Iterations
            // Store the PageRank for All Nodes in Temporary Array
            for (k = 1; k <= totalNodes; k++) {
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (InternalNodeNumber = 1; InternalNodeNumber <= totalNodes; InternalNodeNumber++) {
                for (ExternalNodeNumber = 1; ExternalNodeNumber <= totalNodes; ExternalNodeNumber++) {
                    if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1) {
                        k = 1;
                        OutgoingLinks = 0; // Count the Number of Outgoing Links for each ExternalNodeNumber
                        while (k <= totalNodes) {
                            if (this.path[ExternalNodeNumber][k] == 1) {
                                OutgoingLinks = OutgoingLinks + 1; // Counter for Outgoing Links
                            }
                            k = k + 1;
                        }
                        // Calculate PageRank
                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
                    }
                }
                System.out.printf("\n After " + ITERATION_STEP + "th Step \n");

                for (k = 1; k <= totalNodes; k++) {
                    System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
                }

                ITERATION_STEP = ITERATION_STEP + 1;
            }

            // Add the Damping Factor to PageRank
            for (k = 1; k <= totalNodes; k++) {
                this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
            }

            // Display PageRank
            System.out.print("\n Final Page Rank : \n");
            for (k = 1; k <= totalNodes; k++) {
                System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
            }
        }
    }
}
