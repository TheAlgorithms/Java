/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */
import java.util.LinkedList;

// Driver Program
public class TreeTraversal {
    public static void main(String[] args) {
        Node tree = new Node(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);

        // Prints 1 3 5 7 9
        tree.printInOrder();
        System.out.println();

        // Prints 5 3 1 7 9
        tree.printPreOrder();
        System.out.println();

        // Prints 1 3 9 7 5
        tree.printPostOrder();
        System.out.println();

        // Add a couple more nodes for print level test
        // Print 5 3 7 1 9
        tree.printLevelOrder();
        System.out.println();
    }
}

/**
 * The Node class which initializes a Node of a tree
 * Consists of all 3 traversal methods: printInOrder, printPostOrder & printPreOrder
 * printInOrder: LEFT -> ROOT -> RIGHT
 * printPreOrder: ROOT -> LEFT -> RIGHT
 * printPostOrder: LEFT -> RIGHT -> ROOT
 * printLevelOrder: ROOT -> ROOT's CHILDREN -> ROOT's CHILDREN's CHILDREN -> etc
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

    public void printLevelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            System.out.print(n.data + " ");
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }
}

