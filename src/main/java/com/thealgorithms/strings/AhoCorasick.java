/*
 * Aho-Corasick String Matching Algorithm Implementation
 *
 * This code implements the Aho-Corasick algorithm, which is used for efficient
 * string matching in a given text. It can find multiple patterns simultaneously
 * and records their positions in the text.
 *
 * Author: Prabhat-Kumar-42
 * GitHub: https://github.com/Prabhat-Kumar-42
 */

package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class AhoCorasick {

    // Trie Node Class
    public class Node {
        // Represents a character in the trie
        private HashMap<Character, Node> child = new HashMap<>(); // Child nodes of the current node
        private Node suffixLink; // Suffix link to another node in the trie
        private Node outputLink; // Output link to another node in the trie
        private int patternInd; // Index of the pattern that ends at this node

        Node() {
            this.suffixLink = null;
            this.outputLink = null;
            this.patternInd = -1;
        }

        public HashMap<Character, Node> getChild() {
            return child;
        }

        public Node getSuffixLink() {
            return suffixLink;
        }

        public void setSuffixLink(final Node suffixLink) {
            this.suffixLink = suffixLink;
        }

        public Node getOutputLink() {
            return outputLink;
        }

        public void setOutputLink(final Node outputLink) {
            this.outputLink = outputLink;
        }

        public int getPatternInd() {
            return patternInd;
        }

        public void setPatternInd(final int patternInd) {
            this.patternInd = patternInd;
        }
    }

    // Trie Class
    public class Trie {

        private Node root = null; // Root node of the trie
        private final String[] patterns; // patterns according to which Trie is constructed

        public Trie(final String[] patterns) {
            root = new Node(); // Initialize the root of the trie
            this.patterns = patterns;
            buildTrie();
            buildSuffixAndOutputLinks();
        }

        // builds AcoCorasick Trie
        private void buildTrie() {

            // Loop through each input pattern and building Trie
            for (int i = 0; i < patterns.length; i++) {
                Node curr = root; // Start at the root of the trie for each pattern

                // Loop through each character in the current pattern
                for (int j = 0; j < patterns[i].length(); j++) {
                    char c = patterns[i].charAt(j); // Get the current character

                    // Check if the current node has a child for the current character
                    if (curr.getChild().containsKey(c)) {
                        curr = curr.getChild().get(c); // Update the current node to the child node
                    } else {
                        // If no child node exists, create a new one and add it to the current node's children
                        Node nn = new Node();
                        curr.getChild().put(c, nn);
                        curr = nn; // Update the current node to the new child node
                    }
                }
                curr.setPatternInd(i); // Store the index of the pattern in the current leaf node
            }
        }

        private void initializeSuffixLinksForChildNodesOfTheRoot(Queue<Node> q) {
            for (char rc : root.getChild().keySet()) {
                Node childNode = root.getChild().get(rc);
                q.add(childNode); // Add child node to the queue
                childNode.setSuffixLink(root); // Set suffix link to the root
            }
        }

        private void buildSuffixAndOutputLinks() {
            root.setSuffixLink(root); // Initialize the suffix link of the root to itself
            Queue<Node> q = new LinkedList<>(); // Initialize a queue for BFS traversal

            // Initialize suffix links for child nodes of the root
            initializeSuffixLinksForChildNodesOfTheRoot(q);

            while (!q.isEmpty()) {
                Node currentState = q.poll(); // Get the current node for processing

                // Iterate through child nodes of the current node
                for (char cc : currentState.getChild().keySet()) {
                    Node currentChild = currentState.getChild().get(cc); // Get the child node
                    Node parentSuffix = currentState.getSuffixLink(); // Get the parent's suffix link

                    // Calculate the suffix link for the child based on the parent's suffix link
                    while (!parentSuffix.getChild().containsKey(cc) && parentSuffix != root) {
                        parentSuffix = parentSuffix.getSuffixLink();
                    }

                    // Set the calculated suffix link or default to root
                    if (parentSuffix.getChild().containsKey(cc)) {
                        currentChild.setSuffixLink(parentSuffix.getChild().get(cc));
                    } else {
                        currentChild.setSuffixLink(root);
                    }

                    q.add(currentChild); // Add the child node to the queue for further processing
                }

                // Establish output links for nodes to efficiently identify patterns within patterns
                if (currentState.getSuffixLink().getPatternInd() >= 0) {
                    currentState.setOutputLink(currentState.getSuffixLink());
                } else {
                    currentState.setOutputLink(currentState.getSuffixLink().getOutputLink());
                }
            }
        }

        // Searches for patterns in the input text and records their positions
        public ArrayList<ArrayList<Integer>> searchIn(String text) {
            /*
             * positionByStringIndexValue is the list of list containing the indexes of words in patterns/dictionary
             * list index represents word.
             * eg - list[0] contains the list of start-index of word pattern[0]
             *      list[1] contains the list of start-index of word pattern[1]
             *      ......
             *      ......
             *      list[n] contains the list of start-index of word pattern[n]
             */
            ArrayList<ArrayList<Integer>> positionByStringIndexValue = new ArrayList<>(patterns.length); // Stores positions where patterns are found in the text
            Node parent = root; // Start searching from the root node

            for (int i = 0; i < patterns.length; i++) {
                positionByStringIndexValue.add(new ArrayList<Integer>()); // Initialize a list to store positions of the current pattern
            }

            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i); // Get the current character in the text

                // Check if the current node has a child for the current character
                if (parent.getChild().containsKey(ch)) {
                    parent = parent.getChild().get(ch); // Update the current node to the child node

                    // If the current node represents a pattern, record its position in positionByStringIndexValue
                    if (parent.getPatternInd() > -1) {
                        positionByStringIndexValue.get(parent.getPatternInd()).add(i);
                    }

                    Node outputLink = parent.getOutputLink();
                    // Follow output links to find and record positions of other patterns
                    while (outputLink != null) {
                        positionByStringIndexValue.get(outputLink.getPatternInd()).add(i);
                        outputLink = outputLink.getOutputLink();
                    }
                } else {
                    // If no child node exists for the character, backtrack using suffix links
                    while (parent != root && !parent.getChild().containsKey(ch)) {
                        parent = parent.getSuffixLink();
                    }
                    if (parent.getChild().containsKey(ch)) {
                        i--; // Decrement i to reprocess the same character
                    }
                }
            }
            setUpStartPoints(positionByStringIndexValue);
            return positionByStringIndexValue;
        }
        // by default positionByStringIndexValue contains end-points. This function converts those
        // endpoints to start points
        private void setUpStartPoints(ArrayList<ArrayList<Integer>> positionByStringIndexValue) {
            for (int i = 0; i < patterns.length; i++) {
                for (int j = 0; j < positionByStringIndexValue.get(i).size(); j++) {
                    int endpoint = positionByStringIndexValue.get(i).get(j);
                    positionByStringIndexValue.get(i).set(j, endpoint - patterns[i].length() + 1);
                }
            }
        }
    }

    // method to search for patterns in text
    public static Map<String, ArrayList<Integer>> search(String text, String[] patterns) {
        Trie trie = new AhoCorasick().new Trie(patterns);
        ArrayList<ArrayList<Integer>> positionByStringIndexValue = trie.searchIn(text);
        return convert(positionByStringIndexValue, patterns);
    }

    // method for converting results to a map
    private static Map<String, ArrayList<Integer>> convert(ArrayList<ArrayList<Integer>> positionByStringIndexValue, String[] patterns) {
        Map<String, ArrayList<Integer>> positionByString = new HashMap<>();
        for (int i = 0; i < patterns.length; i++) {
            String pattern = patterns[i];
            ArrayList<Integer> positions = positionByStringIndexValue.get(i);
            positionByString.put(pattern, new ArrayList<>(positions));
        }
        return positionByString;
    }
}
