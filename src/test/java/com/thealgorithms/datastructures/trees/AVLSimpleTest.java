package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AVLSimpleTest {

    private AVLSimple tree;

    @BeforeEach
    public void setup() {
        tree = new AVLSimple();
    }

    @Test
    public void testInsertAndDisplay() {
        tree.insert(20);
        tree.insert(30);
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        // The output is not directly testable since display prints to console,
        // so we'll check the height of the tree to verify that balancing occurred
        assertEquals(3, tree.getHeight());
    }

    @Test
    public void testInsertAndBalance() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30); // This will cause a left rotation
        assertEquals(20, tree.getRoot().data);
        assertEquals(10, tree.getRoot().left.data);
        assertEquals(30, tree.getRoot().right.data);
    }

    @Test
    public void testInsertMultipleValues() {
        int[] values = {30, 20, 40, 10, 25, 35, 50};
        for (int value : values) {
            tree.insert(value);
        }
        assertEquals(30, tree.getRoot().data);
        assertEquals(20, tree.getRoot().left.data);
        assertEquals(40, tree.getRoot().right.data);
        assertEquals(10, tree.getRoot().left.left.data);
        assertEquals(25, tree.getRoot().left.right.data);
        assertEquals(35, tree.getRoot().right.left.data);
        assertEquals(50, tree.getRoot().right.right.data);
    }

    @Test
    public void testHeightAfterInsertions() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        assertEquals(3, tree.getHeight());
    }
}
