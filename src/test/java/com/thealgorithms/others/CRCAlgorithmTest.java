package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRCAlgorithmTest {

    @Test
    void testNoErrorsWithZeroBER() {
        CRCAlgorithm c = new CRCAlgorithm("10010101010100101010010000001010010101010", 10, 0.0);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        c.divideMessageWithP(true);
        assertEquals(0, c.getWrongMess(), "BER=0 should produce no wrong messages");
        assertEquals(0, c.getWrongMessCaught(), "No errors, so no caught wrong messages");
        assertEquals(0, c.getWrongMessNotCaught(), "No errors, so no uncaught wrong messages");
        assertTrue(c.getCorrectMess() > 0, "Should have some correct messages");
    }

    @Test
    void testAllErrorsWithBEROne() {
        CRCAlgorithm c = new CRCAlgorithm("10010101010100101010010000001010010101010", 10, 1.0);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        c.divideMessageWithP(true);
        assertTrue(c.getWrongMess() > 0, "BER=1 should produce wrong messages");
        assertEquals(0, c.getCorrectMess(), "BER=1 should produce no correct messages");
    }

    @Test
    void testIntermediateBER() {
        CRCAlgorithm c = new CRCAlgorithm("1101", 4, 0.5);
        c.generateRandomMess();
        for (int i = 0; i < 1000; i++) {
            c.refactor();
            c.generateRandomMess();
            c.divideMessageWithP(false);
            c.changeMess();
            c.divideMessageWithP(true);
        }
        assertTrue(c.getWrongMess() > 0, "Some wrong messages expected with BER=0.5");
        assertTrue(c.getWrongMessCaught() >= 0, "Wrong messages caught counter >= 0");
        assertTrue(c.getWrongMessNotCaught() >= 0, "Wrong messages not caught counter >= 0");
        assertTrue(c.getCorrectMess() >= 0, "Correct messages counter >= 0");
        assertEquals(c.getWrongMess(), c.getWrongMessCaught() + c.getWrongMessNotCaught(), "Sum of caught and not caught wrong messages should equal total wrong messages");
    }

    @Test
    void testMessageChangedFlag() {
        CRCAlgorithm c = new CRCAlgorithm("1010", 4, 1.0);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        assertTrue(c.getWrongMess() > 0, "Message should be marked as changed with BER=1");
    }

    @Test
    void testSmallMessageSize() {
        CRCAlgorithm c = new CRCAlgorithm("11", 2, 0.0);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        c.divideMessageWithP(true);
        assertEquals(0, c.getWrongMess(), "No errors expected for BER=0 with small message");
    }

    @Test
    void testLargeMessageSize() {
        CRCAlgorithm c = new CRCAlgorithm("1101", 1000, 0.01);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        c.divideMessageWithP(true);
        // Just ensure counters are updated, no exceptions
        assertTrue(c.getWrongMess() >= 0);
        assertTrue(c.getCorrectMess() >= 0);
    }
}
