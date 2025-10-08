package com.thealgorithms.datastructures.trees;

import java.util.stream.IntStream;

/**
 * Implements a PATRICIA Trie (Practical Algorithm to Retrieve Information Coded
 * in Alphanumeric).
 *
 * <p>This implementation uses 32-bit integers as keys, which is common in
 * applications like IP address routing. It relies on efficient bitwise
 * operations to navigate the tree. PATRICIA Tries are a type of compressed
 * binary trie (radix tree with radix 2), where nodes are only created at points
 * of divergence between keys.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Radix_tree">Wikipedia: Radix Tree</a>
 *
 * <p><b>Key Characteristics:</b>
 * <ul>
 * <li><b>Compressed:</b> Nodes with only one child are omitted, saving space.</li>
 * <li><b>Binary (Radix-2):</b> Decisions at each node are based on a single bit from the key.</li>
 * <li><b>Back-Pointers:</b> Traversal ends when a "back-pointer" is found—a link to an ancestor
 * or to the node itself—indicating the location of the key for final comparison.</li>
 * </ul>
 */
public final class PatriciaTrie {

    /**
     * Represents a node in the Patricia Trie. Each node specifies which bit to
     * check and stores a key for comparison.
     */
    private static class Node {
        /**
         * The bit index to check for branching at this node (1-indexed from MSB, 1 to 32).
         * This index must be greater than that of the parent node. A value of 0 is
         * reserved for the root/head node.
         */
        int bitNumber;
        /**
         * The integer key associated with this node. This key is used for the final
         * comparison after traversal to confirm an exact match.
         */
        int key;
        /** Pointer to the next node if the bit being examined is 0. */
        Node leftChild;
        /** Pointer to the next node if the bit being examined is 1. */
        Node rightChild;

        /**
         * Constructs a new Node.
         *
         * @param bitNumber The bit index for comparison at this node.
         * @param key The integer key associated with this node.
         */
        Node(int bitNumber, int key) {
            this.bitNumber = bitNumber;
            this.key = key;
        }
    }

    private Node root;
    private static final int MAX_BITS = Integer.SIZE; // 32 bits for a standard Java int

    /**
     * Initializes an empty Patricia Trie.
     */
    public PatriciaTrie() {
        this.root = null;
    }

    /**
     * Checks if the trie contains any keys.
     * @return {@code true} if the trie is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes all keys from the trie.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Determines the value of the i-th bit of a given key.
     *
     * @param key The integer key.
     * @param i The 1-based index of the bit to check (1=MSB, 32=LSB).
     * @return {@code true} if the bit is 1, {@code false} if it is 0.
     */
    private boolean getBit(int key, int i) {
        // A 1-based index `i` corresponds to a shift of (MAX_BITS - i).
        // Example for 32 bits: i=1 (MSB) -> shift 31; i=32 (LSB) -> shift 0.
        return ((key >>> (MAX_BITS - i)) & 1) == 1;
    }

    /**
     * Searches for an exact key in the trie.
     *
     * @param key The integer key to search for.
     * @return {@code true} if the key is found, {@code false} otherwise.
     */
    public boolean search(int key) {
        if (root == null) {
            return false;
        }
        // Find the node containing the best possible match for the key.
        Node bestMatchNode = findBestMatchNode(root, key);
        // Confirm if the best match is an exact match.
        return bestMatchNode.key == key;
    }

    /**
     * Traverses the trie to find the node containing the key most similar to the search key.
     *
     * @param startNode The node to begin the search from (usually the root).
     * @param key The key being searched for.
     * @return The node containing the best matching key.
     */
    private Node findBestMatchNode(Node startNode, int key) {
        Node currentNode = startNode;
        Node nextNode = startNode.leftChild;

        // Traverse down the trie as long as we are following forward pointers.
        // A forward pointer is indicated by a child's bitNumber being greater
        // than its parent's bitNumber.
        while (nextNode.bitNumber > currentNode.bitNumber) {
            currentNode = nextNode;
            nextNode = getBit(key, nextNode.bitNumber) ? nextNode.rightChild : nextNode.leftChild;
        }
        // The loop terminates upon finding a back-pointer (nextNode.bitNumber <= currentNode.bitNumber),
        // which points to the node containing the best match.
        return nextNode;
    }

