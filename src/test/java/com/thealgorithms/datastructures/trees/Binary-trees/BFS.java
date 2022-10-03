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

    // Breadth First traversal (Level Order Traversal)
    public static List<Integer> breadthFirstTraversal(Node root) {
        List<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            list.add(current.val);
            if(current.left!=null) queue.offer(current.left);
            if(current.right!=null) queue.offer(current.right);
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

        System.out.println("\nBreadth First Traversal: " + breadthFirstTraversal(n1));
    }
}
