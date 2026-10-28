/*
 * TheAlgorithms (https://github.com/TheAlgorithms/Java)
 * Author: Shewale41
 * This file is licensed under the MIT License.
 */

package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Basic tests for ThreadedBinaryTree inorder traversal.
 */
public class ThreadedBinaryTreeTest {

    @Test
    public void testInorderTraversalSimple() {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        List<Integer> expected = List.of(20, 30, 40, 50, 60, 70, 80);
        List<Integer> actual = tree.inorderTraversal();

        assertEquals(expected, actual);
    }

    @Test
    public void testInorderWithDuplicates() {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(7); // duplicate
        tree.insert(2);

        List<Integer> expected = List.of(2, 3, 5, 7, 7);
        List<Integer> actual = tree.inorderTraversal();

        assertEquals(expected, actual);
    }
}
