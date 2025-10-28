package com.thealgorithms.datastructures.trees;

import com.thealgorithms.devutils.ConsoleInterceptor;
import java.util.InputMismatchException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GenericTreeTest {
    GenericTree tree;
    ConsoleInterceptor interceptor = new ConsoleInterceptor();

    /* ========================
        Setup/TearDown
    ======================== */

    /**
     * Sets up the test environment before each test:
     * <ol>
     *   <li>Makes the interceptor provide a predefined sequence of values for the tree.</li>
     *   <li>Initializes a new GenericTree instance.</li>
     *   <li>Starts capturing console output via the interceptor.</li>
     * </ol>
     */
    @BeforeEach
    void setup() {
        String treeValues = "40\n5\n34\n0\n1\n0\n32\n1\n123\n0\n342\n2\n98\n0\n35\n0\n12\n0";
        interceptor.mockInput(treeValues);

        tree = new GenericTree();

        interceptor.captureOutput();
    }

    /** Cleans up after each test by closing the interceptor. */
    @AfterEach
    void tearDown() {
        interceptor.close();
    }

    /* ========================
        Helper methods
    ======================== */

    /**
     * Returns the string representation of the generic tree that's created in the {@link #setup()} method.
     * It looks something like this:
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

    /**
     * Returns the actual string representation of the tree as printed
     * by the display method.
     * <p>
     * This method captures the console output via the interceptor and
     * removes all newline characters, returning a single-line string
     * suitable for comparison with the expected tree string.
     * @return the actual tree structure as a single-line string
     */
    String getConsoleOutput() {
        return interceptor.getAndClearConsoleOutput().replaceAll("[\\r\\n]", "");
    }

    /* ========================
        Tests
    ======================== */

    @Test
    @DisplayName("Asserts the created tree have the expected structure")
    void testValidTree() {
        tree.display();

        Assertions.assertEquals(getExpectedTree(), getConsoleOutput());
    }

    @Test
    @DisplayName("Creating GenericTree with invalid input throws InputMismatchException")
    void testCreateInValidTree() {
        String invalidTreeValues = "a\nb\nc\n";
        interceptor.mockInput(invalidTreeValues);

        Assertions.assertThrows(InputMismatchException.class, GenericTree::new);
    }

    @Test
    @DisplayName("Gets the correct number of nodes in the tree")
    void testGettingCorrectSizeOfTree() {
        Assertions.assertEquals(9, tree.size2call());
    }

    @Test
    @DisplayName("Gets the highest value among the trees nodes")
    void testGettingTheMaximalValueOfTreesNodes() {
        Assertions.assertEquals(342, tree.maxcall());
    }

    @Test
    @DisplayName("Returns the correct height of the tree")
    void testGettingTheHeightOfTree() {
        Assertions.assertEquals(2, tree.heightcall());
    }

    @ParameterizedTest
    @ValueSource(ints = {40, 34, 1, 32, 342, 123, 98, 35})
    @DisplayName("Returns true when searching for values that is in the tree")
    void testFindingAllPresentValuesInTree(int value) {
        Assertions.assertTrue(tree.findcall(value), "The expected value " + value + " wasn't in the tree");
    }

    @ParameterizedTest
    @ValueSource(ints = {41, 31, 2, 52, 542, 223, 92, 38})
    @DisplayName("Returns false when searching for values that isn't in the tree")
    void testFindingAbsentValuesInTree(int value) {
        Assertions.assertFalse(tree.findcall(value), "The value " + value + " was unexpectedly in the tree");
    }

    @Test
    @DisplayName("Outputs all nodes at each tree level from left to right")
    void testFindingAllNodesOfCertainDepthInTree() {
        int height = tree.heightcall();

        for (int i = 0; i <= height; i++) {
            tree.depthcaller(i);
        }

        Assertions.assertEquals("4034132342121239835", getConsoleOutput());
    }

    @Test
    @DisplayName("Prints all node values in pre order")
    void testPreOrderPrintsAsExpected() {
        tree.preordercall();
        Assertions.assertEquals("40 34 1 32 123 342 98 35 12 .", getConsoleOutput());
    }

    @Test
    @DisplayName("Prints all node values in post order")
    void testPostOrderPrintsAsExpected() {
        tree.postordercall();
        Assertions.assertEquals("34 1 123 32 98 35 342 12 40 .", getConsoleOutput());
    }

    @Test
    @DisplayName("Prints all node values in level order")
    void testLevelOrderPrintsAsExpected() {
        tree.levelorder();
        Assertions.assertEquals("40 34 1 32 342 12 123 98 35 .", getConsoleOutput());
    }

    @Test
    @DisplayName("Removes all leaves from the tree")
    void testLeavesAreRemoved() {
        tree.removeleavescall();
        tree.display();

        Assertions.assertEquals("40=>32 342 .32=>.342=>.", getConsoleOutput());
    }
}
