package com.thealgorithms.bitmanipulation;

import java.lang.Math;
/**
 * Complement of Base 10 Number
 * For a given positive number N in base-10, return the
 * complement of its binary representation as a base-10 integer.
 * @author Akshit Kumar Chandora (https://github.com/axitchandora)
 */

public class ComplementOfBaseTenNumber {
    public static int bitwiseComplement(int num) {
        // count number of total bits in 'num'
        int bitCount = 0;
        int n = num;
        while (n > 0) {
            bitCount++;
            n = n >> 1;
        }

        // for a number which is a complete power of '2' i.e., it can be written as
        // pow(2, n), if we subtract '1' from such a number, we get a number which
        // has 'n' least significant bits set to '1'. For example, '4' which is a
        // complete power of '2', and '3' (which is one less than 4) has a binary
        // representation of '11' i.e., it has '2' least significant bits set to '1'
        int all_bits_set = (int) Math.pow(2, bitCount) - 1;

        // from the solution description: complement = number ^ all_bits_set
        return num ^ all_bits_set;
    }
  }

/*
 *#Time Complexity
 *Time complexity of this solution is O(b) where ‘b’ is the number of bits
 *required to store the given number.
 *
 *#Space Complexity
 *Space complexity of this solution is O(1).
 */
