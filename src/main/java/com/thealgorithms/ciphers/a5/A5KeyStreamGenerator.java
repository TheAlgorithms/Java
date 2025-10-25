package com.thealgorithms.ciphers.a5;

import java.util.BitSet;

/**
 * The A5KeyStreamGenerator class is responsible for generating key streams
 * for the A5/1 encryption algorithm using a combination of Linear Feedback Shift Registers (LFSRs).
 *
 * <p>
 * This class extends the CompositeLFSR and initializes a set of LFSRs with
 * a session key and a frame counter to produce a pseudo-random key stream.
 * </p>
 *
 * <p>
 * Note: Proper exception handling for invalid usage is to be implemented.
 * </p>
 */
public class A5KeyStreamGenerator extends CompositeLFSR {

    private BitSet initialFrameCounter;
    private BitSet frameCounter;
    private BitSet sessionKey;
    private static final int INITIAL_CLOCKING_CYCLES = 100;
    private static final int KEY_STREAM_LENGTH = 228;

    /**
     * Initializes the A5KeyStreamGenerator with the specified session key and frame counter.
     *
     * <p>
     * This method sets up the internal state of the LFSRs using the provided
     * session key and frame counter. It creates three LFSRs with specific
     * configurations and initializes them.
     * </p>
     *
     * @param sessionKey a BitSet representing the session key used for key stream generation.
     * @param frameCounter a BitSet representing the frame counter that influences the key stream.
     */
    @Override
    public void initialize(BitSet sessionKey, BitSet frameCounter) {
        this.sessionKey = sessionKey;
        this.frameCounter = (BitSet) frameCounter.clone();
        this.initialFrameCounter = (BitSet) frameCounter.clone();
        registers.clear();
        LFSR lfsr1 = new LFSR(19, 8, new int[] {13, 16, 17, 18});
        LFSR lfsr2 = new LFSR(22, 10, new int[] {20, 21});
        LFSR lfsr3 = new LFSR(23, 10, new int[] {7, 20, 21, 22});
        registers.add(lfsr1);
        registers.add(lfsr2);
        registers.add(lfsr3);
        registers.forEach(lfsr -> lfsr.initialize(sessionKey, frameCounter));
    }

    /**
     * Re-initializes the key stream generator with the original session key
     * and frame counter. This method restores the generator to its initial
     * state.
     */
    public void reInitialize() {
        this.initialize(sessionKey, initialFrameCounter);
    }

    /**
     * Generates the next key stream of bits.
     *
     * <p>
     * This method performs an initial set of clocking cycles and then retrieves
     * a key stream of the specified length. After generation, it re-initializes
     * the internal registers.
     * </p>
     *
     * @return a BitSet containing the generated key stream bits.
     */
    public BitSet getNextKeyStream() {
        for (int cycle = 1; cycle <= INITIAL_CLOCKING_CYCLES; ++cycle) {
            this.clock();
        }

        BitSet result = new BitSet(KEY_STREAM_LENGTH);
        for (int cycle = 1; cycle <= KEY_STREAM_LENGTH; ++cycle) {
            boolean outputBit = this.clock();
            result.set(cycle - 1, outputBit);
        }

        reInitializeRegisters();
        return result;
    }

    /**
     * Re-initializes the registers for the LFSRs.
     *
     * <p>
     * This method increments the frame counter and re-initializes each LFSR
     * with the current session key and frame counter.
     * </p>
     */
    private void reInitializeRegisters() {
        incrementFrameCounter();
        registers.forEach(lfsr -> lfsr.initialize(sessionKey, frameCounter));
    }

    /**
     * Increments the current frame counter.
     *
     * <p>
     * This method uses a utility function to increment the frame counter,
     * which influences the key stream generation process.
     * </p>
     */
    private void incrementFrameCounter() {
        Utils.increment(frameCounter, FRAME_COUNTER_LENGTH);
    }

    /**
     * Retrieves the current frame counter.
     *
     * @return a BitSet representing the current state of the frame counter.
     */
    public BitSet getFrameCounter() {
        return frameCounter;
    }
}
