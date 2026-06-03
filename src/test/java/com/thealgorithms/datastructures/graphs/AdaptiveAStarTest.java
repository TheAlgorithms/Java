package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AdaptiveAStarTest {

    /**
     * Builds a simple grid-like graph used for indoor/outdoor navigation testing.
     *
     *   0 --1-- 1 --1-- 2
     *   |       |       |
     *   1       3       1
     *   |       |       |
     *   3 --1-- 4 --1-- 5
     *
     * Semantic risk defined as:
     * - Node 1: construction zone (risk = 2.0)
     * - Node 4: sidewalk (risk = 0.5)
     * - Others: normal (risk = 0.0)
     */
    private AdaptiveAStar.Graph buildTestGraph() {
        AdaptiveAStar.Graph graph = new AdaptiveAStar.Graph(6);
        graph.addBidirectionalEdge(0, 1, 1);
        graph.addBidirectionalEdge(0, 3, 1);
        graph.addBidirectionalEdge(1, 2, 1);
        graph.addBidirectionalEdge(1, 4, 3);
        graph.addBidirectionalEdge(3, 4, 1);
        graph.addBidirectionalEdge(4, 5, 1);
        graph.addBidirectionalEdge(2, 5, 1);
        return graph;
    }

    @Test
    public void testClassicalAStarEquivalence() {
        // When lambda = 0, Adaptive A* should behave identically to classical A*
        AdaptiveAStar.Graph graph = buildTestGraph();
        int[] heuristic = {3, 2, 1, 2, 1, 0}; // Manhattan distance to node 5
        double[] semanticRisk = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        AdaptiveAStar.PathResult result = AdaptiveAStar.findPath(0, 5, graph, heuristic, semanticRisk, 0.0);

        assertTrue(result.isFound());
        assertEquals(3, result.getTotalCost()); // shortest path cost: 0→3→4→5 (1+1+1) or 0→1→2→5 (1+1+1)
    }

    @Test
    public void testAvoidsHighRiskConstructionZone() {
        // Node 1 has high risk (construction zone, R_sem=2.0)
        // With lambda=2.0, the algorithm should avoid node 1
        // Path via node 1 (0→1→2→5): cost=3, heuristic path=2+2+1+0
        //   f(1) = 1 + 2 + 2*2 = 7
        // Path via node 3 (0→3→4→5): cost=3, no risk
        //   f(3) = 1 + 2 + 2*0 = 3
        AdaptiveAStar.Graph graph = buildTestGraph();
        int[] heuristic = {3, 2, 1, 2, 1, 0};
        double[] semanticRisk = {0.0, 2.0, 0.0, 0.0, 0.5, 0.0};

        AdaptiveAStar.PathResult result = AdaptiveAStar.findPath(0, 5, graph, heuristic, semanticRisk, 2.0);

        assertTrue(result.isFound());
        assertEquals(3, result.getTotalCost());
        // Should prefer safer route via nodes 3→4→5
        assertEquals(List.of(0, 3, 4, 5), result.getPath());
    }

    @Test
    public void testPrefersLowRiskSidewalk() {
        // When construction risk is high, prefer sidewalk (lower semantic risk)
        // Path 0→1→2→5 has risk at node 1 (2.0)
        // Path 0→3→4→5 has mild risk at node 4 (0.5)
        AdaptiveAStar.Graph graph = buildTestGraph();
        int[] heuristic = {3, 2, 1, 2, 1, 0};
        double[] semanticRisk = {0.0, 2.0, 0.0, 0.0, 0.5, 0.0};

        AdaptiveAStar.PathResult result = AdaptiveAStar.findPath(0, 5, graph, heuristic, semanticRisk, 1.0);

        assertTrue(result.isFound());
        assertEquals(3, result.getTotalCost());
        List<Integer> path = result.getPath();

        // Node 4 (sidewalk, risk=0.5) is preferred over node 1 (construction, risk=2.0)
        assertFalse(path.contains(1), "Construction zone (node 1) should be avoided");
        assertTrue(path.contains(4), "Sidewalk (node 4) should be preferred");
    }

    @Test
    public void testSemanticRiskOverridesShorterPath() {
        // Strong semantic weight (lambda=5.0) makes the algorithm detour
        // around construction zone despite longer actual distance
        AdaptiveAStar.Graph graph = buildTestGraph();
        int[] heuristic = {3, 2, 1, 2, 1, 0};
        double[] semanticRisk = {0.0, 10.0, 0.0, 0.0, 0.0, 0.0};

        AdaptiveAStar.PathResult result = AdaptiveAStar.findPath(0, 5, graph, heuristic, semanticRisk, 5.0);

        assertTrue(result.isFound());
        assertFalse(result.getPath().contains(1),
            "Very high risk node should be avoided even if path is longer");
    }

    @Test
    public void testStartNodeEqualsGoal() {
        AdaptiveAStar.Graph graph = buildTestGraph();
        int[] heuristic = {0, 2, 1, 2, 1, 0};
        double[] semanticRisk = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        AdaptiveAStar.PathResult result = AdaptiveAStar.findPath(0, 0, graph, heuristic, semanticRisk, 0.0);

        assertTrue(result.isFound());
        assertEquals(0, result.getTotalCost());
        assertEquals(List.of(0), result.getPath());
    }

    @Test
    public void testNoPathExists() {
        // Two disconnected components
        AdaptiveAStar.Graph graph = new AdaptiveAStar.Graph(4);
        graph.addBidirectionalEdge(0, 1, 1); // component 1
        graph.addBidirectionalEdge(2, 3, 1); // component 2

        int[] heuristic = {3, 2, 1, 0};
        double[] semanticRisk = {0.0, 0.0, 0.0, 0.0};

        AdaptiveAStar.PathResult result = AdaptiveAStar.findPath(0, 3, graph, heuristic, semanticRisk, 0.0);

        assertFalse(result.isFound());
        assertEquals(-1, result.getTotalCost());
    }

    @Test
    public void testInvalidNodeIndex() {
        AdaptiveAStar.Graph graph = buildTestGraph();
        int[] heuristic = {3, 2, 1, 2, 1, 0};
        double[] semanticRisk = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        AdaptiveAStar.PathResult result = AdaptiveAStar.findPath(0, 100, graph, heuristic, semanticRisk, 1.0);

        assertFalse(result.isFound());
        assertEquals(-1, result.getTotalCost());
    }

    @Test
    public void testZeroLambdaBehavesLikeClassicalAStar() {
        // Verify that with lambda=0, risk values don't affect the result
        AdaptiveAStar.Graph graph = buildTestGraph();
        int[] heuristic = {3, 2, 1, 2, 1, 0};

        // High risk on all nodes, but lambda=0 so should be ignored
        double[] highRisk = {5.0, 5.0, 5.0, 5.0, 5.0, 0.0};
        double[] noRisk = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        AdaptiveAStar.PathResult resultHighRisk = AdaptiveAStar.findPath(0, 5, graph, heuristic, highRisk, 0.0);
        AdaptiveAStar.PathResult resultNoRisk = AdaptiveAStar.findPath(0, 5, graph, heuristic, noRisk, 0.0);

        assertTrue(resultHighRisk.isFound());
        assertTrue(resultNoRisk.isFound());
        assertEquals(resultNoRisk.getTotalCost(), resultHighRisk.getTotalCost());
        assertEquals(resultNoRisk.getPath(), resultHighRisk.getPath());
    }
}
