/******************************************************************
 *             Code Implementing Binomail Heap                    *
 *                                                                *
 * For deatiled info regarding Binomail-Heap refer to below link  *
 *      wiki -  https://en.wikipedia.org/wiki/Binomial_heap       *
 *                                                                *
 *                                                                *
 *               Author - Prabhat-Kumar-42                        *
 *        Github - https://github.com/Prabhat-Kumar-42            *
 *                                                                *
 *****************************************************************/

package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Class representing a node in a binomial heap
class BinomialNode {
    int key, degree;
    BinomialNode parent;
    BinomialNode child;
    BinomialNode sibling;

    public BinomialNode(int key) {
        this.key = key; // The key value stored in this node
        degree = 0; // The degree of this node (number of child nodes)
        parent = null; // Reference to the parent node
        child = null; // Reference to one of its child nodes
        sibling = null; // Reference to the next sibling node
    }
}

public class BinomialHeap {
    private BinomialNode head; // The head of the binomial heap

    public BinomialHeap() {
        head = null; // Initialize an empty binomial heap
    }

    // Merge two binomial heaps into one
    public void merge(BinomialHeap heap) {
        head = mergeTrees(head, heap.head);
        heap.head = null;
    }

    // Merge two binomial trees of the same degree
    // Merge two binomial trees of the same degree
    private BinomialNode mergeTrees(BinomialNode tree1, BinomialNode tree2) {
        if (tree1 == null) return tree2;
        if (tree2 == null) return tree1;

        BinomialNode mergedTree;
        if (tree1.degree < tree2.degree) {
            mergedTree = tree1;
            tree1 = tree1.sibling;
        } else {
            mergedTree = tree2;
            tree2 = tree2.sibling;
        }

        BinomialNode tail = mergedTree;

        while (tree1 != null && tree2 != null) {
            if (tree1.degree < tree2.degree) {
                tail.sibling = tree1;
                tree1 = tree1.sibling;
            } else {
                tail.sibling = tree2;
                tree2 = tree2.sibling;
            }
            tail = tail.sibling;
        }

        if (tree1 != null) {
            tail.sibling = tree1;
        } else {
            tail.sibling = tree2;
        }

        return mergedTree;
    }
    // Insert a key into the binomial heap
    public void insert(int key) {
        BinomialHeap newHeap = new BinomialHeap();
        newHeap.head = new BinomialNode(key);
        merge(newHeap);
    }

    // Find the minimum key in the binomial heap
    public int findMin() {
        if (head == null) return Integer.MIN_VALUE; // Return the minimum integer value for an empty heap

        BinomialNode minNode = head;
        BinomialNode current = head;

        // Traverse the heap to find the node with the minimum key
        while (current != null) {
            if (current.key < minNode.key) minNode = current;
            current = current.sibling;
        }

        return minNode.key;
    }

    // Extract the minimum key from the binomial heap
    public int extractMin() {
        if (head == null) return Integer.MIN_VALUE; // Return the minimum integer value for an empty heap

        BinomialNode minNode = head;
        BinomialNode prevMin = null;
        BinomialNode prev = null;
        BinomialNode current = head;

        // Traverse the heap to find the node with the minimum key
        while (current != null) {
            if (current.key < minNode.key) {
                minNode = current;
                prevMin = prev;
            }
            prev = current;
            current = current.sibling;
        }

        // Remove the node with the minimum key from the heap
        if (prevMin != null) {
            prevMin.sibling = minNode.sibling;
        } else {
            head = minNode.sibling;
        }

        // Create a new binomial heap from the children of the extracted node
        BinomialNode child = minNode.child;
        BinomialHeap childHeap = new BinomialHeap();
        while (child != null) {
            BinomialNode nextChild = child.sibling;
            child.sibling = null;
            childHeap.head = child;
            merge(childHeap); // Merge the child heap with the main heap
            child = nextChild;
        }

        return minNode.key;
    }

    // Decrease the key of a node and maintain heap property
    public void decreaseKey(int oldKey, int newKey) {
        if (head == null) return; // Nothing to do for an empty heap

        decreaseKeyHelper(head, oldKey, newKey);
    }

    // Helper function to recursively decrease the key
    private boolean decreaseKeyHelper(BinomialNode node, int oldKey, int newKey) {
        if (node == null) return false;

        // Find the node with the old key and update its key
        if (node.key == oldKey) {
            node.key = newKey;

            // Fix the heap property if necessary by swapping with the parent
            while (node.parent != null && node.key < node.parent.key) {
                int temp = node.key;
                node.key = node.parent.key;
                node.parent.key = temp;
                node = node.parent;
            }

            return true;
        }

        // Recursively search for the node with the old key in children and siblings
        if (decreaseKeyHelper(node.child, oldKey, newKey)) return true;
        return decreaseKeyHelper(node.sibling, oldKey, newKey);
    }

    // Delete a node with a given key from the binomial heap
    public void delete(int key) {
        if (head == null) return; // Nothing to do for an empty heap

        // Decrease the key of the node to negative infinity
        decreaseKey(key, Integer.MIN_VALUE);

        // Extract the minimum node, which is now the node with key -infinity
        extractMin();
    }

    // Display the binomial heap
    public void display() {
        if (head == null) return; // Nothing to display for an empty heap

        List<BinomialNode> nodes = new ArrayList<>();
        LinkedList<BinomialNode> queue = new LinkedList<>();
        queue.add(head);

        // Perform a level-order traversal to collect nodes for display
        while (!queue.isEmpty()) {
            BinomialNode node = queue.poll();
            nodes.add(node);
            if (node.child != null) queue.add(node.child);
            if (node.sibling != null) queue.add(node.sibling);
        }

        System.out.println("Binomial Heap:");
        for (BinomialNode n : nodes) {
            System.out.println(n.key);
        }
    }
}
