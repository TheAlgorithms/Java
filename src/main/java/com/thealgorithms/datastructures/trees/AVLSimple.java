package com.thealgorithms.datastructures.trees;

/**
 * The AVLSimple class implements an AVL Tree, a self-balancing binary search tree.
 * It maintains the balance of the tree using rotations during insertion to ensure that
 * the heights of the two child subtrees of any node differ by at most one.
 *
 * An AVL Tree performs the following operations:
 * - Insertion: Adds a new node while maintaining the AVL property.
 * - Display: Outputs the structure of the tree.
 *
 * The AVL tree balances itself in four main cases during insertion:
 * 1. Left-Left (LL) Case: Right rotation is performed.
 * 2. Right-Right (RR) Case: Left rotation is performed.
 * 3. Right-Left (RL) Case: Right rotation followed by left rotation.
 * 4. Left-Right (LR) Case: Left rotation followed by right rotation.
 *
 * Example usage:
 * <pre>
 *     AVLSimple tree = new AVLSimple();
 *     tree.insert(20);
 *     tree.insert(25);
 *     tree.insert(30);
 *     tree.insert(10);
 *     tree.insert(5);
 *     tree.insert(15);
 *     tree.insert(27);
 *     tree.insert(19);
 *     tree.insert(16);
 *     tree.display();
 * </pre>
 */
public class AVLSimple {

    static class Node {
        int data;
        int height;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    /**
     * Inserts a new value into the AVL Tree.
     *
     * @param data the value to be inserted
     */
    public void insert(int data) {
        this.root = insert(this.root, data);
    }

    private Node insert(Node node, int item) {
        if (node == null) {
            return new Node(item);
        }
        if (node.data > item) {
            node.left = insert(node.left, item);
        } else if (node.data < item) {
            node.right = insert(node.right, item);
        } else {
            return node;
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int bf = bf(node);

        // LL case
        if (bf > 1 && item < node.left.data) {
            return rightRotate(node);
        }
        // RR case
        if (bf < -1 && item > node.right.data) {
            return leftRotate(node);
        }
        // RL case
        if (bf < -1 && item < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        // LR case
        if (bf > 1 && item > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        return node;
    }

    /**
     * Displays the structure of the AVL Tree in a readable format.
     */
    public void display() {
        this.display(this.root);
        System.out.println("Tree Height: " + this.root.height);
    }

    private void display(Node node) {
        String str = "";
        if (node.left != null) {
            str += node.left.data + "=>";
        } else {
            str += "END=>";
        }
        str += node.data + "";
        if (node.right != null) {
            str += "<=" + node.right.data;
        } else {
            str += "<=END";
        }
        System.out.println(str);
        if (node.left != null) {
            display(node.left);
        }
        if (node.right != null) {
            display(node.right);
        }
    }

    /**
     * Returns the height of the AVL Tree.
     *
     * @param node the root node of the AVL Tree
     * @return the height of the AVL Tree
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * Returns the balance factor of the AVL Tree.
     *
     * @param node the root node of the AVL Tree
     * @return the balance factor of the AVL Tree
     */
    private int bf(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /**
     * Performs a right rotation on the AVL Tree.
     *
     * @param c the root node of the AVL Tree
     * @return the new root node of the AVL Tree
     */
    private Node rightRotate(Node c) {
        Node b = c.left;
        Node t3 = b.right;

        b.right = c;
        c.left = t3;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
        b.height = Math.max(height(b.left), height(b.right)) + 1;
        return b;
    }

    /**
     * Performs a left rotation on the AVL Tree.
     *
     * @param c the root node of the AVL Tree
     * @return the new root node of the AVL Tree
     */
    private Node leftRotate(Node c) {
        Node b = c.right;
        Node t3 = b.left;

        b.left = c;
        c.right = t3;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
        b.height = Math.max(height(b.left), height(b.right)) + 1;
        return b;
    }

    public int getHeight() {
        return this.root.height;
    }

    public Node getRoot() {
        return this.root;
    }
}
