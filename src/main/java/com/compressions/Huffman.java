package com.compressions;

import java.util.*;
import java.util.stream.Collectors;

public final class Huffman {

    private final class Node {
        private final Character letter;
        private final int frequency;
        private final Node left;
        private final Node right;
        private String bitString;

        private Node(Character letter, int frequency, Node left, Node right) {
            this.letter = letter;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
    }

    private List<Node> parseInput(String input) {
        Map<Character, Integer> letters = new LinkedHashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            letters.merge(character, 1, Integer::sum);
        }

        return letters.entrySet()
                .stream()
                .map(element -> new Node(element.getKey(), element.getValue(), null, null))
                .sorted(Comparator.comparingInt(left -> left.frequency))
                .collect(Collectors.toList());
    }

    private Node buildTree(List<Node> letters) {
        while (letters.size() > 1) {
            Node left = letters.remove(0);
            Node right = letters.remove(0);

            letters.add(new Node(null, left.frequency + right.frequency, left, right));

            letters.sort(Comparator.comparingInt(node -> node.frequency));
        }

        return letters.get(0);
    }

    private void traverseTree(Node root, String bitString, ArrayList<Node> result) {
        if (root.letter != null) {
            root.bitString = bitString;

            result.add(root);

            return;
        }

        this.traverseTree(root.left, bitString + "0", result);
        this.traverseTree(root.right, bitString + "1", result);
    }

    private String createHuffman(ArrayList<Node> nodes, String path) {
        StringBuilder result = new StringBuilder();

        for (char character : path.toCharArray()) {
            result.append(nodes.stream().filter(node -> node.letter == character).findFirst().get().bitString).append(" ");
        }

        return result.toString().trim();
    }

    public String encode(String value) {
        List<Node> queue = this.parseInput(value);

        Node root = this.buildTree(queue);

        ArrayList<Node> nodes = new ArrayList<>();

        this.traverseTree(root, "", nodes);

        return this.createHuffman(nodes, value);
    }

}
