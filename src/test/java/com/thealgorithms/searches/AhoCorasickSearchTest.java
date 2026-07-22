package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class AhoCorasickSearchTest {

    @Test
    void testBasicSearch() {
        String text = "ahishers";
        String[] patterns = {"he", "she", "his", "hers"};

        List<AhoCorasickSearch.Match> matches = AhoCorasickSearch.search(text, patterns);

        assertEquals(4, matches.size());
        assertEquals(new AhoCorasickSearch.Match("his", 1), matches.get(0));
        assertEquals(new AhoCorasickSearch.Match("she", 3), matches.get(1));
        assertEquals(new AhoCorasickSearch.Match("he", 4), matches.get(2));
        assertEquals(new AhoCorasickSearch.Match("hers", 4), matches.get(3));
    }

    @Test
    void testEmptyText() {
        String text = "";
        String[] patterns = {"he", "she"};
        List<AhoCorasickSearch.Match> matches = AhoCorasickSearch.search(text, patterns);
        assertTrue(matches.isEmpty());
    }

    @Test
    void testNullText() {
        String[] patterns = {"he", "she"};
        List<AhoCorasickSearch.Match> matches = AhoCorasickSearch.search(null, patterns);
        assertTrue(matches.isEmpty());
    }

    @Test
    void testEmptyPatterns() {
        String text = "ahishers";
        String[] patterns = {};
        List<AhoCorasickSearch.Match> matches = AhoCorasickSearch.search(text, patterns);
        assertTrue(matches.isEmpty());
    }

    @Test
    void testNullPatterns() {
        String text = "ahishers";
        List<AhoCorasickSearch.Match> matches = AhoCorasickSearch.search(text, null);
        assertTrue(matches.isEmpty());
    }

    @Test
    void testNoMatches() {
        String text = "ahishers";
        String[] patterns = {"xyz", "abc"};
        List<AhoCorasickSearch.Match> matches = AhoCorasickSearch.search(text, patterns);
        assertTrue(matches.isEmpty());
    }

    @Test
    void testOverlappingMatches() {
        String text = "aaaa";
        String[] patterns = {"a", "aa", "aaa", "aaaa"};
        List<AhoCorasickSearch.Match> matches = AhoCorasickSearch.search(text, patterns);

        // Indices matches:
        // i = 0 ('a'): "a" at 0
        // i = 1 ('a'): "a" at 1, "aa" at 0
        // i = 2 ('a'): "a" at 2, "aa" at 1, "aaa" at 0
        // i = 3 ('a'): "a" at 3, "aa" at 2, "aaa" at 1, "aaaa" at 0
        assertEquals(10, matches.size());
        assertEquals(new AhoCorasickSearch.Match("a", 0), matches.get(0));
        assertEquals(new AhoCorasickSearch.Match("aa", 0), matches.get(1));
        assertEquals(new AhoCorasickSearch.Match("a", 1), matches.get(2));
        assertEquals(new AhoCorasickSearch.Match("aaa", 0), matches.get(3));
        assertEquals(new AhoCorasickSearch.Match("aa", 1), matches.get(4));
        assertEquals(new AhoCorasickSearch.Match("a", 2), matches.get(5));
        assertEquals(new AhoCorasickSearch.Match("aaaa", 0), matches.get(6));
        assertEquals(new AhoCorasickSearch.Match("aaa", 1), matches.get(7));
        assertEquals(new AhoCorasickSearch.Match("aa", 2), matches.get(8));
        assertEquals(new AhoCorasickSearch.Match("a", 3), matches.get(9));
    }
}
