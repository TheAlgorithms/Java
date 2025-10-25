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

    @Test
    void testSingleBitMessage() {
        CRCAlgorithm c = new CRCAlgorithm("11", 1, 0.0);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        c.divideMessageWithP(true);

        assertTrue(c.getCorrectMess() >= 0, "Single bit message should be handled");
    }

    @Test
    void testPolynomialLongerThanMessage() {
        CRCAlgorithm c = new CRCAlgorithm("11010101", 3, 0.0);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        c.divideMessageWithP(true);

        // Should not crash, counters should be valid
        assertTrue(c.getCorrectMess() + c.getWrongMess() >= 0);
    }

    @Test
    void testPolynomialWithOnlyOnes() {
        CRCAlgorithm c = new CRCAlgorithm("1111", 5, 0.1);
        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();
        c.divideMessageWithP(true);

        assertTrue(c.getCorrectMess() + c.getWrongMess() >= 0);
    }

    @Test
    void testMultipleRefactorCalls() {
        CRCAlgorithm c = new CRCAlgorithm("1101", 5, 0.2);

        for (int i = 0; i < 5; i++) {
            c.refactor();
            c.generateRandomMess();
            c.divideMessageWithP(false);
            c.changeMess();
            c.divideMessageWithP(true);
        }

        // Counters should accumulate across multiple runs
        assertTrue(c.getCorrectMess() + c.getWrongMess() > 0);
    }

    @Test
    void testCounterConsistency() {
        CRCAlgorithm c = new CRCAlgorithm("1101", 10, 0.3);

        for (int i = 0; i < 100; i++) {
            c.refactor();
            c.generateRandomMess();
            c.divideMessageWithP(false);
            c.changeMess();
            c.divideMessageWithP(true);
        }

        // Total messages processed should equal correct + wrong
        int totalProcessed = c.getCorrectMess() + c.getWrongMess();
        assertEquals(100, totalProcessed, "Total processed messages should equal iterations");

        // Wrong messages should equal caught + not caught
        assertEquals(c.getWrongMess(), c.getWrongMessCaught() + c.getWrongMessNotCaught(), "Wrong messages should equal sum of caught and not caught");
    }

    @Test
    void testGetterMethodsInitialState() {
        CRCAlgorithm c = new CRCAlgorithm("1101", 10, 0.1);

        // Check initial state
        assertEquals(0, c.getCorrectMess(), "Initial correct messages should be 0");
        assertEquals(0, c.getWrongMess(), "Initial wrong messages should be 0");
        assertEquals(0, c.getWrongMessCaught(), "Initial caught wrong messages should be 0");
        assertEquals(0, c.getWrongMessNotCaught(), "Initial not caught wrong messages should be 0");
    }
}