    /**
     * Inserts an integer key into the Patricia Trie. Does nothing if the key
     * already exists.
     *
     * @param key The integer key to insert.
     */
    public void insert(int key) {
        // 1. Handle Empty Trie (the very first insertion)
        if (root == null) {
            root = new Node(0, key); // bitNumber 0 is a sentinel for the head
            root.leftChild = root; // The first node points back to itself
            return;
        }

        // 2. Find the best matching key already in the trie
        Node bestMatchNode = findBestMatchNode(root, key);

        // 3. Check for Duplicates
        if (key == bestMatchNode.key) {
            return; // Key already exists, do nothing.
        }

        // 4. Find the first bit position where the new key and its best match differ
        int differingBit = 1;
        // BUG FIX: The loop must check all bits (i <= MAX_BITS). The original
        // code (i < MAX_BITS) failed to check the last bit.
        while (differingBit <= MAX_BITS && getBit(key, differingBit) == getBit(bestMatchNode.key, differingBit)) {
            differingBit++;
        }

        // This should not happen if the duplicate check is correct, but serves as a safeguard.
        if (differingBit > MAX_BITS) {
            throw new IllegalStateException("Keys are different but no differing bit was found.");
        }

        // 5. Find the correct insertion point by traversing again
        Node parent = root;
        Node child = root.leftChild;
        while (child.bitNumber > parent.bitNumber && child.bitNumber < differingBit) {
            parent = child;
            child = getBit(key, child.bitNumber) ? child.rightChild : child.leftChild;
        }

        // 6. Create the new node and link it into the trie
        Node newNode = new Node(differingBit, key);

        // Set the children of the new node. One child will be the existing subtree (`child`),
        // and the other will be a back-pointer to the new node itself.
        if (getBit(key, differingBit)) { // New key has a '1' at the differing bit
            newNode.leftChild = child;
            newNode.rightChild = newNode;
        } else { // New key has a '0' at the differing bit
            newNode.leftChild = newNode;
            newNode.rightChild = child;
        }

        // 7. Link the parent to the new node, replacing its old link to `child`
        if (getBit(key, parent.bitNumber)) {
            parent.rightChild = newNode;
        } else {
            parent.leftChild = newNode;
        }
    }

    // --- Main Driver and Example Usage ---

    public static void main(String[] args) {
        PatriciaTrie trie = new PatriciaTrie();
        System.out.println("--- Patricia Trie Demonstration ---");
        System.out.println("Trie is empty: " + trie.isEmpty());

        int[] keys = {10, 20, 15, 7, 5, 25};

        System.out.println("\n--- Inserting Keys ---");
        for (int key : keys) {
            System.out.printf("Inserting: %3d (%s)%n", key, Integer.toBinaryString(key));
            trie.insert(key);
        }
        System.out.println("\nTrie is empty: " + trie.isEmpty());

        System.out.println("\n--- Verifying Existing Keys ---");
        IntStream.of(keys).forEach(key -> System.out.printf("Search %3d: %s%n", key, trie.search(key) ? "Found" : "Not Found"));

        System.out.println("\n--- Searching for Non-Existing Keys ---");
        System.out.printf("Search %3d: %s%n", 100, trie.search(100) ? "Found" : "Not Found");
        System.out.printf("Search %3d: %s%n", 0, trie.search(0) ? "Found" : "Not Found");

        System.out.println("\n--- Attempting Duplicate Insertion ---");
        System.out.println("Inserting 20 again...");
        trie.insert(20); // Should do nothing
        System.out.printf("Search %3d: %s%n", 20, trie.search(20) ? "Found" : "Not Found");
    }
}
