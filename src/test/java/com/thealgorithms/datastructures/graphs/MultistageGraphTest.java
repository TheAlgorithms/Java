package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MultistageGraphTest {

@Test
public void testAddEdge_invalidFromStage() {
    MultistageGraph graph = new MultistageGraph(3);

    assertThrows(IllegalArgumentException.class, () -> graph.addEdge(0, 2));
}

@Test
public void testAddEdge_invalidToStage() {
    MultistageGraph graph = new MultistageGraph(3);

    assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 4));
}

@Test
public void testAddEdge_edgeAlreadyExists() {
    MultistageGraph graph = new MultistageGraph(3);

    graph.addEdge(1, 2);
    graph.addEdge(1, 2); // Attempt to add the same edge again

    List<Integer> expectedAdjacentStages = Arrays.asList(2);
    assertEquals(expectedAdjacentStages, graph.adjacencyList.get(0));
}

@Test
public void testAddEdge_printsSuccessMessage() {
    MultistageGraph graph = new MultistageGraph(3);

    // Capture the standard output stream
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStreamCaptor));

    graph.addEdge(1, 2);

    String expectedOutput = "Added edge between stage 1 and stage 2\r\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
}
}
