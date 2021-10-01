package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/* Class to print Level Order Traversal */
public class LevelOrderTraversalQueue {

  /* Class to represent Tree node */
  class Node {
    int data;
    Node left, right;

    public Node(int item) {
      data = item;
      left = null;
      right = null;
    }
  }

  /* Given a binary tree. Print its nodes in level order
  using array for implementing queue  */
  void printLevelOrder(Node root) {
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);
    while (!queue.isEmpty()) {

      /* poll() removes the present head.
      For more information on poll() visit
      http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
      Node tempNode = queue.poll();
      System.out.print(tempNode.data + " ");

      /*Enqueue left child */
      if (tempNode.left != null) {
        queue.add(tempNode.left);
      }

      /*Enqueue right child */
      if (tempNode.right != null) {
        queue.add(tempNode.right);
      }
    }
  }
}
