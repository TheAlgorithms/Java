package com.thealgorithms.maths;

import java.util.*;

public final class HuffmanCoding {
    private HuffmanCoding() {
    }
    public static class HuffmanNode {
        char data;
        int frequency;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
            left = null;
            right = null;
        }
    }
    public static class FrequencyComparator implements Comparator<HuffmanNode> {
        @Override
        public int compare(HuffmanNode node1, HuffmanNode node2) {
            return node1.frequency - node2.frequency;
        }
    }

    // Build the Huffman tree from a frequency map.
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new FrequencyComparator());

        // Create a leaf node for each character and its frequency.
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Merge nodes until there's only one node left, the root of the Huffman tree.
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode mergedNode = new HuffmanNode('\0', left.frequency + right.frequency);
            mergedNode.left = left;
            mergedNode.right = right;
            priorityQueue.offer(mergedNode);
        }

        return priorityQueue.poll();
    }
    // Print Huffman codes (binary representations) for each character in the tree.
    public static void printHuffmanCodes(HuffmanNode root, String code, StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }

        if (root.data != '\0') {
            // Print the character and its Huffman code.
            System.out.println(root.data + ": " + code);
            stringBuilder.append(root.data);
        }
        // Recursively traverse the left and right branches, appending '0' for left and '1' for right.
        printHuffmanCodes(root.left, code + "0", stringBuilder);
        printHuffmanCodes(root.right, code + "1", stringBuilder);
    }

    public static void main(String[] args) {
        String inputText = "this is an example for huffman encoding";
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Calculate the frequency of each letter in the input text.
        for (char c : inputText.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }
        // Build the Huffman tree and print the Huffman codes.
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        StringBuilder encodedText = new StringBuilder();
        printHuffmanCodes(root, "", encodedText);

        // Print the encoded text.
        System.out.println("Encoded Text: " + encodedText.toString());
    }
}
