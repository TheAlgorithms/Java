package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

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
            for (int i = 0; i < nodesOnLevel; i++) {
                BinaryTree.Node tempNode = q.poll();
                level.add(tempNode.data);

                if (tempNode.left != null) {
                    q.add(tempNode.left);
                }

                if (tempNode.right != null) {
                    q.add(tempNode.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
