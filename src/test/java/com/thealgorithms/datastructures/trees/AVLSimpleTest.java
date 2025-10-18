package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AVLSimpleTest {
    AVLSimple tree = new AVLSimple();

    @BeforeEach
    void setup() {
        tree.insert(20);
        tree.insert(25);
        tree.insert(30);
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(27);
        tree.insert(19);
        tree.insert(16);
    }

    @Test
    void test() {
        tree.display();
        System.out.println();
    }
}
