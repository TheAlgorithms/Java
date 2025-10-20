package com.thealgorithms.datastructures.trees;

import com.thealgorithms.utils.ConsoleInterceptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AVLSimpleTest {
    AVLSimple tree = new AVLSimple();
    ConsoleInterceptor interceptor = new ConsoleInterceptor();

    /* ========================
        Setup/TearDown
    ======================== */

    @BeforeEach
    void setup() {
        interceptor.captureOutput();
    }

    @AfterEach
    void tearDown() {
        interceptor.close();
    }

    /* ========================
        Helper methods
    ======================== */

    String getExpectedTree() {
        return ("""
          10=>20<=30
          END=>10<=END
          END=>30<=END
          2""")
          .replace("\n", "");
    }

    String getActualTree() {
        return interceptor.getAndClearConsoleOutput().replaceAll("[\\r\\n]", "");
    }

    /* ========================
        Tests
    ======================== */

    @Test
    @DisplayName("A longer generic AVL tree creation that should pass")
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
          4""")
          .replace("\n", "");

        assertEquals(expectedTree, getActualTree());
    }

    @ParameterizedTest
    @MethodSource("getTreeNodesInput")
    @DisplayName("Test to ensure all rotation paths are covered")
    void testAllRotations(int node1, int node2, int node3) {
        tree.insert(node1);
        tree.insert(node2);
        tree.insert(node3);

        tree.display();

        assertEquals(getExpectedTree(), getActualTree());
    }

    public static Stream<Arguments> getTreeNodesInput() {
        return Stream.of(
          Arguments.of(30, 20, 10),
          Arguments.of(30, 10, 20),
          Arguments.of(10, 20, 30),
          Arguments.of(10, 30, 20)
        );
    }

    @ParameterizedTest
    @MethodSource("getTreeNodesInputForBFEqualsOneRotations")
    @DisplayName("Rotation not triggered when balance factor equals threshold")
    void testRotatesNotTriggeredWhenBFEqualsOne(int node, String expectedTree) {
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        tree.insert(node);

        tree.display();

        assertEquals(expectedTree, getActualTree());
    }

    public static Stream<Arguments> getTreeNodesInputForBFEqualsOneRotations() {
        return Stream.of(
          Arguments.of(5, ("""
          10=>20<=30
          5=>10<=END
          END=>5<=END
          END=>30<=END
          3""")
            .replace("\n", "")),
          Arguments.of(35, ("""
          10=>20<=30
          END=>10<=END
          END=>30<=35
          END=>35<=END
          3""")
            .replace("\n", ""))
        );
    }

    // TODO: Add rotation tests for RL and LR when bf = 1

    @Test
    @DisplayName("Should return true for a tree that don't account for duplicates")
    void testDuplicatesInTreeCreationDoNotStick() {
        int duplicate = 20;
        tree.insert(30);
        tree.insert(duplicate);
        tree.insert(20);
        tree.insert(duplicate);
        tree.insert(10);

        tree.display();

        assertEquals(getExpectedTree(), getActualTree());
    }
}
