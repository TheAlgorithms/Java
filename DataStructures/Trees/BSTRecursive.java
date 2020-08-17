/**
 *
 *
 * <h1>Binary Search Tree (Recursive)</h1>
 *
 * An implementation of BST recursively. In recursive implementation the checks are down the tree
 * First root is checked if not found then its childs are checked Binary Search Tree is a binary
 * tree which satisfies three properties: left child is less than root node, right child is grater
 * than root node, both left and right childs must themselves be a BST.
 *
 * <p>I have made public functions as methods and to actually implement recursive approach I have
 * used private methods
 *
 * @author [Lakhan Nad](https://github.com/Lakhan-Nad)
 */
public class BSTRecursive {
  /** only data member is root of BST */
  private Node root;
  /** Constructor use to initialize node as null */
  BSTRecursive() {
    root = null;
  }

  /**
   * Recursive method to delete a data if present in BST.
   *
   * @param root the current node to search for data
   * @param data the value to be deleted
   * @return Node the updated value of root parameter after delete operation
   */
  private Node delete(Node root, int data) {
    if (root == null) {
      System.out.println("No such data present in BST.");
    } else if (root.data > data) {
      root.left = delete(root.left, data);
    } else if (root.data < data) {
      root.right = delete(root.right, data);
    } else {
      if (root.right == null && root.left == null) { // If it is leaf node
        root = null;
      } else if (root.left == null) { // If only right node is present
        Node temp = root.right;
        root.right = null;
        root = temp;
      } else if (root.right == null) { // Only left node is present
        Node temp = root.left;
        root.left = null;
        root = temp;
      } else { // both child are present
        Node temp = root.right;
        // Find leftmost child of right subtree
        while (temp.left != null) {
          temp = temp.left;
        }
        root.data = temp.data;
        root.right = delete(root.right, temp.data);
      }
    }
    return root;
  }

  /**
   * Recursive insertion of value in BST.
   *
   * @param root to check if the data can be inserted in current node or its subtree
   * @param data the value to be inserted
   * @return the modified value of the root parameter after insertion
   */
  private Node insert(Node root, int data) {
    if (root == null) {
      root = new Node(data);
    } else if (root.data > data) {
      root.left = insert(root.left, data);
    } else if (root.data < data) {
      root.right = insert(root.right, data);
    }
    return root;
  }

  /**
   * Recursively print Preorder traversal of the BST
   *
   * @param root
   */
  private void preOrder(Node root) {
    if (root == null) {
      return;
    }
    System.out.print(root.data + " ");
    if (root.left != null) {
      preOrder(root.left);
    }
    if (root.right != null) {
      preOrder(root.right);
    }
  }

  /**
   * Recursively print Postorder travesal of BST.
   *
   * @param root
   */
  private void postOrder(Node root) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      postOrder(root.left);
    }
    if (root.right != null) {
      postOrder(root.right);
    }
    System.out.print(root.data + " ");
  }

  /**
   * Recursively print Inorder traversal of BST.
   *
   * @param root
   */
  private void inOrder(Node root) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      inOrder(root.left);
    }
    System.out.print(root.data + " ");
    if (root.right != null) {
      inOrder(root.right);
    }
  }

  /**
   * Serach recursively if the given value is present in BST or not.
   *
   * @param root the current node to check
   * @param data the value to be checked
   * @return boolean if data is present or not
   */
  private boolean search(Node root, int data) {
    if (root == null) {
      return false;
    } else if (root.data == data) {
      return true;
    } else if (root.data > data) {
      return search(root.left, data);
    } else {
      return search(root.right, data);
    }
  }

  /**
   * add in BST. if the value is not already present it is inserted or else no change takes place.
   *
   * @param data the value to be inserted
   */
  public void add(int data) {
    this.root = insert(this.root, data);
  }

  /**
   * If data is present in BST delete it else do nothing.
   *
   * @param data the value to be removed
   */
  public void remove(int data) {
    this.root = delete(this.root, data);
  }

  /** To call inorder traversal on tree */
  public void inorder() {
    System.out.println("Inorder traversal of this tree is:");
    inOrder(this.root);
    System.out.println(); // for next line
  }

  /** To call postorder traversal on tree */
  public void postorder() {
    System.out.println("Postorder traversal of this tree is:");
    postOrder(this.root);
    System.out.println(); // for next li
  }

  /** To call preorder traversal on tree. */
  public void preorder() {
    System.out.println("Preorder traversal of this tree is:");
    preOrder(this.root);
    System.out.println(); // for next li
  }

  /**
   * To check if given value is present in tree or not.
   *
   * @param data
   */
  public void find(int data) {
    if (search(this.root, data)) {
      System.out.println(data + " is present in given BST.");
      return true;
    }
    System.out.println(data + " not found.");
    return false;
  }

  /** The Node class used for building binary search tree */
  private class Node {
    int data;
    Node left;
    Node right;

    /** Constructor with data as parameter */
    Node(int d) {
      data = d;
      left = null;
      right = null;
    }
  }
}
