package com.thealgorithms.datastructures.tries;

import java.util.stream.IntStream;

/**
 * Implements a PATRICIA Trie (Practical Algorithm to Retrieve Information Coded
 * in Alphanumeric) using fixed-width integers (keys).
 *
 * <p>This specific implementation uses the fixed-size 32-bit integer representation
 * as keys, common in many networking and IP lookup contexts, and relies on
 * bitwise operations for efficiency.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Radix_tree">Wikipedia: Radix Tree (Patricia Trie)</a>
 *
 * <p>Key characteristics:
 * <ul>
 * <li>**Radix-2 Trie:** Works on the binary representation of integer keys.</li>
 * <li>**Compacted:** Nodes only exist where branching occurs, compacting unary paths.</li>
 * <li>**External Nodes:** All nodes are internal; the key itself is stored in the
 * leaf/external node found by the search.</li>
 * </ul>
 */
public final class PatriciaTrie {

    /**
     * Represents a node in the Patricia Trie.
     * All nodes are internal nodes that store the key data at the point of creation,
     * and their `bitNumber` indicates the bit position to check when traversing.
     */
    private static class PatriciaTrieNode {
        /**
         * The bit index (1-indexed from MSB) to check for branching at this node.
         * The index must be greater than that of the parent node.
         */
        int bitNumber;
        /**
         * The integer key stored at this node. This is the **data** that was inserted
         * to create this node and acts as a placeholder or final result during search.
         */
        int key;
        /**
         * Pointer to the next node if the current bit is 0.
         */
        PatriciaTrieNode leftChild;
        /**
         * Pointer to the next node if the current bit is 1.
         */
        PatriciaTrieNode rightChild;

        PatriciaTrieNode(int bitNumber, int key) {
            this.bitNumber = bitNumber;
            this.key = key;
        }
    }

    private PatriciaTrieNode root;
    private static final int MAX_BITS = Integer.SIZE; // 32 bits for standard Java int

    /**
     * Initializes an empty Patricia Trie.
     */
    public PatriciaTrie() {
        this.root = null;
    }

    /**
     * Checks if the trie is empty.
     * @return true if the root is null, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Resets the trie, setting the root to null.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Determines the value of the i-th bit (1-indexed from MSB) of a given key.
     * Uses efficient bitwise operations.
     *
     * @param key The integer key.
     * @param i The 1-based index of the bit to check (1 is MSB, 32 is LSB).
     * @return true if the bit is 1, false if the bit is 0.
     */
    private boolean getBit(int key, int i) {
        // Calculate the shift amount: MAX_BITS - i
        // i=1 (MSB) -> shift 31
        // i=32 (LSB) -> shift 0
        int shift = MAX_BITS - i;
        // Use unsigned right shift (>>>) for predictable results, then mask with 1.
        return ((key >>> shift) & 1) == 1;
    }

    /**
     * Searches for a key in the trie.
     *
     * @param key The integer key to search for.
     * @return true if the key is found, false otherwise.
     */
    public boolean search(int key) {
        if (root == null) {
            return false;
        }

        // Search down to the external node
        PatriciaTrieNode foundNode = searchDown(root, key);

        // Check if the key stored in the found node matches the search key
        return foundNode.key == key;
    }

    /**
     * Traverses the trie to find the external node that is the predecessor
     * of the key 'k'. This node contains the most similar key currently in the trie.
     *
     * @param t The starting node for the search (usually the root).
     * @param k The key being searched for.
     * @return The external node where the key comparison should happen.
     */
    private PatriciaTrieNode searchDown(PatriciaTrieNode t, int k) {
        PatriciaTrieNode currentNode = t;
        PatriciaTrieNode nextNode = t.leftChild; // Start by following the default (0) child

        // The condition nextNode.bitNumber > currentNode.bitNumber is the core
        // of the Patricia Trie structure. It means we are moving down a tree edge (forward reference).
        while (nextNode.bitNumber > currentNode.bitNumber) {
            currentNode = nextNode;
            // Determine the next child based on the bit at nextNode.bitNumber
            nextNode = getBit(k, nextNode.bitNumber)
                ? nextNode.rightChild
                : nextNode.leftChild;
        }
        // When nextNode.bitNumber <= currentNode.bitNumber, we've found an external node
        // (a back pointer) which holds the best match key.
        return nextNode;
    }

