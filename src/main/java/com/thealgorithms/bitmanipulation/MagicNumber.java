package com.thealgorithms.bitmanipulation;

/*
Nth magic number can be determined using the binary representation of N. It can be explained via examples:
                N                5³              5²               5¹                 Magic Number
                1       -        0               0                1          =           5
                2       -        0               1                0          =           25
                3       -        0               1                1          =           30
                4       -        1               0                0          =           125
                5       -        1               0                1          =           130

Therefore, Nth magic no. is calculated as: taking N = 6 = (110) in binary = (5³ x 1) + (5² x 1) + (5¹ x 0) = 150
 */

public class MagicNumber {

    public static int magicNum (int n) {

        int ans = 0, base = 5;

        while (n > 0) {

            int last = n & 1;
            ans += (last * base);
            base *= 5;
            n = n >> 1;
        }
        return ans;
    }
}
