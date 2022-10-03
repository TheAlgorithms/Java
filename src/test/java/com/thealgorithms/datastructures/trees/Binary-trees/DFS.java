package BinaryTrees;

import java.util.*;

public class test {

    // Structure of binary tree node
    static class Node {
        private int val;
        private Node left, right;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> depthFirstTraversal(Node root) {
        Stack<Node> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node current = stack.peek();
            stack.pop();
            list.add(current.val);
            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
        }
        return list;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n6.right = n7;

        /*
                 1
               /   \
              2     3
             / \   /
            4   5 6
                   \
                    7
        */

        System.out.println("\nDepth First Traversal: " + depthFirstTraversal(n1));
    }
}
