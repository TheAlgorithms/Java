package main.java.com.thealgorithms.datastructures.trees;

/**
 * Ternary Search Tree (TST) implementation.
 * Stores strings and supports insertion, search, and prefix checking.
 *
 * Time Complexity:
 * - Insert: O(m), where m = word length
 * - Search: O(m)
 */
public class TernarySearchTree {
    private Node root;

    private static class Node {
        char character;
        boolean isEndOfWord;
        Node left, middle, right;

        Node(char character) {
            this.character = character;
        }
    }

    /** Insert a word into the TST */
    public void insert(String word) {
        root = insert(root, word, 0);
    }

    private Node insert(Node node, String word, int index) {
        char ch = word.charAt(index);
        if (node == null) {
            node = new Node(ch);
        }

        if (ch < node.character) {
            node.left = insert(node.left, word, index);
        } else if (ch > node.character) {
            node.right = insert(node.right, word, index);
        } else {
            if (index + 1 < word.length()) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEndOfWord = true;
            }
        }
        return node;
    }

    /** Search if a word exists in TST */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int index) {
        if (node == null) return false;

        char ch = word.charAt(index);
        if (ch < node.character) {
            return search(node.left, word, index);
        } else if (ch > node.character) {
            return search(node.right, word, index);
        } else {
            if (index + 1 == word.length()) {
                return node.isEndOfWord;
            }
            return search(node.middle, word, index + 1);
        }
    }

    // Example usage
    public static void main(String[] args) {
        TernarySearchTree tst = new TernarySearchTree();
        tst.insert("cat");
        tst.insert("cap");
        tst.insert("bat");

        System.out.println(tst.search("cat")); // true
        System.out.println(tst.search("cap")); // true
        System.out.println(tst.search("can")); // false
    }
}
