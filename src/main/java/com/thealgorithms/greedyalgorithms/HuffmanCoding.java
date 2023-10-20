package com.thealgorithms.greedyalgorithms;

import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char data;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanCoding {
    public static void buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        // Step 1: Create leaf nodes for each character and their frequencies.
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Step 2: Build the Huffman tree by repeatedly combining nodes.
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode combined = new HuffmanNode('-', left.frequency + right.frequency);
            combined.left = left;
            combined.right = right;
            priorityQueue.add(combined);
        }

        // Step 3: Print the Huffman codes by traversing the tree.
        HuffmanNode root = priorityQueue.poll();
        printHuffmanCodes(root, "");
    }

    public static void printHuffmanCodes(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }
        if (node.data != '-') {
            // If the node represents a character, print its code.
            System.out.println(node.data + ": " + code);
        }
        // Recursively traverse the left and right subtrees, appending '0' and '1' to the code.
        printHuffmanCodes(node.left, code + "0");
        printHuffmanCodes(node.right, code + "1");
    }
}
