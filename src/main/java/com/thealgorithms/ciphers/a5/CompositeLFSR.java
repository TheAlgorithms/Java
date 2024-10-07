package com.thealgorithms.ciphers.a5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The CompositeLFSR class represents a composite implementation of
 * Linear Feedback Shift Registers (LFSRs) for cryptographic purposes.
 *
 * <p>
 * This abstract class manages a collection of LFSR instances and
 * provides a mechanism for irregular clocking based on the
 * majority bit among the registers. It implements the BaseLFSR
 * interface, requiring subclasses to define specific LFSR behaviors.
 * </p>
 */
public abstract class CompositeLFSR implements BaseLFSR {

    protected final List<LFSR> registers = new ArrayList<>();

    /**
     * Performs a clocking operation on the composite LFSR.
     *
     * <p>
     * This method determines the majority bit across all registers and
     * clocks each register based on its clock bit. If a register's
     * clock bit matches the majority bit, it is clocked (shifted).
     * The method also computes and returns the XOR of the last bits
     * of all registers.
     * </p>
     *
     * @return the XOR value of the last bits of all registers.
     */
    @Override
    public boolean clock() {
        boolean majorityBit = getMajorityBit();
        boolean result = false;
        for (var register : registers) {
            result ^= register.getLastBit();
            if (register.getClockBit() == majorityBit) {
                register.clock();
            }
        }
        return result;
    }

    /**
     * Calculates the majority bit among all registers.
     *
     * <p>
     * This private method counts the number of true and false clock bits
     * across all LFSR registers. It returns true if the count of true
     * bits is greater than or equal to the count of false bits; otherwise,
     * it returns false.
     * </p>
     *
     * @return true if the majority clock bits are true; false otherwise.
     */
    private boolean getMajorityBit() {
        Map<Boolean, Integer> bitCount = new TreeMap<>();
        bitCount.put(Boolean.FALSE, 0);
        bitCount.put(Boolean.TRUE, 0);

        registers.forEach(lfsr -> bitCount.put(lfsr.getClockBit(), bitCount.get(lfsr.getClockBit()) + 1));
        return bitCount.get(Boolean.FALSE) <= bitCount.get(Boolean.TRUE);
    }
}
