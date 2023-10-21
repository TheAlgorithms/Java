package com.thealgorithms.datastructures.trees;

/**
 * A class that converts an AVL tree to a Red-Black tree.
 *
 * @author YourNameHere (or any name you want to use)
 * wiki:https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 */
public class RedBlackTreeFromAVL {
    /**
     * Enumeration representing the colour of a node in a Red-Black tree.
     */
    public enum Colour {
        RED, BLACK
    }

    /**
     * Class representing a node in the tree.
     */
    private class Node {
        int key; // The value/key of the node
        int balanceFactorb; // Balance factor for black nodes
        Node left, right, parent; // Pointers to left, right children and parent
        Colour colour; // Colour of the node

        /**
         * Constructor to initialize the node with a key.
         *
         * @param key The value/key of the node.
         */
        Node(int key) {
            this.key = key;
            this.colour = Colour.BLACK;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public boolean isAVL(Node node) {
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE) && Math.abs(balanceFactor(node)) <= 1;
    }

    /**
     * Helper method to check if a tree is a binary search tree.
     *
     * @param node Current node.
     * @param min  Minimum value a node should have.
     * @param max  Maximum value a node should have.
     * @return true if the subtree rooted at node is a BST, false otherwise.
     */
    private boolean isBST(Node node, int min, int max) {
        if (node == null) return true;

        if (node.key <= min || node.key >= max) return false;

        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    /**
     * Computes the balance factor of a given node.
     *
     * @param node The node whose balance factor is to be computed.
     * @return The balance factor of the node.
     */
    private int balanceFactor(Node node) {
        if (node == null) return 0;

        return height(node.left) - height(node.right);
    }

    /**
     * Computes the height of a given node.
     *
     * @param node The node whose height is to be computed.
     * @return The height of the node.
     */
    private int height(Node node) {
        if (node == null) return 0;

        return 1 + Math.max(height(node.left), height(node.right));
    }

    private Node root; // Root of the tree

    /**
     * Computes and updates the black height of a given node.
     *
     * @param node The node whose black height needs to be computed.
     * @return The black height of the node.
     */
    public int updateBlackHeight(Node node) {
        if (node == null) return 1;

        int leftHeight = updateBlackHeight(node.left);
        int rightHeight = updateBlackHeight(node.right);
        node.balanceFactorb = leftHeight - rightHeight;

        if (node.colour == Colour.BLACK) {
            return 1 + Math.min(leftHeight, rightHeight);
        } else {
            return Math.min(leftHeight, rightHeight);
        }
    }

    /**
     * Paints all nodes of a tree rooted at the given node to red, up to a specified depth.
     *
     * @param node  Root of the sub-tree to be painted.
     * @param depth The depth up to which nodes should be painted red.
     */
    public void paintRed(Node node, int depth) {
        if (node == null || depth <= 0) return;
        node.colour = Colour.RED;
        paintRed(node.left, depth - 1);
        paintRed(node.right, depth - 1);
    }

    /**
     * Converts an AVL tree to a Red-Black tree by painting the nodes based on AVL properties.
     *
     * @param node Root of the tree to be converted.
     */
    public void paintRedBlackFromAVL(Node node) {
        if (node == null) return;
        updateBlackHeight(node);
        paintRedBlackFromAVL(node.left);
        paintRedBlackFromAVL(node.right);

        int bfAbs = Math.abs(node.balanceFactorb);
        if (node.balanceFactorb > 0) {
            paintRed(node.left, bfAbs);
        } else if (node.balanceFactorb < 0) {
            paintRed(node.right, bfAbs);
        }
    }

    /**
     * Paints a node red or its children red in case of a red-red conflict.
     *
     * @param node Node that needs to be checked and painted.
     **/

    public void paintRedInConflict(Node node) {
        if (node == null) return;
        if (node.colour != Colour.RED) {
            node.colour = Colour.RED;
        } else {
            node.left.colour = Colour.RED;
            node.right.colour = Colour.RED;
        }
    }

    /**
     * Resolves red-red conflicts in a Red-Black tree starting from the bottom.
     *
     * @param node Root of the tree/sub-tree to resolve conflicts.
     */
    public void resolveRedRedConflictFromDown(Node node) {
        if (node == null) return;

        if (node.colour == Colour.RED && node.parent != null && node.parent.colour == Colour.RED) {
            node.colour = Colour.BLACK;
            paintRedInConflict(node.left);
            paintRedInConflict(node.right);
        }

        resolveRedRedConflictFromDown(node.left);
        resolveRedRedConflictFromDown(node.right);
    }

    /**
     * Converts an AVL tree to a Red-Black tree.
     *
     * @param tree Root of the AVL tree to be converted.
     */
    public void redBlack(Node tree) {
        if (isAVL(tree)) {
            paintRedBlackFromAVL(tree);
            resolveRedRedConflictFromDown(tree);
        } else {
            System.out.println("Not an AVL tree");
        }
    }

    /**
     * Prints the tree structure.
     *
     * @param displayColour Boolean value determining whether to display node colours.
     */
    public void printTree(boolean displayColour) {
        printTreeHelper(root, "", true, displayColour);
    }


    /**
     * Helper method to print the tree structure.
     *
     * @param node          Current node to be printed.
     * @param indent        Current indentation for printing.
     * @param isLast        Boolean value indicating if the current node is the last child.
     * @param displayColour Boolean value determining whether to display node colours.
     */
    private void printTreeHelper(Node node, String indent, boolean isLast, boolean displayColour) {
        if (node != null) {
            System.out.print(indent);
            if (isLast) {
                System.out.print("└─ ");
                indent += "  ";
            } else {
                System.out.print("├─ ");
                indent += "│ ";
            }
            System.out.print(node.key);
            if (displayColour) {
                System.out.println(" (" + node.colour + ")");
            } else {
                System.out.println();
            }
            printTreeHelper(node.left, indent, false, displayColour);
            printTreeHelper(node.right, indent, true, displayColour);
        }
    }


    public static void main(String[] args) {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        // Build a compliant AVL tree manually
        tree.root = tree.new Node(50);
        tree.root.left = tree.new Node(30);
        tree.root.right = tree.new Node(70);
        tree.root.left.parent = tree.root;
        tree.root.right.parent = tree.root;

        tree.root.left.right = tree.new Node(40);
        tree.root.left.right.parent = tree.root.left;

        tree.root.right.left = tree.new Node(60);
        tree.root.right.right = tree.new Node(80);
        tree.root.right.left.parent = tree.root.right;
        tree.root.right.right.parent = tree.root.right;

        System.out.println("AVL Tree:");
        tree.printTree(false); // Do not display colour for AVL tree

        tree.redBlack(tree.root); // Convert to Red-Black tree

        System.out.println("\nAfter Conversion to Red-Black Tree:");
        tree.printTree(true); // Display colour for Red-Black tree

    }

}

