package com.thealgorithms.datastructures.trees;

import com.thealgorithms.utils.ConsoleInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private final int[] treeInput = new int[] {0, 1, 0, 2, 1, 5, 5, 6, 2, 4, 2, 3, 3, 7, 7, 9, 7, 8};
    private final int arrayLength = 10;
    private final int[] parent = new int[arrayLength];
    private final int[] depth = new int[arrayLength];
    private final ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    @BeforeEach
    void setup() {
        for (int i = 0; i < arrayLength; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < treeInput.length - 1; i += 2) {
            int to = treeInput[i];
            int from = treeInput[i + 1];

            adj.get(to).add(from);
            adj.get(from).add(to);
        }
    }

    @Disabled("This would be the best way to test LCA but since the scanner is a\n"
      + "static global variable it doesn't work, it should be moved into the main method.")
    @ParameterizedTest
    @MethodSource("getInput")
    @DisplayName("Should return correct common ancestor for any two nodes in the tree")
    void shouldReturnCorrectLCAThroughMain(String simulatedInput, String expectedParent) {
        ConsoleInterceptor systemInOut = new ConsoleInterceptor();
        systemInOut.mockInputAndCaptureOutput(simulatedInput);

        LCA.main(new String[0]);

        systemInOut.restoreInAndOutput();

        String actualParent = systemInOut.getConsoleOutput();
        assertEquals(expectedParent, actualParent);
    }

    public static Stream<Arguments> getInput() {
        return Stream.of(
          Arguments.of("10\n0\n1\n0\n2\n1\n5\n5\n6\n2\n4\n2\n3\n3\n7\n7\n9\n7\n8\n9\n4\n", "2"),
          Arguments.of("10\n0\n1\n0\n2\n1\n5\n5\n6\n2\n4\n2\n3\n3\n7\n7\n9\n7\n8\n5\n6\n", "5"),
          Arguments.of("10\n0\n1\n0\n2\n1\n5\n5\n6\n2\n4\n2\n3\n3\n7\n7\n9\n7\n8\n5\n4\n", "0"),
          Arguments.of("10\n0\n1\n0\n2\n1\n5\n5\n6\n2\n4\n2\n3\n3\n7\n7\n9\n7\n8\n3\n8\n", "3"),
          Arguments.of("10\n0\n1\n0\n2\n1\n5\n5\n6\n2\n4\n2\n3\n3\n7\n7\n9\n7\n8\n6\n3\n", "0"),
          Arguments.of("10\n0\n1\n0\n2\n1\n5\n5\n6\n2\n4\n2\n3\n3\n7\n7\n9\n7\n8\n3\n3\n", "3")
        );
    }

    @SuppressWarnings("RFI_SET_ACCESSIBLE")
    @ParameterizedTest
    @CsvSource({"9,4,2", "5,6,5", "5,4,0", "3,8,3", "6,3,0", "3,3,3"})
    @DisplayName("Should return correct common ancestor for any two nodes in the tree")
    void shouldReturnCorrectLCA(int v1, int v2, int expectedParent) throws Exception {
            Method dfs = LCA.class.getDeclaredMethod("dfs", ArrayList.class, int.class, int.class, int[].class, int[].class);
            Method getLCA = LCA.class.getDeclaredMethod("getLCA", int.class, int.class, int[].class, int[].class);
            dfs.setAccessible(true);
            getLCA.setAccessible(true);

            dfs.invoke(null, adj, 0, -1, parent, depth);

            assertEquals(expectedParent, getLCA.invoke(null, v1, v2, depth, parent));
    }

    @SuppressWarnings("RFI_SET_ACCESSIBLE")
    @Test
    void shouldReturnCorrectDepthsForArray() throws Exception {
        Method dfs = LCA.class.getDeclaredMethod("dfs", ArrayList.class, int.class, int.class, int[].class, int[].class);
        dfs.setAccessible(true);

        dfs.invoke(null, adj, 0, -1, parent, depth);

        assertArrayEquals(new int[] {0, 1, 1, 2, 2, 2, 3, 3, 4, 4}, depth);
    }

    @SuppressWarnings("RFI_SET_ACCESSIBLE")
    @Test
    void shouldReturnCorrectParentsForArray() throws Exception {
        Method dfs = LCA.class.getDeclaredMethod("dfs", ArrayList.class, int.class, int.class, int[].class, int[].class);
        dfs.setAccessible(true);

        dfs.invoke(null, adj, 0, -1, parent, depth);

        assertArrayEquals(new int[] {0, 0, 0, 2, 2, 1, 5, 3, 7, 7}, parent);
    }
}
