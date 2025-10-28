package com.thealgorithms.tree.AVLtree;

public class CheckBalance extends AVLInsert {

    public boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balance = getBalance(node);
        if (Math.abs(balance) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }
}
