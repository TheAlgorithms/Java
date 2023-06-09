package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author kumanoit on 10/10/22 IST 1:02 AM
 */
public class CheckTreeIsSymmetricTest {

    @Test
    public void testRootNull() {
        assertTrue(CheckTreeIsSymmetric.isSymmetric(null));
    }

    @Test
    public void testSingleNodeTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {100});
        assertTrue(CheckTreeIsSymmetric.isSymmetric(root));
    }

    @Test
    public void testSymmetricTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 2, 3, 4, 4, 3});
        assertTrue(CheckTreeIsSymmetric.isSymmetric(root));
    }

    @Test
    public void testNonSymmetricTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 2, 3, 5, 4, 3});
        assertFalse(CheckTreeIsSymmetric.isSymmetric(root));
    }
}
