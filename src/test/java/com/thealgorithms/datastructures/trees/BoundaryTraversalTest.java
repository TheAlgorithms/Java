package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class BoundaryTraversalTest {

    @Test
    public void testNullRoot() {
        assertEquals(Collections.emptyList(), BoundaryTraversal.boundaryTraversal(null));
        assertEquals(Collections.emptyList(), BoundaryTraversal.iterativeBoundaryTraversal(null));
    }

    @Test
    public void testSingleNodeTree() {
        final BinaryTree.Node root = new BinaryTree.Node(1);

        List<Integer> expected = List.of(1);

        assertEquals(expected, BoundaryTraversal.boundaryTraversal(root));
        assertEquals(expected, BoundaryTraversal.iterativeBoundaryTraversal(root));
    }

    /*
        1
       / \
      2   3
     / \ / \
    4  5 6  7

    */
    @Test
    public void testCompleteBinaryTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5, 6, 7});

        List<Integer> expected = List.of(1, 2, 4, 5, 6, 7, 3);

        assertEquals(expected, BoundaryTraversal.boundaryTraversal(root));
        assertEquals(expected, BoundaryTraversal.iterativeBoundaryTraversal(root));
    }

    /*
        1
       / \
      2   7
     /     \
    3       8
     \     /
      4   9
         / \
        5   6
           / \
         10  11
    */
    @Test
    public void testBoundaryTraversal() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 7, 3, null, null, 8, null, 4, 9, null, 5, 6, 10, 11});

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 10, 11, 9, 8, 7);

        assertEquals(expected, BoundaryTraversal.boundaryTraversal(root));
        assertEquals(expected, BoundaryTraversal.iterativeBoundaryTraversal(root));
    }

    /*
          1
         /
        2
       /
      3
     /
    4
    */
    @Test
    public void testLeftSkewedTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, null, 3, null, 4, null});

        List<Integer> expected = List.of(1, 2, 3, 4);

        assertEquals(expected, BoundaryTraversal.boundaryTraversal(root));
        assertEquals(expected, BoundaryTraversal.iterativeBoundaryTraversal(root));
    }

    /*
              5
               \
                6
                 \
                  7
                   \
                    8
    */
    @Test
    public void testRightSkewedTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {5, null, 6, null, 7, null, 8});

        List<Integer> expected = List.of(5, 6, 7, 8);

        assertEquals(expected, BoundaryTraversal.boundaryTraversal(root));
        assertEquals(expected, BoundaryTraversal.iterativeBoundaryTraversal(root));
    }
}
