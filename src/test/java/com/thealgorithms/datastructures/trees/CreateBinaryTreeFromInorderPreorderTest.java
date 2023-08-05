package com.thealgorithms.datastructures.trees;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 14/05/2023
 */
public class CreateBinaryTreeFromInorderPreorderTest {
    @Test
    public void testOnNullArraysShouldReturnNullTree() {
        // when
        BinaryTree.Node root = CreateBinaryTreeFromInorderPreorder.createTree(null, null);
        BinaryTree.Node rootOpt = CreateBinaryTreeFromInorderPreorder.createTreeOptimized(null, null);

        // then
        Assertions.assertNull(root);
        Assertions.assertNull(rootOpt);
    }

    @Test
    public void testOnEmptyArraysShouldCreateNullTree() {
        // given
        Integer[] preorder = {};
        Integer[] inorder = {};

        // when
        BinaryTree.Node root = CreateBinaryTreeFromInorderPreorder.createTree(preorder, inorder);
        BinaryTree.Node rootOpt = CreateBinaryTreeFromInorderPreorder.createTreeOptimized(preorder, inorder);

        // then
        Assertions.assertNull(root);
        Assertions.assertNull(rootOpt);
    }

    @Test
    public void testOnSingleNodeTreeShouldCreateCorrectTree() {
        // given
        Integer[] preorder = {1};
        Integer[] inorder = {1};

        // when
        BinaryTree.Node root = CreateBinaryTreeFromInorderPreorder.createTree(preorder, inorder);
        BinaryTree.Node rootOpt = CreateBinaryTreeFromInorderPreorder.createTreeOptimized(preorder, inorder);

        // then
        checkTree(preorder, inorder, root);
        checkTree(preorder, inorder, rootOpt);
    }

    @Test
    public void testOnRightSkewedTreeShouldCreateCorrectTree() {
        // given
        Integer[] preorder = {1, 2, 3, 4};
        Integer[] inorder = {1, 2, 3, 4};

        // when
        BinaryTree.Node root = CreateBinaryTreeFromInorderPreorder.createTree(preorder, inorder);
        BinaryTree.Node rootOpt = CreateBinaryTreeFromInorderPreorder.createTreeOptimized(preorder, inorder);

        // then
        checkTree(preorder, inorder, root);
        checkTree(preorder, inorder, rootOpt);
    }

    @Test
    public void testOnLeftSkewedTreeShouldCreateCorrectTree() {
        // given
        Integer[] preorder = {1, 2, 3, 4};
        Integer[] inorder = {4, 3, 2, 1};

        // when
        BinaryTree.Node root = CreateBinaryTreeFromInorderPreorder.createTree(preorder, inorder);
        BinaryTree.Node rootOpt = CreateBinaryTreeFromInorderPreorder.createTreeOptimized(preorder, inorder);

        // then
        checkTree(preorder, inorder, root);
        checkTree(preorder, inorder, rootOpt);
    }

    @Test
    public void testOnNormalTreeShouldCreateCorrectTree() {
        // given
        Integer[] preorder = {3, 9, 20, 15, 7};
        Integer[] inorder = {9, 3, 15, 20, 7};

        // when
        BinaryTree.Node root = CreateBinaryTreeFromInorderPreorder.createTree(preorder, inorder);
        BinaryTree.Node rootOpt = CreateBinaryTreeFromInorderPreorder.createTreeOptimized(preorder, inorder);

        // then
        checkTree(preorder, inorder, root);
        checkTree(preorder, inorder, rootOpt);
    }

    private static void checkTree(Integer[] preorder, Integer[] inorder, BinaryTree.Node root) {
        Assertions.assertNotNull(root);
        Assertions.assertEquals(PreOrderTraversal.iterativePreOrder(root), Arrays.asList(preorder));
        Assertions.assertEquals(InorderTraversal.iterativeInorder(root), Arrays.asList(inorder));
    }
}
