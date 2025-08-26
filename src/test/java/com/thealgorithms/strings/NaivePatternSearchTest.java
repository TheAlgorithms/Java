package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

class NaivePatternSearchTest {

    @Test
    void testPatternFound() {
        List<Integer> result = NaivePatternSearch.search("abcdxabchjikabc", "abc");
        assertEquals(List.of(0, 5, 12), result);
    }
}
