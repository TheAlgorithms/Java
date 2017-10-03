/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

// Driver Program
public class TreeTraversal {
    public static void main(String[] args) {
        Node tree = new Node(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // Prints 3 5 7
        tree.printInOrder();
        System.out.println();

        // Prints 5 3 7
        tree.printPreOrder();
        System.out.println();

        // Prints 3 7 5
        tree.printPostOrder();
        System.out.println();

        // After reversing, it prints 7 5 3
        reverseTree(tree);
        tree.printInOrder();
    }

    /**
     * Method for reversing the tree.
     * It takes and swaps hole trees left and right side.
     * Example:
     *
     *       6
     *     /   \
     *    3     4
     *   / \   / \
     *  7   3 8   1
     *
     * After reverse:
     *
     *      6
     *    /   \
     *   4     3
     *  / \   / \
     * 1   8 3   7
     *
     *
    */
    public static void reverseTree(Node root) {
        if (root == null) {
            return;
        }

        reverseTree(root.left);
        reverseTree(root.right);
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
}

/**
 * The Node class which initializes a Node of a tree
 * Consists of all 3 traversal methods: printInOrder, printPostOrder & printPreOrder
 * printInOrder: LEFT -> ROOT -> RIGHT
 * printPreOrder: ROOT -> LEFT -> RIGHT
 * printPostOrder: LEFT -> RIGHT -> ROOT
 */
class Node {
    Node left, right;
    int data;

    public Node(int data) {
        this.data = data;
    }

    public void insert (int value) {
        if (value < data) {
            if (left == null) {
                left = new Node(value);
            }
            else {
                left.insert(value);
            }
        }
        else {
            if (right == null) {
                right = new Node(value);
            }
            else {
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

