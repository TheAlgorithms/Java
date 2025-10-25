package com.thealgorithms.graph;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.thealgorithms.graph.PredecessorConstrainedDfs.TraversalEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PredecessorConstrainedDfsTest {

    // A -> B, A -> C, B -> D, C -> D   (classic diamond)
    private static Map<String, List<String>> diamond() {
        Map<String, List<String>> g = new LinkedHashMap<>();
        g.put("A", List.of("B", "C"));
        g.put("B", List.of("D"));
        g.put("C", List.of("D"));
        g.put("D", List.of());
        return g;
    }

    @Test
    void dfsRecursiveOrderEmitsSkipUntilAllParentsVisited() {
        List<TraversalEvent<String>> events = PredecessorConstrainedDfs.dfsRecursiveOrder(diamond(), "A");

        // Expect visits in order and a skip for first time we meet D (via B) before C is visited.
        var visits = events.stream().filter(TraversalEvent::isVisit).toList();
        var skips = events.stream().filter(TraversalEvent::isSkip).toList();

        // Visits should be A(0), B(1), C(2), D(3) in some deterministic order given adjacency
        assertThat(visits).hasSize(4);
        assertThat(visits.get(0).node()).isEqualTo("A");
        assertThat(visits.get(0).order()).isEqualTo(0);
        assertThat(visits.get(1).node()).isEqualTo("B");
        assertThat(visits.get(1).order()).isEqualTo(1);
        assertThat(visits.get(2).node()).isEqualTo("C");
        assertThat(visits.get(2).order()).isEqualTo(2);
        assertThat(visits.get(3).node()).isEqualTo("D");
        assertThat(visits.get(3).order()).isEqualTo(3);

        // One skip when we first encountered D from B (before C was visited)
        assertThat(skips).hasSize(1);
        assertThat(skips.get(0).node()).isEqualTo("D");
        assertThat(skips.get(0).note()).contains("not all parents");
    }

    @Test
    void returnsEmptyWhenStartNotInGraph() {
        Map<Integer, List<Integer>> graph = Map.of(1, List.of(2), 2, List.of(1));
        assertThat(PredecessorConstrainedDfs.dfsRecursiveOrder(graph, 99)).isEmpty();
    }

    @Test
    void nullSuccessorsThrows() {
        assertThrows(IllegalArgumentException.class, () -> PredecessorConstrainedDfs.dfsRecursiveOrder(null, "A"));
    }

    @Test
    void worksWithExplicitPredecessors() {
        Map<Integer, List<Integer>> successors = new HashMap<>();
        successors.put(10, List.of(20));
        successors.put(20, List.of(30));
        successors.put(30, List.of());

        Map<Integer, List<Integer>> predecessors = new HashMap<>();
        predecessors.put(10, List.of());
        predecessors.put(20, List.of(10));
        predecessors.put(30, List.of(20));

        var events = PredecessorConstrainedDfs.dfsRecursiveOrder(successors, predecessors, 10);
        var visitNodes = events.stream().filter(TraversalEvent::isVisit).map(TraversalEvent::node).toList();
        assertThat(visitNodes).containsExactly(10, 20, 30);
    }

    @Test
    void cycleProducesSkipsButNoInfiniteRecursion() {
        Map<String, List<String>> successors = new LinkedHashMap<>();
        successors.put("X", List.of("Y"));
        successors.put("Y", List.of("X")); // 2-cycle

        var events = PredecessorConstrainedDfs.dfsRecursiveOrder(successors, "X");
        // Only X is visited; encountering Y from X causes skip because Y's parent X is visited,
        // but when recursing to Y we'd hit back to X (already visited) and stop; no infinite loop.
        assertThat(events.stream().anyMatch(TraversalEvent::isVisit)).isTrue();
        assertThat(events.stream().filter(TraversalEvent::isVisit).map(TraversalEvent::node)).contains("X");
    }
}
