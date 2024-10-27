package com.thealgorithms.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree data structure where elements have two successors (children).
 * The left child is smaller than the parent, and the right child is larger.
 *
 * @author Unknown
 */
public class BinaryTree {

    /**
     * Node class represents elements in the Binary Tree, with pointers to
     * the left and right children and a reference to its parent node.
     */
    static class Node {
        public int data;
        public Node left, right, parent;

        public Node(int value) {
            data = value;
            left = right = parent = null;
        }
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (key < current.data) {
                if (current.left == null) return current;
                current = current.left;
            } else if (key > current.data) {
                if (current.right == null) return current;
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    public void put(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = find(value);
            if (value < parent.data) {
                parent.left = newNode;
                parent.left.parent = parent;
            } else {
                parent.right = newNode;
                parent.right.parent = parent;
            }
        }
    }

    public boolean remove(int value) {
        Node temp = find(value);
        if (temp == null || temp.data != value) return false;

        if (temp.left == null && temp.right == null) {
            if (temp == root)
                root = null;
            else if (temp.parent.data < temp.data)
                temp.parent.right = null;
            else
                temp.parent.left = null;
            return true;
        } else if (temp.left != null && temp.right != null) {
            Node successor = findSuccessor(temp);
            successor.left = temp.left;
            successor.left.parent = successor;
            if (successor.parent != temp) {
                if (successor.right != null) {
                    successor.right.parent = successor.parent;
                    successor.parent.left = successor.right;
                } else
                    successor.parent.left = null;
                successor.right = temp.right;
                successor.right.parent = successor;
            }
            if (temp == root) {
                root = successor;
                successor.parent = null;
            } else {
                successor.parent = temp.parent;
                if (temp.parent.data < temp.data)
                    temp.parent.right = successor;
                else
                    temp.parent.left = successor;
            }
            return true;
        } else {
            Node child = (temp.right != null) ? temp.right : temp.left;
            if (temp == root)
                root = child;
            else {
                child.parent = temp.parent;
                if (temp.data < temp.parent.data)
                    temp.parent.left = child;
                else
                    temp.parent.right = child;
            }
            return true;
        }
    }

    public Node findSuccessor(Node n) {
        if (n.right == null) return n;
        Node current = n.right;
        while (current.left != null) current = current.left;
        return current;
    }

    public Node getRoot() {
        return root;
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.left);
            System.out.print(localRoot.data + " ");
            inOrder(localRoot.right);
        }
    }

    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.data + " ");
            preOrder(localRoot.left);
            preOrder(localRoot.right);
        }
    }

    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.left);
            postOrder(localRoot.right);
            System.out.print(localRoot.data + " ");
        }
    }

    public void bfs(Node localRoot) {
        if (localRoot == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(localRoot);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}
