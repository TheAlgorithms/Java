package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class RabinKarpTest {

    @Test
    public void testRabinKarpSearch() {
        assertEquals(List.of(0, 1), RabinKarp.search("AAAAABAAABA", "AAAA"));
        assertEquals(List.of(0, 3), RabinKarp.search("ABCABC", "ABC"));
        assertEquals(List.of(10), RabinKarp.search("ABABDABACDABABCABAB", "ABABCABAB"));
        assertEquals(List.of(), RabinKarp.search("ABCDE", "FGH"));
        assertEquals(List.of(), RabinKarp.search("A", "AA"));
        assertEquals(List.of(0, 1, 2), RabinKarp.search("AAA", "A"));
        assertEquals(List.of(0), RabinKarp.search("A", "A"));
        assertEquals(List.of(), RabinKarp.search("", "A"));
        assertEquals(List.of(), RabinKarp.search("A", ""));
    }
}
