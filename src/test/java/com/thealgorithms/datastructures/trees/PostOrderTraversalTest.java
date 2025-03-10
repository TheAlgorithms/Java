package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Given tree is traversed in a 'post-order' way: LEFT -> RIGHT -> ROOT.
 *
 * @author Albina Gimaletdinova on 21/02/2023
 */
public class PostOrderTraversalTest {
    @Test
    public void testNullRoot() {
        assertEquals(Collections.emptyList(), PostOrderTraversal.recursivePostOrder(null));
        assertEquals(Collections.emptyList(), PostOrderTraversal.iterativePostOrder(null));
    }

    /*
         1
        / \
       2   3
      /\   /\
     4  5 6  7
    */
    @Test
    public void testPostOrder() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        List<Integer> expected = List.of(4, 5, 2, 6, 7, 3, 1);

        assertEquals(expected, PostOrderTraversal.recursivePostOrder(root));
        assertEquals(expected, PostOrderTraversal.iterativePostOrder(root));
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
    public void testPostOrderNonBalanced() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {5, null, 6, null, 7, null, 8});
        List<Integer> expected = List.of(8, 7, 6, 5);

        assertEquals(expected, PostOrderTraversal.recursivePostOrder(root));
        assertEquals(expected, PostOrderTraversal.iterativePostOrder(root));
    }
}
