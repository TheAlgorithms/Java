package com.thealgorithms.backtracking;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class AStarSearchTest {

    @Test
    void testSimpleAStar() {
        AStarSearch aStar = new AStarSearch();

        List<List<AStarSearch.Edge>> graph = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new AStarSearch.Edge(1, 1.0)); 
        graph.get(1).add(new AStarSearch.Edge(2, 1.0)); 
        graph.get(2).add(new AStarSearch.Edge(3, 1.0)); 
        graph.get(3).add(new AStarSearch.Edge(4, 1.0)); 
        graph.get(4).add(new AStarSearch.Edge(5, 1.0));

        Map<Integer, int[]> coordinates = new HashMap<>();
        coordinates.put(0, new int[]{0, 0});
        coordinates.put(1, new int[]{1, 1});
        coordinates.put(2, new int[]{2, 2});
        coordinates.put(3, new int[]{3, 3});
        coordinates.put(4, new int[]{4, 4});
        coordinates.put(5, new int[]{5, 5});

        List<Integer> path = aStar.aStar(graph, new int[]{0, 0}, new int[]{5, 5}, coordinates);
        
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5), path);
    }
}
