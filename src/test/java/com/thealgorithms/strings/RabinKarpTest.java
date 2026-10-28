package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class RabinKarpTest {

    @Test
    public void testNullInputs() {
        assertEquals(List.of(), RabinKarp.search(null, "A"));
        assertEquals(List.of(), RabinKarp.search("A", null));
        assertEquals(List.of(), RabinKarp.search(null, null));
    }

    @Test
    public void testHashCollision() {
        // 'a' = 97. (char)198 % 101 = 97.
        // For length 1, h = 1. p = 97. t = 198 % 101 = 97.
        // Collision occurs, loop checks characters: 198 != 97, breaks.
        char collisionChar = (char) 198;
        String text = String.valueOf(collisionChar);
        String pattern = "a";
        assertEquals(List.of(), RabinKarp.search(text, pattern));
    }

    @Test
    public void testSearchWithCustomQ() {
        // Using a different prime
        assertEquals(List.of(0, 1), RabinKarp.search("AAAA", "AAA", 13));
    }

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
