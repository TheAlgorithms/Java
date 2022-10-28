package com.thealgorithms.datastructures.trees;

import com.thealgorithms.datastructures.trees.BinaryTree.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CeilInBinarySearchTreeTest {

    @Test
    public void testRootNull() {
        assertNull(CeilInBinarySearchTree.getCeil(null, 9));
    }

    @Test
    public void testKeyPresentRootIsCeil() {
        final Node root = TreeTestUtils.createTree(new Integer[]{100, 10, 200});
        assertEquals(100, CeilInBinarySearchTree.getCeil(root, 100).data);
    }

    @Test
    public void testKeyPresentLeafIsCeil() {
        final Node root = TreeTestUtils.createTree(new Integer[]{100, 10, 200});
        assertEquals(10, CeilInBinarySearchTree.getCeil(root, 10).data);
    }

    @Test
    public void testKeyAbsentRootIsCeil() {
        final Node root = TreeTestUtils.createTree(new Integer[]{100, 10, 200, 5, 50, 150, 300});
        assertEquals(100, CeilInBinarySearchTree.getCeil(root, 75).data);
    }

    @Test
    public void testKeyAbsentLeafIsCeil() {
        final Node root = TreeTestUtils.createTree(new Integer[]{100, 10, 200, 5, 50, 150, 300});
        assertEquals(50, CeilInBinarySearchTree.getCeil(root, 40).data);
    }

    @Test
    public void testKeyAbsentLeftMostNodeIsCeil() {
        final Node root = TreeTestUtils.createTree(new Integer[]{100, 10, 200, 5, 50, 150, 300});
        assertEquals(5, CeilInBinarySearchTree.getCeil(root, 1).data);
    }

    @Test
    public void testKeyAbsentCeilIsNull() {
        final Node root = TreeTestUtils.createTree(new Integer[]{100, 10, 200, 5, 50, 150, 300});
        assertNull(CeilInBinarySearchTree.getCeil(root, 400));
    }
}
