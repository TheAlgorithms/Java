package com.thealgorithms.datastructures.trees;

/**
 *
 *
 * <h1>Binary Search Tree (Iterative)</h1>
 *
 * <p>
 * An implementation of BST iteratively. Binary Search Tree is a binary tree
 * which satisfies three properties: left child is less than root node, right
 * child is grater than root node, both left and right childs must themselves be
 * a BST.
 *
 * @author [Lakhan Nad](https://github.com/Lakhan-Nad)
 */
import java.util.Stack;

public class BSTIterative {

    /**
     * Reference for the node of BST.
     */
    private Node root;

    /**
     * Default Constructor Initializes the root of BST with null.
     */
    BSTIterative() {
        root = null;
    }

    /**
     * main function for tests
     */
    public static void main(String[] args) {
        BSTIterative tree = new BSTIterative();
        tree.add(3);
        tree.add(2);
        tree.add(9);
        assert !tree.find(4) : "4 is not yet present in BST";
        assert tree.find(2) : "2 should be present in BST";
        tree.remove(2);
        assert !tree.find(2) : "2 was just deleted from BST";
        tree.remove(1);
        assert !tree.find(1) : "Since 1 was not present so find deleting would do no change";
        tree.add(30);
        tree.add(40);
        assert tree.find(40) : "40 was inserted but not found";
        /*
       Will print following order
       3 9 30 40
         */
        tree.inorder();
    }

    /**
     * A method to insert a new value in BST. If the given value is already
     * present in BST the insertion is ignored.
     *
     * @param data the value to be inserted
     */
    public void add(int data) {
        Node parent = null;
        Node temp = this.root;
        int rightOrLeft = -1;
        /* Finds the proper place this node can
     * be placed in according to rules of BST.
         */
        while (temp != null) {
            if (temp.data > data) {
                parent = temp;
                temp = parent.left;
                rightOrLeft = 0;
            } else if (temp.data < data) {
                parent = temp;
                temp = parent.right;
                rightOrLeft = 1;
            } else {
                System.out.println(data + " is already present in BST.");
                return; // if data already present we ignore insertion
            }
        }
        /* Creates a newNode with the value passed
     * Since this data doesn't already exists
         */
        Node newNode = new Node(data);
        /* If the parent node is null
     * then the insertion is to be done in
     * root itself.
         */
        if (parent == null) {
            this.root = newNode;
        } else {
            /* Check if insertion is to be made in
       * left or right subtree.
             */
            if (rightOrLeft == 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    /**
     * A method to delete the node in BST. If node is present it will be deleted
     *
     * @param data the value that needs to be deleted
     */
    public void remove(int data) {
        Node parent = null;
        Node temp = this.root;
        int rightOrLeft = -1;
        /* Find the parent of the node and node itself
     * That is to be deleted.
     * parent variable store parent
     * temp stores node itself.
     * rightOrLeft use to keep track weather child
     * is left or right subtree
         */
        while (temp != null) {
            if (temp.data == data) {
                break;
            } else if (temp.data > data) {
                parent = temp;
                temp = parent.left;
                rightOrLeft = 0;
            } else {
                parent = temp;
                temp = parent.right;
                rightOrLeft = 1;
            }
        }
        /* If temp is null than node with given value is not
     * present in our tree.
         */
        if (temp != null) {
            Node replacement; // used to store the new values for replacing nodes
            if (temp.right == null && temp.left == null) { // Leaf node Case
                replacement = null;
            } else if (temp.right == null) { // Node with only right child
                replacement = temp.left;
                temp.left = null;
            } else if (temp.left == null) { // Node with only left child
                replacement = temp.right;
                temp.right = null;
            } else {
                /* If both left and right child are present
         * we replace this nodes data with
         * leftmost node's data in its right subtree
         * to maintain the balance of BST.
         * And then delete that node
                 */
                if (temp.right.left == null) {
                    temp.data = temp.right.data;
                    replacement = temp;
                    temp.right = temp.right.right;
                } else {
                    Node parent2 = temp.right;
                    Node child = temp.right.left;
                    while (child.left != null) {
                        parent2 = child;
                        child = parent2.left;
                    }
                    temp.data = child.data;
                    parent2.left = child.right;
                    replacement = temp;
                }
            }
            /* Change references of parent after
       * deleting the child.
             */
            if (parent == null) {
                this.root = replacement;
            } else {
                if (rightOrLeft == 0) {
                    parent.left = replacement;
                } else {
                    parent.right = replacement;
                }
            }
        }
    }

    /**
     * A method for inorder traversal of BST.
     */
    public void inorder() {
        if (this.root == null) {
            System.out.println("This BST is empty.");
            return;
        }
        System.out.println("Inorder traversal of this tree is:");
        Stack<Node> st = new Stack<Node>();
        Node cur = this.root;
        while (cur != null || !st.empty()) {
            while (cur != null) {
                st.push(cur);
                cur = cur.left;
            }
            cur = st.pop();
            System.out.print(cur.data + " ");
            cur = cur.right;
        }
        System.out.println(); // for next line
    }

    /**
     * A method used to print postorder traversal of BST.
     */
    public void postorder() {
        if (this.root == null) {
            System.out.println("This BST is empty.");
            return;
        }
        System.out.println("Postorder traversal of this tree is:");
        Stack<Node> st = new Stack<Node>();
        Node cur = this.root, temp2;
        while (cur != null || !st.empty()) {
            if (cur != null) {
                st.push(cur);
                cur = cur.left;
            } else {
                temp2 = st.peek();
                if (temp2.right != null) {
                    cur = temp2.right;
                } else {
                    st.pop();
                    while (!st.empty() && st.peek().right == temp2) {
                        System.out.print(temp2.data + " ");
                        temp2 = st.pop();
                    }
                    System.out.print(temp2.data + " ");
                }
            }
        }
        System.out.println(); // for next line
    }

    /**
     * Method used to display preorder traversal of BST.
     */
    public void preorder() {
        if (this.root == null) {
            System.out.println("This BST is empty.");
            return;
        }
        System.out.println("Preorder traversal of this tree is:");
        Stack<Node> st = new Stack<Node>();
        st.push(this.root);
        Node temp;
        while (!st.empty()) {
            temp = st.pop();
            System.out.print(temp.data + " ");
            if (temp.right != null) {
                st.push(temp.right);
            }
            if (temp.left != null) {
                st.push(temp.left);
            }
        }
        System.out.println(); // for next line
    }

    /**
     * A method to check if given data exists in out Binary Search Tree.
     *
     * @param data the value that needs to be searched for
     * @return boolean representing if the value was find
     */
    public boolean find(int data) {
        Node temp = this.root;
        /* Check if node exists
         */
        while (temp != null) {
            if (temp.data > data) {
                temp = temp.left;
            } else if (temp.data < data) {
                temp = temp.right;
            } else {
                /* If found return true
                 */
                System.out.println(data + " is present in the BST.");
                return true;
            }
        }
        System.out.println(data + " not found.");
        return false;
    }

    /**
     * The Node class used for building binary search tree
     */
    private static class Node {

        int data;
        Node left;
        Node right;

        /**
         * Constructor with data as parameter
         */
        Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }
}