    /**
     * Inserts an integer key into the Patricia Trie.
     *
     * @param key The integer key to insert.
     */
    public void insert(int key) {
        root = insert(root, key);
    }

    /**
     * Recursive helper method for insertion.
     *
     * @param t The current subtree root.
     * @param element The key to insert.
     * @return The updated root of the subtree.
     */
    private PatriciaTrieNode insert(PatriciaTrieNode t, int element) {

        // 1. Handle Empty Trie (Initial Insertion)
        if (t == null) {
            t = new PatriciaTrieNode(0, element); // Bit number 0 for the root/sentinel
            t.leftChild = t; // Root node links back to itself (left pointer)
            t.rightChild = null; // Right pointer unused or null
            return t;
        }

        // 2. Search for the best match (predecessor)
        PatriciaTrieNode lastNode = searchDown(t, element);

        // 3. Check for Duplicates
        if (element == lastNode.key) {
            System.out.println("Key " + element + " already present.");
            return t;
        }

        // 4. Find the first differentiating bit (i)
        int i = 1;
        while (getBit(element, i) == getBit(lastNode.key, i) && i < MAX_BITS) {
            i++;
        }
        // If i reached MAX_BITS + 1, the keys are identical (should have been caught above)
        if (i > MAX_BITS) {
             throw new IllegalStateException("Keys are identical but duplicate check failed.");
        }

        // 5. Find the insertion point (parent)
        // Find the node 'parent' that points to a bit number greater than 'i' or points back
        PatriciaTrieNode currentNode = t.leftChild;
        PatriciaTrieNode parent = t;

        while (currentNode.bitNumber > parent.bitNumber && currentNode.bitNumber < i) {
            parent = currentNode;
            currentNode = getBit(element, currentNode.bitNumber)
                ? currentNode.rightChild
                : currentNode.leftChild;
        }

        // 6. Create the new internal node
        PatriciaTrieNode newNode = new PatriciaTrieNode(i, element);

        // Determine the children of the new node (newNode)
        if (getBit(element, i)) {
            // New key has 1 at bit i: left child points to the old subtree (currentNode), right child points to self
            newNode.leftChild = currentNode;
            newNode.rightChild = newNode;
        } else {
            // New key has 0 at bit i: left child points to self, right child points to the old subtree (currentNode)
            newNode.leftChild = newNode;
            newNode.rightChild = currentNode;
        }

        // 7. Link the parent to the new node
        if (getBit(element, parent.bitNumber)) {
            // Parent's splitting bit matches the new key's bit: link via right child
            parent.rightChild = newNode;
        } else {
            // Parent's splitting bit doesn't match: link via left child
            parent.leftChild = newNode;
        }

        return t;
    }

    // --- Main Driver and Example Usage ---

    public static void main(String[] args) {
        PatriciaTrie trie = new PatriciaTrie();
        System.out.println("Patricia Trie Demonstration (Max Bits: " + MAX_BITS + ")");

        // Example integer keys (representing, perhaps, IP addresses or other binary identifiers)
        int[] keys = {10, 20, 15, 7, 5, 25};

        System.out.println("\n--- Insertion ---");
        for (int key : keys) {
            trie.insert(key);
            System.out.println("Inserted: " + key + " (" + Integer.toBinaryString(key) + ")");
        }

        System.out.println("\n--- Search ---");
        // Test existing keys
        IntStream.of(keys)
                .forEach(key -> System.out.printf("Search %d: %b\n", key, trie.search(key)));

        // Test non-existing keys
        System.out.printf("Search %d: %b\n", 100, trie.search(100)); // Non-existent
        System.out.printf("Search %d: %b\n", 0, trie.search(0));     // Non-existent

        // Test duplicate insertion
        System.out.println("\n--- Duplicate Insertion ---");
        trie.insert(20);
    }
}
