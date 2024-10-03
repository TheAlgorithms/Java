package com.thealgorithms.datastructures.trees;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Trie Data structure implementation without any libraries
 *
 * @author <a href="https://github.com/dheeraj92">Dheeraj Kumar Barnwal</a>
 */
public class TrieImp {
    public class TrieNode {
        char value;
        HashMap<Character, TrieNode> child;
        boolean end;

        public TrieNode(char value) {
            this.value = value;
            child = new HashMap<>();
            end = false;
        }
    }

    private static final char ROOT_NODE_VALUE = '*';

    private final TrieNode root;

    public TrieImp() {
        root = new TrieNode(ROOT_NODE_VALUE);
    }

    public void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode node = currentNode.child.getOrDefault(word.charAt(i), null);
            if (node == null) {
                node = new TrieNode(word.charAt(i));
                currentNode.child.put(word.charAt(i), node);
            }
            currentNode = node;
        }
        currentNode.end = true;
    }

    public boolean search(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = currentNode.child.getOrDefault(ch, null);
            if (node == null) {
                return false;
            }
            currentNode = node;
        }
        return currentNode.end;
    }

    public boolean delete(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = currentNode.child.getOrDefault(ch, null);
            if (node == null) {
                return false;
            }
            currentNode = node;
        }
        if (currentNode.end) {
            currentNode.end = false;
            return true;
        }
        return false;
    }

    public static void sop(String print) {
        System.out.println(print);
    }

    public static void main(String[] args) {
        TrieImp obj = new TrieImp();
        String word;
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
        sop("string should contain only a-z character for all operation");
        while (true) {
            sop("1. Insert\n2. Search\n3. Delete\n4. Quit");
            try {
                int t = scan.nextInt();
                switch (t) {
                case 1:
                    word = scan.next();
                    obj.insert(word);
                    break;
                case 2:
                    word = scan.next();
                    boolean resS = obj.search(word);

                    if (resS) {
                        sop("word found");
                    } else {
                        sop("word not found");
                    }
                    break;
                case 3:
                    word = scan.next();
                    boolean resD = false;
                    resD = obj.delete(word);

                    if (resD) {
                        sop("word got deleted successfully");
                    } else {
                        sop("word not found");
                    }
                    break;
                case 4:
                    sop("Quit successfully");
                    System.exit(1);
                    break;
                default:
                    sop("Input int from 1-4");
                    break;
                }
            } catch (Exception e) {
                String badInput = scan.next();
                sop("This is bad input: " + badInput);
            }
        }
    }
}
