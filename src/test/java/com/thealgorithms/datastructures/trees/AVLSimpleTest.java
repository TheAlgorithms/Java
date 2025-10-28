package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.devutils.ConsoleInterceptor;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AVLSimpleTest {
    AVLSimple tree = new AVLSimple();
    ConsoleInterceptor interceptor = new ConsoleInterceptor();

    /* ========================
        Setup/TearDown
    ======================== */

    /** Starts capturing System.in. */
    @BeforeEach
    void setup() {
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
     * Returns a string representation of the expected tree after inserting
     * the values 10, 20, and 30 in any order.
     * <p>
     * The returned string follows the same format as the tree's display method,
     * where each node is represented in the form "left=>value<=right" and "END"
     * denotes an empty (null) child.
     * @return a string representing the expected tree structure
     */
    String getExpectedTree() {
        return "10=>20<=30END=>10<=ENDEND=>30<=END2";
    }

    /**
     * Returns the actual string representation of the tree as printed
     * by the display method.
     *
     * <p>This method captures the console output via the interceptor and
     * removes all newline characters, returning a single-line string
     * suitable for comparison with the expected tree string.
     *
     * @return the actual tree structure as a single-line string
     */
    String getActualTree() {
        return interceptor.getAndClearConsoleOutput().replaceAll("[\\r\\n]", "");
    }

    /* ========================
        Tests
    ======================== */

    @Test
    @DisplayName("Creates a longer simple AVL tree that matches the expected layout")
    void testTreeCreation() {
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

        String expectedTree = "15=>25<=3010=>15<=195=>10<=ENDEND=>5<=END16=>19<=20END=>16<=ENDEND=>20<=END27=>30<=ENDEND=>27<=END4";

        assertEquals(expectedTree, getActualTree());
    }

    @Test
    @DisplayName("A test where an empty tree should return the string \"Tree is empty\".")
    void testEmptyTree() {
        AVLSimple tree = new AVLSimple();

        tree.display();

        assertEquals("Tree is empty", getActualTree());
    }

    @ParameterizedTest
    @MethodSource("getTreeNodesValues")
    @DisplayName("Test to ensure all rotation paths are covered")
    void testAllRotations(int node1, int node2, int node3, String errorMessage) {
        assertAll(() -> assertDoesNotThrow(() -> tree.insert(node1), "inserting: " + node1), () -> assertDoesNotThrow(() -> tree.insert(node2), "inserting: " + node2), () -> assertDoesNotThrow(() -> tree.insert(node3), "inserting: " + node3));

        tree.display();

        assertEquals(getExpectedTree(), getActualTree(), errorMessage);
    }

    public static Stream<Arguments> getTreeNodesValues() {
        return Stream.of(Arguments.of(30, 20, 10, "LL rotation failed"), Arguments.of(30, 10, 20, "LR rotation failed"), Arguments.of(10, 20, 30, "RR rotation failed"), Arguments.of(10, 30, 20, "RL rotation failed"));
    }

    @ParameterizedTest
    @MethodSource("getTreeNodesInputForBFEqualsOneRotations")
    @DisplayName("Checks rotation isn't triggered when balance factor equals threshold")
    void testRotationsNotTriggeredWhenBFEqualsOne(int boundaryNode, String expectedTree, String errorMessage) {
        tree.insert(20);
        tree.insert(30);
        tree.insert(10);
        tree.insert(boundaryNode);

        tree.display();

        assertEquals(expectedTree, getActualTree(), errorMessage);
    }

    public static Stream<Arguments> getTreeNodesInputForBFEqualsOneRotations() {
        return Stream.of(Arguments.of(5, "10=>20<=305=>10<=ENDEND=>5<=ENDEND=>30<=END3", "Insertion of 5 should not trigger rotation"), Arguments.of(15, "10=>20<=30END=>10<=15END=>15<=ENDEND=>30<=END3", "Insertion of 15 should not trigger rotation"),
            Arguments.of(25, "10=>20<=30END=>10<=END25=>30<=ENDEND=>25<=END3", "Insertion of 25 should not trigger rotation"), Arguments.of(35, "10=>20<=30END=>10<=ENDEND=>30<=35END=>35<=END3", "Insertion of 35 should not trigger rotation"));
    }

    @Test
    @DisplayName("Ignores duplicate insertions and create a tree with depth one")
    void testDuplicateInsertionsIgnored() {
        int duplicate = 20;
        tree.insert(duplicate);
        tree.insert(duplicate);
        tree.insert(duplicate);

        tree.display();

        String actualTree = getActualTree();

        assertEquals('1', actualTree.charAt(actualTree.length() - 1));
    }
}
