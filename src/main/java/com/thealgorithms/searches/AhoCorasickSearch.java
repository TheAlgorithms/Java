package com.thealgorithms.searches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Implementation of the Aho-Corasick string matching algorithm.
 * The Aho-Corasick algorithm is a multi-pattern search algorithm that finds all
 * occurrences of a set of pattern strings within an input text in O(n + m + z) time,
 * where n is the length of the text, m is the total length of all patterns,
 * and z is the number of occurrences.
 */
public final class AhoCorasickSearch {

    private AhoCorasickSearch() {
    }

    /**
     * Represents a match result containing the pattern and the starting index in the text.
     */
    public static class Match {
        private final String pattern;
        private final int index;

        /**
         * Constructs a Match.
         *
         * @param pattern the pattern that was matched
         * @param index   the starting index of the match in the text
         */
        public Match(String pattern, int index) {
            this.pattern = pattern;
            this.index = index;
        }

        /**
         * Gets the matched pattern.
         *
         * @return the pattern
         */
        public String getPattern() {
            return pattern;
        }

        /**
         * Gets the starting index of the match.
         *
         * @return the index
         */
        public int getIndex() {
            return index;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Match other = (Match) obj;
            return index == other.index && pattern.equals(other.pattern);
        }

        @Override
        public int hashCode() {
            int result = pattern.hashCode();
            result = 31 * result + index;
            return result;
        }

        @Override
        public String toString() {
            return pattern + " at index " + index;
        }
    }

    // Trie node structure
    private static class Node {
        // Child transitions: maps character to child Node
        private final Map<Character, Node> children = new HashMap<>();

        // Failure link
        private Node failureLink = null;

        // Output link (points to the nearest node in the failure path that is a pattern end)
        private Node outputLink = null;

        // The pattern index (if this node represents the end of a pattern, else -1)
        private int patternIndex = -1;

        // The pattern string (if this node represents the end of a pattern, else null)
        private String pattern = null;
    }

    /**
     * Searches for occurrences of multiple patterns in the given text using the Aho-Corasick algorithm.
     *
     * @param text     the text to be searched
     * @param patterns the array of patterns to search for
     * @return a list of matches, each containing the pattern and its starting index in the text
     */
    public static List<Match> search(String text, String[] patterns) {
        if (text == null || patterns == null || patterns.length == 0) {
            return Collections.emptyList();
        }

        Node root = buildTrie(patterns);
        computeFailureLinks(root);
        return performSearch(root, text);
    }

    private static Node buildTrie(String[] patterns) {
        Node root = new Node();
        for (int i = 0; i < patterns.length; i++) {
            String pattern = patterns[i];
            if (pattern == null || pattern.isEmpty()) {
                continue;
            }
            Node current = root;
            for (int j = 0; j < pattern.length(); j++) {
                char ch = pattern.charAt(j);
                current = current.children.computeIfAbsent(ch, k -> new Node());
            }
            current.patternIndex = i;
            current.pattern = pattern;
        }
        return root;
    }

    private static void computeFailureLinks(Node root) {
        Queue<Node> queue = new LinkedList<>();

        // First level: children of the root
        for (Node child : root.children.values()) {
            child.failureLink = root;
            queue.add(child);
        }

        // BFS to compute failure links for the rest of the nodes
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Map.Entry<Character, Node> entry : current.children.entrySet()) {
                char ch = entry.getKey();
                Node child = entry.getValue();

                // Find the failure link for child
                Node fail = current.failureLink;
                while (fail != null && !fail.children.containsKey(ch)) {
                    fail = fail.failureLink;
                }

                if (fail == null) {
                    child.failureLink = root;
                } else {
                    child.failureLink = fail.children.get(ch);
                }

                // Compute output link
                if (child.failureLink.patternIndex != -1) {
                    child.outputLink = child.failureLink;
                } else {
                    child.outputLink = child.failureLink.outputLink;
                }

                queue.add(child);
            }
        }
    }

    private static List<Match> performSearch(Node root, String text) {
        List<Match> matches = new ArrayList<>();
        Node current = root;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Follow failure links until we find a valid transition or reach the root
            while (current != root && !current.children.containsKey(ch)) {
                current = current.failureLink;
            }

            // Move to the child node if valid transition exists, otherwise stay at root
            current = current.children.getOrDefault(ch, root);

            // Traverse output links to collect all matches ending at the current character index i
            Node temp = current;
            while (temp != null) {
                if (temp.patternIndex != -1) {
                    // Match starts at index: i - patternLength + 1
                    int startIndex = i - temp.pattern.length() + 1;
                    matches.add(new Match(temp.pattern, startIndex));
                }
                temp = temp.outputLink;
            }
        }

        return matches;
    }
}
