package com.thealgorithms.ciphers.a5;

import static com.thealgorithms.ciphers.a5.A5KeyStreamGenerator.FRAME_COUNTER_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.BitSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class A5KeyStreamGeneratorTest {

    private A5KeyStreamGenerator keyStreamGenerator;
    private BitSet frameCounter;

    @BeforeEach
    void setUp() {
        keyStreamGenerator = new A5KeyStreamGenerator();

        // Initialize session key and frame counter for testing
        final var sessionKey = BitSet.valueOf(new long[] {0b1010101010101010L}); // Example 16-bit key
        frameCounter = BitSet.valueOf(new long[] {0b0000000000000001L}); // Example 16-bit frame counter
        keyStreamGenerator.initialize(sessionKey, frameCounter);
    }

    @Test
    void testInitialization() {
        // Verify that the internal state is set up correctly
        assertNotNull(keyStreamGenerator, "KeyStreamGenerator should be initialized");
    }

    @Test
    void testIncrementFrameCounter() {
        // Generate key stream to increment the frame counter
        keyStreamGenerator.getNextKeyStream();

        // The frame counter should have been incremented
        BitSet incrementedFrameCounter = keyStreamGenerator.getFrameCounter();

        // Check if the incremented frame counter is expected
        BitSet expectedFrameCounter = (BitSet) frameCounter.clone();
        Utils.increment(expectedFrameCounter, FRAME_COUNTER_LENGTH);

        assertEquals(expectedFrameCounter, incrementedFrameCounter, "Frame counter should be incremented after generating key stream");
    }

    @Test
    void testGetNextKeyStreamProducesDifferentOutputs() {
        // Generate a key stream
        BitSet firstKeyStream = keyStreamGenerator.getNextKeyStream();

        // Generate another key stream
        BitSet secondKeyStream = keyStreamGenerator.getNextKeyStream();

        // Assert that consecutive key streams are different
        assertNotEquals(firstKeyStream, secondKeyStream, "Consecutive key streams should be different");
    }
}
