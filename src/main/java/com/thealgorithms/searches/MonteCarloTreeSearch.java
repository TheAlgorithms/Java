package com.thealgorithms.searches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Monte Carlo Tree Search (MCTS) is a heuristic search algorithm used in
 * decition taking problems especially games.
 *
 * See more: https://en.wikipedia.org/wiki/Monte_Carlo_tree_search,
 * https://www.baeldung.com/java-monte-carlo-tree-search
 */
public class MonteCarloTreeSearch {

    public class Node {

        Node parent;
        ArrayList<Node> childNodes;
        boolean isPlayersTurn; // True if it is the player's turn.
        boolean playerWon; // True if the player won; false if the opponent won.
        int score;
        int visitCount;

        public Node() {
        }

        public Node(Node parent, boolean isPlayersTurn) {
            this.parent = parent;
            childNodes = new ArrayList<>();
            this.isPlayersTurn = isPlayersTurn;
            playerWon = false;
            score = 0;
            visitCount = 0;
        }
    }

    static final int WIN_SCORE = 10;
    static final int TIME_LIMIT = 500; // Time the algorithm will be running for (in milliseconds).

    public static void main(String[] args) {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();

        mcts.monteCarloTreeSearch(mcts.new Node(null, true));
    }

    /**
     * Explores a game tree using Monte Carlo Tree Search (MCTS) and returns the
     * most promising node.
     *
     * @param rootNode Root node of the game tree.
     * @return The most promising child of the root node.
     */
    public Node monteCarloTreeSearch(Node rootNode) {
        Node winnerNode;
        double timeLimit;

        // Expand the root node.
        addChildNodes(rootNode, 10);

        timeLimit = System.currentTimeMillis() + TIME_LIMIT;

        // Explore the tree until the time limit is reached.
        while (System.currentTimeMillis() < timeLimit) {
            Node promisingNode;

            // Get a promising node using UCT.
            promisingNode = getPromisingNode(rootNode);

            // Expand the promising node.
            if (promisingNode.childNodes.size() == 0) {
                addChildNodes(promisingNode, 10);
            }

            simulateRandomPlay(promisingNode);
        }

        winnerNode = getWinnerNode(rootNode);
        printScores(rootNode);
        System.out.format("\nThe optimal node is: %02d\n", rootNode.childNodes.indexOf(winnerNode) + 1);

        return winnerNode;
    }

    public void addChildNodes(Node node, int childCount) {
        for (int i = 0; i < childCount; i++) {
            node.childNodes.add(new Node(node, !node.isPlayersTurn));
        }
    }

    /**
     * Uses UCT to find a promising child node to be explored.
     *
     * UCT: Upper Confidence bounds applied to Trees.
     *
     * @param rootNode Root node of the tree.
     * @return The most promising node according to UCT.
     */
    public Node getPromisingNode(Node rootNode) {
        Node promisingNode = rootNode;

        // Iterate until a node that hasn't been expanded is found.
        while (promisingNode.childNodes.size() != 0) {
            double uctIndex = Double.MIN_VALUE;
            int nodeIndex = 0;

            // Iterate through child nodes and pick the most promising one
            // using UCT (Upper Confidence bounds applied to Trees).
            for (int i = 0; i < promisingNode.childNodes.size(); i++) {
                Node childNode = promisingNode.childNodes.get(i);
                double uctTemp;

                // If child node has never been visited
                // it will have the highest uct value.
                if (childNode.visitCount == 0) {
                    nodeIndex = i;
                    break;
                }

                uctTemp = ((double) childNode.score / childNode.visitCount) + 1.41 * Math.sqrt(Math.log(promisingNode.visitCount) / (double) childNode.visitCount);

                if (uctTemp > uctIndex) {
                    uctIndex = uctTemp;
                    nodeIndex = i;
                }
            }

            promisingNode = promisingNode.childNodes.get(nodeIndex);
        }

        return promisingNode;
    }

    /**
     * Simulates a random play from a nodes current state and back propagates
     * the result.
     *
     * @param promisingNode Node that will be simulated.
     */
    public void simulateRandomPlay(Node promisingNode) {
        Random rand = new Random();
        Node tempNode = promisingNode;
        boolean isPlayerWinner;

        // The following line randomly determines whether the simulated play is a win or loss.
        // To use the MCTS algorithm correctly this should be a simulation of the nodes' current
        // state of the game until it finishes (if possible) and use an evaluation function to
        // determine how good or bad the play was.
        // e.g. Play tic tac toe choosing random squares until the game ends.
        promisingNode.playerWon = (rand.nextInt(6) == 0);

        isPlayerWinner = promisingNode.playerWon;

        // Back propagation of the random play.
        while (tempNode != null) {
            tempNode.visitCount++;

            // Add wining scores to bouth player and opponent depending on the turn.
            if ((tempNode.isPlayersTurn && isPlayerWinner) || (!tempNode.isPlayersTurn && !isPlayerWinner)) {
                tempNode.score += WIN_SCORE;
            }

            tempNode = tempNode.parent;
        }
    }

    public Node getWinnerNode(Node rootNode) {
        return Collections.max(rootNode.childNodes, Comparator.comparing(c -> c.score));
    }

    public void printScores(Node rootNode) {
        System.out.println("N.\tScore\t\tVisits");

        for (int i = 0; i < rootNode.childNodes.size(); i++) {
            System.out.printf("%02d\t%d\t\t%d%n", i + 1, rootNode.childNodes.get(i).score, rootNode.childNodes.get(i).visitCount);
        }
    }
}
