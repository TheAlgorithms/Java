package datastructures.trees;

/**
 * The Node class which initializes a Node of a tree
 * Consists of all 3 traversal methods: printInOrder, printPostOrder & printPreOrder
 * printInOrder: LEFT -> ROOT -> RIGHT
 * printPreOrder: ROOT -> LEFT -> RIGHT
 * printPostOrder: LEFT -> RIGHT -> ROOT
 */
public class TreeTraversal {
    private TreeTraversal left, right;
    private int data;

    public TreeTraversal(int data) {
        this.data = data;
    }

    public void insert(int value) {
        if (value < data) {
            if (left == null) {
                left = new TreeTraversal(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new TreeTraversal(value);
            } else {
                right.insert(value);
            }
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.print(data + " ");
        if (right != null) {
            right.printInOrder();
        }
    }

    public void printPreOrder() {
        System.out.print(data + " ");
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }

    public void printPostOrder() {
        if (left != null) {
            left.printPostOrder();
        }
        if (right != null) {
            right.printPostOrder();
        }
        System.out.print(data + " ");
    }
}

