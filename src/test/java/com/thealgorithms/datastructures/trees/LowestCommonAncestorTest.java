package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class LowestCommonAncestorTest {
    /**
     * This input creates the following tree:
     * <pre>
     *     0
     *   /   \
     *  1     2
     *  |    / \
     *  5   4   3
     *  |       |
     *  6       7
     *         / \
     *        9   8
     * <pre>
     */
    private final int[] treeInput = new int[] {0,1,0,2,1,5,5,6,2,4,2,3,3,7,7,9,7,8};

    @ParameterizedTest
    @CsvSource({"9,4,2", "5,6,5", "5,4,0", "3,8,3", "6,3,0", "3,3,3"})
    void shouldReturnCorrectLCA(int v1, int v2, int expectedParent) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int actualParent;
        int arrayLength = 10;

        for (int i = 0; i < arrayLength; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < treeInput.length - 1; i +=2) {
            int to = treeInput[i];
            int from = treeInput[i+1];

            adj.get(to).add(from);
            adj.get(from).add(to);
        }

        int[] parent = new int[arrayLength];
        int[] depth = new int[arrayLength];

        try {
            Method dfs = LCA.class.getDeclaredMethod("dfs", ArrayList.class, int.class, int.class, int[].class, int[].class);
            Method getLCA = LCA.class.getDeclaredMethod("getLCA", int.class, int.class, int[].class, int[].class);
            dfs.setAccessible(true);
            getLCA.setAccessible(true);

            dfs.invoke(null, adj, 0, -1, parent, depth);

            actualParent = (int)getLCA.invoke(null, v1, v2, depth, parent);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expectedParent, actualParent);
    }
}
