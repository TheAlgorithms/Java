package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MonteCarloTreeSearchTest {

    /**
     * Test the creation of a node and its initial state.
     */
    @Test
    void testNodeCreation() {
        MonteCarloTreeSearch.Node node = new MonteCarloTreeSearch().new Node(null, true);
        assertNotNull(node, "Node should be created");
        assertTrue(node.childNodes.isEmpty(), "Child nodes should be empty upon creation");
        assertTrue(node.isPlayersTurn, "Initial turn should be player's turn");
        assertEquals(0, node.score, "Initial score should be zero");
        assertEquals(0, node.visitCount, "Initial visit count should be zero");
    }

    /**
     * Test adding child nodes to a parent node.
     */
    @Test
    void testAddChildNodes() {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
        MonteCarloTreeSearch.Node parentNode = mcts.new Node(null, true);

        mcts.addChildNodes(parentNode, 5);

        assertEquals(5, parentNode.childNodes.size(), "Parent should have 5 child nodes");
        for (MonteCarloTreeSearch.Node child : parentNode.childNodes) {
            assertFalse(child.isPlayersTurn, "Child node should not be player's turn");
            assertEquals(0, child.visitCount, "Child node visit count should be zero");
        }
    }

    /**
     * Test the UCT selection of a promising node.
     */
    @Test
    void testGetPromisingNode() {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
        MonteCarloTreeSearch.Node parentNode = mcts.new Node(null, true);

        // Create child nodes with different visit counts and scores
        for (int i = 0; i < 3; i++) {
            MonteCarloTreeSearch.Node child = mcts.new Node(parentNode, false);
            child.visitCount = i + 1;
            child.score = i * 2;
            parentNode.childNodes.add(child);
        }

        // Get promising node
        MonteCarloTreeSearch.Node promisingNode = mcts.getPromisingNode(parentNode);

        // The child with the highest UCT value should be chosen.
        assertNotNull(promisingNode, "Promising node should not be null");
        assertEquals(0, parentNode.childNodes.indexOf(promisingNode), "The first child should be the most promising");
    }

    /**
     * Test simulation of random play and backpropagation.
     */
    @Test
    void testSimulateRandomPlay() {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
        MonteCarloTreeSearch.Node node = mcts.new Node(null, true);
        node.visitCount = 10; // Simulating existing visits

        // Simulate random play
        mcts.simulateRandomPlay(node);

        // Check visit count after simulation
        assertEquals(11, node.visitCount, "Visit count should increase after simulation");

        // Check if score is updated correctly
        assertTrue(node.score >= 0 && node.score <= MonteCarloTreeSearch.WIN_SCORE, "Score should be between 0 and WIN_SCORE");
    }

    /**
     * Test retrieving the winning node based on scores.
     */
    @Test
    void testGetWinnerNode() {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
        MonteCarloTreeSearch.Node parentNode = mcts.new Node(null, true);

        // Create child nodes with varying scores
        MonteCarloTreeSearch.Node winningNode = mcts.new Node(parentNode, false);
        winningNode.score = 10; // Highest score
        parentNode.childNodes.add(winningNode);

        MonteCarloTreeSearch.Node losingNode = mcts.new Node(parentNode, false);
        losingNode.score = 5;
        parentNode.childNodes.add(losingNode);

        MonteCarloTreeSearch.Node anotherLosingNode = mcts.new Node(parentNode, false);
        anotherLosingNode.score = 3;
        parentNode.childNodes.add(anotherLosingNode);

        // Get the winning node
        MonteCarloTreeSearch.Node winnerNode = mcts.getWinnerNode(parentNode);

        assertEquals(winningNode, winnerNode, "Winning node should have the highest score");
    }

    /**
     * Test the full Monte Carlo Tree Search process.
     */
    @Test
    void testMonteCarloTreeSearch() {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
        MonteCarloTreeSearch.Node rootNode = mcts.new Node(null, true);

        // Execute MCTS and check the resulting node
        MonteCarloTreeSearch.Node optimalNode = mcts.monteCarloTreeSearch(rootNode);

        assertNotNull(optimalNode, "MCTS should return a non-null optimal node");
        assertTrue(rootNode.childNodes.contains(optimalNode), "Optimal node should be a child of the root");
    }
}
