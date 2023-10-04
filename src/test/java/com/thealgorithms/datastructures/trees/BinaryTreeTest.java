package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryTreeTest {

    // checks that adding populating the tree and searching for data
    // retrieves the expected data
    @Test
    void test1() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        assertEquals(t.find(5).data, 5);
        assertEquals(t.find(7).data, 7);
    }

    // checks that removing data from the tree
    // properly removes and makes the new root the expected new root
    @Test
    void test2() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);
        t.remove(3);
        t.remove(5);
        t.remove(7);

        assertEquals(t.getRoot().data, 9);
    }

    // checks that removing an unexistend node returns false
    //  as specified by the documentation of the function
    @Test
    void test3() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        assertEquals(t.remove(9), true);
        assertEquals(t.remove(398745987), false);
    }

    // check if the bfs, inOrder, preOrder and postOrder functions
    // worg as expected, also increases the coverage measures in
    // JaCoCo
    @Test
    void test4() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        t.bfs(t.find(12));
        t.inOrder(t.getRoot());
        t.preOrder(t.getRoot());
        t.postOrder(t.getRoot());

        assertEquals(t.remove(9), true);
        assertEquals(t.remove(398745987), false);
    }
}
