package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the BinaryTreeToString class.
 */
public class BinaryTreeToStringTest {

    @Test
    public void testTreeToStringBasic() {
        BinaryTree tree = new BinaryTree();
        tree.put(1);
        tree.put(2);
        tree.put(3);
        tree.put(4);

        BinaryTreeToString converter = new BinaryTreeToString();
        String result = converter.tree2str(tree.getRoot());

        // Output will depend on insertion logic of BinaryTree.put()
        // which is BST-style, so result = "1()(2()(3()(4)))"
        Assertions.assertEquals("1()(2()(3()(4)))", result);
    }

    @Test
    public void testSingleNodeTree() {
        BinaryTree tree = new BinaryTree();
        tree.put(10);

        BinaryTreeToString converter = new BinaryTreeToString();
        String result = converter.tree2str(tree.getRoot());

        Assertions.assertEquals("10", result);
    }

    @Test
    public void testComplexTreeStructure() {
        BinaryTree.Node root = new BinaryTree.Node(10);
        root.left = new BinaryTree.Node(5);
        root.right = new BinaryTree.Node(20);
        root.right.left = new BinaryTree.Node(15);
        root.right.right = new BinaryTree.Node(25);

        BinaryTreeToString converter = new BinaryTreeToString();
        String result = converter.tree2str(root);

        Assertions.assertEquals("10(5)(20(15)(25))", result);
    }

    @Test
    public void testNullTree() {
        BinaryTreeToString converter = new BinaryTreeToString();
        Assertions.assertEquals("", converter.tree2str(null));
    }
}
