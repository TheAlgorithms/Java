// File 2: BinarySearchTree.java
package trees;

/**
 * Binary Search Tree Implementation
 *
 * A Binary Search Tree (BST) is a data structure that maintains sorted data
 * and enables O(log n) search, insertion, and deletion operations on average.
 *
 * Reference: https://en.wikipedia.org/wiki/Binary_search_tree
 *
 * Operations: - Insert: Add new value maintaining BST property - Delete: Remove
 * value while maintaining BST property - Search: Find value in O(log n) average
 * time - Traversals: Inorder, Preorder, Postorder
 */
public class BinarySearchTree {
  private TreeNode root;
  private static final String EMPTY_TREE_ERROR = "Tree is empty";

  public BinarySearchTree() {
    this.root = null;
  }

  // ============= INSERT OPERATION =============
  public void insert(int value) {
    root = insertRecursive(root, value);
  }

  private TreeNode insertRecursive(TreeNode node, int value) {
    if (node == null) {
      return new TreeNode(value);
    }

    if (value < node.value) {
      node.left = insertRecursive(node.left, value);
    } else if (value > node.value) {
      node.right = insertRecursive(node.right, value);
    }
    // If value == node.value, duplicate is ignored

    return node;
  }

  // ============= SEARCH OPERATION =============
  public boolean search(int value) {
    return searchRecursive(root, value);
  }

  private boolean searchRecursive(TreeNode node, int value) {
    if (node == null) {
      return false;
    }

    if (value == node.value) {
      return true;
    } else if (value < node.value) {
      return searchRecursive(node.left, value);
    } else {
      return searchRecursive(node.right, value);
    }
  }

  // ============= DELETE OPERATION =============
  public void delete(int value) {
    root = deleteRecursive(root, value);
  }

  private TreeNode deleteRecursive(TreeNode node, int value) {
    if (node == null) {
      return null;
    }

    if (value < node.value) {
      node.left = deleteRecursive(node.left, value);
    } else if (value > node.value) {
      node.right = deleteRecursive(node.right, value);
    } else {
      // Node to delete found

      // Case 1: Node has no children (leaf)
      if (node.left == null && node.right == null) {
        return null;
      }

      // Case 2: Node has one child
      if (node.left == null) {
        return node.right;
      }
      if (node.right == null) {
        return node.left;
      }

      // Case 3: Node has two children
      // Find inorder successor (smallest in right subtree)
      TreeNode minRight = findMin(node.right);
      node.value = minRight.value;
      node.right = deleteRecursive(node.right, minRight.value);
    }

    return node;
  }

  // ============= UTILITY METHODS =============
  public int findMin() {
    if (root == null) {
      throw new IllegalStateException(EMPTY_TREE_ERROR);
    }
    return findMin(root).value;
  }

  private TreeNode findMin(TreeNode node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  public int findMax() {
    if (root == null) {
      throw new IllegalStateException(EMPTY_TREE_ERROR);
    }
    return findMax(root).value;
  }

  private TreeNode findMax(TreeNode node) {
    while (node.right != null) {
      node = node.right;
    }
    return node;
  }

  // ============= TREE TRAVERSALS =============
  public void inorder() {
    System.out.print("Inorder: ");
    inorderRecursive(root);
    System.out.println();
  }

  private void inorderRecursive(TreeNode node) {
    if (node != null) {
      inorderRecursive(node.left);
      System.out.print(node.value + " ");
      inorderRecursive(node.right);
    }
  }

  public void preorder() {
    System.out.print("Preorder: ");
    preorderRecursive(root);
    System.out.println();
  }

  private void preorderRecursive(TreeNode node) {
    if (node != null) {
      System.out.print(node.value + " ");
      preorderRecursive(node.left);
      preorderRecursive(node.right);
    }
  }

  public void postorder() {
    System.out.print("Postorder: ");
    postorderRecursive(root);
    System.out.println();
  }

  private void postorderRecursive(TreeNode node) {
    if (node != null) {
      postorderRecursive(node.left);
      postorderRecursive(node.right);
      System.out.print(node.value + " ");
    }
  }

  // ============= HELPER METHODS =============
  public boolean isEmpty() {
    return root == null;
  }

  public void clear() {
    root = null;
  }
}