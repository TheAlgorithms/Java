package DynamicProgramming;

import static org.junit.jupiter.api.Assertions.*;

class KadaneAlgorithmTest {

    /* solution
    https://docs.google.com/document/d/1rl2sWdRUjv73N0SBd_RFCjNJatJaKL_qWrTIVrM5M_c/edit*/

    @org.junit.jupiter.api.Test
    void largestContiguousSum1() {
        assertEquals(0, KadaneAlgorithm.largestContiguousSum(new int[]{0}));
    }

    @org.junit.jupiter.api.Test
    void largestContiguousSum2() {
        assertEquals(1, KadaneAlgorithm.largestContiguousSum(new int[]{1}));
    }

    @org.junit.jupiter.api.Test
    void largestContiguousSum3() {
        assertEquals(-3, KadaneAlgorithm.largestContiguousSum(new int[]{-3, -3}));
    }

    @org.junit.jupiter.api.Test
    void largestContiguousSum4() {
        assertEquals(3, KadaneAlgorithm.largestContiguousSum(new int[]{1, -2, 3}));
    }

    @org.junit.jupiter.api.Test
    void largestContiguousSum5() {
        assertEquals(2147483647, KadaneAlgorithm.largestContiguousSum(new int[]{2147483647, 1, -2147483647}));
    }

    @org.junit.jupiter.api.Test
    void largestContiguousSum6() {
        assertEquals(6, KadaneAlgorithm.largestContiguousSum(new int[]{1, 2, 3}));
    }
}
