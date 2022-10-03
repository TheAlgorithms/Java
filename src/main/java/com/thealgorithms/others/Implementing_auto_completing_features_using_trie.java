package com.thealgorithms.others;

// Java Program to implement Auto-Complete
// Feature using Trie
class Trieac {

    // Alphabet size (# of symbols)
    public static final int ALPHABET_SIZE = 26;

    // Trie node
    static class TrieNode {

        TrieNode children[] = new TrieNode[ALPHABET_SIZE];

        // isWordEnd is true if the node represents
        // end of a word
        boolean isWordEnd;
    }

    // Returns new trie node (initialized to NULLs)
    static TrieNode getNode() {
        TrieNode pNode = new TrieNode();
        pNode.isWordEnd = false;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            pNode.children[i] = null;
        }

        return pNode;
    }

    // If not present, inserts key into trie. If the
    // key is prefix of trie node, just marks leaf node
    static void insert(TrieNode root, final String key) {
        TrieNode pCrawl = root;

        for (int level = 0; level < key.length(); level++) {
            int index = (key.charAt(level) - 'a');
            if (pCrawl.children[index] == null) {
                pCrawl.children[index] = getNode();
            }
            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isWordEnd = true;
    }

    // Returns true if key presents in trie, else false
    boolean search(TrieNode root, final String key) {
        int length = key.length();
        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++) {
            int index = (key.charAt(level) - 'a');

            if (pCrawl.children[index] == null) {
                pCrawl = pCrawl.children[index];
            }
        }

        return (pCrawl != null && pCrawl.isWordEnd);
    }

    // Returns 0 if current node has a child
    // If all children are NULL, return 1.
    static boolean isLastNode(TrieNode root) {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                return false;
            }
        }
        return true;
    }

    // Recursive function to print auto-suggestions
    // for given node.
    static void suggestionsRec(TrieNode root, String currPrefix) {
        // found a string in Trie with the given prefix
        if (root.isWordEnd) {
            System.out.println(currPrefix);
        }

        // All children struct node pointers are NULL
        if (isLastNode(root)) {
            return;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                // append current character to currPrefix string
                currPrefix += (char) (97 + i);

                // recur over the rest
                suggestionsRec(root.children[i], currPrefix);
            }
        }
    }

    // Fucntion  to print suggestions for
    // given query prefix.
    static int printAutoSuggestions(TrieNode root, final String query) {
        TrieNode pCrawl = root;

        // Check if prefix is present and find the
        // the node (of last level) with last character
        // of given string.
        int level;
        int n = query.length();

        for (level = 0; level < n; level++) {
            int index = (query.charAt(level) - 'a');

            // no string in the Trie has this prefix
            if (pCrawl.children[index] == null) {
                return 0;
            }

            pCrawl = pCrawl.children[index];
        }

        // If prefix is present as a word.
        boolean isWord = (pCrawl.isWordEnd == true);

        // If prefix is last node of tree (has no
        // children)
        boolean isLast = isLastNode(pCrawl);

        // If prefix is present as a word, but
        // there is no subtree below the last
        // matching node.
        if (isWord && isLast) {
            System.out.println(query);
            return -1;
        }

        // If there are nodes below the last
        // matching character.
        if (!isLast) {
            String prefix = query;
            suggestionsRec(pCrawl, prefix);
            return 1;
        }

        return 0;
    }

    // Driver code
    public static void main(String[] args) {
        TrieNode root = getNode();
        insert(root, "hello");
        insert(root, "dog");
        insert(root, "hell");
        insert(root, "cat");
        insert(root, "a");
        insert(root, "hel");
        insert(root, "help");
        insert(root, "helps");
        insert(root, "helping");
        int comp = printAutoSuggestions(root, "hel");

        if (comp == -1) {
            System.out.println(
                "No other strings found " + "with this prefix\n"
            );
        } else if (comp == 0) {
            System.out.println("No string found with" + " this prefix\n");
        }
    }
}
