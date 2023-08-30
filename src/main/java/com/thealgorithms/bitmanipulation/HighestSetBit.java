package com.thealgorithms.bitmanipulation;
import java.util.Optional;

/**
 * Find Highest Set Bit
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class HighestSetBit {
    private HighestSetBit() {
    }

    public final static Optional<Integer> findHighestSetBit(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Input cannot be negative");
        }

        if (num == 0) {
            return Optional.empty();
        }

        int position = 0;
        while (num > 0) {
            num >>= 1;
            position++;
        }
        
        return Optional.of(position - 1);
    }
}