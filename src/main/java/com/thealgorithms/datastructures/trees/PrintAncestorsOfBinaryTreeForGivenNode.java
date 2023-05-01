package com.thealgorithms.datastructures.trees;

/*  For a Given a Binary Tree and a target key, you need to find all the ancestors of the given target key.
                1
                /   \
                2      3
                /  \
                4     5
                /
                7
        Key: 7
        Ancestor: 4 2 1 */


public class PrintAncestorsOfBinaryTreeForGivenNode {
    public static class TreeNode
    {
        int data;
        TreeNode left, right, nextRight;

        TreeNode(int item)
        {
            data = item;
            left = right = nextRight = null;
        }
    }

    public static class BinaryTreeForPrintAncestor
    {
        TreeNode root;

    /* If target is present in tree, then prints the ancestors
       and returns true, otherwise returns false. */
        boolean printAncestors(TreeNode node, int target)
        {
            /* base cases */
            if (node == null)
                return false;

            if (node.data == target)
                return true;

        /* If target is present in either left or right subtree
           of this node, then print this node */
            if (printAncestors(node.left, target)
                    || printAncestors(node.right, target))
            {
                System.out.print(node.data + " ");
                return true;
            }

            return false;
        }
        public static void main(String args[])
        {
            BinaryTreeForPrintAncestor tree = new BinaryTreeForPrintAncestor();

        /* Construct the following binary tree
                  1
                /   \
               2     3
              /  \
             4    5
            /
           7
        */
            tree.root = new TreeNode(1);
            tree.root.left = new TreeNode(2);
            tree.root.right = new TreeNode(3);
            tree.root.left.left = new TreeNode(4);
            tree.root.left.right = new TreeNode(5);
            tree.root.left.left.left = new TreeNode(7);

            tree.printAncestors(tree.root, 7);

        }
    }
}
