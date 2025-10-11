package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class CombinationSumTest {
    private static List<List<Integer>> norm(Iterable<List<Integer>> x) {
        List<List<Integer>> y = new ArrayList<>();
        for (var p : x) {
            var q = new ArrayList<>(p);
            q.sort(Integer::compare);
            y.add(q);
        }
        y.sort(Comparator.<List<Integer>>comparingInt(List::size).thenComparing(Object::toString));
        return y;
    }

    @Test
    void sample() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        var expected = List.of(List.of(2, 2, 3), List.of(7));
        assertEquals(norm(expected), norm(CombinationSum.combinationSum(candidates, target)));
    }
}
