package Traversals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Yolanda Tang (https://github.com/ReturnHttp402)
 *
 * This is pure Java implementation of tree traversal algorithms
 *
 */
public class BinaryTreeTraverse {

    public static class TreeNode{
        public int nodeValue;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value){
            this.nodeValue = value;
        }
    }
    private static void insertRec(TreeNode latestRoot, TreeNode node) {

        if (latestRoot.nodeValue > node.nodeValue) {
            if (latestRoot.left == null) {
                latestRoot.left = node;
                return;
            } else {
                insertRec(latestRoot.left, node);
            }
        } else {
            if (latestRoot.right == null) {
                latestRoot.left = node;
                return;
            } else {
                insertRec(latestRoot.right, node);
            }
        }
    }
    public static class BinarySearchTree {
        public TreeNode root;

        public void insert(int value) {
            TreeNode node = new TreeNode(value);

            if (root == null) {
                root = node;
                return;
            }
            insertRec(root,node);

        }
    }
    public static void main(String[] args) {
        System.out.println("\n********* Binary Tree Traversals ************\n");
        Scanner in = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("********Press N to stop entering at any point of time********");
        while(true) {
            if (in.next().equalsIgnoreCase("N")) {
                break;
            }
            System.out.println("Enter the value of the node: ");
            tree.insert(in.nextInt());
        }

        System.out.println("\n********* Depth First ************");
        System.out.println("\n********* Pre Order Traversal ************");
        preOrder(tree.root);

        System.out.println("\n********* In Order Traversal ************");
        inOrder(tree.root);

        System.out.println("\n********* Post Order Traversal ************");
        postOrder(tree.root);

        System.out.println("\n********* Breadth First ************");
        System.out.println("\n********* Level Order Traversal ************");
        levelOrder(tree.root);
    }
    public static void preOrder(TreeNode node){
        if(node == null) {
            return;
        }
        System.out.print(node.nodeValue+",");
        preOrder(node.left);
        preOrder(node.right);
    }
    public static void inOrder(TreeNode node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.nodeValue+",");
        inOrder(node.right);
    }
    public static void postOrder(TreeNode node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.nodeValue+",");
    }
    public static void levelOrder(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            TreeNode nodeDequeued = queue.peek();
            System.out.print(nodeDequeued.nodeValue+",");
            queue.poll();
            if(nodeDequeued.left != null){
                queue.offer(nodeDequeued.left);
            }
            if(nodeDequeued.right != null){
                queue.offer(nodeDequeued.right);
            }
        }
    }

}


