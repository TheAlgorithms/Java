package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.devutils.ConsoleInterceptor;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LCATest {
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
    private static final String TREE = "10\n0\n1\n0\n2\n1\n5\n5\n6\n2\n4\n2\n3\n3\n7\n7\n9\n7\n8\n";

    /* ========================
        Tests
    ======================== */

    @ParameterizedTest
    @MethodSource("getSimulatedInputAndExpectedParent")
    @DisplayName("Should return correct common ancestor for any two nodes in the tree")
    void shouldReturnCorrectLCAThroughMain(String simulatedInput, String expectedParent) {
        try (ConsoleInterceptor interceptor = new ConsoleInterceptor()) {
            interceptor.mockInput(simulatedInput);
            interceptor.captureOutput();

            LCA.main(new String[0]);

            String actualParent = interceptor.getAndClearConsoleOutput().replaceAll("[\\r\\n]", "");

            assertEquals(expectedParent, actualParent, "The two nodes Lowest Common Ancestor wasn't the expected one.");
        }
    }

    public static Stream<Arguments> getSimulatedInputAndExpectedParent() {
        return Stream.of(Arguments.of(TREE + "9\n4\n", "2"), Arguments.of(TREE + "5\n6\n", "5"), Arguments.of(TREE + "5\n4\n", "0"), Arguments.of(TREE + "3\n8\n", "3"), Arguments.of(TREE + "6\n3\n", "0"), Arguments.of(TREE + "3\n3\n", "3"));
    }
}
