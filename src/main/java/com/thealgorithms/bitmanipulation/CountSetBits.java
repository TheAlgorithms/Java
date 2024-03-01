// CountSetBits.java
package com.thealgorithms.bitmanipulation;

public final class CountSetBits {
    private CountSetBits() {
    }

    /*
    * CountSetBits class provides a method to count the number of set bits (1s) in an integer.
    * Implementation by Pankaj Kumar Bind (https://github.com/Pankaj-Bind).
    */
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }
}
