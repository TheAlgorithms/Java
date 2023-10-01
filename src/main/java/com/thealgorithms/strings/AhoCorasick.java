package com.thealgorithms.strings;

import java.util.*;

/**
 * Aho-Corasick String Matching Algorithm Implementation
 *
 * This code implements the Aho-Corasick algorithm, which is used for efficient
 * string matching in a given text. It can find multiple patterns simultaneously
 * and records their positions in the text.
 *
 * Author: Prabhat-Kumar-42
 * GitHub: https://github.com/Prabhat-Kumar-42
 */
public class AhoCorasick {
   
    // Trie Node Class
    class Node {
        HashMap<Character, Node> child = new HashMap<>();
        Node suffix_link;
        Node output_link;
        int pattern_ind;

        Node() {
            this.suffix_link = null;
            this.output_link = null;
            this.pattern_ind = -1;
        }
    }

    Node root = null; // The root node of the Aho-Corasick trie
    private ArrayList<ArrayList<Integer>> res; // Stores the positions where patterns are found in the text
    private String[] patterns; // patterns according to which Trie is constructed

    // Clears the Aho-Corasick data structures
    public void clear() {
        root = null;
        if (res != null) {
            res.clear();
        }
    }

    // Builds the Aho-Corasick trie from a set of input patterns
    public void buildTrie(String[] patterns) {
        root = new Node(); // Initialize the root of the trie
        res = new ArrayList<>(patterns.length); // Initialize the result data structure
        this.patterns = patterns;
        // Loop through each input pattern
        for (int i = 0; i < patterns.length; i++) {
            res.add(new ArrayList<Integer>()); // Initialize a list to store positions of the current pattern

            Node curr = root; // Start at the root of the trie for each pattern

            // Loop through each character in the current pattern
            for (int j = 0; j < patterns[i].length(); j++) {
                char c = patterns[i].charAt(j); // Get the current character

                // Check if the current node has a child for the current character
                if (curr.child.containsKey(c)) {
                    curr = curr.child.get(c); // Update the current node to the child node
                } else {
                    // If no child node exists, create a new one and add it to the current node's children
                    Node nn = new Node();
                    curr.child.put(c, nn);
                    curr = nn; // Update the current node to the new child node
                }
            }
            curr.pattern_ind = i; // Store the index of the pattern in the current leaf node
        }
    }

    // Builds the suffix links and output links in the Aho-Corasick trie
    public void buildSuffixAndOutputLinks() {
        root.suffix_link = root; // Initialize the suffix link of the root to itself
        Queue<Node> q = new LinkedList<>(); // Initialize a queue for BFS traversal

        // Initialize suffix links for child nodes of the root
        for (char rc : root.child.keySet()) {
            q.add(root.child.get(rc)); // Add child node to the queue
            root.child.get(rc).suffix_link = root; // Set suffix link to the root
        }

        while (!q.isEmpty()) {
            Node currentState = q.poll(); // Get the current node for processing

            // Iterate through child nodes of the current node
            for (char cc : currentState.child.keySet()) {
                Node currentChild = currentState.child.get(cc); // Get the child node
                Node parentSuffix = currentState.suffix_link; // Get the parent's suffix link

                // Calculate the suffix link for the child based on parent's suffix link
                while (!parentSuffix.child.containsKey(cc) && parentSuffix != root) {
                    parentSuffix = parentSuffix.suffix_link;
                }

                // Set the calculated suffix link or default to root
                if (parentSuffix.child.containsKey(cc)) {
                    currentChild.suffix_link = parentSuffix.child.get(cc);
                } else {
                    currentChild.suffix_link = root;
                }

                q.add(currentChild); // Add the child node to the queue for further processing
            }

            // Establish output links for nodes to efficiently identify patterns within patterns
            if (currentState.suffix_link.pattern_ind >= 0) {
                currentState.output_link = currentState.suffix_link;
            } else {
                currentState.output_link = currentState.suffix_link.output_link;
            }
        }
    }

    // Searches for patterns in the input text and records their positions
    public void searchIn(String text) {
        Node parent = root; // Start searching from the root node
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i); // Get the current character in the text

            // Check if the current node has a child for the current character
            if (parent.child.containsKey(ch)) {
                parent = parent.child.get(ch); // Update the current node to the child node

                // If the current node represents a pattern, record its position in res
                if (parent.pattern_ind > -1) {
                    res.get(parent.pattern_ind).add(i);
                }

                Node output_link = parent.output_link;
                // Follow output links to find and record positions of other patterns
                while (output_link != null) {
                    res.get(output_link.pattern_ind).add(i);
                    output_link = output_link.output_link;
                }
            } else {
                // If no child node exists for the character, backtrack using suffix links
                while (parent != root && !parent.child.containsKey(ch)) {
                    parent = parent.suffix_link;
                }
                if (parent.child.containsKey(ch)) {
                    i--; // Decrement i to reprocess the same character
                }
            }
        }
        setUpStartPoints();
    }

    /*
     * returns the list of list containing the indexes of words in patterns/dictonary
     * list index represet word.
     * eg - list[0] cointains the list of start-index of word pattern[0]
     *      list[1] cointains the list of start-index of word pattern[1]
     *      ......
     *      ......]
     *      list[n] cointains the list of start-index of word pattern[n]
     */
    public ArrayList<ArrayList<Integer>> getWordsIndexList() {
        return res;
    }
    // Returns the count of occurrences of each pattern in the text
    public ArrayList<Integer> getRepeatCountOfWords() {
        ArrayList<Integer> countOfWords = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            countOfWords.add(res.get(i).size());
        }
        return countOfWords;
    }
    // by default res contains end-points. This function convert those
    // endpoints to start points
    public void setUpStartPoints() {
        for (int i = 0; i < patterns.length; i++) {
            if (!res.get(i).isEmpty()) {
                for (int j = 0; j < res.get(i).size(); j++) {
                    int endpoint = res.get(i).get(j);
                    res.get(i).set(j, endpoint - patterns[i].length() + 1);
                }
            }
        }
    }
}
