package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AVLSimpleTest {
    ByteArrayOutputStream outContent;
    PrintStream originalOut;

    @BeforeEach
    void setupOutputStream() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testTreeCreation() {
        AVLSimple tree = new AVLSimple();
        String expectedTree = ("""
          15=>25<=30
          10=>15<=19
          5=>10<=END
          END=>5<=END
          16=>19<=20
          END=>16<=END
          END=>20<=END
          27=>30<=END
          END=>27<=END
          4""");

        tree.insert(25);
        tree.insert(30);
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(27);
        tree.insert(20);
        tree.insert(19);
        tree.insert(16);

        tree.display();

        String actualTree = outContent.toString().trim();

        assertArrayEquals(expectedTree.toCharArray(), actualTree.toCharArray());
    }

    @Test
    void testLRRotate() {
        AVLSimple tree = new AVLSimple();
        String expectedTree = ("""
          10=>20<=30
          END=>10<=END
          END=>30<=END
          2""")
          .replace("\n", "");

        tree.insert(30);
        tree.insert(10);
        tree.insert(20);

        tree.display();

        String actualTree = outContent
          .toString()
          .trim()
          .replace("\n", "");

        assertEquals(expectedTree, actualTree);
    }

    @Test
    void testRLRotate() {
        AVLSimple tree = new AVLSimple();
        String expectedTree = ("""
          10=>20<=30
          END=>10<=END
          END=>30<=END
          2""")
          .replace("\n", "");

        tree.insert(10);
        tree.insert(30);
        tree.insert(20);

        tree.display();

        String actualTree = outContent
          .toString()
          .trim()
          .replace("\n", "");

        assertEquals(expectedTree, actualTree);
    }
}
