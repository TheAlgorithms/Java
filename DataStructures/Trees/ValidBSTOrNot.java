package DataStructures.Trees;

public class ValidBSTOrNot {

  class Node {
    int data;
    Node left, right;

    public Node(int item) {
      data = item;
      left = right = null;
    }
  }

  // Root of the Binary Tree

  /* can give min and max value according to your code or
  can write a function to find min and max value of tree. */

  /* returns true if given search tree is binary
  search tree (efficient version) */
  boolean isBST(Node root) {
    return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  /* Returns true if the given tree is a BST and its
  values are >= min and <= max. */
  boolean isBSTUtil(Node node, int min, int max) {
    /* an empty tree is BST */
    if (node == null) return true;

    /* false if this node violates the min/max constraints */
    if (node.data < min || node.data > max) return false;

    /* otherwise check the subtrees recursively
    tightening the min/max constraints */
    // Allow only distinct values
    return (isBSTUtil(node.left, min, node.data - 1) && isBSTUtil(node.right, node.data + 1, max));
  }
}
