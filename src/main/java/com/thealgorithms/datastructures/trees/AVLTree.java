package com.thealgorithms.datastructures.trees;

/**
 * AVL Tree (Adelson-Velsky and Landis Tree) implementation.
 * A self-balancing Binary Search Tree where the difference between heights
 * of left and right subtrees cannot be more than one for all nodes.
 * 
 * @author Raghu0703
 */
public class AVLTree {
    
    /**
     * Node class representing each element in the AVL Tree
     */
    class Node {
        int data;
        int height;
        Node left, right;
        
        public Node(int data) {
            this.data = data;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }
    
    private Node root;
    
    /**
     * Constructor to initialize empty AVL Tree
     */
    public AVLTree() {
        this.root = null;
    }
    
    /**
     * Get the height of a node
     * 
     * @param node the node
     * @return height of the node, 0 if null
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    /**
     * Get the balance factor of a node
     * 
     * @param node the node
     * @return balance factor (left height - right height)
     */
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    /**
     * Right rotate subtree rooted with y
     * 
     * @param y the root of subtree to rotate
     * @return new root after rotation
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        // Perform rotation
        x.right = y;
        y.left = T2;
        
        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }
    
    /**
     * Left rotate subtree rooted with x
     * 
     * @param x the root of subtree to rotate
     * @return new root after rotation
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        // Perform rotation
        y.left = x;
        x.right = T2;
        
        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
        return y;
    }
    
    /**
     * Insert a value into the AVL Tree
     * 
     * @param data the value to insert
     */
    public void insert(int data) {
        root = insertRec(root, data);
    }
    
    /**
     * Recursive helper method to insert a value and balance the tree
     * 
     * @param node current node
     * @param data value to insert
     * @return the balanced node
     */
    private Node insertRec(Node node, int data) {
        // Perform normal BST insertion
        if (node == null) {
            return new Node(data);
        }
        
        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        } else {
            // Duplicate values not allowed
            return node;
        }
        
        // Update height of current node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        
        // Get balance factor
        int balance = getBalance(node);
        
        // If node is unbalanced, there are 4 cases
        
        // Left Left Case
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        
        // Right Right Case
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        
        // Left Right Case
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    
    /**
     * Find the node with minimum value
     * 
     * @param node the root of subtree
     * @return node with minimum value
     */
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    /**
     * Delete a value from the AVL Tree
     * 
     * @param data the value to delete
     */
    public void delete(int data) {
        root = deleteRec(root, data);
    }
    
    /**
     * Recursive helper method to delete a value and balance the tree
     * 
     * @param root current node
     * @param data value to delete
     * @return the balanced node
     */
    private Node deleteRec(Node root, int data) {
        // Perform standard BST delete
        if (root == null) {
            return root;
        }
        
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;
                
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    // One child case
                    root = temp;
                }
            } else {
                // Node with two children
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = deleteRec(root.right, temp.data);
            }
        }
        
        // If tree had only one node
        if (root == null) {
            return root;
        }
        
        // Update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        
        // Get balance factor
        int balance = getBalance(root);
        
        // If unbalanced, there are 4 cases
        
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    
    /**
     * Search for a value in the AVL Tree
     * 
     * @param data the value to search for
     * @return true if found, false otherwise
     */
    public boolean search(int data) {
        return searchRec(root, data);
    }
    
    /**
     * Recursive helper method to search for a value
     * 
     * @param root current node
     * @param data value to search for
     * @return true if found, false otherwise
     */
    private boolean searchRec(Node root, int data) {
        if (root == null) {
            return false;
        }
        
        if (root.data == data) {
            return true;
        }
        
        if (data < root.data) {
            return searchRec(root.left, data);
        }
        
        return searchRec(root.right, data);
    }
    
    /**
     * Inorder traversal (Left-Root-Right)
     * 
     * @return string representation of inorder traversal
     */
    public String inorder() {
        StringBuilder result = new StringBuilder();
        inorderRec(root, result);
        return result.toString().trim();
    }
    
    /**
     * Recursive helper for inorder traversal
     */
    private void inorderRec(Node root, StringBuilder result) {
        if (root != null) {
            inorderRec(root.left, result);
            result.append(root.data).append(" ");
            inorderRec(root.right, result);
        }
    }
    
    /**
     * Preorder traversal (Root-Left-Right)
     * 
     * @return string representation of preorder traversal
     */
    public String preorder() {
        StringBuilder result = new StringBuilder();
        preorderRec(root, result);
        return result.toString().trim();
    }
    
    /**
     * Recursive helper for preorder traversal
     */
    private void preorderRec(Node root, StringBuilder result) {
        if (root != null) {
            result.append(root.data).append(" ");
            preorderRec(root.left, result);
            preorderRec(root.right, result);
        }
    }
    
    /**
     * Get the height of the tree
     * 
     * @return the height of the tree
     */
    public int getHeight() {
        return height(root);
    }
    
    /**
     * Check if the tree is empty
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Check if the tree is balanced (for testing purposes)
     * 
     * @return true if balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalancedRec(root);
    }
    
    /**
     * Recursive helper to check if tree is balanced
     */
    private boolean isBalancedRec(Node node) {
        if (node == null) {
            return true;
        }
        
        int balance = getBalance(node);
        
        if (Math.abs(balance) > 1) {
            return false;
        }
        
        return isBalancedRec(node.left) && isBalancedRec(node.right);
    }
}
