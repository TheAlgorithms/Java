package com.thealgorithms.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public
class BinaryTree {

    static class Node {
        public
        int data;
        public
        Node left;
        public
        Node right;
        public
        Node parent;

        Node(int value) {
            data = value;
            left = null;
            right = null;
            parent = null;
        }
    }

    private Node root;
    private
    int size; // Keep track of the number of nodes

    public
    BinaryTree() {
        root = null;
        size = 0; // Initialize size
    }

    public
    void put(int value) {
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
        size++; // Increment size on insertion
    }

    public
    boolean remove(int value) {
        Node temp = find(value);
        if (temp == null || temp.data != value) {
            return false;
        }

        // No children
        if (temp.left == null && temp.right == null) {
            if (temp == root) {
                root = null;
            } else if (temp.parent.data < temp.data) {
                temp.parent.right = null;
            } else {
                temp.parent.left = null;
            }
        }
        // Two children
        else if (temp.left != null && temp.right != null) {
            Node successor = findSuccessor(temp);
            successor.left = temp.left;
            if (temp.left != null) {
                temp.left.parent = successor;
            }
            if (successor.parent != temp) {
                if (successor.right != null) {
                    successor.right.parent = successor.parent;
                    successor.parent.left = successor.right;
                } else {
                    successor.parent.left = null;
                }
                successor.right = temp.right;
                if (temp.right != null) {
                    temp.right.parent = successor;
                }
            }
            if (temp == root) {
                successor.parent = null;
                root = successor;
            } else {
                successor.parent = temp.parent;
                if (temp.parent.data < temp.data) {
                    temp.parent.right = successor;
                } else {
                    temp.parent.left = successor;
                }
            }
        }
        // One child
        else {
            Node child = (temp.left != null) ? temp.left : temp.right;
            if (temp == root) {
                root = child;
                if (child != null) {
                    child.parent = null; // Update parent reference
                }
            } else {
                child.parent = temp.parent;
                if (temp.parent.data < temp.data) {
                    temp.parent.right = child;
                } else {
                    temp.parent.left = child;
                }
            }
        }

        // Decrement size after successful removal
        size--;
        return true;
    }

    public
    Node find(int key) {
        Node current = root;
        while (current != null) {
            if (key < current.data) {
                if (current.left == null) {
                    return current; // Return parent
                }
                current = current.left;
            } else if (key > current.data) {
                if (current.right == null) {
                    return current; // Return parent
                }
                current = current.right;
            } else {
                return current; // Found node
            }
        }
        return null;
    }

    public
    Node getRoot() { return root; }

    public
    int size() {
        return size; // Getter for size
    }

    public
    Node findSuccessor(Node n) {
        if (n.right == null) {
            return n;
        }
        Node current = n.right;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Breadth-First Search (Level Order Traversal)
    public
    void bfs() {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    // In-order Traversal
    public
    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    // Pre-order Traversal
    public
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Post-order Traversal
    public
    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
}
