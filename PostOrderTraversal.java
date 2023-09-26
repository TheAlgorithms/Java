package tree;

import java.util.Stack;

public class PostOrderTraversal {

    void iterativePostOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> currentNode = root;
        BinaryTreeNode<Integer> prevNode = null;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            BinaryTreeNode<Integer> topNode = stack.peek();

            if (topNode.right == null || topNode.right == prevNode) {
                // If the top node's right subtree is null or has been processed,
                // it's time to process the root node
                System.out.print(topNode.data + " ");
                stack.pop();
                prevNode = topNode;
            } else {
                // Move to the right subtree
                currentNode = topNode.right;
            }
        }
    }

    public static void main(String[] args) {
        PostOrderTraversal post = new PostOrderTraversal();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1);
        root.left = new BinaryTreeNode<>(2);
        root.right = new BinaryTreeNode<>(3);
        root.left.left = new BinaryTreeNode<>(4);
        root.left.right = new BinaryTreeNode<>(5);
        post.iterativePostOrder(root);
    }
}
