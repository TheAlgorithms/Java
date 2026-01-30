package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class KMPTest {

    @Test
    public void testNullInputs() {
        assertEquals(List.of(), KMP.kmpMatcher(null, "A"));
        assertEquals(List.of(), KMP.kmpMatcher("A", null));
        assertEquals(List.of(), KMP.kmpMatcher(null, null));
    }

    @Test
    public void testKMPMatcher() {
        assertEquals(List.of(0, 1), KMP.kmpMatcher("AAAAABAAABA", "AAAA"));
        assertEquals(List.of(0, 3), KMP.kmpMatcher("ABCABC", "ABC"));
        assertEquals(List.of(10), KMP.kmpMatcher("ABABDABACDABABCABAB", "ABABCABAB"));
        assertEquals(List.of(), KMP.kmpMatcher("ABCDE", "FGH"));
        assertEquals(List.of(), KMP.kmpMatcher("A", "AA"));
        assertEquals(List.of(0, 1, 2), KMP.kmpMatcher("AAA", "A"));
        assertEquals(List.of(0), KMP.kmpMatcher("A", "A"));
        assertEquals(List.of(), KMP.kmpMatcher("", "A"));
        assertEquals(List.of(), KMP.kmpMatcher("A", ""));
    }
}
