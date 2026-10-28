package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Test class for MosAlgorithm
 *
 * @author BEASTSHRIRAM
 */
class MosAlgorithmTest {

    @Test
    void testRangeSumQueriesBasic() {
        int[] arr = {1, 3, 5, 2, 7};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 2, 0), // Sum of [1, 3, 5] = 9
            new MosAlgorithm.Query(1, 3, 1), // Sum of [3, 5, 2] = 10
            new MosAlgorithm.Query(2, 4, 2) // Sum of [5, 2, 7] = 14
        };

        int[] expected = {9, 10, 14};
        int[] results = MosAlgorithm.solveRangeSumQueries(arr, queries);

        assertArrayEquals(expected, results);
    }

    @Test
    void testRangeSumQueriesSingleElement() {
        int[] arr = {5, 10, 15, 20};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 0, 0), // Sum of [5] = 5
            new MosAlgorithm.Query(1, 1, 1), // Sum of [10] = 10
            new MosAlgorithm.Query(2, 2, 2), // Sum of [15] = 15
            new MosAlgorithm.Query(3, 3, 3) // Sum of [20] = 20
        };

        int[] expected = {5, 10, 15, 20};
        int[] results = MosAlgorithm.solveRangeSumQueries(arr, queries);

        assertArrayEquals(expected, results);
    }

    @Test
    void testRangeSumQueriesFullArray() {
        int[] arr = {1, 2, 3, 4, 5};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 4, 0) // Sum of entire array = 15
        };

        int[] expected = {15};
        int[] results = MosAlgorithm.solveRangeSumQueries(arr, queries);

        assertArrayEquals(expected, results);
    }

    @Test
    void testRangeSumQueriesOverlapping() {
        int[] arr = {2, 4, 6, 8, 10};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 2, 0), // Sum of [2, 4, 6] = 12
            new MosAlgorithm.Query(1, 3, 1), // Sum of [4, 6, 8] = 18
            new MosAlgorithm.Query(2, 4, 2) // Sum of [6, 8, 10] = 24
        };

        int[] expected = {12, 18, 24};
        int[] results = MosAlgorithm.solveRangeSumQueries(arr, queries);

        assertArrayEquals(expected, results);
    }

    @Test
    void testRangeFrequencyQueriesBasic() {
        int[] arr = {1, 2, 2, 1, 3, 2, 1};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 3, 0), // Count of 2 in [1, 2, 2, 1] = 2
            new MosAlgorithm.Query(1, 5, 1), // Count of 2 in [2, 2, 1, 3, 2] = 3
            new MosAlgorithm.Query(4, 6, 2) // Count of 2 in [3, 2, 1] = 1
        };

        int[] expected = {2, 3, 1};
        int[] results = MosAlgorithm.solveRangeFrequencyQueries(arr, queries, 2);

        assertArrayEquals(expected, results);
    }

    @Test
    void testRangeFrequencyQueriesNoMatch() {
        int[] arr = {1, 3, 5, 7, 9};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 2, 0), // Count of 2 in [1, 3, 5] = 0
            new MosAlgorithm.Query(1, 4, 1) // Count of 2 in [3, 5, 7, 9] = 0
        };

        int[] expected = {0, 0};
        int[] results = MosAlgorithm.solveRangeFrequencyQueries(arr, queries, 2);

        assertArrayEquals(expected, results);
    }

    @Test
    void testRangeFrequencyQueriesAllMatch() {
        int[] arr = {5, 5, 5, 5, 5};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 2, 0), // Count of 5 in [5, 5, 5] = 3
            new MosAlgorithm.Query(1, 3, 1), // Count of 5 in [5, 5, 5] = 3
            new MosAlgorithm.Query(0, 4, 2) // Count of 5 in [5, 5, 5, 5, 5] = 5
        };

        int[] expected = {3, 3, 5};
        int[] results = MosAlgorithm.solveRangeFrequencyQueries(arr, queries, 5);

        assertArrayEquals(expected, results);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        MosAlgorithm.Query[] queries = {};

        int[] expected = {};
        int[] results = MosAlgorithm.solveRangeSumQueries(arr, queries);

        assertArrayEquals(expected, results);
    }

    @Test
    void testNullInputs() {
        int[] results1 = MosAlgorithm.solveRangeSumQueries(null, null);
        assertArrayEquals(new int[0], results1);

        int[] results2 = MosAlgorithm.solveRangeFrequencyQueries(null, null, 1);
        assertArrayEquals(new int[0], results2);
    }

    @Test
    void testQueryStructure() {
        MosAlgorithm.Query query = new MosAlgorithm.Query(1, 5, 0);

        assertEquals(1, query.left);
        assertEquals(5, query.right);
        assertEquals(0, query.index);
        assertEquals(0, query.result); // Default value
    }

    @Test
    void testLargerArray() {
        int[] arr = {1, 4, 2, 8, 5, 7, 3, 6, 9, 10};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 4, 0), // Sum of [1, 4, 2, 8, 5] = 20
            new MosAlgorithm.Query(2, 7, 1), // Sum of [2, 8, 5, 7, 3, 6] = 31
            new MosAlgorithm.Query(5, 9, 2), // Sum of [7, 3, 6, 9, 10] = 35
            new MosAlgorithm.Query(1, 8, 3) // Sum of [4, 2, 8, 5, 7, 3, 6, 9] = 44
        };

        int[] expected = {20, 31, 35, 44};
        int[] results = MosAlgorithm.solveRangeSumQueries(arr, queries);

        assertArrayEquals(expected, results);
    }

    @Test
    void testRangeFrequencyWithDuplicates() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        MosAlgorithm.Query[] queries = {
            new MosAlgorithm.Query(0, 5, 0), // Count of 1 in [3, 1, 4, 1, 5, 9] = 2
            new MosAlgorithm.Query(3, 9, 1), // Count of 1 in [1, 5, 9, 2, 6, 5, 3] = 1
            new MosAlgorithm.Query(0, 9, 2) // Count of 1 in entire array = 2
        };

        int[] expected = {2, 1, 2};
        int[] results = MosAlgorithm.solveRangeFrequencyQueries(arr, queries, 1);

        assertArrayEquals(expected, results);
    }

    @Test
    void testMainMethod() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Test main method
            MosAlgorithm.main(new String[] {});
            String output = outputStream.toString();

            // Verify expected output contains demonstration
            assertTrue(output.contains("Range Sum Queries:"));
            assertTrue(output.contains("Range Frequency Queries (count of value 3):"));
            assertTrue(output.contains("Array: [1, 3, 5, 2, 7, 6, 3, 1, 4, 8]"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
