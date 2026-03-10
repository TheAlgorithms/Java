package com.thealgorithms.greedyalgorithms;

import java.util.PriorityQueue;

/**
 * Huffman Coding Algorithm
 *
 * Greedy algorithm used for optimal prefix coding and data compression.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 *
 * https://en.wikipedia.org/wiki/Huffman_coding
 */
public class HuffmanCoding {

    static class Node implements Comparable<Node> {
        char character;
        int frequency;
        Node left;
        Node right;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    public static Node buildHuffmanTree(char[] chars, int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < chars.length; i++) {
            pq.add(new Node(chars[i], freq[i]));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node parent = new Node('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            pq.add(parent);
        }

        return pq.poll();
    }
}
