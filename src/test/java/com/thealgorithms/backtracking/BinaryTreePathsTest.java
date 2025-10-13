package com.thealgorithms.backtracking;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreePathsTest {

    @Test
    void testBinaryTreePathsBasic() {
        BinaryTreePaths.TreeNode root = new BinaryTreePaths.TreeNode(1);
        root.left = new BinaryTreePaths.TreeNode(2);
        root.right = new BinaryTreePaths.TreeNode(3);
        root.left.right = new BinaryTreePaths.TreeNode(5);

        BinaryTreePaths solver = new BinaryTreePaths();
        List<String> result = solver.binaryTreePaths(root);

        assertEquals(2, result.size());
        assertTrue(result.contains("1->2->5"));
        assertTrue(result.contains("1->3"));
    }

    @Test
    void testSingleNodeTree() {
        BinaryTreePaths.TreeNode root = new BinaryTreePaths.TreeNode(42);

        BinaryTreePaths solver = new BinaryTreePaths();
        List<String> result = solver.binaryTreePaths(root);

        assertEquals(List.of("42"), result);
    }

    @Test
    void testEmptyTree() {
        BinaryTreePaths solver = new BinaryTreePaths();
        List<String> result = solver.binaryTreePaths(null);

        assertTrue(result.isEmpty());
    }
}
