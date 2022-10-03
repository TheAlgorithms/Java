package com.thealgorithms.ciphers.a5;

// Source http://www.java2s.com/example/java-utility-method/bitset/increment-bitset-bits-int-size-9fd84.html
//package com.java2s;
//License from project: Open Source License

import java.util.BitSet;

public class Utils {

    public static boolean increment(BitSet bits, int size) {
        int i = size - 1;
        while (i >= 0 && bits.get(i)) {
            bits.set(i--, false);/*from w w w  . j a  v a  2s  .c o  m*/
        }
        if (i < 0) {
            return false;
        }
        bits.set(i, true);
        return true;
    }
}
