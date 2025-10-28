package com.thealgorithms.tree.AVLtree;

public class CheckBalance extends AVLInsert {

    boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balance = getBalance(node);
        if (Math.abs(balance) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    public static void main(String[] args) {
        CheckBalance tree = new CheckBalance();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        System.out.println("Is AVL Tree balanced? " + tree.isBalanced(tree.root));

        // Manually unbalance it
        tree.root.left.left = new Node(5);
        System.out.println("Is AVL Tree balanced after modification? " + tree.isBalanced(tree.root));
    }
}
