package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* Class to print Level Order Traversal */
public class LevelOrderTraversal {

    /* Given a binary tree. Print its nodes in level order
  using array for implementing queue  */
    static List<List<Integer>> traverse(BinaryTree.Node root) {
        if (root == null) {
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();

        Queue<BinaryTree.Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int nodesOnLevel = q.size();
            List<Integer> level = new LinkedList<>();
            /* poll() removes the present head.
               For more information on poll() visit
               http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
            for (int i = 0; i < nodesOnLevel; i++) {
                BinaryTree.Node tempNode = q.poll();
                level.add(tempNode.data);

                /*Enqueue left child */
                if (tempNode.left != null) {
                    q.add(tempNode.left);
                }

                /*Enqueue right child */
                if (tempNode.right != null) {
                    q.add(tempNode.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
