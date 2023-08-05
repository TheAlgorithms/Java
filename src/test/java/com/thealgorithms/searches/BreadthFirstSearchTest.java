package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class BreadthFirstSearchTest {

    private static final DepthFirstSearch.Node rootNode = new DepthFirstSearch.Node("A",
        List.of(new DepthFirstSearch.Node("B", List.of(new DepthFirstSearch.Node("D"), new DepthFirstSearch.Node("F", List.of(new DepthFirstSearch.Node("H"), new DepthFirstSearch.Node("I"))))), new DepthFirstSearch.Node("C", List.of(new DepthFirstSearch.Node("G"))), new DepthFirstSearch.Node("E")));

    @Test
    void searchI() {
        Optional<DepthFirstSearch.Node> Inode = BreadthFirstSearch.search(rootNode, "I");
        assertTrue(Inode.isPresent());
        assertEquals(Inode.get().getName(), "I");
    }

    @Test
    void searchG() {
        Optional<DepthFirstSearch.Node> Gnode = BreadthFirstSearch.search(rootNode, "G");
        assertTrue(Gnode.isPresent());
        assertEquals(Gnode.get().getName(), "G");
    }

    @Test
    void searchE() {
        Optional<DepthFirstSearch.Node> Enode = BreadthFirstSearch.search(rootNode, "E");
        assertTrue(Enode.isPresent());
        assertEquals(Enode.get().getName(), "E");
    }
}