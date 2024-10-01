package com.thealgorithms.tries;

/**
 * TrieNode class which holds the lowercase letter and references to its child nodes
 */
class TrieNode {
    private static final int CHILDREN_NODE_COUNT = 26;

    private TrieNode[] children;
    private char letter;
    private boolean end;

    public TrieNode(char letter) {
        this.letter = letter;
        this.children = new TrieNode[CHILDREN_NODE_COUNT];
        this.end = false;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode[] children) {
        this.children = children;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}

/**
 * Trie class which holds Strings of LowerCase Sensitive characters.
 */
public class Trie {
    private static final char ROOT_CHAR = '*';
    private static final char BASE_CHAR = 'a';

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode(ROOT_CHAR);
    }

    /**
     * Inserts the word into Trie
     *
     * @param word
     */
    public void insert(String word) {
        TrieNode head = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (head.getChildren()[c - BASE_CHAR] == null) {
                head.getChildren()[c - BASE_CHAR] = new TrieNode(c);
            }

            head = head.getChildren()[c - BASE_CHAR];
        }

        head.setEnd(true);
    }

    /**
     * Searches in the Trie if a word exists or not.
     *
     * @param word
     * @return true / false
     */
    public boolean search(String word) {
        TrieNode head = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (head.getChildren()[c - BASE_CHAR] == null) {
                return false;
            }

            head = head.getChildren()[c - BASE_CHAR];
        }

        return head.isEnd();
    }

    /**
     * Searches in the Trie if a prefix exists or not.
     *
     * @param prefix
     * @return true / false
     */
    public boolean startsWith(String prefix) {
        TrieNode head = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if (head.getChildren()[c - BASE_CHAR] == null) {
                return false;
            }

            head = head.getChildren()[c - BASE_CHAR];
        }

        return true;
    }
}
