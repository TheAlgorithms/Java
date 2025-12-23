package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Test cases for Kruskal's Minimum Spanning Tree algorithm. */
class KruskalMSTTest {

  @Test
  void testFindMSTWithSimpleGraph() {
    final int vertices = 4;

    final List<Edge> edges = new ArrayList<>();
    edges.add(new Edge(0, 1, 10));
    edges.add(new Edge(0, 2, 6));
    edges.add(new Edge(0, 3, 5));
    edges.add(new Edge(1, 3, 15));
    edges.add(new Edge(2, 3, 4));

    final List<Edge> mst = KruskalMST.findMST(vertices, edges);

    assertNotNull(mst, "MST should not be null");
    assertEquals(vertices - 1, mst.size(), "MST should contain V-1 edges");

    int totalWeight = 0;
    for (final Edge edge : mst) {
      totalWeight += edge.weight;
    }

    assertEquals(19, totalWeight, "Total weight of MST is incorrect");
  }

  @Test
  void testFindMSTWithSingleVertex() {
    final int vertices = 1;
    final List<Edge> edges = new ArrayList<>();

    final List<Edge> mst = KruskalMST.findMST(vertices, edges);

    assertNotNull(mst, "MST should not be null");
    assertEquals(0, mst.size(), "MST of single vertex graph should be empty");
  }

  @Test
  void testInvalidVertexCountThrowsException() {
    final List<Edge> edges = new ArrayList<>();

    assertThrows(
        IllegalArgumentException.class,
        () -> KruskalMST.findMST(0, edges),
        "Expected exception for non-positive vertex count");
  }

  @Test
  void testPathCompressionScenario() {
    final int vertices = 5;

    final List<Edge> edges = new ArrayList<>();
    edges.add(new Edge(0, 1, 1));
    edges.add(new Edge(1, 2, 2));
    edges.add(new Edge(2, 3, 3));
    edges.add(new Edge(3, 4, 4));

    final List<Edge> mst = KruskalMST.findMST(vertices, edges);

    assertNotNull(mst);
    assertEquals(vertices - 1, mst.size(), "MST should contain V-1 edges");
  }
}
