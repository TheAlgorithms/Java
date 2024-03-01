// CountSetBits.java
package com.thealgorithms.bitmanipulation;

public final class CountSetBits {
    private CountSetBits() {
    }
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }
}
