package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.devutils.ConsoleInterceptor;
import java.util.InputMismatchException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GenericTreeTest {
    GenericTree tree;
    ConsoleInterceptor interceptor = new ConsoleInterceptor();

    /* ========================
        Setup/TearDown
    ======================== */

    @BeforeEach
    void setup() {
        String treeValues = "40\n5\n34\n0\n1\n0\n32\n1\n123\n0\n342\n2\n98\n0\n35\n0\n12\n0";
        interceptor.mockInput(treeValues);
        interceptor.captureOutput();

        tree = new GenericTree();

        interceptor.clearConsoleOutput();
    }

    @AfterEach
    void tearDown() {
        interceptor.close();
    }

    /* ========================
        Helper methods
    ======================== */

    /**
     * Creates a generic tree that looks something like this:
     *
     * <pre>
     *                    40
     *                / / | \  \
     *              /  /  |  \  \
     *             34 1   32 324 12
     *                    |  | \
     *                  123 98 35
     * </pre>
     * @return The tree in a string format mimicking what the display() method gives.
     */
    String getExpectedTree() {
        return "40=>34 1 32 342 12 .34=>.1=>.32=>123 .123=>.342=>98 35 .98=>.35=>.12=>.";
    }

    String getConsoleOutput() {
        return interceptor.getAndClearConsoleOutput().replaceAll("[\\r\\n]", "");
    }

    /* ========================
        Tests
    ======================== */

    @Test
    void testCreateValidTree() {
        tree.display();

        assertEquals(getExpectedTree(), getConsoleOutput());
    }

    @Test
    void testCreateValidTreeIsNotEmpty() {
        tree.display();

        assertDoesNotThrow(interceptor::getAndClearConsoleOutput);
    }

    @Test
    void testCreateInValidTree() {
        String invalidTreeValues = "a\nb\nc\n";
        interceptor.mockInput(invalidTreeValues);

        assertThrows(InputMismatchException.class, GenericTree::new);
    }

    @Test
    void testGettingCorrectSizeOfTree() {
        assertEquals(9, tree.size2call());
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
    @ValueSource(ints = {41, 31, 2, 52, 542, 223, 92, 38})
    void testFindingAbsentValuesInTree(int value) {
        assertFalse(tree.findcall(value));
    }

    @Test
    void testFindingAllNodesOfCertainDepthInTree() {
        int height = tree.heightcall();

        for (int i = 0; i <= height; i++) {
            tree.depthcaller(i);
        }

        assertEquals("4034132342121239835", getConsoleOutput());
    }

    @Test
    void testPreOrderPrintsAsExpected() {
        tree.preordercall();
        assertEquals("40 34 1 32 123 342 98 35 12 .", getConsoleOutput());
    }

    @Test
    void testPostOrderPrintsAsExpected() {
        tree.postordercall();
        assertEquals("34 1 123 32 98 35 342 12 40 .", getConsoleOutput());
    }

    @Test
    void testLevelOrderPrintsAsExpected() {
        tree.levelorder();
        assertEquals("40 34 1 32 342 12 123 98 35 .", getConsoleOutput());
    }

    @Test
    void testLeavesAreRemoved() {
        tree.removeleavescall();
        tree.display();

        assertEquals("40=>32 342 .32=>.342=>.", getConsoleOutput());
    }
}
