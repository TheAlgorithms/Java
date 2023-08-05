
package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CRCAlgorithmTest {

    @Test
    void test1() {
        CRCAlgorithm c = new CRCAlgorithm("10010101010100101010010000001010010101010", 10, 0.0);

        // A bit-error rate of 0.0 should not provide any wrong messages
        c.changeMess();
        c.divideMessageWithP(false);
        assertEquals(c.getWrongMess(), 0);
    }

    @Test
    void test2() {
        CRCAlgorithm c = new CRCAlgorithm("10010101010100101010010000001010010101010", 10, 1.0);

        // A bit error rate of 1.0 should not provide any correct messages
        c.changeMess();
        c.divideMessageWithP(false);
        assertEquals(c.getCorrectMess(), 0);
    }
}
