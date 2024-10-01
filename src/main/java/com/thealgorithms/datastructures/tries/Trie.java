package com.thealgorithms.datastructures.tries;

/**
 * Trie class which holds Strings of characters.
 *
 * <a href="https://en.wikipedia.org/wiki/Trie">Wikipedia</a>
 *
 * @author <a href="https://github.com/sailok">Sailok Chinta</a>
 */
public class Trie {
    private static final char ROOT_CHAR = '*';

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

            if (!head.getChildren().containsKey(c)) {
                head.getChildren().put(c, new TrieNode(c));
            }

            head = head.getChildren().get(c);
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

            if (!head.getChildren().containsKey(c)) {
                return false;
            }

            head = head.getChildren().get(c);
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

            if (!head.getChildren().containsKey(c)) {
                return false;
            }

            head = head.getChildren().get(c);
        }

        return true;
    }
}
