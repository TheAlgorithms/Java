package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericTreeTest {
    GenericTree tree;
    ByteArrayOutputStream outContent;
    PrintStream originalOut;

    /* ========================
        Setup/TearDown
    ======================== */

    @BeforeEach
    void setup() {
        String simulatedScannerInput = "40\n4\n34\n0\n1\n0\n32\n1\n123\n0\n342\n2\n98\n0\n35\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedScannerInput.getBytes()));

        tree = new GenericTree();

        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    /* ========================
        Helper methods
    ======================== */

    String getActualTree() {
        return outContent
          .toString()
          .trim()
          .replace("\n", "");
    }

    /**
     * Creates a generic tree that looks something like this:
     *
     * <pre>
     *                   40
     *                / / \  \
     *              /  /   \  \
     *             34 1    32 324
     *                     |  / \
     *                   123 98 35
     * </pre>
     * @return The tree in a string format mimicking what the display() method gives.
     */
    String getExpectedTree() {
        return "40=>34 1 32 342 .34=>.1=>.32=>123 .123=>.342=>98 35 .98=>.35=>.";
    }

    /* ========================
        Tests
    ======================== */

    @Test
    void testCreateTree() {
        tree.display();

        assertEquals(getExpectedTree(), getActualTree());
    }

    @Test
    void testGettingCorrectSizeOfTree() {
        assertEquals(8, tree.size2call());
    }

    @Test
    void testGettingTheMaximalValueOfTreesNodes() {
        assertEquals(342, tree.maxcall());
    }

    @Test
    void testGettingTheHeightOfTree() {
        assertEquals(2, tree.heightcall());
    }

    @ParameterizedTest
    @ValueSource(ints = {40, 34, 1, 32, 342, 123, 98, 35})
    void testFindingAllPresentValuesInTree(int value) {
        assertTrue(tree.findcall(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {41, 35, 2, 52, 542, 223, 92, 38})
    void testFindingAbsentValuesInTree() {
        assertFalse(tree.findcall(666));
    }
}
