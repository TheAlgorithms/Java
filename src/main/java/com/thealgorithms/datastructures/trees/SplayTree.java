package com.thealgorithms.datastructures.trees;

import java.io.Serial;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a Splay Tree data structure.
 * <p>
 * A splay tree is a self-adjusting binary search tree with the additional property
 * that recently accessed elements are quick to access again. It performs basic
 * operations such as insertion, deletion, and searching in O(log n) amortized time.
 */
public class SplayTree {

    // Preserved public API constants
    public static final TreeTraversal PRE_ORDER = TraversalStrategy.PRE_ORDER;
    public static final TreeTraversal IN_ORDER = TraversalStrategy.IN_ORDER;
    public static final TreeTraversal POST_ORDER = TraversalStrategy.POST_ORDER;

    private Node root;

    /**
     * Checks if the tree is empty.
     *
     * @return True if the tree is empty, otherwise false.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert a key into the SplayTree.
     *
     * @param key The key to insert.
     */
    public void insert(final int key) {
        root = insertRec(root, key);
        root = splay(root, key);
    }

    /**
     * Search for a key in the SplayTree.
     *
     * @param key The key to search for.
     * @return True if the key is found, otherwise false.
     */
    public boolean search(int key) {
        if (isEmpty()) {
            return false;
        }
        root = splay(root, key);
        return root.key == key;
    }

    /**
     * Deletes a key from the SplayTree.
     *
     * @param key The key to delete.
     * @throws EmptyTreeException If the tree is empty.
     */
    public void delete(final int key) {
        if (isEmpty()) {
            throw new EmptyTreeException("Cannot delete from an empty tree");
        }

        root = splay(root, key);

        if (root.key != key) {
            return;
        }

        if (root.left == null) {
            root = root.right;
        } else {
            Node rightSubtree = root.right;
            root = splay(root.left, findMax(root.left).key);
            root.right = rightSubtree;
        }
    }

    /**
     * Perform a traversal of the SplayTree.
     *
     * @param traversal The type of traversal method.
     * @return A list containing the keys in the specified traversal order.
     */
    public List<Integer> traverse(TreeTraversal traversal) {
        List<Integer> result = new LinkedList<>();
        // Safely delegate to the internal enum strategy, hiding Node exposure
        if (traversal instanceof TraversalStrategy strategy) {
            strategy.execute(root, result);
        }
        return result;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * Zig operation (Right Rotation).
     */
    private Node rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        return leftChild;
    }

    /**
     * Zag operation (Left Rotation).
     */
    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        return rightChild;
    }

    /**
     * Splay operation. Moves the accessed node to the root.
     */
    private Node splay(Node node, final int key) {
        if (node == null || node.key == key) {
            return node;
        }

        if (node.key > key) {
            if (node.left == null) {
                return node;
            }
            if (node.left.key > key) {
                node.left.left = splay(node.left.left, key);
                node = rotateRight(node);
            } else if (node.left.key < key) {
                node.left.right = splay(node.left.right, key);
                if (node.left.right != null) {
                    node.left = rotateLeft(node.left);
                }
            }
            return (node.left == null) ? node : rotateRight(node);
        } else {
            if (node.right == null) {
                return node;
            }
            if (node.right.key > key) {
                node.right.left = splay(node.right.left, key);
                if (node.right.left != null) {
                    node.right = rotateRight(node.right);
                }
            } else if (node.right.key < key) {
                node.right.right = splay(node.right.right, key);
                node = rotateLeft(node);
            }
            return (node.right == null) ? node : rotateLeft(node);
        }
    }

    private Node insertRec(Node node, final int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        } else {
            throw new DuplicateKeyException("Duplicate key: " + key);
        }

        return node;
    }

    // --- Inner Classes and Interfaces ---

    private static class Node {
        final int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    /**
     * Public interface acting as a type token for traversals.
     * No longer exposes internal Node architecture to the public API.
     */
    public interface TreeTraversal {
        // Marker interface to preserve public API footprint
    }

    /**
     * Private internal implementation of the traversal strategies.
     * This keeps the 'Node' references completely encapsulated within SplayTree.
     */
    private enum TraversalStrategy implements TreeTraversal {
        PRE_ORDER {
            @Override
            void execute(Node root, List<Integer> result) {
                if (root != null) {
                    result.add(root.key);
                    execute(root.left, result);
                    execute(root.right, result);
                }
            }
        },
        IN_ORDER {
            @Override
            void execute(Node root, List<Integer> result) {
                if (root != null) {
                    execute(root.left, result);
                    result.add(root.key);
                    execute(root.right, result);
                }
            }
        },
        POST_ORDER {
            @Override
            void execute(Node root, List<Integer> result) {
                if (root != null) {
                    execute(root.left, result);
                    execute(root.right, result);
                    result.add(root.key);
                }
            }
        };

        abstract void execute(Node root, List<Integer> result);
    }

    // --- Custom Exceptions ---

    public static class EmptyTreeException extends RuntimeException {
        @Serial
        private static final long serialVersionUID = 1L;

        public EmptyTreeException(String message) {
            super(message);
        }
    }

    public static class DuplicateKeyException extends RuntimeException {
        @Serial
        private static final long serialVersionUID = 1L;

        public DuplicateKeyException(String message) {
            super(message);
        }
    }
}